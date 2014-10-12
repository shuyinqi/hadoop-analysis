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
 * 根据地理区域(城市)统计新用户及启动用户数。
 * 
 * @author 邓飞鸽
 */
public class UserForAreaMapReducer extends AbstractMapReduceJob{

	public static class UserForAreaMapper extends TableMapper<Text, Text>{

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy proxy = ResultProxy.getResultProxy(value, LogLaunch.SN);
        	String uid = proxy.getValue("uid");
			String cityName = proxy.getValueNotNull("city");
			if(cityName!="IGNORE"&&!cityName.equalsIgnoreCase("IGNORE")){
        	Text key = new Text(proxy.getFormatDateByTimestamp("time") + "|" + cityName);
        	context.write(key, new Text(uid));
			}
		};
	}

	public static class UserForAreaReducer extends DBCounterReducer<Text, Text> {
		
		protected void reduce(Text key, Iterable<Text> values, Context context) 
				throws IOException, InterruptedException {
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String cityName = arrKey[1];

			List<String> users = StatUtils.getUniqueTextList(values);
			int newUserCount = StatUtils.statNewUserCountByDay(DateFormatUtils.parseDate(date), users, context.getConfiguration()); //新用户数
			int launchUserCount = users.size();	//启动用户数

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("city", cityName);

			ValueDBWritable valueDbWritable = new ValueDBWritable();
			valueDbWritable.put("visit_user_counter", launchUserCount);
			valueDbWritable.put("new_user_counter", newUserCount);
			context.write(resultKey, valueDbWritable);
		}
	};

	public void run(Job job) {
		try {
			Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogLaunch.SN, new String[]{"time", "uid", "city"});
			TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, UserForAreaMapper.class, 
					Text.class, Text.class, job,getArguments().getAppID(),null,2);
			String outputTable = "la_location_stat";
			job.setNumReduceTasks(3);
			JDBCMapReduceUtil.initTableReducerJob(outputTable, new String[] { "date", "city" }, 
					new String[] { "visit_user_counter", "new_user_counter" }, UserForAreaReducer.class, job);
			
			job.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
