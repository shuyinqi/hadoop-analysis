package com.mapbar.analyzelog.model;

public class ConfigApp {
	private int app_id;
	private int report_policy;
	private String last_config_time;
	private String online_params;
	public final int getApp_id() {
		return app_id;
	}
	public final void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public final int getReport_policy() {
		return report_policy;
	}
	public final void setReport_policy(int report_policy) {
		this.report_policy = report_policy;
	}
	public final String getLast_config_time() {
		return last_config_time;
	}
	public final void setLast_config_time(String last_config_time) {
		this.last_config_time = last_config_time;
	}
	public final String getOnline_params() {
		return online_params;
	}
	public final void setOnline_params(String online_params) {
		this.online_params = online_params;
	}
	
}
