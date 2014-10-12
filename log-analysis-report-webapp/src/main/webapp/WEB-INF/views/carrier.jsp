<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mapbar.analyzelog.report.entity.CarrierVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<%
	StringBuffer seriesData = new StringBuffer();
	List<CarrierVO> list=(List<CarrierVO>)request.getAttribute("stats");seriesData.append("[");
	for(int i=0;i<list.size();i++){
		CarrierVO vo=list.get(i);
		if(i==0&&list.size()>1)seriesData.append("{name:'").append(vo.getDevice()).append("',y:").append(vo.getLaunchcount()).append(",sliced:").append(true).append(",selected:").append(true).append("}"); 
		else seriesData.append("['").append(vo.getDevice()).append("',").append(vo.getLaunchcount()).append("]");
		if(i==list.size()-1)seriesData.append("]");else seriesData.append(",");
	}
	%>
<title>运营商统计 | ${app.name}</title>
<script type="text/javascript">
	Highcharts.theme = {
		colors : [ '#4572A7' ]
	};// prevent errors in default theme
	var highchartsOptions = Highcharts.getOptions();
</script>

<script type="text/javascript">
	var chart;
	$(document).ready(
			function() {
				chart = new Highcharts.Chart({
					chart : {
						renderTo : 'launch_access_distribution_pie_chart',
						plotBackgroundColor : null,
						plotBorderWidth : null,
						plotShadow : false
					},
					title : {
						text : '运营商分布图'
					},
					tooltip : {
						formatter : function() {
							return '<b>' + this.point.name + '</b>: '
									+ Highcharts.numberFormat(this.percentage,2) + ' %';
						}
					},
					plotOptions : {
						pie : {
							allowPointSelect : true,
							cursor : 'pointer',
							dataLabels : {
								enabled : false
							},
							showInLegend : true
						}
					},
					series : [ {
						type : 'pie',
						name : 'launch_access_distribution',
						data :
<%=seriesData.toString()%>
	} ]
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
				<div class="b_right_public">
					<div class="conright">
						<div id="main">
							<%@ include file="commons/date-form.jsp"%>

							<div class="hbar">
								<span>运营商分布</span>
							</div>

							<div class="ccon">
								<div id="launch_access_distribution_pie_chart" class="piecleft"
									style="height: 450px; width: 350px;">
									<div id="highcharts-0" class="highcharts-container"
										style="position: relative; overflow: hidden; width: 355px; height: 465px; text-align: left; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; left: -0.5px; top: 0px;">

										<div id="launch_access_distribution_pie_chart"
											style="height: 300px, width:615px"></div>
									</div>
								</div>
								<div class="piecright">
									<table cellspacing="0" cellpadding="0" border="0"
										class="datatab long new_silver_table"
										id="bartable_launch_users_by_access">
										<thead>
											<tr class="">
												<th width="80" class="new_silver_first_n" scope="col">运营商</th>
												<th width="150" scope="col">比例</th>
											</tr>
											</thead>
											<tbody>
											<c:forEach items="${stats}" var="item" varStatus="status">
												<tr
													class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
													<td class="new_silver_first_n">${item.device}</td>
													<td class="rr" id="access_numbar0"><div
															style="position: relative;">
															<div
																style="background-color: rgb(116, 119, 213); height: 15px; width: ${(item.launchcount/launchRatio)*100}px;"></div>
															<div style="position: absolute; right: 0pt; top: 0px;">
																${item.launchcount}(<c:out
																	value="${fn:substring((item.launchcount/launchRatio)*100,0,fn:indexOf((item.launchcount/launchRatio)*100,'.')+3)}" />
																%)
															</div>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
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
	</div>
	</div>
	</div>
	<div class="clear"></div>
	</div>

	</div>

	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>