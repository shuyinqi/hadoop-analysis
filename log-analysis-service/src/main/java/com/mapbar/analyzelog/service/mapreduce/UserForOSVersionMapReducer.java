package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.core.utils.DateFormatUtils;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;
import com.mapbar.analyzelog.service.utils.StatUtils;

/**
 * 操作系统版本启动用户统计。
 */
public class UserForOSVersionMapReducer extends AbstractMapReduceJob{

	public static class UserForOSVersionMapper extends TableMapper<Text, Text>{
		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy launchResult = ResultProxy.getResultProxy(value, LogLaunch.SN);
			String uid = launchResult.getValue("uid");
			String OSVersion = launchResult.getValueNotNull("osv");

			context.write(new Text(launchResult.getFormatDateByTimestamp("time")+ "|" + OSVersion), new Text(uid));
		};
	}

	public static class UserForOSVersionReducer extends DBCounterReducer<Text, Text> {
		protected void reduce( Text key, Iterable<Text> users, Context context) throws IOException, InterruptedException {
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String osVersion = arrKey[1];
			List<String> launchUsers = StatUtils.getUniqueTextList(users);
			int launchUserCount = launchUsers.size();
			int newUserCount = StatUtils.statNewUserCountByDay(DateFormatUtils.parseDate(date), launchUsers, context.getConfiguration());

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("os_version", osVersion);
			ValueDBWritable value = new ValueDBWritable("launch_user_count", launchUserCount);
			value.put("new_user_count", newUserCount);
			context.write(resultKey, value);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(getStartCalendarForDay(),LogLaunch.SN, new String[]{"time","uid","osv"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, UserForOSVersionMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_osversion_stat_separate", new String[] { "date", "os_version" },
				new String[] { "launch_user_count","new_user_count" }, UserForOSVersionReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
