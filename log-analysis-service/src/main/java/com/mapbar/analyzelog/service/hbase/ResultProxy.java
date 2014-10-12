package com.mapbar.analyzelog.service.hbase;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.mapbar.analyzelog.core.SystemConstants;
import com.mapbar.analyzelog.core.utils.DateFormatUtils;

/**
 * Hadoop result proxy class.
 * 
 * @author 邓飞鸽
 */
public class ResultProxy {

	private Result result;
	private String defaultFamily;

	private ResultProxy(Result result){
		this.result = result;
	}

	private ResultProxy(Result result, String defaultFamily){
		this.result = result;
		this.defaultFamily = defaultFamily;
	}

	public static ResultProxy getResultProxy(Result result) {
		return new ResultProxy(result);
	}
	
	public static ResultProxy getResultProxy(Result result, String defaultFamily) {
		return new ResultProxy(result, defaultFamily);
	}

	public String getValueNotNull(String family, String qualifier){
		return getValue(family, qualifier, SystemConstants.UNKNOWN);
	}
	
	public String getValueNotNull(String qualifier){
		assertDefaultFamily();
		return getValue(this.defaultFamily, qualifier, SystemConstants.UNKNOWN);
	}

	public String getValue(String family, String qualifier, String defaultValue){
		String resultValue;
		byte[] value = result.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
		if (value == null || value.length <= 0) {
			resultValue = defaultValue;
		} else {
			resultValue = Bytes.toString(value);
			if (resultValue.equalsIgnoreCase("null")){
				resultValue = defaultValue;
			}
		}
		return resultValue;
	}

	public String getValue(String family, String qualifier){
		return getValue(family, qualifier, SystemConstants.UNKNOWN);
	}

	public String getValue(String qualifier){
		assertDefaultFamily();
		return getValue(this.defaultFamily, qualifier, SystemConstants.UNKNOWN);
	}

	public String getStringValue(String qualifier, String defaultV){
		assertDefaultFamily();
		return getValue(this.defaultFamily, qualifier, defaultV);
	}

	public long getLongValue(String family, String qualifier){
		return NumberUtils.toLong(getValue(family, qualifier, "0"));
	}

	public long getLongValue(String qualifier){
		assertDefaultFamily();
		return NumberUtils.toLong(getValue(this.defaultFamily, qualifier, "0"));
	}

	public int getIntValue(String family, String qualifier, int defaultVal){
		return NumberUtils.toInt((getValue(family, qualifier)), defaultVal);
	}
	
	public int getIntValue(String qualifier, int defaultVal) {
		assertDefaultFamily();
		return NumberUtils.toInt((getValue(this.defaultFamily, qualifier)), defaultVal);
	}

	public String getFormatDateByTimestamp(String timeQualifier) {
		return DateFormatUtils.timestampToDateFromat(getLongValue(timeQualifier));
	}
	
	public String getFormatDatetimeByTimestamp(String timeQualifier) {
		return DateFormatUtils.timestampToDatetimeFromat(getLongValue(timeQualifier));
	}

	public String getFormatDateByTimestamp(String timeQualifier, String format) {
		return DateFormatUtils.formatDate(getLongValue(timeQualifier), format);
	}

	private void assertDefaultFamily() {
		if (StringUtils.isEmpty(this.defaultFamily)){
			throw new NullPointerException("Non default family name!");
		}
	}
}
