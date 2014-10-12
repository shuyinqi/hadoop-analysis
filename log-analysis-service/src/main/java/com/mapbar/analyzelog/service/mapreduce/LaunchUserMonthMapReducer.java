package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
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
 * 专用于统计app为1000的月活跃
 * <i>(活跃用户统计)</i>
 * 
 * @author sunjy
 */
public class LaunchUserMonthMapReducer extends UserInterval4SonJobMapReducer{
	
	final Log LOG = LogFactory.getLog(LaunchUserMonthMapReducer.class);
//	private static final String OUTPUT_TABLE_NAME = "la_active_stat";
//	private static final String ENLIVEN_USER_DAY = "enliver_user_day";
//	private static final String ONLINE_DATE="la_active_date";
//
//	public static class LaunchUserMonthMapper extends TableMapper<Text, Text>{
//		  
//		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
//			ResultProxy launch = ResultProxy.getResultProxy(value, LogLaunch.SN);
//        	String uid = launch.getValue("uid");
//        	context.write(new Text(uid),new Text("1"));
//        	
//		};
//	}
//	
//	public static class LaunchUserMonthReducer extends Reducer<Text, Text, Text, Text> {
//		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//			context.write(new Text("1"), key);
//		}
//	};
//	
//	public static class LaunchUserMonthTotalMapper extends Mapper<LongWritable, Text,Text,Text>{
//		final Log LOG = LogFactory.getLog(LaunchUserMonthMapReducer.class);
//		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//			LOG.info(value);
//			System.out.println(value);
//			String cur_date = context.getConfiguration().get(ONLINE_DATE);
//			String arrKey = value.toString();
//			System.out.println(cur_date);
//			context.write(new Text(cur_date), new Text(arrKey));
//		};
//	}
//	
//
//	public static class LaunchUserMonthTotalReducer extends DBCounterReducer<Text, Text> {
//		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//			HashSet<String> set = new HashSet<String>();      
//			for(Text val : values){
//				String uid = val.toString();
//				set.add(uid);
//			}
//			int day = context.getConfiguration().getInt(ENLIVEN_USER_DAY, 7);
//			System.out.println(set.size());
//			System.out.println(key.toString());
//			System.out.println(day);
//			KeyDBWritable resultKey = new KeyDBWritable();
//			resultKey.put("date", key.toString());
//			resultKey.put("before_day", day);
//			ValueDBWritable value = new ValueDBWritable("active_user_count", set.size());
//			context.write(resultKey, value);
//		}
//	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		int day = getArguments().getIntValue("-d", 30);
		
		Calendar startDay = getStartCalendarForDay();
		startDay.set(Calendar.DAY_OF_MONTH, startDay.get(Calendar.DAY_OF_MONTH)-day-1);
		Calendar endDay = getYesterdayOfEnd();
		Date dt=endDay.getTime();
		
//		job.getConfiguration().setInt(ENLIVEN_USER_DAY, day);
		String strString =	new SimpleDateFormat("yyyy-MM-dd").format(dt);
//		job.getConfiguration().set(ONLINE_DATE, strString);
		
//		Calendar oneStepDay = (Calendar) startDay.clone();
//		oneStepDay.set(Calendar.DAY_OF_MONTH, oneStepDay.get(Calendar.DAY_OF_MONTH)+10);
//		
//		Scan scan = getTimeRangeScan(startDay, oneStepDay, LogLaunch.SN, new String[]{"uid"});
//		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, LaunchUserMonthMapper.class, 
//				Text.class, Text.class, job,getArguments().getAppID(),null,35);
//		
		String parentsPath="/home/jishu/LaunchUserMonth/"+DateUtil.getDate()+"/"+getArguments().getAppID();
//		
//		String path1 =parentsPath+"/job_"+System.currentTimeMillis();
//		TableMapbarReduceProxy.initReduceProxy(path1,1, LaunchUserMonthReducer.class, Text.class, Text.class, getArguments(), job);
//		job.waitForCompletion(true);
//		/**
//		 * job2
//		 */
		Configuration conf = HBaseConfiguration.create();
//		conf.set(Argument.APP_ID_NAME, getArguments().getAppID());
//		Job job2 = new Job(conf, job.getJobName()+"second");
//		job2.setJarByClass(this.getClass());
//		
//		Calendar twoStepDay = (Calendar) oneStepDay.clone();
//		twoStepDay.set(Calendar.DAY_OF_MONTH, twoStepDay.get(Calendar.DAY_OF_MONTH)+10);
//		Scan scan2 = getTimeRangeScan(oneStepDay, twoStepDay, LogLaunch.SN, new String[]{"uid"});
//		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan2, LaunchUserMonthMapper.class, 
//				Text.class, Text.class, job2,getArguments().getAppID(),null,35);
//		
//		String path2 =parentsPath+"/job_"+System.currentTimeMillis();
//		TableMapbarReduceProxy.initReduceProxy(path2,1, LaunchUserMonthReducer.class, Text.class, Text.class, getArguments(), job2);
//		job2.waitForCompletion(true);
//		/**
//		 * job3
//		 */
//		conf.set(Argument.APP_ID_NAME, getArguments().getAppID());
//		Job job3 = new Job(conf, job.getJobName()+"three");
//		job3.setJarByClass(this.getClass());
//		
//		Scan scan3 = getTimeRangeScan(twoStepDay, endDay, LogLaunch.SN, new String[]{"uid"});
//		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan3, LaunchUserMonthMapper.class, 
//				Text.class, Text.class, job3,getArguments().getAppID(),null,35);
//		
//		String path3 =parentsPath+"/job_"+System.currentTimeMillis();
//		TableMapbarReduceProxy.initReduceProxy(path3,1, LaunchUserMonthReducer.class, Text.class, Text.class, getArguments(), job3);
//		job3.waitForCompletion(true);
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