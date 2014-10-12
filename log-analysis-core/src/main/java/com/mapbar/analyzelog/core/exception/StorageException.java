package com.mapbar.analyzelog.core.exception;

/**
 * 数据存储操作异常。 
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class StorageException extends RuntimeException{

	private static final long serialVersionUID = 8419452780807647067L;

	public StorageException(String msg){
		super(msg);
	}
	
	public StorageException(String msg, Throwable t) {
		super(msg, t);
	}
}
