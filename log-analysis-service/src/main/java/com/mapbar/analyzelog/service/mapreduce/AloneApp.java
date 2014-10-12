package com.mapbar.analyzelog.service.mapreduce;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;
import com.mapbar.analyzelog.core.utils.DateFormatUtils;
import com.mapbar.analyzelog.service.jdbc.JNDIManager;
import com.mapbar.analyzelog.service.utils.DateUtil;
/***
 * @（#）:RepeatApp.java 
 * @description:  
 * @author:  Administrator  2012-6-27 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class AloneApp {
 
	private  Connection conn;
	private Statement st;
	private ResultSet rs;
	
	public void getConnect(String fileName) throws Exception {
		JNDIManager jndi = new JNDIManager(fileName);
		conn = JNDIManager.getConnection();
	}

	public void insertOrUpdate(String sql) throws SQLException{
		 st = conn.createStatement();
		 st.executeUpdate(sql);
		 st.close();
	}	
	
	public Boolean select(String sql) throws SQLException{
		   st = conn.createStatement();
		   rs =st.executeQuery(sql);
		   int oldCount=0;
		   while(rs.next()){
			  oldCount++;
		   }
		   rs.close();
		   st.close();
		   return oldCount==0?false:true;
		}
	
	public static void main(String[] args) throws Exception {
		 //--appid 2000-1000 校验参数
		 String[] appids = args[1].split("-");
		 
		 Long startTime=null;
		 Long endTime=null;
		 if(args[3]!=null){
			 Calendar end=	 DateUtil.StringformatToCalendar(args[3]);
			 endTime=end.getTimeInMillis();
			 startTime=0l;
		 }
		 Set<String> list = new HashSet<String>();
		 LogStorageManager service = HBaseLogStorageManager.getStorageManager();
		 for(int i=0;i<appids.length;i++){
			 LogStorage prelogStorage = service.getLogStorage(appids[i]);
			 Set<String > set = prelogStorage.getRowKeyForTimeRange(startTime, endTime);
			 list.addAll(set);
		 }
		 String insertSql="insert into la_alone_stat values (null,'"+args[1]+"',"+list.size()+",'"+DateFormatUtils.getDate()+"','"+DateFormatUtils.getNowTime()+"')";
		 String selectSql="select * from la_alone_stat where app_ids='"+args[1]+"' and date='"+DateFormatUtils.getDate()+"'";
	     String updateSql="update la_alone_stat set repeat_id="+list.size()+" ,update_time='"+DateFormatUtils.getNowTime()+"' where app_ids='"+args[1]+"' and date='"+DateFormatUtils.getDate()+"'";
	     RepeatApp ra = new RepeatApp();
	     //"D:/server/trunk/log-analysis-service/src/main/resources/db-site.xml"
	     ra.getConnect(args[2]);
		 if(ra.select(selectSql)){
			ra.insertOrUpdate(updateSql);
		 }else{
			ra.insertOrUpdate(insertSql);	
		}
	}
	
}
