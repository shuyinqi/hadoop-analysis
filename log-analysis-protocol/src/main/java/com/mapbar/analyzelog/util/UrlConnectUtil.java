package com.mapbar.analyzelog.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Map;


public class UrlConnectUtil {
	public UrlConnectUtil(){} 
	
	public  String urlConnection(String url, Map<String, String> params,
			String method, String en,boolean fn) throws MalformedURLException, IOException {
		String param = "";
		for (Map.Entry<String, String> entry : params.entrySet()) {
			param = param + entry.getKey() + "="
					+ URLEncoder.encode(entry.getValue(), "utf-8") + "&";
		}
		String request_url = null;
		if ("POST".equals(method)) {
			request_url=url;
		} else if(fn)
		{
			request_url=url+param;
		}else{
			request_url=url+"?"+param;
		}
		String obj=HttpUtil.httpGetText(request_url);
		return obj;
	}
	
	public  String urlConnection(String url, Map<String, String> params,
			String method,boolean fn) throws MalformedURLException, IOException {
		String param = "";
		for (Map.Entry<String, String> entry : params.entrySet()) {
			param = param + entry.getKey() + "="
					+ URLEncoder.encode(entry.getValue(), "utf-8") + "&";
		}
		String request_url = null;
		if ("POST".equals(method)) {
			request_url=url;
		} else if(fn)
		{
			request_url=url+param;
		}else{
			request_url=url+"?"+param;
		}
		//String obj=HttpUtil.httpGetText(request_url);
		return request_url;
	}
	
	public  String getConnection(String url, Map<String, String> params,
			String method,boolean fn) throws MalformedURLException, IOException {
		String param = "";
		for (Map.Entry<String, String> entry : params.entrySet()) {
			param = param + entry.getKey() + "="
					+ entry.getValue()+ "&";
		}
		String request_url = null;
		if ("POST".equals(method)) {
			request_url=url;
		} else if(fn)
		{
			request_url=url+param;
		}else{
			request_url=url+"?"+param;
		}
		//String obj=HttpUtil.httpGetText(request_url);
		return request_url;
	}
	
}
