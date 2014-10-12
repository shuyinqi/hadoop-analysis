<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<link rel="shortcut icon" href="<%=basePath%>statics/images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/layout.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/style.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/salon.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/milk_salon.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/tablesorter.css" />

<script type="text/javascript" src="<%=basePath%>statics/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/rails.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/form.validator.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/jquery.validate.1.8.0.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/umeng-terminologies.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/chart.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/highcharts.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/main/jquery.tablesorter.2.0.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/main/jquery.tablesorter.pager.js"></script>

<script type="text/javascript" src="<%=basePath%>statics/js/switchHeader.js"></script>

<script type="text/javascript">
var cookies_setter = {
		get: function (k) {
			var h = document.cookie.split("; "), g = h.length, f = [];
			for (var j = 0; j < g; j++) {
			  f = h[j].split("=");
			  if (k === f[0]) return unescape(f[1]);
			}
			return null;
		},
		set: function (f, g) {
			var h = new Date();
			h.setDate(h.getDate() + 1);
			document.cookie = f + "=" + escape(g) + "; expires=" + h.toGMTString();
		}
	};
var getChart = function(renderTo,title,categories,step,values,doformat){
	seriesType="line";//line,spline, scatter, splinearea bar,pie,area,column
	//categories横轴,values图表内数值
	var options = {
		chart: {"renderTo":renderTo,"defaultSeriesType":seriesType,"animation":false},
		title: {"text":title},
		legend: {"margin":25,"enabled":true},
        xAxis: {"categories":categories,"labels":{"step":step,"align":"right","rotation":-45}},
        yAxis: {"title":"","tickPixelInterval":50,"min":0},
		tooltip:  {"enabled":true},
        credits: {"enabled":false},
        plotOptions: {"area":{"stacking":"normal"}},
        series: values, 
        subtitle: {}
	};
//Add callbacks (non-JSON compliant)
	options.tooltip.formatter = doformat;
	return new Highcharts.Chart(options);
};
</script>
