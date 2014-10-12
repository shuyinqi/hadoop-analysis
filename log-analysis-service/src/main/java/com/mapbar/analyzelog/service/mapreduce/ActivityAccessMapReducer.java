package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.mortbay.log.Log;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.LogTerminate;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;

/**
 * 每天页面访问统计。
 * 
 * @author 邓飞鸽
 */
public class ActivityAccessMapReducer extends AbstractMapReduceJob{

	public static class ActivityAccessMapper extends TableMapper<Text, Text>{
		protected void map(ImmutableBytesWritable row, Result terminate, Context context) throws IOException, InterruptedException {
        	ResultProxy terminateProxy = ResultProxy.getResultProxy(terminate, LogTerminate.SN);
        	String activityList = terminateProxy.getValue(LogTerminate.SN, "actis", null);
        	String appVersion = terminateProxy.getValueNotNull("appv");
        	String uid = terminateProxy.getValue("uid");
        	if (StringUtils.isEmpty(activityList)){
        		return;
        	}
        	String[] activities = activityList.split("\\|");
        	for (String activitiy : activities){
        		String activitiyName = activitiy.split(",")[0]; //页面名称
        		if (StringUtils.isEmpty(activitiyName)){
        			continue;
        		}
        		int resTime = NumberUtils.toInt(activitiy.split(",")[1], 1); //停留时间
				Text key = new Text(terminateProxy.getFormatDateByTimestamp("time")
						+ "|" + appVersion + "|" + activitiyName);
        		context.write(key, new Text(resTime + "|" + uid));
        	}
		};
	}

	public static class ActivityAccessReducer extends DBCounterReducer<Text, Text> {
		protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			int count = 0;
			long sum = 0;
			List<String> users = new ArrayList<String>();
			for(Text value: values){
				count++;
				String[] arrValues = value.toString().split("\\|");
				sum += Integer.parseInt(arrValues[0]);
				if(!users.contains(arrValues[1])){
					users.add(arrValues[1]);
				}
			}
			int userCount = users.size();
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String appVersion = arrKey[1];
			String activityName = arrKey[2];
			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("app_version", appVersion);
			resultKey.put("activity_name", activityName);
			ValueDBWritable value = new ValueDBWritable("visit_count", count);
			value.put("residence_time", sum);
			value.put("user_count", userCount);

			context.write(resultKey, value);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		
		Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogTerminate.SN, new String[]{"time", "actis", "appv", "uid"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.TERMINATE), scan, ActivityAccessMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,3);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_activity_stat_separate", new String[] { "date", "activity_name", "app_version"},
				new String[] { "visit_count", "user_count", "residence_time"}, ActivityAccessReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
