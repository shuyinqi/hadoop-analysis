package com.mapbar.analyzelog.service.jdbc;
/**
 * 文件名：JNDIManager.java <br/>
 * <br/>
 * 版本信息： <br/>
 * 日期：2011-5-6 <br/>
 * Copyright 黑龙江中科方德软件有限公司 2011 <br/>
 * <br/>
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 配置文件连接数据库
 * @（#）:JNDIManager.java 
 * @description:  
 * @author:  sunjiayu  2011-11-4 
 * @version: [SVN] 
 * @modify: 
 */
public class JNDIManager {
    
	public static String fileName="";
	public static String driver = "";
	public static String url = "";
	public static String user = "";
	public static String password = "";
   
	public JNDIManager(String fileName){
		ReadXml rx = new ReadXml();
		 rx.setFileName(fileName);
		 System.out.println("JNDIManager:"+fileName);
		 rx.ReadXmlToJDBC();
		 driver=rx.getDrivername();
		 url = rx.getSConnStr();
		 user = rx.getUsername();
		 password = rx.getPassword();
	}

	public static Connection getConnection() throws Exception {

		Connection con = null;
		if (con == null) {
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.out.println("获取连接失败！");
			}
		}
		return con;
	}

	public static Connection getConnection(boolean isAutoCommit)
			throws Exception {
		Connection con = getConnection();
		try {
			con.setAutoCommit(isAutoCommit);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("设置是否自动提交模式失败！");
		}
		return con;
	}

	/**
	 * 关闭和连接相关的rs,stmt,con
	 * 
	 * @param rs
	 * @param stmt
	 * @param con
	 */
	public static void close(ResultSet rs, Statement stmt, Connection con) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					;
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					;
				}
				stmt = null;
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					;
				}
				con = null;
			}
		}
	}

	/**
	 * 关闭所有和连接相关的rs,pstmt,con信息
	 * 
	 * @param rs
	 * @param stmt
	 * @param con
	 */
	public static void close(ResultSet rs, PreparedStatement stmt,
			Connection con) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					;
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					;
				}
				stmt = null;
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					;
				}
				con = null;
			}
		}
	}

	public static void close(Statement stmt, Connection con) {
		close(null, stmt, con);
	}

	public static void close(PreparedStatement stmt, Connection con) {
		close(null, stmt, con);
	}

	public static void main(String args[]) throws Exception {
		for (int i = 0; i < 300; i++) {
			JNDIManager jndi = new JNDIManager("D:/server/trunk/log-analysis-service/src/main/resources/db-site.xml");
			Connection con = getConnection();
			
			if (con != null) {
				System.out.println(i + ":ok");
				con.close();
			} else
				System.out.println("failed");
		}
	}
}
