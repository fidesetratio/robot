package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.services.RobotServices;

@Controller
public class CategoryParameterController {
	@Autowired
	private RobotServices robotServices;

	  @RequestMapping(path = "/categoryparameter", method =  { RequestMethod.GET, RequestMethod.POST }	)
	    public String categoryparameter(Model model,HttpServletRequest request) {
		  model.addAttribute("listcategoryparameter",robotServices.selectAllCategoryParameters());  
		  return "categoryParameter";
	  	}
	  

	  	@RequestMapping(path = "/editcategory", method =  { RequestMethod.GET, RequestMethod.POST }	)
	    public String editcategory(Model model,HttpServletRequest request) {
		  model.addAttribute("editcategory",robotServices.selectAllCategoryParameters());  
		  return "categoryParameter";
	  	}
}
