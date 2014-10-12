package com.mapbar.analyzelog.service.mapreduce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mapbar.analyzelog.core.LogStorage;
import com.mapbar.analyzelog.core.LogStorageManager;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;
import com.mapbar.analyzelog.core.utils.DateFormatUtils;
import com.mapbar.analyzelog.service.jdbc.JNDIManager;
/***
 * @（#）:RepeatApp.java 
 * @description:  
 * @author:  Administrator  2012-6-27 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class PreBrandCount {
	private static Logger logger = LoggerFactory.getLogger(PreBrandCount.class);
	private  Connection conn;
	private Statement st;
	private ResultSet rs;
	
	public void getConnect(String fileName) throws Exception {
		JNDIManager jndi = new JNDIManager(fileName);
		conn = JNDIManager.getConnection();
	}

	public void insertOrUpdate(String sql){
		 try {
			 st = conn.createStatement();
			 st.executeUpdate(sql);
			 st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		PreBrandCount pb = new PreBrandCount();
         Map<String,List<String>> map = new HashMap<String, List<String>>();
		 LogStorageManager service = HBaseLogStorageManager.getStorageManager();
		 LogStorage prelogStorage = service.getLogStorage("1000");
		 String arr="eq,chtp,pre_installed,brand,clnt";
		 List<String> list = prelogStorage.getColumnsForfilter(null, null, arr); 
		 System.out.println("所有数据长度为:"+list.size());
		 logger.info("all data length:"+list.size());
		 for(String s:list){
			 if(map.containsKey(s)){
				 map.get(s).add("1");
			 }else{
				 List<String> st = new ArrayList<String>();
				 st.add("1");
				 map.put(s, st);
			 }
		 }
//		 pb.getConnect("D:\\server\\trunk\\log-analysis-service\\src\\main\\resources\\db-site.xml");
		 pb.getConnect(args[0]);
		 for(Entry<String, List<String>> s:map.entrySet()){
			 String insert_sql="insert into pre_brand_count values(null,1000,'"+DateFormatUtils.getDate()+"','"+s.getKey()+"',"+s.getValue().size()+",'"+DateFormatUtils.getNowTime()+"',0)";
			 pb.insertOrUpdate(insert_sql);
		 }
	}
	
}
