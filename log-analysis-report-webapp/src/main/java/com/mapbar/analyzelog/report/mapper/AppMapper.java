/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.mapbar.analyzelog.report.entity.LaApp;
import com.mapbar.analyzelog.report.provider.AppSqlProvider;

/**
 * <p>
 * 应用信息的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.provider.AppSqlProvider
 * @see com.mapbar.analyzelog.report.entity.LaApp
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-13
 */
@Component
public interface AppMapper {

	/**
	 * 获取指定主键的记录
	 * 
	 * @param id
	 * @return LaApp
	 */
	@SelectProvider(type = AppSqlProvider.class, method = "selectByPK")
	@Results(value = {
			@Result(column = "id", jdbcType = JdbcType.INTEGER, property = "id", javaType = Long.class, id = true),
			@Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name", javaType = String.class),
			@Result(column = "memo", jdbcType = JdbcType.VARCHAR, property = "memo", javaType = String.class),
			@Result(column = "addtime", jdbcType = JdbcType.TIMESTAMP, property = "addtime", javaType = Timestamp.class),
			@Result(column = "flag", jdbcType = JdbcType.VARCHAR, property = "flag", javaType = String.class) })
	public LaApp findByPK(@Param("id") Long id);

	/**
	 * 获取全部记录集
	 * 
	 * @return List<LaApp>
	 */
	@SelectProvider(type = AppSqlProvider.class, method = "selectAll")
	@Results(value = {
			@Result(column = "id", jdbcType = JdbcType.INTEGER, property = "id", javaType = Long.class, id = true),
			@Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name", javaType = String.class),
			@Result(column = "memo", jdbcType = JdbcType.VARCHAR, property = "memo", javaType = String.class),
			@Result(column = "addtime", jdbcType = JdbcType.TIMESTAMP, property = "addtime", javaType = Timestamp.class),
			@Result(column = "flag", jdbcType = JdbcType.VARCHAR, property = "flag", javaType = String.class) })
	public List<LaApp> findAll(@Param("id") Long id);
}