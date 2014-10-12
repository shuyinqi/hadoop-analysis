package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;

public class UpgradeUserMapReducer extends AbstractMapReduceJob{

	public static class UpgradeUserMapper extends TableMapper<Text, Text>{
		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
        	ResultProxy proxy = ResultProxy.getResultProxy(value, LogType.UVERSION.shortName());
 
        	String uid = proxy.getValue("uid");
			String version = proxy.getValueNotNull("vs");
        	Text key = new Text(proxy.getFormatDateByTimestamp("time") + "|" + version);

        	context.write(key, new Text(uid));
		};
	}

	public static class UpgradeUserReducer extends DBCounterReducer<Text, Text> {
		protected void reduce( Text key, Iterable<Text> texts, Context context) throws IOException, InterruptedException {
			int upgrateCount = 0;
			for(Text text : texts){
				if (text != null)
					upgrateCount++;
			}
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String version = arrKey[1];

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("version", version);
			ValueDBWritable value = new ValueDBWritable("upgrade_user_count", upgrateCount);
			context.write(resultKey, value);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(1, LogType.UVERSION.shortName(), new String[]{"time","uid","vs"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.UVERSION), scan, UpgradeUserMapper.class, 
			Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_version_stat_separate", new String[] { "date", "version" },
			new String[] { "upgrade_user_count" }, UpgradeUserReducer.class, job);
		
		job.waitForCompletion(true);
	}
}