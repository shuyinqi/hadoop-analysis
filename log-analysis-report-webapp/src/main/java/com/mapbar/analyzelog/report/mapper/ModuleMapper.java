package com.mapbar.analyzelog.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.mapbar.analyzelog.report.entity.LaFeedAnswer;
import com.mapbar.analyzelog.report.entity.LaFeedBack;

/**
 * @（#）:ModuleMapper.java 
 * @description:  
 * @author:  sunjy  2012-7-11 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
@Component("ModuleMapper")
public interface ModuleMapper {
    /***
     * 查询用户反馈信息集合
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param collect  收藏
     * @return
     *
     */
	public List<LaFeedBack> getLaFeedBack(@Param("appid") String appid,@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("collect") String collect);
	/***
	 * 改变问题收藏状态
	 * @param fid     反馈问题id
	 * @param isCollect  是否收藏
	 */
	public void changeCollectStat(@Param("fid") String fid,@Param("isCollect") String isCollect);
	/**
	 * 更新状态
	 * @param fid
	 * @param flag
	 *
	 */
	public void changeFlagStat(@Param("fid") String fid,@Param("flag") String flag);
	/**
	 * 删除问题，在数据库中更改状态为停用，不进行物理删除
	 * @param fid
	 *
	 */
	public void stopFeedBack(@Param("fid") String fid);
	/**
	 * 添加一条回复
	 */
	public void anwserFeedBack(LaFeedAnswer laFeedAnswer);

	
	 /***
     * 查询用户反馈信息集合
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param collect  收藏
     * @return
     *
     */
	public List<LaFeedAnswer> getLaFeedAnswer(@Param("appid") String appid);

}
