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

@Service
public class TestCaseService {
	

	@Async
    public CompletableFuture<ResultTestCase> execute(TestCase testcase,ResultTestCase resultTestCase) throws Exception {
		
		
		WebDriver driver = null;
		
		try {
			
			driver = new RemoteWebDriver(new URL("http://localhost:4545/wd/hub"),
					  new ChromeOptions());
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
		}catch(MalformedURLException e) {
			
		}
		
		
		return CompletableFuture.completedFuture(resultTestCase);
	}
}
