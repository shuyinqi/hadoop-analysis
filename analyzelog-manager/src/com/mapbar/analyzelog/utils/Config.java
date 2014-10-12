package com.mapbar.analyzelog.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class Config {

	private static Configuration conf = null;

	static {
		Config.conf = HBaseConfiguration.create();
		// 必须加下面的配置设置，否则报异常
		Config.conf.set("fs.default.name", "hdfs://localhost:9000/hbase");
	}
	
	public static Configuration getInstance() {
		return Config.conf;
	}

}
