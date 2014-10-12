package com.mapbar.analyzelog.service.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.hadoop.mapreduce.Job;

/**
 * 
 * A container for configuration property names for jobs with DB output. <br>
 * <i> notes: <br>
 * 由于 org.apache.hadoop.mapred.lib.db.DBConfiguration 不支持通过 {@link Job}
 * 进行初始化，只支持使用 {@link JobConf}，所以该 {@link DBConfiguration} 参数 Hadoop 中的
 * DBConfiguration 实现，使其兼容 {@link Job}. </i>
 * 
 * @author dengfg
 * @see org.apache.hadoop.mapred.lib.db.DBConfiguration
 */
public final class DBConfiguration {
	
	public static final String RUN_APP_ID = "mapred.run.app.id";

	/** The JDBC Driver class name */
	public static final String DRIVER_CLASS_PROPERTY = "mapred.jdbc.driver.class";

	/** JDBC Database access URL */
	public static final String URL_PROPERTY = "mapred.jdbc.url";

	/** User name to access the database */
	public static final String USERNAME_PROPERTY = "mapred.jdbc.username";

	/** Password to access the database */
	public static final String PASSWORD_PROPERTY = "mapred.jdbc.password";

	/** Output table name */
	public static final String OUTPUT_TABLE_NAME_PROPERTY = "mapred.jdbc.output.table.name";

	/** Field names in the Output table */
	public static final String OUTPUT_KEY_FIELD_NAMES_PROPERTY = "mapred.jdbc.output.keyfield.names";
	public static final String OUTPUT_VAL_FIELD_NAMES_PROPERTY = "mapred.jdbc.output.valuefield.names";

	public static final String INPUT_CLASS_PROPERTY = "mapred.jdbc.input.class";

	/**
	 * Sets the DB access related fields in the JobConf.
	 * 
	 * @param job  the job
	 * @param driverClass  JDBC Driver class name
	 * @param dbUrl JDBC DB access URL.
	 * @param userName DB access username
	 * @param passwd DB access passwd
	 */
	public static void configureDB(Job job, String driverClass,
			String dbUrl, String userName, String passwd) {
		Configuration conf = job.getConfiguration();

		conf.set(DRIVER_CLASS_PROPERTY, driverClass);
		conf.set(URL_PROPERTY, dbUrl);
		if (userName != null)
			conf.set(USERNAME_PROPERTY, userName);
		if (passwd != null)
			conf.set(PASSWORD_PROPERTY, passwd);
	}

	/**
	 * Sets the DB access related fields in the JobConf.
	 * 
	 * @param job the job
	 * @param driverClass JDBC Driver class name
	 * @param dbUrl JDBC DB access URL.
	 */
	public static void configureDB(Job job, String driverClass, String dbUrl) {
		configureDB(job, driverClass, dbUrl, null, null);
	}

	private Configuration conf;

	public DBConfiguration(Configuration configuration) {
		this.conf = configuration;
	}

	public void setInputClass(Class<? extends DBWritable> inputClass) {
		conf.setClass(DBConfiguration.INPUT_CLASS_PROPERTY, inputClass,
				DBWritable.class);
	}

	public String getOutputTableName() {
		return conf.get(DBConfiguration.OUTPUT_TABLE_NAME_PROPERTY);
	}

	public void setOutputTableName(String tableName) {
		conf.set(DBConfiguration.OUTPUT_TABLE_NAME_PROPERTY, tableName);
	}

	public String[] getOutputKeyFieldNames() {
		return conf.getStrings(DBConfiguration.OUTPUT_KEY_FIELD_NAMES_PROPERTY);
	}

	public void setOutputKeyFieldNames(String... fieldNames) {
		conf.setStrings(DBConfiguration.OUTPUT_KEY_FIELD_NAMES_PROPERTY, fieldNames);
	}
	
	public String[] getOutputValueFieldNames() {
		return conf.getStrings(DBConfiguration.OUTPUT_VAL_FIELD_NAMES_PROPERTY);
	}
	
	public void setOutputValueFieldNames(String... fieldNames) {
		conf.setStrings(DBConfiguration.OUTPUT_VAL_FIELD_NAMES_PROPERTY, fieldNames);
	}

	/**
	 * Returns a connection object o the DB
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {

		Class.forName(conf.get(DBConfiguration.DRIVER_CLASS_PROPERTY));

		if (conf.get(DBConfiguration.USERNAME_PROPERTY) == null) {
			return DriverManager.getConnection(conf
					.get(DBConfiguration.URL_PROPERTY));
		} else {
			return DriverManager.getConnection(
					conf.get(DBConfiguration.URL_PROPERTY),
					conf.get(DBConfiguration.USERNAME_PROPERTY),
					conf.get(DBConfiguration.PASSWORD_PROPERTY));
		}
	}

	public String getAppID() {
		return conf.get(RUN_APP_ID);
	}
}
