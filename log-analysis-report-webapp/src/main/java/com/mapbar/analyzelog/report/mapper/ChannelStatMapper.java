/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.mapbar.analyzelog.report.entity.ChannlReptile;
import com.mapbar.analyzelog.report.entity.SummaryChannelStat;
import com.mapbar.analyzelog.report.provider.ChannelStatSqlProvider;

/**
 * <p>
 * 渠道统计的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.provider.ChannelStatSqlProvider
 * @see com.mapbar.analyzelog.report.entity.SummaryChannelStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-22
 */
@Component
public interface ChannelStatMapper {

	/**
	 * 获取指定应用、渠道类型的新用户统计
	 * 
	 * @param appid
	 * @param type
	 * @return List<SummaryChannelStat>
	 */
	@SelectProvider(type = ChannelStatSqlProvider.class, method = "selectByAppType4Visit")
	@Results(value = {
			@Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name", javaType = String.class),
			@Result(column = "imei_size0", jdbcType = JdbcType.INTEGER, property = "imei_size0", javaType = Long.class),
			@Result(column = "imei_size1", jdbcType = JdbcType.INTEGER, property = "imei_size1", javaType = Long.class),
			@Result(column = "imei_size2", jdbcType = JdbcType.INTEGER, property = "imei_size2", javaType = Long.class),
			@Result(column = "imei_size7", jdbcType = JdbcType.INTEGER, property = "imei_size7", javaType = Long.class),
			@Result(column = "name_imei_size", jdbcType = JdbcType.INTEGER, property = "name_imei_size", javaType = Long.class),
			@Result(column = "type_imei_size", jdbcType = JdbcType.INTEGER, property = "type_imei_size", javaType = Long.class) })
	public List<SummaryChannelStat> findByAppType4Visit(
			@Param("appid") Long appid, @Param("type") String type,@Param("toDate") String toDate,@Param("version") String version);

	/**
	 * 获取指定应用、渠道类型的新用户统计
	 * 
	 * @param appid
	 * @param type
	 * @return List<SummaryChannelStat>
	 */
	@SelectProvider(type = ChannelStatSqlProvider.class, method = "selectByAppType4VisitVersion")
	@Results(value = {
			@Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name", javaType = String.class),
			@Result(column = "imei_size0", jdbcType = JdbcType.INTEGER, property = "imei_size0", javaType = Long.class),
			@Result(column = "imei_size1", jdbcType = JdbcType.INTEGER, property = "imei_size1", javaType = Long.class),
			@Result(column = "imei_size2", jdbcType = JdbcType.INTEGER, property = "imei_size2", javaType = Long.class),
			@Result(column = "imei_size7", jdbcType = JdbcType.INTEGER, property = "imei_size7", javaType = Long.class),
			@Result(column = "name_imei_size", jdbcType = JdbcType.INTEGER, property = "name_imei_size", javaType = Long.class),
			@Result(column = "type_imei_size", jdbcType = JdbcType.INTEGER, property = "type_imei_size", javaType = Long.class) })
	public List<SummaryChannelStat> findByAppType4VisitVersion(
			@Param("appid") Long appid, @Param("type") String type,@Param("toDate") String toDate,@Param("version") String version);

	
	/**
	 * 获取指定应用、渠道类型的访问用户统计
	 * 
	 * @param appid
	 * @param type
	 * @return List<SummaryChannelStat>
	 */
	@SelectProvider(type = ChannelStatSqlProvider.class, method = "selectByAppType4New")
	@Results(value = {
			@Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name", javaType = String.class),
			@Result(column = "imei_size0", jdbcType = JdbcType.INTEGER, property = "imei_size0", javaType = Long.class),
			@Result(column = "imei_size1", jdbcType = JdbcType.INTEGER, property = "imei_size1", javaType = Long.class),
			@Result(column = "imei_size2", jdbcType = JdbcType.INTEGER, property = "imei_size2", javaType = Long.class),
			@Result(column = "imei_size7", jdbcType = JdbcType.INTEGER, property = "imei_size7", javaType = Long.class),
			@Result(column = "name_imei_size", jdbcType = JdbcType.INTEGER, property = "name_imei_size", javaType = Long.class),
			@Result(column = "type_imei_size", jdbcType = JdbcType.INTEGER, property = "type_imei_size", javaType = Long.class) })
	public List<SummaryChannelStat> findByAppType4New(
			@Param("appid") Long appid, @Param("type") String type,@Param("toDate") String toDate,@Param("version") String version);
	
	/**
	 * 获取指定应用、渠道类型的访问用户统计
	 * 
	 * @param appid
	 * @param type
	 * @return List<SummaryChannelStat>
	 */
	@SelectProvider(type = ChannelStatSqlProvider.class, method = "selectByAppType4NewVersion")
	@Results(value = {
			@Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name", javaType = String.class),
			@Result(column = "imei_size0", jdbcType = JdbcType.INTEGER, property = "imei_size0", javaType = Long.class),
			@Result(column = "imei_size1", jdbcType = JdbcType.INTEGER, property = "imei_size1", javaType = Long.class),
			@Result(column = "imei_size2", jdbcType = JdbcType.INTEGER, property = "imei_size2", javaType = Long.class),
			@Result(column = "imei_size7", jdbcType = JdbcType.INTEGER, property = "imei_size7", javaType = Long.class),
			@Result(column = "name_imei_size", jdbcType = JdbcType.INTEGER, property = "name_imei_size", javaType = Long.class),
			@Result(column = "type_imei_size", jdbcType = JdbcType.INTEGER, property = "type_imei_size", javaType = Long.class) })
	public List<SummaryChannelStat> findByAppType4NewVersion(
			@Param("appid") Long appid, @Param("type") String type,@Param("toDate") String toDate,@Param("version") String version);
	/**
	 * 获取指定应用、渠道类型的访问用户统计
	 * 
	 * @param appid
	 * @param type
	 * @return List<SummaryChannelStat>
	 */
	@SelectProvider(type = ChannelStatSqlProvider.class, method = "selectByAppReptileNew")
	@Results(value = {
			@Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name", javaType = String.class),
			@Result(column = "imei_size0", jdbcType = JdbcType.INTEGER, property = "imei_size0", javaType = Long.class),
			@Result(column = "imei_size1", jdbcType = JdbcType.INTEGER, property = "imei_size1", javaType = Long.class),
			@Result(column = "imei_size2", jdbcType = JdbcType.INTEGER, property = "imei_size2", javaType = Long.class),
			@Result(column = "imei_size7", jdbcType = JdbcType.INTEGER, property = "imei_size7", javaType = Long.class),
			@Result(column = "name_imei_size", jdbcType = JdbcType.INTEGER, property = "name_imei_size", javaType = Long.class),
			@Result(column = "type_imei_size", jdbcType = JdbcType.INTEGER, property = "type_imei_size", javaType = Long.class),
			@Result(column = "addCount", jdbcType = JdbcType.INTEGER, property = "addCount", javaType = Long.class)})
	public List<SummaryChannelStat> findReptileByAppNew(
			@Param("appid") Long appid, @Param("type") String type,@Param("toDate") String toDate,@Param("version") String version);


	
	/**
	 * 获取指定应用、渠道类型的访问用户统计
	 * 
	 * @param appid
	 * @param type
	 * @return List<SummaryChannelStat>
	 */
	@SelectProvider(type = ChannelStatSqlProvider.class, method = "selectByAppReptileNewVersion")
	@Results(value = {
			@Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name", javaType = String.class),
			@Result(column = "imei_size0", jdbcType = JdbcType.INTEGER, property = "imei_size0", javaType = Long.class),
			@Result(column = "imei_size1", jdbcType = JdbcType.INTEGER, property = "imei_size1", javaType = Long.class),
			@Result(column = "imei_size2", jdbcType = JdbcType.INTEGER, property = "imei_size2", javaType = Long.class),
			@Result(column = "imei_size7", jdbcType = JdbcType.INTEGER, property = "imei_size7", javaType = Long.class),
			@Result(column = "name_imei_size", jdbcType = JdbcType.INTEGER, property = "name_imei_size", javaType = Long.class),
			@Result(column = "type_imei_size", jdbcType = JdbcType.INTEGER, property = "type_imei_size", javaType = Long.class),
			@Result(column = "addCount", jdbcType = JdbcType.INTEGER, property = "addCount", javaType = Long.class)})
	public List<SummaryChannelStat> findReptileByAppNewVersion(
			@Param("appid") Long appid, @Param("type") String type,@Param("toDate") String toDate,@Param("version") String version);

}