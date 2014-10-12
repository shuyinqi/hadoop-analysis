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
import com.mapbar.analyzelog.report.entity.DeviceSettingVO;
import com.mapbar.analyzelog.report.entity.DeviceVO;
import com.mapbar.analyzelog.report.entity.NoticeVO;
import com.mapbar.analyzelog.report.mapper.DeviceMapper;
import com.mapbar.analyzelog.report.mapper.EventMapper;

/**
 * <p>
 * 用户管理
 * </p>
 * 
 * @see com.mapbar.analyzelog.report.mapper.AppStatMapper
 */
@Controller
public class DeviceSettingController extends MediaType{

	@Resource
	private DeviceMapper deviceMapper;

	@RequestMapping(value = "/apps/{appid}/devicesetting")
	public String device(@PathVariable String appid,
			@RequestParam(value="brand",required=false) String brand,
			@RequestParam(value="device",required=false) String device,
			@RequestParam(value="fn",required=false) String fn,
			@RequestParam(value="page",required=false) String page,
			@RequestParam(value="modifyid",required=false) String modifyid,
			@RequestParam(value="addname",required=false) String addname,
			@RequestParam(value="myapp",required=false) String myapp,
			@RequestParam(value="del",required=false) String del,
			Model model,HttpServletRequest request) {
		String url = checkSession(request);
		if (!url.equals("")) {
			return url;
		}
		String.valueOf(doApp("devicesetting", appid, model));
		try{
			if(addname!=null&&!addname.isEmpty()){
				deviceMapper.insertSetting(myapp,brand,device,fn);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(del!=null&&!del.isEmpty()){
			deviceMapper.deleteSetting(del);
		}
		
		if(modifyid!=null&&!modifyid.isEmpty()){
			deviceMapper.updateSetting(modifyid,brand,device,fn);
		}
		
		List<DeviceSettingVO> list7=deviceMapper.getSettingsAll();
		int pcount=list7.size();
		model.addAttribute("pagecount",pcount);
		if(page==null||pcount<=10)page="1";
		model.addAttribute("page",page);
		int pagenum=Integer.parseInt(page);
		boolean boend=pcount/10==(pagenum-1);
		List<DeviceSettingVO> noticesall=list7.subList((pagenum-1)*10,boend?pcount:pagenum*10);
		model.addAttribute("noticesall",noticesall);
		return DEVICES;
	}
}
