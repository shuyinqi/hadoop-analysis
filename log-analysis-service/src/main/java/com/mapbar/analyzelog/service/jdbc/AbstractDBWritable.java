package com.mapbar.analyzelog.service.jdbc;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.WritableComparable;

public abstract class AbstractDBWritable implements WritableComparable<AbstractDBWritable>{

	public void readFields(DataInput in) throws IOException {
		// No Read, wirte only
	}
	
	public void write(DataOutput out) throws IOException {
	}
	
	@Override
	public int compareTo(AbstractDBWritable o) {
		return 0;
	}


	public void readFields(ResultSet resultSet) throws SQLException {
		// No Read, wirte only
	}
}
