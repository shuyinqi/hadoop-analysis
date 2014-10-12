package com.mapbar.analyzelog.core.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;

import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.SystemConstants.LogType;

/**
 * 针对 HBase 实现的日志数据库管理。<br>
 * 通过 <code>getStorageManager</code> 获取 Manager 对象，该类为单例模式。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class HBaseLogStorageManager implements LogStorageManager{

	private HBaseLogSchemaAdmin logSchemaAdmin; 

	private static HBaseLogStorageManager baseLogStorageManager;

	protected HBaseLogStorageManager(HBaseLogSchemaAdmin logSchemaAdmin){
		this.logSchemaAdmin = logSchemaAdmin;
	}

	/**
	 * 获取日志存储源管理对象。
	 * 
	 * @return the LogStorageManager object
	 */
	public static LogStorageManager getStorageManager(){
		if (baseLogStorageManager == null){
			baseLogStorageManager = new HBaseLogStorageManager(new HBaseLogSchemaAdmin());
		}
		return baseLogStorageManager;
	}

	/**
	 * 根据 HBase 配置信息对象获取存储源管理对象。
	 * 
	 * @param configuration the hbase configuration object
	 * @return the LogStorageManager object
	 */
	public static LogStorageManager getStorageManager(Configuration configuration){
		if (baseLogStorageManager == null){
			baseLogStorageManager = new HBaseLogStorageManager(new HBaseLogSchemaAdmin(configuration));
		}
		return baseLogStorageManager;
	}

	@Override
	public HBaseLogStorage create(String id) {
		createFullLogSchema(id);
		HBaseLogStorage logStorage = generateLogStorage(id);
		if (logStorage == null){
		}
		return logStorage;
	}

	protected void createFullLogSchema(String id) {
		try {
			logSchemaAdmin.createAppLogTables(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public HBaseLogStorage getLogStorage(String appID) {
		return generateLogStorage(appID);
	}

	protected HBaseLogStorage generateLogStorage(String appID) {
		return new HBaseLogStorage(appID, this);
	}

	protected HTable getHTable(String appID, LogType logType) throws IOException{
		return logSchemaAdmin.getHTable(appID, logType);
	}

	protected void putHTable(HTable hTable) throws IOException{
		logSchemaAdmin.putHTable(hTable);
	}

	@Override
	public String[] getAllStorageID() {
		//TODO
		return null;
	}

	@Override
	public boolean drop(String id) {
		//TODO
		return false;
	}

	@Override
	public boolean exists(String id) {
		//TODO
		return false;
	}

}
