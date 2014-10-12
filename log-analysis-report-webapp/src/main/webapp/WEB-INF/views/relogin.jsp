<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
	alert('登录超时，请重新登录系统!'); 
	window.top.location.href= '<%=basePath%>index.jsp';
</script>
<%
	}
%>
