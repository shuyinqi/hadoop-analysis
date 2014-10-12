<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.mapbar.analyzelog.report.utils.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
var vsubm2 = function(upbtn) {
	var par1 = $('#start_date').get(0)!=null?$('#start_date').get(0).value:"";
	var par2 = $('#end_date').get(0)!=null?$('#end_date').get(0).value:"";
	var type1= $('#queryid').get(0)!=null?$('#queryid').get(0).value:"";
	var type2 = $('#stats_type').get(0)!=null?$('#stats_type').get(0).value:"";
	var vtype = $('#app_version_selector').get(0)!=null?$('#app_version_selector').get(0).value:"";
	var version = $('#mySelectVersion').val();

	
	var temp_url2='';
	if($('#stats_type').get(0)!=null||$('#app_version_selector').get(0)!=null){
	if(type2!=undefined)
		temp_url2+="&type=" + type2;
	if(vtype!=undefined)
		temp_url2+="&version=" + vtype;
	}
	
	var temp_url='';
	if($('#queryid').get(0)!=null){
	    temp_url="&queryname=" + type1;
	
	var txt11=new RegExp("[\\',\\~,\\!,\\@,\\&,\\$,\\%,\\;,\\=,\"]");
	var re11 = new RegExp(txt11);
		if (re11.test(type1))
	    {
			alert("请输入合法字符！");return false;
	    }
	}
	temp_url=temp_url+temp_url2;
	if(upbtn!=undefined){
		temp_url=temp_url+"&"+upbtn;
		temp_url+="#pager";
	}
	/////////////////////////设置跳转页面路径/////////////////////////////////
	var url=trimUrl(window.location.href) + "?version="+version+"&fromdate="
			+ par1 + "&todate=" + par2+temp_url;
	window.location.href = url;
};
</script>
<div id='daily_stats_details' style="margin-bottom: 40px;">

	<div class="digg_pagination" style="float: right; margin-top: 10px;">
		<a href="#" onclick="vsubm2('page=1');" id="pager">首页</a>
		<c:if test="${page!=1}">
			<a class="next_page" rel="next" href="#"
				onclick="vsubm2('page=${page-1}');">上一页</a>
		</c:if>
		<c:forEach var="i" begin="${page}"
			end="${pagecount==10?1:fn:substring(pagecount/10,0,fn:indexOf(pagecount/10,'.'))+1}"
			step="1">
			<c:if
				test="${i<page+3 and (i<fn:substring(pagecount/10,0,fn:indexOf(pagecount/10,'.'))-2)}">
				<a rel="next" href="#" onclick="vsubm2('page=${i}');"
					<c:if test="${page==i}">style="color:#DD4B39;"</c:if>><c:out
						value="${i}" />
				</a>
			</c:if>
			<c:if test="${i==page+5}">
				<span class="gap">…</span>
			</c:if>
			<c:if
				test="${(i>=fn:substring(pagecount/10,0,fn:indexOf(pagecount/10,'.'))-2) and (i<=fn:substring(pagecount/10,0,fn:indexOf(pagecount/10,'.'))+1)}">
				<a rel="next" href="#" onclick="vsubm2('page=${i}');"
					<c:if test="${page==i}">style="color:#DD4B39;"</c:if>><c:out
						value="${i}" />
				</a>
			</c:if>
		</c:forEach>

		<c:if
			test="${page!=1&&page!=(fn:substring(pagecount/10,0,fn:indexOf(pagecount/10,'.'))+1)}">
			<a class="next_page" rel="next" href="#"
				onclick="vsubm2('page=${page+1}');">下一页</a>
		</c:if>
		<a href="#"
			onclick="vsubm2('page=${fn:substring(pagecount/10,0,fn:indexOf(pagecount/10,'.'))+1}');">尾页</a>
	</div>
	<div class="clear"></div>
</div>