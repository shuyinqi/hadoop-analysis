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
import com.mapbar.analyzelog.report.entity.ResolutionVO;
import com.mapbar.analyzelog.report.mapper.ResolutionMapper;

/**
 * <p>
 * 处理分辨率统计的跳转控制
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.ResolutionMapper
 */
@Controller
public class ResolutionController extends MediaType{

	@Resource
	private ResolutionMapper resolutionMapper;
	
	@RequestMapping(value = "/apps/{appid}/resolution")
	public String resolution(@PathVariable String appid,
			@RequestParam(value="fromdate",required=false) String fromdate,
			@RequestParam(value="todate",required=false) String todate,
			@RequestParam(value="exp_list",required=false) String exp,
			@RequestParam(value="page",required=false) String page,Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String appid_long = String.valueOf(doApp("resolution", appid, model));
		model.addAttribute("app.id", appid_long);
		List<ResolutionVO> list=null;List<ResolutionVO> list4=null;
		if(fromdate==null||todate==null){
			list=resolutionMapper.select(appid_long);
			list4=resolutionMapper.selectnews(appid_long);
		}
		else{
			list=resolutionMapper.selectByDay(fromdate,todate,appid_long);
			list4=resolutionMapper.selectNewsByDay(fromdate,todate,appid_long);
		}
		int launchRatio=0;int newsRatio=0;
		for(ResolutionVO vo:list){
			launchRatio+=vo.getLaunchcount();
			newsRatio+=vo.getNewcount();
		}
		model.addAttribute("launchRatio",launchRatio);
		model.addAttribute("newsRatio",newsRatio);
		if(exp!=null&&exp.equals("true")){
			model.addAttribute("exp_list",list);
			return "excel5";
		}
		
		List<ResolutionVO> list3=null;
		if(list.size()>=10)list3=list.subList(0,10);
		else list3=list;
		int pcount=list.size();
		model.addAttribute("pagecount",pcount);
		if(page==null||pcount<=10)page="1";
		model.addAttribute("page",page);
		int pagenum=Integer.parseInt(page);
		boolean boend=pcount/10==(pagenum-1);
		List<ResolutionVO> list2=list.subList((pagenum-1)*10,boend?pcount:pagenum*10);
		
		model.addAttribute("stats",list2);
		model.addAttribute("stats1",list3);
		model.addAttribute("newst",list4);
		return RESOLUTION;
	}
}
