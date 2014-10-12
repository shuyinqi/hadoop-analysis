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
import com.mapbar.analyzelog.report.entity.OSVO;
import com.mapbar.analyzelog.report.mapper.OSMapper;

/**
 * <p>
 * 处理操作系统统计的跳转控制
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.OSMapper
 */
@Controller
public class OSController extends MediaType{

	@Resource
	private OSMapper osMapper;
	
	@RequestMapping(value = "/apps/{appid}/os")
	public String os(@PathVariable String appid,
			@RequestParam(value="fromdate",required=false) String fromdate,
			@RequestParam(value="todate",required=false) String todate,
			@RequestParam(value="exp_list",required=false) String exp,
			@RequestParam(value="page",required=false) String page,Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String appid_long = String.valueOf(doApp("os", appid, model));
		model.addAttribute("app.id", appid_long);
		List<OSVO> list=null;List<OSVO> list4=null;
		if(fromdate==null||todate==null){
			list=osMapper.select(appid_long);
			list4=osMapper.selectnews(appid_long);
		}
		else{
			list=osMapper.selectByDay(fromdate,todate,appid_long);
			list4=osMapper.selectNewsByDay(fromdate,todate,appid_long);
		}
		List<OSVO> list3=null;
		if(list.size()>=10)list3=list.subList(0,10);
		else list3=list;
		int launchRatio=0;int newsRatio=0;
		for(OSVO vo:list){
			launchRatio+=vo.getLaunchcount();
			newsRatio+=vo.getNewcount();
		}
		model.addAttribute("launchRatio",launchRatio);
		model.addAttribute("newsRatio",newsRatio);
		if(exp!=null&&exp.equals("true")){
			model.addAttribute("exp_list",list);
			return "excel4";
		}
		
		int pcount=list.size();
		model.addAttribute("pagecount",pcount);
		if(page==null||pcount<=10)page="1";
		model.addAttribute("page",page);
		int pagenum=Integer.parseInt(page);
		boolean boend=pcount/10==(pagenum-1);
		List<OSVO> list2=list.subList((pagenum-1)*10,boend?pcount:pagenum*10);
		
		model.addAttribute("stats",list2);
		model.addAttribute("stats1",list3);
		model.addAttribute("newst",list4);
		return OS;
	}
}
