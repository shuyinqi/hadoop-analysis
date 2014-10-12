package com.mapbar.analyzelog.core;

import java.util.List;
import java.util.Set;

import org.apache.hadoop.hbase.client.ResultScanner;

import com.mapbar.analyzelog.core.SystemConstants.LogType;
import com.mapbar.analyzelog.core.entities.Equipment;
import com.mapbar.analyzelog.core.entities.LogError;
import com.mapbar.analyzelog.core.entities.LogEvent;
import com.mapbar.analyzelog.core.entities.LogLaunch;
import com.mapbar.analyzelog.core.entities.LogTerminate;

/**
 * 日志数据存储对象，提供针对日志数据的管理操作支持.<br>
 * 由于所有为日志数据，所以不提供数据删除的操作。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public interface LogStorage {

	/**
	 * 添加用户及所对应的设备信息，如果用户存在则替换 equipment 数据。
	 * 
	 * @param userID 用户 ID
	 * @param equipment 设置信息
	 */
	public void putUser(String userID, Equipment equipment);

	/**
	 * 批量添加用户启动日志，如果用户不存在则增加新用户。
	 * 
	 * @param userID 用户 ID
	 * @param logLuanchs 用户启动日志列表
	 */
	public void putLuanchs(String userID, List<LogLaunch> logLuanchs);

	/**
	 * 批量添加用户结束日志，如果用户不存在则增加新用户。
	 * 
	 * @param userID 用户 ID
	 * @param logTerminates 用户结束日志列表
	 */
	public void putTerminates(String userID, List<LogTerminate> logTerminates);

	/**
	 * 批量添加用户事件日志，如果用户不存在则增加新用户。
	 * 
	 * @param userID 用户ID
	 * @param logEvents 用户事件访问日志列表
	 */
	public void putEvents(String userID, List<LogEvent> logEvents);

	/**
	 * 批量添加用户的错误日志信息。如果用户不存在则增加新用户。
	 * 
	 * @param userID 用户 ID
	 * @param logErrors 日志错误列表
	 */
	public void putErrors(String userID, List<LogError> logErrors);

	/**
	 * 获取当前存储库的ID。
	 * 
	 * @return the storage id
	 */
	public String getID();
    /**
     * 获取一段时间内RowKey的集合
     * @param startTime
     * @param endTime
     * @return
     *
     */
	public Set<String> getRowKeyForTimeRange(Long startTime,Long endTime); 
	/***
	 * 
	 * @param startTime  
	 * @param endTime
	 * @return
	 *
	 */
	public ResultScanner getReslutForTimeRange(Long startTime,Long endTime) ;
	/***
	 * 删除一段时间内的数据
	 * @param startTime
	 * @param endTime
	 *
	 */
	public void deleteRowKeyForTimeRange(LogType tableName,Long startTime,Long endTime) ;
	/***
	 * 判断rowkey是否存在
	 * @param rowKey
	 * @return
	 *
	 */
	public Boolean rowKeyIsExist(String rowKey);

    /***
     * 获得过滤之后指定列的集合
     * @param startTime
     * @param endTime
     * @param arr    过滤条件 例如：条件：查询 course列族中art列值为97的english成绩："course,art,97,english"  
     * @return
     *
     */
	List<String> getColumnsForfilter(Long startTime, Long endTime, String arr);	
	/**
	 * 根据用户id获取用户配置信息
	 * @param userID
	 * @return com.mapbar.analyzelog.core.entities.Equipment
	 */
	public Equipment getEquipment(String userID);

	
	
	
}
