/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mapbar.analyzelog.common.MediaType;
import com.mapbar.analyzelog.report.entity.DeviceVO;
import com.mapbar.analyzelog.report.mapper.DeviceMapper;

/**
 * <p>
 * 处理设备统计的跳转控制
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.DeviceMapper
 */
@Controller
public class DeviceBrandController extends MediaType{

	@Resource
	private DeviceMapper deviceMapper;

	@RequestMapping(value = "/apps/{appid}/device-brand")
	public String device(@PathVariable String appid,
			@RequestParam(value="fromdate",required=false) String fromdate,
			@RequestParam(value="todate",required=false) String todate,
			@RequestParam(value="page",required=false) String page,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value="exp_list",required=false) String exp,
			@RequestParam(value="queryname",required=false) String queryname,Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String appid_long = String.valueOf(doApp("device-brand", appid, model));
		List<DeviceVO> list=null;
		try {
			queryname= new String((queryname==null?"":queryname).getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deviceMapper.mx1_delete();
		deviceMapper.mx1_getSetting();
		String stype = type == null ? "installation" : type;
		String brand=queryname.isEmpty()?null:queryname;
		if(fromdate==null||todate==null){
			if(!stype.equals("installation"))
			list=deviceMapper.selectmx1(appid, null, brand, null, null);
			else list=deviceMapper.selectmx3(appid, null,brand, null, null);
		}
		else{
			if(!stype.equals("installation"))
			list=deviceMapper.selectmx1(appid, null, brand, fromdate, todate);
			else list=deviceMapper.selectmx3(appid, null,brand, fromdate, todate);
		}
		int launchRatio=0;int newsRatio=0;
		DeviceVO vo2=deviceMapper.selectcount(appid_long,fromdate, todate);
			if(vo2!=null){
				launchRatio+=vo2.getLaunchcount();
				newsRatio+=vo2.getNewcount();
				}
			if(!stype.equals("installation"))
			model.addAttribute("launchRatio",launchRatio);
			else model.addAttribute("launchRatio",newsRatio);
		if(exp!=null&&exp.equals("true")){
			model.addAttribute("exp_list",list);
			return "excel8";
		}
		
		
		int pcount=list.size();
		model.addAttribute("pagecount",pcount);
		if(page==null||pcount<=10)page="1";
		model.addAttribute("page",page);
		int pagenum=Integer.parseInt(page);
		boolean boend=pcount/10==(pagenum-1);
		List<DeviceVO> list2=list.subList((pagenum-1)*10,boend?pcount:pagenum*10);
		
		List<DeviceVO> list9=new ArrayList<DeviceVO>();
		for(DeviceVO vo:list2){
			List<DeviceVO> list5=deviceMapper.selectmx2(appid_long,null,vo.getDevice(),fromdate,todate);
			vo.setVlist(list5);
			list9.add(vo);
		}
		model.addAttribute("queryname",queryname);
		model.addAttribute("stats1",list9);
		model.addAttribute("stype",stype);
		return DEVICE_BRAND;
	}
}
