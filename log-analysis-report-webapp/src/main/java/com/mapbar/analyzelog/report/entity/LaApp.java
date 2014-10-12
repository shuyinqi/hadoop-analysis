/**
 * <p>Project: log-analysis-report-webapp 日志分析系统-图表服务</p>
 * <p>Company: mapbar 图吧</p>
 ***************************************************
 * HISTORY:
 ***************************************************
 */
package com.mapbar.analyzelog.report.entity;

import java.sql.Timestamp;

/**
 * <p>
 * 应用信息实体，对应表名la_app
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-13
 */
public class LaApp implements java.io.Serializable {

	public String getMultipleLaunchCount() {
		return multipleLaunchCount;
	}

	public void setMultipleLaunchCount(String multipleLaunchCount) {
		this.multipleLaunchCount = multipleLaunchCount;
	}

	public String getMultipleLaunchUserCount() {
		return multipleLaunchUserCount;
	}

	public void setMultipleLaunchUserCount(String multipleLaunchUserCount) {
		this.multipleLaunchUserCount = multipleLaunchUserCount;
	}

	public String getMultipleNewUserCount() {
		return multipleNewUserCount;
	}

	public void setMultipleNewUserCount(String multipleNewUserCount) {
		this.multipleNewUserCount = multipleNewUserCount;
	}

	private static final Long serialVersionUID = 8419539800351102808L;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{id:").append(id).append(",name:").append(name)
				.append(",memo:").append(memo).append(",addtime:")
				.append(addtime).append(",flag:").append(flag).append("}");
		return sb.toString();
	}

	private Long id;
	private String name;
	private String memo;
	private Timestamp addtime;
	private String flag;
	private Integer totalUser = 0;
	private Integer launchCount = 0;
	private Integer launchUserCount = 0;
	private Integer newUserCount = 0;
	private String multipleLaunchCount;
	private String multipleLaunchUserCount;
	private String multipleNewUserCount;
	
	
	
	/** default constructor */
	public LaApp() {
	}

	/** full constructor */
	public LaApp(Long id, String name, String memo, Timestamp addtime,
			String flag) {
		this.id = id;
		this.name = name;
		this.memo = memo;
		this.addtime = addtime;
		this.flag = flag;
	}

	/**
	 * PK
	 * 
	 * @return Integer
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 名称
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
	 * 备注
	 * 
	 * @return String
	 */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 创建时间
	 * 
	 * @return java.sql.Timestamp
	 */
	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
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

	public Integer getLaunchCount() {
		return launchCount;
	}

	public void setLaunchCount(Integer launchCount) {
		this.launchCount = launchCount;
	}

	public Integer getLaunchUserCount() {
		return launchUserCount;
	}

	public Integer getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(Integer totalUser) {
		this.totalUser = totalUser;
	}

	public void setLaunchUserCount(Integer launchUserCount) {
		this.launchUserCount = launchUserCount;
	}

	public Integer getNewUserCount() {
		return newUserCount;
	}

	public void setNewUserCount(Integer newUserCount) {
		this.newUserCount = newUserCount;
	}
}