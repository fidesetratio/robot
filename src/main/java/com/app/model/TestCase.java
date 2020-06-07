package com.app.model;

public class TestCase {
	private Long id;
	private String script;
	private int typecase;
	private String remote_ip;
	private String remote_port;
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
	public String getRemote_ip() {
		return remote_ip;
	}
	public void setRemote_ip(String remote_ip) {
		this.remote_ip = remote_ip;
	}
	public String getRemote_port() {
		return remote_port;
	}
	public void setRemote_port(String remote_port) {
		this.remote_port = remote_port;
	}

}
