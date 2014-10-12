package com.mapbar.analyzelog.common;

import java.net.URI;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
/**
 * <p>
 * $Header:
 * /server/analyzelog/protocol/com.mapbar.analyzelog.common.Common
 * .java,lijie Exp $ $Version: 1.0 $ $Date: 2012/02/04 $
 * </p>
 * <p>
 * <ul>
 * <li>
 * Common: This class is installed to the public method.
 * </ul>
 * </p>
 */
public class Common {
	public static final String MIME_MAPBAR_TYPE="applicatioin/mapbar*";
	public static final String MIME_JSON_TYPE="applicatioin/mapbar.*+json";
	public static final String MIME_TYPE="*/*";
	public Common(){};
	
	/**
	 * This method is uri path by UriInfo 
	 * @return String
	 */
	public String getMethods(UriInfo uriInfo){
		return uriInfo.getBaseUri().toString()+uriInfo.getPath().toString();
	}
	
	/**
	 * This method is response 
	 * @return javax.ws.rs.core.Response
	 */
	public Response putAndGetResponse(final int... i) {
		if(i!=null&&i[0]==0)
			return Response.status(400).build();
		String output="{"+"sc"+":"+"Y"+"}";
		return Response.status(200).entity(output).build();
	}
	
	public Response seeOtherResponse(final int... i) {
		if(i!=null&&i[0]==0)
			return Response.noContent().build();
		return Response.seeOther(getBaseURI()).build();
	}
	
	public String PostResponse(final int... i) throws Exception {
		if(i!=null&&i[0]==0){
			return PostError();
		}
		String output="{"+"sc"+":"+"Y"+"}";
		return output;
	}
	
	public String PostResponse(String s) throws Exception {
		if(s!=null&&!s.equals(""))
			return PostError(s);
		String output="{"+"sc"+":"+"Y"+"}";
		return output;
	}
	
	public String putResponse(String s) throws Exception {
		if(s==null||s.equals(""))
			return PostError();
		else return s;
	}
	
	public String PostError(Exception e) throws Exception {
		String output="error";
		Response.status(400).entity(output).build();
		throw new Exception(e.getCause());
	}
	
	public String PostError() throws Exception {
		String output="error";
		Response.status(400).entity(output).build();
		throw new Exception();
	}
	
	public String PostError(String s) throws Exception {
		throw new Exception(s);
	}
	
	public String GetResponse(final int code,final String str) {
		return Response.status(code).entity(str).toString();
	}
	
	public Response putAndGetResponse(final int code,final String str) {
		return Response.status(code).entity(str).build();
	}
	
	public Response GetResponse(final Object str) {
		return Response.status(200).type("application/json").entity(str).build();
	}
	
	public String GetResponse(final String str) {
//		return Response.status(200).entity(str).build().toString();
		return str;
	}
	
	private static URI getBaseURI() {
		return UriBuilder
				.fromUri(ResourcePath.instance.getPath("server.uri"))
				.port(Integer.parseInt(ResourcePath.instance
						.getPath("server.port"))).build();
	}
}
