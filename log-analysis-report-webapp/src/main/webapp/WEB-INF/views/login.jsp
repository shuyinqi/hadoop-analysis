<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>

<title>所有产品 | Mapbar运营平台 Beta2.0</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/main/b_home.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/main/jquery.qtip.css" media="screen" />

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>statics/css/main/b_apps.css" media="screen" />
		<link rel="stylesheet" type="text/css"
	href="<%=basePath%>statics/css/register.css" media="screen" />
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>statics/css/main/layout.css" media="screen" />
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
</style>

		<div id="b_center">
			<br />
			<br />
			<div class="demonotice">欢迎使用 Mapbar运营平台 Beta2.0</div>

<script language="javascript">
function checkForm(){
	if($('#username').get(0).value==""){
		alert("用户名称不能为空。");
		$("#username:focus");
		return false;
	}
	if($('#password').get(0).value==""){
		alert("用户密码不能为空。");
		$("#password:focus");
		return false;
	}
	subm();
}
var subm = function(upbtn) {
	var par1 = $('#username').get(0)!=null?$('#username').get(0).value:"";
	var par2 = $('#password').get(0)!=null?$('#password').get(0).value:"";
	$(":submit")
};
</script>

<div id="center">
<div class="register_content">
    <div class="register_content_top"></div>
    <div class="register_content_center">
      <h3 class="second_title">登  录</h3>
      <div class="kong5yuan_enterprise"></div>
      <form action="login" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="QbRAdafQuPoPaK8tCGN8/lqYgFaOIaCxzxFFDHMRLxw=" /></div>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td></td>
            <td colspan="2">
              <h3 class="login_error">
                	<c:if test="${changed!=null&&changed=='success'}">密码修改成功!请您重新登录. </c:if>
                	<c:if test="${changed!=null&&changed=='failed'}">密码修改失败!请您输入正确旧密码. </c:if>
                	<c:if test="${changed!=null&&changed=='error'}">密码修改错误!请您重新修改. </c:if>
              </h3>
            </td>
          </tr>
          <tr>
            <td align="right" valign="middle">*用户名&nbsp;</td>
            <td>
              <input class="input_205 email" name="username" id="username" size="40" type="text" value="" />
            </td>
            <td class="status"></td>
          </tr>
          <tr>
            <td align="right" valign="middle">*密码&nbsp;</td>
            <td>
              <input class="input_205" name="password" id="password" size="40" type="password" value="" />
            </td>
            <td class="status"></td>
          </tr>
          <tr>
            <td></td>
            <td>
            </td>
            <td></td>
          </tr>
          <tr class="act">
            <td></td>
            <td colspan="2">
              <input id="login" src="statics/images/user_signin_hover.png" style="" type="image"  onclick="return checkForm()">
            </td>
          </tr>

          <tr class="act">
            <td></td>
            <td colspan="2">
            </td>
          </tr>
        </table>
</form>    </div>
    <div class="register_content_bottom"></div>
  </div>
</div>
		</div>
	</div>

	<input id="loginlog" type="hidden" value="" />

	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>