package com.mapbar.analyzelog.report.entity;

import java.util.ArrayList;
import java.util.List;

/***
 * 用户行为轨迹跟踪封装的vo
 * @（#）:LaPageVisitVO.java 
 * @description:  
 * @author:  Administrator  2012-5-3 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 * 格式如下：
 * [{"exit_ratio":71.0,"visits":683506,"visits_ratio":55.7,"stay":446.3,"stay_ratio":98.3,"name":"FBReader","out_chart":[["离开应用",352673,71.0,false],["DaoCaoXieReader",136103,27.4,true],["FileManager",5580,1.1,true],["ReaderLaunch",1073,0.2,true],["其他",1061,0.2,false]],"out":[["离开应用",352673,71.0,false],["DaoCaoXieReader",136103,27.4,true],["FileManager",5580,1.1,true],["ReaderLaunch",1073,0.2,true],["DownloadManager",924,0.2,true],["book",123,0.0,true],["AppIntroduce",12,0.0,true],["geBReader",1,0.0,true],["SearchResult",1,0.0,true]]}
 */
public class LaPageVisitVO {
  /***
   * 跳出率
   */
  public float exit_ratio;
  /**
   * 访问次数
   */
  public int visits;
  /**
   * 访问占比
   */
  public float visits_ratio;
  @Override
public String toString() {
	  String st = "{\"exit_ratio\":" + exit_ratio + ", \"visits\":" + visits
				+ ", \"visits_ratio\":" + visits_ratio + ", \"stay\":" + stay
				+ ", \"stay_ratio\":" + stay_ratio + ",\"name\":\"" + name + "\", \"out_chart\":[";
	for(int i=0;i<out_chart.size();i++){
		st+="[";
		for(int s=0;s<out.get(i).length-1;s++){
			if(s==0){
				st+="\""+out_chart.get(i)[s]+"\",";
			}else{
				st+=out_chart.get(i)[s]+",";
			}
		}
		st+=out_chart.get(i)[out.get(i).length-1];
		st+="],";
	};
	st=st.substring(0,st.length()-1);
	st+="],\"out\":[";
	for(int j=0;j<out.size();j++){
		st+="[";
		for(int l=0;l<out.get(j).length-1;l++){
			if(l==0){
				st+="\""+out.get(j)[l]+"\",";
			}else{
				st+=out.get(j)[l]+",";
			}
		}
		st+=out.get(j)[out.get(j).length-1];
		st+="],";
	}
	st=st.substring(0,st.length()-1);
	st+="]}";
	return st;
}
/**
   * 平均停留时间
   */
  public float stay;
  /**
   * 停留时间占比
   */
  public float stay_ratio;
  /**
   * 页面名称
   */
  public String name;
  /***
   * 显示图表的数据源
   */
  public List<Object[]> out_chart = new ArrayList<Object[]>();
  /***
   * 显示grid的数据源
   */
  public List<Object[]> out=new ArrayList<Object[]>();
public float getExit_ratio() {
	return exit_ratio;
}
public void setExit_ratio(float exit_ratio) {
	this.exit_ratio = exit_ratio;
}
public int getVisits() {
	return visits;
}
public void setVisits(int visits) {
	this.visits = visits;
}
public float getVisits_ratio() {
	return visits_ratio;
}
public void setVisits_ratio(float visits_ratio) {
	this.visits_ratio = visits_ratio;
}
public float getStay() {
	return stay;
}
public void setStay(float stay) {
	this.stay = stay;
}
public float getStay_ratio() {
	return stay_ratio;
}
public void setStay_ratio(float stay_ratio) {
	this.stay_ratio = stay_ratio;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<Object[]> getOut_chart() {
	return out_chart;
}
public void setOut_chart(List<Object[]> out_chart) {
	this.out_chart = out_chart;
}
public List<Object[]> getOut() {
	return out;
}
public void setOut(List<Object[]> out) {
	this.out = out;
}
}
