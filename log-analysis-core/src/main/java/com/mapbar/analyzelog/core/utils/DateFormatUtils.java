package com.mapbar.analyzelog.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtils {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
	
	
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
	 * 获得当前日期
	 * 
	 * @return java.sql.Date
	 */
	public static java.sql.Date getDate() {
		return convert(new Date());
	}
	
	public static String getNowTime(){
		return dateTimeFormat.format(new Date());
	}

	public static int getHour(String date) {
		try {
			return toCalendar(new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(date)).get(Calendar.HOUR_OF_DAY);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
     * Convert a Date into a Calendar object. 
     * 
     * @param date the date to convert to a Calendar
     * @return the created Calendar
     * @throws NullPointerException if null is passed in
     * @since 2.6
     */
    public static Calendar toCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }
	
	public static String timestampToDateFromat(Long timestamp){
		return dateFormat.format(new Date(timestamp));
	}
	
	public static String timestampToDatetimeFromat(long timestamp) {
		return dateTimeFormat.format(new Date(timestamp));
	}

	public static long getNowMillisecond() {
		return new Date().getTime();
	}

	public static Date parseDate(String date) {
		try {
			return dateFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formatDate(long timestamp, String format) {
		try {
			return new SimpleDateFormat(format).format(new Date(timestamp));
		} catch (Exception e) {
		}
		return null;
	}
	
	public static Date parseDatetime(String date) {
		try {
			return dateTimeFormat.parse(date);
		} catch (Exception e) {
		}
		return null;
	}
}
