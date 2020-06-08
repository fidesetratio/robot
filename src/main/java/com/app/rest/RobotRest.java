package com.app.rest;

import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.ResultTestCase;
import com.app.model.TestCase;
import com.app.services.RobotServices;
import com.app.services.TestCaseService;

@RestController
@RequestMapping("/robot")
public class RobotRest {
	
	@Autowired
	private RobotServices robotServices;
	
	@Autowired
	private TestCaseService testCaseServices;
	
	@RequestMapping(value="/run",method = RequestMethod.GET)
	public ResultTestCase run(HttpServletRequest request) {
		 Long id  = ServletRequestUtils.getLongParameter(
			        request, "id", 0);
		 ResultTestCase resultTestCase = new ResultTestCase();
			
		 try {
			 TestCase testCase = new TestCase();
			 
			 CompletableFuture<ResultTestCase> r = new CompletableFuture<ResultTestCase>();
			 if(id>0) {
				 testCase = robotServices.selectTestCaseById(id);
				 r = testCaseServices.execute(testCase,resultTestCase);
			 }else {
				 r.complete(resultTestCase);
			 };
			return r.get();
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return resultTestCase;
	}
	/*
	 * @RequestMapping(value="/run",method = RequestMethod.GET) public TestCase
	 * handle(HttpServletRequest request) { Long id =
	 * ServletRequestUtils.getLongParameter( request, "id", 0);
	 * 
	 * TestCase testCase = new TestCase();
	 * 
	 * if(id >0 ) { testCase = robotServices.selectTestCaseById(id); }
	 * 
	 * GroovyUtilsBinding groovyBinding = new GroovyUtilsBinding();
	 * if(testCase.getScript() != null) { Object object =
	 * groovyBinding.run(testCase.getScript()); if(object == null) {
	 * System.out.println("null nih"); } };
	 * 
	 * 
	 * WebDriver driver = null; ChromeOptions chromeOptions = new ChromeOptions();
	 * chromeOptions.addArguments("--no-sandbox");
	 * chromeOptions.addArguments("window-size=800,600"); DesiredCapabilities
	 * capabilities = DesiredCapabilities.chrome();
	 * capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
	 * 
	 * try { 
	 * driver = new RemoteWebDriver(new URL("http://localhost:4545/wd/hub"),
	 * chromeOptions);
	 * 
	 * // driver = new RemoteWebDriver( new URL("http://127.0.0.1:9515"), new //
	 * ChromeOptions());
	 * 
	 * 
	 * 
	 * } catch (MalformedURLException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } driver.manage().timeouts().implicitlyWait(30,
	 * TimeUnit.SECONDS); driver.get("http://www.google.com"); WebElement searchBox
	 * = driver.findElement(By.name("q")); searchBox.sendKeys("ChromeDriver");
	 * searchBox.submit(); try { Thread.sleep(5000); } catch (InterruptedException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } // Let the
	 * user actually see something! driver.quit();
	 * 
	 * 
	 * return testCase; }
	 */
	

}
