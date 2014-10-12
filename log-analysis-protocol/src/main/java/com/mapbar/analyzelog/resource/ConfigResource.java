package com.mapbar.analyzelog.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * <p>
 * $Header:
 * /server/analyzelog/protocol/com.mapbar.analyzelog.resource.configResource
 * .java,lijie Exp $ $Version: 1.0 $ $Date: 2012/02/04 $
 * </p>
 * <p>
 * <ul>
 * <li>
 * ConfigResource: This {@link Path} is installed to the
 * {@link java.lang.annotation.Annotation} substance</li>
 * </ul>
 * </p>
 */
@Path("/config")
public class ConfigResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/**
	 * This method is called if TEXT_PLAIN is request <h4>uri</h4>
	 * /config
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextConfig() {
		return "see config";
	}

	/**
	 * This method is called if XML is request
	 * <h4>uri</h4>
	 * /config
	 * @return 
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLConfig() {
		return "<?xml version=\"1.0\"?>" + "<hello>see config" + "</hello>";
	}

	/**
	 * This method is called if HTML is request
	 * <h4>uri</h4>
	 * /config
	 * @return 
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlConfig() {
		return "<html> " + "<title>" + "see config" + "</title>"
				+ "<body><h1>" + "see config" + "</body></h1>" + "</html> ";
	}

	/**
	 * This method is called if HTML is request
	 * <h4>uri</h4>
	 * /config/app/{param}
	 * @return 
	 */
	@Path("/app")
	public AppTodoResource getTodo() {
		return new AppTodoResource(uriInfo, request);
	}

}
