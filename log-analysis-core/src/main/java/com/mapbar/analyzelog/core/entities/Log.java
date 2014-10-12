package com.mapbar.analyzelog.core.entities;

import com.mapbar.analyzelog.core.utils.DateFormatUtils;


/**
 * 日志基类.
 * 
 * @author 邓飞鸽
 */
public abstract class Log {
	
	private String clientTime; // 日志在客户端产生时间
	private long time;         //日志在服务端

	public long getTime() {
		if (time <= 0){
			setTime(DateFormatUtils.getNowMillisecond());
		}
		return time;
	}

	/**
	 * 设置日志产生时间，服务器端时间.
	 * 
	 * @param time 毫秒值。
	 */
	public void setTime(long time) {
		this.time = time;
	}

	public String getClientTime() {
		return this.clientTime;
	}

	/**
	 * 设置日志产生时间，客户端.
	 * 
	 * @param time yyyy-MM-dd HH:mm:ss。
	 */
	public void setClientTime(String clientTime) {
		this.clientTime = clientTime;
	}
}
