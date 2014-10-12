<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mapbar.analyzelog.report.entity.MenuVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<% 
Object addful=request.getAttribute("addfull");
%>
<script type="text/javascript">
var tmp_add='<%=addful%>';
</script>
<title>用户角色管理 | ${app.name}</title>
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
				if($('#addname').get(0).value!=''){
				if($('#uname').get(0).value==''||$('#upwd').get(0).value==''){
					alert("用户名和密码不能为空");
					return false;
				}}else{
					if($('#uname').get(0).value==''){
						alert("用户名不能为空");
						return false;
					}
				}
				var c=0;
			    var n=$(".roleraios").size();
			    c=$("input[type='radio']:checked").val();
			    if(c==undefined){alert("请选择角色！");return false;}
				
$.post(window.location.href,
		   formData,
		   function(response, textStatus, xhr){
		   if(tmp_add!=undefined&&tmp_add=='1'){
			   alert("用户名重复！");
		   }
			   window.location.href=trimUrl(window.location.href);
		   }
	);
					return false;
			});
});
			//修改按钮
			function submMd(params, param2, param3, param4,param5,param6,fn) {
				$("#roid").removeClass("hidden");
				$("#flagid").removeClass("hidden");
				$("#appid").removeClass("hidden");
				var applength=$('#appindextrue').get(0).value;
				for(var j=0;j<applength;j++){
					$("#app"+j).attr("checked",false);
				}
				
				$("#vpwd").addClass("hidden");
				$("#mid").removeClass();
				
				console.log(fn);
				$("#choiceRole>input").each(function(i){
					if($(this).val()==param2){
						$(this).attr("checked",true);
					}
					console.log();
				});
				
				$("#uname").val(param3);
				$("#userid").val(params);
				$("#flag"+param4).attr("checked",true);
				$("#addname").val('');
					
				var array = new Array();
				array=param5.split(",");
				for(var i=0;i<array.length;i++){
					$("#app"+i).attr("checked",true);
				}
				
			};
			
			var subAdd = function(params) {
				$("#roid").removeClass("hidden");
				$("#vpwd").removeClass();
				$("#mid").removeClass();
				$("#addname").val(params);
			};
</script>
							<div class="ccon">

								<div id="mid" class="hidden">
									<form id="formu" name="formu">
									<input type="hidden" name="addname" id="addname" value=""></input>
									<input type="hidden" name="userid" id="userid" value=""></input>
									<div  id="roid" class="statsTableHeader">
									<div  id="choiceRole" class="selbox">
			角色选择：<c:forEach items="${roles}" var="item" varStatus="status">
											<input type="radio" id="role${status.index}" name="roleid" 
												value="${item.id}" class="roleraios">${item.name}</input>
										</c:forEach>
			</div>
		<div class="clear"></div>
		</div>
		
		<div  id="appid" class="statsTableHeader hidden" >
									<div  class="selbox">
			app应用选择：<c:forEach items="${apps}" var="item" varStatus="status">
			<input type="hidden" id="appindex<c:out value="${status.last}"/>" value="<c:out value="${status.count}"/>"></input>
											<input type="checkbox" id="app${status.index}" name="myapp"
												value="${item.id}">${item.name}</input>
										</c:forEach>
			</div>
		<div class="clear"></div>
		</div>
		
		<div  id="flagid" class="statsTableHeader hidden">
									<div  class="selbox">
			是否冻结：<input type="radio" id="flag0" name="flag"
												value="0">使用</input>
					<input type="radio" id="flag1" name="flag"
												value="1">冻结</input>
			</div>
			
		<div class="clear"></div>
		
		</div>
		
		<div class="statsTableHeader">
		<strong>用户信息修改：</strong>
			<span>用户名:</span><input type="text" id="uname" name="username" value="" class="datainp"/>
			<span id="vpwd" class="hidden">密码:<input type="text" id="upwd" name="pwd" value="" class="datainp"/></span>
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
											<th scope="col" class="new_silver_first_n">用户名</th>
											<th scope="col">角色 <img alt="Info_tip"
												class="term-tool-tip" data-term="launches" height="16"
												src="<%=basePath%>statics/images/info_tip.png" width="16">
											</th>
											<th>状态</th>
											<th ><!--  class="new_silver_first_n" -->操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userrs}" var="item" varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'} ${status.first?' today':''}">
												<td class="new_silver_first_n">${item.name}<img
													alt="Info_tip" class="term-tool-tip" data-term="today_stat"
													height="16" src="<%=basePath%>statics/images/info_tip.png"
													width="16">
												</td>
												<td>${item.fn}</td>
												<td><c:if test="${item.flag==1}">冻结</c:if><c:if test="${item.flag==0}">使用</c:if></td>
												<td><a
													href="javascript:submMd('${item.id}','${item.flevel}','${item.name}','${item.flag}','${item.appid}','${status.index}','${item.fn}')"
													id="w1">修改</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									</table>
									</div>
									<div style="margin-bottom: 30px;"><a
													href="javascript:subAdd('yes')"
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