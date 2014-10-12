package com.mapbar.analyzelog.core;

/**
 * 提供对Log数据库的管理，如存储数据源的初始化、删除等操作。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public interface LogStorageManager {

	/**
	 * 创建一个新的日志数据存储源。
	 * 
	 * @param id log storage name
	 * @return 返回初始化完成的 LogStorage 对象
	 */
	public LogStorage create(String id);

	/**
	 * 根据 id 获取 LogStorage 实例
	 * 
	 * @param id log storage name
	 * @return 返回对应的 LogStorage 对象，如返回 Null,则不存在对应的 Storage.
	 */
	public LogStorage getLogStorage(String id);
	
	/**
	 * 返回所有 LogStorage 实例名。
	 * 
	 * @return all id arrays
	 */
	public String[] getAllStorageID();

	/**
	 * 根据 id 删除 storage 实例。
	 * 
	 * @param id the storage id
	 * @return true or false?
	 */
	public boolean drop(String id);

	/**
	 * 是否存在 storage 实例。
	 * 
	 * @param id the storage id
	 * @return true or false?
	 */
	public boolean exists(String id);
}

