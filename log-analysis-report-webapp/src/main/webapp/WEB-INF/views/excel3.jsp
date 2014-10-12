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
	  	<th scope="col">品牌/型号</th>
		<th scope="col">用户比例</th>
	  </tr>
	 <c:forEach items="${exp_list}" var="item" varStatus="status">
				<tr>
					<td >${item.device}</td>
					<td>${item.newcount}(
					<c:if test="${fn:indexOf((item.newcount/newsRatio)*100,'E')==-1}">
					<c:out value="${fn:substring((item.newcount/newsRatio)*100,0,fn:indexOf((item.newcount/newsRatio)*100,'.')+2)}" />
					</c:if>
					<c:if test="${fn:indexOf((item.newcount/newsRatio)*100,'E')!=-1}">
					0.0
					</c:if>
					%)</td>
			    </tr>
	</c:forEach>
	 </table>
  </body>
</html>




