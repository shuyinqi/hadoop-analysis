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
	  	<th  scope="col">日期</th>
		<th  scope="col">当日新增用户数</th>
		<th  scope="col">7天内回访用户数</th>
		<th  scope="col">7天内回访用户比例</th>
		<th  scope="col">14天内回访用户数</th>
		<th  scope="col">14天内回访用户比例</th>
	  </tr>
	 <c:forEach items="${exp_list}" var="item" varStatus="status">
				<tr>
													<td >${item.time}</td>
													<td >${item.addCount}</td>
													<td >${item.visitCount_7}</td>
													<td ><c:if
															test="${item.addCount eq 0}">0%</c:if> <c:if
															test="${item.addCount!=0}">
															<c:out
																value="${fn:substring((item.visitCount_7/item.addCount)*100,0,fn:indexOf((item.visitCount_7/item.addCount)*100,'.')+2)}" />%
							</c:if>
														</th>
														<td >${item.visitCount_14}</td>
														<td ><c:if
																test="${item.addCount==0}">0%</c:if> <c:if
																test="${item.addCount!=0}">
																<c:out
																	value="${fn:substring((item.visitCount_14/item.addCount)*100,0,fn:indexOf((item.visitCount_14/item.addCount)*100,'.')+2)}" />%
							</c:if>
															</th>
												</tr>
	</c:forEach>
	 </table>
  </body>
</html>




