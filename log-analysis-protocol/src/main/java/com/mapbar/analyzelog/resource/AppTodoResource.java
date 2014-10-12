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
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;

import com.mapbar.analyzelog.common.Common;
import com.mapbar.analyzelog.model.AppTodo;
import com.mapbar.analyzelog.model.ConfigApp;
import com.mapbar.analyzelog.service.AppTodoDao;
import com.mapbar.analyzelog.service.ConfigAppService;
/**
 * <p>
 * $Header:
 * /server/analyzelog/protocol/com.mapbar.analyzelog.resource.AppTodoResource
 * .java,lijie Exp $ $Version: 1.0 $ $Date: 2012/02/04 $
 * </p>
 * <p>
 * <ul>
 * <li>
 * AppTodoResource: This {@link Path} is installed to the
 * {@link java.lang.annotation.Annotation} substance</li>
 * </ul>
 * </p>
 */
public class AppTodoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	public AppTodoResource(UriInfo uriInfo, Request request) {
		this.uriInfo = uriInfo;
		this.request = request;
	}
	
	@GET
	@Path("/{param}")
	@Produces({Common.MIME_JSON_TYPE,Common.MIME_MAPBAR_TYPE,Common.MIME_TYPE})
	public String getApp(@PathParam("param") String param) {
		ConfigAppService service=new ConfigAppService();Common common=new Common();
		ConfigApp asd=null;String str=null;
//		AppTodo todo=new AppTodo();
		if(StringUtils.isNotEmpty(param))
			asd=service.getConfigAppModel(Integer.parseInt(param));
		if(asd==null){
			asd=service.getConfigAppModel(9999);
		}
		StringBuffer sb=new StringBuffer();
		sb.append("{").append("\"lct\":").append("\"").append(asd.getLast_config_time()).append("\",")
		.append("\"rp\":").append(asd.getReport_policy()).append(",")
		.append("\"ops\":").append(asd.getOnline_params()).append("}");
		str=sb.toString();
		return common.GetResponse(str);
	}
	
	/**
	 * AppTodoDaolication integration
	 * This method is called if APPLICATION_XML,APPLICATION_JSON are request <h4>uri</h4>
	 * /config/app/{param}
	 * @return
	 */ 		
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAppTodo(@PathParam("param") String param) {
		ConfigAppService service=new ConfigAppService();
		ConfigApp asd=null;
		AppTodo todo=new AppTodo();
		if(param!=null&&!param.equals(""))
			asd=service.getConfigAppModel(Integer.parseInt(param));
		if(asd!=null){
			todo.setLct(asd.getLast_config_time());
			todo.setOps(asd.getOnline_params());
			todo.setRp(String.valueOf(asd.getReport_policy()));
		}
		Common common=new Common();
		Response.ok();
		return common.GetResponse(todo);
	}
	
	/**
	 * For the browser
	 * This method is called if TEXT_XML is request <h4>uri</h4>
	 * /config/app/{param}
	 * @return
	 */
	@GET
	@Path("/{param}")
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
	@Path("/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public String PlainTextConfig(@PathParam("param") String param) {
		return "see config/app";
	}
	
	/**
	 * This method is called if TEXT_PLAIN is request <h4>uri</h4>
	 * /config
	 * @return
	 */
	@GET
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
	@Produces(MediaType.TEXT_HTML)
	public String AppHtmlConfig() {
		return "<html> " + "<title>" + "see config/app" + "</title>"
				+ "<body><h1>" + "see config/app" + "</body></h1>" + "</html> ";
	}
	
	/**
	 * This method is called if TEXT_XML is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */
	@Path("/{param}/user")
	public UserTodoResource getUserTodoS(
			@PathParam("param") String param) {
		return new UserTodoResource(uriInfo, request,param);
	}
	
	/**
	 * This method is called if TEXT_XML is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * @return
	 */
	@Path("/{param}/feedbacks")
	public UserFeedBacksResource getFeedBacks(
			@PathParam("param") String param) {
		return new UserFeedBacksResource(uriInfo, request,param);
	}
}
