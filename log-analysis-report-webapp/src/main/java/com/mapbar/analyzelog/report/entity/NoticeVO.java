package com.mapbar.analyzelog.report.entity;

public class NoticeVO {
	private int id;
	private int appid;
	private String note;
	private String name;
	private int flag;
	private String date;
	public final int getAppid() {
		return appid;
	}
	public final void setAppid(int appid) {
		this.appid = appid;
	}
	public final String getNote() {
		return note;
	}
	public final void setNote(String note) {
		this.note = note;
	}
	public final int getId() {
		return id;
	}
	public final void setId(int id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final int getFlag() {
		return flag;
	}
	public final void setFlag(int flag) {
		this.flag = flag;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
