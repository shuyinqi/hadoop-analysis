package com.mapbar.analyzelog.report.entity;

import java.util.Date;



public class RetentionVO {
	private Date date;   //时长所在时间段
	private String time;   //时长所在时间段
	private int addCount;      //当日新增用户数
	private int visitCount_7;      //7日回访用户
	private int visitCount_14;      //14日回访用户
	private int visitCount_30;      
	private int visitCount_60;     
	private int visitCount_90;    
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getAddCount() {
		return addCount;
	}
	public void setAddCount(int addCount) {
		this.addCount = addCount;
	}
	public int getVisitCount_7() {
		return visitCount_7;
	}
	public void setVisitCount_7(int visitCount_7) {
		this.visitCount_7 = visitCount_7;
	}
	public int getVisitCount_14() {
		return visitCount_14;
	}
	public void setVisitCount_14(int visitCount_14) {
		this.visitCount_14 = visitCount_14;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public final int getVisitCount_30() {
		return visitCount_30;
	}
	public final void setVisitCount_30(int visitCount_30) {
		this.visitCount_30 = visitCount_30;
	}
	public final int getVisitCount_60() {
		return visitCount_60;
	}
	public final void setVisitCount_60(int visitCount_60) {
		this.visitCount_60 = visitCount_60;
	}
	public final int getVisitCount_90() {
		return visitCount_90;
	}
	public final void setVisitCount_90(int visitCount_90) {
		this.visitCount_90 = visitCount_90;
	}
	
}
