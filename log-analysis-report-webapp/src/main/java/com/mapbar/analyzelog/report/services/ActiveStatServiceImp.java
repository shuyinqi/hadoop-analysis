/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.services;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapbar.analyzelog.report.entity.LaActiveStat;
import com.mapbar.analyzelog.report.entity.LaBasicStat;
import com.mapbar.analyzelog.report.entity.Trend;
import com.mapbar.analyzelog.report.mapper.ActiveStatMapper;
import com.mapbar.analyzelog.report.mapper.BasicStatMapper;
import com.mapbar.analyzelog.report.utils.DateUtil;

/**
 * <p>
 * 处理基本统计业务逻辑的实现类
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.BasicStatMapper
 * @see com.mapbar.analyzelog.report.entity.LaBasicStat
 * @see com.mapbar.analyzelog.report.entity.Trend
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-15
 */
@Service("activeStatService")
public class ActiveStatServiceImp implements ActiveStatService {

	private static Log log = LogFactory.getLog(ActiveStatServiceImp.class);

	@Autowired
	private ActiveStatMapper activeStatMapper;

	public List<LaActiveStat> findByAppDate(Long appid, Date date) {
		List<LaActiveStat> results = activeStatMapper
				.findByAppDate(appid, date);
		return results != null && results.size() > 0 ? results : null;
	}
}