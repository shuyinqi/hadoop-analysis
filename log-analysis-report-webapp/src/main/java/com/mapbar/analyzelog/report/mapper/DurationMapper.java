package com.mapbar.analyzelog.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.mapbar.analyzelog.report.entity.DurationVO;
/**
 * 更改为xml的形式，方便使用动态sql
 * @（#）:DurationMapper.java 
 * @author:  sunjy  2012-9-11 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
@Component("durationMapper")
public interface DurationMapper {
	public List<DurationVO> select(@Param("appid") String appid);
	/***
	 * 获取数据
	 * @param tableName 单次，单日，单周的表名称。
	 * @param appid 	id
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @param version 版本
	 * @param channelType 渠道类型
	 * @param channelName 渠道名称
	 * @return
	 *
	 */
	public List<DurationVO> selectByDay(@Param("tableName") String tableName,@Param("appid") String appid, @Param("date1") String startDate,@Param("date2") String endDate,@Param("version") String version,@Param("channelType") String channelType,@Param("channelName") String channelName);
	/***
	 * 查渠道
	 * @param appid
	 * @param startDate
	 * @param endDate
	 * @return
	 *
	 */
	public List<String> selectDurationByChannel(@Param("appid") String appid);
	/***
	 * 查询版本
	 * @param appid
	 * @param startDate
	 * @param endDate
	 * @return
	 *
	 */
	public List<String> selectDurationByVersion(@Param("appid") String appid);
	/***
	 * 通过版本和类型查询
	 * @param appid
	 * @param version
	 * @param channelType
	 * @return
	 */
	public List<String> selectDurationCondition(@Param("appid") String appid,@Param("version") String version,@Param("channelType") String channelType);
		
	
	
}
