package com.mapbar.analyzelog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/***
 * 启动项目
 * @（#）:JobBootstrap.java 
 * @description:  
 * @author:  sunjy  2012-4-12 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class JobBootstrap {

	protected static Logger logger = LoggerFactory.getLogger(JobBootstrap.class);

	public static void main(String[] args) {
		JobBootstrap taskBootstrap = new JobBootstrap(args);
		try {
			taskBootstrap.runJob();
		} catch (InterruptedException e) {
			taskBootstrap.printErrorAndExit("Interrupted Task! " + e.getMessage());
		}
	}

	private Argument arguments;
    
	public JobBootstrap(String[] args){
		initArguments(args);
	}
   /***
    * 构造方法，优先执行。判断是否传入参数，没有传入参数就停止
    * @param args
    *
    */
	private void initArguments(String[] args) {
		arguments = new Argument(args);
		arguments.assertRequiredArguments();
	}
    /***
     * 调用启动进程,启动job
     * @throws InterruptedException
     *
     */
	public void runJob() throws InterruptedException {
		//long millisecond = runInterval * 60 * 1000;
		JobTask runTask = newRunTaskInstance();
		runTask.start(arguments);
	}

	protected JobTask newRunTaskInstance() {
		Class<?> runTaskClass = null;
		try {
			runTaskClass = getRunTaskClass(arguments.getJobName());
		} catch (ClassNotFoundException e) {
			printErrorAndExit("Find run task class fails: " + e.getMessage());
		}
		JobTask runTask = null;
		try {
			runTask = (JobTask) runTaskClass.newInstance();
		} catch (Exception e) {
			printErrorAndExit("Create run task instance fails: " + e.getMessage());
		}
		return runTask;
	}

	private void printErrorAndExit(String msg) {
		System.err.println(msg);
		logger.error(msg);
		System.exit(1);
	}

	protected Class<?> getRunTaskClass(String taskName) throws ClassNotFoundException {
		String className = arguments.getPackage() + "." + taskName + "MapReducer";
		Class<?> runTaskClass = Class.forName(className);
		return runTaskClass;
	}
}
