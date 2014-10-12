package com.mapbar.analyzelog.service.mapreduce;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.mapbar.analyzelog.service.AbstractMapReduceJob;
import com.mapbar.analyzelog.service.hbase.ResultProxy;
import com.mapbar.analyzelog.service.jdbc.DBCounterReducer;
import com.mapbar.analyzelog.service.jdbc.JDBCMapReduceUtil;
import com.mapbar.analyzelog.service.jdbc.KeyDBWritable;
import com.mapbar.analyzelog.service.jdbc.ValueDBWritable;
import com.mapbar.analyzelog.service.spliter.TableMapbarReduceProxy;

/**
 * 回访用户统计, 老用户，且在最近 (由昨日向前计算)n天内启动过应用程序
 * 
 * @author 邓飞鸽
 */
public class ReturnVisitUserMapReducer extends AbstractMapReduceJob{
	
	private static final String OUTPUT_TABLE_NAME = "la_retention_stat";

	private static final String DAY = "day";
	private static final String CURRENT_TIME = "current_time";

	public static class ReturnVisitUserMapper extends TableMapper<Text, Text>{

        protected HTable userTable; 
        protected long startTime, endTime;
        private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		protected void setup(Context context) throws IOException, InterruptedException {
			userTable = new HTable(context.getConfiguration(), SystemConstants.getTableName("1000"));
			endTime = context.getConfiguration().getLong(CURRENT_TIME, 0);
        	Calendar calendar = Calendar.getInstance();
        	calendar.setTimeInMillis(endTime);
        	calendar.set(Calendar.HOUR_OF_DAY, 0);
        	calendar.set(Calendar.MILLISECOND, 0);
        	calendar.set(Calendar.MINUTE, 0);
        	calendar.set(Calendar.SECOND, 0);

        	startTime = calendar.getTimeInMillis();
		};

		protected void map(ImmutableBytesWritable row, Result launch, Context context) throws IOException, InterruptedException {
			ResultProxy launchResult = ResultProxy.getResultProxy(launch);
            try {
            	String date = dateFormat.format(new Date(context.getConfiguration().getLong(CURRENT_TIME, 0)));

            	String uid = launchResult.getValue(LogLaunch.SN, "uid");
            	Get get = new Get(Bytes.toBytes(uid));
				Result user = userTable.get(get);
            	if (user == null){
            		return;
            	}
            	ResultProxy userResult = ResultProxy.getResultProxy(user);
				long addTime = userResult.getLongValue(Equipment.SN, "time");
				if (addTime >= startTime && addTime <= endTime) {
					context.write(new Text(date), new Text(uid));
				}
            } catch (InterruptedException e) {
                throw new IOException(e);
            }
		};

		protected void cleanup(Context context) throws IOException ,InterruptedException {
			if (userTable != null){
				userTable.close();
			}
		};
	}

	public static class ReturnVisitUserReducer extends DBCounterReducer<Text, Text>{
		
		protected void reduce(Text key, Iterable<Text> values, Context context)throws IOException, InterruptedException {
			List<String> userKeys = new ArrayList<String>();
			for(Text val : values){
				String uid = val.toString();
				if (!userKeys.contains(uid)){
					userKeys.add(uid);
				}
			}
			int day = context.getConfiguration().getInt(DAY, 0);
			System.out.println(String.format("%d 天回访问用户统计: 日期: %s,  回访用户数：%d", day, key.toString(), userKeys.size()));

			KeyDBWritable resultKey = new KeyDBWritable();
			resultKey.put("date", key.toString());
			resultKey.put("before_day", day);
			ValueDBWritable value = new ValueDBWritable("visit_user_count", userKeys.size());
			context.write(resultKey, value);
		}
	}

	public void run(Job job) {
		try {
			
			int day = getArguments().getIntValue("-d", 7);
//			String jobName =getArguments().getAppID()+"_"+ job.getJobName()+"_"+day;
//			job.setJobName(jobName);
			
			Calendar endDay = getYesterdayOfEnd();
			Calendar startDay = (Calendar) endDay.clone();
			startDay.set(Calendar.DAY_OF_MONTH, startDay.get(Calendar.DAY_OF_MONTH)-(day));

			Calendar currentDay = (Calendar) startDay.clone();
			currentDay.set(Calendar.DAY_OF_MONTH, currentDay.get(Calendar.DAY_OF_MONTH));
			job.getConfiguration().setLong(CURRENT_TIME,  currentDay.getTimeInMillis());

			job.getConfiguration().setInt(DAY, day);
			System.out.println("startTime:"+new SimpleDateFormat("yyyy-MM-dd").format(new Date(startDay.getTimeInMillis())));
			System.out.println("endTime:"+new SimpleDateFormat("yyyy-MM-dd").format(new Date(endDay.getTimeInMillis())));
			Scan scan = getTimeRangeScan(startDay, endDay, LogLaunch.SN, new String[]{"uid"});
			TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.LAUNCH), scan, ReturnVisitUserMapper.class, 
					Text.class, Text.class, job,getArguments().getAppID(),day+"",3);
			job.setNumReduceTasks(3);
			JDBCMapReduceUtil.initTableReducerJob(OUTPUT_TABLE_NAME, new String[] { "date", "before_day" },
					new String[] { "visit_user_count" }, ReturnVisitUserReducer.class, job);
			
			job.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
