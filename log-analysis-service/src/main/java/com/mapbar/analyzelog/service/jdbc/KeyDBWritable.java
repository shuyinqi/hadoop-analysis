package com.mapbar.analyzelog.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;


public class KeyDBWritable extends AbstractDBWritable{

	private static final Log LOG = LogFactory.getLog(KeyDBWritable.class);
	
	protected Map<String, Object> fields = new HashMap<String, Object>();
	protected String[] keyFields, valueFields;

	public void initWrite(Configuration conf) {
		DBConfiguration dbConf = new DBConfiguration(conf);
		keyFields = dbConf.getOutputKeyFieldNames();
		valueFields = dbConf.getOutputValueFieldNames();
		Connection connection = null;
		try {
			connection = dbConf.getConnection();
			Statement statement = connection.createStatement();
			String sql = constructQuery(dbConf.getOutputTableName(), keyFields, fields, dbConf.getAppID());
			System.out.println(sql);
			statement.executeUpdate(sql);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void write(PreparedStatement statement, ValueDBWritable dbWritable)
			throws SQLException {
		int i = 1;
		Map<String, Object> valueFeilds = dbWritable.fields;
		for (String valueField : valueFields){
			statement.setString(i, String.valueOf(valueFeilds.get(valueField)));
			i++;
		}
		for (String keyField : keyFields){
			statement.setString(i, String.valueOf(fields.get(keyField)));
			i++;
		}
		
	}

	//this is hate coding...
	protected String constructQuery(String table, String[] keyFields, Map<String, Object> values, String appID) {
		if (keyFields == null) {
			throw new IllegalArgumentException("Field names may not be null");
		}
		StringBuilder query = new StringBuilder();
		query.append("INSERT ").append(table).append("(appid, ");

		if (keyFields.length > 0 && keyFields[0] != null) {
			for (int i = 0; i < keyFields.length; i++) {
				query.append(keyFields[i]);
				if (i != keyFields.length - 1) {
					query.append(",");
				}
			}
			query.append(", update_time");
			query.append(")");
		}
		query.append(" SELECT ");
		query.append(appID +",");

		for (int i = 0; i < keyFields.length; i++) {
			query.append("'").append(values.get(keyFields[i])).append("'");
			if (i != keyFields.length - 1) {
				query.append(",");
			}
		}
		query.append(", now() FROM DUAL WHERE NOT EXISTS(SELECT 1 FROM ");
		query.append(table).append(" WHERE ");
		for (int i = 0; i < keyFields.length; i++) {
			query.append(keyFields[i]).append("='")
					.append(values.get(keyFields[i])).append("'");
			if (i != keyFields.length - 1) {
				query.append(" AND ");
			}
		}
		query.append(" AND appid=").append(appID);
		
		query.append(")");

		return query.toString();
	}

	public void put(String key, String value) {
		fields.put(key, value);
	}

	public void put(String key, int value) {
		fields.put(key, value);
	}
}
