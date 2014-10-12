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
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.mapbar.analyzelog.report.entity.AccessVO;

/**
 * <p>
 * 应用信息的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.AccessVO
 * @author <a href="mailto:lijie@mapbar.com">lijie</a>
 * @date 2012-02-20
 */
@Component("accessMapper")
public interface AccessMapper {
	@Select("select access as device,sum(new_user_count) as newcount,sum(launch_user_count) as launchcount FROM la_access_stat where appid=${appid} group by access order by launchcount")
	public List<AccessVO> select(@Param("appid") String appid);
	
	@Select("select access as device,sum(new_user_count) as newcount,sum(launch_user_count) as launchcount FROM la_access_stat where appid=${appid} and date >='${date1}' and date<='${date2}' group by access order by launchcount desc")
	public List<AccessVO> selectByDay(@Param("date1") String startDate,@Param("date2") String endDate,@Param("appid") String appid);
}