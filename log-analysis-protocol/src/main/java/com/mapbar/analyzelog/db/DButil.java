package com.mapbar.analyzelog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mapbar.analyzelog.common.LogWriter;

public class DButil {
	static{
		ConfigLoader.loadDBConfig();
		InitDBPool.initPool();
	}

		 public static Connection getConnection(){
				return getConnection(true);
			} 
		 public static Connection getConnection(boolean isAutoCommit){
			 Connection conn=null;
			 try {
				 conn= InitDBPool.getDBPool().getConnection();
				 conn.setAutoCommit(isAutoCommit);
			 } catch (SQLException e) {
				 e.printStackTrace();
			 } catch(Exception ex){
				 LogWriter.dbDebug("see:com.mapbar.analyzelog.db.DButil#getConnection()");
			 }
			 return conn;
		 } 
		
		public static PreparedStatement getPreparedStatement(String aStrSql){
			PreparedStatement ps=null;
			try {
				ps = getConnection().prepareStatement(aStrSql);
			} catch (SQLException e) {
				LogWriter.dbDebug("PreparedStatement�?���?DButil.getPreparedStatement();");
			}
			return ps;
		}
		
		public static PreparedStatement getPreparedStatement(Connection con,String aStrSql){
			PreparedStatement ps=null;
			try {
				ps =con.prepareStatement(aStrSql);
			} catch (SQLException e) {
			}
			return ps;
		}
		
		public static Statement getStatement(Connection con){
			Statement st=null;
			try {
				st =con.createStatement();
			} catch (SQLException e) {
			}
			return st;
		}
		
		public static ResultSet getResultSet(PreparedStatement ps){
			ResultSet rs=null;
			try {
				rs= ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
			
		}
		
		public static void commit(Connection con){
			try {
				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
		}
		
		
		public static int [] executeBatch(PreparedStatement ps){
			int[] iCount = null;
			try {
				iCount = ps.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return iCount;
		}
		public static int [] executeBatch(Statement st){
			int[] iCount = null;
			try {
				iCount = st.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return iCount;
		}
		
		public static void close(Statement st) {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	public static void close(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void closeAll(Connection con,PreparedStatement ps,ResultSet rs){
				if(null!=con){
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(null!=ps){
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(null!=rs){
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		}
		public static void main(String []args){
			getConnection();
		}
		
}
