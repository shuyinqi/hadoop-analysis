package com.mapbar.analyzelog.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 * <p>
 * $Header: /server/analyzelog/protocol/com.mapbar.analyzelog.resource.SayIndexResource.java,lijie Exp $
 * $Version: 1.0 $
 * $Date: 2012/02/04 $
 * </p>
 * <p>
 * <ul>
 * <li>
 * SayIndexResource: This {@link Path} is installed to the
 * {@link java.lang.annotation.Annotation} substance 
 * </li>
 * </ul>
 * </p>
 */
@Path("/")
public class SayIndexResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayIndex() {
		return "index server/analyzelog/protocol";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLIndex() {
		return "<?xml version=\"1.0\"?>" + "<hello>index server/analyzelog/protocol" + "</hello>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlIndex() {
		return "<html> " + "<title>" + "index server/analyzelog/protocol" + "</title>"
				+ "<body><h1>" + "index server/analyzelog/protocol" + "</body></h1>" + "</html> ";
	}
}
