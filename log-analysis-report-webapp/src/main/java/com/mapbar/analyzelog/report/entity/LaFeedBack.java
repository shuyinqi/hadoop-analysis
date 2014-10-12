package com.mapbar.analyzelog.report.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 用户反馈信息实体
 * @（#）:FeedBack.java 
 * @description:  
 * @author:  Administrator  2012-7-11 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class LaFeedBack {

	private Integer id;
	private Integer appid;
	private String userid;
	private Timestamp addtime;
	private Timestamp lasttime;
	private Integer flag;
	private String os;
	private String phone;
	private String lat;
	private String lon;
	private String appv;
	private String ct;
	private String qid;
	private String wid;
	private String note;
	private Integer collect;
	private Integer isDel;
	private String device;
	private String sex;
	private Integer age;
	private List<LaFeedAnswer> answer = new LinkedList<LaFeedAnswer>();
	
	public List<LaFeedAnswer> getAnswer() {
		return answer;
	}
	public void setAnswer(List<LaFeedAnswer> answer) {
		this.answer = answer;
	}
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Timestamp getAddtime() {
		return addtime;
	}
	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}
	public Timestamp getLasttime() {
		return lasttime;
	}
	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getAppv() {
		return appv;
	}
	public void setAppv(String appv) {
		this.appv = appv;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getCollect() {
		return collect;
	}
	public void setCollect(Integer collect) {
		this.collect = collect;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	
	
}
