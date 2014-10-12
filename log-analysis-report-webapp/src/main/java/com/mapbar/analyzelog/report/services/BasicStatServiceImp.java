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

import com.mapbar.analyzelog.report.entity.LaBasicStat;
import com.mapbar.analyzelog.report.entity.Trend;
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
@Service("basicStatService")
public class BasicStatServiceImp implements BasicStatService {

	private static Log log = LogFactory.getLog(BasicStatServiceImp.class);

	@Autowired
	private BasicStatMapper basicStatMapper;

	public List<LaBasicStat> findByApp4Basic(Long appid) {
		return findByAppDate2(appid, DateUtil.getStepDay(-1),
				DateUtil.getStepDay(0), true);
	}

	public Trend findByApp4Trend(Long appid, String fromdate, String todate) {
		List<LaBasicStat> results = null;
		try {
			results = findByAppDate(appid, DateUtil.parse10(fromdate),
					DateUtil.parse10(todate), false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return results != null && results.size() > 0 ? new Trend(results)
				: null;
	}

	public List<LaBasicStat> findByAppDate2(Long appid, Date from, Date to,
			Boolean desc) {
		List<LaBasicStat> results = basicStatMapper.findByAppDate2(appid, from,
				to, desc);
		return results != null && results.size() > 0 ? results : null;
	}
	
	public List<LaBasicStat> findByAppDate(Long appid, Date from, Date to,
			Boolean desc) {
		List<LaBasicStat> results = basicStatMapper.findByAppDate(appid, from,
				to, desc);
		return results != null && results.size() > 0 ? results : null;
	}

	public List<LaBasicStat> findByApp(Long appid) {
		return basicStatMapper.findByApp(appid, true);
	}

	@Override
	public List<LaBasicStat> selectCountByApp(Long appid,String fromdate, String todate) {
		List<LaBasicStat> results= null;
		try {
			results = basicStatMapper.selectCountByApp(appid,DateUtil.parse10(fromdate),
					DateUtil.parse10(todate), false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results != null && results.size() > 0 ? results : null;
	}
}