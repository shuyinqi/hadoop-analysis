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

import com.mapbar.analyzelog.report.entity.LaSectionStat;
import com.mapbar.analyzelog.report.provider.SectionStatSqlProvider;

/**
 * <p>
 * 分段统计的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.provider.SectionStatSqlProvider
 * @see com.mapbar.analyzelog.report.entity.LaSectionStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-15
 */
@Component
public interface SectionStatMapper {

	/**
	 * 获取指定主键的记录
	 * 
	 * @param appid
	 * @param date
	 * @return List<LaSectionStat>
	 */
	@SelectProvider(type = SectionStatSqlProvider.class, method = "selectByAppDate")
	@Results(value = {
			@Result(column = "appid", jdbcType = JdbcType.INTEGER, property = "app.id", javaType = Long.class),
			@Result(column = "date", jdbcType = JdbcType.DATE, property = "date", javaType = Date.class),
			@Result(column = "new_imei_size", jdbcType = JdbcType.INTEGER, property = "new_imei_size", javaType = Long.class),
			@Result(column = "visit_imei_size", jdbcType = JdbcType.INTEGER, property = "visit_imei_size", javaType = Long.class),
			@Result(column = "visit_size", jdbcType = JdbcType.INTEGER, property = "visit_size", javaType = Long.class),
			@Result(column = "section", jdbcType = JdbcType.VARCHAR, property = "section", javaType = String.class) })
	public List<LaSectionStat> findByAppDate(@Param("appid") Long appid,
			@Param("date") Date date);

	@SelectProvider(type = SectionStatSqlProvider.class, method = "selectAllSize")
	@Results(value = {
			@Result(column = "new_imei_size", jdbcType = JdbcType.INTEGER, property = "new_imei_size", javaType = Long.class),
			@Result(column = "visit_size", jdbcType = JdbcType.INTEGER, property = "visit_size", javaType = Long.class) })
	public LaSectionStat findAllSizeByApp(@Param("appid") Long appid);

}