package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import com.mapbar.analyzelog.core.SystemConstants;
import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.Argument;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;
import com.mapbar.analyzelog.service.utils.DateUtil;
/***
 * 
 * @（#）:UserForSubsistenceAllMapReducer.java 
 * @description:  
 * @author:  sunjy  2012-9-18 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class UserForSubsistenceAllMapReducer extends AbstractMapReduceJob {
	final Log LOG = LogFactory.getLog(UserForSubsistenceAllMapReducer.class);
	private static final String OUTPUT_TABLE_NAME = "la_subsistence_stat";
	private static final int SUBSISTECEDAY = 7;
	private static final String CURRENT_TIME = "current_time";
	private static final String DAY = "day";
	
	public static class UserForSubsistenceAllMapper extends TableMapper<Text, Text>{
		final Log LOG = LogFactory.getLog(UserForSubsistenceAllMapper.class);
		private HTable hTable;
		private Long currentTime,endTime,startTime;
		private Calendar startCalendar,endCalendar;
		private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		private Set<String> list = new HashSet<String>();
		@Override
		protected void setup(Context context) throws IOException,InterruptedException {
			String appID = context.getConfiguration().get(Argument.APP_ID_NAME);
			hTable = new HTable(context.getConfiguration(), SystemConstants.getTableName(appID));
			currentTime = context.getConfiguration().getLong(CURRENT_TIME, 0);
			startCalendar = DateUtil.formatToDate(DateUtil.getStepBeforeDay(new Date(currentTime),1));
			startTime=startCalendar.getTimeInMillis();
			endCalendar =DateUtil.formatToDate(DateUtil.getStepBeforeDay(new Date(currentTime),8));
			endTime=endCalendar.getTimeInMillis();
			System.out.println(DateUtil.format(new Date(endTime)));
        	Scan scan = new Scan();
        	scan.setTimeRange(startTime,endTime);
        	scan.setBatch(1000);
        	scan.setCaching(10);
        	ResultScanner ss = hTable.getScanner(scan);
        	try {
        	for(Result r:ss){
                 for(KeyValue kv:r.raw()){
                	 LOG.info(new String(kv.getRow()));
                     list.add(new String(kv.getRow()));
                 }
             }
        	 LOG.info("list大小"+list.size());
        	}catch(Exception e){
        		LOG.info("UserForSubsistenceMapReducer:"+e.getStackTrace());
        	} finally {
        		  ss.close();  
        		}
		}
		

		protected void map(ImmutableBytesWritable row, Result value, Context context) 
				throws IOException, InterruptedException {
			String date = dateFormat.format(new Date(context.getConfiguration().getLong(CURRENT_TIME, 0)));
			ResultProxy launchResult = ResultProxy.getResultProxy(value);
			String uid = launchResult.getValue(LogLaunch.SN, "uid");
			if(list.contains(uid)){
				context.write(new Text(date), new Text(uid));
			}
		};

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
			if (hTable != null){
				hTable.close();
			}
		}

	}
	
	public static class UserForSubsistenceAllReducer extends  DBCounterReducer<Text, Text>{
		final Log LOG = LogFactory.getLog(UserForSubsistenceAllReducer.class);
		protected void reduce(Text key,Iterable<Text> values, Context context) 
				throws IOException, InterruptedException{
			 Set<String> userKeys = new HashSet<String>();
				for(Text val : values){
					String uid = val.toString();
						userKeys.add(uid);
				}
				
				int day = context.getConfiguration().getInt(DAY, 0);
				KeyDBWritable resultKey = new KeyDBWritable();
				resultKey.put("date", key.toString());
				resultKey.put("before_day", day);
				ValueDBWritable value = new ValueDBWritable("visit_user_count", userKeys.size());
				context.write(resultKey, value);
		}
	}
	@Override
	public void run(Job job) throws IOException, InterruptedException,ClassNotFoundException {
		int day = getArguments().getIntValue("-d", 7);
		Calendar endDay = getYesterdayOfEnd();
		Calendar startDay = (Calendar) endDay.clone();
		startDay.add(Calendar.DAY_OF_MONTH, -SUBSISTECEDAY);
		Calendar currentDay = (Calendar) endDay.clone();
		currentDay.add(Calendar.DAY_OF_MONTH, -day);
		
		job.getConfiguration().setLong(CURRENT_TIME, currentDay.getTimeInMillis());
		job.getConfiguration().setInt(DAY, day);	
		job.getConfiguration().setLong(HConstants.HBASE_REGIONSERVER_LEASE_PERIOD_KEY, 2044952);
		
    	
		startDay=DateUtil.formatToDate(DateUtil.getStepBeforeDay(DateUtil.convert(startDay.getTime()),8));
    	endDay=DateUtil.formatToDate(DateUtil.getStepBeforeDay(DateUtil.convert(endDay.getTime()),8));
    	
    	Scan scan = getTimeRangeScan(startDay,endDay, LogLaunch.SN, new String[]{"uid"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, UserForSubsistenceAllMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),day+"",20);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob(OUTPUT_TABLE_NAME, new String[] { "date", "before_day"},
				new String[] { "visit_user_count"}, UserForSubsistenceAllReducer.class, job);
		
		job.waitForCompletion(true);
	}

}