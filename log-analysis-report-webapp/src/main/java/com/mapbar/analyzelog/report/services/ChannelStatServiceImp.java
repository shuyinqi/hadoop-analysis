/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapbar.analyzelog.report.entity.SummaryChannelStat;
import com.mapbar.analyzelog.report.mapper.ChannelStatMapper;
import com.mapbar.analyzelog.report.provider.ChannelStatSqlProvider;

/**
 * <p>
 * 处理渠道统计业务逻辑的实现类
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.ChannelStatMapper
 * @see com.mapbar.analyzelog.report.provider.ChannelStatSqlProvider
 * @see com.mapbar.analyzelog.report.entity.LaChannelStat
 * @see com.mapbar.analyzelog.report.entity.SummaryChannelStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-22
 */
@Service("channelStatService")
public class ChannelStatServiceImp implements ChannelStatService {

	private static Log log = LogFactory.getLog(ChannelStatServiceImp.class);

	@Autowired
	private ChannelStatMapper channelStatMapper;

	public List<SummaryChannelStat> findByApp4MarketNew(Long appid,String toDate,String version) {
		List<SummaryChannelStat> st;
		if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
		  st = channelStatMapper.findReptileByAppNew(appid,ChannelStatSqlProvider.MARKET_TYPE,toDate,version);
		}else{
		  st = channelStatMapper.findReptileByAppNewVersion(appid, ChannelStatSqlProvider.MARKET_TYPE, toDate, version);
		}
		return st;
	}

	public List<SummaryChannelStat> findByApp4MarketVisit(Long appid,String toDate,String version) {
		List<SummaryChannelStat> st;
		if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
		  st = channelStatMapper.findByAppType4Visit(appid,ChannelStatSqlProvider.MARKET_TYPE,toDate,version);
		}else{
		  st = channelStatMapper.findByAppType4VisitVersion(appid,ChannelStatSqlProvider.MARKET_TYPE,toDate,version);
		}
		return st;
	}

	public List<SummaryChannelStat> findByApp4InstalledNew(Long appid,String toDate,String version) {
		List<SummaryChannelStat> st;
		if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
		  st = channelStatMapper.findByAppType4New(appid,ChannelStatSqlProvider.INSTALLED_TYPE,toDate,version);
		}else{
		  st = channelStatMapper.findByAppType4NewVersion(appid,ChannelStatSqlProvider.INSTALLED_TYPE,toDate,version);
		}
		return st;
	}

	public List<SummaryChannelStat> findByApp4InstalledVisit(Long appid,String toDate,String version) {
		List<SummaryChannelStat> st;
		if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
		  st = channelStatMapper.findByAppType4Visit(appid,ChannelStatSqlProvider.INSTALLED_TYPE,toDate,version);
		}else{
		  st = channelStatMapper.findByAppType4VisitVersion(appid,ChannelStatSqlProvider.INSTALLED_TYPE,toDate,version);
		}
		return st;
	}
}