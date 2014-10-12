/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.provider;

import java.util.Map;

import com.mapbar.analyzelog.report.utils.DateUtil;

/**
 * <p>
 * 渠道统计的SQL脚本提供器，提供映射器的定义规则
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-22
 */
public class ChannelStatSqlProvider implements java.io.Serializable {

	private static final long serialVersionUID = -4466828394099921992L;

	/** 市场类型 */
	public static final String MARKET_TYPE = "market";
	/** 预装类型 */
	public static final String INSTALLED_TYPE = "pre_installed";

	/**
	 * 获取指定应用、渠道类型的访问用户统计
	 * 
	 * @param parameters
	 * @return String
	 */
	public String selectByAppType4Visit(Map<String, Object> parameters) {
		return selectByAppReptilType("launch_user_count", parameters);
	}
	
	public String selectByAppType4VisitVersion(Map<String, Object> parameters) {
		return selectByAppReptilTypeVersion("launch_user_count", parameters);
	}
	/**
	 * 获取指定应用、渠道类型的新用户统计
	 * 
	 * @param parameters
	 * @return String
	 */
	public String selectByAppType4New(Map<String, Object> parameters) {
		return selectByAppReptilType("new_user_count", parameters);
	}
	
	public String selectByAppType4NewVersion(Map<String, Object> parameters) {
		return selectByAppReptilTypeVersion("new_user_count", parameters);
	}
    /***
     * 获得新增的爬虫数据
     * @return
     *
     */
	public String selectByAppReptileNew(Map<String, Object> parameters){
		return selectByAppReptilType("new_user_count", parameters);
	}
	/***
     * 按照版本获得新增的爬虫数据
     * @return
     *
     */
	public String selectByAppReptileNewVersion(Map<String, Object> parameters){
		return selectByAppReptilTypeVersion("new_user_count", parameters);
	}
	/***
	 * 不带版本的统计
	 * @param column
	 * @param parameters
	 * @return
	 *
	 */
	private String selectByAppReptilType(String column, Map<String, Object> parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append("select o.name,ifnull(r.addCount,0) as addCount,")
		.append(" sum(case when imei_size0='imei_size0' then ").append(column).append(" end) as imei_size0,")
		.append(" sum(case imei_size0 when 'imei_size1' then ").append(column).append(" end) imei_size1,")
		.append(" sum(case when imei_size0='imei_size2' then ").append(column).append(" end) imei_size2,")
		.append(" sum(case when imei_size0='imei_size3' then ").append(column).append(" end) imei_size7,")
		.append(" ifnull(sum(case when imei_size0='name_imei_size' then ").append(column).append(" end),0) name_imei_size,")
		.append("(select sum(s.a) from ")
		.append(" (SELECT SUM(").append(column).append(") a from la_channel_stat where appid = #{appid} AND type = #{type} and date<=#{toDate} ")
		.append("union")
		.append(" SELECT SUM(").append(column).append(") a from la_channel_version_stat where appid = #{appid} AND type = #{type} and date<=#{toDate} ")
		.append(" ) s") 
		.append(") type_imei_size,b.note ")
		.append(" from (")
		
		.append(" SELECT name,").append(column).append(", 'imei_size0' imei_size0  FROM la_channel_stat WHERE appid = #{appid} AND type = #{type} AND date = '").append(DateUtil.getStepDay(0)).append("'")
	    .append(" union all")
	    .append(" SELECT name,").append(column).append(", 'imei_size1' imei_size0 FROM la_channel_stat WHERE appid = #{appid} AND type = #{type} AND date = '").append(DateUtil.getStepDay(-1)).append("'")
	    .append(" union all")
	    .append(" SELECT name,").append(column).append(", 'imei_size2' imei_size0 FROM la_channel_stat WHERE appid = #{appid} AND type = #{type} AND date = '").append(DateUtil.getStepDay(-2)).append("'")
	    .append(" union all")
	    .append(" SELECT name,").append(column).append(", 'imei_size3' imei_size0 FROM la_channel_stat WHERE appid = #{appid} AND type = #{type} AND date = '").append(DateUtil.getStepDay(-7)).append("'")
	    .append(" union all")
	    .append(" SELECT name,").append(column).append(", 'name_imei_size' imei_size0 FROM la_channel_stat WHERE appid = #{appid} AND type = #{type} and date<=#{toDate}")
	    /***
	     * 兼顾la_channel_version_stat
	     */
	    .append("union all")
	    .append(" SELECT name,").append("sum(").append(column).append(") ").append(column).append(", 'imei_size0' imei_size0  FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} AND date = '").append(DateUtil.getStepDay(0)).append("'").append(" group by name")
	    .append(" union all")
	    .append(" SELECT name,").append("sum(").append(column).append(") ").append(column).append(", 'imei_size1' imei_size0 FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} AND date = '").append(DateUtil.getStepDay(-1)).append("'").append(" group by name")
	    .append(" union all")
	    .append(" SELECT name,").append("sum(").append(column).append(") ").append(column).append(", 'imei_size2' imei_size0 FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} AND date = '").append(DateUtil.getStepDay(-2)).append("'").append(" group by name")
	    .append(" union all")
	    .append(" SELECT name,").append("sum(").append(column).append(") ").append(column).append(", 'imei_size3' imei_size0 FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} AND date = '").append(DateUtil.getStepDay(-7)).append("'").append(" group by name")
	    .append(" union all")
	    .append(" SELECT name,").append("sum(").append(column).append(") ").append(column).append(", 'name_imei_size' imei_size0 FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} and date<=#{toDate}").append(" group by name")
	    
	    .append(" ) o left join la_channel_notes b ")
	    .append(" on #{appid}=b.appid ").append(" and  #{type}=b.type ").append(" and o.name=b.name ").append(" left join la_channel_reptile r ")
	    .append(" on o.name=r.name and r.date='").append(DateUtil.getStepDay(0)).append("' and r.appid=#{appid}")
	    .append(" group by o.name")
	    .append(" ORDER BY ")
	    .append("name_imei_size desc");
		return sb.toString();
	}
	
	/***
	 * 带版本的统计
	 * @param column
	 * @param parameters
	 * @return
	 *
	 */
	private String selectByAppReptilTypeVersion(String column, Map<String, Object> parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append("select o.name,ifnull(r.addCount,0) as addCount,")
		.append(" sum(case when imei_size0='imei_size0' then ").append(column).append(" end) as imei_size0,")
		.append(" sum(case imei_size0 when 'imei_size1' then ").append(column).append(" end) imei_size1,")
		.append(" sum(case when imei_size0='imei_size2' then ").append(column).append(" end) imei_size2,")
		.append(" sum(case when imei_size0='imei_size3' then ").append(column).append(" end) imei_size7,")
		.append(" ifnull(sum(case when imei_size0='name_imei_size' then ").append(column).append(" end),0) name_imei_size,")
		.append(" (SELECT SUM(").append(column).append(") from la_channel_version_stat where appid = #{appid} and version=#{version} AND type = #{type} and date<=#{toDate}) type_imei_size,").append("b.note ")
		.append(" from (")
		.append(" SELECT name,").append(column).append(", 'imei_size0' imei_size0  FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} and version=#{version} and version=#{version} AND date = '").append(DateUtil.getStepDay(0)).append("'")
	    .append(" union all")
	    .append(" SELECT name,").append(column).append(", 'imei_size1' imei_size0 FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} and version=#{version} and version=#{version} AND date = '").append(DateUtil.getStepDay(-1)).append("'")
	    .append(" union all")
	    .append(" SELECT name,").append(column).append(", 'imei_size2' imei_size0 FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} and version=#{version} and version=#{version} AND date = '").append(DateUtil.getStepDay(-2)).append("'")
	    .append(" union all")
	    .append(" SELECT name,").append(column).append(", 'imei_size3' imei_size0 FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} and version=#{version} and version=#{version} AND date = '").append(DateUtil.getStepDay(-7)).append("'")
	    .append(" union all")
	    .append(" SELECT name,").append("sum(").append(column).append(") ").append(column).append(", 'name_imei_size' imei_size0 FROM la_channel_version_stat WHERE appid = #{appid} AND type = #{type} and version=#{version} and date<=#{toDate}").append(" group by name")
	    .append(" ) o left join la_channel_notes b ")
	    .append(" on #{appid}=b.appid ").append(" and  #{type}=b.type ").append(" and o.name=b.name ").append(" left join la_channel_reptile r ")
	    .append(" on o.name=r.name and r.date='").append(DateUtil.getStepDay(0)).append("' and r.appid=#{appid}")
	    .append(" group by o.name")
	    .append(" ORDER BY ")
	    .append("name_imei_size desc");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
}