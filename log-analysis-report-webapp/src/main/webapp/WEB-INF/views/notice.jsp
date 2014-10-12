<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>

<title>公告管理 | ${app.name}</title>
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
								<strong>公告设置</strong><span class="question fr"></span>
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
														
														
														for (var j=0 ; j <n1 ; j++){
													  		var cc=jQuery.trim($("#app"+j).val());
													  		var nn=0;
													  		$(".checkes"+cc).each(function(){
																if($(this).is(':checked')==true){
																	nn++;
																}
															});
													  		if(nn>3){alert("每个app最多只能选择3条公告，请重新选择！");return false;}
													  	}
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

								function submMd2(params, param2) {
									$(".vs").each(function(){
										$(this).addClass("hidden");
									});
									$("#noteid").removeClass("hidden");
									$("#note").removeClass("hidden");
									$("#appid").removeClass("hidden");
									$("#modifyid").val(params);
									$("#uname").val(param2);
									$("#userid").val(params);
									$("#addname").val('');$("#modifyView").val('');
								};
								
								function subAdd2(params) {
									$(".vs").each(function(){
										$(this).addClass("hidden");
									});
									$("#appid").removeClass("hidden");
									$("#noteid").removeClass("hidden");
									$("#note").removeClass("hidden");
									$("#addname").val(params);
									$("#uname").val('');$("#modifyid").val('');$("#modifyView").val('');
								};
								function subDel2(params) {
									$("#delid").val(params);
									window.location.href = trimUrl(window.location.href) + "?del="
									+params;
								};
								function modifyView(){
									$("#noteid").addClass("hidden");
									$("#note").addClass("hidden");
									$("#appid").addClass("hidden");
									$("#addname").val('');$("#uname").val('');$("#modifyid").val('');
									$("#modifyView").val('true');
									$(".vs").each(function(){
										$(this).removeClass("hidden");
									});
								}
							</script>
							<form id="formu" name="formu">
							<div class="ccon">

								<div id="mid">
									
									<input type="hidden" name="modifyid" id="modifyid" value=""></input>
									<input type="hidden" name="addname" id="addname" value=""></input>
									<input type="hidden" name="userid" id="userid" value=""></input>
									<input type="hidden" name="modifyView" id="modifyView" value=""></input>
		<div  id="appid" class="statsTableHeader hidden" >
									<div  class="selbox">
			app应用选择：<c:forEach items="${apps}" var="item" varStatus="status">
			<input type="hidden" id="appindex<c:out value="${status.last}"/>" value="<c:out value="${status.count}"/>"></input>
											<input type="radio" id="app${status.index}" name="myapp" class="check" 
											<c:if test="${status.index==0}">checked="true"</c:if>
												value="${item.id}">${item.name}</input>
										</c:forEach>
			</div>
		<div class="clear"></div>
		</div>
		
		<div class="statsTableHeader hidden" id="noteid">
		<strong>公告信息修改：</strong>
		<div class="clear"></div>
	</div>
		<div id="note" class="hidden">
			<textarea cols="50" rows="4" id="uname" name="username" value="" class=""></textarea><br/>
			<input class="upbtn" type="submit" value="提  交"></input>
		</div>
									
								</div>
								<div>
								<table class="new_silver_table" width="100%" border="0"
									cellspacing="0" cellpadding="">
									<thead>
										<tr>
											<th scope="col" class="vs hidden">选择</th>
											<th scope="col" class="new_silver_first_n">编号</th>
											<th scope="col">app名<img alt="Info_tip"
												class="term-tool-tip" data-term="launches" height="16"
												src="<%=basePath%>statics/images/info_tip.png" width="16">
											</th>
											<th>公告内容</th>
											<th ><!--  class="new_silver_first_n" -->操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${noticesall}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'} ${status.first?' today':''}">
												<td class="vs hidden"><input type="checkbox" <c:if test="${item.flag==1}">checked="true"</c:if> id="checkview${status.index}" name="checkview" class="checkes${item.appid}" value="${item.id}"></input><img
													alt="Info_tip" class="term-tool-tip" data-term="today_stat"
													height="16" src="<%=basePath%>statics/images/info_tip.png"
													width="16">
												</td>
												<td class="new_silver_first_n">${item.id}<img
													alt="Info_tip" class="term-tool-tip" data-term="today_stat"
													height="16" src="<%=basePath%>statics/images/info_tip.png"
													width="16">
												</td>
												<td>${item.name}</td>
												<td>${item.note}</td>
												<td><a
													href="javascript:subDel2('${item.id}')"
													id="w1">删除</a>&nbsp;&nbsp;<a
													href="javascript:submMd2('${item.id}','${item.note}')"
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
									<div style="margin-bottom: 30px;"><a
													href="javascript:modifyView()"
													id="w1">修改显示规则</a>&nbsp;&nbsp;&nbsp;&nbsp;<input class="upbtn" type="submit" value="提  交"></input></div>
									<div style="margin-bottom: 30px;"></div>
									</div>
									</form>
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