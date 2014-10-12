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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapbar.analyzelog.report.entity.LaSectionStat;
import com.mapbar.analyzelog.report.mapper.SectionStatMapper;

/**
 * <p>
 * 处理分段统计业务逻辑的实现类
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.SectionStatMapper
 * @see com.mapbar.analyzelog.report.entity.LaSectionStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-15
 */
@Service("sectionStatService")
public class SectionStatServiceImp implements SectionStatService {

	private static Log log = LogFactory.getLog(SectionStatServiceImp.class);

	@Autowired
	private SectionStatMapper sectionStatMapper;

	public List<LaSectionStat> findByAppDate(Long appid, Date date) {
		return sectionStatMapper.findByAppDate(appid, date);
	}

	public LaSectionStat findAllSizeByApp(Long appid) {
		return sectionStatMapper.findAllSizeByApp(appid);
	}

	public String parseNewImeiByStats(List<LaSectionStat> stats) {
		long[] newImeis = new long[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (LaSectionStat stat : stats)
			newImeis[Integer.parseInt(Integer.parseInt(stat.getSection())>=10?stat.getSection().substring(0, 2):stat.getSection().substring(0, 1))] = stat
					.getNew_imei_size();
		StringBuilder sb = new StringBuilder();
		for (long newImei : newImeis)
			sb.append(newImei).append(",");
		return sb.length() < 1 ? "" : sb.substring(0, sb.length() - 1);
	}

	public String parseVisitByStats(List<LaSectionStat> stats) {
		long[] newImeis = new long[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (LaSectionStat stat : stats)
			newImeis[Integer.parseInt(Integer.parseInt(stat.getSection())>=10?stat.getSection().substring(0, 2):stat.getSection().substring(0, 1))] = stat
					.getVisit_size();
		StringBuilder sb = new StringBuilder();
		for (long newImei : newImeis)
			sb.append(newImei).append(",");
		return sb.length() < 1 ? "" : sb.substring(0, sb.length() - 1);
	}
}