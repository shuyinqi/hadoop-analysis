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
 * 活跃统计实体，对应表名la_active_stat
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-24
 */
public class LaActiveStat implements java.io.Serializable {

	private static final long serialVersionUID = -3832261713291030906L;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{id:").append(id).append(",app:").append(app)
				.append(",date:").append(date).append(",before:")
				.append(before).append(",active_imei_size:")
				.append(active_imei_size).append("}");
		return sb.toString();
	}

	private Long id;
	private LaApp app;
	private Date date;
	private Long before = 0L;
	private Long active_imei_size = 0L;

	/** default constructor */
	public LaActiveStat() {
	}

	/** full constructor */
	public LaActiveStat(Long id, LaApp app, Date date, Long before,
			Long active_imei_size) {
		this.id = id;
		this.app = app;
		this.date = date;
		this.before = before;
		this.active_imei_size = active_imei_size;
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
	 * 之前天数
	 * 
	 * @return Long
	 */
	public Long getBefore() {
		return before;
	}

	public void setBefore(Long before) {
		this.before = before;
	}

	/**
	 * 活跃用户量
	 * 
	 * @return Long
	 */
	public Long getActive_imei_size() {
		return active_imei_size;
	}

	public void setActive_imei_size(Long active_imei_size) {
		this.active_imei_size = active_imei_size;
	}
}