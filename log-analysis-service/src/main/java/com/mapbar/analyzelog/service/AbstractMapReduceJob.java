package com.mapbar.analyzelog.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableInputFormatBase;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.log4j.Logger;
import org.mortbay.log.Log;

import com.mapbar.analyzelog.core.SystemConstants;
import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.service.exception.DataStatException;
import com.mapbar.analyzelog.service.jdbc.DBConfiguration;
import com.mapbar.analyzelog.service.utils.DateUtil;

/**
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public abstract class AbstractMapReduceJob implements JobTask {




	protected Logger logger = Logger.getLogger(getClass());

	private Argument arguments;

	public final void start(Argument arguments) {
		this.arguments = arguments;
		Job job = null;
		try {
			job = this.createJob(getArguments().getJobName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.run(job);
		} catch (Exception e) {
			throw new DataStatException(e, "Job execute exception!");
		}
	}

	/***
	 * 获得hbase中对应的表名称
	 * 
	 * @param logType
	 *            日志类型
	 * @return excmple:'app_1000_event'
	 * 
	 */
	protected String getHTableName(LogType logType) {
		return SystemConstants.getTableName(getArguments().getAppID(), logType);
	}

	public Job createJob(String jobName) throws IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set(Argument.APP_ID_NAME, getArguments().getAppID());
		
		Job job = new Job(conf, jobName);
		job.setJarByClass(this.getClass());
		job.getConfiguration().set(DBConfiguration.RUN_APP_ID,
				arguments.getAppID());

		return job;
	}

	protected Calendar getStartCalendarFor6Hour() {
		Calendar startCalendar = getArguments().getCalendarValue("-t");
		if (startCalendar == null) {
			startCalendar = Calendar.getInstance();
			startCalendar.set(Calendar.MINUTE, 0);
			startCalendar.set(Calendar.SECOND, 0);
			startCalendar.set(Calendar.MILLISECOND, 0);

			int preHour = startCalendar.get(Calendar.HOUR_OF_DAY) - 6;
			startCalendar.set(Calendar.HOUR_OF_DAY, preHour);
		}
		return startCalendar;
	}

	protected Calendar getStartCalendarForDay() {
		Calendar startCalendar = getArguments().getCalendarValue("-t");
		if (startCalendar == null) {
			startCalendar = Calendar.getInstance();
			startCalendar.set(Calendar.MINUTE, 0);
			startCalendar.set(Calendar.MILLISECOND, 0);
			startCalendar.set(Calendar.SECOND, 0);
			startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		}
		return startCalendar;
	}

	protected Calendar getYesterdayOfEnd() {
		Calendar calendar = getArguments().getCalendarValue("-t");
		Calendar yesterday = (calendar == null ? Calendar.getInstance()
				: calendar);
		yesterday.set(Calendar.DAY_OF_MONTH,
				yesterday.get(Calendar.DAY_OF_MONTH));
		yesterday.set(Calendar.HOUR_OF_DAY, 23);
		yesterday.set(Calendar.MINUTE, 59);
		yesterday.set(Calendar.SECOND, 59);
		yesterday.set(Calendar.MILLISECOND, 59);
		return yesterday;
	}

	protected Scan getTimeRangeScan(Calendar startCalendar, String family,
			String qualifier) throws IOException {
		return getTimeRangeScan(startCalendar, family,
				new String[] { qualifier });
	}

	protected Scan getTimeRangeScan(Calendar startCalendar, String family,
			String[] qualifiers) throws IOException {
		return getTimeRangeScan(startCalendar, Calendar.getInstance(), family,
				qualifiers);
	}

	/***
	 * 根据时间段判断版本判断扫描插入的数据
	 * 
	 * @param startCalendar
	 * @param endCalendar
	 * @param family
	 *            hbase中列族的名称：la
	 * @param qualifiers
	 *            要查询出来的东西，查询结果,time,uid
	 * @return
	 * @throws IOException
	 * 
	 */
	protected Scan getTimeRangeScan(Calendar startCalendar,
			Calendar endCalendar, String family, String[] qualifiers)
			throws IOException {
		Scan scan= new Scan();
		scan.setCaching(1000);
		if("la".equals(family)||"te".equals(family)||"ev".equals(family)){
			scan.setStartRow(Bytes.toBytes("99999"+String.valueOf(startCalendar.getTimeInMillis())));
			scan.setStopRow(Bytes.toBytes("99999"+String.valueOf(endCalendar.getTimeInMillis())));
			Log.info("setup row:start:"+startCalendar.getTimeInMillis()+"and stop:"+endCalendar.getTimeInMillis());
		}else{
			scan.setTimeRange(startCalendar.getTimeInMillis(),endCalendar.getTimeInMillis());
			Log.info("setup row:start:"+startCalendar.getTimeInMillis()+"and stop:"+endCalendar.getTimeInMillis());
		}
		for (String qualifier : qualifiers) {
			scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
		}
		return scan;
	}
	
	protected Scan getTimeRangeScan(int day,String family, String[] qualifiers)throws IOException {
		Calendar endCalendar = getYesterdayOfEnd();
		Calendar startCalendar =(Calendar) endCalendar.clone();
		startCalendar.add(Calendar.DAY_OF_MONTH,-day+1);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		return getTimeRangeScan(startCalendar, endCalendar, family,qualifiers);
	}

	protected Argument getArguments() {
		return arguments;
	}
	
	
	
	public abstract void run(Job job) throws IOException, InterruptedException,
			ClassNotFoundException;
}
