package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.Argument;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;

/**
 * 统计过去n天内启动过应用程序的用户名数，其 n 默认为 14. 在启动参数中可通过 -d 来进行指定天数。
 * <i>(活跃用户统计)</i>
 * 
 * @author 邓飞鸽
 */
public abstract class UserInterval4SonJobMapReducer extends AbstractMapReduceJob{

	protected static final String OUTPUT_TABLE_NAME = "la_active_stat";
	protected static final String ENLIVEN_USER_DAY = "enliver_user_day";
	protected static final String ONLINE_DATE="la_active_date";

	private static class LaunchUserInterval4SonJobMapper extends TableMapper<Text, Text>{
		
		private Text key = new Text();
		
		private Text value = new Text();
		  
		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy launch = ResultProxy.getResultProxy(value, LogLaunch.SN);
        	this.key.set(launch.getValue("uid"));
        	this.value.set("1");
        	context.write(this.key, this.value);
        	
		};
	}
	
	private static class LaunchUserInterval4SonJobReducer extends Reducer<Text, Text, Text, Text> {
		
		private Text outKey = new Text();
		
		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			this.outKey.set("1");
			context.write(this.outKey, key);
		}
	};
	
	protected static class LaunchUserMonthTotalMapper extends Mapper<LongWritable, Text, Text, Text>{
		final Log LOG = LogFactory.getLog(LaunchUserMonthMapReducer.class);
		
		private Text key = new Text();
		
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//			LOG.info(value);
//			System.out.println(value);
//			String arrKey = value.toString();
			this.key.set(context.getConfiguration().get(ONLINE_DATE));
			context.write(this.key, value);
		};
	}
	

	protected static class LaunchUserMonthTotalReducer extends DBCounterReducer<Text, Text> {
		
		private KeyDBWritable resultKey = new KeyDBWritable();
		
		private ValueDBWritable value = new ValueDBWritable();
		
		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			HashSet<String> set = new HashSet<String>();      
			for(Text val : values){
				String uid = val.toString();
				set.add(uid);
			}
			int day = context.getConfiguration().getInt(ENLIVEN_USER_DAY, 7);
			this.resultKey.put("date", key.toString());
			this.resultKey.put("before_day", day);
			this.value.put("active_user_count", set.size());
			context.write(this.resultKey, this.value);
		}
	};
	
	/**
	 * 创建并执行子作业
	 * @param appId 应用ID
	 * @param userIntervalDay 总的统计天数
	 * @param setIntervalDay 每个自作业的统计天数
	 * @param startDay 统计的开始时间
	 * @param endDay 统计的结束时间
	 * @param endDate 统计的结束时间
	 * @param jobBaseName 子作业名的基础部分
	 * @param parentsPath 子作业结果的父级目录
	 * @param conf Hadoop配置
	 * @return 子作业路径列表
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 */
	protected String createAndRunSonJob(String appId, int userIntervalDay, int setIntervalDay, Calendar startDay, Calendar endDay, String endDate, String jobBaseName, String parentsPath, Configuration conf) throws IOException, InterruptedException, ClassNotFoundException {
		StringBuilder jobPaths = new StringBuilder();
		int times = (userIntervalDay / setIntervalDay) + (userIntervalDay % setIntervalDay == 0 ? 0 : 1);
		Calendar tempDay = (Calendar) startDay.clone();
		Job job = null;
		Scan scan = null;
		String path = null;
		for (int i = 0; i < times; i++) {
			conf.set(Argument.APP_ID_NAME, getArguments().getAppID());
			job = new Job(conf, new StringBuilder(jobBaseName).append("_").append(i).toString());
			job.setJarByClass(this.getClass());

			path = parentsPath+"/job_"+System.currentTimeMillis();
			jobPaths.append(path);
			if (i == times - 1) {
				tempDay = endDay;
			} else {
				tempDay = (Calendar) startDay.clone();
				tempDay.add(Calendar.DAY_OF_MONTH, setIntervalDay);
				jobPaths.append(",");
			}
			scan = getTimeRangeScan(startDay, tempDay, LogLaunch.SN, new String[]{"uid"});
			TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, LaunchUserInterval4SonJobMapper.class, 
					Text.class, Text.class, job,getArguments().getAppID(),null,55);
			
			TableMapbarReduceProxy.initReduceProxy(path,1, LaunchUserInterval4SonJobReducer.class, Text.class, Text.class, getArguments(), job);
			job.waitForCompletion(true);
			startDay.add(Calendar.DAY_OF_MONTH, setIntervalDay);
			path = null;
			scan = null;
			job = null;
		}
		return jobPaths.toString();
	}
}