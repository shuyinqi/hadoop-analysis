package com.mapbar.analyzelog.core.entities;

/**
 * 错误日志信息实体。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class LogError extends Log{
	
	public static final String SN = "er";

	private BasicLog basicLog;
	private String content;

	public LogError(BasicLog basicLog) {
		this.basicLog = basicLog;
	}

	public BasicLog getBasicLog() {
		return basicLog;
	}

	public void setBasicLog(BasicLog basicLog) {
		this.basicLog = basicLog;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
