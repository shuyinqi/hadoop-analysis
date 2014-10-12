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
 * 趋势分析值对象
 * </p>
 * 
 * @author <a href="mailto:liurun_225@sina.com">liurun</a>
 *         {mobile:15010123578,qq:305760407}
 * @date 2012-02-16
 */
public class Trend implements java.io.Serializable {

	private static final long serialVersionUID = -4777471399701765331L;

	private StringBuffer date = new StringBuffer();
	private StringBuffer new_imei_size = new StringBuffer();
	private StringBuffer imei_size = new StringBuffer();
	private StringBuffer old_imei_size = new StringBuffer();
	private StringBuffer visit_imei_size = new StringBuffer();
	private StringBuffer visit_size = new StringBuffer();
	private StringBuffer average_duration = new StringBuffer();
	private Long all_imei_size = new Long(0);
	private Long all_visit_size = new Long(0);

	public Trend(List<LaBasicStat> stats) {
		if (stats != null)
			for (LaBasicStat stat : stats) {
				date.append("\"")
						.append(getValue(stat.getDate())==""?"":getValue(stat.getDate()).toString().substring(5))
						.append("\",");
				new_imei_size.append(stat.getNew_imei_size()).append(",");
				imei_size.append(stat.getImei_size()).append(",");
				old_imei_size.append(
						stat.getVisit_imei_size() - stat.getNew_imei_size())
						.append(",");
				visit_imei_size.append(stat.getVisit_imei_size()).append(",");
				visit_size.append(stat.getVisit_size()).append(",");
				average_duration.append(stat.getAverage_duration()).append(",");
				all_imei_size += stat.getNew_imei_size();
				all_visit_size += stat.getVisit_size();
			}
	}
	
	private Object getValue(Object val){
		return val==null?"":val;
	}

	public String getDate() {
		return date.length() < 1 ? "" : date.substring(0, date.length() - 1);
	}

	public String getNewImeiSize() {
		return new_imei_size.length() < 1 ? "" : new_imei_size.substring(0,
				new_imei_size.length() - 1);
	}

	public String getImeiSize() {
		return imei_size.length() < 1 ? "" : imei_size.substring(0,
				imei_size.length() - 1);
	}

	public String getOldImeiSize() {
		return old_imei_size.length() < 1 ? "" : old_imei_size.substring(0,
				old_imei_size.length() - 1);
	}

	public String getVisitImeiSize() {
		return visit_imei_size.length() < 1 ? "" : visit_imei_size.substring(0,
				visit_imei_size.length() - 1);
	}

	public String getVisitSize() {
		return visit_size.length() < 1 ? "" : visit_size.substring(0,
				visit_size.length() - 1);
	}

	public String getAverageDuration() {
		return average_duration.length() < 1 ? "" : average_duration.substring(
				0, average_duration.length() - 1);
	}

	public Long getAllImeiSize() {
		return all_imei_size;
	}

	public Long getAllVisitSize() {
		return all_visit_size;
	}
}
