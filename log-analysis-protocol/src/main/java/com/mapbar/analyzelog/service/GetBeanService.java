package com.mapbar.analyzelog.service;

import com.mapbar.analyzelog.model.Body;
import com.mapbar.analyzelog.model.Header;
import com.mapbar.analyzelog.model.LogAppUser;
import com.mapbar.analyzelog.util.JsonToBeanUtil;

public class GetBeanService {
	public GetBeanService(){}
	public final LogAppUser getbean(final Object data){
		LogAppUser beans=null;
		beans=(LogAppUser)getInfoBean(data);
		return beans;
	}
	
	public final LogAppUser getInfoBean(final Object aStrJson){
		JsonToBeanUtil util=new JsonToBeanUtil();
		util.getJsonObject(aStrJson.toString().replaceAll("\\n",""));
		LogAppUser bean =new LogAppUser();
		bean.setHeadermodel((Header) util.getBean("head",Header.class));
		Body body=new Body();
		body.setLaunch(GetListModel.getListLaunch(util.getStr("body")));
		body.setEvent(GetListModel.getEvents(util.getStr("body")));
		body.setProp(GetListModel.getProps(util.getStr("body")));
		body.setTerminates(GetListModel.getTerminates(util.getStr("body")));
		body.setErrors(GetListModel.getErrors(util.getStr("body")));
		bean.setBodymodel(body);
		return bean;
	}
}
