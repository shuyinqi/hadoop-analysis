package com.mapbar.analyzelog.core.hbase;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.io.hfile.Compression.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mapbar.analyzelog.core.SystemConstants;
import com.mapbar.analyzelog.core.SystemConstants.LogType;

/**
 * 提供对日志库的管理。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class HBaseLogSchemaAdmin {

	private static Logger logger = LoggerFactory.getLogger(HBaseLogSchemaAdmin.class);

	public static HBaseAdmin hbaseAdmin;
	public static HTablePool tablePool;

	public HBaseLogSchemaAdmin() {
		this(HBaseConfiguration.create());
	}

	public HBaseLogSchemaAdmin(Configuration configuration) {
		if (hbaseAdmin == null) {
			try {
				logger.debug("Initialize HBase database admin configuration.");
				hbaseAdmin = new HBaseAdmin(configuration);
				tablePool = new HTablePool(configuration, configuration.getInt(
						"hbase.client.htable.pool", 500));
			} catch (Exception e) {
				throw new HBaseConnectionException("连接 HBase 数据库异常。", e);
			}
		}
	}

	/**
	 * 根据 appID 创建 APP 日志主表。
	 * 
	 * @param appID
	 *            APP ID
	 * @throws IOException
	 *             if a remote or network exception occurs
	 */
	public void createAppLogTables(String appID) throws IOException {
		assert StringUtils.isBlank(appID);

		createAppUserTable(appID);
		createLogTable(appID, LogType.LAUNCH);
		createLogTable(appID, LogType.TERMINATE);
		createLogTable(appID, LogType.EVENT);
		createLogTable(appID, LogType.ERROR);
	}

	/*
	 * 根据 APPID 创建 app logs 用户主表。
	 */
	protected void createAppUserTable(String appID) throws IOException {
		assert StringUtils.isBlank(appID);

		String tableName = SystemConstants.getTableName(appID);
		HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
		tableDescriptor.addFamily(getHColumnDescriptor(LogType.CONFIG));
//		tableDescriptor.addFamily(getHColumnDescriptor(LogType.LAUNCH));
	//	tableDescriptor.addFamily(getHColumnDescriptor(LogType.TERMINATE));
//		tableDescriptor.addFamily(getHColumnDescriptor(LogType.EVENT));
//		tableDescriptor.addFamily(getHColumnDescriptor(LogType.ERROR));

		hbaseAdmin.createTable(tableDescriptor);
	}

	/*
	 * 根据日志类型获取 family column 对象.
	 */
	protected HColumnDescriptor getHColumnDescriptor(LogType logType) {
		HColumnDescriptor columnDescriptor = new HColumnDescriptor(
				String.valueOf(logType.shortName()));
		columnDescriptor.setCompressionType(Algorithm.LZO);
		return columnDescriptor;
	}

	/*
	 * 根据 appID 及日志类型创建日志信息表。
	 */
	protected HTable createLogTable(String appID, LogType logType)
			throws IOException {
		assert StringUtils.isBlank(appID);

		String tableName = SystemConstants.getTableName(appID, logType);
		HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
		tableDescriptor
				.addFamily(new HColumnDescriptor(String.valueOf(logType)));
		tableDescriptor.addFamily(new HColumnDescriptor(String
				.valueOf(LogType.CONFIG)));

		hbaseAdmin.createTable(tableDescriptor);
		return getHTable(tableName);
	}

	/*
	 * 根据表名获取 HTable 表。
	 */
	private HTable getHTable(String appTableName) throws IOException {
		return (HTable) tablePool.getTable(appTableName);
	}

	/**
	 * 获取 HTable 对象。
	 * 
	 * @param appID the app id
	 * @param logType the app log type
	 * @return hbase table
	 * 
	 * @throws IOException if a remote or network exception occurs
	 */
	protected HTable getHTable(String appID, LogType logType) throws IOException {
		HTable hTable = (HTable) tablePool.getTable(SystemConstants.getTableName(appID, logType));
		// 默认
		boolean isAutoFlush = hbaseAdmin.getConfiguration().getBoolean("hbase.client.autoflush", true);
		hTable.setAutoFlush(isAutoFlush);

		return hTable;
	}

	protected void putHTable(HTable hTable){
		tablePool.putTable(hTable);
	}

	/**
	 * 根据 APPID 删除所有日志表及数据。
	 * 
	 * @param appID APP ID
	 * @throws IOException  if a remote or network exception occurs
	 */
	public void dropAppLogTables(String appID) throws IOException {
		assert StringUtils.isBlank(appID);

		deleteTable(SystemConstants.getTableName(appID));
		deleteTable(SystemConstants.getTableName(appID, LogType.LAUNCH));
		deleteTable(SystemConstants.getTableName(appID, LogType.TERMINATE));
		deleteTable(SystemConstants.getTableName(appID, LogType.EVENT));
		deleteTable(SystemConstants.getTableName(appID, LogType.ERROR));
	}

	private void deleteTable(String tableName) throws IOException {
		if (hbaseAdmin.tableExists(tableName)) {
			hbaseAdmin.disableTable(tableName);
			hbaseAdmin.deleteTable(tableName);
		}
	}
}
