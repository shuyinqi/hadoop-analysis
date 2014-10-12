package com.mapbar.analyzelog.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Environment {

	/**
	 * 执行cmd命令
	 * 
	 * @param cmd
	 *            cmd命令
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void executCmd(String cmd) throws IOException,
			InterruptedException {
		// String cmd = "find /home/webdocs/bmd -type d | wc -l";
		// String cmd = "ls  -l";
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
		pb.redirectErrorStream(true);
		Process shell = pb.start();
		BufferedReader shellIn = new BufferedReader(new InputStreamReader(shell
				.getInputStream(), "UTF-8"));
		shell.waitFor();
		String line = null;
		while ((line = shellIn.readLine()) != null) {
			System.out.println(line);
		}
		shellIn.close();

	}
}
