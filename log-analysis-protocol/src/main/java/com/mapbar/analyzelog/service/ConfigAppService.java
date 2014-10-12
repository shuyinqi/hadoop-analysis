package com.mapbar.analyzelog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.mapbar.analyzelog.db.DButil;
import com.mapbar.analyzelog.model.ConfigApp;
import com.mapbar.analyzelog.model.FeedBack;
import com.mapbar.analyzelog.util.DateUtil;
import com.mapbar.analyzelog.util.StrUtil;


public class ConfigAppService {

	public ConfigApp getConfigAppModel(int appid){
		ConfigApp model=null;
		String strCountSql="select * from configApps where app_id="+appid;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DButil.getConnection();
		ps=DButil.getPreparedStatement(conn,strCountSql);
		rs=DButil.getResultSet(ps);
			while(rs.next()){
				model=new ConfigApp();
				model.setApp_id(rs.getInt("app_id"));
				model.setReport_policy(rs.getInt("report_policy"));
				model.setLast_config_time(rs.getDate("last_config_time").toString());
				model.setOnline_params(rs.getString("online_params"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DButil.closeAll(conn,ps,rs);
		}
		return model;
	}
	
	
	public String setFeedBacks(FeedBack model){
		String uuid=null;	
			String strSql="insert into la_app_feedbacks(appid,userid,addtime,lasttime,os,phone,lat,lon,appv,ct,qid,wid,note,collect,device) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Connection conn=null;
			PreparedStatement ps=null;//DButil.getPreparedStatement(strSql);
			
			try {
				conn=DButil.getConnection();
				ps=conn.prepareStatement(strSql);
				uuid=UUID.randomUUID().toString().replace("-","");
				ps.setInt(1,Integer.parseInt(StrUtil.getValue(model.getAppid()).toString()));
				ps.setString(2,model.getUserid());
				ps.setTimestamp(3,DateUtil.getTimestamp());
				ps.setTimestamp(4,DateUtil.getTimestamp());
				ps.setString(5,model.getSdkVersion());
				ps.setString(6,model.getPhone());
				ps.setString(7,model.getLat());
				ps.setString(8,model.getLon());
				ps.setString(9,model.getAppVersion());
				ps.setString(10,model.getCt());
				ps.setString(11,uuid);
				ps.setString(12,model.getWid());
				ps.setString(13,model.getNote());
				ps.setInt(14,model.getCollect());
				ps.setString(15,model.getOs());
				ps.execute();
			} catch (SQLException e) {uuid=null;
				e.printStackTrace();
			}finally{
				DButil.closeAll(conn, ps,null);
			}
			return uuid;
	}
	
	
	public FeedBack getFeedBacks(String appid,String userid){
		FeedBack model=null;
		String strCountSql="select * from la_app_feedanswer where userid='"+userid+"' and appid="+appid+" and qid is not null order by lasttime desc limit 1";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DButil.getConnection();
		ps=DButil.getPreparedStatement(conn,strCountSql);
		rs=DButil.getResultSet(ps);
			while(rs.next()){
				model=new FeedBack();
				model.setAppid(String.valueOf(rs.getInt("appid")));
				model.setUserid(rs.getString("userid"));
				model.setLasttime(StrUtil.getValue(rs.getDate("lasttime")).toString());
				model.setCt(rs.getString("answer"));
				model.setQid(rs.getString("qid"));
				model.setWid(rs.getString("wid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DButil.closeAll(conn,ps,rs);
		}
		return model;
	}
	
	public List<FeedBack> getFeedBackByQid(String appid,String userid,String qid){
		List<FeedBack> list=new ArrayList<FeedBack>();
		String strCountSql="select b.qid,b.answer as ct,b.lasttime as htime from la_app_feedanswer b " +
		" where userid='"+userid+"' and appid="+appid+" and isDel!=1 and qid='"+qid+"' order by lasttime";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DButil.getConnection();
		ps=DButil.getPreparedStatement(conn,strCountSql);
		rs=DButil.getResultSet(ps);
			while(rs.next()){
				FeedBack model=new FeedBack();
				model.setAddtime(StrUtil.getValue(rs.getDate("htime")).toString());
				model.setCt(rs.getString("ct"));
				model.setQid(rs.getString("qid"));
				list.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DButil.closeAll(conn,ps,rs);
		}
		return list;
	}
	
	public List<FeedBack> getFeedBacksAll(String appid,String userid){
		List<FeedBack> list=new ArrayList<FeedBack>();
		String strCountSql="select a.qid,a.ct,a.lasttime  from la_app_feedbacks a " +
				" where a.userid='"+userid+"' and a.appid="+appid+" and a.isDel!=1 order by a.lasttime desc limit 50";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DButil.getConnection();
		ps=DButil.getPreparedStatement(conn,strCountSql);
		rs=DButil.getResultSet(ps);
			while(rs.next()){
				FeedBack model=new FeedBack();
				model.setLasttime(StrUtil.getValue(rs.getDate("lasttime")).toString());
				model.setCt(rs.getString("ct"));
				model.setQid(rs.getString("qid"));
				list.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DButil.closeAll(conn,ps,rs);
		}
		return list;
	}
	
}
