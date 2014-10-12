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
import com.mapbar.analyzelog.report.entity.MenuVO;
import com.mapbar.analyzelog.report.mapper.AppStatMapper;
import com.mapbar.analyzelog.report.mapper.DeviceMapper;

/**
 * <p>
 * 用户管理
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.AppStatMapper
 */
@Controller
public class RolesController extends MediaType{

	@Resource
	private AppStatMapper appStatMapper;

	@RequestMapping(value = "/apps/{appid}/roles")
	public String device(@PathVariable String appid,
			@RequestParam(value="roleid",required=false) String roleid,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="userid",required=false) String userid,
			@RequestParam(value="del",required=false) String del,
			@RequestParam(value="modifyid",required=false) String modifyid,
			@RequestParam(value="addname",required=false) String addname,
			Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String appid_long = String.valueOf(doApp("roles", appid, model));
		try{
			if(addname!=null&&!addname.isEmpty()&&username!=null){
				MenuVO vo=new MenuVO();
				vo.setName(username);
				appStatMapper.insertRS(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(del!=null&&!del.isEmpty()){
			appStatMapper.deleteRS(del);
		}
		
		if(modifyid!=null&&!modifyid.isEmpty()){
			MenuVO vo=new MenuVO();
			vo.setName(username);
			vo.setId(Integer.parseInt(modifyid));
			appStatMapper.updateRS(vo);
			/*appStatMapper.deleteRS(modifyid);
			MenuVO vo=new MenuVO();
			vo.setName(username);
			appStatMapper.insertRS(vo);*/
		}
		
		
		List<MenuVO> roles=appStatMapper.selectRoles();
		model.addAttribute("roles",roles);
		return ROLE;
	}
}
