package com.mapbar.analyzelog.model;

import java.util.List;
/**
 * 发送用户操作日志API
 * 第三个接口，
 * @（#）:Body.java 
 * @description:  
 * @author:  sunjy  2012-4-1 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class Body {
	private List<Launch> launchs;
	private List<Terminate> terminates;
	private List<Event> events;
	private List<Prop> props;
	private List<Error> errors;
	public final List<Launch> getLaunch() {
		return launchs;
	}
	public final void setLaunch(List<Launch> launchs) {
		this.launchs = launchs;
	}
	
	public final List<Terminate> getTerminates() {
		return terminates;
	}
	public final void setTerminates(List<Terminate> terminates) {
		this.terminates = terminates;
	}
	public final List<Event> getEvent() {
		return events;
	}
	public final void setEvent(List<Event> events) {
		this.events = events;
	}
	public final List<Prop> getProp() {
		return props;
	}
	public final void setProp(List<Prop> props) {
		this.props = props;
	}
	public final List<Error> getErrors() {
		return errors;
	}
	public final void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
}
