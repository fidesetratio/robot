package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.BrowserDriver;
import com.app.services.RobotServices;

@Controller
public class BrowsersController {
	@Autowired
	private RobotServices robotServices;
	
	  @RequestMapping(path = "/browsers", method =  { RequestMethod.GET, RequestMethod.POST }	)
	    public String browsers(Model model,HttpServletRequest request) {
		  model.addAttribute("listbrowsers",robotServices.selectBrowsers());  
		  
		  return "browsers";
	  	}
	  

	 @RequestMapping(path = "/editbrowser", method =  { RequestMethod.GET, RequestMethod.POST }	)
    public String editbrowser(Model model,HttpServletRequest request,@ModelAttribute("browser") BrowserDriver br) {
		  BrowserDriver browserDriver = new BrowserDriver();
		  String action = "add";
		  Integer error = 0;
		  String message_error = "";
		  Integer back = 0;
		  String back_url = "/browsers";
		  String formAction="addBrowser";
		  Long browser_id = ServletRequestUtils.getLongParameter(request,"browser_id",new Long(0));
		  String act = ServletRequestUtils.getStringParameter(request,"action","");
		
		  
		  if(browser_id>0) {
			  browserDriver = robotServices.selectBrowserDriverById(browser_id);
			  action="update";
		  }
		 
		  if(act.equals("add")) {
			  robotServices.insertBrowserDriver(br);
			  error = 1;
			  back = 1;
			  formAction = "forms/addbrowser";
			  message_error = "Succesfully added";
		  }else if(act.equals("update")) {
			  br.setBrowser_id(browser_id);
			  robotServices.updateBrowserDriver(br);
			  back = 1;
			  error = 1;
			  back_url = "/browsers";
			  formAction = "forms/addbrowser";
			  message_error = "Succesfully edit";
		  
		  }else if(act.equals("delete")) {
			  browserDriver.setBrowser_id(browser_id);
			  robotServices.deleteBrowserId(browserDriver);
			  return "redirect:/browsers";
		  }
		  
		  
		
		model.addAttribute("back",back);
		model.addAttribute("back_url",back_url);
		
		model.addAttribute("message_error",message_error);  
		model.addAttribute("error",error);  
		model.addAttribute("action",action);
		model.addAttribute("browser",browserDriver); 
		return formAction;
		  
		  
		  
	  }
	
}
