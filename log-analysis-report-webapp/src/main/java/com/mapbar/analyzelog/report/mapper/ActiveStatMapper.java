/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.mapbar.analyzelog.report.entity.LaActiveStat;
import com.mapbar.analyzelog.report.provider.ActiveStatSqlProvider;

/**
 * <p>
 * 基本统计的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.provider.BasicStatSqlProvider
 * @see com.mapbar.analyzelog.report.entity.LaBasicStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-24
 */
@Component
public interface ActiveStatMapper {

	/**
	 * 获取指定主键的记录
	 * 
	 * @param appid
	 * @param date
	 * @return List<LaBasicStat>
	 */
	@SelectProvider(type = ActiveStatSqlProvider.class, method = "selectByAppDate")
	@Results(value = {
			@Result(column = "date", jdbcType = JdbcType.DATE, property = "date", javaType = Date.class),
			@Result(column = "before_day", jdbcType = JdbcType.INTEGER, property = "before", javaType = Long.class),
			@Result(column = "active_user_count", jdbcType = JdbcType.INTEGER, property = "active_imei_size", javaType = Long.class) })
	public List<LaActiveStat> findByAppDate(@Param("appid") Long appid,
			@Param("date") Date date);
}