package com.mapbar.analyzelog.model;

public class Header {
	private String carrier;
	private String access;
	private String lat;
	private String lon;
	
	private String appv;
	private String osv;
	private String chn;
	private String cht;
	private String channel;
	private int version_code;
	private String city;
	private String country;
	public final String getCarrier() {
		return carrier;
	}
	public final void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public final String getAccess() {
		return access;
	}
	public final void setAccess(String access) {
		this.access = access;
	}
	public final String getLat() {
		return lat;
	}
	public final void setLat(String lat) {
		this.lat = lat;
	}
	public final String getLon() {
		return lon;
	}
	public final void setLon(String lon) {
		this.lon = lon;
	}
	public final String getAppv() {
		return appv;
	}
	public final void setAppv(String appv) {
		this.appv = appv;
	}
	public final String getOsv() {
		return osv;
	}
	public final void setOsv(String osv) {
		this.osv = osv;
	}
	public final String getChn() {
		return chn;
	}
	public final void setChn(String chn) {
		this.chn = chn;
	}
	public final String getCht() {
		return cht;
	}
	public final void setCht(String cht) {
		this.cht = cht;
	}
	public final String getChannel() {
		return channel;
	}
	public final void setChannel(String channel) {
		this.channel = channel;
	}
	
	public final int getVersion_code() {
		return version_code;
	}
	public final void setVersion_code(int version_code) {
		this.version_code = version_code;
	}
	public final String getCity() {
		return city;
	}
	public final void setCity(String city) {
		this.city = city;
	}
	public final String getCountry() {
		return country;
	}
	public final void setCountry(String country) {
		this.country = country;
	}
	
}
