package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapred.TableMapReduceUtil;
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
 * 按小时统计指定日的启动次数、新用户数、启动用户数。
 * 
 * @author 邓飞鸽
 */
public class UserCounterMapReducer extends AbstractMapReduceJob{

	public static class UserCounterMapper extends TableMapper<Text, Text>{
		private static final String hourFormat =  "yyyy-MM-dd HH:00:00";

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy proxy = ResultProxy.getResultProxy(value, LogLaunch.SN);
			context.write(
					new Text(proxy.getFormatDateByTimestamp("time", hourFormat)),
					new Text(proxy.getValue("uid")));
		};
	}

	public static class UserCounterReducer extends DBCounterReducer<Text, Text>{

		protected void reduce(Text key,Iterable<Text> values, Context context) throws IOException, InterruptedException {
			String datetime = key.toString();
			String date = datetime.split(" ")[0];
			
			List<String> userIds = new ArrayList<String>();
			Set<String> set = new HashSet<String>();
			int launchCount = 0;
			for (Text textUserID : values) {
				String userId = textUserID.toString();
				set.add(userId);
				launchCount++;
			}
			userIds.addAll(set);
			int newUserCount = StatUtils.statNewUserCountByHour(DateFormatUtils.parseDatetime(datetime), userIds, context.getConfiguration());
			int hour = DateFormatUtils.getHour(datetime);

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("section", hour);
			ValueDBWritable value = new ValueDBWritable("visit_size", launchCount);
			value.put("new_imei_size", newUserCount);
			context.write(resultKey, value);
		}
	}

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(getStartCalendarFor6Hour(), LogLaunch.SN, new String[]{"time", "uid"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, UserCounterMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_section_stat_separate", new String[] { "date", "section" },
				new String[] {"new_imei_size", "visit_size" }, UserCounterReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
