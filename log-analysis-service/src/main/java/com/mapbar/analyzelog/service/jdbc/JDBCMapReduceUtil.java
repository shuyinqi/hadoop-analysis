package com.mapbar.analyzelog.service.jdbc;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;


/**
 * Utility for {@link Reducer}.
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public class JDBCMapReduceUtil {

	/**
	 * Use this before submitting a JdbcMap job. It will appropriately set up the job.
	 * @param table The table name  write to.
	 * @param valueFields 
	 * @param reducerClass The reudcer class to use.
	 * @param outputKeyClass The class of the output key.
	 * @param outputValueClass The class of the output value.
	 * @param job The current job to adjust.
	 * 
	 * @throws IOException When setting up the details fails.
	 */
	public static void initTableReducerJob(String table, String[] keyFields,
			String[] valueFeilds, Class<? extends DBCounterReducer<?, ?>> reducerClass, Job job)
			throws IOException {
		if (reducerClass != null){
			job.setReducerClass(reducerClass);
		}
		job.setOutputKeyClass(KeyDBWritable.class);
		DBOutputFormat.setOutput(job, table, keyFields, valueFeilds);
	}
}
