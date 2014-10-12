package com.mapbar.analyzelog.service.jdbc;

import org.apache.hadoop.mapreduce.Reducer;

public abstract class DBCounterReducer<KEYIN, VALUEIN> extends Reducer<KEYIN, VALUEIN, KeyDBWritable, ValueDBWritable> {

}
