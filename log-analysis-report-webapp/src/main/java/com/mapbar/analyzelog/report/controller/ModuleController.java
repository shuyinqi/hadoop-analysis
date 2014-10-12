package com.mapbar.analyzelog.report.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mapbar.analyzelog.report.entity.LaFeedAnswer;
import com.mapbar.analyzelog.report.entity.LaFeedBack;
import com.mapbar.analyzelog.report.mapper.ModuleMapper;
import com.mapbar.analyzelog.report.utils.DateUtil;
/**
 * 处理组件的控制层
 * @（#）:ModuleController.java 
 * @description:  
 * @author:  Administrator  2012-7-10 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
@Controller
public class ModuleController extends AppController{
	
	@Resource
	private ModuleMapper moduleMapper;
    /**
     * 显示全部
     * @param appid
     * @param request
     * @param model
     * @return
     *
     */
	@RequestMapping(value = "/apps/{appid}/module")
	public String module(@PathVariable String appid, HttpServletRequest request,
			@RequestParam(value = "page", required = false) String page,
			Model model) {
		encapsulationFeedBack(appid,request,model,"0",page);
		return "module";
	}
	/**
	 * 显示收藏
	 * @param appid
	 * @param request
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/apps/{appid}/feedBackCollect")
	public String feedBackCollect(@PathVariable String appid, HttpServletRequest request,
			@RequestParam(value = "page", required = false) String page,
			Model model) {
		
		encapsulationFeedBack(appid,request,model,"1",page);
		return "module";
	}
	/**
	 * 删除问题
	 * @param appid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/apps/{appid}/feedBackDelete")
	public String feedBackDelete(@PathVariable String appid, HttpServletRequest request,HttpServletResponse response,
			Model model) {
		String id = request.getParameter("fid");
		moduleMapper.stopFeedBack(id);
		encapsulationFeedBack(appid,request,model,"0",null);
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		try {
			response.sendRedirect("/reports/apps/"+appid+"/module?fromdate="+fromdate+"&todate="+todate);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    return null;
	}
	/**
	 * 更改收藏状态
	 * @param appid
	 * @param request
	 * @param model
	 *
	 */
	@RequestMapping(value = "/apps/{appid}/fbChangeCollect", method = RequestMethod.POST)
	public @ResponseBody void fbChangeCollect(@PathVariable String appid,HttpServletRequest request, Model model){
		String id = request.getParameter("fid");
		String isCollect = request.getParameter("isc");
		moduleMapper.changeCollectStat(id, isCollect);
	}
	/***
	 * 提交回复
	 * @param appid
	 * @param request
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/apps/{appid}/feedBackReply")
	public String feedBackReply(@PathVariable String appid, HttpServletRequest request,HttpServletResponse response,
			Model model) {
		
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		
		String fid = request.getParameter("fid");
		String userid = request.getParameter("userid");
		String qid = request.getParameter("qid");
		String wid = request.getParameter("wid");
		String answer = request.getParameter("answer");
		try {
			answer = URLDecoder.decode(answer, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		String answerusername = request.getParameter("answerusername");
		LaFeedAnswer lfa = new LaFeedAnswer();
		lfa.setFid(Integer.valueOf(fid));
		lfa.setUserid(userid);
		lfa.setQid(qid);
		lfa.setWid(wid);
		lfa.setAnswer(answer);
		lfa.setAnswerusername(answerusername);
		lfa.setAppid(Integer.valueOf(appid));
		lfa.setIsDel(0);
		lfa.setLasttime(DateUtil.getTimestamp());
		moduleMapper.anwserFeedBack(lfa);
		moduleMapper.changeFlagStat(fid, "1");
		try {
			response.sendRedirect("/reports/apps/"+appid+"/module?fromdate="+fromdate+"&todate="+todate);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return "module";
	}
	/****
	 * 封装传递用户反馈功能
	 * @param appid
	 * @param request
	 * @param model
	 * @param isCollect  收藏1，全部传0
	 *
	 */
	private void encapsulationFeedBack(String appid,HttpServletRequest request,Model model,String isCollect,String page){
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		doApp("module", appid, model);
		fromdate=fromdate.trim()+" 00:00:00";
		todate=todate.trim()+" 23:59:59";
		List<LaFeedBack> lfb = moduleMapper.getLaFeedBack(appid, fromdate, todate, isCollect=="0"?null:isCollect);
		List<LaFeedAnswer> lfa = moduleMapper.getLaFeedAnswer(appid);
		for(LaFeedBack laFeedBack:lfb){
			List<LaFeedAnswer> answer = new LinkedList<LaFeedAnswer>();
			for(LaFeedAnswer laFeedAnswer:lfa){
				if(laFeedBack.getId().equals(laFeedAnswer.getFid())){
                      answer.add(laFeedAnswer);
				}
				laFeedBack.setAnswer(answer);
			}
		}
		/**
		 * 分页功能
		 */
		int pcount = lfb.size();
		model.addAttribute("pagecount", pcount);
		if (page == null || pcount <= 10)
			page = "1";
		model.addAttribute("page", page);
		int pagenum = Integer.parseInt(page);
		boolean boend = pcount / 10 == (pagenum - 1);
		List<LaFeedBack> lfb2 = lfb.subList((pagenum - 1) * 10,
				boend ? pcount : pagenum * 10);
		
		model.addAttribute("isCollect",isCollect);
		model.addAttribute("feedBack", lfb2);
	}
}
