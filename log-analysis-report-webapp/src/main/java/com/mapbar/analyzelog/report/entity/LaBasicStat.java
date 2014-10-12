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
 * 基本统计实体，对应表名la_basic_stat
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-13
 */
public class LaBasicStat implements java.io.Serializable {

	private static final long serialVersionUID = 4032652454448495668L;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{id:").append(id).append(",app:").append(app)
				.append(",date:").append(date).append(",imei_size:")
				.append(imei_size).append(",new_imei_size:")
				.append(new_imei_size).append(",visit_imei_size:")
				.append(visit_imei_size).append(",visit_size:")
				.append(visit_size).append(",upgrade_imei_size:")
				.append(upgrade_imei_size).append(",average_duration:")
				.append(average_duration).append(",flag:").append(flag)
				.append("}");
		return sb.toString();
	}

	private Long id;
	private LaApp app;
	private Date date;
	private Long imei_size = 0L;
	private Long new_imei_size = 0L;
	private Long visit_imei_size = 0L;
	private Long visit_size = 0L;
	private Long upgrade_imei_size = 0L;
	private Long average_duration = 0L;
	private String flag;

	/** default constructor */
	public LaBasicStat() {
	}

	/** full constructor */
	public LaBasicStat(Long id, LaApp app, Date date, Long imei_size,
			Long new_imei_size, Long visit_imei_size, Long visit_size,
			Long upgrade_imei_size, Long average_duration, String flag) {
		this.id = id;
		this.app = app;
		this.date = date;
		this.imei_size = imei_size;
		this.new_imei_size = new_imei_size;
		this.visit_imei_size = visit_imei_size;
		this.visit_size = visit_size;
		this.upgrade_imei_size = upgrade_imei_size;
		this.average_duration = average_duration;
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
	 * 累计用户量
	 * 
	 * @return Long
	 */
	public Long getImei_size() {
		return imei_size;
	}

	public void setImei_size(Long imei_size) {
		this.imei_size = imei_size;
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
	 * 升级用户量
	 * 
	 * @return Long
	 */
	public Long getUpgrade_imei_size() {
		return upgrade_imei_size;
	}

	public void setUpgrade_imei_size(Long upgrade_imei_size) {
		this.upgrade_imei_size = upgrade_imei_size;
	}

	/**
	 * 平均使用时长
	 * 
	 * @return Long
	 */
	public Long getAverage_duration() {
		return average_duration;
	}

	public void setAverage_duration(Long average_duration) {
		this.average_duration = average_duration;
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