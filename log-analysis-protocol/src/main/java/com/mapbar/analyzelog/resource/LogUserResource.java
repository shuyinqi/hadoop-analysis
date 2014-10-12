package com.mapbar.analyzelog.resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import com.mapbar.analyzelog.model.LogApp;

public class LogUserResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	public LogUserResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postTodo(JAXBElement<LogApp> todo) {
		LogApp c = todo.getValue();
		return putAndGetResponse(c);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(JAXBElement<LogApp> todo) {
		LogApp c = todo.getValue();
		return putAndGetResponse(c);
	}
	
	private Response putAndGetResponse(LogApp todo) {
		Response res=null;
		return res;
	}

}
