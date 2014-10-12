package com.mapbar.analyzelog.service.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
/***
 * 用户map的key统一处理
 * @description:  
 * @author:  Administrator  2012-5-7 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class TextPair implements WritableComparable<TextPair>  {

	private Text first;
	private Text second;
	private Text three;
	private Text four;
	
	public TextPair(){
		set(new Text(),new Text(),new Text(),new Text());
	}
	public TextPair(String first){
		set(new Text(first),new Text(),new Text(),new Text());
	}
	public TextPair(String first,String second){
		set(new Text(first),new Text(second),new Text(),new Text());
	}
	public TextPair(String first,String second,String three){
		set(new Text(first),new Text(second),new Text(three),new Text());
	}
	public TextPair(String first,String second,String three,String four){
		set(new Text(first),new Text(second),new Text(three),new Text(four));
	}
	
	public TextPair(Text first, Text second, Text three,Text four) {
		set(first,second,three,four);
	}
	public void set(Text first,Text second,Text three,Text four){
		this.first = first;
		this.second = second;
		this.three = three;
		this.four=four;
	}
	public Text getFirst(){
		return first;
	}
	public Text getSecond(){
		return second;
	}
	public Text getThree(){
		return three;
	}
	public Text getFour(){
		return four;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		first.readFields(in);
		second.readFields(in);
		three.readFields(in);
		four.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		first.write(out);
		second.write(out);
		three.write(out);
		four.write(out);
	}

	@Override
	public int compareTo(TextPair tp) {
		int a = first.compareTo(tp.first);
		if( a !=0 ){
			return a;
		}
		int b = second.compareTo(tp.second);
		if( b!=0 ){
			return b;
		}
		int c = three.compareTo(tp.three);
		if(c!=0){
			return c;
		}
		return four.compareTo(tp.four);
	}
}
