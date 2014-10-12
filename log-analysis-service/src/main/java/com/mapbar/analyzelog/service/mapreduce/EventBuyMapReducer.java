package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.mapbar.analyzelog.core.SystemConstants;
import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogEvent;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.Argument;
import com.mapbar.analyzelog.service.exception.DataStatException;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;

/**
 * 购买时间统计
 * 
 */
public class EventBuyMapReducer extends AbstractMapReduceJob{

	public static class EventBuyMapper extends TableMapper<Text, Text>{
		protected HTable hTable; 

		protected void setup(Context context) throws IOException, InterruptedException {
			String appID = context.getConfiguration().get(Argument.APP_ID_NAME);
			if (StringUtils.isEmpty(appID)){
				throw new DataStatException("Not found appid in conf!");
			}
			hTable = new HTable(context.getConfiguration(), SystemConstants.getTableName(appID));
		};
		
		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
	    	ResultProxy proxy = ResultProxy.getResultProxy(value, LogEvent.SN);
	    	String eventID = proxy.getValueNotNull("eid");
	    	String lable = proxy.getValueNotNull( "lab");
	    	String uid = proxy.getValueNotNull( "uid");
	    	if("datamanage_event".equalsIgnoreCase(eventID)){
	    		if(lable.indexOf("购买专业版本数据")!=-1){
	    			Get get = new Get(Bytes.toBytes(uid));
	    			Result result = hTable.get(get);
	    			if (result.isEmpty()) {
	    				return;
	    			}
	    			ResultProxy userResultProxy = ResultProxy.getResultProxy(result, Equipment.SN);
	    			userResultProxy.getValue("appv");
	    			context.write(new Text(uid+"|"+userResultProxy.getFormatDateByTimestamp("time")), new Text(lable.substring(0,3)));
	    		}
	    	}
		};
		
		protected void cleanup(Context context) throws IOException ,InterruptedException {
			if (hTable != null){
				hTable.close();
			}
		};
	}

	public static class EventBuyReducer extends DBCounterReducer<Text, Text> {
		
		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			HashSet<String> set = new HashSet<String>();   
			for(Text val : values){
				String label = val.toString();
				set.add(label);
			}
			StringBuffer label=new StringBuffer("");
			for(String st:set){
				label.append(st).append(";");
			}
			String date = context.getConfiguration().get("DAY");
			String uid = key.toString().split("\\|")[0];
			String newdate = key.toString().split("\\|")[1];
			String labels = label.toString();

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("newdate", newdate);
			resultKey.put("uid", uid);
			resultKey.put("label", labels);
			ValueDBWritable value = new ValueDBWritable("count", 1);
			context.write(resultKey, value);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		
		job.getConfiguration().setStrings("DAY", getArguments().getDayValue());
		Scan scan = getTimeRangeScan(1, LogEvent.SN, new String[]{"eid", "lab", "uid"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.EVENT), scan, EventBuyMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_event_buy_date_stat", new String[] { "date","newdate", "uid","label" },
				new String[] { "count" }, EventBuyReducer.class, job);
		job.waitForCompletion(true);
	}
}
