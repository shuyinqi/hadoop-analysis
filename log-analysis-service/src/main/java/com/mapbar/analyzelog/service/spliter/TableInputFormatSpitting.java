package com.mapbar.analyzelog.service.spliter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableSplit;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Pair;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;

/**
 * 覆盖hbase的splitting,defalut=20
 * @（#）:TableInputFormatSpitting.java 
 * @description:  
 * @author:  sunjy  2012-5-22 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class TableInputFormatSpitting extends TableInputFormat {
	public int getDivisor() {
		return divisor;
	}
	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}
	private  int divisor =30;
	@Override
	protected boolean includeRegionInSplit(byte[] startKey, byte[] endKey) {
		return true;
	}

	@Override
	public List<InputSplit> getSplits(JobContext context) throws IOException {
		final Log LOG = LogFactory.getLog(TableInputFormatSpitting.class);
		if (getHTable() == null) {
		    throw new IOException("No table was provided.");
		}
	    Pair<byte[][], byte[][]> keys = getHTable().getStartEndKeys();
	    if (keys == null || keys.getFirst() == null ||
	        keys.getFirst().length == 0) {
	      throw new IOException("Expecting at least one region.");
	    }
	    int count = 0;
	    int step = (int) Math.ceil(keys.getFirst().length/Double.valueOf(getDivisor()));
	    List<InputSplit> splits = new ArrayList<InputSplit>(keys.getFirst().length);
	    	
	    for (int i = 0; i < step; i++) {
	    	if ( !includeRegionInSplit(keys.getFirst()[i], keys.getSecond()[i])) {
	        continue;
	      }
	    	int startKey,endKey;
	    	if(i==0){
	    		 startKey = i;
	    		 endKey = keys.getFirst().length>getDivisor()?getDivisor():keys.getFirst().length-1;
	    	}else{
	    		 startKey = getDivisor()*i;
	    		 endKey = keys.getFirst().length>(getDivisor()*i+getDivisor())?(getDivisor()*i+getDivisor()):keys.getFirst().length-1;
	    	}
	    	
	      String regionLocation = getHTable().getRegionLocation(keys.getFirst()[startKey]).getServerAddress().getHostname();
	      byte[] startRow = getScan().getStartRow();
	      byte[] stopRow = getScan().getStopRow();
	      // determine if the given start an stop key fall into the region
	      if ((startRow.length == 0 || keys.getSecond()[endKey].length == 0 ||Bytes.compareTo(startRow, keys.getSecond()[endKey]) < 0) &&(stopRow.length == 0 ||Bytes.compareTo(stopRow, keys.getFirst()[startKey]) > 0)) {
	        byte[] splitStart = startRow.length == 0 ||Bytes.compareTo(keys.getFirst()[startKey], startRow) >= 0 ? keys.getFirst()[startKey] : startRow;
	        byte[] splitStop = (stopRow.length == 0 ||Bytes.compareTo(keys.getSecond()[endKey], stopRow) <= 0) &&keys.getSecond()[endKey].length > 0 ? keys.getSecond()[endKey] : stopRow;
	        InputSplit split = new TableSplit(getHTable().getTableName(),splitStart, splitStop, regionLocation);
	        splits.add(split);
	        if (LOG.isDebugEnabled())
	          LOG.debug("getSplits: split -> " + (count++) + " -> " + split);
	      }
	    }
	    return splits;
	}
	
	
		

}