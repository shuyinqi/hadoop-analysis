/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.services;

import java.util.Date;
import java.util.List;

import com.mapbar.analyzelog.report.entity.ChannlReptile;
import com.mapbar.analyzelog.report.entity.SummaryChannelStat;

/**
 * <p>
 * 处理渠道统计业务逻辑的接口
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.LaChannelStat
 * @see com.mapbar.analyzelog.report.entity.SummaryChannelStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-22
 */
public interface ChannelStatService {

	/**
	 * 获取指定应用的市场新用户量统计
	 * 
	 * @param appid
	 * @return List<SummaryChannelStat>
	 */
	public List<SummaryChannelStat> findByApp4MarketNew(Long appid,String toDate,String version);

	/**
	 * 获取指定应用的市场访问用户量统计
	 * 
	 * @param appid
	 * @return List<SummaryChannelStat>
	 */
	public List<SummaryChannelStat> findByApp4MarketVisit(Long appid,String toDate,String version);

	/**
	 * 获取指定应用的预装新用户量统计
	 * 
	 * @param appid
	 * @return List<SummaryChannelStat>
	 */
	public List<SummaryChannelStat> findByApp4InstalledNew(Long appid,String toDate,String version);

	/**
	 * 获取指定应用的预装访问用户量统计
	 * 
	 * @param appid
	 * @return List<SummaryChannelStat>
	 */
	public List<SummaryChannelStat> findByApp4InstalledVisit(Long appid,String toDate,String version);
}