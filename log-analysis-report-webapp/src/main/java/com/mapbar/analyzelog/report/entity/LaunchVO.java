package com.mapbar.analyzelog.report.entity;

import java.util.Date;



public class LaunchVO {
	private Date date;   //省份
	private int c1_2;      //频率1-2次的用户数
	private int c2_2;
	private int c3_3;
	private int c4_4;
	private int c5_5;   //频率3-5次的用户数
	private int c6_9;      //频率6-9次的用户数
	private int c10_19;   //频率10-19次的用户数
	private int c20_49;      //频率20-49次的用户数
	private int c50_;   //频率50次以上的用户数
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getC1_2() {
		return c1_2;
	}
	public void setC1_2(int c1_2) {
		this.c1_2 = c1_2;
	}
	
	public final int getC2_2() {
		return c2_2;
	}
	public final void setC2_2(int c2_2) {
		this.c2_2 = c2_2;
	}
	public final int getC3_3() {
		return c3_3;
	}
	public final void setC3_3(int c3_3) {
		this.c3_3 = c3_3;
	}
	public final int getC4_4() {
		return c4_4;
	}
	public final void setC4_4(int c4_4) {
		this.c4_4 = c4_4;
	}
	public final int getC5_5() {
		return c5_5;
	}
	public final void setC5_5(int c5_5) {
		this.c5_5 = c5_5;
	}
	public int getC6_9() {
		return c6_9;
	}
	public void setC6_9(int c6_9) {
		this.c6_9 = c6_9;
	}
	public int getC10_19() {
		return c10_19;
	}
	public void setC10_19(int c10_19) {
		this.c10_19 = c10_19;
	}
	public int getC20_49() {
		return c20_49;
	}
	public void setC20_49(int c20_49) {
		this.c20_49 = c20_49;
	}
	public int getC50_() {
		return c50_;
	}
	public void setC50_(int c50_) {
		this.c50_ = c50_;
	}
	
}
