package com.mapbar.analyzelog.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.mapbar.analyzelog.report.entity.LaunchVO;

/**
 * <p>
 * 查询启动次数的数据
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.LocationVO
 * @author <a href="mailto:renzg@mapbar.com">renzg</a>
 * @date 2012-02-22
 */
@Component("launchMapper")
public interface LaunchMapper {

	@Select("SELECT date, sum(case when frequency='1' then launch_user_count else 0 end) as c1_2 , sum(case when frequency='2' then launch_user_count else 0 end) as c2_2 , sum(case when frequency='3' then  launch_user_count  else 0 end) as c3_3 ,sum(case when frequency='4' then  launch_user_count  else 0 end) as c4_4 ,sum(case when frequency='5' then  launch_user_count  else 0 end) as c5_5 , sum(case when frequency='6-9'  then  launch_user_count  else 0 end) as c6_9 , sum(case when frequency='10-19'  then  launch_user_count  else 0 end) as c10_19 , sum(case when frequency='20-49'  then  launch_user_count  else 0 end) as c20_49 , sum(case when frequency='50+'  then  launch_user_count  else 0 end) as c50_ FROM  la_launch_stat where appid=${appid}  group by date")
	public List<LaunchVO> select(@Param("appid") String appid);

	@Select("SELECT date, sum(case when frequency='1' then launch_user_count else 0 end) as c1_2 , sum(case when frequency='2' then launch_user_count else 0 end) as c2_2 , sum(case when frequency='3' then  launch_user_count  else 0 end) as c3_3 ,sum(case when frequency='4' then  launch_user_count  else 0 end) as c4_4 ,sum(case when frequency='5' then  launch_user_count  else 0 end) as c5_5 , sum(case when frequency='6-9'  then  launch_user_count  else 0 end) as c6_9 , sum(case when frequency='10-19'  then  launch_user_count  else 0 end) as c10_19 , sum(case when frequency='20-49'  then  launch_user_count  else 0 end) as c20_49 , sum(case when frequency='50+'  then  launch_user_count  else 0 end) as c50_ FROM  la_launch_stat where appid=${appid}  group by date having (date >='${date1}' and date<='${date2}')")
	public List<LaunchVO> selectByDay(@Param("appid") String appid, @Param("date1") String startDate,@Param("date2") String endDate);
	
}
