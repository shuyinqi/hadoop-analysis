package com.mapbar.analyzelog.service.hbase;

import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;


public class TestDelete {
	
	public void delete(String logType,String starttime,String endtime) throws Exception {
	    LogStorageManager service = HBaseLogStorageManager.getStorageManager();
		LogStorage logStorage = service.getLogStorage("1000");
		if(logType.equals("1")){
			logStorage.deleteRowKeyForTimeRange(LogType.TERMINATE,Long.valueOf(starttime),Long.valueOf(endtime));
		}else{
			logStorage.deleteRowKeyForTimeRange(LogType.LAUNCH,Long.valueOf(starttime),Long.valueOf(endtime));
		}
	}
	public static void main(String[] args){
		TestDelete test = new TestDelete();
		try {
			test.delete(args[0],args[1],args[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
