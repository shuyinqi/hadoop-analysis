package com.mapbar.analyzelog.model;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class UserTodo {
	private String deviceModel;
	private String app_id;
	private String user_id;
	public UserTodo(){
		
	}
	public final String getApp_id() {
		return app_id;
	}
	public final void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public final String getUser_id() {
		return user_id;
	}
	public final void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	
}
