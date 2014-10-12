package com.mapbar.analyzelog.service.mapreduce;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.entities.BasicLog;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;

public class UserForAreaMapReducerTest extends TestCase{

	LogStorageManager logStorageManager;

	protected void setUp() throws Exception {
		logStorageManager = HBaseLogStorageManager.getStorageManager();
	}

	public void testInitTestData(){
		LogStorage logStorage = logStorageManager.getLogStorage("1000");
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_MONTH, 5);
	
		for (int i = 20; i < 30; i++){
			Equipment equipment = new Equipment();
			equipment.setCity("北京");

		//	logStorage.putUser("u"+i, equipment);

			List<LogLaunch> launchs = new ArrayList<LogLaunch>();
			BasicLog basicLog = new BasicLog();
			basicLog.setCity("北京");
			launchs.add(new LogLaunch(basicLog));
			logStorage.putLuanchs("u"+i, launchs);
		}
	}
}
