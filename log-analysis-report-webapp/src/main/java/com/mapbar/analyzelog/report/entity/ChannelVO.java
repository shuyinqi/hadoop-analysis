package com.mapbar.analyzelog.report.entity;

public class ChannelVO {
	private int id;
	private int appid;
	private String type;
	private String name;
	private String note;
	private String appname;
	public final int getAppid() {
		return appid;
	}
	public final void setAppid(int appid) {
		this.appid = appid;
	}
	public final String getType() {
		return type;
	}
	public final void setType(String type) {
		this.type = type;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getNote() {
		return note;
	}
	public final void setNote(String note) {
		this.note = note;
	}
	public final String getAppname() {
		return appname;
	}
	public final void setAppname(String appname) {
		this.appname = appname;
	}
	public final int getId() {
		return id;
	}
	public final void setId(int id) {
		this.id = id;
	}
	
}
