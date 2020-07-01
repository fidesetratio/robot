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
import com.app.model.CategoryParameter;
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
	    public String editcategory(Model model,HttpServletRequest request,@ModelAttribute("categoryParameter") CategoryParameter cat) {
			  CategoryParameter categoryParameter = new CategoryParameter();
			  String action = "add";
			  Integer error = 0;
			  String message_error = "";
			  Integer back = 0;
			  String back_url = "/categoryparameter";
			  String formAction="addCategoryParameter";
			  Long cat_id = ServletRequestUtils.getLongParameter(request,"cat_id",new Long(0));
			  String act = ServletRequestUtils.getStringParameter(request,"action","");
			  
			  
			  
			  if(cat_id>0) {
				  categoryParameter = robotServices.selectCategoryParameterById(cat_id);
				  action="update";
			  }
			  
			  if(act.equals("add")) {
				  robotServices.insertCategoryParameter(cat);
				  error = 1;
				  back = 1;
				  formAction = "forms/addparametercategory";
				  message_error = "Succesfully added";
			  }else if(act.equals("update")) {
				  cat.setCat_id(cat_id);
				  robotServices.updateCategoryParameter(cat);
				  back = 1;
				  error = 1;
				  back_url = "/categoryparameter";
				  formAction = "forms/addparametercategory";
				  message_error = "Succesfully edit";
			  
			  }else if(act.equals("delete")) {
				  categoryParameter.setCat_id(cat_id);
				  robotServices.deleteCategoryParameter(categoryParameter);
				  robotServices.deleteParametersByCatId(cat_id);
				  return "redirect:/categoryparameter";
			  }
			  
			  
			
			model.addAttribute("back",back);
			model.addAttribute("back_url",back_url);
			
			model.addAttribute("message_error",message_error);  
			model.addAttribute("error",error);  
			model.addAttribute("action",action);
			model.addAttribute("categoryParameter",categoryParameter); 
			return formAction;
	  	}
}
