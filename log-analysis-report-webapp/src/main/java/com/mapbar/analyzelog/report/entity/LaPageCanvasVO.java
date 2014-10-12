package com.mapbar.analyzelog.report.entity;
/***
 * 用户行为轨迹跟踪封装的vo
 * @（#）:LaPageCanvasVO.java 
 * @description:  
 * @author:  Administrator  2012-5-3 
 * @version: [SVN] 
 * @modify: 
 * @Copyright:  图吧
 */
public class LaPageCanvasVO {
    /***
     * 前一个名称
     */
	public String pre_name;
	/***
	 * 后一个名称
	 */
	public String next_name;
	/***
	 * 占比
	 */
	public float ratio;
	/***
	 * 访问数
	 */
	public int visits;
	/**
	 * 判断是叶子节点还是根节点
	 */
	public Boolean leaf;
	
	@Override
	public String toString() {
		return "LaPageCanvasVO [pre_name=" + pre_name + ", next_name="
				+ next_name + ", ratio=" + ratio + ", visits=" + visits + "]";
	}
	public String getPre_name() {
		return pre_name;
	}
	public void setPre_name(String pre_name) {
		this.pre_name = pre_name;
	}
	public String getNext_name() {
		return next_name;
	}
	public void setNext_name(String next_name) {
		this.next_name = next_name;
	}
	public float getRatio() {
		return ratio;
	}
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
}
