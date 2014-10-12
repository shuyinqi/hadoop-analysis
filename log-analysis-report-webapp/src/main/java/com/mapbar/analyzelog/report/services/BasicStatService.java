/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.services;

import java.sql.Date;
import java.util.List;

import com.mapbar.analyzelog.report.entity.LaBasicStat;
import com.mapbar.analyzelog.report.entity.Trend;

/**
 * <p>
 * 处理基本统计业务逻辑的接口
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.LaBasicStat
 * @see com.mapbar.analyzelog.report.entity.Trend
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-15
 */
public interface BasicStatService {

	/**
	 * 获取指定应用一定日期间的基本统计
	 * 
	 * @param appid
	 * @return List<LaBasicStat>
	 */
	public List<LaBasicStat> findByApp4Basic(Long appid);

	/**
	 * 获取指定日期区间内的趋势统计，日期字符串均为10
	 * 
	 * @param appid
	 * @param fromdate
	 * @param todate
	 * @return Trend
	 */
	public Trend findByApp4Trend(Long appid, String fromdate, String todate);

	/**
	 * 获取指定应用指定日期间的基本统计
	 * 
	 * @param appid
	 * @param from
	 * @param to
	 * @param desc
	 * @return List<LaBasicStat>
	 */
	public List<LaBasicStat> findByAppDate(Long appid, Date from, Date to,
			Boolean desc);

	/**
	 * 获取指定应用的基本统计
	 * 
	 * @param appid
	 * @return List<LaBasicStat>
	 */
	public List<LaBasicStat> findByApp(Long appid);
	
	public List<LaBasicStat> selectCountByApp(Long appid,String fromdate, String todate);
}