package com.mapbar.analyzelog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StrUtil {
	private static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static Object getValue(Object obj){
		try{
		return obj!=null?obj:"";
		}catch(Exception e){
			return "";
		}
	}
	
	public static boolean checkV(Object obj){
		try{
		return obj!=null&&!obj.equals("")?true:false;
		}catch(Exception e){
			return false;
		}
	}
	
	public static String StrtoDate(String str){
		java.util.Date oDate=null;
		try {
			oDate = dayFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long t = oDate.getTime () ;
		java.sql.Date sqlDate = new java.sql.Date(t) ;
		return sqlDate.toString();
	}
}
