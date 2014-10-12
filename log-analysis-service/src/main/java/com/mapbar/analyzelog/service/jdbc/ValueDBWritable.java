package com.mapbar.analyzelog.service.jdbc;

import java.util.HashMap;
import java.util.Map;

public class ValueDBWritable extends AbstractDBWritable{

	protected Map<String, Object> fields = new HashMap<String, Object>();
	
	public ValueDBWritable(){
	}

	public ValueDBWritable(String key, long count){
		fields.put(key, count);
	}

	public void put(String key, long count) {
		fields.put(key, count);
	}
}
