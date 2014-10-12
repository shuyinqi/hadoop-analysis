<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/tzSelect/jquery.tzSelect.css" media="screen" />
<script type="text/javascript" src="<%=basePath%>statics/tzSelect/jquery.tzSelect.js"></script>
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
	<input type="hidden" id="week_time" value="${lastWeek1}"/>
	<div class="not_has_son_menu">
		<div class="accordion">
			<h2 class="l_m_controls ">用户反馈</h2>
			<div class="pane_con">
				<ul>
					<li class="l_m_controls current">用户反馈</li>
				</ul>
			</div>
		</div>
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
	//根据当前导航菜单，取得当前连接的外围，将其显示
	jQuery(".accordion .l_m_controls").removeClass("current");
	var a_c = jQuery(".b_leftmenu .accordion").find("a.current");
	jQuery(a_c).parent().parent().parent().show();
	jQuery(a_c).parent().parent().parent().prev(".l_m_controls").addClass("current");

	var option_l = jQuery('select.ltselect option').length;
	if (option_l > 0) {jQuery('select.ltselect').tzSelect();};
});
</script>