/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import com.mapbar.analyzelog.report.entity.ChannlReptile;
import com.mapbar.analyzelog.report.entity.VersionVVO;
import com.mapbar.analyzelog.report.entity.ChannelVO;
/**
 * <p>
 * 应用信息的映射器
 * </p>
 * @see com.mapbar.analyzelog.report.entity.VersionVVO
 * @author <a href="mailto:lijie@mapbar.com">lijie</a>
 * @date 2012-02-23
 */
@Component("channelMapper")
public interface ChannelMapper {
	@Select("select appid,type,name,note FROM la_channel_notes o WHERE o.appid =${appid} GROUP BY o.appid,o.type,o.name ")
	public List<ChannelVO> queryNames(@Param("appid") String appid);
	
	@Select("select a.id,appid,type,a.name,note,b.name as appname from (select id,appid,type,name,note FROM la_channel_notes o GROUP BY o.appid,o.type,o.name )a left join la_app b on a.appid=b.id")
	public List<ChannelVO> queryNamesAll();
	
	@Select("select type as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} group by version,date  ")
	public List<VersionVVO> selectV(@Param("appid") String appid);
	
	@Select("select type as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and date >='${date1}' and date<='${date2}' group by version,date  ")
	public List<VersionVVO> selectVByDay(@Param("appid") String appid,@Param("date1") String startDate,@Param("date2") String endDate);
	
	@Select("select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and type='${type}' group by date,name order by date desc")
	public List<VersionVVO> selectT(@Param("appid") String appid,@Param("type") String type);
	
	@Select("select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and type='${type}' and name='${name}' group by date,name order by date desc")
	public List<VersionVVO> selectTExp(@Param("appid") String appid,@Param("type") String type,@Param("name") String name);
	
	@Select("select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and (type='${type1}' or type='${type2}') group by date,name order by date desc")
	public List<VersionVVO> selectT2(@Param("appid") String appid,@Param("type1") String type1,@Param("type2") String type2);
	/***
	 * 增加现实昨日信息功能
	 * @param appid
	 * @param type1
	 * @param type2
	 * @return
	 */
	@Select("select a.name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,a.date,b.addCount as addCount2  from la_channel_stat a left join la_channel_reptile b on a.name=b.name and a.date=(b.date-1) and b.appid=${appid} where a.appid=${appid} and (type='${type1}' or type='${type2}')  group by a.date,a.name order by a.date desc")
	public List<VersionVVO> selectT3(@Param("appid") String appid,@Param("type1") String type1,@Param("type2") String type2);
	/***
	 * 获得市场新增信息，带下载量信息
	 * @param appid
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 *
	 */
	@Select("select a.name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,a.date,ifnull(b.addCount,0) as addCount2 from (SELECT name,SUM(new_user_count) new_user_count,SUM(launch_user_count) launch_user_count,date from la_channel_stat where appid=${appid} and  (type='${type1}' or type='${type1}') and date >='${date1}' and date<='${date2}' GROUP BY name,date UNION ALL SELECT name,SUM(new_user_count) new_user_count,SUM(launch_user_count) launch_user_count,date from la_channel_version_stat where appid=${appid} and (type='${type1}' or type='${type1}') and date >='${date1}' and date<='${date2}' GROUP BY name,date) a left join la_channel_reptile b on a.name=b.name and a.date=ADDDATE(b.date,-1) and b.appid=${appid} GROUP BY a.name,date")
	public List<VersionVVO> selectTByDay3(@Param("appid") String appid,@Param("type1") String type1,@Param("type2") String type2,@Param("date1") String startDate,@Param("date2") String endDate);
	
	
	@Select("select a.name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,a.date,ifnull(b.addCount,0) as addCount2  from la_channel_version_stat a left join la_channel_reptile b on a.name=b.name and a.date=ADDDATE(b.date,-1) and b.appid=${appid} where  a.appid=${appid} and a.version='${version}' and  (type='${type1}' or type='${type2}') and a.date >='${date1}' and a.date<='${date2}' group by a.date,a.name order by a.date desc")
	public List<VersionVVO> selectTByDay3Version(@Param("appid") String appid,@Param("type1") String type1,@Param("type2") String type2,@Param("date1") String startDate,@Param("date2") String endDate,@Param("version") String version);
	
	
	@Select("select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and (type='${type1}' or type='${type2}') and name='${name}' group by date,name order by date desc")
	public List<VersionVVO> selectT2Exp(@Param("appid") String appid,@Param("type1") String type1,@Param("type2") String type2,@Param("name") String name);
	/***
	 * 获得预装渠道新增，启动信息
	 * @param appid
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 *
	 */
	@Select("select version,news,launchs,date from (select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and type='${type}' and date >='${date1}' and date<='${date2}' group by date,name UNION ALL select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_version_stat where appid=${appid} and type='${type}' and date >='${date1}' and date<='${date2}' group by date,name ) b ORDER BY b.date DESC")
	public List<VersionVVO> selectTByDay(@Param("appid") String appid,@Param("type") String type,@Param("date1") String startDate,@Param("date2") String endDate);
	/***
	 * 获得版本预装渠道新增，启动信息
	 * @param appid
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @param version
	 * @return
	 *
	 */
	@Select("select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_version_stat where appid=${appid} and type='${type}' and date >='${date1}' and date<='${date2}' and version='${version}' group by date,name ")
	public List<VersionVVO> selectTByDayVersion(@Param("appid") String appid,@Param("type") String type,@Param("date1") String startDate,@Param("date2") String endDate,@Param("version") String version);
	
	@Select("select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and type='${type}' and date >='${date1}' and date<='${date2}' and name='${name}' group by date,name order by date desc")
	public List<VersionVVO> selectTByDayExp(@Param("appid") String appid,@Param("type") String type,@Param("date1") String startDate,@Param("date2") String endDate,@Param("name") String name);
	/***
	 * 获得市场渠道信息
	 * @param appid
	 * @param type1
	 * @param type2
	 * @param startDate
	 * @param endDate
	 * @return
	 *
	 */
	@Select("select name as version ,news,launchs,date from (select name ,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and (type='${type1}' or type='${type2}') and date >='${date1}' and date<='${date2}' group by date,name UNION ALL select name ,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_version_stat where appid=${appid} and (type='${type1}' or type='${type2}') and date >='${date1}' and date<='${date2}' group by date,name ) b ORDER BY b.date DESC")
	public List<VersionVVO> selectT2ByDay(@Param("appid") String appid,@Param("type1") String type1,@Param("type2") String type2,@Param("date1") String startDate,@Param("date2") String endDate);
//	修改！删除了version sql语句书写问题  
//	@Select("select name as name1 , version ,news,launchs,date from (select name ,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and (type='${type1}' or type='${type2}') and date >='${date1}' and date<='${date2}' group by date,name UNION ALL select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_version_stat where appid=${appid} and (type='${type1}' or type='${type2}') and date >='${date1}' and date<='${date2}' group by date,name ) b ORDER BY b.date DESC")
//	public List<VersionVVO> selectT2ByDay(@Param("appid") String appid,@Param("type1") String type1,@Param("type2") String type2,@Param("date1") String startDate,@Param("date2") String endDate);
	
	/***
	 * 获得版本对应的市场渠道信息
	 * @param appid
	 * @param type1
	 * @param type2
	 * @param startDate
	 * @param endDate
	 * @param version
	 * @return
	 *
	 */
	@Select("select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_version_stat where appid=${appid} and (type='${type1}' or type='${type2}') and date >='${date1}' and date<='${date2}' and version='${version}' group by date,name order by date desc")
	public List<VersionVVO> selectT2ByDayVersion(@Param("appid") String appid,@Param("type1") String type1,@Param("type2") String type2,@Param("date1") String startDate,@Param("date2") String endDate,@Param("version") String version);
	/**
     * 默认取得前一天爬虫数据
     * @return
     *
     */
	@Select("SELECT s.date,s.name,addCount,total,new_user_count as proportion from (SELECT t.date as date,name,addCount,total from (SELECT MAX(date) as date from la_channel_reptile) as t,la_channel_reptile b where b.date=t.date and appid=${appid} ) s left JOIN la_channel_stat w on s.name=w.name WHERE s.date =w.date and w.type='market' and w.appid=${appid}")
	public List<ChannlReptile> selectReptile(@Param("appid") String appid);
	/***
	 * 取得所有爬虫数据，可以修改
	 * @return
	 *
	 */
	@Select("SELECT date,name,addCount,total,id from (select max(date) mdate from la_channel_reptile) a,la_channel_reptile b where a.mdate=b.date and appid=${appid} order by date desc")
	public List<ChannlReptile> queryAllForReptile(@Param("appid") String appid);
	/***
	 * 爬取爬虫数据
	 * @param appid
	 * @param date
	 * @return
	 *
	 */
	@Select("select date,name,addCount,total,id from la_channel_reptile where appid=${ appid } and date='${date}'")
	public List<ChannlReptile> queryDateForReptile(@Param("appid") String appid,@Param("date") String date);
    /**
     * 修改爬虫数据
     */
	@Update("update la_channel_reptile set addCount=${addCount},total=${total} where id=${id}")
	public void updateForReptile(@Param("addCount") int addCount,@Param("total") int total,@Param("id") int id);
	/***
	 * 添加爬虫数据
	 */
	@Insert("insert into la_channel_reptile value(null,'${date}','${name}',${addCount},${total},'${appid}')")
	public void addReptile(@Param("appid") String appid,@Param("name") String name,@Param("addCount") int addCount,@Param("total") int total,@Param("date") String date);
	
	@Select("select name as version,sum(new_user_count) as news,sum(launch_user_count) as launchs,date  from la_channel_stat where appid=${appid} and (type='${type1}' or type='${type2}') and date >='${date1}' and date<='${date2}' and name='${name}' group by date,name order by date desc")
	public List<VersionVVO> selectT2ByDayExp(@Param("appid") String appid,@Param("type1") String type1,@Param("type2") String type2,@Param("date1") String startDate,@Param("date2") String endDate,@Param("name") String name);
	
	
	@Insert("insert into la_channel_notes(appid,type,name,note) values (${appid},'${type}','${name}','${note}')")
	public void insertNote(@Param("appid") String appid,@Param("type") String type,@Param("name") String name,@Param("note") String note);
	
	@Update("update la_channel_notes set name='${name}',note='${note}' where id=#{id}")
	public void updateNote(@Param("id") String id,@Param("name") String name,@Param("note") String note);
	
	@Delete("delete from la_channel_notes where id=#{id}")
	public void deleteNote(@Param("id") String id);
    /***
     * 查询时间段内对应的版本信息
     * @param id
     * @param startDate
     * @param endDate
     */
	@Select("select DISTINCT version from la_channel_version_stat where appid=${appid} and date between '${date1}' and '${date2}' order by version desc")
	public List<String> queryVersionDate(@Param("appid") String appid,@Param("date1") String startDate,@Param("date2") String endDate);
}