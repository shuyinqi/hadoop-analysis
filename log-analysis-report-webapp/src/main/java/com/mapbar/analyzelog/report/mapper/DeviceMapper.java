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

import com.mapbar.analyzelog.report.entity.DeviceSettingVO;
import com.mapbar.analyzelog.report.entity.DeviceVO;

/**
 * <p>
 * 应用信息的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.DeviceVO
 * @author <a href="mailto:lijie@mapbar.com">lijie</a>
 * @date 2012-02-20
 */
@Component("deviceMapper")
public interface DeviceMapper {
//	@Select("select device,newcount,launchcount from (select CONCAT(brand,'/',device) as device,sum(new_user_count) as newcount,sum(launch_user_count) as launchcount FROM la_device_stat where appid=${appid} group by device )a order by launchcount desc")
	public List<DeviceVO> select(@Param("appid") String appid,@Param("queryname") String queryname);
	
	public DeviceVO selectcount(@Param("appid") String appid,@Param("date1") String startDate,@Param("date2") String endDate);
	
	public List<DeviceVO> selectmx(@Param("appid") String appid,@Param("device") String device,@Param("brand") String brand,@Param("date1") String startDate,@Param("date2") String endDate);
	
	public void mx1_delete();
	public void mx1_getSetting();
	public List<DeviceVO> selectmx1(@Param("appid") String appid,@Param("device") String device,@Param("brand") String brand,@Param("date1") String startDate,@Param("date2") String endDate);
	
	public List<DeviceVO> selectmx2(@Param("appid") String appid,@Param("device") String device,@Param("brand") String brand,@Param("date1") String startDate,@Param("date2") String endDate);
	
	public List<DeviceVO> selectmx3(@Param("appid") String appid,@Param("device") String device,@Param("brand") String brand,@Param("date1") String startDate,@Param("date2") String endDate);
	
	public List<DeviceVO> selectnews(@Param("appid") String appid);

//	@Select("select device,newcount,launchcount from (select device,sum(new_user_count) as newcount,sum(launch_user_count) as launchcount FROM la_device_stat where appid=${appid} and date >='${date1}' and date<='${date2}' group by device )a order by launchcount desc")
	public List<DeviceVO> selectByDay(@Param("date1") String startDate,@Param("date2") String endDate,@Param("appid") String appid,@Param("queryname") String queryname);
	
	public List<DeviceVO> selectNewsByDay(@Param("date1") String startDate,@Param("date2") String endDate,@Param("appid") String appid);
	
	@Insert("insert into la_device_setting(id,brand,device,fn) values (${id},'${brand}','${device}','${fn}')")
	public void insertSetting(@Param("id") String id,@Param("brand") String brand,@Param("device") String device,@Param("fn") String fn);
	
	@Update("update la_device_setting set brand='${brand}',device='${device}',fn='${fn}' where id=#{id}")
	public void updateSetting(@Param("id") String id,@Param("brand") String brand,@Param("device") String device,@Param("fn") String fn);
	
	@Delete("delete from la_device_setting where id=#{id}")
	public void deleteSetting(@Param("id") String id);
	
	@Select("select * from la_device_setting")
	public List<DeviceSettingVO> getSettingsAll();
}