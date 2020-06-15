package com.app.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.BrowserDriver;
import com.app.model.ResultTestCase;
import com.app.model.Script;
import com.app.model.TestCase;
import com.app.services.RobotServices;
import com.app.services.TestCaseService;
import com.app.utils.WebDriverUtils;

@RestController
@RequestMapping("/robot")
public class RobotRest {
	
	@Autowired
	private RobotServices robotServices;
	
	@Autowired
	private TestCaseService testCaseServices;
	
	
	
	
	
	
	@RequestMapping(value="/results",method = RequestMethod.GET)
	public List<ResultTestCase> results(HttpServletRequest request){
		String prefix  = ServletRequestUtils.getStringParameter(
		        request, "prefix", "default");
		String refrensi  = ServletRequestUtils.getStringParameter(
		        request, "refrensi", "");
		
	 
		ArrayList<ResultTestCase> testcases = new ArrayList<ResultTestCase>();
		testcases = (ArrayList<ResultTestCase>) robotServices.selectResultByReffAndPrefix(refrensi, prefix);
		
		return testcases;
	}
	
	
	@RequestMapping(value="/exec",method = RequestMethod.GET)
	public ResultTestCase exec(HttpServletRequest request) {
		 Long browser_id  = ServletRequestUtils.getLongParameter(
			        request, "browser_id", 0);
		 Long script_id  = ServletRequestUtils.getLongParameter(
			        request, "script_id", 0);
		 
		 Long timeout  = ServletRequestUtils.getLongParameter(
			        request, "timeout", 10000);
		 
		 String prefix  = ServletRequestUtils.getStringParameter(
			        request, "prefix", "default");
		 
		 
		 
		 
		 
		 
		 
		 System.out.println("browserId:"+browser_id);
		 System.out.println("script_id:"+script_id);
		  
		 String refferensi = testCaseServices.generateRandomKey();
		 ResultTestCase resultTestCase = new ResultTestCase();
		 resultTestCase.setReff_id(refferensi);
		 resultTestCase.setPrefix(prefix);
		 
		 
		 BrowserDriver browserDriver = robotServices.selectBrowserDriverById(browser_id);
		 Script script = robotServices.selectScriptById(script_id);
		 CompletableFuture<ResultTestCase> r = new CompletableFuture<ResultTestCase>();
		 try { 
		 WebDriver webDriver = WebDriverUtils.basedOnBrowserDriver(browserDriver,false);
		 r = testCaseServices.execute(webDriver, script, refferensi,prefix);
		 r.complete(resultTestCase);
		
		 return r.get(timeout,TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			resultTestCase.setPass(-1);
			resultTestCase.setStatus(e.getMessage());
		} catch (ExecutionException e) {
			resultTestCase.setPass(-1);
			resultTestCase.setStatus(e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();	
			resultTestCase.setPass(-1);
			resultTestCase.setStatus(e.getMessage());
		}
		 return resultTestCase;
	};
	
	
	
	@RequestMapping(value="/run",method = RequestMethod.GET)
	public ResultTestCase run(HttpServletRequest request) {
		 Long id  = ServletRequestUtils.getLongParameter(
			        request, "id", 0);
		 ResultTestCase resultTestCase = new ResultTestCase();
			
		 try {
			 TestCase testCase = new TestCase();
			 
			 if(id>0) {
							 testCase = robotServices.selectTestCaseById(id);
							 if(testCase != null) {
								 CompletableFuture<ResultTestCase> r = new CompletableFuture<ResultTestCase>();
								 Script script =  new Script();
								 String refferensi = testCaseServices.generateRandomKey();
								 
								 if(testCase.getScript_id()>0) {
									 script = robotServices.selectScriptById(testCase.getScript_id());
								 }
								 r = testCaseServices.execute(testCase,resultTestCase,script,refferensi);
								 r.complete(resultTestCase);
								 return r.get();
							 }
							
				 };
	
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
