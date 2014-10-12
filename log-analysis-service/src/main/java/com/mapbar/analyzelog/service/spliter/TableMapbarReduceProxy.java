package com.mapbar.analyzelog.service.spliter;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.mapbar.analyzelog.service.Argument;
/**
 * 添加更改split方法
 * @（#）:TableMapbarReduceProxy.java 
 * @description:  
 * @author:  sunjy  2012-5-23 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class TableMapbarReduceProxy{
	/**
	 * hadoopmap任务整合
	 * @param table   hbase中表名称
	 * @param scan    hbase中查询区间
	 * @param mapper  mapperClass
	 * @param outputKeyClass  输出Key类型
	 * @param outputValueClass  输出value类型
	 * @param job   任务名称
	 * @param appID 产品id
	 * @param day   执行间隔
	 * @param split 切分规则
	 * @throws IOException
	 *
	 */
	@SuppressWarnings("rawtypes")
	public static void initTableMapperJobProxy(String table, Scan scan,
		          Class<? extends TableMapper> mapper,
		          Class<? extends WritableComparable> outputKeyClass,
		          Class<? extends Writable> outputValueClass, 
		          Job job,String appID,String day,int split) throws IOException{
		TableMapReduceUtil.initTableMapperJob(table, scan, mapper, outputKeyClass, outputValueClass,job);
		/**
		 * 设置job名称，区分appid
		 */
		String jobName = appID+"_"+job.getJobName()+(day==null?"":day);
		job.setJobName(jobName);
		
//		job.setInputFormatClass(TableInputFormatSpitting.class);
	  if(split!=0){
			/**
			 * 设置切割方式
			 */
			TableInputFormatSpitting tif = new TableInputFormatSpitting();
			tif.setDivisor(split);
			job.setInputFormatClass(tif.getClass());
		}
	}
	/***
	 * 
	 * @param paths   输出路径
	 * @param numReduceTasks reduce个数
	 * @param reduce   reduce类
	 * @param outputKey  输出key类型
	 * @param outputValue 输出value类型
	 * @param arguments   参数
	 * @param job   job
	 *
	 */
	public static void initReduceProxy(String paths,int numReduceTasks,Class< ? extends Reducer>  reduce,
			Class<?> outputKey,Class<?> outputValue,Argument arguments,Job job){
		
		job.setNumReduceTasks(numReduceTasks);
		job.setReducerClass(reduce);
		job.setOutputKeyClass(outputKey);
		job.setOutputValueClass(outputValue);
		FileOutputFormat.setOutputPath(job, new Path(paths));
	}

	public TableMapbarReduceProxy() {
	}
}
