<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<title>地域统计 | ${app.name}</title>
</head>

<body>
	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>

		<div id="b_center">
			<%@ include file="/WEB-INF/views/body-leftmenu.jsp"%>

			<div class="fr b_rightconten">
				<div class="fl b_right_menu">
					<ul>
						<li><a id="reports_tab" class="current " href="#">统计分析</a>
						</li>
						<li><a href="#" id="module_tab">组件</a></li>
						<div class="clear"></div>
					</ul>
				</div>
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
							<div class="b_right_public">
								<!--location begin-->
								<div class="conright">
									<div id="main">
										<div class="bbox_con">
											<div class="statsTableHeader">地域分布</div>
											<%@ include file="commons/date-form.jsp"%>
											<div style="margin: 10px 0;" class="blockboxbg">
												<div class="blockbox">
													<div class="bitem selbitem"
														onclick="changeTab(0,2,'a','ac','bitem','selbitem')"
														id="a0">活跃用户</div>
													<div class="bitem"
														onclick="changeTab(1,2,'a','ac','bitem','selbitem')"
														id="a1">新增用户</div>
												</div>
											</div>
											<div style="padding-bottom: 10px;" class="bitemcon" id="ac0">
												<div id="active_users_total_numbers" class="hidden">87990</div>
												<table cellspacing="0" cellpadding="0" border="0"
													class="datatab long new_silver_table"
													id="bartable_daily_active_users_devices">
													<thead>
														<tr class="">
															<th width="120" class="new_silver_first_n" scope="col">省市</th>
															<th width="80" class="hidden" scope="col">用户数</th>
															<th scope="col">比例</th>
														</tr>
														</thead>
														<tbody>
														<c:forEach items="${stats}" var="item" varStatus="status">
															<tr
																class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
																<td class="new_silver_first_n">${item.city}</td>
																<td class="hidden"
																	id="daily_active_users_devices_num${status.index}">${item.visitcount}</td>
																<td class="rr"
																	id="daily_active_users_devices_numbar${status.index}"><div
																		style="position: relative;">
																		<div
																			style="background-color: rgb(116, 119, 213); height: 15px; width:<fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitcount/visitRatio)*100}" />%;"></div>
																		<div style="position: absolute; right: 0pt; top: 0px;">
																		${item.visitcount}(
																		<fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitcount/visitRatio)*100}" />
																			%)
																		</div>
																	</div>
																</td>
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
															<th width="120" class="new_silver_first_n" scope="col">省市</th>
															<th width="80" class="hidden" scope="col">用户数</th>
															<th scope="col">比例</th>
														</tr>
														</thead>
														<tbody>
														<c:forEach items="${newst}" var="item" varStatus="status">
															<tr
																class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
																<td class="new_silver_first_n">${item.city}</td>
																<td class="hidden" id="device_model_num${status.index}">${item.newcount}</td>
																<td class="rr" id="device_model_numbar${status.index}"><div
																		style="position: relative;">
																		<div
																			style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(item.newcount/newRatio)*100}" />%;"></div>
																		<div style="position: absolute; right: 0pt; top: 0px;">
																			${item.newcount}(
																			<fmt:formatNumber type="number" pattern="###.##"  value="${(item.newcount/newRatio)*100}" />
																			%)
																		</div>
																	</div>
																</td>
															</tr>
														</c:forEach>

													</tbody>
												</table>
											</div>


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
						</div>
					</div>
					<div class="bs_bottom">
						<div class="bs_bl"></div>
						<div class="bs_br"></div>
					</div>
				</div>
			</div>
		</div>

		<style type="text/css" media="screen">
b.ab {
	color: red;
}
</style>

		<script>
			jQuery(document).ready(function() {
				jQuery(".pn_close").click(function() {
					jQuery("#pn_wrap").fadeOut("slow");
					jQuery.ajax({
						url : "/new_home/tip_close",
						data : "tip=close",
						type : "GET",
						success : function(msg) {/*todo...*/
						}
					});
				});
			});
		</script>

		<div class="clear"></div>
	</div>

	</div>

	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>