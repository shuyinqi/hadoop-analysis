package com.mapbar.analyzelog.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

	/**
	 * 获取当前目录的绝对路径
	 * 
	 * @return 当前目录的绝对路径
	 */
	public static String getWorkspace() {
		File dir = new File("RegionDir");
		if (!dir.exists()) {
			dir.mkdir();
		}
		return dir.getAbsolutePath().replaceFirst("RegionDir$", "");
	}

	/**
	 * 根据表名生成存放该表分区Key的文件名
	 * 
	 * @param tableName
	 *            表的名称
	 * @return 存放表分区Key的文件名
	 */
	public static String getFileName(String tableName) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		StringBuilder fileName = new StringBuilder(tableName).append("_")
				.append(dateFormat.format(new Date())).append(".txt");
		dateFormat = null;
		return fileName.toString();
	}

	/**
	 * 根据表名和后缀名生成存放该表分区Key的文件名
	 * 
	 * @param tableName
	 *            表名
	 * @param suffix
	 *            后缀名
	 * @return 存放表分区Key的文件名
	 */
	public static String getFileName(String tableName, String suffix) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		StringBuilder fileName = new StringBuilder(tableName).append("_")
				.append(dateFormat.format(new Date())).append("_").append(
						suffix).append(".txt");
		dateFormat = null;
		return fileName.toString();
	}

	/**
	 * 根据表名生成存放该表分区Key的输出流
	 * 
	 * @param tableName
	 *            表的名称
	 * @return 存放表分区Key的输出流
	 * @throws IOException
	 */
	public static BufferedWriter getOut(String tableName) throws IOException {
		File dir = new File("RegionDir");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(dir, FileUtils.getFileName(tableName));
		if (file.exists()) {
			file.delete();
		}
		dir = null;

		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		file = null;

		return out;
	}

	/**
	 * 根据表名和后缀名生成读取存放该表分区Key文件的输入流
	 * 
	 * @param tableName
	 *            表名
	 * @param suffix
	 *            后缀名
	 * @return 读取存放该表分区Key文件的输入流
	 * @throws IOException
	 */
	public static BufferedWriter getOut(String tableName, String suffix)
			throws IOException {
		File dir = new File("RegionDir");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(dir, FileUtils.getFileName(tableName, suffix));
		if (file.exists()) {
			file.delete();
		}
		dir = null;

		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		file = null;

		return out;
	}

	/**
	 * 根据表名生成读取存放该表分区Key文件的输入流
	 * 
	 * @param tableName
	 *            表的名称
	 * @return 读取存放表分区Key文件的输入流
	 * @throws IOException
	 */
	public static BufferedReader getIn(String tableName) throws IOException {
		File dir = new File("RegionDir");
		if (!dir.exists()) {
			return null;
		}
		File file = new File(dir, FileUtils.getFileName(tableName));
		if (!file.exists()) {
			return null;
		}
		dir = null;

		BufferedReader in = new BufferedReader(new FileReader(file));
		file = null;

		return in;
	}
}
