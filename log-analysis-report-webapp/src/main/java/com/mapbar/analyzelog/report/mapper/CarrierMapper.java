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
import com.mapbar.analyzelog.report.entity.CarrierVO;

/**
 * <p>
 * 应用信息的映射器
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.CarrierVO
 * @author <a href="mailto:lijie@mapbar.com">lijie</a>
 * @date 2012-02-20
 */
@Component("carrierMapper")
public interface CarrierMapper {
//	@Select("select device,sum(newcount) as newcount,sum(launchcount) as launchcount from (select case when (device like '%CMCC%' or device like '%移动%' or device like '%移動%'  or device like '%China Mobile%' ) then  '中国移动' else  case when  (device like '%UNICOM%'  or device like '%CUGSM%'  or device like '%联通%' or device like '%聯通%' ) then '中国联通' else case when ( device like '%Telecom%'   or device like '%电信%' or device like '%電信%' ) then '中国电信' else  '其他' end end end as 'device',newcount,launchcount from ( select carrier as device,sum(new_user_count) as newcount,sum(launch_user_count) as launchcount FROM la_carrier_stat group by carrier)a)b group by b.device ")
	public List<CarrierVO> select(@Param("appid") String appid);

//	@Select("select device,sum(newcount) as newcount,sum(launchcount) as launchcount from (select case when (device like '%CMCC%' or device like '%移动%' or device like '%移動%'  or device like '%China Mobile%' ) then  '中国移动' else  case when  (device like '%UNICOM%'  or device like '%CUGSM%'  or device like '%联通%' or device like '%聯通%' ) then '中国联通' else case when ( device like '%Telecom%'   or device like '%电信%' or device like '%電信%' ) then '中国电信' else  '其他' end end end as 'device',newcount,launchcount from ( select carrier as device,sum(new_user_count) as newcount,sum(launch_user_count) as launchcount FROM la_carrier_stat where date >='${date1}' and date<='${date2}' group by carrier)a)b group by b.device")
	public List<CarrierVO> selectByDay(@Param("date1") String startDate,@Param("date2") String endDate,@Param("appid") String appid);
}