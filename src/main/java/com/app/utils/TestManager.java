package com.app.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.app.model.ResultTestCase;
import com.app.services.RobotServices;

public class TestManager {
	
	private WebDriver driver;
	private String pathScreenShot;
	private String refferensiId;
	private RobotServices robotServices;
	private String prefix;
	public TestManager(WebDriver driver, String pathScreenshot,String refferensiId, RobotServices robotServices,String prefix) {
		this.driver = driver;
		this.pathScreenShot = pathScreenshot;
		this.refferensiId = refferensiId;
		this.robotServices = robotServices;
		this.prefix = prefix;
	}
	
	
	public void result(String caseName, boolean pas, String description) {
		  ResultTestCase result = new ResultTestCase();
		  result.setReff_id(this.refferensiId);
		  result.setPrefix(this.prefix);
		  result.setPass(pas==true?1:0);
		  result.setDescription(description);
		  result.setCase_name(caseName);
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  String fileName=generateRandomName("png");
		  
		  String path = pathScreenShot+fileName;
		  System.out.println("path="+path);
		  try {
			FileUtils.copyFile(scrFile, new File(path));
			result.setPhoto_id(fileName);
		  } catch (IOException e) {
			  result.setPass(-1);
			  result.setStatus(e.getMessage());
		  }
		  robotServices.insertResultTestCase(result);
		  
	}
		  
	private String generateRandomName(String type) {
		String DATE_FORMAT="yyyyMMdd_HHmmss-SSS";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		String timeStamp= sdf.format(new Date());
		java.util.Random nahcis = new java.util.Random();
		String randomNumber =  Integer.toString(nahcis.nextInt());
		return "img_" + timeStamp + "_" + randomNumber+"."+type;
	
	}

}
