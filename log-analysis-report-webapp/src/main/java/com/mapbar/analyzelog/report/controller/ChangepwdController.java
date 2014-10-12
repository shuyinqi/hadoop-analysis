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
import org.springframework.web.bind.annotation.RequestParam;

import com.mapbar.analyzelog.report.entity.LaApp;
import com.mapbar.analyzelog.report.entity.MenuVO;
import com.mapbar.analyzelog.report.mapper.AppStatMapper;
import com.mapbar.analyzelog.report.mapper.EventMapper;

/**
 * 
 */
@Controller
public class ChangepwdController extends AppController{
	
	@Resource
	private AppStatMapper appStatMapper;

	@RequestMapping(value = "/changepwd")
	public String index(Model model,HttpServletRequest request,
			@RequestParam(value="oldpwd",required=false) String oldpwd,
			@RequestParam(value="newpwd",required=false) String newpwd) {
		
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		try{
		String userid=getUserId(request);
		MenuVO pwd=appStatMapper.selectUPwd(userid);
		if(pwd!=null&&pwd.getName().equals(oldpwd)){
		MenuVO vo=new MenuVO();
		vo.setId(Integer.parseInt(userid));
		vo.setName(newpwd.split(",")[0]);
		appStatMapper.changepwd(vo);
		model.addAttribute("changed","success");
		}else model.addAttribute("changed","failed");
		}catch(Exception e){
			model.addAttribute("changed","error");
			e.printStackTrace();
		}
		
		return "login";
	}
}
