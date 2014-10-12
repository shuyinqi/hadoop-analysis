/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mapbar.analyzelog.report.entity.LaActiveStat;
import com.mapbar.analyzelog.report.entity.LaBasicStat;
import com.mapbar.analyzelog.report.entity.LaSectionStat;
import com.mapbar.analyzelog.report.entity.Trend;
import com.mapbar.analyzelog.report.services.ActiveStatService;
import com.mapbar.analyzelog.report.services.BasicStatService;
import com.mapbar.analyzelog.report.services.SectionStatService;
import com.mapbar.analyzelog.report.utils.DateUtil;

/**
 * <p>
 * 处理基本统计的跳转控制
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.services.BasicStatService
 * @see com.mapbar.analyzelog.report.services.SectionStatService
 * @see com.mapbar.analyzelog.report.services.ActiveStatService
 * @see com.mapbar.analyzelog.report.entity.LaBasicStat
 * @see com.mapbar.analyzelog.report.entity.LaSectionStat
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-02
 */
@Controller
public class BaseController extends AppController {

	@Resource
	private BasicStatService basicStatService;

	@Resource
	private SectionStatService sectionStatService;

	@Resource
	private ActiveStatService activeStatService;

	@RequestMapping(value = "/apps/{appid}/base")
	public String base(@PathVariable String appid, HttpServletRequest request,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "exp_list", required = false) String exp,
			Model model) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		Long appid_long = doApp("base", appid, model);

		List<LaBasicStat> list = basicStatService.findByApp(appid_long);
		if (exp != null && exp.equals("true")) {
			model.addAttribute("exp_list", list);
			return "excel";
		}
		// 基本统计
		model.addAttribute("basicStats",
				basicStatService.findByApp4Basic(appid_long));

		// 活跃统计
		List<LaActiveStat> actives = activeStatService.findByAppDate(
				appid_long, DateUtil.getStepBeforeDay(DateUtil.getDate(),0));
		if (actives != null)
			for (LaActiveStat active : actives) {
				if (active.getBefore() == 2)
					model.addAttribute("active2", active);
				else if (active.getBefore() == 7)
					model.addAttribute("active7", active);
				else if (active.getBefore() == 14)
					model.addAttribute("active14", active);
			}
		List<LaActiveStat> actives30 = null;
		try {
			actives30 = activeStatService.findByAppDate(
					appid_long, DateUtil.getCurrentMonthOfDay(1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (actives30 != null){
			for (LaActiveStat active30 : actives30) {
				 if (active30.getBefore() == 30)
					model.addAttribute("active30", active30);
			}
		}
			

		// 时段分析
		List<LaSectionStat> stats0 = sectionStatService.findByAppDate(
				appid_long, DateUtil.getStepDay(0));
		model.addAttribute("newImeis0",
				sectionStatService.parseNewImeiByStats(stats0));
		model.addAttribute("visits0",
				sectionStatService.parseVisitByStats(stats0));
		List<LaSectionStat> stats1 = sectionStatService.findByAppDate(
				appid_long, DateUtil.getStepDay(-1));
		model.addAttribute("newImeis1",
				sectionStatService.parseNewImeiByStats(stats1));
		model.addAttribute("visits1",
				sectionStatService.parseVisitByStats(stats1));

		Long count_new = 0L, count_launch = 0L, count_visit = 0L;
		for (LaBasicStat vo : list) {
			count_new += vo.getNew_imei_size();
			count_launch += vo.getVisit_imei_size();
			count_visit += vo.getVisit_size();
		}
		int pcount = list.size();
		model.addAttribute("pagecount", pcount);
		if (page == null || pcount <= 10)
			page = "1";
		model.addAttribute("page", page);
		int pagenum = Integer.parseInt(page);
		boolean boend = pcount / 10 == (pagenum - 1);
		List<LaBasicStat> list2 = list.subList((pagenum - 1) * 10,
				boend ? pcount : pagenum * 10);
		model.addAttribute("stats", list2);

		LaSectionStat allsize = sectionStatService.findAllSizeByApp(appid_long);
		if (allsize != null) {
			model.addAttribute("imeiSize", allsize.getNew_imei_size());
			model.addAttribute("visitSize", allsize.getVisit_size());
		} else {
			model.addAttribute("imeiSize", 0);
			model.addAttribute("visitSize", 0);
		}

		// 趋势分析
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		if (fromdate == null && todate == null) {
			fromdate = DateUtil.getStepMonth(-1).toString();
			todate = DateUtil.getDate().toString();
		}
		
		if(todate.equals(DateUtil.getDate().toString())){
			try {
				todate=DateUtil.getStepDay(DateUtil.parse10(todate), -1).toString();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if (!StringUtils.isBlank(fromdate) && !StringUtils.isBlank(todate)) {
			model.addAttribute("fromdate", fromdate);
			model.addAttribute("todate", todate);
			Trend trend = basicStatService.findByApp4Trend(appid_long,
					fromdate, todate);
			if (trend != null) {
				model.addAttribute("trendDate", trend.getDate());
				model.addAttribute("trendNewImeiSize", trend.getNewImeiSize());
				model.addAttribute("trendImeiSize", trend.getImeiSize());
				model.addAttribute("trendOldImeiSize", trend.getOldImeiSize());
				model.addAttribute("trendVisitSize", trend.getVisitSize());
				model.addAttribute("averageDuration",
						trend.getAverageDuration());
			}
		}

		
		model.addAttribute("count_new", count_new);
		model.addAttribute("count_launch", count_launch);
		model.addAttribute("count_visit", count_visit);
		return "base";
	}
}
