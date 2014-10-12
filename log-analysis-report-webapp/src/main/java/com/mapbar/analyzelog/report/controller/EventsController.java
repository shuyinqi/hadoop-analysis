/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mapbar.analyzelog.common.MediaType;
import com.mapbar.analyzelog.report.entity.EventVO;
import com.mapbar.analyzelog.report.entity.EventVVO;
import com.mapbar.analyzelog.report.mapper.EventMapper;

/**
 * <p>
 * 处理设备统计的跳转控制
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.DeviceMapper
 */
@Controller
public class EventsController extends MediaType{

	@Resource
	private EventMapper eventMapper;

	@RequestMapping(value = "/apps/{appid}/events/{name}")
	public String event(@PathVariable String appid,@PathVariable String name,
			@RequestParam(value="fromdate",required=false) String fromdate,
			@RequestParam(value="todate",required=false) String todate, Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String appid_long = String.valueOf(doApp(name, appid, model));
		List<EventVO> list=null;
		List<EventVVO> list2=null;
		if(fromdate==null||todate==null){
			list=eventMapper.select(appid_long,name);
			list2=eventMapper.selectV(appid_long,name);
		}
		else{
			list=eventMapper.selectByDay(appid_long,name,fromdate,todate);
			list2=eventMapper.selectVByDay(appid_long, name, fromdate, todate);
		}
		int launchRatio=0;
		for(EventVO vo:list){
			launchRatio+=vo.getCount();
		}
		model.addAttribute("launchRatio",launchRatio);
		model.addAttribute("eventname",name);
		model.addAttribute("eventvs",list2);
		model.addAttribute("stats",list);
		
		return EVENTS;
	}
}
