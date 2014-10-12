package com.mapbar.analyzelog.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.mapbar.analyzelog.report.entity.LaApp;
import com.mapbar.analyzelog.report.entity.MenuVO;

public interface AppStatMapper {

	public List<LaApp> selectAppsForStat(@Param("appid") String appid);
	
	public List<LaApp> selectAppsForStatYesterDay(@Param("appid") String appid);
	
	public void callSplit(@Param("appid") String appid);
	
	public LaApp selectUser(@Param("name") String name,@Param("pwd") String pwd);
	
	public List<MenuVO> selectMenus(@Param("name") String name);
	
	public List<MenuVO> selectMenusByname(@Param("name") String name);
	
	public List<MenuVO> selectMenuByUid(@Param("id") String id);
	
	public List<MenuVO> selectRoles();
	
	public void deleteRF(@Param("id") String id);
	
	public void insertRF(List list);
	
	public List<MenuVO> selectUserRS(@Param("name") String name);
	
	public void updateAuthor(MenuVO vo);
	
	public void updateRole(MenuVO vo);
	
	public void deleteRole(@Param("id") String id);
	
	public List<MenuVO> selectRS();
	
	public void insertRS(MenuVO vo);
	
	public void updateRS(MenuVO vo);
	
	public void deleteRS(@Param("id") String id);
	
	public void insertFS(List list);
	
	public void insertU(@Param("name") String name,@Param("pwd") String pwd);
	
	public void insertR(MenuVO vo);
	
	public MenuVO selectU(@Param("name") String name);
	
	public MenuVO selectRbyU(@Param("id") String id);
	
	public void insertApps(MenuVO vo);
	public void deleteApps(@Param("id") String id);
	
	public void changepwd(MenuVO vo);
	
	public MenuVO selectUPwd(@Param("id") String id);
	/***
	 * 获取重复数据，暂时只获取一条数据
	 * @return
	 *
	 */
	public LaApp selectRepeatForApp();
	
}
