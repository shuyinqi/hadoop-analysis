package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

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
 * 按天统计用户启动单次使用时长。
 * 
 * @author 邓飞鸽
 */
public class UseTimeForCVIntervalMapReducer extends AbstractMapReduceJob{

	private static final String OUTPUT_TABLE_NAME = "la_duration_time_stat_separate";

	protected enum TimeInterval {
		T0T3, T4T9, T10T29, T30T59, T60T179, T180T599, T600T1799, T1800A;

		public static TimeInterval getTimeInterval(long time) {
			TimeInterval interval;
			if (time < 4) {
				interval = TimeInterval.T0T3;
			} else if (time < 10) {
				interval = TimeInterval.T4T9;
			} else if (time < 30) {
				interval = TimeInterval.T10T29;
			} else if (time < 61) {
				interval = TimeInterval.T30T59;
			} else if (time < 180) {
				interval = TimeInterval.T60T179;
			} else if (time < 600) {
				interval = TimeInterval.T180T599;
			} else if (time < 1800) {
				interval = TimeInterval.T600T1799;
			} else {
				interval = TimeInterval.T1800A;
			}
			return interval;
		}

		public String toString() {
			return super.toString().replaceFirst("T", "").replaceAll("T", "-")
					.replace("A", "+");
		}
	}

	public static class UseTimeForCVIntervalMapper extends TableMapper<Text, IntWritable>{

		private static SimpleDateFormat dayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		private static final IntWritable one = new IntWritable(1);

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy user = ResultProxy.getResultProxy(value, LogTerminate.SN);
            try {
            	long time = user.getLongValue("time");
            	String appv = user.getValue("appv");
            	String chne = user.getValue("chne");
            	String chtp = user.getValue("chtp");
            	int duration = user.getIntValue("duti", 0)/1000;
            	TimeInterval interval = TimeInterval.getTimeInterval(duration);
				context.write(new Text(dayDateFormat.format(new Date(time)) + "|" + interval+"|"+appv+"|"+chne+"|"+chtp+"|"), one);
            } catch (InterruptedException e) {
                throw new IOException(e);
            }
		};
	}

	public static class UseTimeForCVIntervalReducer extends DBCounterReducer<Text, IntWritable>{

		protected void reduce(Text key,Iterable<IntWritable> values,Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			String[] keys = key.toString().split("\\|");
			String date = keys[0];
			String timeInterval = keys[1];
			String timeappv = keys[2];
			String timechne = keys[3];
			String timechtp = keys[4];
			
			for(IntWritable val : values){
				sum += val.get();
			}
			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("segment", timeInterval);
			resultKey.put("channel_type", timechtp);
			resultKey.put("channel_name",timechne);
			resultKey.put("version", timeappv);
			ValueDBWritable value = new ValueDBWritable("visit_count", sum);
			context.write(resultKey, value);
		}
	}

	public void run(Job job) {
		try {
			Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogTerminate.SN, new String[]{"time", "appv","chne","chtp","uid","duti"});
			TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.TERMINATE), scan, UseTimeForCVIntervalMapper.class, 
					Text.class, IntWritable.class, job,getArguments().getAppID(),null,3);
			job.setNumReduceTasks(3);
			JDBCMapReduceUtil.initTableReducerJob(OUTPUT_TABLE_NAME, new String[] {"date", "segment","channel_type","channel_name","version" },
					new String[] { "visit_count" }, UseTimeForCVIntervalReducer.class, job);
			
			job.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
