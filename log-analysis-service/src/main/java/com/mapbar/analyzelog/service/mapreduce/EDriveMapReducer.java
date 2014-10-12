package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.LogEvent;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.mapreduce.EventBuyMapReducer.EventBuyReducer;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;
import com.mapbar.analyzelog.service.utils.DateUtil;

public class EDriveMapReducer extends AbstractMapReduceJob{
	
	private final static String SDATE ="CURENTDATE";
	
	public static class EDriveMapper extends TableMapper<Text, IntWritable>{
		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
	    	ResultProxy proxy = ResultProxy.getResultProxy(value, LogEvent.SN);
	    	String eventID = proxy.getValueNotNull("eid");
	    	String label = proxy.getValueNotNull( "lab");
	    	String clnt = proxy.getValueNotNull("clnt");
	    	if(eventID.equalsIgnoreCase("eDaiJia")||eventID.equalsIgnoreCase("edrive_event")){
	    		context.write(new Text(label+"|"+clnt), new IntWritable(1));
	    	}
		};
	}

	public static class EDriveReducer extends DBCounterReducer<Text, IntWritable> {
		protected void reduce( Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			String date = context.getConfiguration().get(SDATE);
			String[] arrKey = key.toString().split("\\|");
			String label = arrKey[0];
			String clnt = arrKey[1];
			int sumEventAcc = 0;
			for(IntWritable acc: values){
				sumEventAcc += acc.get();
			}
			
			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("clnt", clnt);
			resultKey.put("label", label);
			ValueDBWritable value = new ValueDBWritable("count", sumEventAcc);
			context.write(resultKey, value);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		if(this.getArguments().getDayValue()==null){
			System.exit(1);
		}
		job.getConfiguration().set(SDATE,this.getArguments().getDayValue());
		Scan scan = getTimeRangeScan(1, LogEvent.SN, new String[]{"eid", "lab","clnt"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.EVENT), scan, EDriveMapper.class, 
				Text.class, IntWritable.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_event_edrive_stat", new String[] { "date", "clnt","label" },
				new String[] { "count" }, EDriveReducer.class, job);
		job.waitForCompletion(true);
	}
}
