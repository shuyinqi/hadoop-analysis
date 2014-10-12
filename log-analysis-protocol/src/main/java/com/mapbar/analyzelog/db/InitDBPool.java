package com.mapbar.analyzelog.db;
import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;
public class InitDBPool {
	private  static ComboPooledDataSource cpds=new ComboPooledDataSource();
	public static  ComboPooledDataSource getDBPool(){
		if(cpds==null){
			cpds= new ComboPooledDataSource();
		}
		return cpds;
	}
	private InitDBPool() {}
	
	public static void initPool() {
		ResourceBundle db=ResourceBundle.getBundle("db");
		try {
			cpds.setDriverClass(SystemConstant.JDBCDriver);
		} catch (PropertyVetoException e) {
			System.out.println("加载驱动程序失败!");
			e.printStackTrace();
		} //loads the jdbc driver
		cpds.setJdbcUrl(SystemConstant.JDBCURL); 
		cpds.setUser(SystemConstant.JDBCUSER); 
		cpds.setPassword(SystemConstant.JDBCPASSWORD);
		cpds.setMinPoolSize(Integer.parseInt(db.getString("db.minPoolSize").trim())); 
		cpds.setMaxPoolSize(Integer.parseInt(db.getString("db.maxPoolSize").trim()));
		cpds.setAcquireIncrement(Integer.parseInt(db.getString("db.acquireIncrement").trim()));
		cpds.setAutomaticTestTable(db.getString("db.automaticTestTable").trim());
		cpds.setIdleConnectionTestPeriod(Integer.parseInt(db.getString("db.idleConnectionTestPeriod").trim()));
		cpds.setMaxStatements(0);
		cpds.setMaxStatementsPerConnection(Integer.parseInt(db.getString("db.maxStatementsPerConnection").trim()));
		cpds.setTestConnectionOnCheckin(Boolean.valueOf(db.getString("db.connectionOnCheckIn").trim()));
		//获取connnection时测试是否有效 
		cpds.setTestConnectionOnCheckout(Boolean.valueOf(db.getString("db.connectionOnCheckOut").trim()));
		cpds.setMaxIdleTime(60);
	}

}
