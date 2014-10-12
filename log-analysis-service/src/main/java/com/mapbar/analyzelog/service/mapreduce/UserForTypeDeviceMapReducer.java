package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

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
import com.mapbar.analyzelog.service.utils.FileSystemUtil;
import com.mapbar.analyzelog.service.utils.StatUtils;

/**
 * 设备用户统计区分类型。
 */
public class UserForTypeDeviceMapReducer extends AbstractMapReduceJob{

	public static class UserForTypeDeviceMapper extends TableMapper<Text, Text>{
		final Log LOG = LogFactory.getLog(UserForTypeDeviceMapReducer.class);
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
			String brand = userResult.getValueNotNull("brand");
			String deviceModel = userResult.getValueNotNull("devm");
			String chtp =  userResult.getValueNotNull("chtp");
        	Text key = new Text(launchResult.getFormatDateByTimestamp("time") + "|" + brand + "|" + deviceModel+"|"+chtp);
        	context.write(key, new Text(uid));
		};
		
		protected void cleanup(Context context) throws IOException ,InterruptedException {
			if (userTable != null){
				userTable.close();
			}
		};
	}

	public static class UserForTypeDeviceReducer extends Reducer<Text,Text,Text,Text> {
		final Log LOG = LogFactory.getLog(UserForDeviceMapReducer.class);
		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			String[] arrKey = key.toString().split("\\|");
			String date = arrKey[0];
			String device = arrKey[2];
			String brand = arrKey[1];
			String chtp = arrKey[3];
			List<String> users = StatUtils.getUniqueTextList(values);
			int launchUserCount = users.size();
			int newUserCount = StatUtils.statNewUserCountByDay(DateFormatUtils.parseDate(date), users, context.getConfiguration());
			context.write(new Text(date+","+device+","+brand+","+chtp+","), new Text(newUserCount+","+launchUserCount));
		}
		protected void cleanup(Context context) throws IOException ,InterruptedException {
			LOG.info("**************************reducer end current time info:"+DateUtil.format(new Date()));
		};
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(1,LogLaunch.SN, new String[]{"time","uid"});
		
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, UserForTypeDeviceMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,2);
		
		String paths ="/home/jishu/userfortypedevice/"+DateUtil.getDate()+"/"+getArguments().getAppID()+"/job_"+System.currentTimeMillis();
		
		TableMapbarReduceProxy.initReduceProxy(paths, 10, UserForTypeDeviceReducer.class, Text.class, Text.class, getArguments(), job);
		/*
		job.setNumReduceTasks(10);
		job.setReducerClass(UserForTypeDeviceReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileOutputFormat.setOutputPath(job, new Path("/home/jishu/userfortypedevice/"+DateUtil.getDate()+"/"+getArguments().getAppID()+"/"+job.getJobID()));
	    */
		job.waitForCompletion(true);
		Calendar endCalendar = getYesterdayOfEnd();
		DateUtil.convert(new Date(endCalendar.getTimeInMillis()));
		Calendar startCalendar =(Calendar) endCalendar.clone();
		startCalendar.add(Calendar.DAY_OF_MONTH,-1+1);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		FileSystemUtil.start(paths, DateUtil.convert(new Date(startCalendar.getTimeInMillis())),DateUtil.convert(new Date(endCalendar.getTimeInMillis())) ,getArguments().getAppID(), "la_device_type_stat");
	}
}
