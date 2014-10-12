/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.entity;

import java.util.List;

/**
 * <p>
 * 渠道统计概要值对象
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-22
 */
public class SummaryChannelStat implements java.io.Serializable {

	private static final long serialVersionUID = -5193081600882172441L;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{type:").append(type).append(",name:").append(name)
				.append(",imei_size0:").append(imei_size0)
				.append(",imei_size1:").append(imei_size1)
				.append(",imei_size2:").append(imei_size2)
				.append(",imei_size7:").append(imei_size7)
				.append(",name_imei_size:").append(name_imei_size)
				.append(",type_imei_size:").append(type_imei_size)
				.append(",vlist:").append(vlist)
				.append("}");
		return sb.toString();
	}

	private String type;
	private String name;
	private Long imei_size0=(long) 0;
	private Long imei_size1=(long) 0;
	private Long imei_size2=(long) 0;
	private Long imei_size7=(long) 0;
	private Long name_imei_size;
	private Long type_imei_size;
	private Long addCount;
	private List<VersionVVO> vlist;
	private String note;

	/** default constructor */
	public SummaryChannelStat() {
	}

	/** full constructor */
	public SummaryChannelStat(String type, String name, Long imei_size0,
			Long imei_size1, Long imei_size2, Long imei_size7,
			Long name_imei_size,Long addCount, Long type_imei_size,List<VersionVVO> vlist) {
		this.type = type;
		this.name = name;
		this.imei_size0 = imei_size0;
		this.imei_size1 = imei_size1;
		this.imei_size2 = imei_size2;
		this.imei_size7 = imei_size7;
		this.name_imei_size = name_imei_size;
		this.type_imei_size = type_imei_size;
		this.vlist = vlist;
		this.addCount=addCount;
	}

	/**
	 * 渠道类型，目前为市场和预装
	 * 
	 * @return String
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 市场类型时为渠道名称，预装类型时为机型品牌
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/***
	 * 爬虫下载
	 * @return
	 *
	 */
	public Long getAddCount() {
		return addCount;
	}

	public void setAddCount(Long addCount) {
		this.addCount = addCount;
	}

	/**
	 * 当天用户量
	 * 
	 * @return Long
	 */
	public Long getImei_size0() {
		return imei_size0;
	}

	public void setImei_size0(Long imei_size0) {
		this.imei_size0 = imei_size0;
	}

	/**
	 * 前一天用户量
	 * 
	 * @return Long
	 */
	public Long getImei_size1() {
		return imei_size1;
	}

	public void setImei_size1(Long imei_size1) {
		this.imei_size1 = imei_size1;
	}

	/**
	 * 前两天用户量
	 * 
	 * @return Long
	 */
	public Long getImei_size2() {
		return imei_size2;
	}

	public void setImei_size2(Long imei_size2) {
		this.imei_size2 = imei_size2;
	}

	/**
	 * 前七天用户量
	 * 
	 * @return Long
	 */
	public Long getImei_size7() {
		return imei_size7;
	}

	public void setImei_size7(Long imei_size7) {
		this.imei_size7 = imei_size7;
	}

	/**
	 * 指定渠道内累计用户量
	 * 
	 * @return Long
	 */
	public Long getName_imei_size() {
		return name_imei_size;
	}

	public void setName_imei_size(Long name_imei_size) {
		this.name_imei_size = name_imei_size;
	}

	/**
	 * 指定渠道类型内累计用户量
	 * 
	 * @return Long
	 */
	public Long getType_imei_size() {
		return type_imei_size;
	}

	public void setType_imei_size(Long type_imei_size) {
		this.type_imei_size = type_imei_size;
	}

	public final List<VersionVVO> getVlist() {
		return vlist;
	}

	public final void setVlist(List<VersionVVO> vlist) {
		this.vlist = vlist;
	}

	public final String getNote() {
		return note;
	}

	public final void setNote(String note) {
		this.note = note;
	}
	
	
}