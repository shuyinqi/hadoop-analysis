package com.mapbar.analyzelog.resource;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.Inflater;
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
import com.mapbar.analyzelog.common.LogWriter;
import com.mapbar.analyzelog.model.AppTodo;
import com.mapbar.analyzelog.model.LogAppUser;
import com.mapbar.analyzelog.model.UserTodo;
import com.mapbar.analyzelog.service.AppTodoDao;
import com.mapbar.analyzelog.service.UserService;
import com.mapbar.analyzelog.service.GetBeanService;
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
public class LogUserTodoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String app_id;

	public LogUserTodoResource(UriInfo uriInfo, Request request, String app_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.app_id = app_id;
	}

	private static final Logger LOGGER = Grizzly
			.logger(LogUserTodoResource.class);

	/**
	 * This method is called if TEXT_PLAIN is request <h4>uri</h4>
	 * /config/app/{param}/user
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String AppPlainTextConfig() {
		return "see /config/app/{param}/user";
	}

	/**
	 * This method is called if XML is request <h4>uri</h4>
	 * /config/app/{param}/user
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public String AppXMLConfig() {
		return "<?xml version=\"1.0\"?>"
				+ "<hello>see /config/app/{param}/user" + "</hello>";
	}

	/**
	 * This method is called if HTML is request <h4>uri</h4>
	 * /config/app/{param}/user
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String AppHtmlConfig() {
		return "<html> " + "<title>" + "see /config/app/{param}/user"
				+ "</title>" + "<body><h1>" + "see /config/app/{param}/user"
				+ "</body></h1>" + "</html> ";
	}

	/**
	 * AppTodoDaolication integration This method is called if
	 * APPLICATION_XML,APPLICATION_JSON are request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * 
	 * @return
	 */
	@GET
	@Path("/{param}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AppTodo getUserAppTodo(@PathParam("param") String param) {
		AppTodo AppTodo = AppTodoDao.instance.getModel().get(param);
		if (AppTodo == null)
			throw new RuntimeException("Get: AppTodo with " + param
					+ " not found");
		return AppTodo;
	}

	/**
	 * For the browser This method is called if TEXT_XML is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * 
	 * @return
	 */
	@GET
	@Path("/{param}")
	@Produces(MediaType.TEXT_XML)
	public List<AppTodo> getUserTodoXML(@PathParam("param") String param) {
		List<AppTodo> todos2 = new ArrayList<AppTodo>();
		todos2.addAll(AppTodoDao.instance.getModel().values());
		return todos2;
	}

	/**
	 * For the browser This method is called if TEXT_PLAIN is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * 
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
	 * 
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/{param}")
	@Produces({ Common.MIME_JSON_TYPE, Common.MIME_MAPBAR_TYPE,
			Common.MIME_TYPE })
	public String postUsers(@PathParam("param") String param, byte[] data)
			throws Exception {
		com.sun.jersey.spi.container.ContainerRequest mreq = (ContainerRequest) request;
		String contentName = mreq.getHeaderValue("content-encoding");
		String acceptEncoding = mreq.getHeaderValue("accept-encoding");
		String accept = mreq.getHeaderValue("accept");
LOGGER.info("see:com.mapbar.analyzelog.resource.LogUserTodoResource#postUsers();accept="+accept);
		String newItem = null;
		UserTodo c = new UserTodo();
		c.setApp_id(app_id);
		c.setUser_id(param);
		if (contentName == null & contentName == null &(accept.indexOf("mapbar") == -1)) {
			try {
				String getdata = new String(data, "utf-8");
				newItem = java.net.URLDecoder.decode(getdata, "utf-8");
				if (accept.indexOf("mapbar") == -1) {
					String referer = mreq.getHeaderValue("referer");
					LOGGER.info(referer);
					return getAppTodo(param, newItem);
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				LOGGER.severe(e.getMessage());
			}
		} else if ((contentName != null && contentName.equals("deflate"))||(acceptEncoding != null
				&& acceptEncoding.indexOf("gzip") != -1)) {
			try {
				Inflater decompresser = new Inflater();
				decompresser.setInput(data);
				byte[] result = new byte[8192];
				int resultLength = decompresser.inflate(result);
				decompresser.end();

				String outputString = new String(result, 0, resultLength,
						"UTF-8");
				newItem = outputString;
			} catch (java.io.UnsupportedEncodingException e) {
				LOGGER.severe(e.getMessage());
			} catch (java.util.zip.DataFormatException e) {
				e.printStackTrace();
				LOGGER.severe(e.getMessage());
			}
		}
		GetBeanService bean = new GetBeanService();
		if(accept.indexOf("mapbar") != -1){
			String getdata = new String(data, "utf-8");
			newItem = java.net.URLDecoder.decode(getdata, "utf-8");
		}
		String newstr = newItem.replace("content=", "");
		LogAppUser user = bean.getbean(newstr);
		UserService service = new UserService();
		int i = service.putLogUserApp(user, c);
		Common common = new Common();
		return common.PostResponse(i);
	}

	/**
	 * This method is called if APPLICATION_JSON is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * 
	 * @return
	 */
	@POST
	@Path("/{param}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postTodo(@PathParam("param") String param,
			JAXBElement<UserTodo> todo) {
		LogWriter
				.reqDebug("see:com.mapbar.analyzelog.resource.LogUserTodoResource#postTodo();info:start:data:"
						+ todo.getValue().toString());
		Common common = new Common();
		return common.putAndGetResponse(0);
	}

	/**
	 * This method is called if APPLICATION_XML is request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * 
	 * @return
	 */
	@PUT
	@Path("/{param}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(@PathParam("param") String param,
			JAXBElement<UserTodo> todo) {
		Common common = new Common();
		return common.putAndGetResponse(0);
	}

	/**
	 * AppTodoDaolication integration This method is called if
	 * APPLICATION_XML,APPLICATION_JSON are request <h4>uri</h4>
	 * /config/app/{param}/user/{user_id}
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAppTodo(@PathParam("param") String param, String appid)
			throws Exception {
		TestFormUtil util = new TestFormUtil();
		int i = 0;
		Common common = new Common();
		try {
			UserTodo c = new UserTodo();
			String[] st = appid.split("&");
			c.setApp_id(st[0].split("=")[1]);
			c.setUser_id(st[1].split("=")[1]);
			String newstr = util.formatStr(st[2]).replace("content=", "");

			GetBeanService bean = new GetBeanService();
			LogAppUser user = bean.getbean(newstr);
			UserService service = new UserService();
			i = service.putLogUserApp(user, c);
		} catch (Exception e) {
			LOGGER.severe("see:com.mapbar.analyzelog.resource.LogUserTodoResource#getAppTodo();error_appid:"+appid+";message:"+e.getMessage());
			return common.PostError(e);
		}
		return common.PostResponse(i);
	}
	
	@POST
	@Path("/{param}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String getAppTodo2(@PathParam("param") String param, String appid)
			throws Exception {
		TestFormUtil util = new TestFormUtil();
		int i = 0;
		Common common = new Common();
		try {
			UserTodo c = new UserTodo();
			String newstr = util.formatStr(appid).replace("content=", "");
			c.setApp_id(app_id);
			c.setUser_id(param);
			GetBeanService bean = new GetBeanService();
			LogAppUser user = bean.getbean(newstr);
			UserService service = new UserService();
			i = service.putLogUserApp(user, c);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return common.PostError(e);
		}
		return common.PostResponse(i);
	}
}
