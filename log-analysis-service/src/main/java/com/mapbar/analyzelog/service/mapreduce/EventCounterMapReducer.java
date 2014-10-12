package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.LogEvent;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;

/**
 * 事件消息数量统计。
 * 
 * @author 邓飞鸽
 */
public class EventCounterMapReducer extends AbstractMapReduceJob{

	public static class EventCounterMapper extends TableMapper<Text, IntWritable>{
		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
	    	ResultProxy proxy = ResultProxy.getResultProxy(value, LogEvent.SN);
	    	String eventID = proxy.getValueNotNull("eid");
	    	String lable = proxy.getValueNotNull( "lab");
	    	String appVersion = proxy.getValueNotNull("appv");
	    	int acc = proxy.getIntValue("acc", 1);
	
			Text key = new Text(proxy.getFormatDateByTimestamp("time") + "|"
					+ eventID + "|" + lable + "|" + appVersion);
	
	    	context.write(key, new IntWritable(acc));
		};
	}

	public static class EventCounterReducer extends DBCounterReducer<Text, IntWritable> {
		
		protected void reduce( Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sumEventAcc = 0;
			for(IntWritable acc: values){
				sumEventAcc += acc.get();
			}
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String eventID = arrKey[1];
			String label = arrKey[2];
			String appVersion = arrKey[3];

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("app_version", appVersion);
			resultKey.put("name", eventID);
			resultKey.put("label", label);
			ValueDBWritable value = new ValueDBWritable("count", sumEventAcc);
			context.write(resultKey, value);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(1, LogEvent.SN, new String[]{"eid", "lab", "appv", "time", "acc"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.EVENT), scan, EventCounterMapper.class, 
				Text.class, IntWritable.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_event_stat_separate", new String[] { "date", "app_version", "name", "label" },
				new String[] { "count" }, EventCounterReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
