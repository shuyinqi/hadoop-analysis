<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/tzSelect/jquery.tzSelect.css" media="screen" />
<script type="text/javascript" src="<%=basePath%>statics/tzSelect/jquery.tzSelect.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/jquery.tools.min.js"></script>

<div class="fl b_leftmenu">
	<div class="b_product_sel">
		<select class="ltselect" onchange="showSelect(this.value)">
<c:forEach items="${apps}" var="item">
	<c:if test="${app.id==item.id}"><option value="${item.id}" selected="selected">${item.name}</option></c:if>
	<c:if test="${app.id!=item.id}"><option value="${item.id}">${item.name}</option></c:if>
</c:forEach>
		</select>
		<div class="b_backtoapp">
			<a href="<%=basePath%>apps"><span class="fl"><pre>«</pre></span> 返回我的产品首页</a>
			<div class="clear"></div>
		</div>
	</div>
	<div class="not_has_son_menu">
		<div class="accordion">
		<c:forEach items="${usermenus}" var="item" varStatus="status">
			<c:if test="${item.flevel==1}">
			<h2 class="l_m_controls current">${item.name}</h2>
			
			<div class="pane_con">
				<ul>
				
				<c:forEach items="${usermenus}" var="item2" varStatus="status">
					<c:if test="${item2.flevel==2}">
					<c:if test="${item.id==item2.fatherid}">
					<c:if test="${item2.fatherid!=17}">
					<li><a href="
					<c:choose>
					<c:when test="${item2.flag==1}">${item2.link}</c:when>
					<c:when test="${item2.flag==0}"><%=basePath%>apps/${app.id}/${item2.link}${lastWeek1}</c:when>
					<c:when test="${item2.flag==2}"><%=basePath%>apps/${app.id}/${item2.link}${lastMonth1}</c:when>
					</c:choose>
					" class="${method == item2.fn ? 'current':'click'}">${item2.name}</a></li>
					</c:if>
					<c:if test="${item2.fatherid==17}">
						<c:forEach items="${events}" var="item3" varStatus="status">
						<c:if test="${item2.fn==item3.name}">
					<li><a href="
					<c:choose>
					<c:when test="${item2.flag==1}">${item2.link}</c:when>
					<c:when test="${item2.flag==0}"><%=basePath%>apps/${app.id}/${item2.link}${lastWeek1}</c:when>
					<c:when test="${item2.flag==2}"><%=basePath%>apps/${app.id}/${item2.link}${lastMonth1}</c:when>
					</c:choose>
					" class="${method == item2.fn ? 'current':'click'}">${item2.name}</a></li>
						</c:if>
						</c:forEach>
						</c:if> 
					</c:if>
					</c:if>
				</c:forEach>
				
				</ul>
			</div>
			</c:if>
		</c:forEach>
			
			<!-- <h2 class="l_m_controls">设置</h2>
			<div class="pane_con">
				<ul>
					<li><a class="disabled-link h2-tit">编辑应用</a></li>
					<li><a class="disabled-link h2-tit">发送策略</a></li>
					<li><a class="disabled-link h2-tit">编辑自定义事件</a></li>
					<li><a class="disabled-link h2-tit">渠道管理</a></li>
				</ul>
			</div> -->
		</div>
	</div>
	<div class="kong35"></div>
	<div class="b_sdk_down">
<!--<strong>Appkey: &nbsp;</strong><div class="t_appkey_v">4c14d4471d41c86c6400072b</div>-->
		<!-- <h3>
			<a href="#" target="_blank" class="android_a" onclick="javascript: window.open('download/Analytics_Android_SDK_3.1.1.zip');"></a>
			<span class="sdk_info">版本: 3.1.1&nbsp;&nbsp;&nbsp;更新日期: 2012-01-04</span>
		</h3> -->
	</div>
</div>

<script type="text/javascript">
jQuery(document).ready(function() {
	//左侧菜单切换
	jQuery.tools.tabs.addEffect("slide", function(i, done) {
	  this.getPanes().slideUp().css({backgroundColor: "#fff"});
	  this.getPanes().eq(i).slideDown(function() {
	    jQuery(this).css({backgroundColor: 'transparent'});
	    done.call();
	  });
	});
	jQuery(".accordion").tabs(".accordion div.pane_con", {tabs: 'h2.l_m_controls', effect: 'slide', initialIndex: null});
	
	jQuery(".accordion .l_m_controls").removeClass("current");
	//根据当前导航菜单，取得当前连接的外围，将其显示
	jQuery(".accordion .l_m_controls").removeClass("current");
	var a_c = jQuery(".b_leftmenu .accordion").find("a.current");
	jQuery(a_c).parent().parent().parent().show();
	jQuery(a_c).parent().parent().parent().prev(".l_m_controls").addClass("current");

	//自定义事件中，鼠标滑过更多时的js
	jQuery(".pane_con > ul > li").hover(function() {
		jQuery(this).addClass("selected");
		jQuery(this).children("a:eq(0)").addClass("h2-tit");
		jQuery(this).children("div").show();
	}, function() {
		jQuery(this).removeClass("selected");
		jQuery(this).children(".tit").removeClass("h2-tit");
		jQuery(this).children("div").hide();
	});

	var option_l = jQuery('select.ltselect option').length;
});
</script>

