<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>

<title>访问页面统计 | ${app.name}</title>
</head>
<body>
	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>
		<div id="b_center">
			<%@ include file="/WEB-INF/views/body-leftmenu.jsp"%>
			<div class="fr b_rightconten">
				<div class="fl b_right_menu">
					<ul>
						<li><a href="#" class="click current " id="reports_tab">统计分析</a>
						</li>
						<li><a href="#" id="module_tab">组件</a></li>
					</ul>
					<div class="clear"></div>
				</div>
              <!-- flow chart start-->
<!--   <div id="flowchart" class="">
                <canvas id ="backline" width="600" height="600"></canvas>
                <div class="line"> <div id="flowchart_top" class="rec1 rec has_child mainline" level = 0 index = 0 title="分支"><div class="content">分支</div><span title="设为第一级"></span></div></div>
</div> -->
<!-- flow chart end -->
               
				<div class="clear">&nbsp;</div>
				<div class="b_right_public">
					<!--launch begin-->
<%@ include file="commons/date-form.jsp"%>
					<div class="statsTableHeader">
						<div style="float: left; text-align: left;">页面访问详情</div>
						<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list=true')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list=true')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20"/>
			</a>
		</div>
		<div class="path_choose_version">
							 选择应用程序版本: &nbsp; <select name="app_version"
								id="app_version_selector"
								onchange="vsubm(this)">
								<option value=''>全部</option>
								<c:forEach items="${clist}" var="items" varStatus="status">
									<c:if test="${items.value==version}">
										<option value='${items.value}' selected="selected">
											${items.value}</option>
									</c:if>
									<c:if test="${items.value!=version}">
										<option value='${items.value}'>${items.value}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</div>
					<script type="text/javascript">

</script>
					<div style="padding-bottom: 10px;" class="bitemcon" id="ac0">
						<div id="active_users_total_numbers" class="hidden">87990</div>
						<table cellspacing="0" cellpadding="0" border="0"
							class="datatab long new_silver_table"
							id="bartable_daily_active_users_devices">
							<thead>
								<tr>
									<th class="new_silver_first_n" scope="col">Activity</th>
									<th class="new_silver_first_n" scope="col">访问用户数</th>
									<th class="new_silver_first_n" id="daily_th" scope="col">页面访问次数(%)</th>
									<th class="new_silver_first_n" scope="col">平均停留时间</th>
									<th class="new_silver_first_n" scope="col">停留时间占比</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${stats}" var="item" varStatus="status">
									<tr
										class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
										<td class="new_silver_first_n">${item.name}</td>
										<td class="new_silver_first_n">${item.user_count}</td>
										<td class="new_silver_first_n">${item.count}(<fmt:formatNumber type="number" pattern="###.##"  value="${(item.count/totalCount)*100}" />%)</td>
										<td class="new_silver_first_n">
										<c:set value="${fn:substring((item.time/item.count/1000/60),0,fn:indexOf((item.time/item.count/1000/60),'.'))}" var="fen"/>
										<c:if test="${fen!=0}"><c:out value="${fn:substring((item.time/item.count/1000/60),0,fn:indexOf((item.time/item.count/1000/60),'.'))}"/>分</c:if><fmt:formatNumber type="number" pattern="###"  value="${(item.time/item.count/1000)%60}" />秒</td>
										<td class="new_silver_first_n">
												<fmt:formatNumber type="number" pattern="###.##"  value="${(item.time/totalTime)*100}" />%
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="hidden">
<%@ include file="commons/pager.jsp"%></div>
					<div style="margin-bottom: 30px;"></div>
				</div>
			</div>
			<div class="bs_bottom">
				<div class="bs_bl"></div>
				<div class="bs_br"></div>
			</div>
		</div>
		<!--launch end-->

	</div>

	<div class="clear"></div>

	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html><script type="text/javascript" src="<%=basePath%>statics/js/path.js"></script>