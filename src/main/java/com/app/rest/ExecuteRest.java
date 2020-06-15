package com.app.rest;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.app.services.DeferredResultService;

@RestController
@RequestMapping("/execute")
public class ExecuteRest {
	

	private final ConcurrentHashMap<String,DeferredResult<ResponseEntity<String>>> map = new ConcurrentHashMap<String, DeferredResult<ResponseEntity<String>>>();
	
	@Autowired
	private DeferredResultService deferedResultService;
	
	
	@RequestMapping(value="/run",method = RequestMethod.GET)
	public DeferredResult <ResponseEntity <String>> handle(HttpServletRequest request) {
		 String reg_spaj  = ServletRequestUtils.getStringParameter(
			        request, "reg_spaj", "");
		 
		 DeferredResult<ResponseEntity< String>> deferredResult = new DeferredResult<ResponseEntity<String>>(100000L);
		 
		 if(!reg_spaj.equals("")) {
		// map.put(reg_spaj, deferredResult);
			 try {
			 deferedResultService.update(deferredResult);
			 }catch(Exception e) {
				 deferredResult.setResult(new ResponseEntity<String>(e.getMessage(),HttpStatus.OK));
			 }
		 }else {
			 deferredResult.setResult(new ResponseEntity<String>("Please Try Then", HttpStatus.OK));
		 }
		
		 deferredResult.onTimeout(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				map.remove(reg_spaj);
				deferredResult.setResult(new ResponseEntity<String>("Timeout ya", HttpStatus.OK));
			}
		});
		 
		return deferredResult;
	}
	
	

	@RequestMapping(value="/subscribe",method = RequestMethod.GET)
	public String subscribe(HttpServletRequest request) {
		deferedResultService.subscribe();
		return"OK";
		
	}
	
	@RequestMapping(value="/terminate",method = RequestMethod.GET)
	public String terminate(HttpServletRequest request) {
		 String reg_spaj  = ServletRequestUtils.getStringParameter(
			        request, "reg_spaj", "");
		 String chat  = ServletRequestUtils.getStringParameter(
			        request, "chat", "");
		 
		 
		 DeferredResult<ResponseEntity<String>> term = (DeferredResult<ResponseEntity<String>>)map.get(reg_spaj);
		 if(term != null) {
			 
			 term.setResult(new ResponseEntity<String>("Success ya"+chat, HttpStatus.OK));
			 map.remove(reg_spaj);
			 return "successfully terminate";
		 }
		
		 
		return "ok";
	}
	@RequestMapping(value="/message",method = RequestMethod.GET)
	public String message(HttpServletRequest request) {
		 String chat  = ServletRequestUtils.getStringParameter(
			        request, "chat", "");
		 deferedResultService.updateString(chat);
		 
		 
		return "ok";
	}

}
