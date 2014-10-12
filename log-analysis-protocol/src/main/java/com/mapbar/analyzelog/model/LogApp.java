package com.mapbar.analyzelog.model;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class LogApp {
	private String rp;
	private String lct;
	private Map<String,String> ops;
	
	public LogApp(){
		
	}
	public LogApp (String lct, String rp){
		this.rp = rp;
		this.lct = lct;
	}
	public final String getLct() {
		return lct;
	}
	public final void setLct(String lct) {
		this.lct = lct;
	}
	public final String getRp() {
		return rp;
	}
	public final void setRp(String rp) {
		this.rp = rp;
	}
	public final Map<String, String> getOps() {
		return ops;
	}
	public final void setOps(Map<String, String> ops) {
		this.ops = ops;
	}
}
