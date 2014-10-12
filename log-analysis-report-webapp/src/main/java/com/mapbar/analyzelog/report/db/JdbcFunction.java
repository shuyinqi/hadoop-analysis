package com.mapbar.analyzelog.report.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mapbar.analyzelog.report.utils.DateUtil;


/**
 * 执行JDBC连接数据库
 * @（#）:JdbcFunction.java 
 * @description:  
 * @author:  sunjiayu  
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class JdbcFunction {
	private Connection conn;
	private Statement st;
	private ResultSet rs;

	/**
	 * 构造方法 JdbcFunction.
	 * 
	 * @throws Exception
	 */
	public JdbcFunction() {
		try {
			conn = JNDIManager.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/***
 * 获取前一天的数据，计算今天新增数据，保存数据库
 * @param name
 * @param total
 * @param appid
 * @throws SQLException
 */
	public void saveReptile(String name,String total,String appid) throws SQLException {
	   String oldTotal = select("select total from la_channel_reptile where appid='"+appid+"' and name='"+name+"' and date='"+DateUtil.getStepDay(-1)+"'");
	   int addCount=0;
	   if(oldTotal!=null && !"".equals(oldTotal)){
		  addCount = Integer.parseInt(total.trim())-Integer.parseInt(oldTotal);
	   }
	   insert("insert into la_channel_reptile value (null,'"+DateUtil.getDate()+"','"+name+"',"+addCount+","+total+","+appid+");");
	}
	
	public String select(String sql) throws SQLException{
	   st = conn.createStatement();
	   rs =st.executeQuery(sql);
	   String oldCount="";
	   while(rs.next()){
		  oldCount = rs.getString(1);
	   }
	   rs.close();
	   st.close();
	   return oldCount;
	}
	
	public void insert(String sql) throws SQLException{
		 st = conn.createStatement();
		 int s =st.executeUpdate(sql);
		 st.close();
	}
	
	public static void main(String[] args) throws Exception {
		JdbcFunction jf = new JdbcFunction();
		jf.saveReptile("91", "1211", "1000");
	}
}

