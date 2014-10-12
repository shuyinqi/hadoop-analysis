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
import com.mapbar.analyzelog.report.entity.ResolutionVO;

/**
 * <p>
 * 应用信息的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.ResolutionVO
 * @author <a href="mailto:lijie@mapbar.com">lijie</a>
 * @date 2012-02-20
 */
@Component("resolutionMapper")
public interface ResolutionMapper {
	public List<ResolutionVO> select(@Param("appid") String appid);

	public List<ResolutionVO> selectByDay(@Param("date1") String startDate,@Param("date2") String endDate,@Param("appid") String appid);
	
	public List<ResolutionVO> selectnews(@Param("appid") String appid);
	
	public List<ResolutionVO> selectNewsByDay(@Param("date1") String startDate,@Param("date2") String endDate,@Param("appid") String appid);

}