package com.mapbar.analyzelog.common;

import java.util.ResourceBundle;

import javax.ws.rs.Path;
/**
 * <p>
 * $Header: /server/analyzelog/protocol/com.mapbar.analyzelog.common.ResourcePath.java,lijie Exp $
 * $Version: 1.0 $
 * $Date: 2012/02/04 $
 * </p>
 * <p>
 * <ul>
 * <li>
 * ConfigResource: This {@link Path} is installed to the
 * {@link java.lang.annotation.Annotation} substance 
 * </li>
 * </ul>
 * </p>
 * 
 */
public enum ResourcePath {
	instance;
	
	private ResourceBundle bundle;

	private ResourcePath() {
		try {
			bundle = ResourceBundle.getBundle("resource");
		}catch(java.lang.NullPointerException e) {
			LogWriter.ResDebug("see:com.mapbar.analyzelog.common.ResourcePath#ResourcePath();error:NullPointerException;message:"+e.getMessage());
		}catch(java.util.MissingResourceException me){
			LogWriter.ResDebug("see:com.mapbar.analyzelog.common.ResourcePath#ResourcePath();error:MissingResourceException;message:"+me.getMessage());
		}
	}
	
	public String getPath(String name){
		String value=null;
		if(bundle.containsKey(name))
		  value=bundle.getString(name);
		else LogWriter.ResDebug("see:com.mapbar.analyzelog.common.ResourcePath#ResourcePath();info:name='"+name+"'.");
		return value;
	}
}
