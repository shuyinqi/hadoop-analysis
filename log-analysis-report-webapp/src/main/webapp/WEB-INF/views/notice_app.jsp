<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>

<title>最新 | ${app.name}</title>
</head>
<body>
	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>

		<div id="b_center">
			<%@ include file="/WEB-INF/views/body-leftmenu.jsp"%>

			<div class="fr b_rightconten">

				<div class="fl b_right_menu">
					<ul>
						<li><a id="reports_tab" class="click current " href="#">统计分析</a>
						</li>
						<li><a href="#" id="module_tab">组件</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear">&nbsp;</div>
				<div class="b_right_public">
					<div class="conright">
						<div id="main">
							<div class="statsTableHeader">
								<strong>最新公告</strong><span class="question fr"></span>
								<div class="clear"></div>
							</div>

							<div class="ccon">
								<div>
								<table class="new_silver_table" width="100%" border="0"
									cellspacing="0" cellpadding="">
									<thead>
										<tr>
											<th>公告内容</th>
											<th scope="col" style="width: 15%">创建日期</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${notices}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'} ${status.first?' today':''}">
												<td>${item.note}</td>
												<td>${item.date}</td>
											</tr>
										</c:forEach>
									</tbody>
									</table>
									</div>
									<div style="margin-bottom: 30px;"></div>
									</div>
									</div>
									<div class="bs_bottom">
										<div class="bs_bl"></div>
										<div class="bs_br"></div>
									</div>
									</div>
									<!-- end-->
									</div>
									</div>
									<div class="bs_bottom">
										<div class="bs_bl"></div>
										<div class="bs_br"></div>
									</div>
									<div class="clear"></div>
									</div>
</div>
									<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>