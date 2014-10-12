package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;

/**
 * 用户启动次数统计。
 * 
 * @author 邓飞鸽
 */
public class LaunchCounterIntervalMapReducer extends AbstractMapReduceJob{

	protected enum CountInterval {
		T1, T2, T3, T4, T5,  T6T9, T10T19, T20T49, T50A;

		public static CountInterval getCountInterval(long count) {
			CountInterval countInterval;
			if (count < 2) {
				countInterval = CountInterval.T1;
			} else if (count < 3) {
				countInterval = CountInterval.T2;
			}else if(count < 4){
				countInterval = CountInterval.T3;
			} else if(count < 5){
				countInterval = CountInterval.T4;
			}else if(count < 6){
					countInterval = CountInterval.T5;
			} else if (count < 10) {
				countInterval = CountInterval.T6T9;
			} else if (count < 20) {
				countInterval = CountInterval.T10T19;
			} else if (count < 50) {
				countInterval = CountInterval.T20T49;
			} else {
				countInterval = CountInterval.T50A;
			}
			return countInterval;
		}

		public String toString() {
			return super.toString().replaceFirst("T", "").replace("T", "-")
					.replace("A", "+");
		}
	}

	public static class LaunchCounterIntervalMapper extends TableMapper<Text, Text>{

		protected void map(ImmutableBytesWritable row, Result value, Context context) 
				throws IOException, InterruptedException {
			ResultProxy proxy = ResultProxy.getResultProxy(value, LogLaunch.SN);

        	String uid = proxy.getValue("uid");
        	if(StringUtils.isEmpty(uid)){
        		return;
        	}
        	Text keyOut = new Text(proxy.getFormatDateByTimestamp("time") + "|" + uid);
			context.write(keyOut, new Text("1"));
		};
	}

	public static class LaunchCounterIntervalCombiner extends Reducer<Text, Text, Text, Text>{
		@SuppressWarnings("unused")
		protected void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			int launchCount = 0;
			for (Text uid : values) {
				launchCount++;
			}
			String date = key.toString().split("\\|")[0];
			Text reduceKey = new Text(date);
			context.write(reduceKey, new Text(CountInterval.getCountInterval(launchCount) + "|" + 1));
		}
	}

	public static class LaunchCounterIntervalReducer extends DBCounterReducer<Text, Text>{
		protected void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			Map<String, Integer> countIntervals = new HashMap<String, Integer>();
			String date = key.toString();
			for (Text value : values) {
				String[] arrCountInterval = value.toString().split("\\|");
				String interval = arrCountInterval[0];
				String strCount = arrCountInterval[1];
				int count = Integer.parseInt(strCount);
				if (countIntervals.containsKey(interval)) {
					count = countIntervals.get(interval) + count;
				}
				countIntervals.put(interval, count);
			}
			Iterator<Entry<String, Integer>> entries = countIntervals.entrySet().iterator();
			while (entries.hasNext()){
				Entry<String, Integer> entry = entries.next();
				KeyDBWritable resultKey = new KeyDBWritable();
				resultKey.put("date", date);
				resultKey.put("frequency", entry.getKey());
				System.out.println(entry.getValue());
				ValueDBWritable value = new ValueDBWritable("launch_user_count", entry.getValue());
				context.write(resultKey, value);
			}
		}
	}

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogLaunch.SN, new String[]{"time", "uid"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, 
				LaunchCounterIntervalMapper.class, Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setCombinerClass(LaunchCounterIntervalCombiner.class);

		job.setNumReduceTasks(1);
		JDBCMapReduceUtil.initTableReducerJob("la_launch_stat", new String[] {
				"date", "frequency" }, new String[] { "launch_user_count",
				}, LaunchCounterIntervalReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
