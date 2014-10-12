<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
.ae{
 display:block;/*这个属性是必须的*/
 font-size:12px;
 line-height:18px;
 text-decoration:none;
 color:#333;
 font-family:Arial;
}
.notices{
 /*border:1px solid #aaa;*/
 width:698px;
 height:58px;
 padding:8px 2px 0px 20px;
 background-image:url(../../statics/images/top_scroll.jpg")
}
#div1{
 height:50px;
 overflow:hidden;
 width:600px;

}
</style>
<script type="text/javascript">
/* function AutoScroll(obj){
	$(obj).find("ul:first").animate({
	marginTop:"-1px"
	},1000,function(){
	$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
	});
	}
	$(document).ready(function(){
	setInterval('AutoScroll("div#mynotice")',1000)
	}); */
function out()
{
	$("form#reloginForm").submit();
}
</script>
<form name="reloginForm" id="reloginForm" action="<%=basePath%>relogin.jsp" method="post">
<input type="hidden" name="exit" value="yes">
<div id="b_top">
	<input type="hidden" id="module_username" value="${username }"/>
	<a href="/reports/" class="fl"><div style="margin-top: 7px; height: 20px; font-size: 16px; color: #FFFFFF; font-weight: bold;">Mapbar运营平台 Beta2.0</div></a> 
	<div class="fr" style="padding-top: 10px;">
		<a href="#"  style="color:white;font-size: 12px; font-weight: 100;"><c:if test="${username==null||username==''}">登录</c:if><c:if test="${username!=null&&username!=''}">欢迎您：${username}</c:if></a> | 
		<a href="<%=basePath%>changepwd.jsp" style="color:white;font-size: 12px; font-weight: 100;"><c:if test="${username!=null&&username!=''}">修改密码</c:if></a> | 
		<a href="javascript:out()" style="color:white;font-size: 12px; font-weight: 100;">退出</a>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>
	<c:if test="${app.id!=null&&method!='login'}">
	<div  class="notices hidden">
	<ul id="div1" class="ae">
	<c:forEach items="${notices}" var="item">
		<li><c:if test="${app.id!=null}">${item.note}</c:if></li>
	</c:forEach>
	</ul>
</div></c:if>
</div> 
</form>
<script language="javascript">
var box=document.getElementById("div1"),can=true;
	box.innerHTML+=box.innerHTML;
	box.onmouseover=function(){can=false};
	box.onmouseout=function(){can=true};
new function (){
 var stop=box.scrollTop%54==0&&!can;
 if(!stop)box.scrollTop==parseInt(box.scrollHeight/2)?box.scrollTop=0:box.scrollTop++;
	setTimeout(arguments.callee,box.scrollTop%54?10:10000);
};
</script>