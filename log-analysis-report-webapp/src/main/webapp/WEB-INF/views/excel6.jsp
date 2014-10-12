<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String fileName = "log";
	response.reset();
	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
	response.addHeader("Content-Disposition", "attachment; filename=\""
			+ fileName + ".xls\"");
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
%>
<html>
  <head>
    <meta http-equiv="content-type"
	content="application/vnd.ms-excel;charset=UTF-8">
  </head>
  
  <body>
    <table  width="100%" border="1">
	  <tr align="center">
				<th  >日期</th>
				<th  >渠道</th>
				<th >新增用户</th>
				<th >活跃用户</th>
	  </tr>
	 <c:forEach items="${exp_list}" var="item" varStatus="status">
				<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
				<td>${item.date}</td>
				<td>${item.version}</td>
				<td>${item.news}</td>
				<td>${item.launchs}</td>
				</tr>
	</c:forEach>
	 </table>
  </body>
</html>




