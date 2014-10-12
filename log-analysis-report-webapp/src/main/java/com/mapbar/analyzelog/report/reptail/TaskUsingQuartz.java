package com.mapbar.analyzelog.report.reptail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mapbar.analyzelog.report.db.JdbcFunction;

public class TaskUsingQuartz extends QuartzJobBean  {

 public void executeInternal(JobExecutionContext context)
     throws JobExecutionException {
	 Grab app_1000 = new App1000Grab();
	 app_1000.grabStart();
	 Grab app_1005 = new App1005Grab();
	 app_1005.grabStart();
   System.out.println("使用Quartz 认为调度: Hello!!");
 }
 public static void main(String[] args) {
	 TaskUsingQuartz tq = new TaskUsingQuartz();
	 try {
		tq.executeInternal(null);
	} catch (JobExecutionException e) {
		e.printStackTrace();
	}
}
}
