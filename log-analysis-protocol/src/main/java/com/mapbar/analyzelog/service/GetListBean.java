package com.mapbar.analyzelog.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.model.Error;
import com.mapbar.analyzelog.model.Event;
import com.mapbar.analyzelog.model.Launch;
import com.mapbar.analyzelog.model.Prop;
import com.mapbar.analyzelog.model.Terminate;
import com.mapbar.analyzelog.util.JsonToBeanUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class GetListBean {
	
	public List<Object> GetListBean1(String string, String str2, Class<Launch> class1) {
		return GetListBean(string,str2,class1);
	}

	public List<Object> GetListBean2(String string, String str2, Class<Terminate> class1) {
		return GetListBean5(string,str2,class1);
	}
	public List<Object> GetListBean3(String string, String str2, Class<Event> class1) {
		return GetListBean(string,str2,class1);
	}
	public List<Object> GetListBean4(String string, String str2, Class<Prop> class1) {
		return GetListBean(string,str2,class1);
	}
	public List<Object> GetListBean8(String string, String str2, Class<Equipment> class1) {
		return GetListBean6(string,str2,class1);
	}
	public List<Object> GetListBean9(String string, String str2, Class<com.mapbar.analyzelog.model.Error> class1) {
		return GetListBean(string,str2,class1);
	}
	
	List<Object> GetListBean(final String str,final String str2,final Class<?> aClz){
		List<Object> list = new ArrayList<Object>();
		JsonToBeanUtil util2=new JsonToBeanUtil();
		util2.getJsonObject(str2.replaceAll("\\n",""));
		JSONArray jsonAry = util2.getoptArray(str);
		if(jsonAry!=null)
		for(int i=0;i<jsonAry.size();i++){
			JSONObject jsonObj=jsonAry.getJSONObject(i);
			for (Iterator<?> it = jsonObj.keySet().iterator(); it.hasNext(); ) {
				  String key = (String)it.next();
		            		if(jsonObj.get(key).toString().equals("null"))
		            			jsonObj.put(key,"");
		            }
		            
			Object bean=JSONObject.toBean(jsonObj,aClz);
			list.add(bean);
		}
		return list;
	}
	
	List<Object> GetListBean5(final String str,final String str2,final Class<?> aClz){
		List<Object> list = new ArrayList<Object>();
		JsonToBeanUtil util2=new JsonToBeanUtil();
		util2.getJsonObject(str2);
		JSONArray jsonAry = util2.getoptArray(str);	
		if(jsonAry!=null)
		for(int i=0;i<jsonAry.size();i++){
			JSONObject jsonObj=jsonAry.getJSONObject(i);
			List<Object> st=new ArrayList<Object>();
			for (Iterator<?> it = jsonObj.keySet().iterator(); it.hasNext(); ) {
				  String key = (String)it.next();
				  if(key.equals("atvs")){
					  JSONArray jsonAry2 = jsonObj.optJSONArray("atvs");
					  for(int j=0;j<jsonAry2.size();j++){
						  Object obj=null;
							  try{
						  JSONArray jsonObj2=jsonAry2.getJSONArray(j);
							obj=jsonObj2;
							  }catch(JSONException e){
								  obj=jsonAry2.get(j);
							  }
						  st.add(obj);
					  }
          			jsonObj.put("atvs",st);
          		}else  		
				  if(jsonObj.get(key).toString().equals("null"))
		            			jsonObj.put(key,"");
		            }
		            
			Object bean=JSONObject.toBean(jsonObj);
			list.add(bean);
		}
		return list;
	}
	
	List<Object> GetListBean6(final String str,final String str2,final Class<?> aClz){
		List<Object> list = new ArrayList<Object>();
		JsonToBeanUtil util2=new JsonToBeanUtil();
		util2.getJsonObject(str2);
		JSONArray jsonAry = util2.getoptArray(str);	
		if(jsonAry!=null)
		for(int i=0;i<jsonAry.size();i++){
			JSONObject jsonObj=jsonAry.getJSONObject(i);
			List<String> st=new ArrayList<String>();
			for (Iterator<?> it = jsonObj.keySet().iterator(); it.hasNext(); ) {
				  String key = (String)it.next();
				  if(key.equals("atvs")){
					  JSONArray jsonAry2 = jsonObj.optJSONArray("atvs");
					  for(int j=0;j<jsonAry2.size();j++){
						  JSONArray jsonObj2=jsonAry2.getJSONArray(j);
							/*for(int k=0;k<jsonObj2.size();k++){
								String obj=jsonObj2.get(k).toString();
								st.add(obj);
							}*/
						  String obj=jsonObj2.toString();
						  st.add(obj);
					  }
          			jsonObj.put("atvs",st);
          		}else  		
				  if(jsonObj.get(key).toString().equals("null"))
		            			jsonObj.put(key,"");
		            }
		            
			Object bean=JSONObject.toBean(jsonObj);
			list.add(bean);
		}
		return list;
	}
	
}
