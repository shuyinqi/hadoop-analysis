package com.mapbar.analyzelog.resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
import com.mapbar.analyzelog.service.UserFeedBacksService;
import com.mapbar.analyzelog.util.DateUtil;
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
public class UserFeedBacksResource {
	@Context
	UriInfo uriInfo3;
	@Context
	Request request;
	String param;
	public UserFeedBacksResource(UriInfo uriInfo, Request request, String app_id) {
		this.uriInfo3 = uriInfo;
		this.request = request;
		param=app_id;
	}
	private static final Logger LOGGER = Grizzly
	.logger(UserFeedBacksResource.class);
	
	/**
	 * get user news feedback  integration
	 * This method is called if APPLICATION_XML,APPLICATION_JSON are request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */ 		
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String getUserAppTodo(@PathParam("param") String param) {
		UserFeedBacksService service=new UserFeedBacksService();
		return service.getContent(this.param,param.replaceAll("&again=",""));
	}
	
	@GET
	@Path("/{param}/qid/{qid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String getUserFeedBackByQid(@PathParam("param") String param,@PathParam("qid") String qid) {
		UserFeedBacksService service=new UserFeedBacksService();
		return service.getContentByQid(this.param,param.replaceAll("&again=",""),qid);
	}
	
	/**
	 * get all of user feedbacks integration
	 * This method is called if APPLICATION_XML,APPLICATION_JSON are request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */ 
	@GET
	@Path("/{param}/all")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.WILDCARD})
	public byte[] getUserFeedBacksAll(@PathParam("param") String param) {
		UserFeedBacksService service=new UserFeedBacksService();
		return service.getContentAll(this.param,param.replaceAll("&again=",""));
	}
	
	@GET
	@Path("/{param}/all/test")
	@Produces({MediaType.TEXT_HTML})
	public String getUserFeedBacksAllTest(@PathParam("param") String param) {
		UserFeedBacksService service=new UserFeedBacksService();
		return service.getDecompressStr(this.param,param.replaceAll("&again=",""));
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
		Common common=new Common();
		String newItem=null;
		try {
			newItem=java.net.URLDecoder.decode(
					data, "utf-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.severe("see:com.mapbar.analyzelog.resource.UserFeedBacksResource#postUsers();error_appid:"+this.param+"&userid:"+param+";message:"+e.getMessage());
		}
		UserTodo c = new UserTodo();
		c.setApp_id(this.param);
		c.setUser_id(param);
		String newstr=newItem.replace("content=","");
		c.setDeviceModel(newstr);
		UserFeedBacksService service=new UserFeedBacksService();
		String s=service.putFeedBacks(c);
		return common.putResponse(s);
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
		return UUID.randomUUID().toString().replace("-","");
	}
	
}
