package com.mapbar.analyzelog.report.entity;

import java.util.List;

public class DeviceVO {
	private String device;
	private int newcount=0;
	private int launchcount=0;
	private String date;
	private List<DeviceVO> vlist;
	public final String getDevice() {
		return device;
	}
	public final void setDevice(String device) {
		this.device = device;
	}
	public final int getNewcount() {
		return newcount;
	}
	public final void setNewcount(int newcount) {
		this.newcount = newcount;
	}
	public final int getLaunchcount() {
		return launchcount;
	}
	public final void setLaunchcount(int launchcount) {
		this.launchcount = launchcount;
	}
	public final List<DeviceVO> getVlist() {
		return vlist;
	}
	public final void setVlist(List<DeviceVO> vlist) {
		this.vlist = vlist;
	}
	public final String getDate() {
		return date;
	}
	public final void setDate(String date) {
		this.date = date;
	}
	
}
