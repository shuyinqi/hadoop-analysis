<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.mapbar.analyzelog.report.entity.LaunchVO"%>
<%
	StringBuffer seriesData = new StringBuffer();seriesData.append("[");
	StringBuffer xAxisData = new StringBuffer();xAxisData.append("[");
	List<LaunchVO> list=(List<LaunchVO>)request.getAttribute("stats");
	String vname=null;boolean vflag=false;
	StringBuffer sb1_2 = new StringBuffer();      //频率1-2次的用户数
	StringBuffer sb2_2 = new StringBuffer();
	StringBuffer sb3_3 = new StringBuffer();
	StringBuffer sb4_4 = new StringBuffer();
	StringBuffer sb5_5 = new StringBuffer();   //频率3-5次的用户数
	StringBuffer sb6_9 = new StringBuffer();      //频率6-9次的用户数
	StringBuffer sb10_19 = new StringBuffer();   //频率10-19次的用户数
	StringBuffer sb20_49 = new StringBuffer();      //频率20-49次的用户数
	StringBuffer sb50_ = new StringBuffer();   //频率50次以上的用户数
	sb1_2.append("{name:'").append("1次").append("',data:[");
	sb2_2.append("{name:'").append("2次").append("',data:[");
	sb3_3.append("{name:'").append("3次").append("',data:[");
	sb4_4.append("{name:'").append("4次").append("',data:[");
	sb5_5.append("{name:'").append("5次").append("',data:[");
	sb6_9.append("{name:'").append("6-9次").append("',data:[");
	sb10_19.append("{name:'").append("10-19次").append("',data:[");
	sb20_49.append("{name:'").append("20-49次").append("',data:[");
	sb50_.append("{name:'").append("50+次").append("',data:[");
	for(int i=0;i<list.size();i++){
		LaunchVO vo=list.get(i);
		xAxisData.append("'").append(vo.getDate()).append("'");		
			
		int c1_2 = vo.getC1_2();      //频率1-1次的用户数
		int c2_2 = vo.getC2_2();
		int c3_3 = vo.getC3_3();
		int c4_4 = vo.getC4_4();
		int c5_5 = vo.getC5_5();   //频率5-5次的用户数
		int c6_9 = vo.getC6_9();      //频率6-9次的用户数
		int c10_19 = vo.getC10_19();   //频率10-19次的用户数
		int c20_49 = vo.getC20_49();      //频率20-49次的用户数
		int c50_ = vo.getC50_();   //频率50次以上的用户数
		//Date date = vo.getDate();
		sb1_2.append(c1_2);
		sb2_2.append(c2_2);
		sb3_3.append(c3_3);
		sb4_4.append(c4_4);
		sb5_5.append(c5_5);
		sb6_9.append(c6_9);
		sb10_19.append(c10_19);
		sb20_49.append(c20_49);
		sb50_.append(c50_);
		
		if(i==list.size()-1){
			xAxisData.append("]");
			sb1_2.append("]}");
			sb2_2.append("]}");
			sb3_3.append("]}");
			sb4_4.append("]}");
			sb5_5.append("]}");
			sb6_9.append("]}");
			sb10_19.append("]}");
			sb20_49.append("]}");
			sb50_.append("]}");
		}else {
			xAxisData.append(",");
			sb1_2.append(",");
			sb2_2.append(",");
			sb3_3.append(",");
			sb4_4.append(",");
			sb5_5.append(",");
			sb6_9.append(",");
			sb10_19.append(",");
			sb20_49.append(",");
			sb50_.append(",");
		}
	}
	seriesData.append(sb1_2).append(",").append(sb2_2).append(",").append(sb3_3).append(",").append(sb4_4).append(",").append(sb5_5).append(",").append(sb6_9).append(",").append(sb10_19).append(",").append(sb20_49).append(",").append(sb50_).append("]");
	/* seriesData.replace(seriesData.indexOf("]")-1,seriesData.length()-1,""); */
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<title>启动次数统计 | ${app.name}</title>
<script type="text/javascript">
	var chart;
	$(document).ready(
			function() {
				chart = new Highcharts.Chart({
					chart : {
						renderTo : 'launch_count_distribution_chart',
						type : 'spline'
					},
					title : {
						text : ''
					},
					subtitle : {
						text : ''
					},
					xAxis : {
						categories :
<%=xAxisData.toString()%>
	},
					yAxis : {
						title : {
							text : ' '
						}
					},
					tooltip : {
						formatter : function() {
							return '<b>' + this.series.name + '</b><br/>'
									+ this.x + ': ' + this.y + '';
						}
					},
					plotOptions : {
						line : {
							dataLabels : {
								enabled : true
							},
							enableMouseTracking : false
						}
					},
					series :
<%=seriesData.toString()%>
	});
			});
</script>
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
				<div class="clear">&nbsp;</div>

				<div class="b_right_public">
					<!--launch begin-->
					<div class="conright">
						<div id="main">

							<div class="bs_tr"></div>
							<div class="bbox_con">
								<%@ include file="commons/date-form.jsp"%>
								<div id='launch_count_distribution_chart' class=""
									style="height: 450px; padding-bottom: 15px; display: none"></div>

								<div class="statsTableHeader">启动次数分布</div>
								<div style="padding-bottom: 10px;" class="bitemcon" id="ac0">
									<div id="active_users_total_numbers" class="hidden">87990</div>
									<table cellspacing="0" cellpadding="0" border="0"
										class="datatab long new_silver_table"
										id="bartable_daily_active_users_devices">
										<tbody>
											<tr class="">
												<th width="120" class="new_silver_first_n" scope="col">日启动次数</th>
												<th width="80" class="hidden" scope="col">用户数</th>
												<th scope="col">比例</th>
											</tr>
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">1</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c1_2}">${c1_2}</td>
												<td class="rr" id="daily_active_users_devices_numbar${c1_2}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c1_2/count)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c1_2}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c1_2/count)*100}" />%
															)
														</div>
													</div>
												</td>
											</tr>
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">2</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c2_2}">${c2_2}</td>
												<td class="rr" id="daily_active_users_devices_numbar${c2_2}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c2_2/count)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c2_2}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c2_2/count)*100}" />%
															)
														</div>
													</div>
												</td>
											</tr>
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">3</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c3_3}">${c3_3}</td>
												<td class="rr" id="daily_active_users_devices_numbar${c3_3}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c3_3/count)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c3_3}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c3_3/count)*100}" />%
															)
														</div>
													</div>
												</td>
											</tr>
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">4</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c4_4}">${c4_4}</td>
												<td class="rr" id="daily_active_users_devices_numbar${c4_4}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c4_4/count)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c4_4}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c4_4/count)*100}" />%
															)
														</div>
													</div>
												</td>
											</tr>
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">5</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c5_5}">${c5_5}</td>
												<td class="rr" id="daily_active_users_devices_numbar${c5_5}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c5_5/count)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c5_5}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c5_5/count)*100}" />%
															)
														</div>
													</div>
												</td>
											</tr>
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">6-9</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c6_9}">${c6_9}</td>
												<td class="rr" id="daily_active_users_devices_numbar${c6_9}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c6_9/count)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c6_9}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c6_9/count)*100}" />%
															)
														</div>
													</div>
												</td>
											</tr>
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">10-19</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c10_19}">${c10_19}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${c10_19}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c10_19/count)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c10_19}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c10_19/count)*100}" />%
															)
														</div>
													</div>
												</td>
											</tr>
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">20-49</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c20_49}">${c20_49}</td>
												<td class="rr"
													id="daily_active_users_devices_numbar${c20_49}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c20_49/count)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c20_49}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c20_49/count)*100}" />%
															)
														</div>
													</div>
												</td>
											</tr>
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">50+</td>
												<td class="hidden"
													id="daily_active_users_devices_num${c50_}">${c50_}</td>
												<td class="rr" id="daily_active_users_devices_numbar${c50_}"><div
														style="position: relative;">
														<div
															style="background-color: rgb(116, 119, 213); height: 15px; width: ${(c50_/count)*100}%;"></div>
														<div style="position: absolute; right: 0pt; top: 0px;">
															${c50_}(
															<fmt:formatNumber type="number" pattern="###.##"  value="${(c50_/count)*100}" />%
															)
														</div>
													</div>
												</td>
											</tr>
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
			<div class="clear"></div>
		</div>

	</div>

	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>