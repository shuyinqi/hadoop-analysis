package com.mapbar.analyzelog.report;

import org.apache.ibatis.session.RowBounds;

public class PageBounds extends RowBounds{

	public PageBounds(int pageNo, int pageSize){
		super((pageSize * pageNo) - pageSize, pageSize);
	}
}
