<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mapbar.analyzelog.report.entity.MenuVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta2.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat2.jsp"%>

<title>菜单权限管理 | ${app.name}</title>

<% 
StringBuffer treeData=new StringBuffer();
List<MenuVO> menus=(List<MenuVO>)request.getAttribute("usermenus2");
List<MenuVO> getmenus=(List<MenuVO>)request.getAttribute("menus");
treeData.append("[");
for(int i=0;i<menus.size();i++){
	MenuVO vo=menus.get(i);
if(vo.getFlevel()==1){
	treeData.append("{title: '"+vo.getName()+"', isFolder: true,key:'"+vo.getId()+"',expand: true,");
	for(int k=0;k<getmenus.size();k++){
		if(getmenus.get(k).getId()==vo.getId())
		treeData.append("select: true,");
	}

	treeData.append("children: [");
	for(int j=0;j<menus.size();j++){
		MenuVO vo2=menus.get(j);
		if(vo2.getFlevel()==2){
			if(vo.getId()==vo2.getFatherid()){
				treeData.append("{title: '"+vo2.getName()+"', key: '"+vo2.getId()+"'");
				for(int k=0;k<getmenus.size();k++){
					if(getmenus.get(k).getId()==vo2.getId())
					treeData.append(",select: true");
				}
				treeData.append("},");
			}
		}
	}

	treeData.append("]");
	treeData.append("},");
	}
}
treeData.append("]");
%>
</head>
<body>
	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>

		<div id="b_center">
			<%@ include file="/WEB-INF/views/body-leftmenu2.jsp"%>
			
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
<div class="statsTableHeader"><strong>角色菜单权限设置</strong><span class="question fr"></span><div class="clear"></div></div>
							
							<script src="<%=path %>/CheckTree.js" type="text/javascript"></script>
							<div class="ccon">
 <script type="text/javascript">
 function trimUrl(url) {
		if (url != null && url.length > 1) {
			var temp = url[url.length - 1] == '#' ? url.substring(0, url.length - 1) : url;
			var pos = temp.indexOf("?", 0);
			if (pos != -1)temp = url.substring(0, pos);
			return temp[temp.length - 1] == '/' ? temp.substring(0, temp.length - 1) : temp;
		} else
			return url;
	}
	$(function(){
		$("#tree").dynatree({
			checkbox: true,
			selectMode: 2,
			children:<%=treeData.toString()%> 
		});
		$("#formf").submit(function() {
			// Serialize standard form fields:
			var formData = $(this).serializeArray();

			// then append Dynatree selected 'checkboxes':
			var tree = $("#tree").dynatree("getTree");
			formData = formData.concat(tree.serializeArray());

			// and/or add the active node as 'radio button':
			if(tree.getActiveNode()){
				formData.push({name: "activeNode", value: tree.getActiveNode().data.key});
			}

			$.post(window.location.href,
				   formData,
				   function(response, textStatus, xhr){
					   /* alert("Modify returned " + textStatus); */
					   window.location.href = trimUrl(window.location.href).substring(0,trimUrl(window.location.href).lastIndexOf("/"))+"/roles";
				   }
			);
			return false;
		});
	});
 
 </script>
								<form id="formf" name="formf">
		角色选择：
		<c:forEach items="${roles}" var="item" varStatus="status">
		<input type="radio" name="roleid" value="${item.id}" <c:if test="${roleid==item.id}">checked="true"</c:if>>${item.name}</input>
		</c:forEach>
		<br>

		角色对应菜单选择： 
		<div id="tree" name="functionid">
		</div>

		<input type="submit" value="提  交">
	</form>
							</div>
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
	</div>
	</div>
	</div>
	<div class="clear"></div>
	</div>

	</div>
	<script src="<%=basePath%>statics/js/stat/tree/jquery-ui.custom.js" type="text/javascript"></script>

	<link href="<%=basePath%>statics/css/stat/skin/ui.dynatree.css" rel="stylesheet" type="text/css">
	<script src="<%=basePath%>statics/js/stat/tree/jquery.dynatree.js" type="text/javascript"></script> 
	
	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>