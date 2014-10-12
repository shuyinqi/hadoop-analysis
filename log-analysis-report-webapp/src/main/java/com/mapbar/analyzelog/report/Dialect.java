package com.mapbar.analyzelog.report;

/**
 * 分页SQL语言接口。
 * 
 * @author <a href="mailto:dengfg@mapbar.com">邓飞鸽</a>
 */
public interface Dialect {
	public boolean supportsLimitOffset();

	public boolean supportsLimit();

	public String getLimitString(String sql, int offset, int limit);

	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder);
}
