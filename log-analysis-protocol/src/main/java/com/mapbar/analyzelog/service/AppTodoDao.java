package com.mapbar.analyzelog.service;
import java.util.HashMap;
import java.util.Map;

import com.mapbar.analyzelog.model.AppTodo;

public enum  AppTodoDao {
instance;
	
	private Map<String, AppTodo> contentProvider = new HashMap<String, AppTodo>();
	
	/** (non-Javadoc)
	 * @see com.mapbar.analyzelog.dao.AppTodoDao#queryByNamedSql(Class<T>, java.lang.String, java.util.Map)
	 */
	private AppTodoDao() {
		
		AppTodo AppTodo = new AppTodo("1","2011-10-10 10:1:12");
		AppTodo.setOps("sssssssss");
		contentProvider.put("1", AppTodo);
	}
	public Map<String, AppTodo> getModel(){
		return contentProvider;
	}

}
