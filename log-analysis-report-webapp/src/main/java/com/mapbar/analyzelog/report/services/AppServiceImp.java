/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapbar.analyzelog.report.entity.EventVO;
import com.mapbar.analyzelog.report.entity.FunctionVO;
import com.mapbar.analyzelog.report.entity.LaApp;
import com.mapbar.analyzelog.report.mapper.AppMapper;
import com.mapbar.analyzelog.report.mapper.EventMapper;

/**
 * <p>
 * 处理应用信息业务逻辑的实现类
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.AppMapper
 * @see com.mapbar.analyzelog.report.entity.LaApp
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-13
 */
@Service("appService")
public class AppServiceImp implements AppService {

	private static Log log = LogFactory.getLog(AppServiceImp.class);

	@Autowired
	private AppMapper appMapper;
	
	@Autowired
	private EventMapper eventMapper;

	public LaApp findById(Long id) {
		return appMapper.findByPK(id);
	}

	public List<LaApp> findAll(Long id) {
		return appMapper.findAll(id);
	}
	
	public List<FunctionVO> findEventAll(String id) {
		return eventMapper.selectAll(id);
	}
	
}