package com.mapbar.analyzelog.report.entity;

import java.sql.Timestamp;
/***
 * 客服回复用户反馈的实体
 * @（#）:LaFeedAnswer.java 
 * @description:  
 * @author:  sjy  2012-7-12 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class LaFeedAnswer {

	public String getAnswerusername() {
		return answerusername;
	}
	public void setAnswerusername(String answerusername) {
		this.answerusername = answerusername;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Timestamp getLasttime() {
		return lasttime;
	}
	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	private Integer id;
	private String userid;
	private String qid;
	private String wid;
	private String answer;
	private Timestamp lasttime;
	private Integer isDel;
	private Integer fid;
	private Integer appid;
	private String answerusername;
	
	
}
