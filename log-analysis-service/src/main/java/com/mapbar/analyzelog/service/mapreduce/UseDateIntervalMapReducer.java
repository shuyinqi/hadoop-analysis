package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.LogTerminate;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.Argument;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBConfiguration;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;
import com.mapbar.analyzelog.service.utils.DateUtil;

/**
 * 按天统计用户启动使用时长。
 * 
 * @author 邓飞鸽
 */
public class UseDateIntervalMapReducer extends AbstractMapReduceJob{

	private static final String START_DAY = "startday";
	private static final String DAY_NUMBER = "DAY_NUMBER";
	protected enum TimeInterval {
		T0T3, T4T9, T10T29, T30T59, T60T179, T180T599, T600T1799, T1800A,
		T0T30,T31T60,T61T180,T181T600,T601T1800,T1801T3600,T3601T5400,T5401T7200,T7200A;
		
		public static TimeInterval getTimeInterval(int time,int day) {
			TimeInterval interval;
			switch(day){
			case 1:
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
				 break;
		      default:
		    	  if (time < 31) {
						interval = TimeInterval.T0T30;
					} else if (time < 61) {
						interval = TimeInterval.T31T60;
					} else if (time < 181) {
						interval = TimeInterval.T61T180;
					} else if (time < 601) {
						interval = TimeInterval.T181T600;
					} else if (time < 1801) {
						interval = TimeInterval.T601T1800;
					} else if (time < 3601) {
						interval = TimeInterval.T1801T3600;
					} else if (time < 5401) {
						interval = TimeInterval.T3601T5400;
					}else if(time <7201){
						interval = TimeInterval.T5401T7200;
					} 
					else {
						interval = TimeInterval.T7200A;
					}
		      break;
				
			}
			return interval;
		}

		public String toString() {
			return super.toString().replaceFirst("T", "").replaceAll("T", "-")
					.replace("A", "+");
		}
	}

	public static class UseDateIntervalMapper extends TableMapper<Text, IntWritable>{

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy user = ResultProxy.getResultProxy(value, LogTerminate.SN);
            try {
            	String appv = user.getValue("appv");
            	String chne = user.getValue("chne");
            	String chtp = user.getValue("chtp");
            	String uid = user.getValue("uid");
            	int duration = user.getIntValue("duti", 0)/1000;
				context.write(new Text(appv+"|"+chne+"|"+chtp+"|"+uid), new IntWritable(duration));
            } catch (InterruptedException e) {
                throw new IOException(e);
            }
		};
	}
	
	public static class UseDateIntervalReducer extends Reducer<Text, IntWritable, Text, Text>{
		protected void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			 int interval =0; 
			for(IntWritable value :values){
				 interval+=value.get();
			 }
			String[] arrKey = key.toString().split("\\|");
			String appv = arrKey[0];
			String chne = arrKey[1];
			String chtp = arrKey[2];
			String uid = arrKey[3];
			
			TimeInterval interval1 = TimeInterval.getTimeInterval(interval,context.getConfiguration().getInt(DAY_NUMBER,1));
			context.write(new Text(appv+"|"+chne+"|"+chtp+"|"+interval1+"|"),new Text("1"));
		}
	}
	
	public static class UseDateIntervalSecondMapper extends Mapper<LongWritable, Text,Text,Text>{

		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			
			String[] arrKey = value.toString().split("\\|");
			String appv = arrKey[0];
			String chne = arrKey[1];
			String chtp = arrKey[2];
			String interval = arrKey[3];
			context.write(new Text(appv+"|"+chne+"|"+chtp+"|"+interval+"|"), new Text("1"));
		};
	}
	

	public static class UseDateIntervalSecondReducer extends DBCounterReducer<Text, Text>{

		protected void reduce(Text key,Iterable<Text> values,Context context)
				throws IOException, InterruptedException {
			String[] arrKey = key.toString().split("\\|");
			String appv = arrKey[0];
			String chne = arrKey[1];
			String chtp = arrKey[2];
			String interval = arrKey[3];
			int sum = 0;
			for(Text val : values){
				sum ++;
			}
			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", context.getConfiguration().get(START_DAY));
			resultKey.put("segment", interval);
			resultKey.put("channel_type",chtp);
			resultKey.put("channel_name",  chne);
			resultKey.put("version", appv);
			ValueDBWritable value = new ValueDBWritable("visit_count", sum);
			context.write(resultKey, value);
		}
	}

	public void run(Job job) {
		try {
			if(getArguments().getDayValue()!=null){
				job.getConfiguration().set(START_DAY,getArguments().getDayValue());
				job.getConfiguration().setInt(DAY_NUMBER, getArguments().getIntValue("-d", 1));
			}else{
				System.out.println("request -t to set start time");
				System.exit(1);
			}
			Scan scan = getTimeRangeScan(getArguments().getIntValue("-d", 1), LogTerminate.SN, new String[]{"appv","chne","chtp","uid","duti"});
			TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.TERMINATE), scan, UseDateIntervalMapper.class, 
					Text.class, IntWritable.class, job,getArguments().getAppID(),getArguments().getIntValue("-d", 1)+"",30);
			job.setNumReduceTasks(3);
			job.setReducerClass(UseDateIntervalReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			String path = "/home/jishu/"+job.getJobName()+"/job_"+System.currentTimeMillis();
			FileOutputFormat.setOutputPath(job, new Path(path));
			job.waitForCompletion(true);

				
			Configuration conf = HBaseConfiguration.create();
			conf.set(Argument.APP_ID_NAME, getArguments().getAppID());
			Job job2 = new Job(conf, job.getJobName()+"second");
			job2.setJarByClass(this.getClass());
			job2.getConfiguration().set(DBConfiguration.RUN_APP_ID, getArguments().getAppID());
			job2.getConfiguration().set(START_DAY,getArguments().getDayValue());
			job2.getConfiguration().setInt(DAY_NUMBER, getArguments().getIntValue("-d", 1));
			job2.setMapperClass(UseDateIntervalSecondMapper.class);
			job2.setMapOutputKeyClass(Text.class);
		    job2.setMapOutputValueClass(Text.class);
		    FileInputFormat.addInputPaths(job2, path);
		    final String OUTPUT_TABLE_NAME =getArguments().getIntValue("-d", 1)==1?"la_duration_day_stat_separate":"la_duration_week_stat_separate";
			JDBCMapReduceUtil.initTableReducerJob(OUTPUT_TABLE_NAME, new String[] { "date", "segment","channel_type","channel_name","version" },
					new String[] { "visit_count" }, UseDateIntervalSecondReducer.class, job2);
			job2.setNumReduceTasks(3);
			job2.waitForCompletion(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
