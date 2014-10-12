<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.mapbar.analyzelog.report.utils.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 
<div class="b_right_public">
	<div class="conright">
		<div id="main">
			<div class="bbox_con">
			 -->
<div class="time_select_wrap">
	<div class="statsTableHeader">
		<strong>选择时段</strong>
		<div class="selbox">
			<input type="date" id="start_date" name="start_date" value="${param.fromdate }"
				class="datainp first_date date"> <span>到</span> <input
				type="date" id="end_date" name="end_date" value="${param.todate }"
				class="datainp last_date date"> <input type="hidden"
				name="ad_man" id="ad_man"> <input type="hidden" name="other"
				id="other"> <input type="button" onclick="subm(this)"
				value="&nbsp;更新&nbsp;" class="upbtn">
		</div>
		<div style="color: #444" class="mnum">
			<a href="javascript:submLast('${lastWeek1}')" id="lw1">过去一周</a> | <a id="lm1" href="javascript:submLast('${lastMonth1}')">过去一月</a> | <a id="lm3" href="javascript:submLast('${lastMonth3}')">过去三月</a> | <a class="" href="javascript:submLast('${allDay}')" id="ad">全部</a>
		</div>
		<div class="clear"></div>
	</div>
	
	<c:if test="${vdisdate!=1}">
	<div class="statsTableHeader">
		<strong>最近5天时段选择</strong>
		<div style="color: #444" class="mnum">
			<a href="javascript:submLast('${lastDay1}')" id="ld1"><%=DateUtil.getDate().toString()%></a> | <a id="ld2" href="javascript:submLast('${lastDay2}')"><%=DateUtil.getStepDay(-1).toString()%></a> | <a id="ld3" href="javascript:submLast('${lastDay3}')"><%=DateUtil.getStepDay(-2).toString()%></a> | <a class="" href="javascript:submLast('${lastDay4}')" id="ld4"><%=DateUtil.getStepDay(-3).toString()%></a> | <a class="" href="javascript:submLast('${lastDay5}')" id="ld5"><%=DateUtil.getStepDay(-4).toString()%></a>
		</div>
		<div class="clear"></div>
	</div>
	</c:if>
</div>

<script type="text/javascript">
jQuery(function(){
	var url = window.location.href;
	if(url.indexOf("${lastWeek1}")>0)jQuery("#lw1").attr("class","current");
	else if(url.indexOf("${lastMonth1}")>0)jQuery("#lm1").attr("class","current");
	else if(url.indexOf("${lastMonth3}")>0)jQuery("#lm3").attr("class","current");
	else if(url.indexOf("${allDay}")>0)jQuery("#ad").attr("class","current");
	else if(url.indexOf("${lastDay1}")>0)jQuery("#ld1").attr("class","current");
	else if(url.indexOf("${lastDay2}")>0)jQuery("#ld2").attr("class","current");
	else if(url.indexOf("${lastDay3}")>0)jQuery("#ld3").attr("class","current");
	else if(url.indexOf("${lastDay4}")>0)jQuery("#ld4").attr("class","current");
	else if(url.indexOf("${lastDay5}")>0)jQuery("#ld5").attr("class","current");
});
var submLast = function(params) {
	if(params){
		window.location.href = trimUrl(window.location.href) + "?" + params;	
	} else {
		var pos = window.location.href.indexOf("?", 0);
		window.location.href = window.location.href.substring(0, pos);
	}
};
function trimUrl(url) {
	if (url != null && url.length > 1) {
		var temp = url[url.length - 1] == '#' ? url.substring(0, url.length - 1) : url;
		var pos = temp.indexOf("?", 0);
		if (pos != -1)temp = url.substring(0, pos);
		return temp[temp.length - 1] == '/' ? temp.substring(0, temp.length - 1) : temp;
	} else
		return url;
}

var subm = function(upbtn) {
	var par1 = jQuery(upbtn).siblings('.first_date').first().val();
	var par2 = jQuery(upbtn).siblings('.last_date').first().val();
	var str="";
	if($("#mySelectChannelType").val()!=null){
		str+="&channel_type="+$("#mySelectChannelType").val();
	}
	if($("#mySelectVersion").val()!=null){
		str+="&version="+$("#mySelectVersion").val();
		}
	if($("#mySelectChannelName").val()!=null){
		str+="&channel_name="+$("#mySelectChannelName").val();
	}
	
	window.location.href = trimUrl(window.location.href) + "?fromdate="
			+ par1 + "&todate=" + par2+str;
};
	function trimUrl(url) {
		if (url != null && url.length > 1) {
			var temp = url[url.length - 1] == '#' ? url.substring(0,
					url.length - 1) : url;
			var pos = temp.indexOf("?", 0);
			if (pos != -1)
				temp = url.substring(0, pos);
			return temp[temp.length - 1] == '/' ? temp.substring(0,
					temp.length - 1) : temp;
		} else
			return url;
	}
</script>