package com.mapbar.analyzelog.core.entities;

/**
 * APP启动信息实体。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class LogLaunch extends Log{

	public static final String SN = "la";

	private BasicLog basicLog;
	private String sid; //启动id

	public LogLaunch(BasicLog basicLog) {
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
}
