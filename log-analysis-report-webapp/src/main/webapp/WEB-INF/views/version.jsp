<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="com.mapbar.analyzelog.report.entity.VersionVVO"%>
<%
	String seriesData = request.getAttribute("seriesData").toString();
	String xAxisData = request.getAttribute("xAxisData").toString();
	String stype = request.getAttribute("stype")==null?"":request.getAttribute("stype").toString();
	String typetext="";
	if(stype.equals("installation"))typetext="新增用户版本变化趋势";
	else if(stype.equals("launch"))typetext="启动用户版本变化趋势";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>

<%@ include file="/WEB-INF/views/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<title>应用版本分布统计 | ${app.name}</title>
<script type="text/javascript">
	var chart0;
	$(document).ready(
			function() {
				chart0 = new Highcharts.Chart({
					chart : {
						renderTo : 'message-count-chart',
						type : 'spline'
					},
					title : {
						text : '<%=typetext%>'
					},
					subtitle : {
						text : ''
					},
					xAxis : {
						categories : <%=xAxisData.toString()%>,
						labels : {
							"step" : 1,
							"align" : "right",
							"rotation" : -45
						}
					},
					yAxis : {
						title : {
							text : ' '
						},
						min : 0
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
					series : <%=seriesData.toString()%>

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
						<li><a id="reports_tab" class="click current " href="#">统计分析</a>
						</li>
						<li><a href="#" id="module_tab">组件</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear">&nbsp;</div>
					<script type="text/javascript">
$(document).ready(function() {
    $("#version_table1").tablesorter({
    	cssDesc: "sort-header-desc",
    	cssAsc:"headerSortAsc",
    	cssDesc:"headerSortDesc",
    	cancelSelection: false,
    	sortList:[[1,1]]
    });
});

</script>
				<div class="b_right_public">
					<div class="conright">
						<div id="main">
							<div class="statsTableHeader">TOP10版本统计<strong>基本统计</strong><span class="question fr"></span><div class="clear"></div></div>
							<div style="margin-bottom: 40px;" class="stats_content"
								id="device_stats_details">
								<table width="100%" cellspacing="0" border="0"
									id="version_table1" class="new_silver_table">
									<thead>
										<tr>
											<th class="new_silver_first_n" scope="col">应用版本</th>
											<th scope="col">版本累计用户</th>
											<th scope="col">今日新用户</th>
											<th scope="col">今日升级用户</th>
											<th scope="col">今日启动用户</th>
											<th scope="col">昨日启动用户</th>
										</tr>
										</thead>
<tbody>
										<c:forEach items="${stats}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">${item.version}</td>
												<td>${item.counts}</td>
												<td>${item.news}</td>
												<td>${item.ups}</td>
												<td>${item.tds}</td>
												<td>${item.yds}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
<c:set value="1" var="vdisdate" scope="request"/>
<%@ include file="commons/date-form.jsp"%>
							
							<div class="statsTableHeader">
						<div style="float: left; text-align: left;">版本每日明细</div>
						<div class="path_choose_version" style="float: right; text-align: right;width: 500px">
						<div style="float: left; text-align: left;width: 230px">
							<input type="text" id="queryid" name="queryname" value="${queryname=='undefined'?'':queryname}"
				class="input_200"/>
				 <input type="button" onclick="vsubm2()"
				value="模糊查询&nbsp;" class="query"/>
				</div>
							 选择应用程序版本: &nbsp; <select name="app_version"
								id="app_version_selector" 
								onchange="vsubm2()">
								<option value=''>全部</option>
								<c:forEach items="${vlist}" var="items" varStatus="status">
									<c:if test="${items.version==version}">
										<option value='${items.version}' selected>
											${items.version}</option>
									</c:if>
									<c:if test="${items.version!=version}">
										<option value='${items.version}'>${items.version}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</div>
							<div style="margin-bottom: 40px;" class="stats_content"
								id="device_stats_details">
								<table width="100%" cellspacing="0" border="0"
									id="version_table1" class="new_silver_table">
									<thead>
										<tr>
											<th class="new_silver_first_n" scope="col">应用版本</th>
											<th scope="col">版本累计用户</th>
											<th scope="col">今日新用户</th>
											<th scope="col">今日升级用户</th>
											<th scope="col">昨日新增用户</th>
											<th scope="col">昨日升级用户</th>
											<th scope="col">昨日启动用户</th>
											<th scope="col">明细</th>
										</tr>
										</thead>
<tbody>
										<c:forEach items="${vdetails}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n">${item.version}</td>
												<td>${item.counts}</td>
												<td>${item.news}</td>
												<td>${item.ups}</td>
												<td>${item.tds}</td>
												<td>${item.ysups}</td>
												<td>${item.yds}</td>
												<td><a
													href=""
													class="load_channel_detail_btn">展开</a> <img alt="Loading"
													class="loading" src="/images/loading.gif?1329537551"
													style="display: none;" /></td>
											</tr>
											
											<tr class="channel_detail_tr hidden">
												<td class="channel_detail_area" colspan="7"><div
														class="fr"></div>
													<div class="clear"></div>
													<div>版本明细：${item.version}</div>
													<table cellspacing="0" cellpadding="0" border="0"
														style="margin-bottom: 20px;"
														class="datatab long new_silver_table">
														<tbody>
															<tr>
																<th class="new_silver_first_n" scope="col">日期</th>
																<th scope="col">新用户</th>
																<th scope="col">升级用户</th>
																<th scope="col">启动用户</th>
															</tr>
															<c:forEach items="${item.vdes}" var="item2" varStatus="vs">
														      <tr>
														        <td>${item2.date}</td>
														        <td>${item2.news}</td>
														        <td>${item2.ups}</td>
														        <td>${item2.launchs}</td>
														      </tr>
														      </c:forEach>
														</tbody>
													</table>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
<script type="text/javascript">
$("div.digg_pagination a").live('click', function(e){
	var $dailyStatsDetails = $('#daily_stats_details');
    var initialStatsDetailsUrl = window.location.href;
    initialStatsDetailsUrl = initialStatsDetailsUrl.replace("/reports", "/reports/daily_stats_details");
    $dailyStatsDetails.load(initialStatsDetailsUrl);
    $("div.digg_pagination a").live('click', function(e){
      var $target = $(e.target);
      var link = $target.attr("href");
      $('<img>', { src : '/images/ajax-loader.gif',
        style : 'float: right; width:20px;margin-right:10px;margin-top:10px;'}
    ).insertAfter("div.digg_pagination");
      $.get(link, {}, function(response) {
        $dailyStatsDetails.html(response);
      })
      return false;
    });
});
</script>
<%@ include file="commons/pager.jsp"%>							
							
							<div class="statsTableHeader">
								<strong>累计用户TOP5版本变化趋势</strong>
								<div class="fr" style="font-weight: normal; font-size: 13px;">
									<span class="hidden request_data_type">json</span> 过滤规则： <select onchange="vsubm2()"
										id="stats_type" name="stats_type" style="margin-right: 5px;"><option
											value="installation" 
											<c:if test="${stype=='installation'}">selected="selected"</c:if>>新增用户</option>
										<option value="launch" <c:if test="${stype=='launch'}">selected="selected"</c:if>>启动用户</option>
									</select>

								</div>
								<div class="clear"></div>
							</div>

							
							<div id="message-period-charts">
								<div class="meassage_contents">

									<div id="message-count-tab" class="meassage_contents_list">
										<div id="message-count-chart" class="line_chart_common"
											style="height: 260px; width: 95%;"></div>
									</div>
								</div>
							</div>
							
							<div class="clear"></div>
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
		<script type="text/javascript">
								jQuery(function() {
									/***
									**弹出文档框选项
									***/
									   $('.question').click(function() {
										      pop_dialog($('#question_wrap'), '818px');
										    });
									
									jQuery('a.load_channel_detail_btn')
											.click(
													function() {
														var target = jQuery(
																this)
																.closest('tr')
																.next(
																		'tr.channel_detail_tr')
																.children('td');
														if (jQuery.trim(target
																.html()) == '') {
															var loadingImg = jQuery(
																	this).next(
																	'img')
																	.show();
															var link = jQuery(this);
															target
																	.load(
																			this.href,
																			function() {
																				loadingImg
																						.hide();
																				target
																						.closest(
																								'tr')
																						.show();
																				link
																						.html('收起');
																			});
														} else {
															if (jQuery(this)
																	.html() == '展开') {
																jQuery(this)
																		.html(
																				'收起');
															} else {
																jQuery(this)
																		.html(
																				'展开');
															}
															jQuery(this)
																	.closest(
																			'tr')
																	.next(
																			'tr.channel_detail_tr')
																	.toggle();
														}
														return false;
													});

									jQuery('.select_all_btn')
											.click(
													function() {
														if (jQuery(this).attr(
																'checked')) {
															jQuery(this)
																	.closest(
																			'div.question_content_center_new_b')
																	.find(
																			":checkbox[name='channels[]']")
																	.attr(
																			'checked',
																			'checked');
														} else {
															jQuery(this)
																	.closest(
																			'div.question_content_center_new_b')
																	.find(
																			":checkbox[name='channels[]']")
																	.removeAttr(
																			'checked');
														}
													});

									jQuery('a.choose_channels').click(
											function() {
												var channels_select = jQuery(
														this).siblings(
														'div.channel_select');
												jQuery.blockUI({
													css : {
														color : '#cccccc',
														border : 0,
														width : '500px',
														top : '20%',
														textAlign : 'left',
														background : 'none'
													},
													message : channels_select
												});
											});

									jQuery('a.channel_select_commit').click(
											function() {
												var upbtn = jQuery(this
														.getAttribute("href"));
												jQuery.unblockUI({
													onUnblock : function() {
														upbtn.click();
													}
												});
												return true;
											});

									//jQuery('.filter_params .upbtn').click();
								});
							</script>
	</div>
	<div class="clear"></div>
    <div id="question_wrap" style="display: none;">
    <div class="question_content_top">
    <h3 class="title">
      <span class="fl"><b>应用程序版本分布</b>词汇表</span>
      <span class="fr tg_rss"><img alt="" src="<%=path%>/statics/images/report_subscribe_close_normal.png"></span>
      <div class="clear"></div>
    </h3>
  </div>
  <div class="question_content_center">
    <table cellpadding="0" cellspacing="0" border="0" width="775">
      <tbody><tr>
        <td class="title">版本累计用户（%）</td>
        <td>截止当前，该版本的全体用户（版本累计用户/累计用户）</td>
      </tr>
      <tr class="question_even">
        <td class="title">今日启动用户（%）</td>
        <td>该版本的今日启动用户（该版本的今日启动用户/全体今日启动用户）</td>
      </tr>
      <tr>
        <td class="title">昨日启动用户（%）</td>
        <td>该版本的昨日启动用户（该版本的昨日启动用户/全体昨日启动用户）</td>
      </tr>
    </tbody></table>
  </div>
  <div class="question_content_bottom"></div>
	</div>
	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>