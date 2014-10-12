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
																<th >新用户</th>
																<th >启动用户</th>
	  </tr>
	 <c:forEach items="${exp_detail}" var="item2" varStatus="status">
				<tr>
														        <td>${item2.date}</td>
														        <td>${item2.news}</td>
														        <td>${item2.launchs}</td>
														      </tr>
	</c:forEach>
	 </table>
  </body>
</html>




