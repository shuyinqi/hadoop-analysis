/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapbar.analyzelog.common.UserAnalysisType;
import com.mapbar.analyzelog.report.entity.DurationVO;
import com.mapbar.analyzelog.report.entity.SummaryChannelStat;
import com.mapbar.analyzelog.report.entity.VersionVVO;
import com.mapbar.analyzelog.report.mapper.DurationMapper;

/**
 * <p>
 * 处理使用时长的跳转控制
 * @author <a href="mailto:renzg@mapbar.com">renzg</a> 
 * @date 2012-02-22
 */
@Controller
public class DurationController  extends UserAnalysisType{
	@Resource
	private DurationMapper durationMapper;
	private static final String LA_DURATION_TIME="la_duration_time_stat";
	private static final String LA_DURATION_DAY="la_duration_day_stat";
	private static final String LA_DURATION_WEEK="la_duration_week_stat";
	
	@RequestMapping(value = "/apps/{appid}/duration")
	public String duration(@PathVariable String appid,
			@RequestParam(value = "fromdate", required=false) String fromdate,
			@RequestParam(value = "todate", required=false) String todate,
			@RequestParam(value = "channel_type", required=false) String channel_type,
			@RequestParam(value = "version", required=false) String version,
			@RequestParam(value = "channel_name", required=false) String channel_name, Model model,HttpServletRequest request) {
		
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		doApp("duration", appid, model);
		List<DurationVO> list = null;
		if(fromdate == null || todate == null){
			list = durationMapper.select(appid);
		}else{
			list = durationMapper.selectByDay(LA_DURATION_TIME,appid, fromdate,todate,version,channel_type,channel_name);
		}
		int totalCount = 0;		
		int c0_3 = 0;	
		int c4_9 = 0;	
		int c10_29 = 0;	
		int c30_59 = 0;	
		int c60_179 = 0;	
		int c180_599 = 0;	
		int c600_1799 = 0;	
		int c1800_ = 0;	
		for(DurationVO vo : list){
			totalCount += vo.getCount();
			String segment = vo.getSegment();
			if(segment.equals("0-3")){
				c0_3 += vo.getCount();
			}else if(segment.equals("4-9")){
				c4_9 += vo.getCount();
			}else if(segment.equals("10-29")){
				c10_29 += vo.getCount();
			}else if(segment.equals("30-59")){
				c30_59 += vo.getCount();
			}else if(segment.equals("60-179")){
				c60_179 += vo.getCount();
			}else if(segment.equals("180-599")){
				c180_599 += vo.getCount();
			}else if(segment.equals("600-1799")){
				c600_1799 += vo.getCount();
			}else if(segment.equals("1800+")){
				c1800_ += vo.getCount();
			}
		}	
		model.addAttribute("c0_3", c0_3);
		model.addAttribute("c4_9", c4_9);
		model.addAttribute("c10_29", c10_29);
		model.addAttribute("c30_59", c30_59);
		model.addAttribute("c60_179", c60_179);
		model.addAttribute("c180_599", c180_599);
		model.addAttribute("c600_1799", c600_1799);
		model.addAttribute("c1800_", c1800_);
		model.addAttribute("totalCount", totalCount==0 ? 1 : totalCount);
		model.addAttribute("stats", list);
		
		
		List<DurationVO> lista = null;
		if(fromdate == null || todate == null){
			lista = durationMapper.select(appid);
		}else{
			lista = durationMapper.selectByDay(LA_DURATION_DAY,appid, fromdate,todate,version,channel_type,channel_name);
		}
		int atotalCount = 0;		
		int ac0_3 = 0;	
		int ac4_9 = 0;	
		int ac10_29 = 0;	
		int ac30_59 = 0;	
		int ac60_179 = 0;	
		int ac180_599 = 0;	
		int ac600_1799 = 0;	
		int ac1800_ = 0;	
		for(DurationVO vo : lista){
			atotalCount += vo.getCount();
			String segment = vo.getSegment();
			if(segment.equals("0-3")){
				ac0_3 += vo.getCount();
			}else if(segment.equals("4-9")){
				ac4_9 += vo.getCount();
			}else if(segment.equals("10-29")){
				ac10_29 += vo.getCount();
			}else if(segment.equals("30-59")){
				ac30_59 += vo.getCount();
			}else if(segment.equals("60-179")){
				ac60_179 += vo.getCount();
			}else if(segment.equals("180-599")){
				ac180_599 += vo.getCount();
			}else if(segment.equals("600-1799")){
				ac600_1799 += vo.getCount();
			}else if(segment.equals("1800+")){
				ac1800_ += vo.getCount();
			}
		}	
		model.addAttribute("ac0_3", ac0_3);
		model.addAttribute("ac4_9", ac4_9);
		model.addAttribute("ac10_29", ac10_29);
		model.addAttribute("ac30_59", ac30_59);
		model.addAttribute("ac60_179", ac60_179);
		model.addAttribute("ac180_599", ac180_599);
		model.addAttribute("ac600_1799", ac600_1799);
		model.addAttribute("ac1800_", ac1800_);
		model.addAttribute("atotalCount", atotalCount==0 ? 1 : atotalCount);
		model.addAttribute("astats", lista);
		
		
		List<DurationVO> listc = null;
		if(fromdate == null || todate == null){
			listc = durationMapper.select(appid);
		}else{
			listc = durationMapper.selectByDay(LA_DURATION_WEEK,appid, fromdate,todate,version,channel_type,channel_name);
		}
		int ctotalCount = 0;		
		int cc0_3 = 0;	
		int cc4_9 = 0;	
		int cc10_29 = 0;	
		int cc30_59 = 0;	
		int cc60_179 = 0;	
		int cc180_599 = 0;	
		int cc600_1799 = 0;	
		int cc1800_ = 0;	
		int cc7200_ = 0;	
		for(DurationVO vo : listc){
			ctotalCount += vo.getCount();
			String segment = vo.getSegment();
			if(segment.equals("0-30")){
				cc0_3 += vo.getCount();
			}else if(segment.equals("31-60")){
				cc4_9 += vo.getCount();
			}else if(segment.equals("61-180")){
				cc10_29 += vo.getCount();
			}else if(segment.equals("181-600")){
				cc30_59 += vo.getCount();
			}else if(segment.equals("601-1800")){
				cc60_179 += vo.getCount();
			}else if(segment.equals("1801-3600")){
				cc180_599 += vo.getCount();
			}else if(segment.equals("3601-5400")){
				cc600_1799 += vo.getCount();
			}else if(segment.equals("5401-7200")){
				cc1800_ += vo.getCount();
			}else if(segment.equals("7200+")){
				cc7200_ +=vo.getCount();
			}
		}	
		model.addAttribute("cc0_3", cc0_3);
		model.addAttribute("cc4_9", cc4_9);
		model.addAttribute("cc10_29", cc10_29);
		model.addAttribute("cc30_59", cc30_59);
		model.addAttribute("cc60_179", cc60_179);
		model.addAttribute("cc180_599", cc180_599);
		model.addAttribute("cc600_1799", cc600_1799);
		model.addAttribute("cc1800_", cc1800_);
		model.addAttribute("cc7200_", cc7200_);
		model.addAttribute("ctotalCount", ctotalCount==0 ? 1 : ctotalCount);
		model.addAttribute("cstats", listc);
		
		List<String> selectDurationByChannel =  durationMapper.selectDurationCondition(appid, version, channel_type);
		List<String> selectDurationByVersion =  durationMapper.selectDurationByVersion(appid);
		if(selectDurationByChannel.size()>1){
			selectDurationByChannel.add("all");
		}
		if(selectDurationByVersion.size()>1){
			selectDurationByVersion.add("all");
		}
		if(channel_type==null){
			model.addAttribute("version", "all");
			model.addAttribute("channelType", "all");
			model.addAttribute("channelName", "all");
		}else{
			model.addAttribute("version", version);
			model.addAttribute("channelType", channel_type);
			model.addAttribute("channelName", channel_name);
		}
		model.addAttribute("selectDurationByChannel", selectDurationByChannel);
		model.addAttribute("selectDurationByVersion", selectDurationByVersion);
		return "duration";
	}
	/***
	 * 动态获取渠道名称
	 * @param appid
	 * @param version
	 * @param channelType
	 * @param request
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/apps/{appid}/durationCondition", method = RequestMethod.GET)
	public @ResponseBody List durationCondition(@PathVariable String appid,String version,String channelType,HttpServletRequest request, Model model){
		List<String> channel_name= new ArrayList<String>();
			 channel_name=durationMapper.selectDurationCondition(appid, version, channelType);
		return channel_name;
	}
}
