package com.mapbar.analyzelog.report.entity;

public class LocationVO {
	private String city;   //省份
	private int newcount;      //新用户
	private int visitcount;   //活跃用户
	public final String getCity() {
		return city;
	}
	public final void setCity(String city) {
		this.city = city;
	}
	public final int getNewcount() {
		return newcount;
	}
	public final void setNewcount(int newcount) {
		this.newcount = newcount;
	}
	public final int getVisitcount() {
		return visitcount;
	}
	public final void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}
	
}
