package com.mapbar.analyzelog.core.entities;

/**
 * App 事件信息实体对象。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class LogEvent extends Log{
	
	public static final String SN = "ev";

	private BasicLog basicLog;
	private String sid;
	private String eventID;
	private String lable;
	private int acc = 1;

	public LogEvent(BasicLog basicLog){
		this.basicLog = basicLog;
	}

	public BasicLog getBasicLog() {
		return basicLog;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String session_id) {
		this.sid = session_id;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String event_id) {
		this.eventID = event_id;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public int getAcc() {
		return acc;
	}
	public void setAcc(int acc) {
		this.acc = acc;
	}
}
