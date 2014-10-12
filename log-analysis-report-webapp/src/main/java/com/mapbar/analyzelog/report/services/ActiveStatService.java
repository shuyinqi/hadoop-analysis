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

import com.mapbar.analyzelog.report.entity.LaActiveStat;

/**
 * <p>
 * 处理基本统计业务逻辑的接口
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.LaActiveStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-24
 */
public interface ActiveStatService {

	/**
	 * 获取指定应用指定日期间的活跃统计
	 * 
	 * @param appid
	 * @param date
	 * @return List<LaActiveStat>
	 */
	public List<LaActiveStat> findByAppDate(Long appid, Date date);

}