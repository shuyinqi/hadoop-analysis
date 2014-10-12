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
import com.mapbar.analyzelog.report.mapper.AppStatMapper;

/**
 * App 主页，将显示当前所有应用程序。
 * 
 * @author dengfg
 * 
 */
@Controller
public class AppsController extends AppController{
	
	@Resource
	private AppStatMapper appStatMapper;

	@RequestMapping(value = "/apps")
	public String index(Model model,HttpServletRequest request) {
		doApp("device", "0", model);
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String userapp=!getUser(request).equals("relogin")?getUser(request):null;
		List<LaApp> list = appStatMapper.selectAppsForStat(userapp);
		model.addAttribute("apps", list);
		List<LaApp> listYesterday = appStatMapper.selectAppsForStatYesterDay(userapp);
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
		
		int totalNum=0;
		for(LaApp la :list){
			totalNum += la.getTotalUser();
		}
		LaApp ls = appStatMapper.selectRepeatForApp();
		ls.setTotalUser(totalNum);
		ls.setLaunchCount(ls.getTotalUser()-ls.getLaunchUserCount());
//		ls.setLaunchCount(ls.getLaunchUserCount());
		model.addAttribute("repeatApps",ls);
		return "apps";
	}
}
