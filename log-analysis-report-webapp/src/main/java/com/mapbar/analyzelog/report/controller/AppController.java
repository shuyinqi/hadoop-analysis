/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.mapbar.analyzelog.report.entity.FunctionVO;
import com.mapbar.analyzelog.report.mapper.EventMapper;
import com.mapbar.analyzelog.report.services.AppService;
import com.mapbar.analyzelog.report.utils.DateUtil;

/**
 * <p>
 * 处理应用信息的跳转控制
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.services.AppService
 * @see com.mapbar.analyzelog.report.entity.LaApp
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-20
 */
@Controller
public class AppController {

	@Resource
	private AppService appService;
	@Resource
	private EventMapper eventMapper;
	List<FunctionVO> events=null;
	public Long doApp(String method, String appid, Model model) {
		model.addAttribute("method", method);
		Long result = Long.parseLong(appid);
		model.addAttribute("app", appService.findById(result));
		model.addAttribute("apps", appService.findAll(null));
		events=appService.findEventAll(appid);
		model.addAttribute("events", events);
		model.addAttribute("notices",eventMapper.getNotices(appid));

		// 增加过去一周、过去三月、全部
		model.addAttribute("lastWeek1", "fromdate="
				+ DateUtil.getStepDay(-7).toString() + "&todate="
				+ DateUtil.getDate().toString());
		model.addAttribute("lastMonth1", "fromdate="
				+ DateUtil.getStepMonth(-1).toString() + "&todate="
				+ DateUtil.getDate().toString());
		model.addAttribute("lastMonth3", "fromdate="
				+ DateUtil.getStepMonth(-3).toString() + "&todate="
				+ DateUtil.getDate().toString());
		model.addAttribute("allDay","fromdate=2012-03-20"+ "&todate="
				+ DateUtil.getDate().toString());
		
		//增加最近一周
		model.addAttribute("lastDay1", "fromdate="
				+ DateUtil.getDate().toString() + "&todate="
				+ DateUtil.getDate().toString());
		model.addAttribute("lastDay2", "fromdate="
				+ DateUtil.getStepDay(-1).toString() + "&todate="
				+ DateUtil.getStepDay(-1).toString());
		model.addAttribute("lastDay3", "fromdate="
				+ DateUtil.getStepDay(-2).toString() + "&todate="
				+ DateUtil.getStepDay(-2).toString());
		model.addAttribute("lastDay4", "fromdate="
				+ DateUtil.getStepDay(-3).toString() + "&todate="
				+ DateUtil.getStepDay(-3).toString());
		model.addAttribute("lastDay5", "fromdate="
				+ DateUtil.getStepDay(-4).toString() + "&todate="
				+ DateUtil.getStepDay(-4).toString());
		return result;
	}
	
	public String checkSession(HttpServletRequest request) {
		String url=checkSession(request,"username");
		String userapp=getApp(request);
		if (!url.equals("")) {
			return "relogin";
		}
		if (!userapp.equals("")||userapp.equals("relogin")) {
			return "reSelectApp";
		}
		return "";
	}
	
	public String checkSession(HttpServletRequest request,String s) {
		String result = "";
		HttpSession hs = request.getSession();
		if (hs != null) {
			Object username = hs.getAttribute(s);
			if (username == null || username.equals("")) {
				result ="relogin";
			}
		} else {
			result = "relogin";
		}
		return result;
	}
	
	public String getUser(HttpServletRequest request) {
		String result = "";
		HttpSession hs = request.getSession();
		if (hs != null) {
			Object username = hs.getAttribute("username");
			if (username != null && !username.equals("")) 
				result=username.toString();
		} else {
			result = "relogin";
		}
		return result;
	}
	
	public String getUserId(HttpServletRequest request) {
		String result = "";
		HttpSession hs = request.getSession();
		if (hs != null) {
			Object username = hs.getAttribute("userid");
			if (username != null && !username.equals("")) 
				result=username.toString();
		} else {
			result = "relogin";
		}
		return result;
	}
	
	public String setAttribute(HttpServletRequest request,String s,Object value) {
		String result = "";
		HttpSession hs = request.getSession();
		if (hs != null) {
			hs.setAttribute(s,value);
		} else {
			result = "relogin";
		}
		return result;
	}
	
	public String getApp(HttpServletRequest request) {
		return checkSession(request,"userappid");
	}
}
