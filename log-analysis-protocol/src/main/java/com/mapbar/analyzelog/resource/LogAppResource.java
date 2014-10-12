package com.mapbar.analyzelog.resource;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

public class LogAppResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	public LogAppResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@Path("/user/{user_id}")
	public LogUserResource getAppTodo(
			@PathParam("user_id") String user_id) {
		return new LogUserResource(uriInfo, request, user_id);
	}
}
