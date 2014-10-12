package com.mapbar.analyzelog.core.hbase;

import com.mapbar.analyzelog.core.exception.ConnectionException;

/**
 * HBase 数据连接异常。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 *
 */
public class HBaseConnectionException extends ConnectionException{

	private static final long serialVersionUID = 2602295443930048780L;

	public HBaseConnectionException(String msg){
		super(msg);
	}

	public HBaseConnectionException(String msg, Throwable t) {
		super(msg, t);
	}

}
