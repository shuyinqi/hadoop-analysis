package com.mapbar.analyzelog.service.mapreduce;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.entities.BasicLog;
import com.mapbar.analyzelog.core.entities.BasicLog.ChannelType;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;

public class ReturnVisitUserMapReducerTest extends TestCase {
	
	LogStorageManager logStorageManager;

	@Override
	protected void setUp() throws Exception {
		logStorageManager = HBaseLogStorageManager.getStorageManager();
	}

	public void testPutTestData(){
		LogStorage logStorage = logStorageManager.getLogStorage("1000");
		// 添加测试数据。
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-8);

		//1. 在当前时间前7天添加 20 个用户
		for (int i = 0; i < 30; i++) {
			Equipment equipment = new Equipment();
			equipment.setTime(calendar.getTimeInMillis());
			logStorage.putUser("u" + i, equipment);
		}
		int day = calendar.get(Calendar.DAY_OF_MONTH)+1;
		for (int i = 0; i < 20; i++){
			calendar.set(Calendar.DAY_OF_MONTH, day++);
			List<LogLaunch> launchs = new ArrayList<LogLaunch>();
			LogLaunch launch = new LogLaunch(getDefaultBasicLog());
			launch.setTime(calendar.getTimeInMillis());
			launchs.add(launch);
			logStorage.putLuanchs("u"+i, launchs);
		}
	}
	
	protected BasicLog getDefaultBasicLog(){
		BasicLog basicLog = new BasicLog();
		basicLog.setAccess("Wifi");
		basicLog.setCarrier("CMCC");
		basicLog.setAppVersion("1.0");
		basicLog.setCity("北京市");
		basicLog.setChannelType(ChannelType.MARKET);
		basicLog.setChannelName("Android Market");
		basicLog.setLat("115.00034");
		basicLog.setLon("39.24579");
		basicLog.setOSVersion("4.0");
		
		return basicLog;
	}
}
