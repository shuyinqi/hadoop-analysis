package com.mapbar.analyzelog.resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import org.glassfish.grizzly.Grizzly;
import com.mapbar.analyzelog.common.Common;
import com.mapbar.analyzelog.model.AppTodo;
import com.mapbar.analyzelog.model.UserTodo;
import com.mapbar.analyzelog.service.AppTodoDao;
import com.mapbar.analyzelog.service.UserService;
import com.mapbar.analyzelog.util.TestFormUtil;
import com.sun.jersey.spi.container.ContainerRequest;
/**
 * <p>
 * $Header:
 * /server/analyzelog/protocol/com.mapbar.analyzelog.resource.UserTodoResource
 * .java,lijie Exp $ $Version: 1.0 $ $Date: 2012/02/04 $
 * </p>
 * <p>
 * <ul>
 * <li>
 * UserTodoResource: This {@link Path} is installed to the
 * {@link java.lang.annotation.Annotation} substance</li>
 * </ul>
 * </p>
 */
public class UserTodoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String app_id;
	public UserTodoResource(UriInfo uriInfo, Request request, String app_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.app_id=app_id;
	}
	private static final Logger LOGGER = Grizzly
	.logger(UserTodoResource.class);
	/**
	 * This method is called if TEXT_PLAIN is request <h4>uri</h4>
	 * /config/app/{param}/user
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String AppPlainTextConfig() {
		return "see /config/app/{param}/user";
	}

	/**
	 * This method is called if XML is request
	 * <h4>uri</h4>
	 * /config/app/{param}/user
	 * @return 
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public String AppXMLConfig() {
		return "<?xml version=\"1.0\"?>" + "<hello>see /config/app/{param}/user" + "</hello>";
	}
	
	/**
	 * This method is called if HTML is request
	 * <h4>uri</h4>
	 * /config/app/{param}/user
	 * @return 
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String AppHtmlConfig() {
		return "<html> " + "<title>" + "see /config/app/{param}/user" + "</title>"
				+ "<body><h1>" + "see /config/app/{param}/user" + "</body></h1>" + "</html> ";
	}
	
	/**
	 * AppTodoDaolication integration
	 * This method is called if APPLICATION_XML,APPLICATION_JSON are request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */ 		
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public AppTodo getUserAppTodo(@PathParam("param") String param) {
		AppTodo AppTodo = AppTodoDao.instance.getModel().get(param);
		if(AppTodo==null)
			throw new RuntimeException("Get: AppTodo with " + param +  " not found");
		return AppTodo;
	}
	
	/**
	 * For the browser
	 * This method is called if TEXT_XML is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */
	@GET
	@Path("/{param}")
	@Produces(MediaType.TEXT_XML)
	public List<AppTodo> getUserTodoXML(@PathParam("param") String param) {
		List<AppTodo> todos2 = new ArrayList<AppTodo>();
		todos2.addAll( AppTodoDao.instance.getModel().values() );
		return todos2;
	}
	
	/**
	 * For the browser
	 * This method is called if TEXT_PLAIN is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */
	@GET
	@Path("/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserPlainTextConfig(@PathParam("param") String param) {
		return "see /config/app/{param}/user/{user_id}";
	}
	
	
	/**
	 * This method is called if APPLICATION_JSON is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Path("/{param}")
	@Produces({Common.MIME_JSON_TYPE,Common.MIME_MAPBAR_TYPE,Common.MIME_TYPE})
	public String postUsers(@PathParam("param") String param,String data) throws Exception {
//		LogWriter.reqDebug("see:com.mapbar.analyzelog.resource.UserTodoResource#postUsers;info:start:data:"
//				+ "app_id=" + app_id + "&param=" + param);
		Common common=new Common();
		String newItem=null;
		com.sun.jersey.spi.container.ContainerRequest mreq = (ContainerRequest) request;
		String accept=mreq.getHeaderValue("accept");
		
		try {
			newItem=java.net.URLDecoder.decode(
					data, "utf-8");
			if(accept.indexOf("mapbar")==-1){
				/*String referer=mreq.getHeaderValue("referer");
				LOGGER.log(Level.INFO,referer);*/
				return getAppTodo(param,newItem);
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.severe("see:com.mapbar.analyzelog.resource.UserTodoResource#postUsers();error_appid:"+param+";message:"+e.getMessage());
		}
		
		UserTodo c = new UserTodo();
		c.setApp_id(app_id);
		c.setUser_id(param);
		String newstr=newItem.replace("content=","");
		c.setDeviceModel(newstr);
		UserService service=new UserService();
		String i=service.PutUser(c);
		
		return common.PostResponse(i);
	}
	
	/**
	 * This method is called if APPLICATION_JSON is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */
	@POST
	@Path("/{param}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postTodo(@PathParam("param") String param,JAXBElement<UserTodo> todo) {
		Common common=new Common();
		return common.putAndGetResponse(0);
	}
	
	/*
	 * This method is called if APPLICATION_XML and TEXT_HTML are request 
	 * <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 */

	
	
	/**
	 * This method is called if APPLICATION_XML is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */
	@PUT
	@Path("/{param}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(@PathParam("param") String param,JAXBElement<UserTodo> todo) {
		Common common=new Common();
		return common.putAndGetResponse(1);
	}
	
	/**
	 * AppTodoDaolication integration
	 * This method is called if APPLICATION_XML,APPLICATION_JSON are request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String getAppTodo2(@PathParam("param") String param,String appid) throws Exception {
		String i=null;Common common=new Common();
		try {
		TestFormUtil util=new TestFormUtil();
		UserTodo c = new UserTodo();
		String newstr=util.formatStr(appid).replace("content=", "");
		c.setDeviceModel(newstr);
		UserService service=new UserService();
		i=service.PutUser(c);
		}catch(Exception e){
				LOGGER.log(Level.INFO, e.toString(), e);
				return common.PostError(e);
			}
		
		return common.PostResponse(i);
	}
	
	public String getAppTodo(@PathParam("param") String param,String appid) throws Exception {
		String i=null;Common common=new Common();
		try {
		TestFormUtil util=new TestFormUtil();
		UserTodo c = new UserTodo();
		String[] st=appid.split("&");
		c.setApp_id(st[0].split("=")[1]);
		c.setUser_id(st[1].split("=")[1]);
		String newstr=util.formatStr(st[2]).replace("content=", "");
		c.setDeviceModel(newstr);
		UserService service=new UserService();
		i=service.PutUser(c);
		}catch(Exception e){
			LOGGER.severe("see:com.mapbar.analyzelog.resource.UserTodoResource#getAppTodo();error_appid:"+param+";message:"+e.getMessage());
				return common.PostError(e);
			}
		
		return common.PostResponse(i);
	}
	
}
