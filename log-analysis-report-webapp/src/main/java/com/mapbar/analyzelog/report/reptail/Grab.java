package com.mapbar.analyzelog.report.reptail;

import com.mapbar.analyzelog.report.db.JdbcFunction;

public abstract class Grab {

	private AparmProcess aparmProcess = new AparmProcess();
	private JdbcFunction jdbcFunction = new JdbcFunction();
	/***
	 * 入口开始方法 
	 */
	public abstract void grabStart();
	/***
	 * 分析的和入库 
	 * @param url  路径
 	 * @param segmentation 关键字
	 * @param channel   渠道名称
	 */
	public abstract void separate(String url,String segmentation,String channel);
	
	public AparmProcess getAparmProcess() {
		return aparmProcess;
	}
	public JdbcFunction getJdbcFunction() {
		return jdbcFunction;
	}
}
