package com.app.model;

import java.util.Date;

public class ResultTestCase {
	
	private String reff_id;
	
	private String photo_id;
	
	private String description;
	
	private Integer pass;
	
	private String status;
	
	private String prefix;
	
	private String case_name;
	
	private Integer penanda;
	
	private Date created_on;

	
	public ResultTestCase() {
	
		status = "";
		penanda = 0;
	}


	public String getReff_id() {
		return reff_id;
	}

	public void setReff_id(String reff_id) {
		this.reff_id = reff_id;
	}

	public String getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPass() {
		return pass;
	}

	public void setPass(Integer pass) {
		this.pass = pass;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getCase_name() {
		return case_name;
	}

	public void setCase_name(String case_name) {
		this.case_name = case_name;
	}


	public Integer getPenanda() {
		return penanda;
	}


	public void setPenanda(Integer penanda) {
		this.penanda = penanda;
	}


	public Date getCreated_on() {
		return created_on;
	}


	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	
}
