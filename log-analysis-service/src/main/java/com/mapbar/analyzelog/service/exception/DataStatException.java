package com.mapbar.analyzelog.service.exception;

public class DataStatException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataStatException(Exception e, String msg) {
		super(msg, e);
	}

	public DataStatException(Throwable throwable){
		super(throwable);
	}

	public DataStatException(String msg){
		super(msg);
	}

	
}
