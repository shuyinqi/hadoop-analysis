<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<title>使用时长统计 | ${app.name}</title>
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
						<div class="clear"></div>
					</ul>
				</div>
				<span class="fr b_docu_link">
				</span>

				<div class="clear">&nbsp;</div>
				<div class="b_right_public">
					<!--launch begin-->
					<div class="conright">
						<div id="main">

							<div class="bs_tr"></div>
							<div class="bbox_con">
								<%@ include file="commons/channel-version-form.jsp"%>
								<%@ include file="commons/date-form.jsp"%>
								<div class="statsTableHeader">使用时长分布</div>
								
								
								<div class="blockboxbg" style="margin: 10px 0;">
									<div class="blockbox">
										<div id="a0"
											onclick="changeTab(0,4,'a','ac','bitem','selbitem')"
											class="bitem selbitem">单次使用时长</div>
										<div id="a1"
											onclick="changeTab(1,4,'a','ac','bitem','selbitem')"
											class="bitem">日使用时长</div>
										<div id="a2"
											onclick="changeTab(2,4,'a','ac','bitem','selbitem')"
											class="bitem">周使用时长</div>
								</div>
								</div>
								<div style="margin-top:10px;padding-bottom: 0px;" class="bitemcon" id="ac0">
								<div class="clear"></div>
									<table cellspacing="0" cellpadding="0" border="0" 
										class="datatab long new_silver_table"
										id="bartable_daily_active_users_devices">
										<thead>
											<tr class="">
												<th width="120" class="new_silver_first_n" scope="col">单次使用时长</th>
												<th width="80" class="hidden" scope="col">用户数</th>
												<th scope="col">比例</th>
											</tr>
											</thead>
											<tbody>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">0-3秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c0_3}">${c0_3}</td>
												<td class="rr" id="daily_active_users_devices_numbar${c0_3}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c0_3/totalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c0_3}(
															<c:out
																value="${fn:substring((c0_3/totalCount)*100,0,fn:indexOf((c0_3/totalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">4-10秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c4_9}">${c4_9}</td>
												<td class="rr" id="daily_active_users_devices_numbar${c4_9}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c4_9/totalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c4_9}(
															<c:out
																value="${fn:substring((c4_9/totalCount)*100,0,fn:indexOf((c4_9/totalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">10-30秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c10_29}">${c10_29}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${c10_29}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c10_29/totalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c10_29}(
															<c:out
																value="${fn:substring((c10_29/totalCount)*100,0,fn:indexOf((c10_29/totalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">30-60秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c30_59}">${c30_59}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${c30_59}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c30_59/totalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c30_59}(
															<c:out
																value="${fn:substring((c30_59/totalCount)*100,0,fn:indexOf((c30_59/totalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">1-3分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c60_179}">${c60_179}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${c60_179}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(c60_179/totalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c60_179}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c60_179/totalCount)*100}" />%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">3-10分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c180_599}">${c180_599}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${c180_599}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(c180_599/totalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c180_599}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c180_599/totalCount)*100}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">10-30分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c600_1799}">${c600_1799}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${c600_1799}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(c600_1799/totalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c600_1799}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c600_1799/totalCount)*100}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">30分钟以上</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c1800_}">${c1800_}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${c1800_}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width:<fmt:formatNumber type="number" pattern="###.##"  value="${(c1800_/totalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c1800_}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c1800_/totalCount)*100}" />
															%)
														</div>
													</div>
												</td>

											</tr>

										</tbody>
									</table>
									</div>
									<!-- ----------------------------------------------------------------------------------------------------------- -->
									
									<div style="margin-top:10px;padding-bottom: 0px;display:none;"  class="bitemcon" id="ac1">
								<div class="clear"></div>
									<table cellspacing="0" cellpadding="0" border="0" 
										class="datatab long new_silver_table"
										id="bartable_daily_active_users_devices_a">
										<thead>
											<tr class="">
												<th width="120" class="new_silver_first_n" scope="col">日使用时长</th>
												<th width="80" class="hidden" scope="col">用户数</th>
												<th scope="col">比例</th>
											</tr>
											</thead>
											<tbody>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">0-3秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${ac0_3}">${ac0_3}</td>
												<td class="rr" id="daily_active_users_devices_numbar${ac0_3}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(ac0_3/atotalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${ac0_3}(
															<c:out
																value="${fn:substring((ac0_3/atotalCount)*100,0,fn:indexOf((ac0_3/atotalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">4-10秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${ac4_9}">${ac4_9}</td>
												<td class="rr" id="daily_active_users_devices_numbar${ac4_9}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(ac4_9/atotalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${ac4_9}(
															<c:out
																value="${fn:substring((ac4_9/atotalCount)*100,0,fn:indexOf((ac4_9/atotalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">10-30秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${ac10_29}">${ac10_29}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${ac10_29}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(ac10_29/atotalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${ac10_29}(
															<c:out
																value="${fn:substring((ac10_29/atotalCount)*100,0,fn:indexOf((ac10_29/atotalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">30-60秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${ac30_59}">${ac30_59}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${ac30_59}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(ac30_59/atotalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${ac30_59}(
															<c:out
																value="${fn:substring((ac30_59/atotalCount)*100,0,fn:indexOf((ac30_59/atotalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">1-3分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${ac60_179}">${ac60_179}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${ac60_179}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(ac60_179/atotalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${ac60_179}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(ac60_179/atotalCount)*100}" />%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">3-10分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${ac180_599}">${ac180_599}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${ac180_599}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(ac180_599/atotalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${ac180_599}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(ac180_599/atotalCount)*100}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">10-30分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${ac600_1799}">${ac600_1799}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${ac600_1799}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(ac600_1799/atotalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${ac600_1799}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(ac600_1799/atotalCount)*100}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">30分钟以上</td>
												<td class="hidden"
													id="daily_active_users_devices_num${ac1800_}">${ac1800_}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${ac1800_}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width:<fmt:formatNumber type="number" pattern="###.##"  value="${(ac1800_/atotalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${ac1800_}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(ac1800_/atotalCount)*100}" />
															%)
														</div>
													</div>
												</td>

											</tr>

										</tbody>
									</table>
									</div>
									<!-- ----------------------------------------------------------------------------------------------------------- -->
									<!-- ----------------------------------------------------------------------------------------------------------- -->
									
									<div style="margin-top:10px;padding-bottom: 0px;display:none;"  class="bitemcon" id="ac2">
								<div class="clear"></div>
									<table cellspacing="0" cellpadding="0" border="0" 
										class="datatab long new_silver_table"
										id="bartable_daily_active_users_devices_b">
										<thead>
											<tr class="">
												<th width="120" class="new_silver_first_n" scope="col">周使用时长</th>
												<th width="80" class="hidden" scope="col">用户数</th>
												<th scope="col">比例</th>
											</tr>
											</thead>
											<tbody>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">1-30秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${cc0_3}">${cc0_3}</td>
												<td class="rr" id="daily_active_users_devices_numbar${cc0_3}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(cc0_3/ctotalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${cc0_3}(
															<c:out
																value="${fn:substring((cc0_3/ctotalCount)*100,0,fn:indexOf((cc0_3/ctotalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">30-60秒</td>
												<td class="hidden"
													id="daily_active_users_devices_num${cc4_9}">${cc4_9}</td>
												<td class="rr" id="daily_active_users_devices_numbar${cc4_9}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(cc4_9/ctotalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${cc4_9}(
															<c:out
																value="${fn:substring((cc4_9/ctotalCount)*100,0,fn:indexOf((cc4_9/ctotalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">1-3分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${cc10_29}">${cc10_29}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${cc10_29}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(cc10_29/ctotalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${cc10_29}(
															<c:out
																value="${fn:substring((cc10_29/ctotalCount)*100,0,fn:indexOf((cc10_29/ctotalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">3-10分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${cc30_59}">${cc30_59}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${cc30_59}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(cc30_59/ctotalCount)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${cc30_59}(
															<c:out
																value="${fn:substring((cc30_59/ctotalCount)*100,0,fn:indexOf((cc30_59/ctotalCount)*100,'.')+2)}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">10-30分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${cc60_179}">${cc60_179}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${cc60_179}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(cc60_179/ctotalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${cc60_179}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(cc60_179/ctotalCount)*100}" />%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">30-60分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${cc180_599}">${cc180_599}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${cc180_599}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(cc180_599/ctotalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${cc180_599}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(cc180_599/ctotalCount)*100}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">60-90分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${cc600_1799}">${cc600_1799}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${cc600_1799}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: <fmt:formatNumber type="number" pattern="###.##"  value="${(cc600_1799/ctotalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${cc600_1799}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(cc600_1799/ctotalCount)*100}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_odd'}">
												<td class="new_silver_first_n">90-120分钟</td>
												<td class="hidden"
													id="daily_active_users_devices_num${cc1800_}">${cc1800_}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${cc1800_}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width:<fmt:formatNumber type="number" pattern="###.##"  value="${(cc1800_/ctotalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${cc1800_}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(cc1800_/ctotalCount)*100}" />
															%)
														</div>
													</div>
												</td>
											</tr>
											<tr class="${'new_silver_even'}">
												<td class="new_silver_first_n">120分钟以上</td>
												<td class="hidden"
													id="daily_active_users_devices_num${cc7200_}">${cc7200_}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${cc7200_}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width:<fmt:formatNumber type="number" pattern="###.##"  value="${(cc7200_/ctotalCount)*100}" />%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${cc7200_}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(cc7200_/ctotalCount)*100}" />
															%)
														</div>
													</div>
												</td>
											</tr>

										</tbody>
									</table>
									</div>
									<!-- ----------------------------------------------------------------------------------------------------------- -->
									
								</div>

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
			</div>
			<script type="text/javascript">
				createNumBar('bartable_launch_distribution',
						'launch_distribution_num', 'launch_distribution_numbar');
				//chart
				var start_date = $(".datainp[name='start_date']").val();
				var end_date = $(".datainp[name='end_date']").val();
				render_chart('launch_count_distribution_chart', '',
						'/apps/1000/launch', {
							type : 'launch',
							'start_date' : start_date,
							'end_date' : end_date
						}, false, {
							tooltip : {
								formatter : function() {
									return this.series.name + this.x + ': '
											+ this.y;
								}
							}
						});
				//chart end
			</script>
			<script type="text/javascript">
				$(function() {
					$(".feedback_wrap").mouseover(function() {
						$(".feedback_tip").show();
					});
					$(".feedback_wrap").mouseout(function() {
						$(".feedback_tip").hide();
					});
				});
			</script>
			<script type="text/javascript">
				var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl."
						: "http://www.");
				document
						.write(unescape("%3Cscript src='"
								+ gaJsHost
								+ "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
			</script>
			<script type="text/javascript">
				try {
					var pageTracker = _gat._getTracker("UA-17469061-1");
					pageTracker._trackPageview();
				} catch (err) {
				}
			</script>
			<script type="text/javascript">
				function change_input_hover_bg(n_input, n_normal, n_hover) {
					$(n_input).mouseover(function() {
						$(this)[0].src = n_hover;
					}).mouseout(function() {
						$(this)[0].src = n_normal;
					});
				}
			</script>

			<script type="text/javascript">
				var user_id = '4bd67e721d41c85a03000002';
				var is_today_user = false;
				var is_demo_user = true;
			</script>
			<script src="/javascripts/click.js?1329905492" type="text/javascript"></script>






			<script type="text/javascript">
				jQuery(function() {
					var $dailyStatsDetails = jQuery('#daily_stats_details');
					var initialStatsDetailsUrl = window.location.href;
					initialStatsDetailsUrl = initialStatsDetailsUrl.replace(
							"/reports", "/reports/daily_stats_details");
					$dailyStatsDetails.load(initialStatsDetailsUrl);
					jQuery("div.digg_pagination a")
							.live(
									'click',
									function(e) {
										var $target = jQuery(e.target);
										var link = $target.attr("href");
										jQuery(
												'<img>',
												{
													src : '/images/ajax-loader.gif',
													style : 'float: right; width:20px;margin-right:10px;margin-top:10px;'
												}).insertAfter(
												"div.digg_pagination");
										jQuery.get(link, {},
												function(response) {
													$dailyStatsDetails
															.html(response);
												});
										return false;
									});
				});
			</script>

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