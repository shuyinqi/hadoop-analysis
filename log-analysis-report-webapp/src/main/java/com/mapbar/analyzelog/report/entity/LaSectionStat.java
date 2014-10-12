/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * <p>
 * 分段统计实体，对应表名la_section_stat
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-15
 */
public class LaSectionStat implements java.io.Serializable {

	private static final long serialVersionUID = -891039937327099687L;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{id:").append(id).append(",app:").append(app)
				.append(",date:").append(date).append(",new_imei_size:")
				.append(new_imei_size).append(",visit_imei_size:")
				.append(visit_imei_size).append(",visit_size:")
				.append(visit_size).append(",section:").append(section)
				.append(",flag:").append(flag).append("}");
		return sb.toString();
	}

	private Long id;
	private LaApp app;
	private Date date;
	private Long new_imei_size;
	private Long visit_imei_size;
	private Long visit_size;
	private String section;
	private String flag;

	/** default constructor */
	public LaSectionStat() {
	}

	/** full constructor */
	public LaSectionStat(Long id, LaApp app, Date date, Long new_imei_size,
			Long visit_imei_size, Long visit_size, String section,
			Timestamp addtime, String flag) {
		this.id = id;
		this.app = app;
		this.date = date;
		this.new_imei_size = new_imei_size;
		this.visit_imei_size = visit_imei_size;
		this.visit_size = visit_size;
		this.section = section;
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
	 * 分段
	 * 
	 * @return String
	 */
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
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