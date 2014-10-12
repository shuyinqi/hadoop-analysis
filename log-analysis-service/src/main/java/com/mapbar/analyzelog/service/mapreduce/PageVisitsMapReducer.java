package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Reporter;
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
import com.mapbar.analyzelog.service.utils.TextPair;

public class PageVisitsMapReducer extends AbstractMapReduceJob {
	
	
	public static class PageVisitsMapper extends TableMapper<TextPair, Text>{
		protected void map(ImmutableBytesWritable row, Result value, Context context) 
				throws IOException, InterruptedException {
			ResultProxy proxy = ResultProxy.getResultProxy(value, LogTerminate.SN);
			String activityList = proxy.getValue(LogTerminate.SN, "actis", null);
        	String appVersion = proxy.getValueNotNull("appv");
        	String time = proxy.getFormatDateByTimestamp("time");
         	if (StringUtils.isEmpty(activityList)){
        		return;
        	}
        	String[] activities = activityList.split("\\|");
        	for(int i=0;i<activities.length;i++){
        	if(activities.length==1){
        		continue;
        	}
        	if(i==activities.length-1){
        			break;
        	}
        	 String preName=activities[i].split(",")[0];
        	 String nextName=activities[i+1].split(",")[0];
        	 /**
        	  * 注意顺序
        	  */
             TextPair tp = new TextPair(preName,nextName,appVersion,time);
        	 String resTime = activities[i].split(",")[0]; //停留时间
             context.write(tp,new Text(resTime));
        	}
		};
	}
	
	public static class PageVisitsReducer extends  DBCounterReducer<TextPair, Text>{
		@SuppressWarnings("unused")
		protected void reduce(TextPair key,Iterable<Text> values, Context context) throws IOException, InterruptedException{
			int count = 0;
			for(Text value :values){
				count++;
			}
			Text preName = key.getFirst();
		    Text nextName = key.getSecond();
		    Text appVersion = key.getThree();
		    Text time = key.getFour();
		    KeyDBWritable resultKey = new KeyDBWritable();
		    resultKey.put("date", time.toString());
			resultKey.put("version", appVersion.toString());
			resultKey.put("pre_name", preName.toString());
			resultKey.put("next_name", nextName.toString());
			ValueDBWritable value = new ValueDBWritable("visits", count);
			context.write(resultKey, value);
		}
		
	}
	
	@Override
	public void run(Job job) throws IOException, InterruptedException,ClassNotFoundException{
		Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogTerminate.SN, new String[]{"time", "actis", "appv", "uid"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.TERMINATE), scan, PageVisitsMapper.class, 
				TextPair.class, Text.class, job ,getArguments().getAppID(),null,3);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_page_visit_canvas", new String[] { "date", "pre_name", "next_name","version"},
				new String[] { "visits"}, PageVisitsReducer.class, job);
		
		job.waitForCompletion(true);
		
	}

}
