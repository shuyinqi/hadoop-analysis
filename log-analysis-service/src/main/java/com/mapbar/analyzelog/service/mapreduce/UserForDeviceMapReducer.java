package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.mapbar.analyzelog.service.utils.DateUtil;
import com.mapbar.analyzelog.service.utils.StatUtils;

/**
 * 设备启动用户统计。
 */
public class UserForDeviceMapReducer extends AbstractMapReduceJob{

	public static class UserForDeviceMapper extends TableMapper<Text, Text>{
		final Log LOG = LogFactory.getLog(UserForDeviceMapReducer.class);
		protected HTable userTable; 

		protected void setup(Context context) throws IOException, InterruptedException {
			String appID = context.getConfiguration().get(Argument.APP_ID_NAME);
			if (StringUtils.isEmpty(appID)){
				throw new DataStatException("Not found appid in conf!");
			}
			userTable = new HTable(context.getConfiguration(), SystemConstants.getTableName(appID));
		};

		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
        	ResultProxy launchResult = ResultProxy.getResultProxy(value,  LogLaunch.SN);
        	String uid = launchResult.getValue("uid");
        	if(StringUtils.isEmpty(uid)){
        		return;
        	}
        	Get get = new Get(Bytes.toBytes(uid));
        	Result result = userTable.get(get);
        	if (result.isEmpty()){
        		return;
        	}
        	ResultProxy userResult = ResultProxy.getResultProxy(result, Equipment.SN);
			String brand = userResult.getValueNotNull("brand").toLowerCase().trim();
			String deviceModel = userResult.getValueNotNull("devm").toLowerCase().trim();
        	Text key = new Text(launchResult.getFormatDateByTimestamp("time") + "|" + brand + "|" + deviceModel);
        	context.write(key, new Text(uid));
		};
		
		protected void cleanup(Context context) throws IOException ,InterruptedException {
			if (userTable != null){
				userTable.close();
			}
		};
	}

	public static class UserForDeviceReducer extends DBCounterReducer<Text, Text> {
		final Log LOG = LogFactory.getLog(UserForDeviceMapReducer.class);
		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String device = arrKey[2];
			String brand = arrKey[1];
			
			List<String> users = StatUtils.getUniqueTextList(values);
			int launchUserCount = users.size();
			int newUserCount = StatUtils.statNewUserCountByDay(DateFormatUtils.parseDate(date), users, context.getConfiguration());

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", date);
			resultKey.put("device", device);
			resultKey.put("brand", brand);
			ValueDBWritable value = new ValueDBWritable("launch_user_count", launchUserCount);
			value.put("new_user_count", newUserCount);
			context.write(resultKey, value);
		}
		protected void cleanup(Context context) throws IOException ,InterruptedException {
			LOG.info("**************************reducer end current time info:"+DateUtil.format(new Date()));
		};
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(1,LogLaunch.SN, new String[]{"time","uid"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, UserForDeviceMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,2);
		job.setNumReduceTasks(3);
		JDBCMapReduceUtil.initTableReducerJob("la_device_stat_separate", new String[] { "date", "brand", "device" },
				new String[] { "launch_user_count", "new_user_count" }, UserForDeviceReducer.class, job);
		
		job.waitForCompletion(true);
	}
}
