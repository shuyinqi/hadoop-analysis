<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.mapbar.analyzelog.report.entity.EventVVO"%>
<%
	StringBuffer seriesData = new StringBuffer();seriesData.append("[");
	StringBuffer xAxisData = new StringBuffer();xAxisData.append("[");
	String evname=(String)request.getAttribute("eventname");
	List<EventVVO> list=(List<EventVVO>)request.getAttribute("eventvs");
	for(int i=0;i<list.size();i++){
		EventVVO vo=list.get(i);
		xAxisData.append("'").append(vo.getDate()).append("'");
		if(i==0)seriesData.append("{name:'").append(evname).append("',data:["); 
		seriesData.append(vo.getCount());
		if(i==list.size()-1){
			seriesData.append("]");
			xAxisData.append("]");
		}else {
			seriesData.append(",");
			xAxisData.append(",");
		}
	}
	seriesData.append("}]");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<title>${eventname }事件统计 | ${app.name} </title>
<script type="text/javascript">
	$(function() {
		//var $message_period_charts = $('#message-period-charts').tabs();
		$("ul.meassage_control").tabs(
				"div.meassage_contents > div.meassage_contents_list");

		var $eventLabelsStats = $('#event-labels-stats');
		var initialEventLabelsStatsUrl = window.location.href;
		var tagRegexStr = "[^?]+"
		var eventTag = "MainPageMenu";
		var period = getURLParameter('period') || 'week';
		period = period == 'null' ? 'week' : period;
		var chartCache = {};
		initialEventLabelsStatsUrl = initialEventLabelsStatsUrl.replace(
				new RegExp("events\/" + tagRegexStr),
				"events/MainPageMenu/event_labels_stats");
		var labelStatsUrl = location.href.replace(new RegExp("(events/"
				+ tagRegexStr + ")"), "$1/label_stats");

		$('select#app_version').change(
				function() {
					var selected_version = this.value;
					getEventLabelsStat(initialEventLabelsStatsUrl, {
						id : eventTag,
						app_version : selected_version
					});
					$('.export_link').each(
							function() {
								if (this.href.indexOf('app_version') > 0) {
									this.href = this.href.replace(
											/app_version=[^&]+/, 'app_version='
													+ selected_version);
								} else {
									this.href = this.href + '&app_version='
											+ selected_version;
								}
							});
				});

		$("div.digg_pagination a").live('click', function(e) {
			var $target = $(e.target);
			var link = $target.attr("href");
			$('<img>', {
				src : '/images/ajax-loader.gif',
				style : 'float: right; width:20px;margin-right:10px;'
			}).insertAfter("div.digg_pagination");
			getEventLabelsStat(link);
			return false;
		});

		function renderLabelStatsChart(element, label, dates, data) {
			var options = {
				title : {
					text : label
				},
				chart : {
					renderTo : element,
					height : 200,
					defaultSeriesType : "spline",
					width : 698
				},
				legend : {
					enabled : false
				},
				xAxis : {
					labels : {
						align : "right",
						rotation : -90,
						step : 4
					},
					categories : dates
				},
				yAxis : {
					min : 0,
					title : "",
					min:0
				},
				tooltip : {
					enabled : true
				},
				credits : {
					enabled : false
				},
				plotOptions : {
					areaspline : {
						fillOpacity : 0.5
					}
				},
				series : [ {
					name : label,
					data : data
				} ]
			};
			options.tooltip.formatter = function() {
				return this.x + ': ' + this.y;
			}
			new Highcharts.Chart(options);
		}

		$("a.get_event_labels_stats").click(function() {
			getEventLabelsStat($(this).attr("href"))
			return false;
		})

	});
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
	var chart;
	$(document).ready(
			function() {
				chart = new Highcharts.Chart({
					chart : {
						renderTo : 'message-count-chart',
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
<script type="text/javascript">
	$(function() {
		$('.period_selector').each(
				function() {
					var period_param = $(this).attr('href').replace('?', '');
					var base_url = window.location.href;
					base_url.replace(/[\?&]start_date=[^&]+/, '').replace(
							/[\?&]end_date=[^&]+/, '');
					if (base_url.indexOf('?') > 0) {
						if (base_url.indexOf('period=') > 0) {
							this.href = base_url.replace(/period=[^&]+/,
									period_param);
						} else {
							this.href = base_url + '&' + period_param;
						}
					} else {
						this.href = base_url + '?' + period_param;
					}
					$(this).removeClass('period_selector');
				});

	})

	function setDate(dates) {
		var dates_array = dates.split(";");
		var first_date = dates_array[0];
		var last_date = dates_array[1];
		$('#first_date').val(first_date);
		$('#last_date').val(last_date);
	}

	var subm = function(upbtn) {
		var par1 = $(upbtn).siblings('.first_date').first().val();
		var par2 = $(upbtn).siblings('.last_date').first().val();
		var par3 = $(upbtn).siblings('#ad_man').first().val();
		var par4 = $(upbtn).siblings('#other').first().val();
		var url = trimUrl(window.location.href) + "?start_date=" + par1
				+ "&end_date=" + par2 + "&ad_man=" + par3 + "&other=" + par4
		window.location.href = url;
	}
	function trimUrl(url) {
		if (url != null && url.length > 1) {
			var temp = url[url.length - 1] == '#' ? url.substring(0,
					url.length - 1) : url;
			var pos = temp.indexOf("?", 0);
			if (pos != -1) {
				temp = url.substring(0, pos)
			}
			return temp[temp.length - 1] == '/' ? temp.substring(0,
					temp.length - 1) : temp;
		} else
			return url;
	}
</script>

<script type="text/javascript">
	jQuery(function() {
		var $dailyStatsDetails = jQuery('#daily_stats_details');
		var initialStatsDetailsUrl = window.location.href;
		initialStatsDetailsUrl = initialStatsDetailsUrl.replace("/reports",
				"/reports/daily_stats_details");
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
									}).insertAfter("div.digg_pagination");
							jQuery.get(link, {}, function(response) {
								$dailyStatsDetails.html(response);
							});
							return false;
						});
	});
</script>
</head>

<body>
	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>

		<div id="b_center">
			<%@ include file="/WEB-INF/views/body-leftmenu.jsp"%>

			<div class="fr b_rightconten event_show">

				<div class="fl b_right_menu">
					<ul>
						<li><a
							href="#"
							class="click current " id="reports_tab">统计分析</a></li>
								<li><a href="#" id="module_tab">组件</a></li>
						<div class="clear"></div>
					</ul>
				</div>

				<div class="clear">&nbsp;</div>

				<div class="b_right_public">
					<div class="conright">

						<div id="main">
							<div class="bs_tr"></div>

							<div class="bbox_con event_table">

								<div class="kong5"></div>
								<!-- start -->
								<%@ include file="commons/date-form.jsp"%>


								<div class="statsTableHeader">
									事件ID:<span>${eventname }</span>&nbsp;&nbsp;&nbsp;&nbsp; 事件名称:<span>无</span>



									<div id='event_app_version_filter'
										style="font-size: 13px; font-weight: normal; float: right;">
										<form accept-charset="UTF-8"
											action="/apps/b2700046c68c14d1744d41c4/events/MainPageMenu"
											method="get">
											<div style="margin: 0; padding: 0; display: inline">
												<input name="utf8" type="hidden" value="&#x2713;" />
											</div>
											<input id="period" name="period" type="hidden" value="week" />
											<input id="app_versions" name="app_versions" type="hidden" />


										</form>
									</div>

									<div class="clear"></div>

								</div>

								<div id="message-period-charts">
									<ul class="meassage_control">
										<li id="a0" class="current"><span>事件消息数量</span>
										</li>
										<!-- 
										<li id="a1"><span>消息数量 / 启动用户</span>
										</li>
										<li id="a2"><span>消息数量 / 启动次数</span>
										</li> -->
										<div class="clear"></div>
									</ul>
									<div class="meassage_contents">

										<div id="message-count-tab" class="meassage_contents_list">
											<div id="message-count-chart" class="line_chart_common"
												style="height: 260px; width: 95%;"></div>
										</div>

										<div id="message-user-tab" class="meassage_contents_list">
											<div id="message-count-over-active-user-chart"
												style="height: 260px; width: 95%;"></div>
										</div>

										<div id="message-launch-tab" class="meassage_contents_list">
											<div id="message-count-over-launches-chart"
												style="height: 260px; width: 95%;"></div>
										</div>
									</div>
								</div>
								<div class="event_tips">
									注释:&nbsp;若客户端发送策略为"启动时发送"，那么当前使用所产生的事件消息，将在应用程序下次启动时才能发送到服务器，所以事件统计会有一定延时。采用"实时发送"策略时,事件会立刻反映在统计报表中。
								</div>

								<div class="statsTableHeader">
									事件标签分布
									<div class="fr" id="csv_link"
										style="margin: 1px 0 0 15px; font-weight: normal; font-size: 13px;">
									</div>
								</div>

								<div class="clear"></div>

								<div id="event-labels-stats">
									<div id="event_labels_total_numbers" class="hidden">373</div>
									<table cellspacing="0" cellpadding="0" border="0"
										class="datatab long new_silver_table"
										id="event-labels-stats-table">

										<tbody>
											<tr>
												<th width="150" class="new_silver_first_n" scope="col">事件标签</th>
												<th width="150" scope="col">消息数量</th>
												<th scope="col">比例</th>
											<!-- <th width="60" scope="col">趋势</th> -->	
											</tr>

											<c:forEach items="${stats}" var="item" varStatus="status">
												<tr
													class="${status.index%2==0?'new_silver_odd':'new_silver_even'}"
													id="label-Menu${status.index}">
													<td class="new_silver_first_n" alt="Menu${status.index}"
														title="Menu${status.index}">${item.label}</td>
													<td id="numA${status.index}">${item.count}</td>
													<td id="numbarA${status.index}"><div
															style="position: relative;">
															<div
																style="background-color: rgb(116, 119, 213); height: 15px; width:${(item.count/launchRatio)*100}%;"></div>
															<div style="position: absolute; right: 0pt; top: 0px;">
																${item.count}(${launchRatio})(<c:out
																	value="${fn:substring((item.count/launchRatio)*100,0,fn:indexOf((item.count/launchRatio)*100,'.')+2)}" />
																%)
															</div>
														</div>
													</td>
													<!-- 
													<td id="td${status.index}"><a
														data-label="Menu${status.index}" href=""
														class="open-label-stats-chart">详情</a>
														<div class="in-table-stats-chart"></div></td> -->
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
								<!-- end -->
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
		</div>

	</div>

	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>