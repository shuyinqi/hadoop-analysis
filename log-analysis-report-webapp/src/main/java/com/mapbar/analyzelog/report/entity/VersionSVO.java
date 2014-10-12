package com.mapbar.analyzelog.report.entity;

import java.util.List;

public class VersionSVO implements Comparable{
	private String version;
	private int tds;
	private int news;
	private int ups;
	private int yds;
	private int ysups;
	private String counts;
	private List<VersionVVO> vdes;
	public final String getVersion() {
		return version;
	}
	public final void setVersion(String version) {
		this.version = version;
	}
	public final int getTds() {
		return tds;
	}
	public final void setTds(int tds) {
		this.tds = tds;
	}
	public final int getNews() {
		return news;
	}
	public final void setNews(int news) {
		this.news = news;
	}
	public final int getUps() {
		return ups;
	}
	public final void setUps(int ups) {
		this.ups = ups;
	}
	public final int getYds() {
		return yds;
	}
	public final void setYds(int yds) {
		this.yds = yds;
	}
	public final String getCounts() {
		return counts;
	}
	public final void setCounts(String counts) {
		this.counts = counts;
	}
	
	
	public final List<VersionVVO> getVdes() {
		return vdes;
	}
	public final void setVdes(List<VersionVVO> vdes) {
		this.vdes = vdes;
	}
	@Override  
    public int compareTo(Object o) {  
		VersionSVO p = (VersionSVO)o;  
        if(Integer.parseInt(this.counts) < Integer.parseInt(p.counts)){      
            return 1;
        }  
        else if(Integer.parseInt(this.counts) > Integer.parseInt(p.counts)){     
            return -1;        
        }     
        return 0;  
    }
	public int getYsups() {
		return ysups;
	}
	public void setYsups(int ysups) {
		this.ysups = ysups;
	} 
	
}
