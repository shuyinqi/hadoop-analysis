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
import com.mapbar.analyzelog.report.entity.ChannelVO;
import com.mapbar.analyzelog.report.entity.NoticeVO;
import com.mapbar.analyzelog.report.mapper.ChannelMapper;
import com.mapbar.analyzelog.report.mapper.EventMapper;

/**
 * <p>
 * 用户管理
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.AppStatMapper
 */
@Controller
public class ChannelNotesController extends MediaType{

	@Resource
	private ChannelMapper channelMapper;

	@RequestMapping(value = "/apps/{appid}/channelnotes")
	public String device(@PathVariable String appid,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="modifyid",required=false) String modifyid,
			@RequestParam(value="addname",required=false) String addname,
			@RequestParam(value="myapp",required=false) String myapp,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="usernote",required=false) String usernote,
			@RequestParam(value="del",required=false) String del,
			Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String.valueOf(doApp("channelnotes", appid, model));
		try{
			if(addname!=null&&!addname.isEmpty()&&username!=null){
				channelMapper.insertNote(myapp,type,username,usernote);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(del!=null&&!del.isEmpty()){
			channelMapper.deleteNote(del);
		}
		
		if(modifyid!=null&&!modifyid.isEmpty()){
			channelMapper.updateNote(modifyid,username,usernote);
		}
		List<ChannelVO> names=channelMapper.queryNamesAll();
		
//		List<NoticeVO> noticesall=eventMapper.getNoticesAll();
		model.addAttribute("noticesall",names);
		return NOTES;
	}
}
