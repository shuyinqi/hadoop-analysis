package com.mapbar.analyzelog.service;

import java.util.Calendar;

import com.mapbar.analyzelog.service.utils.ArgumentUtils;

/**
 * The job bootstrap arguments.
 * 参数配置
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 *
 */
public final class Argument {
	
	private String[] arguments;

	protected static String ROOT_TASKS_PACKAGE = "com.mapbar.analyzelog.service.mapreduce";
	
	public final static String APP_ID_NAME = "appid";

	public Argument(String[] arguments){
		this.arguments = arguments;
	}
    /***
     *判断是否传入参数，否则停止执行
     *
     */
	public void assertRequiredArguments() {
		String errorMsg = "Usage: runTask <taskName> --appid <1000>";
		if ((arguments == null || arguments.length < 3)
				|| !ArgumentUtils.has(arguments, "--"+APP_ID_NAME)) {
			System.err.println(errorMsg);
			System.exit(1);
		}
	}
	
	public String getAppID() {
		return ArgumentUtils.getValue(arguments, "--"+APP_ID_NAME);
	}
    /****
     * 返回传递过来的第一个参数为JOB名称
     * @return
     *
     */
	public String getJobName() {
		return this.arguments[0];
	}

	public Calendar getCalendarValue(String name) {
		return ArgumentUtils.getCalendarValue(arguments, name);
	}
	public String getDayValue(){
		return ArgumentUtils.getValue(arguments, "-t");
	}
	
	public int getIntValue(String name, int day) {
		return ArgumentUtils.getIntValue(arguments, name, day);
	}

	public String getPackage() {
		return ArgumentUtils.getValue(arguments, "--package", ROOT_TASKS_PACKAGE);
	}

}
