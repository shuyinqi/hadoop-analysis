<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
	<%@ include file="commons/meta.jsp"%>
	<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
    <title>Android Demo App 回访用户分析</title>
</head>

<body>
    <div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>

          <div id="b_center">
 		<%@ include file="/WEB-INF/views/body-leftmenu.jsp"%>

<div class="fr b_rightconten">
	<div class="fl b_right_menu">
		<ul>
			<li><a href="#" class="click current " id="reports_tab">统计分析</a></li>
				<li><a href="#" id="module_tab">组件</a></li>
			<li><a href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/error_types" class="click " id="developer_tools_tab">开发工具</a></li>
			<div class="clear"></div>
		</ul>
	</div>
	<span class="fr b_docu_link"><a href="http://www.umeng.com/doc/home.html" target="_blank">文档中心</a></span>
	<div class="clear">&nbsp;</div>
	<div class="b_right_public">
		<div class="conright">
			<div id="main">
				<h3 class="title" style="display:none;">
					<span class="text fl">Android Demo App</span>
					<span class="sub_text fl"><span class="days">统计第<span class="daynums">&nbsp;604&nbsp;</span>天</span></span>
					<div class="clear"></div>
				</h3>
<%@ include file="/WEB-INF/views/a-retention-bbox.jsp"%>
			</div>
			<div class="bs_bottom"><div class="bs_bl"></div><div class="bs_br"></div></div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/a-retention-question.jsp"%>


<script type="text/javascript">
  $(function(){
    var $rententionDetails = $('#retention_stats_details');
    var initialStatsDetailsUrl = window.location.href;
    initialStatsDetailsUrl = initialStatsDetailsUrl.replace("/reports/retention", "/reports/retention_stats_details");
    $rententionDetails.load(initialStatsDetailsUrl);
    $("div.digg_pagination a").live('click', function(e){
		var $target = $(e.target);
		var link = $target.attr("href");
		$('<img>', {
			src : '/images/ajax-loader.gif',
			style : 'float: right; width:20px;margin-right:10px;margin-top:10px;'} 
		).insertAfter("div.digg_pagination");
		$.get(link, {}, function(response) { $rententionDetails.html(response); });
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