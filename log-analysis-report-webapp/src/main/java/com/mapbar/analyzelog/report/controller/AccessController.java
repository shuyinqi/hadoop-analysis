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
import com.mapbar.analyzelog.report.entity.AccessVO;
import com.mapbar.analyzelog.report.mapper.AccessMapper;

/**
 * <p>
 * 处理联网方式的跳转控制
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.AccessMapper
 */
@Controller
public class AccessController extends MediaType{

	@Resource
	private AccessMapper accessMapper;
	@RequestMapping(value = "/apps/{appid}/access")
	public String access(@PathVariable String appid,
			@RequestParam(value="fromdate",required=false) String fromdate,
			@RequestParam(value="todate",required=false) String todate, Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String appid_long = String.valueOf(doApp("access", appid, model));
		model.addAttribute("app.id", appid_long);
		List<AccessVO> list=null;
		if(fromdate==null||todate==null)
			list=accessMapper.select(appid_long);
		else 
			list=accessMapper.selectByDay(fromdate,todate,appid_long);
		int launchRatio=0;int newsRatio=0;
		for(AccessVO vo:list){
			launchRatio+=vo.getLaunchcount();
			newsRatio+=vo.getNewcount();
		}
		List<AccessVO> listSub;
		if(list.size()>5){
			 listSub = list.subList(0, 5);
		}else{
			 listSub=list;
		}
		model.addAttribute("launchRatio",launchRatio);
		model.addAttribute("newsRatio",newsRatio);
		model.addAttribute("stats",listSub);
		return ACCESS;
	}
}
