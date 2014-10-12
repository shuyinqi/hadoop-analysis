package com.mapbar.analyzelog.model;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 没有用到
 * @（#）:AppTodo.java 
 * @description:  
 * @author:  sunjy  2012-4-1 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
@XmlRootElement
public class AppTodo {
	private String rp;
	private String lct;
	private String ops;
	
	public AppTodo(){
		
	}
	public AppTodo (String lct, String rp){
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
	public final String getOps() {
		return ops;
	}
	public final void setOps(String ops) {
		this.ops = ops;
	}
	
}
