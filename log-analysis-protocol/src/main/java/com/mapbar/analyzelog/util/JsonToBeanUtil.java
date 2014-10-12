package com.mapbar.analyzelog.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class JsonToBeanUtil{
	
	private JSONObject jsonLogObject;

	public final Object getBean(final String aStrKey,final Class<?> aClz){
		return JSONObject.toBean((JSONObject) this.jsonLogObject.get(aStrKey),aClz);
	}
	
	public final Object getBean(final Class<?> aClz){
		return JSONObject.toBean(jsonLogObject,aClz);
	}
	
	public final void getJsonObject(final Object aStrJson){
		try{
		this.jsonLogObject=JSONObject.fromObject(aStrJson);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public final String getStr(final String str){
		return str!=null&&!str.equalsIgnoreCase("null")?jsonLogObject.getString(str):"";
	}
	
	public final JSONArray getoptArray(final String str){
		return jsonLogObject.optJSONArray(str);
	}
	
	public final JSONArray getArray(final String str){
		 return jsonLogObject.getJSONArray(str);
	 }
}