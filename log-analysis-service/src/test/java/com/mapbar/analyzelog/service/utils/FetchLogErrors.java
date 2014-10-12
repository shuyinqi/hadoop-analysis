package com.mapbar.analyzelog.service.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

public class FetchLogErrors {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Exproting...");
		System.out.println(new FetchLogErrors().fetchAllLogErrors().size() + " Export Done.");
	}
	
	private static final Logger LOGGER = Logger.getLogger(FetchLogErrors.class);
	
	public List<String> fetchAllLogErrors() throws IOException{
		HTable hTable = new HTable("app_1000_error");
		List<String> errors = new ArrayList<String>();

		Scan scan = new Scan();
		scan.setTimeRange(1218891389000L, 1333025789000L);
		scan.addColumn(Bytes.toBytes("er"), Bytes.toBytes("chne"));
		scan.addColumn(Bytes.toBytes("er"), Bytes.toBytes("clnt"));
		scan.addColumn(Bytes.toBytes("er"), Bytes.toBytes("osv"));
		scan.addColumn(Bytes.toBytes("er"), Bytes.toBytes("acs"));
		scan.addColumn(Bytes.toBytes("er"), Bytes.toBytes("appv"));
		scan.addColumn(Bytes.toBytes("er"), Bytes.toBytes("content"));
		scan.addColumn(Bytes.toBytes("er"), Bytes.toBytes("uid"));
		ResultScanner resultScanner = hTable.getScanner(scan);
		Iterator<Result> iterator = resultScanner.iterator();
		while(iterator.hasNext()){
			Result result = iterator.next();
			if(getValue(result, "uid").length() <=  25){
				continue;
			}
			LOGGER.info("--------------------");
			LOGGER.info("UID：" + getValue(result, "uid"));
			LOGGER.info("渠道名称：" + getValue(result, "chne"));
			LOGGER.info("错误发生时间：" + getValue(result, "clnt"));
			LOGGER.info("操作系统版本：" + getValue(result, "osv"));
			LOGGER.info("应用版本：" + getValue(result, "appv"));
			LOGGER.info("错误日志：" + getValue(result, "content"));
			LOGGER.info("--------------------");
			LOGGER.info("");
			errors.add("");
		}
		resultScanner.close();
		return errors;
	}
	
	public String getValue(Result result, String name){
		return Bytes.toString(result.getValue(Bytes.toBytes("er"), Bytes.toBytes(name)));
	}
}
