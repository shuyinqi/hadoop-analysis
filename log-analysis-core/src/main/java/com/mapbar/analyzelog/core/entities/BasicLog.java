package com.mapbar.analyzelog.core.entities;

/**
 * 基础日志信息。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class BasicLog {

	private String carrier;  //通讯运营商
	private String access;  //联网方式
	private String country;  //国家
	private String city;    //城市
	private String lat;     //维度
	private String lon;     //经度
	private String appVersion;  //应用版本号
	private String OSVersion;   //操作系统版本号
	private ChannelType channelType;  //渠道类型
	private String channelName;   //渠道名称

	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getOSVersion() {
		return OSVersion;
	}
	public void setOSVersion(String oSVersion) {
		OSVersion = oSVersion;
	}
	public ChannelType getChannelType() {
		return channelType;
	}
	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public enum ChannelType {
		MARKET, PRE_INSTALLED, UNKNOW;

		/**
		 * 根据 int 值获取对应的 ChannelType 类型。
		 * @param type 1: MARKET, 2: PRE_INSTALLED or UNKNOW
		 */
		public static ChannelType get(int type) {
			switch (type) {
			case 1:
				return MARKET;
			case 2:
				return PRE_INSTALLED;
			default:
				return UNKNOW;
			}
		};

		public String toString(){
			return super.toString().toLowerCase();
		}
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
