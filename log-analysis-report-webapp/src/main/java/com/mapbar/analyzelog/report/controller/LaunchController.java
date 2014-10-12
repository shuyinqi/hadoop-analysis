/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.controller;



import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mapbar.analyzelog.common.UserAnalysisType;
import com.mapbar.analyzelog.report.entity.LaunchVO;
import com.mapbar.analyzelog.report.mapper.LaunchMapper;

/**
 * <p>
 * 处理启动次数的跳转控制
 * @author <a href="mailto:renzg@mapbar.com">renzg</a> 
 * @date 2012-02-22
 */
@Controller
public class LaunchController  extends UserAnalysisType{
	@Resource
	private LaunchMapper launchMapper;
	
	@RequestMapping(value = "/apps/{appid}/launch")
	public String launch(@PathVariable String appid,
			@RequestParam(value = "fromdate", required=false) String fromdate,
			@RequestParam(value = "todate", required=false) String todate, Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		doApp("launch", appid, model);
		List<LaunchVO> list = null;
		if(fromdate == null || todate == null){
			list = launchMapper.select(appid);
		}else{
			list = launchMapper.selectByDay(appid, fromdate, todate);
		}
		
//		List<Date> dlist = getTime(fromdate, todate);		
//		List<LaunchVO> lv = getVO(fromdate, todate);
		
		int c1_2 = 0;      //频率1-2次的总用户数
		int c2_2 = 0;      //频率2-2次的总用户数
		int c3_3 = 0;      //频率3-3次的总用户数
		int c4_4 = 0;      //频率4-4次的总用户数
		int c5_5 = 0;   //频率5-5次的总用户数
		int c6_9 = 0;      //频率6-9次的总用户数
		int c10_19 = 0;   //频率10-19次的总用户数
		int c20_49 = 0;      //频率20-49次的总用户数
		int c50_ = 0;   //频率50次以上的总用户数		
		for(LaunchVO vo : list){
			c1_2 += vo.getC1_2();      //频率1-2次的用户数
			c2_2 += vo.getC2_2();
			c3_3 += vo.getC3_3();
			c4_4 += vo.getC4_4();
			c5_5 += vo.getC5_5();   //频率3-5次的用户数
			c6_9 += vo.getC6_9();      //频率6-9次的用户数
			c10_19 += vo.getC10_19();   //频率10-19次的用户数
			c20_49 += vo.getC20_49();      //频率20-49次的用户数
			c50_ += vo.getC50_();   //频率50次以上的用户数
//			Date time = getValue(date, dlist);
//			for(int i=0;i<lv.size();i++){
//				LaunchVO lvo = lv.get(i);
//				Date s = lvo.getDate();
//				if(s.compareTo(time)==0){
//					int lc1_2 = lvo.getC1_2();      //频率1-2次的总用户数
//					int lc3_5 = lvo.getC3_5();   //频率3-5次的总用户数
//					int lc6_9 = lvo.getC6_9();      //频率6-9次的总用户数
//					int lc10_19 = lvo.getC10_19();   //频率10-19次的总用户数
//					int lc20_49 = lvo.getC20_49();      //频率20-49次的总用户数
//					int lc50_ = lvo.getC50_();   //频率50次以上的总用户数
//					lvo.setC1_2(vo.getC1_2()+lc1_2);
//					lvo.setC3_5(vo.getC3_5()+lc3_5);
//					lvo.setC6_9(vo.getC6_9()+lc6_9);
//					lvo.setC10_19(vo.getC10_19()+lc10_19);
//					lvo.setC20_49(vo.getC20_49()+lc20_49);
//					lvo.setC50_(vo.getC50_()+lc50_);					
//				}
//			}
			
		}
		int count = c1_2+c2_2+c3_3+c4_4+c5_5+c6_9+c10_19+c20_49+c50_;
		model.addAttribute("c1_2",c1_2);
		model.addAttribute("c2_2",c2_2);
		model.addAttribute("c3_3",c3_3);
		model.addAttribute("c4_4",c4_4);
		model.addAttribute("c5_5",c5_5);
		model.addAttribute("c6_9",c6_9);
		model.addAttribute("c10_19",c10_19);
		model.addAttribute("c20_49",c20_49);
		model.addAttribute("c50_",c50_);
		model.addAttribute("count",count==0 ? 1 : count);
		model.addAttribute("stats",list);
		
		return "launch";
	}
	
	public Date getValue(Date tempDate, List<Date> list){
		int len = list==null?0:list.size();
		Date time = null;
		for(int i=0; i<len; i++){
			Date d1 = list.get(i);
			Date d2 = list.get(++i);
			if(tempDate.compareTo(d1)>=0){
				if(tempDate.compareTo(d2)<=0){
//					DateFormat ft = new SimpleDateFormat("HH:mm:ss");//显示时间格式
//						time = ft.format(d2);
					return d2;
				}
			}
		}
		return time;
	}
	
	public List<LaunchVO> getVO(String start, String end){
		List<LaunchVO> list = new ArrayList<LaunchVO>();
		try {
//			String sdate = "2012-01-12";
//			String edate = "2012-02-23";
			if(start==null){
				start = "2012-01-01";
			}
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date sbdate = formatter.parse(start);
			Date ebdate = new Date();
			if(end==null||end.equals("")){
//				ebdate = formatter.parse(end);
			}else{
				ebdate = formatter.parse(end);
			}
			
			
			  Calendar c_begin = new GregorianCalendar();
			  Calendar c_end = new GregorianCalendar();
			  DateFormatSymbols dfs = new DateFormatSymbols();
			  String[] weeks = dfs.getWeekdays();
			  c_begin.setTime(sbdate);
			  c_end.setTime(ebdate);
			 
			  while (c_begin.before(c_end)) {
				  Date dd = new java.sql.Date(c_begin.getTime().getTime());
				  if(weeks[c_begin.get(Calendar.DAY_OF_WEEK)].equals("星期日")){	
					 LaunchVO lv = new LaunchVO();
					 lv.setDate(dd);
					 list.add(lv);
				  } 				 
			  }
			  
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	public List<Date> getTime(String start, String end){
		List<Date> list = new ArrayList<Date>();
		try {
//			String sdate = "2012-01-12";
//			String edate = "2012-02-23";
			if(start==null){
				start = "2012-01-01";
			}			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date sbdate = formatter.parse(start);
			Date ebdate = new Date();
			if(end==null||end.equals("")){
//				ebdate = formatter.parse(end);
			}else{
				ebdate = formatter.parse(end);
			}
			
			  Calendar c_begin = new GregorianCalendar();
			  Calendar c_end = new GregorianCalendar();
			  DateFormatSymbols dfs = new DateFormatSymbols();
			  String[] weeks = dfs.getWeekdays();
			  c_begin.setTime(sbdate);
			  c_end.setTime(ebdate);
//			  c_begin.set(2012, 0, 23); // Calendar的月从0-11，所以4月是3.
//			  c_end.set(2012, 1, 20); // Calendar的月从0-11，所以5月是4.

			  c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天
			  Date ss = new java.sql.Date(c_begin.getTime().getTime());
			  Date ee = new java.sql.Date(c_end.getTime().getTime());			
			  list.add(ss);
			  while (c_begin.before(c_end)) {
				  Date dd = new java.sql.Date(c_begin.getTime().getTime());
				  if(weeks[c_begin.get(Calendar.DAY_OF_WEEK)].equals("星期日")){					
					 list.add(dd);
				  } 
				  if(weeks[c_begin.get(Calendar.DAY_OF_WEEK)].equals("星期一")){					 
					  list.add(dd);
				  } 
			  }
			  list.add(ee);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
}
