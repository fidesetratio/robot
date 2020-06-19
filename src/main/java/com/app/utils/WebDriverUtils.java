package com.app.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.app.model.BrowserDriver;

public class WebDriverUtils {
	
	public static WebDriver mozilla(String remoteUrl,boolean headless) {
		WebDriver driver = null;
		
		return driver;
	}
	
	
	public static WebDriver basedOnBrowserDriver(BrowserDriver driver,boolean headless) {
		WebDriver dr = null;
		
		if(driver != null) {
				switch(driver.getBrowser_type()) {
				case 0:
					dr = WebDriverUtils.internetExplorer(driver.getRemote_url(),headless);
				break;
				case 1:
					dr = WebDriverUtils.chrome(driver.getRemote_url(),headless);
				break;
				}
			
		}
	
		return dr;
		
	}
	
	public static WebDriver internetExplorer(String remoteUrl,boolean headless) {
		DesiredCapabilities cap = new DesiredCapabilities();
     	cap = DesiredCapabilities.internetExplorer();
     	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		 cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
         cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); //disable protected mode settings
         cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true); //disable protected mode settings
         cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true); //disable protected mode settings
         cap.setCapability(
        		 InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
        		 true);
         
         WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(remoteUrl), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return driver;
	}
	public static WebDriver chrome(String remoteUrl,boolean headless) {
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(remoteUrl),
					  new ChromeOptions());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 return driver;
	}
	
}
