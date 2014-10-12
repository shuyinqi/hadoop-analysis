<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>

<title>设备自定义统计 | ${app.name}</title>
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

								function submMd2(params, param2,param3,param4) {
									$("#noteid").removeClass("hidden");
									$("#channelid").removeClass("hidden");
									$("#modifyid").val(params);
									
									$("#userid").val(params);
									$("#uname").val(param2);
									$("#unote").val(param3);
									$("#ufn").val(param4);
									$("#addname").val('');
								};
								
								function subAdd2(params) {
									$("#channelid").removeClass("hidden");
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
		
		
		<div class="statsTableHeader hidden" id="noteid">
		<strong>渠道信息修改：</strong>
			<span>品牌名:</span><input type="text" id="uname" name="brand" value="" class="datainp"/>
			<span>型号名:</span><input type="text" id="unote" name="device" value="" class="datainp"/>
			<span>统计品牌名:</span><input type="text" id="ufn" name="fn" value="" class="datainp"/>
	</div>
			<div  id="channelid" class="statsTableHeader hidden" >
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
											<th scope="col">品牌名</th>
											<th scope="col">型号名<img alt="Info_tip"
												class="term-tool-tip" data-term="launches" height="16"
												src="<%=basePath%>statics/images/info_tip.png" width="16">
											</th>
											<th>统计品牌名</th>
											<th ><!--  class="new_silver_first_n" -->操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${noticesall}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'} ${status.first?' today':''}">
												<td>${item.brand}</td>
												<td>${item.device}</td>
												<td>${item.fn}</td>
												<td><a
													href="javascript:subDel2('${item.id}')"
													id="w1">删除</a>&nbsp;&nbsp;<a
													href="javascript:submMd2('${item.id}','${item.brand}','${item.device}','${item.fn}')"
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
									<%@ include file="commons/pager.jsp"%>
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