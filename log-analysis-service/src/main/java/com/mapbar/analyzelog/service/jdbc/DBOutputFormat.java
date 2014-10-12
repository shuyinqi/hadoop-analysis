package com.mapbar.analyzelog.service.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.mapreduce.TableOutputCommitter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.util.StringUtils;

import com.mapbar.analyzelog.core.utils.DateFormatUtils;

/**
 * A OutputFormat that sends the reduce output to a SQL table.
 * <p>
 * {@link DBOutputFormat} accepts &lt;key,value&gt; pairs, where key has a type
 * extending DBWritable. Returned {@link RecordWriter} writes <b>only the
 * key</b> to the database with a batch SQL query.
 * 
 * @author 邓飞鸽
 * 
 */
public class DBOutputFormat<K extends KeyDBWritable, V extends ValueDBWritable> extends OutputFormat<K, V> implements Configurable{

	private static final Log LOG = LogFactory.getLog(DBOutputFormat.class);
	
	private Configuration configuration;

	/**
	 * A RecordWriter that writes the reduce output to a SQL table
	 */
	protected class DBRecordWriter extends RecordWriter<K, V> {

		private Connection connection;
		private PreparedStatement statement;

		protected DBRecordWriter(Connection connection,
				PreparedStatement statement) throws SQLException {
			this.connection = connection;
			this.statement = statement;
			this.connection.setAutoCommit(false);
		}

		/** {@inheritDoc} */
		public void write(K key, V value) throws IOException {
			try {
				key.initWrite(getConf());
				key.write(statement, value);
				statement.addBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/** {@inheritDoc} */
		public void close(TaskAttemptContext context) throws IOException,
				InterruptedException {
			try {
				statement.executeBatch();
				connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException ex) {
					LOG.warn(StringUtils.stringifyException(ex));
				}
				throw new IOException(e.getMessage());
			} finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException ex) {
					throw new IOException(ex.getMessage());
				}
			}
		}
	}

	/**
	 * Constructs the query used as the prepared statement to update data.
	 * 
	 * @param table the table to insert into
	 * @param fieldNames the fields to insert into. If field names are unknown, supply an array of nulls.
	 */
	protected String constructQuery(String table, String[] keyFields,
			String[] valueFeilds, String appID) {    
		if (keyFields == null || valueFeilds == null) {
			throw new IllegalArgumentException("Field names may not be null");
		}

		StringBuilder query = new StringBuilder();
		query.append("UPDATE ").append(table);

		if (valueFeilds.length > 0 && valueFeilds[0] != null) {
			query.append(" SET ");
			for (int i = 0; i < valueFeilds.length; i++) {
				query.append(valueFeilds[i]);
				query.append("=?");
				if (i != valueFeilds.length - 1) {
					query.append(",");
				}
			}
			query.append(",update_time='").append(DateFormatUtils.getNowTime()).append("'");
		}
		query.append(" WHERE appid="+ appID);
		for (int i = 0; i < keyFields.length; i++) {
			query.append(" AND ");
			query.append(keyFields[i]);
			query.append(" = ? ");
		}
		System.out.println("DBOupputFormat:"+query.toString());
		return query.toString();
	}

	/** {@inheritDoc} */
	public void checkOutputSpecs(JobContext context) throws IOException,
			InterruptedException {
	}

	/** {@inheritDoc} */
	public RecordWriter<K, V> getRecordWriter(TaskAttemptContext context)
			throws IOException, InterruptedException {
		DBConfiguration dbConf = new DBConfiguration(context.getConfiguration());
		setConf(context.getConfiguration());
		addDBConfiguration(getConf());

		String tableName = dbConf.getOutputTableName();
		String[] keyFieldNames = dbConf.getOutputKeyFieldNames();
		String[] valueFieldNames = dbConf.getOutputValueFieldNames();
		String appID = dbConf.getAppID();

		try {
			Connection connection = dbConf.getConnection();
			PreparedStatement statement = connection
					.prepareStatement(constructQuery(tableName, keyFieldNames,
							valueFieldNames, appID));
			return new DBRecordWriter(connection, statement);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IOException(ex.getMessage());
		}
	}

	/** {@inheritDoc} */
	public OutputCommitter getOutputCommitter(TaskAttemptContext context)
			throws IOException, InterruptedException {
		return new TableOutputCommitter();
	}
	
	 /**
	   * Initializes the reduce-part of the job with the appropriate output settings
	   * 
	   * @param job The job
	   * @param tableName The table to insert data into
	   * @param fieldNames The field names in the table. If unknown, supply the appropriate
	   *          number of nulls.
	   */
	  public static void setOutput(Job job, String tableName, String[] keyFields, String[] valFeilds) {
		job.setOutputFormatClass(DBOutputFormat.class);

	    DBConfiguration dbConf = new DBConfiguration(job.getConfiguration());
	    dbConf.setOutputTableName(tableName);
	    dbConf.setOutputKeyFieldNames(keyFields);
		dbConf.setOutputValueFieldNames(valFeilds);
	  }

	@Override
	public void setConf(Configuration conf) {
		this.configuration = conf;
	}
	
	private void addDBConfiguration(Configuration configuration) throws IOException {
		ClassLoader cL = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = cL.getResourceAsStream("db-site.xml");
		if (inputStream == null){
			throw new IOException("Not found db-site.xml in classpath!!");
		}
		configuration.addResource(inputStream);
	}

	@Override
	public Configuration getConf() {
		return configuration;
	}
}