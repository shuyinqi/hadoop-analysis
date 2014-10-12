package com.mapbar.analyzelog.core;

import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.hbase.HBaseLogStorageManager;

public class HbaseTest {
	public static void main(String[] args) {
		System.out.println("Hbase Demo Application ");

		// CONFIGURATION

		// ENSURE RUNNING
		try {
			for (int i = 0;i < 100; i++){
				
				new Thread(new Runnable() {
					public void run() {
						LogStorage logStorage = HBaseLogStorageManager.getStorageManager().getLogStorage("1000");
						try {
							Thread.sleep(10000);
							logStorage.putUser("uid", new Equipment());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(logStorage);
					}
				}).start();
				System.out.println("run..");
			}
		
//			HBaseConfiguration config = new HBaseConfiguration();
//			config.clear();
//			config.set("hbase.zookeeper.quorum", "192.168.0.162");
//			config.set("hbase.zookeeper.property.clientPort", "2181");
//			config.set("hbase.master", "192.168.0.162:60000");
////			config.set("hbase.master.dns.interface", "eth0");
////			config.set("hbase.master.dns.nameserver", "192.168.0.162");
////			config.set("hbase.regionserver.dns.interface", "eth0");
////			config.set("hbase.regionserver.dns.nameserver", "192.168.0.162");
//			// HBaseConfiguration config = HBaseConfiguration.create();
//			// config.set("hbase.zookeeper.quorum", "localhost"); // Here we are
//			// running zookeeper locally
//			HBaseAdmin.checkHBaseAvailable(config);
//
//			System.out.println("HBase is running!");
//			// createTable(config);
//			// creating a new table
//			HTable table = new HTable(config, "mytable");
//			System.out.println("Table mytable obtained ");
//			// addData(table);
//		} catch (MasterNotRunningException e) {
//			System.out.println("HBase is not running!");
//			System.exit(1);
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}
}
