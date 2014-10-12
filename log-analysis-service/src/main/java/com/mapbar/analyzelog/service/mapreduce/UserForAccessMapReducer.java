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
 * 联网方式活跃用户统计。
 * 
 * @author 邓飞鸽
 * 
 */
public class UserForAccessMapReducer extends AbstractMapReduceJob{

	public static class UserForAccessMapper extends TableMapper<Text, Text> {

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy proxy = ResultProxy.getResultProxy(value, LogLaunch.SN);

        	String uid = proxy.getValue("uid");
			String access = proxy.getValueNotNull("acs");
			if(access!="IGNORE"&&!access.equalsIgnoreCase("IGNORE")){
				Text key = new Text(proxy.getFormatDateByTimestamp("time") + "|" + access);
	        	context.write(key, new Text(uid));
			}
		};
	}

	public static class UserForAccessReducer extends DBCounterReducer<Text, Text> {
		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			int launchUserCount = StatUtils.statUniqueTextNumber(values);
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String access = arrKey[1].toUpperCase();

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("access", access);

			ValueDBWritable value = new ValueDBWritable("launch_user_count", launchUserCount);
			context.write(resultKey, value);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException  {
		Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogLaunch.SN, new String[] { "time", "uid", "acs" });
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan,
				UserForAccessMapper.class, Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_access_stat", 
				new String[] { 	"date", "access" }, new String[] { "launch_user_count" },
				UserForAccessReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
