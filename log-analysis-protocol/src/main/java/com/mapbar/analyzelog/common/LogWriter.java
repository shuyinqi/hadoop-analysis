package com.mapbar.analyzelog.common;

import org.apache.log4j.Logger;

/**
 * <p>
 * $Header: /server/analyzelog/protocol/com.mapbar.analyzelog.common.LogWriter.java,lijie Exp $
 * $Version: 1.0 $
 * $Date: 2012/02/04 $
 * </p>
 */
public class LogWriter {
	private static Logger logReq = Logger.getLogger("query");
	private static Logger logSrv = Logger.getLogger("server");
	private static Logger logRes = Logger.getLogger("resource");
	private static Logger logDb = Logger.getLogger("db");
	
	public static void reqDebug(String str) {
		logReq.debug(str);
	}

	public static void SrvDebug(String str) {
		logSrv.debug(str);
	}
	
	public static void ResDebug(String str) {
		logRes.debug(str);
	}
	public static void dbDebug(String str) {
		logDb.debug(str);
	}
}
