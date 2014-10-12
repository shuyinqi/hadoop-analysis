package com.mapbar.analyzelog.core.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.BasicLog;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.Log;
import com.mapbar.analyzelog.core.entities.LogError;
import com.mapbar.analyzelog.core.entities.LogEvent;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.core.entities.LogTerminate;
import com.mapbar.analyzelog.core.exception.StorageException;
import com.mapbar.analyzelog.core.utils.DateFormatUtils;

/**
 * 针对 HBase 数据库实现的数据存储支持。<br>
 * 该类为非线程安全，在多线程的模式中需要使用多实例。
 * 
 * @author 邓飞鸽
 */
public class HBaseLogStorage implements LogStorage{
	
	private static Logger logger = LoggerFactory.getLogger(HBaseLogStorage.class);
	private static final String IGNORE="IGNORE";
	private String appID;
	private HBaseLogStorageManager logStorageManager;
	protected Map<String, HTable> htableCache = new HashMap<String, HTable>();

	public HBaseLogStorage(String appID, HBaseLogStorageManager logStorageManager){
		this.appID = appID;
		this.logStorageManager = logStorageManager;
	}

	@Override
	public void putUser(String userID, Equipment equipment) {
		try {
			HTable hTable = getHTable(LogType.USER);
			Result user = getUser(userID, hTable);
			if(user == null){
				hTable.put(generateUserPut(userID, equipment));
				logger.debug("Put user {} to HBase successful.", userID);
				putUserAdapteeLaunch(userID,equipment);
				logger.debug("Put new_user to launch {} to HBase successful.", userID);
			} else {
				String appVersion = Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("appv")));
				if (!StringUtils.isEmpty(appVersion) && !appVersion.equals(equipment.getAppVersion())){
					putUserVersionIfNotExist(userID, equipment.getAppVersion(), equipment.getTime());
				}
				logger.debug("User {} exist, be ignored.", userID);
			}
			logStorageManager.putHTable(hTable);
		} catch (IOException ioe) {
			throwStorageException(ioe, "Put user [" + userID + "] fails!");
		}
	}
	
	public void putUserAdapteeLaunch(String userID, Equipment equipment){
		try {
			List<LogLaunch> logLaunchs = new ArrayList<LogLaunch>();
			BasicLog basicLog = new BasicLog();
			basicLog.setCarrier(IGNORE);
			basicLog.setAccess(IGNORE);
			basicLog.setCountry(IGNORE);
			basicLog.setAppVersion(equipment.getAppVersion());
			basicLog.setChannelName(equipment.getChannelName());
			basicLog.setChannelType(equipment.getChannelType());
			basicLog.setCity(equipment.getCity());
			basicLog.setLat(equipment.getLat());
			basicLog.setLon(equipment.getLon());
			basicLog.setOSVersion(equipment.getOSVersion());
			LogLaunch logLaunch = new LogLaunch(basicLog);
			logLaunch.setClientTime(equipment.getClientTime());
			logLaunch.setSid(DateFormatUtils.parseDatetime(equipment.getClientTime()).getTime()+userID);
			logLaunchs.add(logLaunch);
			putLuanchs(userID,logLaunchs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Equipment getEquipment(String userID){
		Equipment equipment=new Equipment();
		try {
			HTable hTable = getHTable(LogType.USER);
			Result user = getUser(userID, hTable);
			if(user == null){
				logger.debug("get user is null from HBase.", userID);
			} else {
				equipment.setAppVersion(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("appv"))));
				equipment.setClientTime(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("clnt"))));
				equipment.setCpu(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("cpu"))));
				equipment.setBrand(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("brand"))));
				equipment.setOs(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("os"))));
				equipment.setSdkVersion(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("sdkv"))));
				equipment.setResolution(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("reln"))));
				equipment.setDeviceModel(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("devm"))));
				equipment.setTimeZone(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("tz"))));
				equipment.setLanguage(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("lang"))));
				equipment.setImei(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("imei"))));
				equipment.setMacAddr(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("mac"))));
				equipment.setMobile(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("mobile"))));
				equipment.setImsi(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("imsi"))));
				equipment.setChannelName(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("chne"))));
				equipment.setCity(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("city"))));
				equipment.setAppVersion(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("osv"))));
				equipment.setOSVersion(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("lat"))));
				equipment.setLon(Bytes.toString(user.getValue(Bytes.toBytes(Equipment.SN), Bytes.toBytes("lon"))));
				
				logger.debug("get user is ok.", userID);
			}
//			logStorageManager.putHTable(hTable);
		} catch (IOException ioe) {
			throwStorageException(ioe, "Get user [" + userID + "] fails!");
		}
		return equipment;
	}

	private void putUserVersionIfNotExist(String userID, String version, long time) throws IOException {
		HTable userVersionTable = getHTable(LogType.UVERSION);
		Get versoinGet = new Get(Bytes.toBytes(userID + version));
		Result versionResult = userVersionTable.get(versoinGet);
		if(versionResult == null || versionResult.isEmpty()){
			userVersionTable.put(generateUserVersionPut(userID, version, time));
		}
		logStorageManager.putHTable(userVersionTable);
	}

	private Put generateUserVersionPut(String userID, String version, long time) {
		Put versionPut = new Put(Bytes.toBytes(userID + version));
		addTo(versionPut, LogType.UVERSION.shortName(), "time", String.valueOf(time), time);
		addTo(versionPut, LogType.UVERSION.shortName(), "uid", userID, time);
		addTo(versionPut, LogType.UVERSION.shortName(), "vs", version, time);
		return versionPut;
	}

	protected Result getUser(String userID, HTable userTable) throws IOException {
		Result user = userTable.get(new Get(Bytes.toBytes(userID)));
		if (user == null || user.isEmpty()){
			return null;
		} else {
			return user;
		}
	}

	protected Put generateUserPut(String userID, Equipment equipment){
		Put userPut = new Put(Bytes.toBytes(userID));
		long time = equipment.getTime();

		addEquipmentPropToPut(userPut, "time", String.valueOf(time), time);
		addEquipmentPropToPut(userPut, "clnt", equipment.getClientTime(), time);
		addEquipmentPropToPut(userPut, "cpu", equipment.getCpu(), time);
		addEquipmentPropToPut(userPut, "brand", equipment.getBrand(), time);
		addEquipmentPropToPut(userPut, "os", equipment.getOs(), time);
		addEquipmentPropToPut(userPut, "sdkv", equipment.getSdkVersion(), time);
		addEquipmentPropToPut(userPut, "reln", equipment.getResolution(), time);
		addEquipmentPropToPut(userPut, "devm", equipment.getDeviceModel(), time);
		addEquipmentPropToPut(userPut, "tz", equipment.getTimeZone(), time);
		addEquipmentPropToPut(userPut, "lang", equipment.getLanguage(), time);
		addEquipmentPropToPut(userPut, "imei", equipment.getImei(), time);
		addEquipmentPropToPut(userPut, "mac", equipment.getMacAddr(), time);
		addEquipmentPropToPut(userPut, "mobile", equipment.getMobile(), time);
		addEquipmentPropToPut(userPut, "imsi", equipment.getImsi(), time);
		addEquipmentPropToPut(userPut, "chtp", String.valueOf(equipment.getChannelType()), time);
		addEquipmentPropToPut(userPut, "chne", equipment.getChannelName(), time);
		addEquipmentPropToPut(userPut, "city", equipment.getCity(), time);
		addEquipmentPropToPut(userPut, "appv", equipment.getAppVersion(), time);
		addEquipmentPropToPut(userPut, "osv", equipment.getOSVersion(), time);
		addEquipmentPropToPut(userPut, "lat", equipment.getLat(), time);
		addEquipmentPropToPut(userPut,"lon", equipment.getLon(), time);

		return userPut;
	}
	
	protected void addEquipmentPropToPut(Put userPut, String name, String value) {
		assert StringUtils.isEmpty(name);
		addTo(userPut, Equipment.SN, name, value);
	}

	protected void addEquipmentPropToPut(Put userPut, String name, String value, long time) {
		assert StringUtils.isEmpty(name);
		addTo(userPut, Equipment.SN, name, value, time);
	}

	@Override
	public void putLuanchs(String userID, List<LogLaunch> logLuanchs) {
		try {
			int size = putLogsToHTable(userID, logLuanchs, LogType.LAUNCH);
			logger.debug("Put {} luanchs to HBase successful.", size);
		} catch (IOException ioe) {
			throwStorageException(ioe, "Put luanch logs exception!!");
		}
	}

	protected Put generateLuanchPut(String userID, LogLaunch logLuanch){
		Put luanchPut = new Put(getLogRowKey(userID, logLuanch));
		long time = logLuanch.getTime();

		addBasicLogToPut(luanchPut, logLuanch.getBasicLog(), LogLaunch.SN, time);
		addTo(luanchPut, LogLaunch.SN, "uid", userID, time);
		addTo(luanchPut, LogLaunch.SN, "time", String.valueOf(time), time);
		addTo(luanchPut, LogLaunch.SN, "sid", logLuanch.getSid(), time);
		addTo(luanchPut, LogLaunch.SN, "clnt", logLuanch.getClientTime(), time);
		return luanchPut;
	}

	@Override
	public void putTerminates(String userID, List<LogTerminate> logTerminates) {
		try {
			int size = putLogsToHTable(userID, logTerminates, LogType.TERMINATE);
			logger.debug("Put {} terminates to HBase successful.", size);
		} catch (IOException ioe) {
			throwStorageException(ioe, "Put user [" + userID + "] terminate logs fail!");
		}
	}

	protected Put generateTerminatePut(String userID, LogTerminate logTerminate){
		Put terminatePut = new Put(getLogRowKey(userID, logTerminate));
		long time = logTerminate.getTime();

		addBasicLogToPut(terminatePut, logTerminate.getBasicLog(), LogTerminate.SN, time);
		addTo(terminatePut, LogTerminate.SN, "uid", userID, time);
		addTo(terminatePut, LogTerminate.SN, "time", String.valueOf(time), time);
		addTo(terminatePut, LogTerminate.SN, "clnt", logTerminate.getClientTime(), time);
		addTo(terminatePut, LogTerminate.SN, "sid", logTerminate.getSid(), time);
		addTo(terminatePut, LogTerminate.SN, "duti", logTerminate.getDuration(), time);
		addTo(terminatePut, LogTerminate.SN, "actis", logTerminate.getActivities(), time);
		return terminatePut;
	}

	@Override
	public void putEvents(String userID, List<LogEvent> logEvents) {
		try {
			int size = putLogsToHTable(userID, logEvents, LogType.EVENT);
			logger.debug("Put {} events to HBase successful.", size);
		} catch (IOException ioe) {
			throwStorageException(ioe, "Put user [" + userID + "] event logs fail!");
		}
	}

	protected Put generateEventPut(String userID, LogEvent logEvent){
		Put eventPut = new Put(getLogRowKey(userID, logEvent));
		long time = logEvent.getTime();

		addBasicLogToPut(eventPut, logEvent.getBasicLog(), LogEvent.SN, time);
		addTo(eventPut, LogEvent.SN, "sid", logEvent.getSid(), time);
		addTo(eventPut, LogEvent.SN, "uid", userID, time);
		addTo(eventPut, LogEvent.SN, "time", String.valueOf(time), time);
		addTo(eventPut, LogEvent.SN, "clnt", logEvent.getClientTime(), time);
		addTo(eventPut, LogEvent.SN, "eid", logEvent.getEventID(), time);
		// Notes: 如果事件没有 Lable 则其默认Lable为 EventID 
		if (StringUtils.isEmpty(logEvent.getLable())){
			logEvent.setLable(logEvent.getEventID().toLowerCase());
		}
		addTo(eventPut, LogEvent.SN, "lab", logEvent.getLable(), time);
		addTo(eventPut, LogEvent.SN, "acc", String.valueOf(logEvent.getAcc()), time);
		return eventPut;
	}

	@Override
	public void putErrors(String userID, List<LogError> logErrors) {
		try {
			int size = putLogsToHTable(userID, logErrors, LogType.ERROR);
			logger.debug("Put {} errors to HBase successful.", size);
		} catch (IOException ioe) {
			throwStorageException(ioe, "Put user [" + userID + "] error logs fail!");
		}
	}

	protected Put generateErrorPut(String userID, LogError logLuanch){
		Put errorPut = new Put(getLogRowKey(userID, logLuanch));
		long time = logLuanch.getTime();

		addBasicLogToPut(errorPut, logLuanch.getBasicLog(), LogError.SN, time);
		addTo(errorPut, LogError.SN, "uid", userID, time);
		addTo(errorPut, LogError.SN, "time", String.valueOf(time), time);
		addTo(errorPut, LogError.SN, "clnt", logLuanch.getClientTime(), time);
		addTo(errorPut, LogError.SN, "content", logLuanch.getContent(), time);
		return errorPut;
	}

	@Override
	public String getID() {
		return appID;
	}
	
	protected void addBasicLogToPut(Put put, BasicLog basicLog, String family) {
		addBasicLogToPut(put, basicLog, family, new Date().getTime());
	}

	protected void addBasicLogToPut(Put put, BasicLog basicLog, String family, long time) {
		addTo(put, family, "acs", basicLog.getAccess(), time);
		addTo(put, family, "car", basicLog.getCarrier(), time);
		addTo(put, family, "lat", basicLog.getLat(), time);
		addTo(put, family, "lon", basicLog.getLon(), time);
		addTo(put, family, "city", basicLog.getCity(), time);
		addTo(put, family, "appv", basicLog.getAppVersion(), time);
		addTo(put, family, "osv", basicLog.getOSVersion(), time);
		addTo(put, family, "chtp", String.valueOf(basicLog.getChannelType()), time);
		addTo(put, family, "chne", basicLog.getChannelName(), time);
	}

	protected byte[] getLogRowKey(String userID, Log log){
		assert StringUtils.isNotEmpty(userID);
		assert log != null;
		Date client = DateFormatUtils.parseDatetime(log.getClientTime());
		long serverTime = log.getTime();
		long clientTime;
		if (client == null){
			clientTime = log.getTime();
		} else {
			clientTime = client.getTime();
		}
		return Bytes.toBytes("99999"+String.valueOf(serverTime)+String.valueOf(clientTime) + userID);
	}

	protected int putLogsToHTable(String userID, List<? extends Log> logs, LogType logType) 
			throws IOException{
		HTable logTable = getHTable(logType);
		List<Put> logPuts = generateLogPuts(userID, logs, logType);
		logTable.put(logPuts);
		logStorageManager.putHTable(logTable);
		return logPuts.size();
	}

	protected List<Put> generateLogPuts(String userID, List<? extends Log> logs, LogType logType){
		List<Put> logPuts = new ArrayList<Put>(logs.size());
		for (Log log : logs){
			if (log == null){
				continue;
			}
			switch (logType) {
			case LAUNCH:
				logPuts.add(generateLuanchPut(userID, (LogLaunch) log));
				break;
			case TERMINATE:
				logPuts.add(generateTerminatePut(userID, (LogTerminate) log));
				break;
			case ERROR:
				logPuts.add(generateErrorPut(userID, (LogError) log));
				break;
			case EVENT:
				logPuts.add(generateEventPut(userID, (LogEvent) log));
				break;
			}
		}
		return logPuts;
	}

	protected void addTo(Put put, String family, String name, String value) {
		if (StringUtils.isNotEmpty(value)){
			put.add(Bytes.toBytes(family), Bytes.toBytes(name), Bytes.toBytes(value));
		}
	}

	protected void addTo(Put put, String family, String name, String value, long time) {
		if (StringUtils.isNotEmpty(value)){
			put.add(Bytes.toBytes(family), Bytes.toBytes(name), time, Bytes.toBytes(value));
		}
	}

	protected HTable getHTable(LogType logType) throws IOException{
		return this.logStorageManager.getHTable(getID(), logType);
	}

	protected void throwStorageException(IOException ioe, String msg) {
		logger.error(msg, ioe);
		throw new StorageException(msg, ioe);
	}

	@Override
	public Set<String> getRowKeyForTimeRange(Long startTime,Long endTime) {
		Set<String> set = new HashSet<String>();
		try {
			HTable hTable = getHTable(LogType.USER);
			Scan scan = new Scan();
			scan.setBatch(10000);
			if(startTime!=null && endTime!=null){
				scan.setTimeRange(startTime,endTime);
			}
			scan.setBatch(5000);
			ResultScanner ss = hTable.getScanner(scan);
			for(Result r:ss){
                for(KeyValue kv:r.raw()){
                    set.add(new String(kv.getRow()));
                }
            }
		} catch (IOException e) {
		e.printStackTrace();
		}
		return set;
	}
	
	public ResultScanner getReslutForTimeRange(Long startTime,Long endTime) {
		ResultScanner ss=null;
		try {
			HTable hTable = getHTable(LogType.LAUNCH);
			Scan scan = new Scan();
			scan.setBatch(10000);
			if(startTime!=null && endTime!=null){
				scan.setTimeRange(startTime,endTime);
			}
			scan.setBatch(5000);
			ss = hTable.getScanner(scan);
		} catch (IOException e) {
		e.printStackTrace();
		}
		return ss;
	}
	
	public void deleteRowKeyForTimeRange(LogType tableName,Long startTime,Long endTime) {
		try {
			HTable hTable = getHTable(tableName);
			Scan scan = new Scan();
			scan.setStartRow(startTime.toString().getBytes());
			scan.setStopRow(endTime.toString().getBytes());
			/*if(startTime!=null && endTime!=null){
				scan.setTimeRange(startTime,endTime);
			}*/
			scan.setBatch(5000);
			ResultScanner ss = hTable.getScanner(scan);
			int i=0;
			System.out.println("deleting.....");
			for(Result r:ss){
					i++;
				   Delete delete = new Delete(r.getRow());
				   hTable.delete(delete);
				   hTable.flushCommits();
            }
			System.out.println("delete:"+i);
		} catch (IOException e) {
		e.printStackTrace();
		}finally{
		}
	}
	
	
	@Override
	public List<String> getColumnsForfilter(Long startTime,Long endTime,String arr) {
		List<String> list = new ArrayList<String>();
		try {
			HTable hTable = getHTable(LogType.USER);
			FilterList filter = new FilterList(); 
			Scan scan = new Scan();
            String [] s=arr.split(",");  
            filter.addFilter(
              new SingleColumnValueFilter(Bytes.toBytes(s[0]),  
		        Bytes.toBytes(s[1]),CompareOp.EQUAL,Bytes.toBytes(s[2])  
		      )  
		    );
//		    if(startTime!=null && endTime!=null){
//						scan.setTimeRange(startTime,endTime);
//			}
		    scan.setBatch(10000);
			scan.setFilter(filter);
//			scan.addColumn(Bytes.toBytes("eq"), Bytes.toBytes("brand"));  
			scan.addColumn(Bytes.toBytes("eq"), Bytes.toBytes("chtp")); 
			ResultScanner rsFilter = hTable.getScanner(scan);  
			 for(Result rr=rsFilter.next();rr!=null;rr=rsFilter.next()){
					String name="";
		            for(KeyValue kv:rr.list()){
						if(name.toString()==""||"".equals(name.toString())){
							logger.info("name append:"+new String(kv.getValue()));
						    name=new String(kv.getValue());
						}
						if(new String(kv.getValue())=="pre_installed"||"pre_installed".equals(new String(kv.getValue()))){
							logger.info("list append:"+name);
							list.add(name);
						    
						}
		            }
		        }  
		
		} catch (IOException e) {
		  e.printStackTrace();
		}
		return list;
	}
	
	
	
	@Override
	public Boolean rowKeyIsExist(String rowKey) {
		try {
			HTable hTable = getHTable(LogType.USER);
			Get rowKeyGet = new Get(Bytes.toBytes(rowKey));
			Result result = hTable.get(rowKeyGet);
		    if(result!=null && !result.isEmpty()){
		    	return true;
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
