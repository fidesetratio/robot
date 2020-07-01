package com.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.model.Parameter;
import com.app.model.ResultTestCase;
import com.app.model.Script;
import com.app.model.TestCase;
import com.app.utils.GroovyUtilsBinding;
import com.app.utils.ParameterUtils;
import com.app.utils.TestManager;
import com.app.utils.WebDriverUtils;

@Service
public class TestCaseService {
	
	@Autowired
	private RobotServices robotServices;
	
	
	public String generateRandomKey() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	};
	
	
	
	
	
	
	@Async
	public CompletableFuture<ResultTestCase> execute(WebDriver driver, Script script, String referensi,String prefix, Long parameterId){
		
		ResultTestCase resultTestCase = new ResultTestCase();
		resultTestCase.setReff_id(referensi);
		resultTestCase.setPrefix(prefix);
	
		try {
			TestManager testManager = new TestManager(driver, "img/",referensi, robotServices, prefix);
			HashMap<String,Object> m = new HashMap<String,Object>();		 
			List<Parameter> parameters = new ArrayList<Parameter>();
			if(parameterId>0) {
				
				parameters = robotServices.selectParameterByCategoryId(parameterId);
			}
			ParameterUtils parameterUtils = new ParameterUtils(parameters);
			m.put("driver", driver);
			m.put("result", resultTestCase);
			m.put("tm", testManager);
			m.put("p",parameterUtils);
			GroovyUtilsBinding groovyBinding = new GroovyUtilsBinding(m);
			groovyBinding.addLib("import java.util.concurrent.TimeUnit");
			groovyBinding.addLib("import org.openqa.selenium.WebDriver");
			groovyBinding.addLib("import org.openqa.selenium.WebElement");
			groovyBinding.addLib("import org.openqa.selenium.NoAlertPresentException");
			groovyBinding.addLib("import org.openqa.selenium.JavascriptExecutor");
			groovyBinding.addLib("import org.openqa.selenium.By");
			groovyBinding.addLib("import org.openqa.selenium.Alert");
			groovyBinding.addLib("import org.openqa.selenium.Alert");
			groovyBinding.addLib("import org.openqa.selenium.support.ui.ExpectedConditions");
			groovyBinding.addLib("import org.openqa.selenium.support.ui.Select");
			groovyBinding.addLib("import org.openqa.selenium.support.ui.WebDriverWait");
			if(script!= null) {
			Object object = groovyBinding.run(script.getScripts());
			if(object != null) {
				if(object instanceof ResultTestCase) {
					resultTestCase  = (ResultTestCase)object;
				}
			};
			
			};
			
		}catch(Exception e) {
			resultTestCase.setPass(-1);
			resultTestCase.setStatus(e.getMessage());
		}
		return CompletableFuture.completedFuture(resultTestCase);
	}
	

	@Async
    public CompletableFuture<ResultTestCase> execute(TestCase testcase,ResultTestCase resultTestCase,Script script,String refrensi) throws Exception {
		
		
		WebDriver driver = null;
	//	resultTestCase.setReffId(refrensi);
		robotServices.insertResultTestCaseReff(testcase.getId(),refrensi);;
		
		
		
		if(testcase.getType_browser() == 1 ) { // internet explorer
			driver = WebDriverUtils.internetExplorer(testcase.getRemote(),true);
		}
		if(testcase.getType_browser() == 3 ) { // chrome
			driver = WebDriverUtils.chrome(testcase.getRemote(),true);
		};
		
		if(testcase.getType_browser() == 2 ) { // mozilla
			driver = WebDriverUtils.mozilla(testcase.getRemote(),true);
		};
		
		HashMap<String,Object> m = new HashMap<String,Object>();		 
		m.put("driver", driver);
		m.put("result", resultTestCase);
		
		GroovyUtilsBinding groovyBinding = new GroovyUtilsBinding(m);
		groovyBinding.addLib("import java.util.concurrent.TimeUnit;");
		groovyBinding.addLib("import org.openqa.selenium.WebDriver;");
		Object object = groovyBinding.run(script.getScripts());
		if(object instanceof ResultTestCase) {
			resultTestCase  = (ResultTestCase)object;
		}
	
		
		
		return CompletableFuture.completedFuture(resultTestCase);
	}
}
