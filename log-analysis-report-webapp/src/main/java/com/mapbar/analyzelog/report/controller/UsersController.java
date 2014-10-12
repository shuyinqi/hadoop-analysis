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
public class UsersController extends MediaType{

	@Resource
	private AppStatMapper appStatMapper;

	@RequestMapping(value = "/apps/{appid}/users")
	public String device(@PathVariable String appid,
			@RequestParam(value="roleid",required=false) String roleid,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="userid",required=false) String userid,
			@RequestParam(value="myapp",required=false) String myapp,@RequestParam(value="addname",required=false) String addname,
			@RequestParam(value="flag",required=false) String flag,
			@RequestParam(value="pwd",required=false) String pwd,Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String appid_long = String.valueOf(doApp("users", appid, model));
		model.addAttribute("app.id", appid_long);
		
		String c="";
		try{
			if(addname!=null&&!addname.isEmpty()){
				MenuVO vo=appStatMapper.selectU(username);
				if(vo==null||vo.getName()==null||vo.getName().isEmpty()){
				appStatMapper.insertU(username, pwd);
				}
				else c="1"; 
			}
			
			if(roleid!=null&&userid!=null&&(addname==null||addname.isEmpty())){
				MenuVO vo=new MenuVO();
				vo.setId(Integer.parseInt(userid));
				vo.setFlevel(Integer.parseInt(roleid));
				vo.setName(username);
				vo.setFlag(flag==null?0:Integer.parseInt(flag));
				appStatMapper.updateAuthor(vo);
				
				MenuVO vo3=appStatMapper.selectRbyU(userid);
				MenuVO vo2=new MenuVO();
				vo2.setId(Integer.parseInt(userid));
				vo2.setFlevel(Integer.parseInt(roleid));
				vo2.setName(username);
				appStatMapper.deleteRole(userid);
				appStatMapper.insertR(vo2);
				/*if(vo3==null||vo3.getId()==0){
				appStatMapper.insertR(vo2);
				}else
				appStatMapper.updateRole(vo2);*/
				
				if(myapp!=null&&!myapp.isEmpty()){
					appStatMapper.deleteApps(userid);
					vo2.setAppid(myapp);
					appStatMapper.insertApps(vo2);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		List<MenuVO> userrs=appStatMapper.selectUserRS(null);
		model.addAttribute("userrs",userrs);
		List<MenuVO> roles=appStatMapper.selectRoles();
		model.addAttribute("roles",roles);
		model.addAttribute("addfull",c);
		return USER;
	}
}
