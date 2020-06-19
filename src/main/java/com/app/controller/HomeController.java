package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Script;
import com.app.services.RobotServices;

@Controller
public class HomeController {

	@Autowired
	private RobotServices robotServices;
	
	@GetMapping({"/home"})
    public String home(Model model,HttpServletRequest request) {
		return "homePage";
	}
	
	  @RequestMapping(path = "/editscript", method =  { RequestMethod.GET, RequestMethod.POST }	)
    public String editScript(Model model,HttpServletRequest request,@ModelAttribute("script") Script s) {
		  
		  Script script = new Script();
		  String action = "add";
		  Integer error = 0;
		  String message_error = "";
		  Integer back = 0;
		  String back_url = "/scripts";
		  
		  String formAction="addScript";
		  Long script_id = ServletRequestUtils.getLongParameter(request,"script_id",new Long(0));
		  String act = ServletRequestUtils.getStringParameter(request,"action","");
			
		  if(script_id>0) {
			  script = robotServices.selectScriptById(script_id);
			  action="update";
		  }
		 
		  if(act.equals("add")) {
			  robotServices.insertScript(s);
			  error = 1;
			  
			  formAction = "forms/add";
			  message_error = "Succesfully added";
		  }else if(act.equals("update")) {
			  robotServices.updateScript(s);
			  back = 1;
			  error = 1;
			  back_url = "/scripts";
			  formAction = "forms/add";
			  message_error = "Succesfully edit";
		  }
		  
		model.addAttribute("back",back);
		model.addAttribute("back_url",back_url);
		
		model.addAttribute("message_error",message_error);  
		model.addAttribute("error",error);  
		model.addAttribute("action",action);
		model.addAttribute("script",script); 
		return formAction;
	}
	  @RequestMapping(path = "/scripts", method =  { RequestMethod.GET, RequestMethod.POST }	)
	    public String script(Model model,HttpServletRequest request) {
		  String view = "listScript";
		  model.addAttribute("listscripts",robotServices.selectAllScripts());  
		  
		  return view;
	    }	  
	  
	  @RequestMapping("favicon.ico")
	    @ResponseBody
	    void favicon() {}
		

}
