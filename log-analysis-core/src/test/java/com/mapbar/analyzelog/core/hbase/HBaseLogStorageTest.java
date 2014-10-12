package com.mapbar.analyzelog.core.hbase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.entities.BasicLog;
import com.mapbar.analyzelog.core.entities.BasicLog.ChannelType;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogEvent;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.core.entities.LogTerminate;

/**
 * HBaseLogStorage.java Test Case.
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 *
 */
public class HBaseLogStorageTest extends TestCase {

	private LogStorageManager logStorageManager;

	@Override
	protected void setUp() throws Exception {
		logStorageManager = HBaseLogStorageManager.getStorageManager();
	}

	public void testPutUser(){
		LogStorage logStorage = logStorageManager.getLogStorage("1000");

		String userID = "test-user";
		Equipment equipment = new Equipment();
		equipment.setCpu("ARMv7 Processor");
		equipment.setOs("Android1.5");
		equipment.setClientTime(String.valueOf(new Date().getTime()));
		equipment.setDeviceModel("HTC Incredible S");
		equipment.setSdkVersion("3.1.0");
		equipment.setResolution("800*480");
		equipment.setTimeZone("8");
		equipment.setLanguage("Unknown");
		equipment.setSdkVersion("3.0");
		equipment.setMobile("1388888888");
		equipment.setChannelType(ChannelType.get(1));
		equipment.setChannelName("Andorid Market");
		equipment.setAppVersion("2.0");
		equipment.setCity("北京");
		equipment.setOs("Android");
		logStorage.putUser(userID, equipment);
	}
	
	public void tes1tPutUsers(){
		for (int i = 1; i < 100; i++) {
			for (int j = 0; j < 20; j++) {
				LogStorage logStorage = logStorageManager.getLogStorage("1000");
				String userID = "2bac35dcf07884cf2cdb5b62fd8a58ac" + i + "" + j;
				Equipment equipment = new Equipment();
				equipment.setCpu("ARMv7 Processor");
				equipment.setOs("Android1.5");
				equipment.setSdkVersion("3.1.0");
				equipment.setResolution("800*480");
				equipment.setTimeZone("8");
				equipment.setLanguage("Unknown");
				equipment.setSdkVersion("1.0");
				equipment.setMobile("1388888888");
				if (j % 2 == 0 || i % 5 == 0) {
					equipment.setAppVersion("4.0");
					equipment.setCity("长沙");
					equipment.setChannelType(ChannelType.get(1));
					equipment.setChannelName("Andorid Market");
					equipment.setBrand("三星");
					equipment.setDeviceModel("三星 i9000");
				} else {
					equipment.setAppVersion("2.0");
					equipment.setCity("上海");
					equipment.setChannelType(ChannelType.get(2));
					equipment.setChannelName("HTC");
					equipment.setBrand("HTC");
					equipment.setDeviceModel("HTC Incredible S");
				}
				equipment.setOs("Android");
				logStorage.putUser(userID, equipment);
			}
		}
	}

	public void te1stPutLuanchs() throws InterruptedException{
		LogStorage logStorage = logStorageManager.getLogStorage("1000");

		for (int i = 10; i < 10000; i++){
			String userID = null;
			List<LogLaunch> logLuanchs = new ArrayList<LogLaunch>();
			for (int j = 0; j < 20;j++){
				userID = "2bac35dcf07884cf2cdb5b62fd8a58ac"+i+""+j;
				BasicLog basicLog = getDefaultBasicLog();
				if (j % 2 == 0){
					basicLog.setCity("北京市");
					basicLog.setAppVersion("2.0");
				} else if(j%3==0){
					basicLog.setCity("长沙市");
					basicLog.setAppVersion("4.0");
				} else if (j%5==0){
					basicLog.setCity("上海市");
				}else{
					basicLog.setCity("南京市");
					basicLog.setAppVersion("1.0.1");
				}
				LogLaunch logLuanch = new LogLaunch(basicLog);
				logLuanch.setClientTime(String.valueOf(new Date().getTime() + i));
				logLuanch.setSid(logLuanch.getTime()+"ec47f505270151ff5");
				logLuanchs.add(logLuanch);
			}
			logStorage.putLuanchs(userID, logLuanchs);
		}
	}
	
	public void te1stPutEvents(){
		LogStorage logStorage = logStorageManager.getLogStorage("1000");
		String userId = "ec47f505270151ff5";
		List<LogEvent> logEvents = new ArrayList<LogEvent>();
		for (int i= 0; i < 100; i++){
			LogEvent event = new LogEvent(getDefaultBasicLog());
			if(i %2==0){
				event.setEventID("Topic");
			} else {
				event.setEventID("Export");
			}
			logEvents.add(event);
		}
		logStorage.putEvents(userId, logEvents);
	}
	
	public void tes1tPutTerminates() throws InterruptedException{
		LogStorage logStorage = logStorageManager.getLogStorage("1000");
		String userID = "2bac35dcf07884cf2cdb5b62fd8a58avt";
		List<LogTerminate> logTerminates = new ArrayList<LogTerminate>();
		BasicLog basicLog = getDefaultBasicLog();
		for (int i = 0; i < 100; i++){
			LogTerminate terminate = new LogTerminate(basicLog);
			terminate.setClientTime(String.valueOf(new Date().getTime() + i));
			terminate.setSid(terminate.getTime()+"ec47f505270151ff5"+i);
			terminate.setDuration(String.valueOf(i*2));
			logTerminates.add(terminate);
		}
		logStorage.putTerminates(userID, logTerminates);
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
