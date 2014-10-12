/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mapbar.analyzelog.report.entity.ChannlReptile;
import com.mapbar.analyzelog.report.entity.ChannelVO;
import com.mapbar.analyzelog.report.entity.SummaryChannelStat;
import com.mapbar.analyzelog.report.entity.VersionVVO;
import com.mapbar.analyzelog.report.mapper.ChannelMapper;
import com.mapbar.analyzelog.report.services.ChannelStatService;
import com.mapbar.analyzelog.report.utils.DateUtil;

/**
 * <p>
 * 处理测试的跳转控制
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.services.ChannelStatService
 * @see com.mapbar.analyzelog.report.entity.LaChannelStat
 * @see com.mapbar.analyzelog.report.entity.SummaryChannelStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-22
 */
@Controller
public class ChannelController extends AppController {

	@Resource
	private ChannelStatService channelStatService;
	@Resource
	private ChannelMapper channelMapper;
	@RequestMapping(value = "/apps/{appid}/channel")
	public String channel(@PathVariable String appid,@RequestParam(value="exp_list",required=false) Integer exp,
			@RequestParam(value="exp_list1",required=false) String exp_list1,
			@RequestParam(value="exp_list2",required=false) String exp_list2,
			@RequestParam(value="version",required=false) String version,
			HttpServletRequest request, Model model) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		Long appid_long = doApp("channel", appid, model);
		/*结束
		 * */
		// 渠道变化趋势
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		if (fromdate == null && todate == null) {
			fromdate = DateUtil.getStepMonth(-1).toString();
			todate = DateUtil.getDate().toString();
		}
		if (!StringUtils.isBlank(fromdate) && !StringUtils.isBlank(todate)) {
			model.addAttribute("fromdate", fromdate);
			model.addAttribute("todate", todate);
		}
		/*if(exp_list1!=null&&!exp_list1.isEmpty()){
			List<VersionVVO> list1 = null;
			list1 = channelMapper.selectT2ByDayExp(String.valueOf(appid_long),"market","unknown",fromdate,todate,exp_list1);
			model.addAttribute("exp_detail",list1);return "excel7";
		}
		if(exp_list2!=null&&!exp_list2.isEmpty()){
			List<VersionVVO> list2 = null;
			if(fromdate==null||todate==null){
				list2 = channelMapper.selectTExp(String.valueOf(appid_long),"pre_installed",exp_list2);
			}else{
				list2 = channelMapper.selectTByDayExp(String.valueOf(appid_long),"pre_installed",fromdate,todate,exp_list2);
			}
			model.addAttribute("exp_detail",list2);return "excel7";
		}*/
		/***
		 * 核心代码
		 */
//		List<ChannelVO> names=channelMapper.queryNames(String.valueOf(appid_long));
		// 渠道统计
		List<SummaryChannelStat> listMarketNew=channelStatService.findByApp4MarketNew(appid_long,todate,version);
		List<VersionVVO> listc = null;
		
		if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
		listc = channelMapper.selectTByDay3(String.valueOf(appid_long),"market","unknown",fromdate,todate);
		}else{
			listc = channelMapper.selectTByDay3Version(String.valueOf(appid_long),"market","unknown",fromdate,todate,version);
		}
			/*lista = channelMapper.selectT2ByDay(String.valueOf(appid_long),"market","unknown",fromdate,todate);
			listb = channelMapper.selectTByDay(String.valueOf(appid_long),"pre_installed",fromdate,todate);
		*/
		/*
		 * add start
		 */
		List<SummaryChannelStat> list11=new ArrayList<SummaryChannelStat>();
		for(SummaryChannelStat vo:listMarketNew){
			List<VersionVVO> list_tmp=new ArrayList<VersionVVO>();
			for(VersionVVO vo2:listc){
				if(vo.getName().equals(vo2.getVersion()))list_tmp.add(vo2);
			}
			vo.setVlist(list_tmp);
			list11.add(vo);
		}
		
		model.addAttribute("summaryMarketNew",list11);
		
		List<String> channelVersion = channelMapper.queryVersionDate(appid,fromdate,todate);
		channelVersion.add("all");
		if(version!=null){
			model.addAttribute("version",version);
		}else{
			model.addAttribute("version","all");
		}
		model.addAttribute("channelVersion",channelVersion);
		
		List<VersionVVO> lista = null;
		List<VersionVVO> listb = null;
		List<VersionVVO> listd = null;
		
		
		if(exp!=null&&exp!=0){
			switch(exp)
			{
			case 1:{
   			    model.addAttribute("exp_list",listc);
			    break;
			}
			case 2:{
				if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
					lista = channelMapper.selectT2ByDay(String.valueOf(appid_long),"market","unknown",fromdate,todate);
					}else{
					lista = channelMapper.selectT2ByDayVersion(String.valueOf(appid_long),"market","unknown",fromdate,todate,version);
					}
				model.addAttribute("exp_list",lista);
				break;
			}
			case 3:{
				if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
					listb = channelMapper.selectTByDay(String.valueOf(appid_long),"pre_installed",fromdate,todate);
				}else{
					listb = channelMapper.selectTByDayVersion(String.valueOf(appid_long),"pre_installed",fromdate,todate,version);
				}
				model.addAttribute("exp_list",listb);
				break;
			}
			case 4:{
				if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
			     	listd = channelMapper.selectTByDay(String.valueOf(appid_long),"pre_installed",fromdate,todate);
				}else{
					listd = channelMapper.selectTByDayVersion(String.valueOf(appid_long),"pre_installed",fromdate,todate,version);
				}
				model.addAttribute("exp_list",listd);break;
			}
			}
			return "excel6";
		}
		return "channel";
	}
	/***
	 * 预装访问用户请求数据
	 * @param appid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/apps/{appid}/channel/summaryInstalledVisit", method = RequestMethod.GET)
	public @ResponseBody List<SummaryChannelStat> summaryInstalledVisit(@PathVariable String appid,HttpServletRequest request, Model model){
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		String version = request.getParameter("version");
		List<VersionVVO> listb = null;
		Long appid_long = Long.parseLong(appid);
		List<SummaryChannelStat> listInstalledVisit=channelStatService.findByApp4InstalledVisit(appid_long,todate,version);
		if(fromdate==null||todate==null){
			listb = channelMapper.selectT(String.valueOf(appid_long),"pre_installed");
		}else{
			if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
		     	listb = channelMapper.selectTByDay(String.valueOf(appid_long),"pre_installed",fromdate,todate);
			}else{
				listb = channelMapper.selectTByDayVersion(String.valueOf(appid_long),"pre_installed",fromdate,todate,version);
			}
			}
		List<SummaryChannelStat> list14=new ArrayList<SummaryChannelStat>();
		for(SummaryChannelStat vo:listInstalledVisit){
			List<VersionVVO> list_tmp=new ArrayList<VersionVVO>();
			for(VersionVVO vo2:listb){
				if(vo.getName().equals(vo2.getVersion()))list_tmp.add(vo2);
			}
			vo.setVlist(list_tmp);
			list14.add(vo);
	}
		return list14;
	}
	
	/***
	 * 预装新增用户请求数据
	 * @param appid
	 * @param request
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/apps/{appid}/channel/summaryInstalledNew", method = RequestMethod.GET)
	public @ResponseBody List<SummaryChannelStat> summaryInstalledNew(@PathVariable String appid,HttpServletRequest request, Model model){
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		String version = request.getParameter("version");
		List<VersionVVO> listb = null;
		Long appid_long = Long.parseLong(appid);
		List<SummaryChannelStat> listInstalledNew=channelStatService.findByApp4InstalledNew(appid_long,todate,version);
		if(fromdate==null||todate==null){
			listb = channelMapper.selectT(String.valueOf(appid_long),"pre_installed");
		}else{
			if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
				listb = channelMapper.selectTByDay(String.valueOf(appid_long),"pre_installed",fromdate,todate);
			}else{
				listb = channelMapper.selectTByDayVersion(String.valueOf(appid_long),"pre_installed",fromdate,todate,version);
			}
		}
		
		List<SummaryChannelStat> list13=new ArrayList<SummaryChannelStat>();
		for(SummaryChannelStat vo:listInstalledNew){
			List<VersionVVO> list_tmp=new ArrayList<VersionVVO>();
			for(VersionVVO vo2:listb){
				if(vo.getName().equals(vo2.getVersion()))list_tmp.add(vo2);
			}
			vo.setVlist(list_tmp);
			list13.add(vo);
		}
		return list13;
	}
	
	/***
	 * 市场活跃用户请求数据
	 * @param appid
	 * @param request
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/apps/{appid}/channel/summaryMarketVisit", method = RequestMethod.GET)
	public @ResponseBody List<SummaryChannelStat> summaryMarketVisit(@PathVariable String appid,HttpServletRequest request, Model model){
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		String version = request.getParameter("version");
		List<VersionVVO> lista = null;
		Long appid_long = Long.parseLong(appid);
		List<SummaryChannelStat> listMarketVisit=channelStatService.findByApp4MarketVisit(appid_long,todate,version);
		if(fromdate==null||todate==null){
			lista = channelMapper.selectT2(String.valueOf(appid_long),"market","unknown");
		}else{
			if(StringUtils.isBlank(version)||"all".equalsIgnoreCase(version)){
			lista = channelMapper.selectT2ByDay(String.valueOf(appid_long),"market","unknown",fromdate,todate);
			}else{
			lista = channelMapper.selectT2ByDayVersion(String.valueOf(appid_long),"market","unknown",fromdate,todate,version);
			}
			}
		List<SummaryChannelStat> list12=new ArrayList<SummaryChannelStat>();
		for(SummaryChannelStat vo:listMarketVisit){
			List<VersionVVO> list_tmp=new ArrayList<VersionVVO>();
			for(VersionVVO vo2:lista){
				if(vo.getName().equals(vo2.getVersion()))list_tmp.add(vo2);
			}
			vo.setVlist(list_tmp);
			list12.add(vo);
		}
				return list12;
	}
	
	@RequestMapping(value = "/apps/{appid}/channel/ajax", method = RequestMethod.GET)
	public @ResponseBody String ajax(@PathVariable String appid,
			String ajax_method, String fromdate, String todate,
			String[] channels,HttpServletRequest request, Model model) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		// 渠道变化趋势
		// String fromdate = request.getParameter("fromdate");
		// String todate = request.getParameter("todate");
		if (fromdate == null && todate == null) {
			fromdate = DateUtil.getStepMonth(-1).toString();
			todate = DateUtil.getDate().toString();
		}
		if (!StringUtils.isBlank(fromdate) && !StringUtils.isBlank(todate)) {
			model.addAttribute("fromdate", fromdate);
			model.addAttribute("todate", todate);
			// Trend trend = basicStatService.findByApp4Trend(appid_long,
			// fromdate, todate);
			// if (trend != null) {
			// model.addAttribute("trendDate", trend.getDate());
			// model.addAttribute("trendNewImeiSize", trend.getNewImeiSize());
			// model.addAttribute("trendImeiSize", trend.getImeiSize());
			// model.addAttribute("trendOldImeiSize", trend.getOldImeiSize());
			// model.addAttribute("trendVisitSize", trend.getVisitSize());
			// model.addAttribute("imeiSize", trend.getAllImeiSize());
			// model.addAttribute("visitSize", trend.getAllVisitSize());
			// }
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("dates", "aaaaa");
		map.put("stats", "ccccc");
		return map.toString();
	}
	
	

  /***
   * 渠道管理设置
   * @param appid
   * @param request
   * @param model
   * @return
   *
   */
	@RequestMapping(value = "/apps/{appid}/channel/reptail")
	public  String reptail(@PathVariable String appid,
			HttpServletRequest request, Model model) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		List<ChannlReptile> listReptitle = channelMapper.queryAllForReptile(appid);
		model.addAttribute("reptail", listReptitle);
		/***
		 * 配置菜单
		 */
		String appid_long = String.valueOf(doApp("reptail", appid, model));
		model.addAttribute("app.id", appid_long);
		
		return 	"reptail";
	}
	/***
	 *  修改或者添加渠道管理ajax
	 * @param appid
	 * @param name   渠道名称
	 * @param total  总数
	 * @param addCount 今日新增
	 * @param id    
	 * @param date   日期
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/apps/{appid}/channel/reptailAjax", method = RequestMethod.POST)
	public @ResponseBody String reptailAjax(@PathVariable String appid,String name,String total,String addCount,String id,String date, HttpServletRequest request,Model model) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		if(id==""){
			channelMapper.addReptile(appid, name, Integer.parseInt(addCount), Integer.parseInt(total), date);
		}else{
			channelMapper.updateForReptile(Integer.parseInt(addCount), Integer.parseInt(total),Integer.parseInt(id));
		}
		// 渠道变化趋势
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("dates", "aaaaa");
		map.put("stats", "ccccc");
		return map.toString();
	}
	/****
	 * 渠道管理，安装日期查询
	 * @param appid  
	 * @param date
	 * @param request   
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/apps/{appid}/channel/reptailDate")
	public  String reptailDate(@PathVariable String appid,
			String date, HttpServletRequest request,Model model) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		List<ChannlReptile> listReptitle = channelMapper.queryDateForReptile(appid,date);
		model.addAttribute("reptail", listReptitle);
		/***
		 * 配置菜单
		 */
		String appid_long = String.valueOf(doApp("reptail", appid, model));
		model.addAttribute("app.id", appid_long);
		
		return 	"reptail";
	}
}
