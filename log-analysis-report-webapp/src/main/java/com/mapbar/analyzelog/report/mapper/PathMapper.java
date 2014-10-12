package com.mapbar.analyzelog.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.mapbar.analyzelog.report.entity.CommonVO;
import com.mapbar.analyzelog.report.entity.LaPageCanvasVO;
import com.mapbar.analyzelog.report.entity.LaPageVisitVO;
import com.mapbar.analyzelog.report.entity.PathVO;

/**
 * <p>
 * 查询访问页面的数据
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.LocationVO
 * @author <a href="mailto:renzg@mapbar.com">renzg</a>
 * @date 2012-02-22
 */
@Component("pathMapper")
public interface PathMapper {
	
	@Select("SELECT app_version as value FROM  la_activity_stat where appid=${appid}  group by app_version order by app_version")
	public List<CommonVO> searchVersion(@Param("appid") String appid);
	
	@Select("select case when b.cname!='' then CONCAT(name,'/',b.cname) else name end as name,count,user_count,time from (SELECT REVERSE(LEFT(REVERSE(activity_name),INSTR(REVERSE(activity_name),'.')-1)) as name, sum(visit_count) as count,sum(user_count) as user_count, sum(residence_time) as time  FROM la_activity_stat where appid=${appid} group by activity_name order by count desc  ) a left join la_path_name b on a.name=b.ename")
	public List<PathVO> select(@Param("appid") String appid);
	
	@Select("select case when b.cname!='' then CONCAT(name,'/',b.cname) else name end as name,count,user_count,time from (SELECT REVERSE(LEFT(REVERSE(activity_name),INSTR(REVERSE(activity_name),'.')-1)) as name, sum(visit_count) as count,sum(user_count) as user_count, sum(residence_time) as time  FROM la_activity_stat where appid=${appid} and date >='${date1}' and date<='${date2}' group by activity_name order by count desc ) a left join la_path_name b on a.name=b.ename")
	public List<PathVO> selectByDay(@Param("appid") String appid,@Param("date1") String startDate,@Param("date2") String endDate);

	@Select("select case when b.cname!='' then CONCAT(name,'/',b.cname) else name end as name,count,user_count,time from (SELECT REVERSE(LEFT(REVERSE(activity_name),INSTR(REVERSE(activity_name),'.')-1)) as name, sum(visit_count) as count,sum(user_count) as user_count, sum(residence_time) as time  FROM la_activity_stat where appid=${appid} and app_version='${version}' and date >='${date1}' and date<='${date2}' group by activity_name order by count desc ) a left join la_path_name b on a.name=b.ename")
	public List<PathVO> selectByVersion(@Param("appid") String appid, @Param("version") String version,@Param("date1") String startDate,@Param("date2") String endDate);
	/**
	 * 查询页面访问基础页面名称
	 * @param appid
	 * @return
	 */
	@Select("SELECT DISTINCT a.name,a.visits,a.visits_ratio,a.stay,a.stay_ratio,a.exit_ratio from la_page_visit a LEFT JOIN la_page_visit_canvas b on a.`name`=b.pre_name where b.next_name is not NULL and a.appid=${appid}")
    public List<LaPageVisitVO> selectPageVisit(@Param("appid") String appid);
	/***
	 * 查询页面访问轨迹
	 * @param appid
	 * @return
	 */
	@Select("SELECT a.pre_name,a.next_name,a.ratio,a.visits,(SELECT case when COUNT(*)>0 then true else false end as leaf from la_page_visit_canvas b WHERE a.next_name = b.pre_name ) as leaf from la_page_visit_canvas a where appid=1000  order by pre_name,ratio desc")
	public List<LaPageCanvasVO> selectPageVisitCanvas(@Param("appid") String appid);
}
