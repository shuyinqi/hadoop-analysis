package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.Argument;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBConfiguration;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;
import com.mapbar.analyzelog.service.utils.DateUtil;

/**
 * 统计过去n天内启动过应用程序的用户名数，其 n 默认为 14. 在启动参数中可通过 -d 来进行指定天数。
 * <i>(活跃用户统计)</i>
 * 
 * @author 邓飞鸽
 */
public class LaunchUserIntervalMapReducer extends UserInterval4SonJobMapReducer{

//	private static final String OUTPUT_TABLE_NAME = "la_active_stat";
//	private static final String ENLIVEN_USER_DAY = "enliver_user_day";
//	private static final String ONLINE_DATE="la_active_date";
//
//	public static class LaunchUserIntervalMapper extends TableMapper<Text, Text>{
//		  
//		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
//			ResultProxy launch = ResultProxy.getResultProxy(value, LogLaunch.SN);
//			String cur_date = context.getConfiguration().get(ONLINE_DATE);
//        	String uid = launch.getValue("uid");
//        	context.write(new Text(cur_date), new Text(uid));
//        	
//		};
//	}
//
//	public static class LaunchUserIntervalReducer extends DBCounterReducer<Text, Text> {
//		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//			HashSet<String> set = new HashSet<String>();      
//			for(Text val : values){
//				String uid = val.toString();
//				set.add(uid);
//			}
//			int day = context.getConfiguration().getInt(ENLIVEN_USER_DAY, 7);
//			KeyDBWritable resultKey = new KeyDBWritable();
//			resultKey.put("date", key.toString());
//			resultKey.put("before_day", day);
//			ValueDBWritable value = new ValueDBWritable("active_user_count", set.size());
//			context.write(resultKey, value);
//		}
//	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		int day = getArguments().getIntValue("-d", 7);

		job.getConfiguration().setInt(ENLIVEN_USER_DAY, day);
		Calendar startDay = getStartCalendarForDay();
		startDay.set(Calendar.DAY_OF_MONTH, startDay.get(Calendar.DAY_OF_MONTH)-day-1);
		Calendar endDay = getYesterdayOfEnd();
		Date dt=endDay.getTime();
		String strString =	new SimpleDateFormat("yyyy-MM-dd").format(dt);
//		job.getConfiguration().set(ONLINE_DATE, strString);
//		
//		Scan scan = getTimeRangeScan(startDay, endDay, LogLaunch.SN, new String[]{"uid"});
//		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, LaunchUserIntervalMapper.class, 
//				Text.class, Text.class, job,getArguments().getAppID(),null,25);
//		job.setNumReduceTasks(3);
//		JDBCMapReduceUtil.initTableReducerJob(OUTPUT_TABLE_NAME, new String[] { "date", "before_day" },
//				new String[] { "active_user_count" }, LaunchUserIntervalReducer.class, job);
//		
//		job.waitForCompletion(true);
		String parentsPath="/home/jishu/LaunchUserMonth/"+DateUtil.getDate()+"/"+getArguments().getAppID();
		Configuration conf = HBaseConfiguration.create();
		String jobPaths = super.createAndRunSonJob(getArguments().getAppID(), day, 3, startDay, endDay, strString, job.getJobName(), parentsPath, conf);
		/**
		 * job4
		 */
		conf.set(Argument.APP_ID_NAME, getArguments().getAppID());
		Job job4 = new Job(conf, job.getJobName()+"_total");
		job4.setJarByClass(this.getClass());
		job4.getConfiguration().set(DBConfiguration.RUN_APP_ID, getArguments().getAppID());
		job4.getConfiguration().setInt(UserInterval4SonJobMapReducer.ENLIVEN_USER_DAY, day);
		job4.getConfiguration().set(UserInterval4SonJobMapReducer.ONLINE_DATE, strString);
		
		job4.setMapperClass(LaunchUserMonthTotalMapper.class);
		job4.setMapOutputKeyClass(Text.class);
	    job4.setMapOutputValueClass(Text.class);
	    FileInputFormat.addInputPaths(job4, jobPaths);
	    
		JDBCMapReduceUtil.initTableReducerJob(UserInterval4SonJobMapReducer.OUTPUT_TABLE_NAME, new String[] { "date", "before_day" },
				new String[] { "active_user_count" }, LaunchUserMonthTotalReducer.class, job4);
		
		job4.waitForCompletion(true);
	}
}