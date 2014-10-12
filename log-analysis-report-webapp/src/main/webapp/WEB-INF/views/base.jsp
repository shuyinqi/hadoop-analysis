<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>

<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<title>基本统计统计 | ${app.name}</title>
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
						<li><a href="#" id="module_tab">组件</a></li>
						</li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear">&nbsp;</div>
				<div class="b_right_public">
					<div class="conright">
						<div id="main">
							<h3 class="title" style="display: none;">
								<span class="text fl">${app.name} App</span> <span
									class="sub_text fl"><span class="days">统计第<span
										class="daynums">&nbsp;604&nbsp;</span>天</span>
								</span>
								<div class="clear"></div>
							</h3>
							<%@ include file="/WEB-INF/views/base-bbox.jsp"%>
						</div>
						<div class="bs_bottom">
							<div class="bs_bl"></div>
							<div class="bs_br"></div>
						</div>
					</div>
				</div>
			</div>

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