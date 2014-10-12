package com.mapbar.analyzelog.core.entities;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * App 结束日志信息实体。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class LogTerminate extends Log{
	
	public static final String SN = "te";
	private BasicLog basicLog;

	private String sid;
	private String duration;
	private List<String[]> activities;

	public LogTerminate(BasicLog basicLog) {
		this.basicLog = basicLog;
	}

	public BasicLog getBasicLog() {
		return basicLog;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String session_id) {
		this.sid = session_id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getActivities() {
		return convertToTextActivitis(activities);
	}

	//转将成 page1,time1|page2,time2
	protected String convertToTextActivitis(List<String[]> activities) {
		if (activities == null || activities.size() <= 0) {
			return "";
		}
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < activities.size(); i++) {
			String[] activity = activities.get(i);
			if (activity == null || activity.length <= 1) {
				continue;
			}
			String name = activity[0];
			if (StringUtils.isEmpty(name)) {
				continue;
			}
			int time = NumberUtils.toInt(activity[1], 0);
			text.append(activity[0]).append(",").append(time);
			if (i != activities.size() - 1) {
				text.append("|");
			}
		}
		return text.toString();
	}

	public void setActivities(List<String[]> activities) {
		this.activities = activities;
	}

	public void setBasicLog(BasicLog basicLog) {
		this.basicLog = basicLog;
	}
}
