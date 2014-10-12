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
import com.mapbar.analyzelog.common.UserAnalysisType;
import com.mapbar.analyzelog.report.entity.LocationVO;
import com.mapbar.analyzelog.report.mapper.LocationMapper;

/**
 * <p>
 * 处理地域的跳转控制 
 * @author <a href="mailto:renzg_225@mapbar.com">renzg</a> 
 * @date 2012-02-22
 */
@Controller
public class LocationController extends UserAnalysisType{
	@Resource
	private LocationMapper locationMapper;

	@RequestMapping(value = "/apps/{appid}/location")
	public String location(@PathVariable String appid,
			@RequestParam(value = "fromdate", required=false) String fromdate,
			@RequestParam(value = "todate", required=false) String todate,
			@RequestParam(value="page",required=false) String page,Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		doApp("location", appid, model);
		List<LocationVO> list = null;List<LocationVO> list4=null;
		if(fromdate==null||todate==null){
			list=locationMapper.select(appid);
			list4=locationMapper.selectnews(appid);
		}
		else{
			list = locationMapper.selectByDay(appid, fromdate, todate);
			list4=locationMapper.selectNewsByDay(appid, fromdate, todate);
		}
		int visitRatio = 0;
		int newRatio   = 0;
		for(LocationVO vo : list){
			visitRatio += vo.getVisitcount();
			newRatio   += vo.getNewcount();
		}
		model.addAttribute("visitRatio",visitRatio == 0 ? 1 : visitRatio);
		model.addAttribute("newRatio",newRatio==0 ? 1 : newRatio);
		model.addAttribute("stats",list);
		model.addAttribute("newst",list4);
		
		return "location";
	}
}
