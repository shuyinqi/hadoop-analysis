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

import com.mapbar.analyzelog.report.entity.LocationVO;

/**
 * <p>
 * 查询地域的数据
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.LocationVO
 * @author <a href="mailto:renzg@mapbar.com">renzg</a>
 * @date 2012-02-22
 */
@Component("locationMapper")
public interface LocationMapper {
	@Select("select city,newcount,visitcount from( select city,sum(new_user_counter) as newcount,sum(visit_user_counter) as visitcount FROM la_location_stat where appid=${appid}  group by city)a order by visitcount desc ")
	public List<LocationVO> select(@Param("appid") String appid);

	@Select("select city,newcount,visitcount from( select city,sum(new_user_counter) as newcount,sum(visit_user_counter) as visitcount FROM la_location_stat where appid=${appid} and date >='${date1}' and date<='${date2}' group by city)a order by visitcount desc ")
	public List<LocationVO> selectByDay(@Param("appid") String appid, @Param("date1") String startDate,@Param("date2") String endDate);
	
	@Select("select city,newcount,visitcount from( select city,sum(new_user_counter) as newcount,sum(visit_user_counter) as visitcount FROM la_location_stat where appid=${appid}  group by city)a order by newcount desc ")
	public List<LocationVO> selectnews(@Param("appid") String appid);

	@Select("select city,newcount,visitcount from( select city,sum(new_user_counter) as newcount,sum(visit_user_counter) as visitcount FROM la_location_stat where appid=${appid} and date >='${date1}' and date<='${date2}' group by city)a order by newcount desc ")
	public List<LocationVO> selectNewsByDay(@Param("appid") String appid, @Param("date1") String startDate,@Param("date2") String endDate);

}