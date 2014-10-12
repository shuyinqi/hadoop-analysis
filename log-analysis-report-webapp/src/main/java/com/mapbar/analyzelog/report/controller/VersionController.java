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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mapbar.analyzelog.common.MediaType;
import com.mapbar.analyzelog.report.entity.DeviceVO;
import com.mapbar.analyzelog.report.entity.SearchVersionVO;
import com.mapbar.analyzelog.report.entity.VersionSVO;
import com.mapbar.analyzelog.report.entity.VersionVVO;
import com.mapbar.analyzelog.report.mapper.VersionMapper;

/**
 * <p>
 * 处理测试的跳转控制
 * </p>
 * 
 * @author 
 * @date 2012-02-02
 */
@Controller
public class VersionController extends MediaType {
	@Resource
	private VersionMapper versionMapper;

	@RequestMapping(value = "/apps/{appid}/version")
	public String version(
			@PathVariable String appid,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "version", required = false) String version,
			@RequestParam(value="page",required=false) String page,
			@RequestParam(value="queryname",required=false) String queryname,
			Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		try {
			queryname= new String((queryname==null?"":queryname).getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String appid_long = String.valueOf(doApp("version", appid, model));
		List<VersionSVO> list = new ArrayList<VersionSVO>();
		List<VersionVVO> list2 = null;
		List<VersionSVO> list3 = null;
		List<VersionSVO> list4 = null;
		list3 = versionMapper.selectAll(appid_long,null,null,null,null);
		list4 = versionMapper.selectVAll(appid_long,null,null,null,null);

		SearchVersionVO map = new SearchVersionVO();
		map.setAppid_long(appid_long);
		map.setFromdate(fromdate);
		map.setTodate(todate);
		list2 = versionMapper.selectVByDay2(map);

		 List<VersionVVO> list6=versionMapper.selectVersion(appid_long,fromdate,todate);

		for (VersionSVO vv : list4) {
			VersionSVO vo = null;
			boolean flag = false;
			for (VersionSVO v3 : list3) {
				if (v3.getVersion().equals(vv.getVersion())) {
					vo = v3;
					flag = true;
					break;
				} else
					flag = false;
			}
			if (!flag) {
				vo = vv;
				vo.setCounts(String.valueOf(vv.getNews()));
				vo.setNews(0);
			}
			list.add(vo);
		}

		Collections.sort(list);
		if(list.size()>=10)
		list=list.subList(0,10);
		
		
		
		
		//vdetails
		List<VersionSVO> list9 = versionMapper.selectAll(appid_long,fromdate,todate,version,queryname);
		List<VersionSVO> list10 = versionMapper.selectVAll(appid_long,fromdate,todate,version,queryname);
		List<VersionSVO> list11 = new ArrayList<VersionSVO>();
		for (VersionSVO vv : list10) {
			VersionSVO vo = null;
			boolean flag = false;
			for (VersionSVO v3 : list9) {
				if (v3.getVersion().equals(vv.getVersion())) {
					vo = v3;
					flag = true;
					break;
				} else
					flag = false;
			}
			if (!flag) {
				vo = vv;
				vo.setCounts(String.valueOf(vv.getNews()));
				vo.setNews(0);
			}
			List<VersionVVO> list8 = versionMapper.selectVByVD(appid_long,vv.getVersion(),fromdate,todate);
			vo.setVdes(list8);
			list11.add(vo);
		}
		Collections.sort(list11);
		
		
		
		int pcount=list11.size();
		model.addAttribute("pagecount",pcount);
		if(page==null||pcount<=10)page="1";
		model.addAttribute("page",page);
		int pagenum=Integer.parseInt(page);
		boolean boend=pcount/10==(pagenum-1);
		List<VersionSVO> list81=list11.subList((pagenum-1)*10,boend?pcount:pagenum*10);
		
		/*
		 * 	
		 */

		StringBuffer seriesData = new StringBuffer();
		seriesData.append("[");
		StringBuffer xAxisData = new StringBuffer();
		xAxisData.append("[");
		
		String stype = type == null ? "installation" : type;
		String vtype = version == null ? "" : version;
		List<String> sdlist = new ArrayList<String>();
		for (int i = 0; i < list2.size(); i++) {
			VersionVVO vo = list2.get(i);
			if (xAxisData.indexOf(vo.getDate().substring(
					vo.getDate().indexOf("-") + 1)) == -1) {
				xAxisData.append("'");
				xAxisData.append(vo.getDate().substring(
						vo.getDate().indexOf("-") + 1));
				sdlist.add(vo.getDate()
						.substring(vo.getDate().indexOf("-") + 1));
				xAxisData.append("'");
				if (i != list2.size() - 1) {
					xAxisData.append(",");
				}
			}
		}
		xAxisData.append("]");

		int length=list81.size()>=5?5:list81.size();
		for (int j = 0; j < length; j++) {
			VersionSVO vov = list.get(j);
			int i = 0;

			seriesData.append("{name:'").append(vov.getVersion())
					.append("',data:[");

			List<VersionVVO> list7 = versionMapper.selectVByV(appid_long,
					vov.getVersion(),fromdate,todate);
			if (sdlist.size() < list7.size()) {
				for (int m = 0; m < sdlist.size();) {
					for (; i < list7.size();) {
						VersionVVO vo = list7.get(i);
						if (sdlist.get(m).equals(
								vo.getDate().substring(
										vo.getDate().indexOf("-") + 1))) {
							seriesData.append(stype.equals("installation")?vo.getNews():(stype.equals("launch")?vo.getLaunchs():"")).append(",");
							m++;
							i++;
							break;
						} else if (!sdlist.get(m).equals(
								vo.getDate().substring(
										vo.getDate().indexOf("-") + 1))) {
							seriesData.append("0,");
							i++;
							break;
						}
					}
				}
			}else {
				for (int m = 0; m < sdlist.size();m++) {
					for (; i < list7.size();) {
						VersionVVO vo = list7.get(i);
						if (sdlist.get(m).equals(
								vo.getDate().substring(
										vo.getDate().indexOf("-") + 1))) {
							seriesData.append(stype.equals("installation")?vo.getNews():(stype.equals("launch")?vo.getLaunchs():"")).append(",");
							i++;
							break;
						} else if (!sdlist.get(m).equals(
								vo.getDate().substring(
										vo.getDate().indexOf("-") + 1))) {
							seriesData.append("0,");
							break;
						}
					}
				}
			}
			seriesData.append("]").append("}");
			if (j != list.size() - 1)
				seriesData.append(",");
		}
		seriesData.append("]");

		model.addAttribute("xAxisData", xAxisData.toString());
		model.addAttribute("seriesData", seriesData.toString());
		
		model.addAttribute("stype",stype);
		model.addAttribute("stats", list);
		model.addAttribute("version",vtype);
		model.addAttribute("vlist",list6);
		model.addAttribute("vdetails",list81);
		model.addAttribute("queryname",queryname);
		return VERSIONS;
	}
}
