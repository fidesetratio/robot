package com.app.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
public class GroovyUtilsBinding {
		private GroovyShell shell;
		private Binding binding;
		
		private List<String> libs;
		public GroovyUtilsBinding() {
			this.binding = new Binding();
			this.shell = new GroovyShell(this.binding);
			this.libs = new ArrayList<String>();
		}
		
		public GroovyUtilsBinding(HashMap<String,Object> params) {
			this.binding = new Binding();
			this.shell = new GroovyShell(this.binding);
			this.setVariableFroParams(params);
			this.libs = new ArrayList<String>();
	
		}

		private void setVariableFroParams(HashMap<String,Object> params) {
			for(String keys:params.keySet()) {
				this.binding.setVariable(keys, params.get(keys));
			}
		}
		
		public Object run(String scripts) {
			scripts = createAllScripts(scripts);
			System.out.println("null yaiids"+scripts);
			
			Object object = shell.evaluate(scripts);
			return object;
		}
		public void addLib(String packageName) {
			this.libs.add(packageName);
		}
		
		
		private String createAllScripts(String scripts) {
			StringBuffer buffer = new StringBuffer();	
			if(libs.size()>0) {
				for(String l:libs) {
					buffer.append(l);
					buffer.append(";");
				}
				buffer = buffer.deleteCharAt(buffer.toString().length()-1);
			};
			buffer.append("\n");
			
			
			buffer.append(scripts);
			
			return buffer.toString();
			
		}
}
