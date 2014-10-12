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
import com.mapbar.analyzelog.core.utils.DateFormatUtils;
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
public class UseTimeIntervalMapReducer extends AbstractMapReduceJob{

	private static final String OUTPUT_TABLE_NAME = "la_duration_city_stat";

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

	public static class UseTimeIntervalMapper extends TableMapper<Text, IntWritable>{

		private static final String hourFormat =  "yyyy-MM-dd HH:00:00";
		private static final IntWritable one = new IntWritable(1);

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy user = ResultProxy.getResultProxy(value, LogTerminate.SN);
            try {
            	int duration = user.getIntValue("duti", 0)/1000;
            	String city = user.getValueNotNull("city");
            	TimeInterval interval = TimeInterval.getTimeInterval(duration);
				context.write(new Text(user.getFormatDateByTimestamp("time", hourFormat) + "|" + interval+"|"+city), one);
            } catch (InterruptedException e) {
                throw new IOException(e);
            }
		};
	}

	public static class UseTimeIntervalReducer extends DBCounterReducer<Text, IntWritable>{

		protected void reduce(Text key,Iterable<IntWritable> values,Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			String[] keys = key.toString().split("\\|");
			String datetime=keys[0];
			String date = datetime.split(" ")[0];
			String timeInterval = keys[1];
			String city=keys[2];
			int section =DateFormatUtils.getHour(datetime);
			for(IntWritable val : values){
				sum += val.get();
			}
			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("segment", timeInterval);
			resultKey.put("section", section);
			resultKey.put("city",city);
			ValueDBWritable value = new ValueDBWritable("visit_count", sum);
			context.write(resultKey, value);
		}
	}

	public void run(Job job) {
		try {
			Scan scan = getTimeRangeScan(1, LogTerminate.SN, new String[]{"time", "duti","city"});
			TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.TERMINATE), scan, UseTimeIntervalMapper.class, 
					Text.class, IntWritable.class, job,getArguments().getAppID(),null,2);
			job.setNumReduceTasks(3);
			JDBCMapReduceUtil.initTableReducerJob(OUTPUT_TABLE_NAME, new String[] { "date", "segment","section","city" },
					new String[] { "visit_count" }, UseTimeIntervalReducer.class, job);
			
			job.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
