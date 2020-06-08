package com.app.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.model.ResultTestCase;
import com.app.model.TestCase;
import com.app.utils.GroovyUtilsBinding;
import com.app.utils.WebDriverUtils;

@Service
public class TestCaseService {
	

	@Async
    public CompletableFuture<ResultTestCase> execute(TestCase testcase,ResultTestCase resultTestCase) throws Exception {
		
		
		WebDriver driver = null;
		
		if(testcase.getType_browser() == 1 ) { // internet explorer
			driver = WebDriverUtils.internetExplorer(testcase.getRemote());
		}
		if(testcase.getType_browser() == 3 ) { // chrome
			driver = WebDriverUtils.chrome(testcase.getRemote());
		}
		
		if(testcase.getType_browser() == 2 ) { // mozilla
			driver = WebDriverUtils.mozilla(testcase.getRemote());
		}
		
		HashMap<String,Object> m = new HashMap<String,Object>();		 
		m.put("driver", driver);
		m.put("result", resultTestCase);
		
		GroovyUtilsBinding groovyBinding = new GroovyUtilsBinding(m);
		groovyBinding.addLib("import java.util.concurrent.TimeUnit");
		groovyBinding.addLib("import org.openqa.selenium.WebDriver");

		Object object = groovyBinding.run(testcase.getScript());
	
		if(object instanceof ResultTestCase) {
			resultTestCase  = (ResultTestCase)object;
		}
		
		
		return CompletableFuture.completedFuture(resultTestCase);
	}
}
