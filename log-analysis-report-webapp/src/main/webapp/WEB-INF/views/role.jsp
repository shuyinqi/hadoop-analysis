<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mapbar.analyzelog.report.entity.MenuVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>

<title>角色管理 | ${app.name}</title>
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
							<div class="statsTableHeader">
								<strong>角色菜单权限设置</strong><span class="question fr"></span>
								<div class="clear"></div>
							</div>

							<script type="text/javascript">
								$(function() {
									$("#formu")
											.submit(
													function() {
														// Serialize standard form fields:
														var formData = $(this).serializeArray();
	$.post(window.location.href,
			   formData,
			   function(response, textStatus, xhr){
		/* alert("Modify returned " + textStatus); */
		window.location.href=trimUrl(window.location.href);
			   }
		);
														return false;
													});
								});

								var submMd = function(params) {
									$("#mid").removeClass();
									$("#addname").val("addname");
									/* $("#role" + param2).attr("checked",true);;
									$("#uname").val(param3);
									$("#uid").val(params); */
								};
								
								var subDel = function(params) {
									$("#delid").val(params);
									window.location.href = trimUrl(window.location.href) + "?del="
									+params;
								};
								var subMod = function(params,params2) {
									$("#mid").removeClass();
									$("#modifyid").val(params);
									$("#uname").val(params2);
									$("#addname").val('');
								};
								var forward = function(params) {
									$("#delid").val(params);
									window.location.href = trimUrl(window.location.href).substring(0,trimUrl(window.location.href).lastIndexOf("/"))+"/function?roleid="+params;
								};
							</script>
							<div class="ccon">

								<div id="mid" class="hidden">
									<form id="formu" name="formu">
									<input type="hidden" name="modifyid" id="modifyid" value=""></input>
									<input type="hidden" name="addname" id="addname" value=""></input>
									<div class="statsTableHeader">
									<div class="mnum" >
		<strong>角色名:</strong>
		<div style="color: #444" class="mnum">
			<input type="text" id="uname" name="username" value="" class="datainp"/><input type="submit" value="提  交" class="upbtn">
		</div>
		</div>
		<div class="clear"></div>
			</div>						
											
									</form>
								</div>
								<table class="new_silver_table" width="100%" border="0"
									cellspacing="0" cellpadding="">
									<thead>
										<tr>
											<th scope="col" class="new_silver_first_n">角色</th>
											<th scope="col">编号<img alt="Info_tip"
												class="term-tool-tip" data-term="launches" height="16"
												src="<%=basePath%>statics/images/info_tip.png" width="16">
											</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									<form id="formdel" name="formdel"><input type="hidden" name="del" id="delid" value=""></input>
										<c:forEach items="${roles}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'} ${status.first?' today':''}">
												<td class="new_silver_first_n">${item.name}<img
													alt="Info_tip" class="term-tool-tip" data-term="today_stat"
													height="16" src="<%=basePath%>statics/images/info_tip.png"
													width="16">
												</td>
												<td>${item.id}</td>
												<td><%-- <a
													href="javascript:subDel('${item.id}')"
													id="w1">删除</a> --%><a
													href="javascript:subMod('${item.id}','${item.name}')"
													id="w1">修改</a>
													<a
													href="javascript:forward('${item.id}')"
													id="w1">权限设置</a></td>
											</tr>
										</c:forEach>
										</form>
									</tbody>
									</table>
									</div>
									<div style="margin-bottom: 30px;"><a
													href="javascript:submMd()"
													id="w1">增加</a></div>
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
									<div class="clear"></div>
									</div>
</div>
									<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>