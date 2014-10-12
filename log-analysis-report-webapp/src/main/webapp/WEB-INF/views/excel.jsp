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
	  	<th scope="col" class="new_silver_first_n">日期</th>
		<th scope="col">新用户</th>
		<th scope="col">累计用户</th>
		<th scope="col">启动用户</th>
		<th scope="col">启动次数</th>
		<th scope="col" style="width: 120px;">平均使用时长</th>
	  </tr>
	 <c:forEach items="${exp_list}" var="item" varStatus="status">
				<tr>
					<td>${item.date}</td>
					<td>${item.new_imei_size}</td>
					<td>${item.imei_size}</td>
					<td>${item.visit_imei_size}</td>
					<td>${item.visit_size}</td>
					<td><c:set value="${item.average_duration/60.0}" var="ad"/>${fn:substring(ad,0,fn:indexOf(ad,'.'))}分${item.average_duration%60}秒</td>
				</tr>
	</c:forEach>
	 </table>
  </body>
</html>




