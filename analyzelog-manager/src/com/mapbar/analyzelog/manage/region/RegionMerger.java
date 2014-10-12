package com.mapbar.analyzelog.manage.region;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.hadoop.hbase.util.Merge;
import org.apache.hadoop.util.ToolRunner;

import com.mapbar.analyzelog.utils.Config;
import com.mapbar.analyzelog.utils.Environment;
import com.mapbar.analyzelog.utils.FileUtils;

/**
 * 分区聚合器
 * 
 * @author lisf
 * 
 */
public class RegionMerger {

	private static Merge merger = null;

	static {
		RegionMerger.merger = new Merge();
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub
		int status = 0;
		if (args == null || args.length <= 0) {
			status = -1;
		} else if (args.length == 1) {
			status = RegionMerger.runMerge(args[0]);
		} else {
			RegionCollector.collectRegion(args[0]);
			if (!args[1].endsWith("stop-hbase.sh")) {
				status = 1;
			} else {
				StringBuilder path = new StringBuilder();
				if (!args[1].startsWith("/")) {
					path.append(FileUtils.getWorkspace());
				}
				Environment.executCmd(path.append(args[1]).toString());
				path.delete(0, path.length());
				status = RegionMerger.runMerge(args[0]);
				if (args.length >= 3 && args[2].endsWith("start-hbase.sh")) {
					if (!args[2].startsWith("/")) {
						path.append(FileUtils.getWorkspace());
					}
					Environment.executCmd(path.append(args[2]).toString());
					path.delete(0, path.length());
				}
				path = null;
			}
		}
		System.exit(status);
	}

	/**
	 * 执行合并指定表的分区
	 * 
	 * @param tableName
	 *            表的名称
	 * @return 合并结果，0表示成功，其他表示失败
	 * @throws IOException
	 */
	private static int runMerge(String tableName) throws IOException {
		String[] mergeArgs = new String[3];
		mergeArgs[0] = tableName;
		mergeArgs[1] = null;
		mergeArgs[2] = null;

		BufferedReader in = FileUtils.getIn(mergeArgs[0]);
		BufferedWriter out = FileUtils.getOut(mergeArgs[0], "out");

		StringBuilder result = new StringBuilder();
		int status = 0;
		while ((mergeArgs[1] = in.readLine()) != null) {
			if ((mergeArgs[2] = in.readLine()) == null) {
				break;
			}
			status = RegionMerger.merge(mergeArgs);
			result.append(mergeArgs[1]).append("\t").append(mergeArgs[2])
					.append("\t").append(status);
			out.write(result.toString());
			out.newLine();
			result.delete(0, result.length());
			mergeArgs[1] = null;
			mergeArgs[2] = null;
		}

		result = null;
		in.close();
		in = null;
		out.flush();
		out.close();
		out = null;

		return status;
	}

	/**
	 * 合并分区
	 * 
	 * @param args
	 *            合并参数
	 * @return 合并结果，0表示成功、其他表示失败
	 */
	private static int merge(String[] args) {
		int status = 0;
		try {
			status = ToolRunner.run(Config.getInstance(), RegionMerger.merger,
					args);
		} catch (Exception e) {
			status = -1;
		}
		return status;
	}

}
