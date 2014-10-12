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
 * 菜单权限管理
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.AppStatMapper
 */
@Controller
public class FunctionController extends MediaType{

	@Resource
	private AppStatMapper appStatMapper;

	@RequestMapping(value = "/apps/{appid}/function")
	public String device(@PathVariable String appid,
			@RequestParam(value="roleid",required=false) String roleid,
			@RequestParam(value="functionid",required=false) String functionid,Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String appid_long = String.valueOf(doApp("roles", appid, model));
		List<MenuVO> roles=appStatMapper.selectRoles();
		model.addAttribute("roles",roles);
		String role=null;
		if(roleid!=null)
		role=roleid.split(",")[0];
		model.addAttribute("roleid",role);
		
		try{
		if(role!=null&&functionid!=null){
			List<MenuVO> list=new ArrayList<MenuVO>();
			appStatMapper.deleteRF(role);
			String[] s=functionid.split(",");
			for(int i=0;i<s.length;i++){
				MenuVO vo =new MenuVO();
				vo.setId(Integer.parseInt(role));
				vo.setFatherid(Integer.parseInt(s[i]));
				list.add(vo);
			}
			appStatMapper.insertRF(list);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		List<MenuVO> menus=appStatMapper.selectMenuByUid(role);
		model.addAttribute("menus",menus);
		List<MenuVO> usermenus=appStatMapper.selectMenus(null);
		model.addAttribute("usermenus2",usermenus);
		return FUNCTOIN;
	}
}
