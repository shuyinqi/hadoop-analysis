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

import com.mapbar.analyzelog.report.entity.SearchVersionVO;
import com.mapbar.analyzelog.report.entity.VersionSVO;
import com.mapbar.analyzelog.report.entity.VersionVVO;

/**
 * <p>
 * 应用信息的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.VersionVVO
 * @author <a href="mailto:lijie@mapbar.com">lijie</a>
 * @date 2012-02-23
 */
@Component("versionMapper")
public interface VersionMapper {
	/*@Select("select version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_version_stat where appid=${appid} group by version,date  ")
	public List<VersionVVO> selectV(@Param("appid") String appid);
	
	@Select("select version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_version_stat where appid=${appid} and date >='${date1}' and date<='${date2}' group by version,date  ")
	public List<VersionVVO> selectVByDay(@Param("appid") String appid,@Param("date1") String startDate,@Param("date2") String endDate);
	
	@Select("select version,sum(new_user_count) as news  from la_version_stat where appid=${appid} group by version")
	public List<VersionSVO> selectVAll(@Param("appid") String appid);
	
	@Select("select a.version,sum(days1)as tds,sum(news)as news,sum(ups)as ups,sum(days2) as yds,counts from (select a.version,case when a.date=curdate()  then a.launchs  end  as days1,case when a.date=curdate()  then a.news  end  as news,case when a.date=curdate()  then a.ups  end  as ups,case when a.date=date_sub(curdate(),interval 1 day)  then a.launchs  end  as days2  , (select sum(new_user_count) as counts  from la_version_stat b where b.version=a.version group by version ) as counts from  (select version,sum(new_user_count) as news,sum(launch_user_count) as launchs,sum(upgrade_user_count) as ups,date from la_version_stat WHERE  date BETWEEN date_sub(curdate(),interval 1 day) AND curdate() and appid=${appid} group by version,date )a)a group by a.version")
	public List<VersionSVO> selectAll(@Param("appid") String appid);*/
	
	public List<VersionVVO> selectV(String appid);
	
	public List<VersionVVO> selectVByDay(SearchVersionVO map);
	
	public List<VersionVVO> selectVByDay2(SearchVersionVO map);
	public List<VersionSVO> selectVAll(@Param("appid") String appid,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("version") String version,@Param("queryname") String queryname);
	
	public List<VersionSVO> selectAll(@Param("appid") String appid,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("version") String version,@Param("queryname") String queryname);
	
	public List<VersionVVO> selectVersion(@Param("appid") String appid,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	public List<VersionVVO> selectVByV(@Param("appid") String appid,@Param("version") String version,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	public List<VersionVVO> selectVByVD(@Param("appid") String appid,@Param("version") String version,@Param("fromdate") String fromdate,@Param("todate") String todate);
}