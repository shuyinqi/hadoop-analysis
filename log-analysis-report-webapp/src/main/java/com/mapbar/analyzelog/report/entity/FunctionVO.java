package com.mapbar.analyzelog.report.entity;

public class FunctionVO {
	private int id;
	private int fatherid;
	private String name;
	private int flevel;
	private String link;
	private String fn;
	public final int getId() {
		return id;
	}
	public final void setId(int id) {
		this.id = id;
	}
	public final int getFatherid() {
		return fatherid;
	}
	public final void setFatherid(int fatherid) {
		this.fatherid = fatherid;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final int getFlevel() {
		return flevel;
	}
	public final void setFlevel(int flevel) {
		this.flevel = flevel;
	}
	public final String getLink() {
		return link;
	}
	public final void setLink(String link) {
		this.link = link;
	}
	public final String getFn() {
		return fn;
	}
	public final void setFn(String fn) {
		this.fn = fn;
	}
	
}
