package com.mapbar.analyzelog.service.mapreduce;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.entities.BasicLog;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;

public class UserCounterMapReducerTest extends TestCase{

	LogStorage logStorage;

	protected void setUp() throws Exception {
		logStorage = HBaseLogStorageManager.getStorageManager().getLogStorage("1000");
		for (int i = 0; i < 1000; i++){
			//logStorage.putUser("u"+i, new Equipment());
		}
		for (int i = 0; i < 20; i++){
			List<LogLaunch> logLuanchs = new ArrayList<LogLaunch>();
			LogLaunch logLaunch = new LogLaunch(new BasicLog());
			logLuanchs.add(logLaunch);
			logStorage.putLuanchs("u"+i, logLuanchs);
		}
	}

	public void testData(){
	}
}
