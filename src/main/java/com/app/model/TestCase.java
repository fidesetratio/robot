package com.app.model;

public class TestCase {
	private Long id;
	private String script;
	private int typecase;
	private String remote;
	private Long type_browser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
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

}
