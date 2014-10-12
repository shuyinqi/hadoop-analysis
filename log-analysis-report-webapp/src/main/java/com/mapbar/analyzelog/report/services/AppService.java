/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.services;

import java.util.List;

import com.mapbar.analyzelog.report.entity.EventVO;
import com.mapbar.analyzelog.report.entity.FunctionVO;
import com.mapbar.analyzelog.report.entity.LaApp;

/**
 * <p>
 * 处理应用信息业务逻辑的接口
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.entity.LaApp
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-13
 */
public interface AppService {

	/**
	 * 获取指定主键的记录
	 * 
	 * @param id
	 * @return LaApp
	 */
	public LaApp findById(Long id);

	/**
	 * 获取全部记录集
	 * 
	 * @return List<LaApp>
	 */
	public List<LaApp> findAll(Long id);
	
	/**
	 * 获取事件菜单
	 */
	public List<FunctionVO> findEventAll(String id);
}