package com.mapbar.analyzelog.core.exception;

/**
 * 数据连接异常。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public abstract class ConnectionException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ConnectionException(String msg){
		super(msg);
	}

	public ConnectionException(String msg, Throwable t) {
		super(msg, t);
	}

}
