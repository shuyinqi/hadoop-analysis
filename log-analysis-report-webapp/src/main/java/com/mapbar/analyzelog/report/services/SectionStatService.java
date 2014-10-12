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
import com.mapbar.analyzelog.report.entity.LaSectionStat;

/**
 * <p>
 * 处理分段统计业务逻辑的接口
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.LaSectionStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-15
 */
public interface SectionStatService {

	/**
	 * 获取应用在指定日期中的分段统计
	 * 
	 * @param appid
	 * @param date
	 * @return List<LaSectionStat>
	 */
	public List<LaSectionStat> findByAppDate(Long appid, Date date);

	public LaSectionStat findAllSizeByApp(Long appid);

	/**
	 * 抽取统计数据中的分段新用户量统计
	 * 
	 * @param stats
	 * @return String
	 */
	public String parseNewImeiByStats(List<LaSectionStat> stats);

	/**
	 * 抽取统计数据中的分段访问量统计
	 * 
	 * @param stats
	 * @return String
	 */
	public String parseVisitByStats(List<LaSectionStat> stats);
	
}