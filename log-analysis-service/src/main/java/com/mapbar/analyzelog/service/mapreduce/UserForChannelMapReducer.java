package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.List;

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

import com.mapbar.analyzelog.core.SystemConstants;
import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.core.utils.DateFormatUtils;
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.Argument;
import com.mapbar.analyzelog.service.exception.DataStatException;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;
import com.mapbar.analyzelog.service.utils.StatUtils;

/**
 * 渠道启动用户统计。
 * 
 * @author 邓飞鸽
 */
public class UserForChannelMapReducer extends AbstractMapReduceJob{

	public static class UserForChannelMapper extends TableMapper<Text, Text>{
		protected HTable hTable; 

		protected void setup(Context context) throws IOException, InterruptedException {
			String appID = context.getConfiguration().get(Argument.APP_ID_NAME);
			if (StringUtils.isEmpty(appID)){
				throw new DataStatException("Not found appid in conf!");
			}
			hTable = new HTable(context.getConfiguration(), SystemConstants.getTableName(appID));
		};

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			ResultProxy launchResult = ResultProxy.getResultProxy(value, LogLaunch.SN);
        	String uid = launchResult.getValue("uid");
        	Get get = new Get(Bytes.toBytes(uid));
			Result result = hTable.get(get);
			if (result.isEmpty()) {
				return;
			}
			ResultProxy userResultProxy = ResultProxy.getResultProxy(result, Equipment.SN);
			String channelType = userResultProxy.getValueNotNull("chtp");
			String channelName = userResultProxy.getValueNotNull("chne");
        	Text key = new Text(launchResult.getFormatDateByTimestamp("time") + "|" + channelType + "|" + channelName);
        	context.write(key, new Text(uid));
		};

		protected void cleanup(Context context) throws IOException ,InterruptedException {
			if (hTable != null){
				hTable.close();
			}
		};
	}

	public static class UserForChannelReducer extends DBCounterReducer<Text, Text> {
		protected void reduce( Text key, Iterable<Text> users, Context context) throws IOException, InterruptedException {
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String channelType = arrKey[1];
			String channelName = arrKey[2];

			List<String> userIDs = StatUtils.getUniqueTextList(users);
			int launchUserCount = userIDs.size();
			int newUserCount = StatUtils.statNewUserCountByDay(DateFormatUtils.parseDate(date), userIDs, context.getConfiguration());

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("type", channelType);
			resultKey.put("name", channelName);
			ValueDBWritable resultValue = new ValueDBWritable();
			resultValue.put("launch_user_count", launchUserCount);
			resultValue.put("new_user_count", newUserCount);
			context.write(resultKey, resultValue);
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(getStartCalendarForDay(), LogLaunch.SN, new String[]{"time", "uid"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, UserForChannelMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_channel_stat_separate", new String[] { "date", "type", "name" },
				new String[] { "launch_user_count", "new_user_count" }, UserForChannelReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
