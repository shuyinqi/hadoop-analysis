package com.mapbar.analyzelog.report.db;
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
import java.util.Properties;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 配置文件连接数据库
 * @（#）:JNDIManager.java 
 * @description:  
 * @author:  sunjiayu  2011-11-4 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  黑龙江中科方德软件有限公司
 */
public class JNDIManager {
	private static final String resource = "/database.properties";

	public static String driver = "";
	public static String url = "";
	public static String user = "";
	public static String password = "";
	public static ComboPooledDataSource ds = null;
	static {
		LoadProperties(); // 加载配置文件
		LoadDataBasePool();// 加载连接池
	}
	public static void LoadDataBasePool() {
		try {
			ds = null;
			ds = new ComboPooledDataSource();
			ds.setDriverClass(driver);
			ds.setJdbcUrl(url);
			ds.setUser(user);
			ds.setPassword(password);
			ds.setMaxPoolSize(500);
			ds.setInitialPoolSize(20);
			ds.setMinPoolSize(50);
			ds.setAcquireIncrement(3);
			ds.setCheckoutTimeout(3000);// 当连接池用完时客户端调用getConnection()后等待获取新连接的时间，超时后将抛出SQLException
			ds.setMaxIdleTime(10000);// 最大空闲时间，超过空闲时间的连接将被丢弃。为0或负数则永不丢弃。默认为0；

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void LoadProperties() {
		Properties p = new Properties();
		// System.out.println(resource);
		try {
			System.out.println("从properties文件获取信息....");
			p.load(JNDIManager.class.getResourceAsStream(resource));
		} catch (Exception e) {
			System.out.println("从properties文件获取数据库连接信息失败" + e.getMessage());
		}
		driver = p.getProperty("db.driver");
		url = p.getProperty("db.url");
		user = p.getProperty("db.username");
		password = p.getProperty("db.password");
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
		/*
		 * String resource = "properties/database.properties"; Properties p =
		 * new Properties();
		 * p.load(ClassLoader.getSystemResourceAsStream(resource));
		 * p.list(System.out); System.out.println("url:" +
		 * p.getProperty("url")); System.out.println("password:" +
		 * p.getProperty("password")); System.out.println("driver:" +
		 * p.getProperty("driver")); System.out.println("username:" +
		 * p.getProperty("username"));
		 */
		for (int i = 0; i < 300; i++) {
			Connection con = getConnection();
			
			if (con != null) {
				System.out.println(i + ":ok");
				con.close();
			} else
				System.out.println("failed");
		}
	}
}
