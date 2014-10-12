<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="shortcut icon" href="<%=basePath%>statics/images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/layout.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/style.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/salon.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/milk_salon.css" media="screen"/>

<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/skin/ui.dynatree.css" media="screen"/>

<script type="text/javascript" src="<%=basePath%>statics/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/rails.js"></script>

<script type="text/javascript" src="<%=basePath%>statics/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/umeng-terminologies.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/chart.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/highcharts.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/main/jquery.tablesorter.2.0.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/main/jquery.tablesorter.pager.js"></script>

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
</script>

