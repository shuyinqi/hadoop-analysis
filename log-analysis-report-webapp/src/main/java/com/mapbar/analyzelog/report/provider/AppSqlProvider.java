/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;

import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;

import java.util.Map;

/**
 * <p>
 * 应用信息的SQL脚本提供器，提供映射器的定义规则
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-13
 */
public class AppSqlProvider implements java.io.Serializable {

	private static final long serialVersionUID = 7904474871778150893L;

	/** 查询表名 */
	private static final String TABLE_NAME = "la_app";

	/**
	 * 获取指定主键的记录
	 * 
	 * @param parameters
	 * @return String
	 */
	public String selectByPK(Map<String, Object> parameters) {
		BEGIN();
		SELECT("id,name,memo,addtime,flag");
		FROM(TABLE_NAME);
		if (parameters != null && parameters.containsKey("id")
				&& parameters.get("id") != null)
			WHERE("id = #{id}");
		return SQL();
	}

	/**
	 * 获取全部记录集
	 * 
	 * @return String
	 */
	public String selectAll(Map<String, Object> parameters) {
		return selectByPK(parameters);
	}
}