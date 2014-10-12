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
 * 基本统计的SQL脚本提供器，提供映射器的定义规则
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-15
 */
public class BasicStatSqlProvider implements java.io.Serializable {

	private static final long serialVersionUID = 2691762800958882493L;

	/** 查询表名 */
	private static final String TABLE_NAME_O = "la_basic_stat";
	private static final String TABLE_NAME_S = "la_section_stat";

	/**
	 * 获取指定应用指定日期间的基本统计
	 * 
	 * @param parameters
	 * @return String
	 */
	public String selectByAppDate2(Map<String, Object> parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append("select a.date,a.imei_size,a.new_imei_size,a.visit_imei_size,a.visit_size,b.ups as upgrade_imei_size,a.average_duration  from (");
		sb.append("SELECT o.date,(SELECT SUM(new_imei_size) FROM ")
				.append(TABLE_NAME_S)
				.append(" WHERE date<=o.date and appid=#{appid}) imei_size,SUM(s.new_imei_size) new_imei_size,SUM(s.visit_imei_size) visit_imei_size,SUM(s.visit_size) visit_size,o.upgrade_user_count upgrade_imei_size,o.average_duration FROM ")
				.append(TABLE_NAME_O).append(" o inner join ").append(TABLE_NAME_S)
				.append(" s on o.appid = s.appid AND o.date = s.date");
		if (parameters != null) {
			if (parameters.containsKey("appid")
					&& parameters.get("appid") != null)
				sb.append(" AND o.appid = #{appid}");
			if (parameters.containsKey("from")
					&& parameters.get("from") != null)
				sb.append(" AND o.date >= #{from}");
			if (parameters.containsKey("to") && parameters.get("to") != null)
				sb.append(" AND o.date <= #{to}");
		}
		sb.append(" GROUP BY o.date");
		if (parameters != null && parameters.containsKey("desc")
				&& parameters.get("desc") != null
				&& (Boolean) parameters.get("desc"))
			sb.append(" ORDER BY o.id DESC");
		sb.append(")a inner join");
		sb.append("(select sum(upgrade_user_count) as ups,date from la_version_stat where appid=#{appid} ");
		sb.append("and date BETWEEN date_sub(curdate(),interval 1 day) AND curdate() group by date)b on a.date=b.date order by date desc ");
		return sb.toString();
	}
	
	
	public String selectByAppDate(Map<String, Object> parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT o.date,(SELECT SUM(new_imei_size) FROM ")
				.append(TABLE_NAME_S)
				.append(" WHERE date<=o.date and appid=#{appid}) imei_size,SUM(s.new_imei_size) new_imei_size,SUM(s.visit_imei_size) visit_imei_size,SUM(s.visit_size) visit_size,o.upgrade_user_count upgrade_imei_size,o.average_duration FROM ")
				.append(TABLE_NAME_O).append(" o inner join ").append(TABLE_NAME_S)
				.append(" s on o.appid = s.appid AND o.date = s.date");
		if (parameters != null) {
			if (parameters.containsKey("appid")
					&& parameters.get("appid") != null)
				sb.append(" AND o.appid = #{appid}");
			if (parameters.containsKey("from")
					&& parameters.get("from") != null)
				sb.append(" AND o.date >= #{from}");
			if (parameters.containsKey("to") && parameters.get("to") != null)
				sb.append(" AND o.date <= #{to}");
		}
		sb.append(" GROUP BY o.date");
		if (parameters != null && parameters.containsKey("desc")
				&& parameters.get("desc") != null
				&& (Boolean) parameters.get("desc"))
			sb.append(" ORDER BY o.date DESC");
		return sb.toString();
	}
	
	public String selectCountByApp(Map<String, Object> parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT SUM(s.new_imei_size) new_imei_size,SUM(s.visit_imei_size) visit_imei_size,SUM(s.visit_size) visit_size FROM ")
				.append(TABLE_NAME_S);
		if (parameters != null) {
			if (parameters.containsKey("appid")
					&& parameters.get("appid") != null)
				sb.append(" s where s.appid = #{appid}");
			if (parameters.containsKey("from")
					&& parameters.get("from") != null)
				sb.append(" AND s.date >= #{from}");
			if (parameters.containsKey("to") && parameters.get("to") != null)
				sb.append(" AND s.date <= #{to}");
		}
		if (parameters != null && parameters.containsKey("desc")
				&& parameters.get("desc") != null
				&& (Boolean) parameters.get("desc"))
			sb.append(" ORDER BY s.id DESC");
		return sb.toString();
	}

	/**
	 * 获取指定应用的基本统计
	 * 
	 * @param parameters
	 * @return String
	 */
	public String selectByApp(Map<String, Object> parameters) {
		return selectByAppDate(parameters);
	}
}