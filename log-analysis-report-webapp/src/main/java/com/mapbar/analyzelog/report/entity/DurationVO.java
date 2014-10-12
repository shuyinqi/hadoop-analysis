package com.mapbar.analyzelog.report.entity;

import java.util.Date;



public class DurationVO {
	private String segment;   //时长所在时间段
	private int count;      //用户数
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
