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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.mapbar.analyzelog.report.entity.CommonVO;
import com.mapbar.analyzelog.report.entity.LaPageCanvasVO;
import com.mapbar.analyzelog.report.entity.LaPageVisitVO;
import com.mapbar.analyzelog.report.entity.PathVO;
import com.mapbar.analyzelog.report.mapper.PathMapper;

/**
 * <p>
 * 处理访问页面的跳转控制
 * @author <a href="mailto:renzg@mapbar.com">renzg</a> 
 * @date 2012-02-22
 */
@Controller
public class PathController  extends UserAnalysisType{
	@Resource
	private PathMapper pathMapper;
	
	@RequestMapping(value = "/apps/{appid}/path")
	public String path(@PathVariable String appid,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "version", required=false) String version,
			@RequestParam(value="exp_list",required=false) String exp,Model model,HttpServletRequest request) {

		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		doApp("path", appid, model);
		List<PathVO> list = null;
		if(version == null||version.equals("")){
			if(fromdate==null||todate==null)list = pathMapper.select(appid);
			else list = pathMapper.selectByDay(appid,fromdate,todate);
		}else{
			list = pathMapper.selectByVersion(appid, version,fromdate,todate);
		}
		
		long totalCount = 0;
		long totalTime   = 0;
		for(PathVO vo : list){
			totalCount += vo.getCount();
			totalTime   += vo.getTime();
		}
		model.addAttribute("totalCount",totalCount == 0 ? 1 : totalCount);
		model.addAttribute("totalTime",totalTime==0 ? 1 : totalTime);
		if(exp!=null&&exp.equals("true")){
			model.addAttribute("exp_list",list);
			return "excel2";
		}
		model.addAttribute("stats",list);
		List<CommonVO> clist = pathMapper.searchVersion(appid);
		model.addAttribute("clist",clist);	
		model.addAttribute("version",version);
		return "path";
	}
	
	
	/***
	 * 获得图表的数据源
	 * @param appid
	 * @param request
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/apps/{appid}/path/pathAjax",method=RequestMethod.GET)
	public @ResponseBody String pathAjax(@PathVariable String appid, HttpServletRequest request,Model model) {
		List<LaPageVisitVO> pvo = pathMapper.selectPageVisit(appid);
		List<LaPageCanvasVO> cvo = pathMapper.selectPageVisitCanvas(appid);
		Map<String,List<Object[]>> map = new HashMap<String,List<Object[]>>();
		/***
		 * 拼接访问轨迹成out形式，详细参加LaPageVisitVO实例中out格式
		 * key:对应的上一级页面的名称，value：key进入的下级页面
		 */
		for(int i=0;i<cvo.size();i++){
            if(map.containsKey(cvo.get(i).getPre_name())){
            List<Object> list = new LinkedList<Object>();
            	list.add(cvo.get(i).getNext_name());
            	list.add(cvo.get(i).getVisits());
            	list.add(cvo.get(i).getRatio());
            	list.add(cvo.get(i).getLeaf());
                map.get(cvo.get(i).getPre_name()).add(list.toArray());
            }else{
            	List<Object> list = new LinkedList<Object>();
            	list.add(cvo.get(i).getNext_name());
            	list.add(cvo.get(i).getVisits());
            	list.add(cvo.get(i).getRatio());
            	list.add(cvo.get(i).getLeaf());
            	List<Object[]> lo = new ArrayList<Object[]>();
                lo.add(list.toArray());
                map.put(cvo.get(i).getPre_name(),lo );
            }
		}
		/***
		 * 负值
		 */
	    for(int k=0;k<pvo.size();k++){
	    	pvo.get(k).setOut(map.get(pvo.get(k).getName()));
	    	pvo.get(k).setOut_chart(getfive(map).get(pvo.get(k).getName()));
	    }	
		return pvo.toString();
	}
	
	/****
	 * 将map的数据拆成5个，多了就全都用：其他,用于显示图表最多有5列
	 * @param map   
	 * @return
	 *
	 */
	private Map<String,List<Object[]>> getfive(Map<String,List<Object[]>> map){
		Map<String,List<Object[]>> fiveMap = new HashMap<String,List<Object[]>>();
		for(Entry<String, List<Object[]>> s :map.entrySet()){
			if(s.getValue().size()>5){
				float ratio=0f;
				float visits=0f;
				List<Object[]> list = new LinkedList<Object[]>();
				for(int i=0;i<4;i++){
					list.add(s.getValue().get(i));
				}
				for(int i=4;i<s.getValue().size();i++){
					Object[] ob = s.getValue().get(i);
				    ratio+=Float.valueOf(ob[2].toString());
				    visits+=Integer.parseInt(ob[1].toString());
				}
				List<Object> fiveList= new LinkedList<Object>();
				fiveList.add("其他");
				fiveList.add(visits);
				fiveList.add(ratio);
				fiveList.add(false);
				list.add(fiveList.toArray());
				fiveMap.put(s.getKey(), list);
			}else{
				fiveMap.put(s.getKey(), s.getValue());
			}
		}
		return fiveMap;
	}
}
