package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
 * 版本新用户、启动用户、升级用户统计等。
 * 
 * @author 邓飞鸽
 */
public class UserForVersionMapReducer extends AbstractMapReduceJob{

	public static class UserForVersionMapper extends TableMapper<Text, Text>{

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
	        ResultProxy launchResult = ResultProxy.getResultProxy(value, LogLaunch.SN);
	        String uid = launchResult.getValue("uid");
	        String version = launchResult.getStringValue("appv", null);
	        if(StringUtils.isNotEmpty(version)){
	        	version = version.replaceAll("\\.", "").replaceFirst("V", "");
	        	context.write(new Text(launchResult.getFormatDateByTimestamp("time") + "|" + version), new Text(uid));
	        }
		};
	}

	public static class UserForVersionReducer extends DBCounterReducer<Text, Text> {
		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String version = arrKey[1];

			List<String> users = StatUtils.getUniqueTextList(values);
			int launchUserCount = users.size();
			int newUserCount = StatUtils.statNewUserCountByDay(DateFormatUtils.parseDate(date), users, context.getConfiguration());

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("version", version);
			ValueDBWritable value = new ValueDBWritable();
			value.put("launch_user_count", launchUserCount);
			value.put("new_user_count", newUserCount);
			context.write(resultKey, value);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(1, LogLaunch.SN, new String[]{"time", "uid", "appv"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, UserForVersionMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(1);
		JDBCMapReduceUtil.initTableReducerJob("la_version_stat_separate", new String[] { "date", "version" },
				new String[] { "launch_user_count", "new_user_count" }, UserForVersionReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
