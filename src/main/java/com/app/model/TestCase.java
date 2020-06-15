package com.app.model;

public class TestCase {
	private Long id;
	private int typecase;
	private String remote;
	private Long type_browser;
	private Long script_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public int getTypecase() {
		return typecase;
	}
	public void setTypecase(int typecase) {
		this.typecase = typecase;
	}
	public String getRemote() {
		return remote;
	}
	public void setRemote(String remote) {
		this.remote = remote;
	}
	public Long getType_browser() {
		return type_browser;
	}
	public void setType_browser(Long type_browser) {
		this.type_browser = type_browser;
	}
	public Long getScript_id() {
		return script_id;
	}
	public void setScript_id(Long script_id) {
		this.script_id = script_id;
	}

}
