package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.Script;
import com.app.services.RobotServices;

@Controller
public class TestCaseController {
	
	@Autowired
	private RobotServices robotServices;
	
	
	
	  @RequestMapping(path = "/executescenario", method =  { RequestMethod.GET, RequestMethod.POST }	)
	    public String executescenario(Model model,HttpServletRequest request,@ModelAttribute("script") Script s) {
		  model.addAttribute("scripts",robotServices.selectAllScripts());
		  model.addAttribute("browsers",robotServices.selectBrowsers());
		  return "executeScenario";
	  }
}
