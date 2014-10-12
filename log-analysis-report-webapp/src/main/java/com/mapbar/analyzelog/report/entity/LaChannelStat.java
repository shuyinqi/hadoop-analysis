/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.entity;

import java.sql.Date;

/**
 * <p>
 * 渠道统计实体，对应表名la_basic_stat
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-22
 */
public class LaChannelStat implements java.io.Serializable {

	private static final long serialVersionUID = -672863182262622444L;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{id:").append(id).append(",app:").append(app)
				.append(",date:").append(date).append(",type:").append(type)
				.append(",name:").append(name).append(",new_imei_size:")
				.append(new_imei_size).append(",visit_imei_size:")
				.append(visit_imei_size).append(",visit_size:")
				.append(visit_size).append(",flag:").append(flag).append("}");
		return sb.toString();
	}

	private Long id;
	private LaApp app;
	private Date date;
	private String type;
	private String name;
	private Long new_imei_size;
	private Long visit_imei_size;
	private Long visit_size;
	private String flag;

	/** default constructor */
	public LaChannelStat() {
	}

	/** full constructor */
	public LaChannelStat(Long id, LaApp app, Date date, String type,
			String name, Long new_imei_size, Long visit_imei_size,
			Long visit_size, String flag) {
		this.id = id;
		this.app = app;
		this.date = date;
		this.type = type;
		this.name = name;
		this.new_imei_size = new_imei_size;
		this.visit_imei_size = visit_imei_size;
		this.visit_size = visit_size;
		this.flag = flag;
	}

	/**
	 * PK
	 * 
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 应用信息实体
	 * 
	 * @return LaApp
	 */
	public LaApp getApp() {
		return app;
	}

	public void setApp(LaApp app) {
		this.app = app;
	}

	/**
	 * 日志日期
	 * 
	 * @return Date
	 */
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	/**
	 * 新用户量
	 * 
	 * @return Long
	 */
	public Long getNew_imei_size() {
		return new_imei_size;
	}

	public void setNew_imei_size(Long new_imei_size) {
		this.new_imei_size = new_imei_size;
	}

	/**
	 * 访问用户量
	 * 
	 * @return Long
	 */
	public Long getVisit_imei_size() {
		return visit_imei_size;
	}

	public void setVisit_imei_size(Long visit_imei_size) {
		this.visit_imei_size = visit_imei_size;
	}

	/**
	 * 访问量
	 * 
	 * @return Long
	 */
	public Long getVisit_size() {
		return visit_size;
	}

	public void setVisit_size(Long visit_size) {
		this.visit_size = visit_size;
	}

	/**
	 * 有效标示
	 * 
	 * @return Integer
	 */
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}