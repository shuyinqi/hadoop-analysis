package com.mapbar.analyzelog.core;


/**
 * Static const class of storage used.
 * 
 * @author @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class SystemConstants extends ConfigurableConstants{

	/** Storage table name prefix. **/
	public static final String TAB_PREFIX = "app";

	/** unknow tag */
	public static final String UNKNOWN = "unknown";

	/***
	 * 日志类型
	 */
	public enum LogType {
		USER,  //服务端配置信息的用户名  
		CONFIG, //服务端配置信息的配置 
		LAUNCH, //用户操作日志中，body部分的启动协议
		TERMINATE,// 用户操作日志中，body部分的退出协议
		EVENT, // 用户操作日志中，body部分的事件协议
		ERROR,// 用户操作日志中，body部分的错误协议
		UVERSION;// 

		public String toString() {
			return super.toString().toLowerCase();
		};

		/**
		 * 获取类型短名称。
		 */
		public String shortName() {
			return String.valueOf(toString().substring(0, 2));
		}
	}
	
	/*
	 * 根据 APPID 名获取表名。
	 */
	public static String getTableName(String appID) {
		return TAB_PREFIX + "_" + appID;
	}

	/***
	 * 获得hbase中表的名称
	 * @param appID   appid 传入的参数
	 * @param logType  日志类型    
	 * @return   hbase中对应的表
	 *
	 */
	public static String getTableName(String appID, LogType logType) {
		if (logType == LogType.USER) {
			return getTableName(appID);
		}
		return TAB_PREFIX + "_" + appID + "_" + String.valueOf(logType);
	}
}
