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
	  	<th scope="col">分辨率</th>
											<th scope="col">用户比例</th>
	  </tr>
	 <c:forEach items="${exp_list}" var="item" varStatus="status">
				<tr>
					<td>${item.device}</td>
												<td><c:out
														value="${fn:substring((item.launchcount/launchRatio)*100,0,fn:indexOf((item.launchcount/launchRatio)*100,'.')+3)}" />%</td>
											</tr>
	</c:forEach>
	 </table>
  </body>
</html>




