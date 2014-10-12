package com.mapbar.analyzelog.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.mapbar.analyzelog.model.AppTodo;
import com.mapbar.analyzelog.service.AppTodoDao;

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
@Path("/logs")
public class LogsResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextConfig() {
		return "see config";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLConfig() {
		return "<?xml version=\"1.0\"?>" + "<hello>see config" + "</hello>";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlConfig() {
		return "<html> " + "<title>" + "see config" + "</title>"
				+ "<body><h1>" + "see config" + "</body></h1>" + "</html> ";
	}
	
	/*@GET
	@Path("/app")
	public LogAppTodoResource getTodo() {
		return new LogAppTodoResource(uriInfo, request);
	}*/
	
	/**
	 * This method is called if TEXT_PLAIN is request <h4>uri</h4>
	 * /config
	 * @return
	 */
	@GET
	@Path("/app")
	@Produces(MediaType.TEXT_PLAIN)
	public String AppPlainTextConfig() {
		return "see config/app";
	}

	/**
	 * This method is called if XML is request
	 * <h4>uri</h4>
	 * /config
	 * @return 
	 */
	@GET
	@Path("/app")
	@Produces(MediaType.TEXT_XML)
	public String AppXMLConfig() {
		return "<?xml version=\"1.0\"?>" + "<hello>see config/app" + "</hello>";
	}
	
	/**
	 * This method is called if HTML is request
	 * <h4>uri</h4>
	 * /config
	 * @return 
	 */
	@GET
	@Path("/app")
	@Produces(MediaType.TEXT_HTML)
	public String AppHtmlConfig() {
		return "<html> " + "<title>" + "see config/app" + "</title>"
				+ "<body><h1>" + "see config/app" + "</body></h1>" + "</html> ";
	}
	

	/**
	 * AppTodoDaolication integration
	 * This method is called if APPLICATION_XML,APPLICATION_JSON are request <h4>uri</h4>
	 * /config/app/{param}
	 * @return
	 */ 		
	@GET
	@Path("/app/{param}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public AppTodo getAppTodo(@PathParam("param") String param) {
		AppTodo AppTodo = AppTodoDao.instance.getModel().get(param);
		if(AppTodo==null)
			throw new RuntimeException("Get: AppTodo with " + param +  " not found");
		return AppTodo;
	}
	
	/**
	 * For the browser
	 * This method is called if TEXT_XML is request <h4>uri</h4>
	 * /config/app/{param}
	 * @return
	 */
	@GET
	@Path("/app/{param}")
	@Produces(MediaType.TEXT_XML)
	public List<AppTodo> getAppTodoXML(@PathParam("param") String param) {
		List<AppTodo> todos2 = new ArrayList<AppTodo>();
		todos2.addAll( AppTodoDao.instance.getModel().values() );
		return todos2;
	}
	
	/**
	 * For the browser
	 * This method is called if TEXT_PLAIN is request <h4>uri</h4>
	 * /config/app/{param}
	 * @return
	 */
	@GET
	@Path("/app/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public String PlainTextConfig(@PathParam("param") String param) {
		return "see config/app";
	}
	
	/**
	 * This method is called if TEXT_XML is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */
	@Path("/app/{param}/user")
	public LogUserTodoResource getUserTodoS(
			@PathParam("param") String param) {
		return new LogUserTodoResource(uriInfo, request,param);
	}

}
