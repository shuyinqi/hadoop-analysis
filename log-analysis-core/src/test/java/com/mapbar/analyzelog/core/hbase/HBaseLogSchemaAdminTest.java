package com.mapbar.analyzelog.core.hbase;

import java.io.IOException;

import junit.framework.TestCase;

import com.mapbar.analyzelog.core.SystemConstants.LogType;


/**
 * HBaseLogSchemaAdmin.java Test Case.
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 *
 */
public class HBaseLogSchemaAdminTest extends TestCase{

	public void t1estNewSchemaAdminObjeInstance(){
		HBaseLogSchemaAdmin baseLogSchemaAdmin = new HBaseLogSchemaAdmin();
		assertNotNull(baseLogSchemaAdmin);
	}

	public void testCreateAppLogTables(){
		String appID = "1010";
		HBaseLogSchemaAdmin baseLogSchemaAdmin = new HBaseLogSchemaAdmin();
		assertNotNull(baseLogSchemaAdmin);

		try {
		//	baseLogSchemaAdmin.dropAppLogTables(appID);
			baseLogSchemaAdmin.createAppLogTables(appID);
		} catch (IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public void t1estCreateLogTable(){
		String appID = "1003";
		HBaseLogSchemaAdmin baseLogSchemaAdmin = new HBaseLogSchemaAdmin();
		try {
			//baseLogSchemaAdmin.dropAppLogTables(appID);
			baseLogSchemaAdmin.createLogTable(appID, LogType.TERMINATE);
			baseLogSchemaAdmin.createLogTable(appID, LogType.LAUNCH);
		} catch (IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
