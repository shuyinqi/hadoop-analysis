package com.mapbar.analyzelog.model;

import java.util.List;

public class Terminate {
	private String t;
	private String sid;
	private String dt;
	private List<String> atvs;
	
	public final String getT() {
		return t;
	}
	public final void setT(String t) {
		this.t = t;
	}
	public final String getSid() {
		return sid;
	}
	public final void setSid(String sid) {
		this.sid = sid;
	}

	public final String getDt() {
		return dt;
	}
	public final void setDt(String dt) {
		this.dt = dt;
	}
	public final List<String> getAtvs() {
		return atvs;
	}
	public final void setAtvs(List<String> atvs) {
		this.atvs = atvs;
	}
	
}
