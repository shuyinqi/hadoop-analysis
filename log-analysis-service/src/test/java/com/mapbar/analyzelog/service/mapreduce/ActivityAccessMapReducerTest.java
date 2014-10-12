package com.mapbar.analyzelog.service.mapreduce;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.entities.BasicLog;
import com.mapbar.analyzelog.core.entities.BasicLog.ChannelType;
import com.mapbar.analyzelog.core.entities.LogTerminate;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;

public class ActivityAccessMapReducerTest extends TestCase {
	
	LogStorageManager logStorageManager;

	@Override
	protected void setUp() throws Exception {
		logStorageManager = HBaseLogStorageManager.getStorageManager();
	}

	public void testPutTestData(){
		LogStorage logStorage = logStorageManager.getLogStorage("1000");
		String userID ="u1";
		
		List<LogTerminate> logTerminates = new ArrayList<LogTerminate>();
		
		for (int i = 0; i < 100; i++){
			LogTerminate logTerminate = new LogTerminate(getDefaultBasicLog());
			logTerminate.setActivities(getActivities());
			logTerminates.add(logTerminate);
		}
		
		logStorage.putTerminates(userID, logTerminates);
	}

	public List<String[]> getActivities(){
		List<String[]> activities = new ArrayList<String[]>();
		for (int i = 0; i < 10; i++) {
			if (i %2 == 0){
				String[] activie = {"MainPage", "10"};
				activities.add(activie);
			} else {
				String[] activie = {"TopicPage", "20"};
				activities.add(activie);
			}
		}
		return activities;
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
