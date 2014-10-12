/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mapbar.analyzelog.common.UserAnalysisType;
import com.mapbar.analyzelog.report.entity.RetentionDaysVO;
import com.mapbar.analyzelog.report.entity.RetentionVO;
import com.mapbar.analyzelog.report.mapper.RetentionMapper;
import com.mapbar.analyzelog.report.utils.DateUtil;

/**
 * <p>
 * 处理回访用户的跳转控制
 * @author <a href="mailto:renzg@mapbar.com">renzg</a> 
 * @date 2012-02-22
 */
@Controller
public class RetentionController extends UserAnalysisType{
	
	@Resource
	private RetentionMapper retentionMapper;
	
	@RequestMapping(value = "/apps/{appid}/retention")
	public String retention(@PathVariable String appid,			
			@RequestParam(value = "period", required=false) String period,
			@RequestParam(value="page",required=false) String page,
			@RequestParam(value="exp_list",required=false) String exp,
			@RequestParam(value="fromdate",required=false) String fromdate,
			@RequestParam(value="todate",required=false) String todate,
			@RequestParam(value="channel_name",required=false) String channel_name,
			Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		doApp("retention", appid, model);
		List<String> channel_name_list=retentionMapper.selectChannelName(appid, fromdate, todate);
		channel_name_list.add("all");
		model.addAttribute("channel_name_list",channel_name_list);
		if(channel_name==null){
			model.addAttribute("channel_name","all");
		}else{
			model.addAttribute("channel_name",channel_name);
		}
		
		List<RetentionVO> list = null;
		String startDate = fromdate;
		String endDate = todate;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date ssdate =null;Date edate =null;
		Date sDate=null;java.sql.Date eDate=null;
		if(startDate == null || endDate == null){
			list = retentionMapper.select(appid);
		}else{
			list = retentionMapper.selectByDay(appid, startDate,endDate);
			try {
				sDate = DateUtil.parse10(startDate);
				eDate = DateUtil.parse10(endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ssdate=getStepAfterDay(sDate);
		}
		
		Date toDay4=null;
		try {
			toDay4 = (DateUtil.stepWeekOfMonth(new Date(),-2).after(eDate)&&DateUtil.parse10(DateUtil.stepWeekOfMonth(new Date(),-2).toLocaleString()).compareTo(eDate)!=0)?eDate:DateUtil.stepWeekOfMonth(new Date(),-1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<RetentionDaysVO> dayslist=new ArrayList<RetentionDaysVO>();
		if(ssdate.compareTo(toDay4)==-1){
			edate=getStepBeforeDay(toDay4);
			
			//转换成天
			int day = (int) (DateUtil.subDate(edate,ssdate)/(1000*60*60*24))+1;
			int days=day/7;
			model.addAttribute("days",days);
			
			Date tmp_Day=ssdate;
			Date toDay=tmp_Day;
			for(int i=1;i<=days;i++){
				Date toDay1=getStepBeforeDay(toDay);
				RetentionDaysVO counts;
				if(channel_name==null||"all".equals(channel_name)){
					 counts = retentionMapper.selectByDays(appid, String.valueOf(tmp_Day), String.valueOf(toDay1));
				}else{
					 counts = retentionMapper.selectByChannelNameDays(appid, String.valueOf(tmp_Day), String.valueOf(toDay1),channel_name);
				}
				if(counts!=null){counts.setTime(tmp_Day+"~"+toDay1);}
				toDay=DateUtil.stepWeekOfMonth(tmp_Day,1);
				if(counts!=null){/*counts.setVisitList(getColumns(tmp_Day,toDay1,appid));*/
				dayslist.add(counts);}
				tmp_Day=toDay;
			}
		}
		
		model.addAttribute("dayslist",dayslist);
		model.addAttribute("beginDay",startDate);
		model.addAttribute("endDay",edate);
		
		int addTotalCount = 0;
		int visitTotalCount_7   = 0;
		int visitTotalCount_14   = 0;
		int visitTotalCount_30   = 0;
		int visitTotalCount_60   = 0;
		int visitTotalCount_90   = 0;
		for(RetentionVO vo : list){
			Date dd = vo.getDate();
			String d = format.format(dd);
			vo.setTime(d);
			addTotalCount += vo.getAddCount();
			visitTotalCount_7   += vo.getVisitCount_7();
			visitTotalCount_14   += vo.getVisitCount_14();
			visitTotalCount_30  += vo.getVisitCount_30();
			visitTotalCount_60  += vo.getVisitCount_60();
			visitTotalCount_90  += vo.getVisitCount_90();
		}
		if(exp!=null&&exp.equals("true")){
			model.addAttribute("exp_list",list);
			return "excel1";
		}
		model.addAttribute("period",period);
		model.addAttribute("addTotalCount",addTotalCount == 0 ? 1 : addTotalCount);
		model.addAttribute("visitTotalCount_7",visitTotalCount_7==0 ? 1 : visitTotalCount_7);
		model.addAttribute("visitTotalCount_14",visitTotalCount_14==0 ? 1 : visitTotalCount_14);
		model.addAttribute("visitTotalCount_30",visitTotalCount_30==0 ? 1 : visitTotalCount_30);
		model.addAttribute("visitTotalCount_60",visitTotalCount_60==0 ? 1 : visitTotalCount_60);
		model.addAttribute("visitTotalCount_90",visitTotalCount_90==0 ? 1 : visitTotalCount_90);
		
		int pcount=list.size();
		model.addAttribute("pagecount",pcount);
		if(page==null||pcount<=10)page="1";
		model.addAttribute("page",page);
		int pagenum=Integer.parseInt(page);
		boolean boend=pcount/10==(pagenum-1);
		List<RetentionVO> list2=list.subList((pagenum-1)*10,boend?pcount:pagenum*10);
		model.addAttribute("stats",list);
		model.addAttribute("pagestats",list2);
		return "retention";
	}
	
	private List getColumns(Date ssdate,Date edate,String appid){
		List<RetentionDaysVO> dayslist=new ArrayList<RetentionDaysVO>();
		int day = (int) (DateUtil.subDate(edate,ssdate)/(1000*60*60*24))+1;
		int days=day/7;
		Date tmp_Day=ssdate;Date toDay=tmp_Day;
		for(int i=1;i<=days;i++){
			Date toDay1=getStepBeforeDay(toDay);
			RetentionDaysVO counts = retentionMapper.selectByDays(appid, String.valueOf(tmp_Day), String.valueOf(toDay1));
			if(counts!=null){counts.setTime(tmp_Day+"~"+toDay1);
			dayslist.add(counts);}
			toDay=DateUtil.stepWeekOfMonth(tmp_Day,1);
			tmp_Day=toDay;
		}
		return dayslist;
	}
	
	private Date getStepBeforeDay(Date toDay4){
		Date edate =null;
		int dayOfWeek=DateUtil.getDayOfWeek(toDay4,-1);
		switch(dayOfWeek) {
		case 1: 
			edate = DateUtil.getStepDay(toDay4,6); break; 
		case 2: 
			edate = DateUtil.getStepDay(toDay4,5); break; 
		case 3: 
			edate = DateUtil.getStepDay(toDay4,4); break; 
		case 4: 
			edate = DateUtil.getStepDay(toDay4,3); break; 
		case 5: 
			edate = DateUtil.getStepDay(toDay4,2); break; 
		case 6: 
			edate = DateUtil.getStepDay(toDay4,1); break; 
		default: edate=toDay4;
		}
		return edate;
	}
	
	private Date getStepAfterDay(Date sDate){
		Date ssdate =null;
		int dayOfWeek=DateUtil.getDayOfWeek(sDate,-1);
		switch(dayOfWeek) {
		case 2: 
			ssdate = DateUtil.getStepDay(sDate,-1); break; 
		case 3: 
			ssdate = DateUtil.getStepDay(sDate,-2); break; 
		case 4: 
			ssdate = DateUtil.getStepDay(sDate,-3); break; 
		case 5: 
			ssdate = DateUtil.getStepDay(sDate,-4); break; 
		case 6: 
			ssdate = DateUtil.getStepDay(sDate,-5); break; 
		case 7: 
			ssdate = DateUtil.getStepDay(sDate,1); break;
		default: ssdate=sDate;
		}
		return ssdate;
	}
}
	
