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
import com.mapbar.analyzelog.report.entity.NoticeVO;
import com.mapbar.analyzelog.report.mapper.EventMapper;

/**
 * <p>
 * 用户管理
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.AppStatMapper
 */
@Controller
public class NoticesController extends MediaType{

	@Resource
	private EventMapper eventMapper;

	@RequestMapping(value = "/apps/{appid}/notices")
	public String device(@PathVariable String appid,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="modifyid",required=false) String modifyid,
			@RequestParam(value="addname",required=false) String addname,
			@RequestParam(value="myapp",required=false) String myapp,
			@RequestParam(value="del",required=false) String del,
			@RequestParam(value="checkview",required=false) String checkview,
			@RequestParam(value="modifyView",required=false) String modifyView,
			Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String.valueOf(doApp("notices", appid, model));
		try{
			if(addname!=null&&!addname.isEmpty()&&username!=null){
				eventMapper.insertNotice(myapp,username);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(del!=null&&!del.isEmpty()){
			eventMapper.deleteNotice(del);
		}
		
		if(modifyid!=null&&!modifyid.isEmpty()){
			eventMapper.updateNotice(modifyid,username,myapp);
		}
		
		if(modifyView!=null&&modifyView.equals("true")){
			eventMapper.updateCVAll();
			eventMapper.updateCV(checkview);
		}
		
		List<NoticeVO> noticesall=eventMapper.getNoticesAll();
		model.addAttribute("noticesall",noticesall);
		return NOTICE;
	}
	
	@RequestMapping(value = "/apps/{appid}/notices-app")
	public String notice(@PathVariable String appid,
			Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String.valueOf(doApp("notices-app", appid, model));
		return NOTICE_APP;
	}
}
