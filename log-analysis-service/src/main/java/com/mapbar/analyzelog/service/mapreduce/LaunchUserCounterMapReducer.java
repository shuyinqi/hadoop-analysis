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
 * 按小时统计指定日的启动次数、新用户数、启动用户数。
 * 
 * @author 邓飞鸽
 */
public class LaunchUserCounterMapReducer extends AbstractMapReduceJob{

	public static class LaunchUserCounterMapper extends TableMapper<Text, Text>{

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy proxy = ResultProxy.getResultProxy(value, LogLaunch.SN);
			context.write(
					new Text(proxy.getFormatDateByTimestamp("time")),
					new Text(proxy.getValue("uid")));
		};
	}

	public static class LaunchUserCounterReducer extends DBCounterReducer<Text, Text>{

		protected void reduce(Text key,Iterable<Text> values, Context context) throws IOException, InterruptedException {
			int launchUserCount = StatUtils.statUniqueTextNumber(values);

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", key.toString());
			resultKey.put("section", 10);
			ValueDBWritable value = new ValueDBWritable();
			value.put("visit_imei_size", launchUserCount);
			context.write(resultKey, value);
		}
	}

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogLaunch.SN, new String[]{"time", "uid"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, LaunchUserCounterMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_section_stat_separate", new String[] { "date", "section" },
				new String[] {"visit_imei_size" }, LaunchUserCounterReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
