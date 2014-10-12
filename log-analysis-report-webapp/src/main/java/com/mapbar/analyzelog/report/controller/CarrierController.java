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
import com.mapbar.analyzelog.report.entity.CarrierVO;
import com.mapbar.analyzelog.report.mapper.CarrierMapper;

/**
 * <p>
 * 处理运营商统计的跳转控制
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.CarrierMapper
 */
@Controller
public class CarrierController extends MediaType{

	@Resource
	private CarrierMapper carrierMapper;

	@RequestMapping(value = "/apps/{appid}/carrier")
	public String carrier(@PathVariable String appid,
			@RequestParam(value="fromdate",required=false) String fromdate,
			@RequestParam(value="todate",required=false) String todate, Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String appid_long = String.valueOf(doApp("carrier", appid, model));
		model.addAttribute("app.id", appid_long);
		List<CarrierVO> list=null;
		if(fromdate==null||todate==null)list=carrierMapper.select(appid_long);
		else list=carrierMapper.selectByDay(fromdate,todate,appid_long);
		int launchRatio=0;int newsRatio=0;
		for(CarrierVO vo:list){
			launchRatio+=vo.getLaunchcount();
			newsRatio+=vo.getNewcount();
		}
		model.addAttribute("launchRatio",launchRatio);
		model.addAttribute("newsRatio",newsRatio);
		model.addAttribute("stats",list);
		return CARRIER;
	}
}
