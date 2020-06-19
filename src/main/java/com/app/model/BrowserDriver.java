package com.app.model;

public class BrowserDriver {
 private Long browser_id;
 private String browser_name;
 private Integer browser_type;
 private String remote_url;
 
 public BrowserDriver() {
	 browser_type = 0;
 }
public Long getBrowser_id() {
	return browser_id;
}
public void setBrowser_id(Long browser_id) {
	this.browser_id = browser_id;
}
public String getBrowser_name() {
	return browser_name;
}
public void setBrowser_name(String browser_name) {
	this.browser_name = browser_name;
}
public Integer getBrowser_type() {
	return browser_type;
}
public void setBrowser_type(Integer browser_type) {
	this.browser_type = browser_type;
}
public String getRemote_url() {
	return remote_url;
}
public void setRemote_url(String remote_url) {
	this.remote_url = remote_url;
}
 
}
