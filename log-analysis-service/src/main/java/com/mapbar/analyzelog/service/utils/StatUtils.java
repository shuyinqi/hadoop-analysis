package com.mapbar.analyzelog.service.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import com.mapbar.analyzelog.core.SystemConstants;
import com.mapbar.analyzelog.service.Argument;
import com.mapbar.analyzelog.service.exception.DataStatException;

/**
 * Statistical tools.
 * 
 * @author 邓飞鸽
 * 
 */
public class StatUtils {

	/**
	 * 统计不重复 Text 的数量。
	 * 
	 * @param texts the text iterable.
	 * @return total of number
	 */
	public static int statUniqueTextNumber(Iterable<Text> texts) {
		return getUniqueTextList(texts).size();
	}
	
	/**
	 * 统计不重复 Text 的集合。
	 * 
	 * @param texts the text iterable.
	 * @return the text of string list 
	 */
	public static List<String> getUniqueTextList(Iterable<Text> texts) {
		List<String> strTexts = new ArrayList<String>();
		Set<String> hset = new HashSet<String>();
		for (Text text : texts) {
			String strText = text.toString();
			hset.add(strText);
		}
		for(String st:hset){
			strTexts.add(st);
		}
		return strTexts;
	}

	public static int statNewUserCountByDay(Date date, List<String> users, Configuration conf) throws IOException {
		int dayTimestamp = 24 * 3600 * 1000; 
		return statNewUserCountByTime(date, users, conf, dayTimestamp); 
	}

	public static int statNewUserCountByHour(Date date, List<String> users, Configuration conf) throws IOException {
		int hourTimeStamp = (1 * 3600 * 1000)-1000; 
		return statNewUserCountByTime(date, users, conf, hourTimeStamp);
	}

	/**
	 * 统计指定时间一天内的新用户数。
	 * 
	 * @param date new user of date 
	 * @param users all users
	 * @return new user total number
	 * @throws IOException 
	 */
	public static int statNewUserCountByTime(Date date, List<String> users, Configuration conf, long maxStampStep) throws IOException {
		Set<String> set = new HashSet<String>();
		if (date == null || users.size() <= 0) {
			return 0;
		}
		String appID = conf.get(Argument.APP_ID_NAME);
		if (StringUtils.isEmpty(appID)) {
			throw new DataStatException("从 Configuration 中无法获取 appID");
		}
		HTable userTable = new HTable(conf, SystemConstants.getTableName(appID));
		long minStamp = date.getTime();
		long maxStamp = minStamp + maxStampStep;

		List<Get> userGets = new ArrayList<Get>();
		for (String userID : users) {
			Get get = new Get(Bytes.toBytes(userID));
			get.setTimeRange(minStamp, maxStamp);
			userGets.add(get);
		}
		Result[] results = userTable.get(userGets);
		for (Result result : results) {
			   for(KeyValue kv:result.raw()){
				   set.add(new String(kv.getRow()));
               }
		}
		if (userTable != null){
			userTable.close();
		}
		return set.size();
	}

}
