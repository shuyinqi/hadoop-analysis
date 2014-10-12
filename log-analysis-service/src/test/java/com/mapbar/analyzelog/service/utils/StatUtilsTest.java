package com.mapbar.analyzelog.service.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

import com.mapbar.analyzelog.service.Argument;

import junit.framework.TestCase;

public class StatUtilsTest extends TestCase {

	
	public void testStatNewUserCountByDay() throws IOException{
		List<String> userids = new ArrayList<String>();
		for (int i =0; i < 100000; i++){
			userids.add("user"+i);
		}
		Configuration configuration = HBaseConfiguration.create();
		configuration.set(Argument.APP_ID_NAME, "1000");
		int i = StatUtils.statNewUserCountByDay(new Date(), userids, configuration);
		assertEquals(0, i);
	}
	
	public static void main(String[] args) {
		System.out.println(getMD5("866259000237169"));
	}
	
	public static String getMD5(String paramString) {
		try {
		MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
		localMessageDigest.update(paramString.getBytes());
		byte[] arrayOfByte = localMessageDigest.digest();
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < arrayOfByte.length; ++i) {
		int j = 0xFF & arrayOfByte[i];
		localStringBuffer.append(Integer.toHexString(j));
		}
		return localStringBuffer.toString();
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
		
		}
		return "";
		}
}
