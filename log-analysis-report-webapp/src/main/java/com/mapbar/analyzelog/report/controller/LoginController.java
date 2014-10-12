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
import org.springframework.web.bind.annotation.RequestMapping;

import com.mapbar.analyzelog.report.entity.LaApp;
import com.mapbar.analyzelog.report.entity.MenuVO;
import com.mapbar.analyzelog.report.mapper.AppStatMapper;

/**
 * 
 */
@Controller
public class LoginController extends AppController{
	
	@Resource
	private AppStatMapper appStatMapper;

	@RequestMapping(value = "/login")
	public String index(Model model,HttpServletRequest request) {
		
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		if(name==null||pwd==null||name.isEmpty()||pwd.isEmpty())
			return "login";
		LaApp vo=appStatMapper.selectUser(name,pwd);
		if(vo==null||vo.getName().isEmpty())
			return "login";
//		eventMapper.deleteFS();
		
		setAttribute(request,"username",vo.getName());
		if(vo==null||vo.getId()==0)
			return "reSelectApp";
		setAttribute(request,"userappid",vo.getId());
		doApp("login",String.valueOf(vo.getId()), model);
//		appStatMapper.insertFS(events);
		
		List<MenuVO> menus=appStatMapper.selectMenusByname(name);
		setAttribute(request,"usermenus",menus);
		setAttribute(request,"userid",vo.getTotalUser());
		appStatMapper.callSplit(name);
		List<LaApp> list = appStatMapper.selectAppsForStat(name);
		List<LaApp> listYesterday = appStatMapper.selectAppsForStatYesterDay(name);
		/***
		 * 昨日和今日用户数
		 */
		for(LaApp la:list){
			for(LaApp lay:listYesterday){
				if(lay.getName().equals(la.getName())){
					la.setMultipleLaunchCount(la.getLaunchCount()+"/"+lay.getLaunchCount());
					la.setMultipleLaunchUserCount(la.getLaunchUserCount()+"/"+lay.getLaunchUserCount());
					la.setMultipleNewUserCount(la.getNewUserCount()+"/"+lay.getNewUserCount());
				}
			}
		}
		
		model.addAttribute("apps", list);
		int totalNum=0;
		for(LaApp la :list){
			totalNum += la.getTotalUser();
		}
		LaApp ls = appStatMapper.selectRepeatForApp();
		ls.setTotalUser(totalNum);
//		ls.setLaunchCount(ls.getLaunchUserCount());
		ls.setLaunchCount(ls.getTotalUser()-ls.getLaunchUserCount());
		model.addAttribute("repeatApps",ls);
		
		return "apps";
	}
}
