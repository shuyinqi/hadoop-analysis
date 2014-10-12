package com.mapbar.analyzelog.report.entity;

import java.util.Date;
import java.util.List;



public class RetentionDaysVO {
	private Date date;   //时长所在时间段
	private String time;   //时长所在时间段
	private int addCount;      //当日新增用户数
	private int visitCount_7;      //7日回访用户
	private int visitCount_14;      //14日回访用户
	private int visitCount_21;      
	private int visitCount_28;     
	private int visitCount_35;    
	private int visitCount_42;    
	private int visitCount_49;    
	private int visitCount_56;    
	private List<RetentionDaysVO> visitList;
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
	public int getVisitCount_21() {
		return visitCount_21;
	}
	public void setVisitCount_21(int visitCount_21) {
		this.visitCount_21 = visitCount_21;
	}
	public int getVisitCount_28() {
		return visitCount_28;
	}
	public void setVisitCount_28(int visitCount_28) {
		this.visitCount_28 = visitCount_28;
	}
	public int getVisitCount_35() {
		return visitCount_35;
	}
	public void setVisitCount_35(int visitCount_35) {
		this.visitCount_35 = visitCount_35;
	}
	public int getVisitCount_42() {
		return visitCount_42;
	}
	public void setVisitCount_42(int visitCount_42) {
		this.visitCount_42 = visitCount_42;
	}
	public int getVisitCount_49() {
		return visitCount_49;
	}
	public void setVisitCount_49(int visitCount_49) {
		this.visitCount_49 = visitCount_49;
	}
	public int getVisitCount_56() {
		return visitCount_56;
	}
	public void setVisitCount_56(int visitCount_56) {
		this.visitCount_56 = visitCount_56;
	}
	public List getVisitList() {
		return visitList;
	}
	public void setVisitList(List visitList) {
		this.visitList = visitList;
	}
	
}
