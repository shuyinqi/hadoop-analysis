<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.mapbar.analyzelog.report.entity.RetentionVO"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	StringBuffer seriesData_7 = new StringBuffer();seriesData_7.append("[");
	StringBuffer seriesData_14 = new StringBuffer();seriesData_14.append("[");
	StringBuffer seriesData_30 = new StringBuffer();seriesData_30.append("[");
	StringBuffer seriesData_60 = new StringBuffer();seriesData_60.append("[");
	StringBuffer seriesData_90 = new StringBuffer();seriesData_90.append("[");
	
	StringBuffer seriesBili_7 = new StringBuffer();seriesBili_7.append("[");
	StringBuffer seriesBili_14 = new StringBuffer();seriesBili_14.append("[");
	StringBuffer seriesBili_30 = new StringBuffer();seriesBili_30.append("[");
	StringBuffer seriesBili_60 = new StringBuffer();seriesBili_60.append("[");
	StringBuffer seriesBili_90 = new StringBuffer();seriesBili_90.append("[");
	StringBuffer xAxisData = new StringBuffer();xAxisData.append("[");
	List<RetentionVO> list=(List<RetentionVO>)request.getAttribute("stats");
	String vname=null;boolean vflag=false;
	StringBuffer count_add = new StringBuffer();      //频率1-2次的用户数
	StringBuffer count_7 = new StringBuffer();   //频率3-5次的用户数
	StringBuffer count_14 = new StringBuffer();      //频率6-9次的用户数	
	StringBuffer count_30 = new StringBuffer();      
	StringBuffer count_60 = new StringBuffer();     
	StringBuffer count_90 = new StringBuffer();     
	
	StringBuffer bl_7 = new StringBuffer();   //频率3-5次的用户数
	StringBuffer bl_14 = new StringBuffer();      //频率6-9次的用户数
	StringBuffer bl_30 = new StringBuffer();
	StringBuffer bl_60 = new StringBuffer();
	StringBuffer bl_90 = new StringBuffer();
	
	count_add.append("{name:'").append("当日新增用户").append("',data:[");
	count_7.append("{name:'").append("7天内回访用户").append("',data:[");
	count_14.append("{name:'").append("14天内回访用户").append("',data:[");
	count_30.append("{name:'").append("1个月内回访用户").append("',data:[");
	count_60.append("{name:'").append("2个月内回访用户").append("',data:[");
	count_90.append("{name:'").append("3个月内回访用户").append("',data:[");
	
	bl_7.append("{name:'").append("7天内回访用户比例").append("',data:[");
	bl_14.append("{name:'").append("14天内回访用户比例").append("',data:[");
	bl_30.append("{name:'").append("1个月内回访用户比例").append("',data:[");
	bl_60.append("{name:'").append("2个月内回访用户比例").append("',data:[");
	bl_90.append("{name:'").append("3个月内回访用户比例").append("',data:[");
	
	for(int i=0;i<list.size();i++){
		RetentionVO vo=list.get(i);
		Date dd = vo.getDate();
		DateFormat format = new SimpleDateFormat("MM-dd");
		String date = format.format(dd);
		xAxisData.append("'").append(date).append("'");		
			
		double c_add = vo.getAddCount();      //频率1-2次的用户数
		double c_7 = vo.getVisitCount_7();   //频率3-5次的用户数
		double c_14 = vo.getVisitCount_14();      //频率6-9次的用户数
		double c_30 = vo.getVisitCount_30();
		double c_60 = vo.getVisitCount_60();
		double c_90 = vo.getVisitCount_90();
		
		double b_7 =  c_add == 0 ? 0 : (double)Math.floor(c_7/c_add*100);
		double b_14 =  c_add == 0 ? 0 : (double)Math.floor(c_14/c_add*100);
		double b_30 =  c_add == 0 ? 0 : (double)Math.floor(c_30/c_add*100);
		double b_60 =  c_add == 0 ? 0 : (double)Math.floor(c_60/c_add*100);
		double b_90 =  c_add == 0 ? 0 : (double)Math.floor(c_90/c_add*100);
		//Date date = vo.getDate();
		count_add.append(c_add);
		count_7.append(c_7);
		count_14.append(c_14);
		count_30.append(c_30);
		count_60.append(c_60);
		count_90.append(c_90);
		
		bl_7.append(b_7);
		bl_14.append(b_14);
		bl_30.append(b_30);
		bl_60.append(b_60);
		bl_90.append(b_90);
		
		if(i==list.size()-1){
			xAxisData.append("]");
			count_add.append("]}");
			count_7.append("]}");
			count_14.append("]}");
			count_30.append("]}");
			count_60.append("]}");
			count_90.append("]}");
			
			bl_7.append("]}");
			bl_14.append("]}");
			bl_30.append("]}");
			bl_60.append("]}");
			bl_90.append("]}");
		}else {
			xAxisData.append(",");
			count_add.append(",");
			count_7.append(",");
			count_14.append(",");
			count_30.append(",");
			count_60.append(",");
			count_90.append(",");
			bl_7.append(",");
			bl_14.append(",");
			bl_30.append(",");
			bl_60.append(",");
			bl_90.append(",");
		}
	}
	seriesData_7.append(count_add).append(",").append(count_7).append("]");
	seriesData_14.append(count_add).append(",").append(count_14).append("]");
	seriesData_30.append(count_add).append(",").append(count_30).append("]");
	seriesData_60.append(count_add).append(",").append(count_60).append("]");
	seriesData_90.append(count_add).append(",").append(count_90).append("]");
	seriesBili_7.append(bl_7).append("]");
	seriesBili_14.append(bl_14).append("]");
	seriesBili_30.append(bl_30).append("]");
	seriesBili_60.append(bl_60).append("]");
	seriesBili_90.append(bl_90).append("]");
	/* seriesData.replace(seriesData.indexOf("]")-1,seriesData.length()-1,""); */
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<title>回访用户分析 | ${app.name}</title>
<script type="text/javascript">
	var chart;
	$(document).ready(
			function() {
				/***
				**弹出文档框选项
				***/
				   $('.question').click(function() {
					      pop_dialog($('#question_wrap'), '818px');
					    });
				chart = new Highcharts.Chart({
					chart : {
						renderTo : 'ac0',
						type : 'spline'
					},
					title : {
						text : ''
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
					series : <%=seriesData_7.toString()%>

				});
			});
</script>
<script type="text/javascript">
	var chart2;
	$(document).ready(
			function() {
				chart2 = new Highcharts.Chart({
					chart : {
						renderTo : 'ac1',
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
<%=xAxisData.toString()%>,labels : {
	"step" : 1,
	"align" : "right",
	"rotation" : -45
}
	},
					yAxis : {
						title : {
							text : ' '
						},"min":0
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
<%=seriesData_14.toString()%>
	});
			});
</script>
<script type="text/javascript">
	var chart3;
	$(document).ready(
			function() {
				chart3 = new Highcharts.Chart({
					chart : {
						renderTo : 'ac2',
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
<%=xAxisData.toString()%>,labels : {
	"step" : 1,
	"align" : "right",
	"rotation" : -45
}
	},
					yAxis : {
						title : {
							text : ' '
						},"min":0
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
<%=seriesData_30.toString()%>
	});
			});
</script>
<script type="text/javascript">
	var chart4;
	$(document).ready(
			function() {
				chart4 = new Highcharts.Chart({
					chart : {
						renderTo : 'ac3',
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
<%=xAxisData.toString()%>,labels : {
	"step" : 1,
	"align" : "right",
	"rotation" : -45
}
	},
					yAxis : {
						title : {
							text : ' '
						},"min":0
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
<%=seriesData_60.toString()%>
	});
			});
</script>
<script type="text/javascript">
	var chart5;
	$(document).ready(
			function() {
				chart5 = new Highcharts.Chart({
					chart : {
						renderTo : 'ac4',
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
<%=xAxisData.toString()%>,labels : {
	"step" : 1,
	"align" : "right",
	"rotation" : -45
}
	},
					yAxis : {
						title : {
							text : ' '
						},"min":0
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
<%=seriesData_90.toString()%>
	});
			});
</script>
<script type="text/javascript">
	var chart6;
	$(document).ready(
			function() {
				
				chart6 = new Highcharts.Chart({
					chart : {
						renderTo : 'bl0',
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
<%=xAxisData.toString()%>,labels : {
	"step" : 1,
	"align" : "right",
	"rotation" : -45
}
	},
					yAxis : {
						title : {
							text : ' '
						},"min":0
					},
					tooltip : {
						formatter : function() {
							return '<b>' + this.series.name + '</b><br/>'
									+ this.x + ': ' + this.y + '%';
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
<%=seriesBili_7.toString()%>
	});
			});
</script>
<script type="text/javascript">
	var chart7;
	$(document).ready(
			function() {
				chart7 = new Highcharts.Chart({
					chart : {
						renderTo : 'bl1',
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
<%=xAxisData.toString()%>,labels : {
	"step" : 1,
	"align" : "right",
	"rotation" : -45
}
	},
					yAxis : {
						title : {
							text : ' '
						},"min":0
					},
					tooltip : {
						formatter : function() {
							return '<b>' + this.series.name + '</b><br/>'
									+ this.x + ': ' + this.y + '%';
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
<%=seriesBili_14.toString()%>
	});
			});
</script>
<script type="text/javascript">
	var chart8;
	$(document).ready(
			function() {
				chart8 = new Highcharts.Chart({
					chart : {
						renderTo : 'bl2',
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
<%=xAxisData.toString()%>,labels : {
	"step" : 1,
	"align" : "right",
	"rotation" : -45
}
	},
					yAxis : {
						title : {
							text : ' '
						},"min":0
					},
					tooltip : {
						formatter : function() {
							return '<b>' + this.series.name + '</b><br/>'
									+ this.x + ': ' + this.y + '%';
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
<%=seriesBili_30.toString()%>
	});
			});
</script>
<script type="text/javascript">
	var chart9;
	$(document).ready(
			function() {
				chart9 = new Highcharts.Chart({
					chart : {
						renderTo : 'bl3',
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
<%=xAxisData.toString()%>,labels : {
	"step" : 1,
	"align" : "right",
	"rotation" : -45
}
	},
					yAxis : {
						title : {
							text : ' '
						},"min":0
					},
					tooltip : {
						formatter : function() {
							return '<b>' + this.series.name + '</b><br/>'
									+ this.x + ': ' + this.y + '%';
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
<%=seriesBili_60.toString()%>
	});
			});
</script>
<script type="text/javascript">
	var chart10;
	$(document).ready(
			function() {
				chart10 = new Highcharts.Chart({
					chart : {
						renderTo : 'bl4',
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
<%=xAxisData.toString()%>,labels : {
	"step" : 1,
	"align" : "right",
	"rotation" : -45
}
	},
					yAxis : {
						title : {
							text : ' '
						},"min":0
					},
					tooltip : {
						formatter : function() {
							return '<b>' + this.series.name + '</b><br/>'
									+ this.x + ': ' + this.y + '%';
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
<%=seriesBili_90.toString()%>
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
					<div class="conright">
						<div id="main">
							<div class="bbox_con">
								<%@ include file="commons/date-form.jsp"%>
								<div class="statsTableHeader">
									<strong>回访用户数量</strong> 
									<span class="question fr"></span>
									<div class="clear"></div>
								</div>
								<div class="blockboxbg">
								<div class="blockbox">
								<div id="a0" class="bitem selbitem" onclick="changeTab(0,5,'a','ac','bitem','selbitem')">7天内回访</div>
								<div id="a1" class="bitem" onclick="changeTab(1,5,'a','ac','bitem','selbitem')">14天内回访</div>
								<div id="a2" class="bitem" onclick="changeTab(2,5,'a','ac','bitem','selbitem')">1个月内回访</div>
								<div id="a3" class="bitem" onclick="changeTab(3,5,'a','ac','bitem','selbitem')">2个月内回访</div>
								<div id="a4" class="bitem" onclick="changeTab(4,5,'a','ac','bitem','selbitem')">3个月内回访</div>
								</div>
								</div>
								<div id='ac0' class="line_chart_common"
									style="height: 450px; padding-bottom: 15px;"></div>
								<div id='ac1' class=""
									style="height: 450px; padding-bottom: 15px; display: none"></div>
									<div id='ac2' class=""
									style="height: 450px; padding-bottom: 15px; display: none"></div>
									<div id='ac3' class=""
									style="height: 450px; padding-bottom: 15px; display: none"></div>
									<div id='ac4' class=""
									style="height: 450px; padding-bottom: 15px; display: none"></div>
								<table border="0" class="benchmark_tips" cellpadding="0"
									cellspacing="0"
									style="margin: 0px auto 40px; padding-top: 5px;">
									<tbody>
										<tr>
											<td valign="top" align="right" width="130px;"><span>7天
													(或14天) 内回访:</span></td>
											<td>老用户，且在最近 (由昨日向前计算) 的7天 (或14天) 内启动过应用程序</td>

										</tr>
										<tr>
											<td valign="top" align="right"><span>蓝色折线:</span></td>
											<td>代表每日的新增用户</td>
										</tr>
										<tr>
											<td valign="top" align="right"><span>红色折线:</span></td>

											<td>代表当日的新增用户近期回访的情况，可以选择7天内回访或14天内回访</td>
										</tr>
										<tr>
											<td></td>
											<td>*
												由于最近7天(或14天)内的新增用户与回访用户是完全重合的，故上图中的回访用户只显示到7天(或14天)前</td>
										</tr>
									</tbody>
								</table>

								<div class="statsTableHeader">
									回访用户比例
								</div>
								<div style="margin: 10px 0;" class="blockboxbg">
									<div class="blockbox">
										<div class="bitem selbitem"
											onclick="changeTab(0,5,'b','bl','bitem','selbitem')" id="b0">7天内回访</div>
										<div class="bitem"
											onclick="changeTab(1,5,'b','bl','bitem','selbitem')" id="b1">14天内回访</div>
											<div class="bitem"
											onclick="changeTab(2,5,'b','bl','bitem','selbitem')" id="b2">1个月内回访</div>
											<div class="bitem"
											onclick="changeTab(3,5,'b','bl','bitem','selbitem')" id="b3">2个月内回访</div>
											<div class="bitem"
											onclick="changeTab(4,5,'b','bl','bitem','selbitem')" id="b4">3个月内回访</div>
									</div>
								</div>
								<div id='bl0' class=""
									style="height: 450px; padding-bottom: 15px;"></div>
								<div id='bl1' class=""
									style="height: 450px; padding-bottom: 15px; display: none"></div>
									<div id='bl2' class=""
									style="height: 450px; padding-bottom: 15px; display: none"></div>
									<div id='bl3' class=""
									style="height: 450px; padding-bottom: 15px; display: none"></div>
									<div id='bl4' class=""
									style="height: 450px; padding-bottom: 15px; display: none"></div>
									
								<table border="0" class="benchmark_tips" cellpadding="0"
									cellspacing="0"
									style="margin: 0px auto 40px; padding-top: 5px;">
									<tbody>
										<tr>
											<td valign="top" align="right" width="150px;"><span>7天
													(或14天) 内回访比例:</span></td>
											<td>7天 (或14天) 内回访用户/当日新增用户</td>

										</tr>
										<tr>
											<td valign="top" align="right"><span>举例说明:</span></td>
											<td>
												<p>某应用3月1日新增了100名用户，这100名用户在最近7天内有20人回访，在最近14天内有30人回访，那么其7天内的回访比例为20%，14天内的回访比例为30%</p>
												<p>* 一般说来，距离现在时间越久的老用户，回访率越低。您可以通过回访用户分析，观察到用户流失的规律</p>
											</td>

										</tr>
									</tbody>
								</table>
								
								
								
								<div class="statsTableHeader">留存用户</div>
          <table class="datatab long new_silver_table">
          <tbody>
            <tr class="first_tr">

              <th width="130">首次使用时间</th>
              <th width="50">新用户</th>
              <th colspan='8' class="last_th">
                <div class="fl" style="margin-left: 30px;width:300px;">
                 留存率 (%) ： 
                <div class="select_option fr select_short select_short_special select_retention_rates fc_1335497053385" style="z-index:998;z-index:990;">
  <div class="select_arrow fr"></div>
  <span class="selected_value fr" style="" param="type" val="week_retention">自然周</span>
  <div class="clear"></div>
  <div class="select_list_body" style="display:none;">
    <ul>
        <li val="week_retention" title="自然周" class='current' >
          <a >自然周</a>
        </li>
        <li val="month_retention" title="自然月"  >
          <a >自然月</a>
        </li>
    </ul>
  </div>
</div>


                </div>
              </th>
            </tr>
            <tr class="first_line">
              <th></th>
              <th></th>
              <td width='33' class="first_td">1周后</td>
              <td width='33'>2周后</td>
              <td width='33'>3周后</td>
              <td width='33'>4周后</td>
              <td width='33'>5周后</td>
              <td width='33'>6周后</td>
              <td width='33'>7周后</td>
              <td width='33'>8周后</td>
            </tr><c:set value="${beginDay}" var="toDay0"/>
            <c:forEach var="item" items="${dayslist}"  varStatus="status">
            <tr>
                <th>${item.time}</th>
                <th>${item.addCount}</th>
                  <%-- <td width='33' class="colorGrad2 first_td"><c:out
																	value="${fn:substring((item.visitCount_7/addTotalCount)*100,0,fn:indexOf((item.visitCount_7/addTotalCount)*100,'.')+2)}" />%</td>
                   --%>
                  <td width='33' class="colorGrad2 first_td"><fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitCount_7/item.addCount)*100}" />%</td>
                  <td width='33' class="colorGrad2 first_td"><fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitCount_14/item.addCount)*100}" />%</td>
                  <td width='33' class="colorGrad2 first_td"><fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitCount_21/item.addCount)*100}" />%</td>
                  <td width='33' class="colorGrad2 first_td"><fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitCount_28/item.addCount)*100}" />%</td>
                  <td width='33' class="colorGrad2 first_td"><fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitCount_35/item.addCount)*100}" />%</td>
                  <td width='33' class="colorGrad2 first_td"><fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitCount_42/item.addCount)*100}" />%</td>
                  <td width='33' class="colorGrad2 first_td"><fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitCount_49/item.addCount)*100}" />%</td>
                  <td width='33' class="colorGrad2 first_td"><fmt:formatNumber type="number" pattern="###.##"  value="${(item.visitCount_56/item.addCount)*100}" />%</td>
                  
                  <%-- <c:forEach items="${item.visitList}" var="item2" varStatus="vs">
                  <c:if test="${vs.last}"><c:set value="${vs.count}" var="cols"/></c:if>
														        <td width='33' class="colorGrad2 first_td"><fmt:formatNumber type="number" pattern="###.##"  value="${(item2.visitCount_7/item.addCount)*100}" />%</td>
														     </c:forEach> --%>
                  <%-- <c:if test="${cols<8}">
                  	<c:forEach var="i" begin="${cols}"
			end="7"
			step="1">
						<td width='33' class="colorGrad2 first_td"></td>
					</c:forEach>
                  </c:if> --%>
              </tr>
            
            
            </c:forEach>
            
              <!-- <tr>
                <th>2012-03-26 ~ 2012-04-01 </th>
                <th>1808</th>
                  <td width='33' class="colorGrad2 first_td">40.7</td>
                  <td width='33' class="colorGrad3 first_td">30.3</td>
                  <td width='33' class="colorGrad3 first_td">25.2</td>
                  <td width='33' class="colorGrad3 first_td">20.7</td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
              </tr>
              <tr>
                <th>2012-04-02 ~ 2012-04-08 </th>
                <th>1822</th>
                  <td width='33' class="colorGrad2 first_td">41.6</td>
                  <td width='33' class="colorGrad3 first_td">28.9</td>
                  <td width='33' class="colorGrad3 first_td">23.0</td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
              </tr>
              <tr>
                <th>2012-04-09 ~ 2012-04-15 </th>
                <th>1855</th>
                  <td width='33' class="colorGrad3 first_td">33.7</td>
                  <td width='33' class="colorGrad3 first_td">22.3</td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
                  <td width='33' class=" first_td"></td>
              </tr> -->
              </tbody>
          </table>
          <div style="margin-bottom:30px;"></div>
								
								
								
								
								
								
								
								
								

<div class="statsTableHeader">
		<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list=true')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list=true')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div>
		<strong>回访用户详情</strong>
	</div>
								<div style="padding-bottom: 10px;" class="bitemcon" >
									<div id="active_users_total_numbers" class="hidden">87990</div>
									<table cellspacing="0" cellpadding="0" border="0"
										class="datatab long new_silver_table"
										id="bartable_daily_active_users_devices">
										<tbody>
											<tr class="">
												<th class="new_silver_first_n" scope="col">日期</th>
												<th class="new_silver_first_n" scope="col">当日新增用户数</th>
												<th class="new_silver_first_n" scope="col">7天内回访用户数</th>
												<th class="new_silver_first_n" scope="col">7天内回访用户比例</th>
												<th class="new_silver_first_n" scope="col">14天内回访用户数</th>
												<th class="new_silver_first_n" scope="col">14天内回访用户比例</th>
												<!-- <th class="new_silver_first_n" scope="col">1个月内回访用户数</th>
												<th class="new_silver_first_n" scope="col">1个月内回访用户比例</th>
												<th class="new_silver_first_n" scope="col">2个也内回访用户数</th>
												<th class="new_silver_first_n" scope="col">2个月内回访用户比例</th>
												<th class="new_silver_first_n" scope="col">3个也内回访用户数</th>
												<th class="new_silver_first_n" scope="col">3个月内回访用户比例</th> -->
											</tr>
											<c:forEach items="${pagestats}" var="item" varStatus="status">
												<tr
													class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
													<td class="new_silver_first_n">${item.time}</td>
													<td class="new_silver_first_n">${item.addCount}<input type=hidden value="${item.addCount}" size="5" maxlength="25"  id="NEWSFER_${status.index}" class="NEWSFER"></td>
													<td class="new_silver_first_n">${item.visitCount_7}<input type=hidden value="${item.visitCount_7}" size="5" maxlength="25"  id="NEWSFER2_${status.index}" class="NEWSFER2"></td>
													<td class="new_silver_first_n"><c:if
															test="${item.addCount eq 0}">0%</c:if> <c:if
															test="${item.addCount!=0}">
															<c:out
																value="${fn:substring((item.visitCount_7/item.addCount)*100,0,fn:indexOf((item.visitCount_7/item.addCount)*100,'.')+2)}" />%
							</c:if>
														</td>
														<td class="new_silver_first_n">${item.visitCount_14}<input type=hidden value="${item.visitCount_14}" size="5" maxlength="25"  id="NEWSFER3_${status.index}" class="NEWSFER3"></td>
														<td class="new_silver_first_n"><c:if
																test="${item.addCount==0}">0%</c:if> <c:if
																test="${item.addCount!=0}">
																<c:out
																	value="${fn:substring((item.visitCount_14/item.addCount)*100,0,fn:indexOf((item.visitCount_14/item.addCount)*100,'.')+2)}" />%
							</c:if>
															</td>
															
															<%-- <td class="new_silver_first_n">${item.visitCount_30}<input type=hidden value="${item.visitCount_30}" size="5" maxlength="25"  id="NEWSFER4_${status.index}" class="NEWSFER4"></td>
														<td class="new_silver_first_n"><c:if
																test="${item.addCount==0}">0%</c:if> <c:if
																test="${item.addCount!=0}">
																<c:out
																	value="${fn:substring((item.visitCount_30/item.addCount)*100,0,fn:indexOf((item.visitCount_30/item.addCount)*100,'.')+2)}" />%
							</c:if>
															</td>
															
															<td class="new_silver_first_n">${item.visitCount_60}<input type=hidden value="${item.visitCount_60}" size="5" maxlength="25"  id="NEWSFER5_${status.index}" class="NEWSFER5"></td>
														<td class="new_silver_first_n"><c:if
																test="${item.addCount==0}">0%</c:if> <c:if
																test="${item.addCount!=0}">
																<c:out
																	value="${fn:substring((item.visitCount_60/item.addCount)*100,0,fn:indexOf((item.visitCount_60/item.addCount)*100,'.')+2)}" />%
							</c:if>
															</td>
															
															<td class="new_silver_first_n">${item.visitCount_90}<input type=hidden value="${item.visitCount_90}" size="5" maxlength="25"  id="NEWSFER6_${status.index}" class="NEWSFER6"></td>
														<td class="new_silver_first_n"><c:if
																test="${item.addCount==0}">0%</c:if> <c:if
																test="${item.addCount!=0}">
																<c:out
																	value="${fn:substring((item.visitCount_90/item.addCount)*100,0,fn:indexOf((item.visitCount_90/item.addCount)*100,'.')+2)}" />%
							</c:if>
															</td> --%>
												</tr>
											</c:forEach>
										
										</tbody>
									</table>
									<table>
									<tr>
											<td style="width:90px;">小计：</td>
											<td style="width:111px;"><span id='RECEIVE'></span></td>
											<td style="width:118px;"><span id='RECEIVE2'></span></td>
											<td style="width:132px;"></td>
											<td style="width:127px;"><span id='RECEIVE3'></span></td>
											<td style="width:142px;"></td>
											<!-- <td><span id='RECEIVE4'></span></td>
											<td></td>
											<td><span id='RECEIVE5'></span></td>
											<td></td>
											<td><span id='RECEIVE6'></span></td>
											<td></td> -->
										</tr>
									</table>
								</div>
										<script type="text/javascript">
$(document).ready(
   function(){
     basecolumns(".NEWSFER","#NEWSFER_","#RECEIVE");
     basecolumns(".NEWSFER2","#NEWSFER2_","#RECEIVE2");
     basecolumns(".NEWSFER3","#NEWSFER3_","#RECEIVE3");
     basecolumns(".NEWSFER4","#NEWSFER4_","#RECEIVE4");
     basecolumns(".NEWSFER5","#NEWSFER5_","#RECEIVE5");
     basecolumns(".NEWSFER6","#NEWSFER6_","#RECEIVE6");
   });

</script>
<%@ include file="commons/pager.jsp"%>
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
			<div class="clear"></div>
		</div>

	</div>
	<div id="question_wrap" style="display: none;">
	 <div class="question_content_top">
    <h3 class="title">
      <span class="fl"><b>回访用户分析</b>词汇表</span>
      <span class="fr tg_rss"><img alt="" src="<%=path%>/statics/images/report_subscribe_close_normal.png"></span>
      <div class="clear"></div>
    </h3>
  </div>
  <div class="question_content_center">
    <table cellpadding="0" cellspacing="0" border="0" width="775">
      <tbody><tr>
        <td class="title">7天内回访</td>
        <td>老用户，且在最近 (由昨日向前计算)7天内启动过应用程序</td>
      </tr>
      <tr class="question_even">
        <td class="title">7天内回访用户比例</td>
        <td>7天内回访用户/当日新增用户</td>
      </tr>
      <tr>
        <td class="title">14天内回访</td>
        <td>老用户，且在最近 (由昨日向前计算)14天内启动过应用程序</td>
      </tr>
      <tr class="question_even">
        <td class="title">14天内回访用户比例</td>
        <td>14天回访用户/当日新增用户</td>
      </tr>
      <tr>
        <td class="title">举例说明</td>
        <td>某应用3月1日新增了100名用户，这100名用户在最近7天内有20人回访，在最近14天内有30人回访，那么其7天内的回访比例为20%，14天内的回访比例为30% <br> 一般说来，距离现在时间越久的老用户，回访率越低。您可以通过回访用户分析，观察到用户流失的规律</td>
      </tr>
    </tbody></table>
  </div>
  <div class="question_content_bottom"></div>
  </div>
	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>