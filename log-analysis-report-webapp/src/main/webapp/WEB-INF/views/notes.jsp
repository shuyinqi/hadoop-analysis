<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>

<title>渠道备注管理 | ${app.name}</title>
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
								<strong>备注设置</strong><span class="question fr"></span>
								<div class="clear"></div>
							</div>

							<script type="text/javascript">
								$(function() {
									$("#formu").submit(
													function() {
														// Serialize standard form fields:
														var formData = $(this).serializeArray();
														var n1=$(".check").size();
														if(n1<=0){alert("请选择app应用");return false;}
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

								function submMd2(params, param2,param3) {
									$("#noteid").removeClass("hidden");
									$("#channelid").addClass("hidden");
									$("#appid").addClass("hidden");
									$("#modifyid").val(params);
									$("#unote").val(param2);
									$("#userid").val(params);
									$("#uname").val(param3);
									$("#addname").val('');
								};
								
								function subAdd2(params) {
									$("#channelid").removeClass("hidden");
									$("#appid").removeClass("hidden");
									$("#noteid").removeClass("hidden");
									$("#addname").val(params);
								};
								function subDel2(params) {
									$("#delid").val(params);
									window.location.href = trimUrl(window.location.href) + "?del="
									+params;
								};
							</script>
							<div class="ccon">

								<div id="mid">
									<form id="formu" name="formu">
									<input type="hidden" name="modifyid" id="modifyid" value=""></input>
									<input type="hidden" name="addname" id="addname" value=""></input>
									<input type="hidden" name="userid" id="userid" value=""></input>
		
		<div  id="appid" class="statsTableHeader hidden" >
									<div  class="selbox">
			app应用选择：<c:forEach items="${apps}" var="item" varStatus="status">
			<input type="hidden" id="appindex<c:out value="${status.last}"/>" value="<c:out value="${status.count}"/>"></input>
											<input type="checkbox" id="app${item.id}" name="myapp" class="check"
												value="${item.id}">${item.name}</input>
										</c:forEach>
			</div>
		<div class="clear"></div>
		</div>
		
		<div  id="channelid" class="statsTableHeader hidden" >
									<div  class="selbox">
			渠道类型选择：<input type="radio" id="channel0" name="type" class="type"
												value="market">market</input>
						<input type="radio" id="channel1" name="type" class="type"
												value="pre_installed">pre_installed</input>
			</div>
		<div class="clear"></div>
		</div>
		
		<div class="statsTableHeader hidden" id="noteid">
		<strong>渠道信息修改：</strong>
			<span>渠道名:</span><input type="text" id="uname" name="username" value="" class="datainp"/>
			<span>备注:</span><input type="text" id="unote" name="usernote" value="" class="datainp"/>
			<input class="upbtn" type="submit" value="提  交"></input>
		<div class="clear"></div>
	</div>
		
									</form>
								</div>
								<div>
								<table class="new_silver_table" width="100%" border="0"
									cellspacing="0" cellpadding="">
									<thead>
										<tr>
											<th scope="col" class="new_silver_first_n">渠道类型</th>
											<th scope="col">渠道名</th>
											<th scope="col">app名<img alt="Info_tip"
												class="term-tool-tip" data-term="launches" height="16"
												src="<%=basePath%>statics/images/info_tip.png" width="16">
											</th>
											<th>备注</th>
											<th ><!--  class="new_silver_first_n" -->操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${noticesall}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'} ${status.first?' today':''}">
												<td class="new_silver_first_n">${item.type}</td>
												<td>${item.name}</td>
												<td>${item.appname}</td>
												<td>${item.note}</td>
												<td><a
													href="javascript:subDel2('${item.id}')"
													id="w1">删除</a>&nbsp;&nbsp;<a
													href="javascript:submMd2('${item.id}','${item.note}','${item.name}')"
													id="w1">修改</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									</table>
									</div>
									<div style="margin-bottom: 30px;"><a
													href="javascript:subAdd2('yes')"
													id="w1">增加</a></div>
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
									<div class="clear"></div>
									</div>
</div>
									<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>