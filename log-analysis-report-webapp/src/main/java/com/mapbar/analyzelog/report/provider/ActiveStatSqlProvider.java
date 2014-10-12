/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.provider;

import java.util.Map;

/**
 * <p>
 * 活跃统计的SQL脚本提供器，提供映射器的定义规则
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-24
 */
public class ActiveStatSqlProvider implements java.io.Serializable {

	private static final long serialVersionUID = 2691762800958882493L;

	/** 查询表名 */
	private static final String TABLE_NAME = "la_active_stat";

	/**
	 * 获取指定应用指定日期间的活跃统计
	 * 
	 * @param parameters
	 * @return String
	 */
	public String selectByAppDate(Map<String, Object> parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT date,before_day,active_user_count FROM ")
				.append(TABLE_NAME).append(" WHERE 1=1");
		if (parameters != null) {
			if (parameters.containsKey("appid")
					&& parameters.get("appid") != null)
				sb.append(" AND appid = #{appid}");
			if (parameters.containsKey("date")
					&& parameters.get("date") != null)
				sb.append(" AND date = #{date}");
		}
		sb.append(" ORDER BY before_day DESC");
		return sb.toString();
	}
}