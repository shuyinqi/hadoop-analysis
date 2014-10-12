package com.mapbar.analyzelog.report.entity;

public class VersionVVO {
	private String version;
	private String news;
	private String launchs;
	private String ups;
	private String date;
	private Long addCount2;
	
	
	public Long getAddCount2() {
		return addCount2;
	}
	public void setAddCount2(Long addCount2) {
		this.addCount2 = addCount2;
	}
	public final String getVersion() {
		return version;
	}
	public final void setVersion(String version) {
		this.version = version;
	}
	public final String getNews() {
		return news;
	}
	public final void setNews(String news) {
		this.news = news;
	}
	public final String getLaunchs() {
		return launchs;
	}
	public final void setLaunchs(String launchs) {
		this.launchs = launchs;
	}
	public final String getDate() {
		return date;
	}
	public final void setDate(String date) {
		this.date = date;
	}
	public final String getUps() {
		return ups;
	}
	public final void setUps(String ups) {
		this.ups = ups;
	}
	
}
