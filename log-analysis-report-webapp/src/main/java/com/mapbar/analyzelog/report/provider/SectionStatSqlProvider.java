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
 * 分段统计的SQL脚本提供器，提供映射器的定义规则
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-15
 */
public class SectionStatSqlProvider implements java.io.Serializable {

	private static final long serialVersionUID = 5992750492603998141L;

	/** 查询表名 */
	private static final String TABLE_NAME = "la_section_stat";

	/**
	 * 获取指定主键的记录
	 * 
	 * @param parameters
	 * @return String
	 */
	public String selectByAppDate(Map<String, Object> parameters) {
		BEGIN();
		SELECT("appid,date,new_imei_size,visit_imei_size,visit_size,section");
		FROM(TABLE_NAME);
		if (parameters != null) {
			if (parameters.containsKey("appid")
					&& parameters.get("appid") != null)
				WHERE("appid = #{appid}");
			if (parameters.containsKey("date")
					&& parameters.get("date") != null)
				WHERE("date = #{date}");
		}
		return SQL();
	}

	public String selectAllSize(Map<String, Object> parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT SUM(new_imei_size) new_imei_size, SUM(visit_size) visit_size FROM ")
				.append(TABLE_NAME).append(" WHERE appid = #{appid}");
		return sb.toString();
	}
}