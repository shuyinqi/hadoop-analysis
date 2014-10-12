package com.mapbar.analyzelog.db;

import java.util.ResourceBundle;

import com.mapbar.analyzelog.common.LogWriter;

public class ConfigLoader {
	//加载JDBC配置信息
	public  static void loadDBConfig(){
		ResourceBundle resourceBundle=ResourceBundle.getBundle("db");
		if(resourceBundle!=null){
		SystemConstant.JDBCURL=resourceBundle.getString("jdbc.url").trim();
		SystemConstant.JDBCDriver=resourceBundle.getString("jdbc.driver").trim();
		SystemConstant.JDBCUSER=resourceBundle.getString("jdbc.username").trim();
		SystemConstant.JDBCPASSWORD=resourceBundle.getString("jdbc.password").trim();
		}else{
			LogWriter.dbDebug("找不到配置文件db.properties");
		}
	}

}
