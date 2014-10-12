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

import com.mapbar.analyzelog.report.entity.LaBasicStat;
import com.mapbar.analyzelog.report.provider.BasicStatSqlProvider;

/**
 * <p>
 * 基本统计的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.provider.BasicStatSqlProvider
 * @see com.mapbar.analyzelog.report.entity.LaBasicStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-15
 */
@Component
public interface BasicStatMapper {

	/**
	 * 获取指定主键的记录
	 * 
	 * @param appid
	 * @param from
	 * @param to
	 * @param desc
	 * @return List<LaBasicStat>
	 */
	@SelectProvider(type = BasicStatSqlProvider.class, method = "selectByAppDate")
	@Results(value = {
			@Result(column = "appid", jdbcType = JdbcType.INTEGER, property = "app.id", javaType = Long.class),
			@Result(column = "date", jdbcType = JdbcType.DATE, property = "date", javaType = Date.class),
			@Result(column = "imei_size", jdbcType = JdbcType.INTEGER, property = "imei_size", javaType = Long.class),
			@Result(column = "new_imei_size", jdbcType = JdbcType.INTEGER, property = "new_imei_size", javaType = Long.class),
			@Result(column = "visit_imei_size", jdbcType = JdbcType.INTEGER, property = "visit_imei_size", javaType = Long.class),
			@Result(column = "visit_size", jdbcType = JdbcType.INTEGER, property = "visit_size", javaType = Long.class),
			@Result(column = "upgrade_imei_size", jdbcType = JdbcType.INTEGER, property = "upgrade_imei_size", javaType = Long.class),
			@Result(column = "average_duration", jdbcType = JdbcType.INTEGER, property = "average_duration", javaType = Long.class) })
	public List<LaBasicStat> findByAppDate(@Param("appid") Long appid,
			@Param("from") Date from, @Param("to") Date to,
			@Param("desc") Boolean desc);
	
	@SelectProvider(type = BasicStatSqlProvider.class, method = "selectByAppDate2")
	@Results(value = {
			@Result(column = "appid", jdbcType = JdbcType.INTEGER, property = "app.id", javaType = Long.class),
			@Result(column = "date", jdbcType = JdbcType.DATE, property = "date", javaType = Date.class),
			@Result(column = "imei_size", jdbcType = JdbcType.INTEGER, property = "imei_size", javaType = Long.class),
			@Result(column = "new_imei_size", jdbcType = JdbcType.INTEGER, property = "new_imei_size", javaType = Long.class),
			@Result(column = "visit_imei_size", jdbcType = JdbcType.INTEGER, property = "visit_imei_size", javaType = Long.class),
			@Result(column = "visit_size", jdbcType = JdbcType.INTEGER, property = "visit_size", javaType = Long.class),
			@Result(column = "upgrade_imei_size", jdbcType = JdbcType.INTEGER, property = "upgrade_imei_size", javaType = Long.class),
			@Result(column = "average_duration", jdbcType = JdbcType.INTEGER, property = "average_duration", javaType = Long.class) })
	public List<LaBasicStat> findByAppDate2(@Param("appid") Long appid,
			@Param("from") Date from, @Param("to") Date to,
			@Param("desc") Boolean desc);

	/**
	 * 获取全部记录集
	 * 
	 * @return List<LaBasicStat>
	 */
	@SelectProvider(type = BasicStatSqlProvider.class, method = "selectByApp")
	@Results(value = {
			@Result(column = "appid", jdbcType = JdbcType.INTEGER, property = "app.id", javaType = Long.class),
			@Result(column = "date", jdbcType = JdbcType.DATE, property = "date", javaType = Date.class),
			@Result(column = "imei_size", jdbcType = JdbcType.INTEGER, property = "imei_size", javaType = Long.class),
			@Result(column = "new_imei_size", jdbcType = JdbcType.INTEGER, property = "new_imei_size", javaType = Long.class),
			@Result(column = "visit_imei_size", jdbcType = JdbcType.INTEGER, property = "visit_imei_size", javaType = Long.class),
			@Result(column = "visit_size", jdbcType = JdbcType.INTEGER, property = "visit_size", javaType = Long.class),
			@Result(column = "upgrade_imei_size", jdbcType = JdbcType.INTEGER, property = "upgrade_imei_size", javaType = Long.class),
			@Result(column = "average_duration", jdbcType = JdbcType.INTEGER, property = "average_duration", javaType = Long.class) })
	public List<LaBasicStat> findByApp(@Param("appid") Long appid,
			@Param("desc") Boolean desc);
	
	@SelectProvider(type = BasicStatSqlProvider.class, method = "selectCountByApp")
	@Results(value = {
			@Result(column = "appid", jdbcType = JdbcType.INTEGER, property = "app.id", javaType = Long.class),
			@Result(column = "date", jdbcType = JdbcType.DATE, property = "date", javaType = Date.class),
			@Result(column = "imei_size", jdbcType = JdbcType.INTEGER, property = "imei_size", javaType = Long.class),
			@Result(column = "new_imei_size", jdbcType = JdbcType.INTEGER, property = "new_imei_size", javaType = Long.class),
			@Result(column = "visit_imei_size", jdbcType = JdbcType.INTEGER, property = "visit_imei_size", javaType = Long.class),
			@Result(column = "visit_size", jdbcType = JdbcType.INTEGER, property = "visit_size", javaType = Long.class),
			@Result(column = "upgrade_imei_size", jdbcType = JdbcType.INTEGER, property = "upgrade_imei_size", javaType = Long.class),
			@Result(column = "average_duration", jdbcType = JdbcType.INTEGER, property = "average_duration", javaType = Long.class) })
	public List<LaBasicStat> selectCountByApp(@Param("appid") Long appid,
			@Param("from") Date from, @Param("to") Date to,
			@Param("desc") Boolean desc);
}