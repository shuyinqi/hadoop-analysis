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
public class SpecialUserForChannelDeviceMapReducer extends AbstractMapReduceJob{

	public static class UserForChannelDeviceMapper extends TableMapper<Text, Text>{
		final Log LOG = LogFactory.getLog(UserForDeviceMapReducer.class);
		protected void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
        	ResultProxy launchResult = ResultProxy.getResultProxy(value,  Equipment.SN);
        	String chne = launchResult.getValue("chne");
        	String appv = launchResult.getValue("appv");
        	LOG.info("setup row:start:"+chne+"and stop:"+appv);
        	if("360".equals(chne)){
        		if("6".equals(appv.substring(0, 1))){
        			context.write(new Text(launchResult.getFormatDateByTimestamp("time")),new Text(chne));
        		}
        	}
	    }
	}

	public static class UserForChannelDeviceReducer extends Reducer<Text,Text,Text,Text> {
		final Log LOG = LogFactory.getLog(UserForDeviceMapReducer.class);
		protected void reduce( Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			int count=0;
			for(Text uid:values){
				count++;
			}
			context.write(key, new Text(count+""));
		}
	};

	public void run(Job job) throws IOException, InterruptedException, ClassNotFoundException {
		Scan scan = getTimeRangeScan(DateUtil.StringformatToCalendar("2012-09-20"),DateUtil.StringformatToCalendar("2012-10-08"),Equipment.SN, new String[]{"time","chne","appv"});
		TableMapbarReduceProxy.initTableMapperJobProxy(getHTableName(LogType.USER), scan, UserForChannelDeviceMapper.class, 
				Text.class, Text.class, job,getArguments().getAppID(),null,10);
		
		String paths ="/home/jishu/UserForChannelDevice/"+DateUtil.getDate()+"/"+getArguments().getAppID()+"/job_"+System.currentTimeMillis();
		
		TableMapbarReduceProxy.initReduceProxy(paths,1, UserForChannelDeviceReducer.class, Text.class, Text.class, getArguments(), job);
		
		job.waitForCompletion(true);
		
	}
}
