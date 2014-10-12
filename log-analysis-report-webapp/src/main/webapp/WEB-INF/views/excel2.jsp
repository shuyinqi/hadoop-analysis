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
	  	<th  scope="col">Activity</th>
									<th  scope="col">访问用户数</th>
									<th  id="daily_th" scope="col">页面访问次数(%)</th>
									<th  scope="col">平均停留时间</th>
									<th  scope="col">停留时间占比</th>
	  </tr>
	 <c:forEach items="${exp_list}" var="item" varStatus="status">
				<tr>
					<td >${item.name}</td>
										<td >${item.user_count}</td>
										<td >${item.count}(<fmt:formatNumber type="number" pattern="###.##"  value="${(item.count/totalCount)*100}" />%)</td>
										<td >
										<c:set value="${fn:substring((item.time/item.count/1000/60),0,fn:indexOf((item.time/item.count/1000/60),'.'))}" var="fen"/>
										<c:if test="${fen!=0}"><c:out value="${fn:substring((item.time/item.count/1000/60),0,fn:indexOf((item.time/item.count/1000/60),'.'))}"/>分</c:if><fmt:formatNumber type="number" pattern="###"  value="${(item.time/item.count/1000)%60}" />秒</td>
										<td >
												<fmt:formatNumber type="number" pattern="###.##"  value="${(item.time/totalTime)*100}" />%
										</td>
				</tr>
	</c:forEach>
	 </table>
  </body>
</html>




