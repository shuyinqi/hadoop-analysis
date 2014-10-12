/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.mapbar.analyzelog.report.entity.OSVO;

/**
 * <p>
 * 应用信息的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.OSVO
 * @author <a href="mailto:lijie@mapbar.com">lijie</a>
 * @date 2012-02-20
 */
@Component("osMapper")
public interface OSMapper {
	public List<OSVO> select(@Param("appid") String appid);

	public List<OSVO> selectByDay(@Param("date1") String startDate,@Param("date2") String endDate,@Param("appid") String appid);
	
	public List<OSVO> selectnews(@Param("appid") String appid);
	
	public List<OSVO> selectNewsByDay(@Param("date1") String startDate,@Param("date2") String endDate,@Param("appid") String appid);
}