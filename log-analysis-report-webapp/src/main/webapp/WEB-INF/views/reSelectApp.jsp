<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="commons/meta.jsp"%>
<%
	session.invalidate();
	String exit = request.getParameter("exit");
	if(exit != null && exit.equals("yes"))
	{
%>
<script language = javascript> 
	window.top.location.href= '<%=basePath%>index.jsp';
</script>
<%		
	}
	else
	{
%>
<script language = javascript> 
	alert('您没有访问此app的权限，请重新选择app,或与管理员联系!'); 
	window.top.location.href= '<%=basePath%>apps';
</script>
<%
	}
%>
