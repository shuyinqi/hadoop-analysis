package com.mapbar.analyzelog.common;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

public abstract class AppPath extends Common{
	@Context
	UriInfo uriInfo3;
	@Context
	Request request;
	String[] param;
	public AppPath(UriInfo uriInfo,Request request,String... param){
		this.uriInfo3 = uriInfo;
		this.request = request;
		this.param=new String[param.length];
		for(int i=0;i<param.length;i++)
			this.param[i]=param[i];
	}
	public AppPath() {
	}
	
	protected final UriInfo getUri(){
		return this.uriInfo3;
	}
	
	protected final Request getRequest(){
		return this.request;
	}
	
	protected final String[] getParam(){
		return this.param;
	}
	
	/**
	 * This method is called if TEXT_PLAIN is request
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String AppPlainTextConfig() {
		return "see config/app";
	}

	/**
	 * This method is called if XML is request
	 * /logs
	 * @return 
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public String AppXMLConfig() {
		return "<?xml version=\"1.0\"?>" + "<hello>see config/app" + "</hello>";
	}
	
	/**
	 * This method is called if HTML is request
	 * /logs
	 * @return 
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String AppHtmlConfig() {
		return "<html> " + "<title>" + "see config/app" + "</title>"
				+ "<body><h1>" + "see config/app" + "</body></h1>" + "</html> ";
	}
}
