package com.mapbar.analyzelog.manage.region;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.mapbar.analyzelog.utils.Config;
import com.mapbar.analyzelog.utils.FileUtils;

/**
 * 分区收集器
 * 
 * @author lisf
 * 
 */
public class RegionCollector {

	private static HTable table = null;

	static {
		try {
			RegionCollector.table = new HTable(Config.getInstance(), ".META.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if (args == null || args.length <= 0) {
			return;
		}
		RegionCollector.collectRegion(args[0]);
	}

	/**
	 * 根据表名收集该表的分区Key
	 * 
	 * @param tableName
	 *            表的名称
	 * @throws IOException
	 */
	public static void collectRegion(String tableName) throws IOException {
		BufferedWriter out = FileUtils.getOut(tableName);
		ResultScanner resultScanner = RegionCollector.table
				.getScanner(RegionCollector.getRegionScan(tableName));
		String regex = new StringBuilder("^").append(tableName).append(",.*?$")
				.toString();
		String regionKey = null;
		for (Result result : resultScanner) {
			regionKey = Bytes.toString(result.getRow());
			if (regionKey.matches(regex)) {
				out.write(regionKey);
				out.newLine();
			}
			regionKey = null;
		}
		out.flush();
		out.close();
		out = null;
	}

	/**
	 * 根据表名生成获取该表分区Key的Scan对象
	 * 
	 * @param table
	 *            表的名称
	 * @return 获取指定表分区Key的Scan对象
	 */
	private static Scan getRegionScan(String table) {
		Scan scan = new Scan();
		scan.setCaching(1000);
		scan.setFilter(new PrefixFilter(Bytes.toBytes(table)));
		scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("server"));
		return scan;
	}

}
