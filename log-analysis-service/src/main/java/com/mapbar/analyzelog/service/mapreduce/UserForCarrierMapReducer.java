package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;
import com.mapbar.analyzelog.service.utils.StatUtils;

/**
 * 运营商活跃用户统计。
 * 
 */
public class UserForCarrierMapReducer extends AbstractMapReduceJob{

	public static class UserForCarrierMapper extends TableMapper<Text, Text>{
		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
        	ResultProxy proxy = ResultProxy.getResultProxy(value, LogLaunch.SN);
        	
        	String uid = proxy.getValue("uid");
			String carrier = proxy.getValueNotNull("car");
			if(carrier!="IGNORE"&&!carrier.equalsIgnoreCase("IGNORE")){
        	Text key = new Text(proxy.getFormatDateByTimestamp("time")+ "|" + carrier);
        	context.write(key, new Text(uid));
			}
		};
	}

	public static class UserForCarrierReducer extends DBCounterReducer<Text, Text> {
		protected void reduce( Text key, Iterable<Text> texts, Context context) throws IOException, InterruptedException {
			int launchUserCount = StatUtils.statUniqueTextNumber(texts);

			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String carrier = arrKey[1];

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("carrier", carrier);
			ValueDBWritable value = new ValueDBWritable("launch_user_count", launchUserCount);
			context.write(resultKey, value);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogLaunch.SN, new String[]{"time","uid","car"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, UserForCarrierMapper.class, 
			Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_carrier_stat", new String[] { "date", "carrier" },
			new String[] { "launch_user_count" }, UserForCarrierReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
