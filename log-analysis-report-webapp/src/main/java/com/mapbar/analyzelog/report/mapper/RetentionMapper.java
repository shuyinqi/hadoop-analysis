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

import com.mapbar.analyzelog.report.entity.RetentionDaysVO;
import com.mapbar.analyzelog.report.entity.RetentionVO;

/**
 * <p>
 * 查询回访用户的数据
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.LocationVO
 * @author <a href="mailto:renzg@mapbar.com">renzg</a>
 * @date 2012-02-22
 */
@Component("retentionMapper")
public interface RetentionMapper {
	@Select("select s.date as date, s.c7 as visitCount_7, s.c14 as visitCount_14, s.c30 as visitCount_30, s.c60 as visitCount_60, s.c90 as visitCount_90,t.newCounte as addCount from (SELECT appid,date, sum(case when before_day=7 then visit_user_count else 0 end ) as c7,sum(case when before_day=14 then visit_user_count else 0 end )  as c14 ,sum(case when before_day=30 then visit_user_count else 0 end )  as c30 ,sum(case when before_day=60 then visit_user_count else 0 end )  as c60 ,sum(case when before_day=90 then visit_user_count else 0 end )  as c90  FROM la_retention_stat where appid=${appid} group by date ) s left join (SELECT appid, date, sum(case when new_imei_size=null then 0 else new_imei_size  end) as newCounte  FROM la_section_stat where appid=${appid} group by date ) t on s.date=t.date")
	public List<RetentionVO> select(@Param("appid") String appid);

	@Select("select s.date as date, s.c7 as visitCount_7, s.c14 as visitCount_14, s.c30 as visitCount_30, s.c60 as visitCount_60, s.c90 as visitCount_90,t.newCounte as addCount from (SELECT appid,date, sum(case when before_day=7 then visit_user_count else 0 end ) as c7,sum(case when before_day=14 then visit_user_count else 0 end )  as c14 ,sum(case when before_day=30 then visit_user_count else 0 end )  as c30 ,sum(case when before_day=60 then visit_user_count else 0 end )  as c60 ,sum(case when before_day=90 then visit_user_count else 0 end )  as c90  FROM la_retention_stat where appid=${appid} group by date ) s left join (SELECT appid, date, sum(case when new_imei_size=null then 0 else new_imei_size  end) as newCounte  FROM la_section_stat where appid=${appid} group by date ) t on s.date=t.date where s.date >='${date1}' and s.date<='${date2}'")
	public List<RetentionVO> selectByDay(@Param("appid") String appid, @Param("date1") String startDate,@Param("date2") String endDate);
	
	@Select("select  max(visitCount_7) as visitCount_7,max(visitCount_14) as visitCount_14 ,max(visitCount_21) as visitCount_21,max(visitCount_28) as visitCount_28,max(visitCount_35) as visitCount_35,max(c42) as visitCount_42,max(c49) as visitCount_49,max(c56) as visitCount_56,(select sum(newCounte) from (SELECT sum(case when new_imei_size=null then 0 else new_imei_size  end) as newCounte  FROM la_section_stat  where appid=${appid} and date >='${date1}' and date<='${date2}' group by date) b)as addCount from (select s.date as date, s.c7 as visitCount_7, s.c14 as visitCount_14, s.c21 as visitCount_21, s.c28 as visitCount_28, s.c35 as visitCount_35,s.c42,s.c49,s.c56 from (SELECT appid,date,max(case when before_day=7 then visit_user_count else 0 end ) as c7,max(case when before_day=14 then visit_user_count else 0 end )  as c14 ,max(case when before_day=21 then visit_user_count else 0 end )  as c21 ,max(case when before_day=28 then visit_user_count else 0 end )  as c28 ,max(case when before_day=35 then visit_user_count else 0 end )  as c35, max(case when before_day=42 then visit_user_count else 0 end )  as c42,max(case when before_day=49 then visit_user_count else 0 end )  as c49,max(case when before_day=56 then visit_user_count else 0 end )  as c56   FROM la_subsistence_stat where appid=${appid} group by date ) s  where s.date >='${date1}' and s.date<='${date2}' ) c")
	public RetentionDaysVO selectByDays(@Param("appid") String appid, @Param("date1") String startDate,@Param("date2") String endDate);
	
//	@Select("select  max(visitCount_7) as visitCount_7,max(visitCount_14) as visitCount_14 ,max(visitCount_21) as visitCount_21,max(visitCount_28) as visitCount_28,max(visitCount_35) as visitCount_35,max(c42) as visitCount_42,max(c49) as visitCount_49,max(c56) as visitCount_56,(select sum(newCounte) from (SELECT sum(case when new_user_count=null then 0 else new_user_count  end) as newCounte  FROM la_channel_stat  where appid=${appid} and type='market' and name='${channel_name}' and date >='${date1}' and date<='${date2}' group by date) b)as addCount from (select s.date as date, s.c7 as visitCount_7, s.c14 as visitCount_14, s.c21 as visitCount_21, s.c28 as visitCount_28, s.c35 as visitCount_35,s.c42,s.c49,s.c56 from (SELECT appid,date,max(case when before_day=7 then visit_user_count else 0 end ) as c7,max(case when before_day=14 then visit_user_count else 0 end )  as c14 ,max(case when before_day=21 then visit_user_count else 0 end )  as c21 ,max(case when before_day=28 then visit_user_count else 0 end )  as c28 ,max(case when before_day=35 then visit_user_count else 0 end )  as c35, max(case when before_day=42 then visit_user_count else 0 end )  as c42,max(case when before_day=49 then visit_user_count else 0 end )  as c49,max(case when before_day=56 then visit_user_count else 0 end )  as c56   FROM la_subsistence_channel_stat where appid=${appid} and channel_name='${channel_name}' group by date ) s  where s.date >='${date1}' and s.date<='${date2}' ) c")
//	public RetentionDaysVO selectByChannelNameDays(@Param("appid") String appid, @Param("date1") String startDate,@Param("date2") String endDate,@Param("channel_name") String channel_name);
	@Select("select  max(visitCount_7) as visitCount_7,max(visitCount_14) as visitCount_14 ,max(visitCount_21) as visitCount_21,max(visitCount_28) as visitCount_28,max(visitCount_35) as visitCount_35,max(c42) as visitCount_42,max(c49) as visitCount_49,max(c56) as visitCount_56,(select sum(newCounte) from (SELECT sum(case when new_user_count=null then 0 else new_user_count  end) as newCounte  FROM la_channel_stat  where appid=${appid} and type='market' and name='${channel_name}' and date >='${date1}' and date<='${date2}' union all SELECT sum(case when new_user_count=null then 0 else new_user_count  end) as newCounte  FROM la_channel_version_stat  where appid=${appid} and type='market' and name='${channel_name}' and date >='${date1}' and date<='${date2}' group by date) b)as addCount from (select s.date as date, s.c7 as visitCount_7, s.c14 as visitCount_14, s.c21 as visitCount_21, s.c28 as visitCount_28, s.c35 as visitCount_35,s.c42,s.c49,s.c56 from (SELECT appid,date,max(case when before_day=7 then visit_user_count else 0 end ) as c7,max(case when before_day=14 then visit_user_count else 0 end )  as c14 ,max(case when before_day=21 then visit_user_count else 0 end )  as c21 ,max(case when before_day=28 then visit_user_count else 0 end )  as c28 ,max(case when before_day=35 then visit_user_count else 0 end )  as c35, max(case when before_day=42 then visit_user_count else 0 end )  as c42,max(case when before_day=49 then visit_user_count else 0 end )  as c49,max(case when before_day=56 then visit_user_count else 0 end )  as c56   FROM la_subsistence_channel_stat where appid=${appid} and channel_name='${channel_name}' group by date ) s  where s.date >='${date1}' and s.date<='${date2}' ) c")
	public RetentionDaysVO selectByChannelNameDays(@Param("appid") String appid, @Param("date1") String startDate,@Param("date2") String endDate,@Param("channel_name") String channel_name);
	
	
	
	@Select("select DISTINCT channel_name from la_subsistence_channel_stat where appid='${appid}' and date between '${date1}' and '${date2}' order by channel_name")
	public List<String> selectChannelName(@Param("appid") String appid, @Param("date1") String startDate,@Param("date2") String endDate);
}