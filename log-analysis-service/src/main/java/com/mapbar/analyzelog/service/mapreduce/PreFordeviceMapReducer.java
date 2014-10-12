package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import com.mapbar.analyzelog.core.SystemConstants;
import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.Argument;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;
import com.mapbar.analyzelog.service.utils.DateUtil;
/**
 * 统计留存用户
 * @description:  
 * @author:  sunjy  2012-6-5 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class PreFordeviceMapReducer extends AbstractMapReduceJob {
	
	private static final String OUTPUT_TABLE_NAME = "pre_device_count";
	
	public static class PreFordeviceMapper extends TableMapper<Text, Text>{
		protected void map(ImmutableBytesWritable row, Result value, Context context) 
				throws IOException, InterruptedException {
			ResultProxy launchResult = ResultProxy.getResultProxy(value,Equipment.SN);
			String chtp = launchResult.getValue(Equipment.SN, "chtp");
			String devm = launchResult.getValue(Equipment.SN, "devm");
			if(chtp.equals("pre_installed")||chtp=="pre_installed"){
				if(devm.equalsIgnoreCase("T703")||devm=="T703"){
					Text key = new Text(launchResult.getFormatDateByTimestamp("time"));
		        	context.write(key, new Text(devm));
			}
		};
	}
	}
	
	public static class PreFordeviceReducer extends  DBCounterReducer<Text, Text>{
		protected void reduce(Text key,Iterable<Text> values, Context context) 
				throws IOException, InterruptedException{
			 List<String> userKeys = new ArrayList<String>();
				for(Text val : values){
					String uid = val.toString();
					userKeys.add(uid);
				}
				KeyDBWritable resultKey = new KeyDBWritable();
				resultKey.put("date", key.toString());
				resultKey.put("device_name", userKeys.size()==0?null:userKeys.get(0));
				ValueDBWritable value = new ValueDBWritable("new_imei_count", userKeys.size());
				context.write(resultKey, value);
		}
	}
	@Override
	public void run(Job job) throws IOException, InterruptedException,ClassNotFoundException {
    	Scan scan = new Scan();
    	scan.setCaching(10);
		scan.setBatch(5000);
		scan.addColumn(Bytes.toBytes(Equipment.SN), Bytes.toBytes("chtp"));
		scan.addColumn(Bytes.toBytes(Equipment.SN), Bytes.toBytes("devm"));
		scan.addColumn(Bytes.toBytes(Equipment.SN), Bytes.toBytes("time"));
    	
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.USER), scan, PreFordeviceMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,20);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob(OUTPUT_TABLE_NAME, new String[] { "date", "device_name"},
				new String[] {"new_imei_count"}, PreFordeviceReducer.class, job);
		job.waitForCompletion(true);
	}

}

