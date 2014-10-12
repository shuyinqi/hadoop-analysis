package com.mapbar.analyzelog.report.entity;


public class PathVO {
	private String name;   //页面名称
	private long time;   //访问总时间
	private String user_count;
	private long count;   //用户点击数
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public final String getUser_count() {
		return user_count;
	}
	public final void setUser_count(String user_count) {
		this.user_count = user_count;
	}
	
}
