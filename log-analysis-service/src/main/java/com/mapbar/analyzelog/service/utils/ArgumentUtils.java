package com.mapbar.analyzelog.service.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 参数解析处理工具类。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class ArgumentUtils {
	
	public static String getValue(String[]args, String name){
		return getValue(args, name, null);
	}

	public static String getValue(String[]args, String name, String defaultVal){
		String value = null;
		for (int i = 0; i < args.length; i++){
			if (args[i].equals(name)){
				value = args[i+1];
			}
		}
		if (StringUtils.isEmpty(value) && StringUtils.isNotEmpty(defaultVal)){
			value = defaultVal;
		}
		return value;
	}

	public static int getIntValue(String[]args, String name, int defaultVal){
		String value = getValue(args, name);
		if (StringUtils.isNotEmpty(value) && NumberUtils.isNumber(value)){
			return Integer.parseInt(value);
		} else {
			return defaultVal;
		}
	}

	public static boolean has(String[] arguments, String name) {
		return StringUtils.isNotEmpty(getValue(arguments, name));
	}

	public static Calendar getCalendarValue(String[] arguments, String name) {
		String value = getValue(arguments, name);
		if (StringUtils.isEmpty(value)){
			return null;
		}
		Calendar calendar;
		try {
			calendar = GregorianCalendar.getInstance();
			calendar.setTimeInMillis(new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(value).getTime());
		} catch (Exception e) {
			try {
				calendar = GregorianCalendar.getInstance();
				calendar.setTimeInMillis(new SimpleDateFormat("yyyy-MM-dd").parse(value).getTime());
			} catch (Exception e2) {
				e2.printStackTrace();
				calendar = null;
			}
		}
		return calendar;
	}
}
