/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.service.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * <p>
 * 日期时间工具类，提供对日期和时间进行各种运算操作的支持
 * </p>
 */
public class DateUtil {
	/**
	 * 1000毫秒*60秒*60分*24小时=一天毫秒数
	 */
	public static final long MILLISECOND_DAY = 86400000;

	/**
	 * 1年=31536000秒
	 */
	public static final int SECOND_YEAR = 31536000;

	/**
	 * 将日期类型转换为字符类型
	 * 
	 * @param date
	 * @param pattern
	 * @return String
	 */
	private static String format(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	/**
	 * calendar类型转化为String类型
	 * @param calendar
	 * @return
	 *
	 */
	public static String convertCalendar(Calendar calendar){
		return format(new Date(calendar.getTimeInMillis()));
	}
	/**
	 * 将日期类型转换为字符类型[19位：yyyy-MM-dd HH:mm:ss]
	 * 
	 * @param date
	 * @return String
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将字符类型转换为sql类日期类型
	 * 
	 * @param date
	 * @param pattern
	 * @return java.sql.Date
	 * @throws ParseException
	 */
	private static java.sql.Date parse(String date, String pattern)
			throws ParseException {
		return convert(new SimpleDateFormat(pattern).parse(date));
	}

	/**
	 * 将字符类型[19位：yyyy-MM-dd HH:mm:ss]转换为日期类型
	 * 
	 * @param date
	 * @return java.sql.Date
	 * @throws ParseException
	 */
	public static java.sql.Date parse19(String date) throws ParseException {
		return parse(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将字符类型[10位：yyyy-MM-dd HH:mm:ss]转换为日期类型
	 * 
	 * @param date
	 * @return java.sql.Date
	 * @throws ParseException
	 */
	public static java.sql.Date parse10(String date) throws ParseException {
		return parse(date, "yyyy-MM-dd");
	}

	/**
	 * 将util类日期转换为sql类日期
	 * 
	 * @param date
	 * @return java.sql.Date
	 */
	public static java.sql.Date convert(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 获得当前日期时间
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 获得当前日期
	 * 
	 * @return java.sql.Date
	 */
	public static java.sql.Date getDate() {
		return convert(new Date());
	}

	/**
	 * 获得从现在开始步进<b>天</b>数后的日期，如-1则昨天、2则后天，以此类推
	 * 
	 * @param amount
	 * @return java.sql.Date
	 */
	public static java.sql.Date getStepDay(int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, amount);
		return convert(calendar.getTime());
	}
	
	/**
	 * 获得从指定日期开始步进<b>天</b>数后的日期，如-1则昨天、2则后天，以此类推
	 * 
	 * @param amount
	 * @return java.sql.Date
	 */
	public static java.sql.Date getStepDay(Date date,int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return convert(calendar.getTime());
	}

	/**
	 * 获得从现在开始步进<b>周</b>数后的日期，如-1则上周、2则下下周，以此类推
	 * 
	 * @param amount
	 * @return java.sql.Date
	 */
	public static java.sql.Date stepWeekOfMonth(int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.WEEK_OF_MONTH, amount);
		return convert(calendar.getTime());
	}
	
	/*
	 * 获得从现在开始步进<b>周</b>数后的日期，如-1则上周、2则下下周，以此类推
	 * @param date
	 * @param amount
	 * @return java.sql.Date
	 */
	public static java.sql.Date stepWeekOfMonth(Date date,int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_MONTH, amount);
		return convert(calendar.getTime());
	}
	
	/**
	 * 获得从现在开始步进<b>周</b>数后的日期，如-1则上周、2则下下周，以此类推
	 * @param date
	 * @param amount
	 * @return java.sql.Date
	 */
	public static java.sql.Date stepWeekOfMonth(String date,int amount){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar();	
		try {
			cal.setTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stepWeekOfMonth(cal.getTime(),amount);
	}

	/**
	 * 获得从现在开始步进<b>月</b>数后的日期，如-1则上月、2则下下月，以此类推
	 * 
	 * @param amount
	 * @return java.sql.Date
	 */
	public static java.sql.Date getStepMonth(int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, amount);
		return convert(calendar.getTime());
	}
	
	public static java.sql.Date getStepMonth(Date date,int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return convert(calendar.getTime());
	}

	/**
	 * 日期相减，返回长整型数值
	 * 
	 * @param begin
	 * @param end
	 * @return long
	 */
	public static long subDate(Date begin, Date end) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);
		long beginTime = calendar.getTimeInMillis();
		calendar.setTime(end);
		long endTime = calendar.getTimeInMillis();
		return beginTime - endTime;
	}
	
	/**
	 * 返回日期指示一个星期中的某天
	 * @param date
	 * @param amount
	 * @return
	 */
	public static int getDayOfWeek(Date date,int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_WEEK, amount);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 始终返回某日期一周中的星期几 
	 * @param toDay4 以某日期为基准
	 * @param i	返回星期几
	 * @return java.sql.Date
	 * <p>
	 * <ul>
	 * <li>
	 * Example: getStepBeforeDay(new Date(),0) 返回当前日期为基本的上周日</li>
	 * </ul>
	 * </p>
	 */
	public static Date getStepBeforeDay(Date toDay4,int i){
		Date edate =null;
		int dayOfWeek=DateUtil.getDayOfWeek(toDay4,-1);
		switch(dayOfWeek) {
		case 1: 
			edate = DateUtil.getStepDay(toDay4,i-1); break; 
		case 2: 
			edate = DateUtil.getStepDay(toDay4,i-2); break; 
		case 3: 
			edate = DateUtil.getStepDay(toDay4,i-3); break; 
		case 4: 
			edate = DateUtil.getStepDay(toDay4,i-4); break; 
		case 5: 
			edate = DateUtil.getStepDay(toDay4,i-5); break; 
		case 6: 
			edate = DateUtil.getStepDay(toDay4,i-6); break; 
		default: edate=toDay4;
		}
		return edate;
	}
	
	public static Calendar formatToDate(Date date){
		Calendar calendar;
		try {
			calendar = GregorianCalendar.getInstance();
			calendar.setTimeInMillis(parse(date.toString(),"yyyy-MM-dd").getTime());
		} catch (Exception e2) {
			e2.printStackTrace();
			calendar = null;
		}
		return calendar;
	}
	
	public static Calendar StringformatToCalendar(String date){
		Calendar calendar;
		try {
			calendar = GregorianCalendar.getInstance();
			calendar.setTimeInMillis(parse(date,"yyyy-MM-dd").getTime());
		} catch (Exception e2) {
			e2.printStackTrace();
			calendar = null;
		}
		return calendar;
	}
}
