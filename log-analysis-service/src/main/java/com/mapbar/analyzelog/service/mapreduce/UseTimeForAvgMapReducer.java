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
 * 按天统计每天平均使用时长。
 * 
 * @author 邓飞鸽
 */
public class UseTimeForAvgMapReducer extends AbstractMapReduceJob{

	private static final String OUTPUT_TABLE_NAME = "la_basic_stat";

	public static class UseTimeForAvgMapper extends TableMapper<Text, IntWritable>{

        private static SimpleDateFormat dayDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy user = ResultProxy.getResultProxy(value);
            try {
            	long time = user.getLongValue(LogTerminate.SN, "time");
            	int duration = user.getIntValue(LogTerminate.SN, "duti", 0);
            	context.write(new Text(dayDateFormat.format(new Date(time))), new IntWritable(duration));
            } catch (InterruptedException e) {
                throw new IOException(e);
            }
		};
	}

	public static class UseTimeForAvgReducer extends DBCounterReducer<Text, IntWritable> {
		protected void reduce( Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			long sum = 0;
			int count = 0;
			for (IntWritable val : values) {
				sum += val.get();
				count++;
			}
			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", key.toString());
			ValueDBWritable value = new ValueDBWritable("average_duration", sum/1000/count);
			context.write(resultKey, value);
		}
	};

	public void run(Job job) {
		try {
			Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogTerminate.SN, new String[]{"time", "duti"});
			TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.TERMINATE), scan, UseTimeForAvgMapper.class, 
					Text.class, IntWritable.class, job,getArguments().getAppID(),null,2);
			job.setNumReduceTasks(3);
			JDBCMapReduceUtil.initTableReducerJob(OUTPUT_TABLE_NAME, new String[] { "date" },
					new String[] { "average_duration" }, UseTimeForAvgReducer.class, job);
			
			job.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
