package com.mapbar.analyzelog.report.entity;

import java.sql.Date;

/***
 * 爬虫实体类
 * @（#）:ChannlReptile.java 
 * @author:  Administrator  2012-4-20 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class ChannlReptile {

	public Integer Id;
	public String name;
	public Date date;
	public Integer addCount;
	//每天爬过来的总数
	public Integer total;
	//计算出来的百分比
	public double proportion;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Integer getAddCount() {
		return addCount;
	}
	public void setAddCount(Integer addCount) {
		this.addCount = addCount;
	}
	public double getProportion() {
		return proportion;
	}
	public void setProportion(double proportion) {
		this.proportion = proportion;
	}
}//:~
