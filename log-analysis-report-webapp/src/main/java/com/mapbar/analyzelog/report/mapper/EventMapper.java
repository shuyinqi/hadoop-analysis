/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import com.mapbar.analyzelog.report.entity.EventVO;
import com.mapbar.analyzelog.report.entity.EventVVO;
import com.mapbar.analyzelog.report.entity.FunctionVO;
import com.mapbar.analyzelog.report.entity.NoticeVO;

/**
 * <p>
 * 应用信息的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.EventVO
 * @author <a href="mailto:lijie@mapbar.com">lijie</a>
 * @date 2012-02-20
 */
@Component("eventMapper")
public interface EventMapper {
	@Delete("delete from la_app_events where appid=${appid}")
	public void deleteFS(@Param("appid") String appid);
	
	@Select("select appid as id,name,2 as flevel,concat('events/',name) as link,name as fn from la_event_stat where appid=${appid} group by name ")
	public List<FunctionVO> selectAll(@Param("appid") String appid);
	
	@Select("select label,sum(count) as count from la_event_stat where appid=${appid} and name='${name}' ")
	public List<EventVO> select(@Param("appid") String appid,@Param("name") String name);
	
	@Select("select label,sum(count) as count from la_event_stat where appid=${appid} and name='${name}' and date >='${date1}' and date<='${date2}' group by label order by count desc")
	public List<EventVO> selectByDay(@Param("appid") String appid,@Param("name") String name,@Param("date1") String startDate,@Param("date2") String endDate);
	
	@Select("select label,sum(count) as count,date,app_version from la_event_stat where appid=${appid} and name='${name}' group by name,date ")
	public List<EventVVO> selectV(@Param("appid") String appid,@Param("name") String name);
	
	@Select("select label,sum(count) as count,date,app_version from la_event_stat where appid=${appid} and name='${name}' and date >='${date1}' and date<='${date2}' group by name,date ")
	public List<EventVVO> selectVByDay(@Param("appid") String appid,@Param("name") String name,@Param("date1") String startDate,@Param("date2") String endDate);
	
	@Select("select a.*,b.name from la_notice_apps a left join la_app b on a.appid=b.id where appid=${appid} and a.flag=1 order by id desc")
	public List<NoticeVO> getNotices(@Param("appid") String appid);
	
	@Select("select a.*,b.name from la_notice_apps a left join la_app b on a.appid=b.id order by appid,id desc")
	public List<NoticeVO> getNoticesAll();
	
	@Insert("insert into la_notice_apps(appid,note,date) values (${appid},'${name}',CURDATE())")
	public void insertNotice(@Param("appid") String appid,@Param("name") String name);
	
	@Update("update la_notice_apps set note='${name}',appid=${appid} where id=${id}")
	public void updateNotice(@Param("id") String id,@Param("name") String name,@Param("appid") String appid);
	
	@Delete("delete from la_notice_apps where id=${id}")
	public void deleteNotice(@Param("id") String id);
	
	@Update("update la_notice_apps set flag=0")
	public void updateCVAll();
	
	@Update("update la_notice_apps set flag=1 where id in(${id})")
	public void updateCV(@Param("id") String id);
}