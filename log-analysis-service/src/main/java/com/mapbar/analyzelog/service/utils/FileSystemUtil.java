package com.mapbar.analyzelog.service.utils;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
	 
public class FileSystemUtil {
	private static final String OUT_PATH="/home/jishu/plan/"+DateUtil.getDate();
	/**
	 * 将计划任务写入hdfs
	 * @param paths 读取文件的路径
	 * @param startDate 开始时间
	 * @param endDate 
	 * @param output_table 表明
	 *
	 */
	public static void start(String paths, java.sql.Date startDate,
			java.sql.Date endDate,String appid,String output_table) {
		   Configuration conf=new Configuration();
	        FileSystem hdfs = null;
			try {
				hdfs = FileSystem.get(conf);
				String st = paths+","+appid+","+startDate+","+endDate+","+output_table+"\n";
				byte[] buff=st.getBytes();
				Path dfs=new Path(OUT_PATH+"/"+output_table+System.currentTimeMillis()+".unload");
					FSDataOutputStream outputStream = null;
					outputStream = hdfs.create(dfs);
					outputStream.write(buff,0,buff.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
	   }
	
	public static void readHDFS(String paths){
		checkFile(paths);
		InputStream in =null;
		try {
			in = new URL(paths).openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeStream(in);
		}
	}
	
	private static void checkFile(String paths){
		try {
			Configuration conf=new Configuration();
	        FileSystem hdfs = FileSystem.get(conf);
				Path findf=new Path(paths);
			    boolean isExists=hdfs.exists(findf);
			    if(!isExists){
			    	System.err.println("not exists"+paths);
					System.exit(1);
			    }
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
}
