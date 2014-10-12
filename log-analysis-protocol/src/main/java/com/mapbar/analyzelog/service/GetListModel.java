package com.mapbar.analyzelog.service;

import java.util.ArrayList;
import java.util.List;

import com.mapbar.analyzelog.model.Error;
import com.mapbar.analyzelog.model.Event;
import com.mapbar.analyzelog.model.Launch;
import com.mapbar.analyzelog.model.Prop;
import com.mapbar.analyzelog.model.Terminate;

public class GetListModel {

	public final static List<Launch> getListLaunch(final String str){
		GetListBean listbean=new GetListBean();
		List<Object> list=listbean.GetListBean1("launch",str,Launch.class);
		List<Launch> list2=new ArrayList<Launch>();
		for(Object model:list){
			Launch launch=(Launch)model;
			list2.add(launch);
		}
		return list2;
	}

	public final static List<Terminate> getTerminates(final String str){
		GetListBean listbean=new GetListBean();
		List<Object> list=listbean.GetListBean2("terminate",str,Terminate.class);
		List<Terminate> list2=new ArrayList<Terminate>();
		for(Object model:list){
			net.sf.ezmorph.bean.MorphDynaBean bean=(net.sf.ezmorph.bean.MorphDynaBean)model;
			
			Terminate launch=new Terminate();
			launch.setT(get(bean,"t").toString());
			Object obj=get(bean,"atvs");
			if(!obj.equals(""))
			launch.setAtvs((List)obj);
			launch.setDt(get(bean,"dt").toString());
			launch.setSid(get(bean,"sid").toString());
			list2.add(launch);
		}
		return list2;
	}
	
	private final static Object get(final net.sf.ezmorph.bean.MorphDynaBean bean,final String obj){
		try{
			return obj!=null&&!obj.equals("")?bean.get(obj):"";
		}catch(net.sf.ezmorph.MorphException e){
			return "";
		}
	}
	
	public final static List<Event> getEvents(final String str){
		GetListBean listbean=new GetListBean();
		List<Object> list=listbean.GetListBean3("event",str,Event.class);
		List<Event> list2=new ArrayList<Event>();
		for(Object model:list){
			Event launch=(Event)model;
			list2.add(launch);
		}
		return list2;
	}
	
	public final static List<Prop> getProps( final String str){
		GetListBean listbean=new GetListBean();
		List<Object> list=listbean.GetListBean4("props",str,Prop.class);
		List<Prop> list2=new ArrayList<Prop>();
		for(Object model:list){
			Prop launch=(Prop)model;
			list2.add(launch);
		}
		return list2;
	}
	
	public final static List<Error> getErrors(final String str){
		GetListBean listbean=new GetListBean();
		List<Object> list=listbean.GetListBean9("error",str,com.mapbar.analyzelog.model.Error.class);
		List<Error> list2=new ArrayList<Error>();
		for(Object model:list){
			Error launch=(Error)model;
			list2.add(launch);
		}
		return list2;
	}
}
