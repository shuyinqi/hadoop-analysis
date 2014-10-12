<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<title>分辨率统计 | ${app.name}</title>
</head>

<body>
	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>

		<div id="b_center">
			<%@ include file="/WEB-INF/views/body-leftmenu.jsp"%>

			<div class="fr b_rightconten">
				<div class="fl b_right_menu">
					<ul>
						<li><a href="<%=basePath%>${app.id}/base"
							class="click current " id="reports_tab">统计分析</a></li>
							<li><a href="#" id="module_tab">组件</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				</span>
				<div class="clear">&nbsp;</div>
				<script type="text/javascript">
$(document).ready(function() { 
    // call the tablesorter plugin 
    $("table").tablesorter({
    	sortForce: [[1,1]],
        sortList: [[1,1]]
    });
});
</script>
				<div class="b_right_public">
					<div class="conright">
						<div id="main">
							<h3 class="title" style="display: none;">
								<span class="text fl">${app.name}</span> <span
									class="sub_text fl"><span class="days">统计第<span
										class="daynums">&nbsp;604&nbsp;</span>天</span> </span>
								<div class="clear"></div>
							</h3>
							<%@ include file="commons/date-form.jsp"%>
							<div class="statsTableHeader">TOP 10分辨率</div>
							<div style="margin: 10px 0;" class="blockboxbg">
								<div class="blockbox">
									<div class="bitem selbitem"
										onclick="changeTab(0,2,'a','ac','bitem','selbitem')" id="a0">活跃用户</div>
									<div class="bitem"
										onclick="changeTab(1,2,'a','ac','bitem','selbitem')" id="a1">新增用户</div>
								</div>
							</div>
							<div style="padding-bottom: 10px;" class="bitemcon" id="ac0">
								<div id="active_users_total_numbers" class="hidden">87990</div>
								<table cellspacing="0" cellpadding="0" border="0"
									class="datatab long new_silver_table"
									id="bartable_daily_active_users_devices">
									<thead>
										<tr class="">
											<th width="120" class="new_silver_first_n" scope="col">分辨率</th>
											<th width="80" class="hidden" scope="col">用户数</th>
											<th scope="col">比例</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${stats1}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">${item.device}</td>
												<td class="hidden"
													id="daily_active_users_devices_num${status.index}">${item.launchcount}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${status.index}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(item.launchcount/launchRatio)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${item.launchcount}(<c:out
																value="${fn:substring((item.launchcount/launchRatio)*100,0,fn:indexOf((item.launchcount/launchRatio)*100,'.')+3)}" />
															%)
														</div>
													</div></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
							<div style="display: none; padding-bottom: 10px;"
								class="bitemcon" id="ac1">
								<div id="install_total_numbers" class="hidden">5882</div>
								<table cellspacing="0" cellpadding="0" border="0"
									class="datatab long new_silver_table"
									id="bartable_install_devices">
									<thead>
										<tr>
											<th width="120" class="new_silver_first_n" scope="col">分辨率</th>
											<th width="80" class="hidden" scope="col">用户数</th>
											<th scope="col">比例</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${newst}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">${item.device}</td>
												<td class="hidden" id="device_model_num${status.index}">${item.newcount}</td>
												<td class="rr" id="device_model_numbar${status.index}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width:<fmt:formatNumber type="number" pattern="###.##"  value="${(item.newcount/newsRatio)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${item.newcount}(<c:out
																value="${fn:substring((item.newcount/newsRatio)*100,0,fn:indexOf((item.newcount/newsRatio)*100,'.')+3)}" />
															%)
														</div>
													</div></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
							<div class="statsTableHeader">
		<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list=true')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list=true')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div>
		<strong>分辨率分布明细</strong>
	</div>
							<div style="margin-bottom: 40px;" class="stats_content"
								id="device_stats_details">
								<table width="100%" cellspacing="0" border="0"
									class="new_silver_table">
									<thead>
										<tr>
											<th class="new_silver_first_n" scope="col">分辨率</th>
											<th scope="col">用户比例</th>
										</tr>
										</thead>
<tbody>
										<c:forEach items="${stats}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">${item.device}</td>
												<td>${item.launchcount}(<c:out
														value="${fn:substring((item.launchcount/launchRatio)*100,0,fn:indexOf((item.launchcount/launchRatio)*100,'.')+3)}" />%)</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>
					</div>
					<div class="bs_bottom">
						<div class="bs_bl"></div>
						<div class="bs_br"></div>
					</div>
				</div>
				<!--location end-->
				<%@ include file="commons/pager.jsp"%>
			</div>
		</div>
		<div class="bs_bottom">
			<div class="bs_bl"></div>
			<div class="bs_br"></div>
		</div>
	</div>
	</div>
	</div>
	<div class="clear"></div>

	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>