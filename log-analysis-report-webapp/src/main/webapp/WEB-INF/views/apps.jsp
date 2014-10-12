<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>

<title>所有产品 | Mapbar运营平台 Beta2.0</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/main/b_home.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/main/jquery.qtip.css" media="screen" />

<script type="text/javascript"
	src="<%=basePath%>statics/js/main/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>statics/js/main/jquery.qtip.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>statics/css/main/develop_center.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>statics/css/main/develop_guide.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>statics/css/main/chili_salon.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>statics/css/main/b_apps.css" media="screen" />

<script type="text/javascript"
	src="<%=basePath%>statics/js/main/jquery.tablesorter.2.0.js"></script>
<script type="text/javascript"
	src="<%=basePath%>statics/js/main/jquery-easing-1.3.pack.js"></script>
<script type="text/javascript"
	src="<%=basePath%>statics/js/main/jquery-easing-compatibility.1.2.pack.js"></script>
<script type="text/javascript"
	src="<%=basePath%>statics/js/main/coda-slider.1.1.1.pack.js"></script>
<script type="text/javascript"
	src="<%=basePath%>statics/js/main/content_scroll.js"></script>
<script type="text/javascript"
	src="<%=basePath%>statics/js/main/salon.js"></script>
</head>

<body class="product_home">

	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>
		<style type="text/css">
#salonForm_form .status {
	width: 68px;
}

#salonForm_wrap input.in {
	width: 220px;
}

.switch {
	height: 30px;
	width: 150px;
	background-image: url('/statics/images/main/report_switch_bg_r.png');
	float: left
}

a.switch-link {
	display: block;
	width: 59px;
	float: left;
	line-height: 28px;
	font-size: 14px;
	padding: 0 6px 0 10px;
	font-weight: normal;
}

a.switch-link.active,a.switch-link.active:hover {
	color: #000000;
}

a.switch-link.inactive,a.switch-link.inactive:hover {
	color: #999999;
}

.set-default {
	float: left;
	height: 30px;
	line-height: 30px;
	font-size: 12px;
	font-weight: normal;
	margin-left: 10px;
}

.set-default a {
	color: #999999;
}

.b_chart {
	padding: 15px;
	height: 325px;
	background-color: #efefef;
	border-left: 1px solid #CACACA;
	border-right: 1px solid #CACACA;
	border-bottom: 1px solid #CACACA;
	display: none;
}

.b_chart_show {
	background-color: #efefef;
	padding-top: 10px;
	border-left: 1px solid #CACACA;
	border-right: 1px solid #CACACA;
	border-bottom: 1px solid #CACACA;
	height: 25px;
	padding-left: 15px;
}

.b_chart_show .tip {
	background: url('/statics/images/main/b_chart_show_down.png') no-repeat;
	padding-left: 25px;
	width: 100px;
	height: 15px;
	line-height: 15px;
	color: #19456f;
	cursor: pointer;
}

.b_chart_tab {
	float: left;
	width: 148px;
	/*height:40px;
	line-height:20px;*/
}

.b_chart_con {
	float: right;
	height: 280px;
	width: 710px;
	background-image: url('/statics/images/main/b_chart_block.jpg');
	padding: 15px;
}

.b_chart_con_title {
	padding-top: 10px;
	text-align: center;
	font-weight: bold;
}

.bitem {
	font-size: 13px;
	width: 100%;
	padding: 13px 10px;
	margin-bottom: 10px;
	text-align: center;
	cursor: pointer;
	background-color: #efefef;
}

.selbitem {
	font-weight: bold;
	color: #2a5295;
	background: url('/statics/images/main/b_chart_tab.png') no-repeat;
}

.b_chart_time {
	width: 910px;
	height: 18px;
	padding: 0 5px 5px 5px;
}

.b_chart_time a {
	font-size: 12px;
	color: #2a5295;
}

.b_chart_time .time {
	float: left;
	padding-left: 190px;
	text-align: right;
}

.b_chart_time .time a.current {
	color: #000000;
	font-weight: bold;
	text-decoration: none;
}

.b_chart_time .output {
	float: left;
	margin-left: 400px;
	text-align: right;
}
.repeat {
    float: right;
	margin-top: 30px;
	color: #000000;
	text-align: right;
	font-size: 12px;
}
</style>

		<div id="b_center">
			<br />
			<br />
			<div class="demonotice">欢迎使用 Mapbar运营平台 Beta2.0</div>
			<div id="b_pro_status_home" class="b_pro_status b_apps_top">
				<h3 class="title">
					<span class="title fl" style="height: 30px; line-height: 30px">所有产品</span>
					</span> <span class="add fr"> <a href="javascript:alert('暂时不支持新应用添加/管理，添加请与技术人员沟通。');">添加新应用</a>
					</span>
					<div class="clear"></div>
				</h3>
				<div class="clear"></div>
			</div>


			<div class="b_homeconten">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="tablesorter">
					<thead>
						<tr>
							<th class="first header" title="system" width="6%">平台&nbsp;</th>
							<th title="appname" class="header">应用名/应用ID</th>
							<th title="usercount" width="8%" class="header">总用户数</th>
							<th title="newuser" width="14%" class="header">新增用户[今日/昨日]</th>
							<th title="chang_count" width="14%" class="header">启动用户[今日/昨日]</th>
							<th title="startontoday" width="14%" class="header">启动次数[今日/昨日]</th>
							<th class="last header" title="seechar" width="18%">查看报表</th>
							<!-- <th class="header" title="usercount" width="8%">重复用户数</th> -->
						</tr>
					</thead>
					<tbody>
					<c:forEach var="app" items="${apps }" varStatus="status">
						<tr class="${status.index%2==0 ? 'odd_number':'even_number'}" >
							<td class="first system"><img alt="Android" title="Android"
								width="20" src="<%=basePath%>statics/images/main/aa.png" /></td>
							<td class="appname"><div class="app_name_w">
									<a href="<%=basePath%>apps/${app.id }/base?${lastMonth1}" class="click app_n" id="reports_tab">${app.name } / ${app.id }</a>
								</div></td>
							<td class="usercount">${app.totalUser }</td>
							<td class="newuser">${app.multipleNewUserCount }</td>
							<td class="chang_count">${app.multipleLaunchUserCount }</td>
							<td class="startontoday">${app.multipleLaunchCount }</td>
							<td class="last seechar"><span class="fl char_01"><a
									href="<%=basePath%>apps/${app.id }/base?${lastMonth1}"
									class="click " id="">统计</a> </span> 
								
							</td>
							<!-- <td class="last seechar">${ repeatApps.launchUserCount }<div class="clear"></div></td>  -->
							
						</tr>
					</c:forEach>
                  			 
					
						<!-- 
						<tr class="even_number">
							<td class="first system"><img alt="iOS" title="iOS"
								src="<%=basePath%>statics/images/main/aa.png" width="20" /></td>
							<td class="appname"><div class="app_name_w">
									<a href="<%=basePath%>apps/1000/base" class="click app_n"
										id="reports_tab">iPhone Demo App</a>
								</div></td>
							<td class="usercount">16832</td>
							<td class="newuser">0 / 2</td>
							<td class="chang_count">0 / 3</td>
							<td class="startontoday">0 / 3</td>
							<td class="last seechar"><span class="fl char_01"><a
									href="<%=basePath%>apps/b2700046c68c14d1744d41c4/base"
									class="click " id="">统计</a> </span>
								<div class="clear"></div>
							</td>
						</tr> -->
					</tbody>
				</table>
				 <div class="clear"></div>
				  <div class="repeat">总用户数：${repeatApps.totalUser }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;独立用户数:${repeatApps.launchCount }</div>		
				<div class="fl b_hp_edit">
					<a href="">编辑应用</a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>

	<input id="loginlog" type="hidden" value="" />

	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>