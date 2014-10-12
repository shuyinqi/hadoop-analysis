<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<script type="text/javascript" src="<%=basePath%>statics/js/reptail.js"></script>
<title>渠道设置 | ${app.name}</title>
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
								<strong>市场渠道设置 </strong>
							</div>
							<div class="statsTableHeader">
							<strong>选择查询开始时间:</strong>
							<div>
							<input type="date" id="re_sel_date"  name="stat_date" value="" class="datainp first_date date"></input> 
							<input type="submit" class="upbtn" value="查询" onclick="date_submit()"></input>
							</div>
							</div>
							<div class="ccon">
		<div id="ch_re" class="statsTableHeader hidden">
		<!-- <strong>添加：</strong> -->
			<span>渠道:</span><input type="text" id="uname" readonly="readonly" name="name" value="" class="datainpReptail"/>
			<span>昨日下载用户:<input type="text" id="newCount" name="addCount" value="" class="datainpReptail"/></span>
			<span>累计下载总数:<input type="text" id="total"  name="total" value="" class="datainpReptail"/></span>
			<span>时间:<input type="date" id="re_stat_date"  name="stat_date" value="" class="datainpReptail first_date date"/></span>           
			<input type="hidden" id="uid" name="id" value=""></input>
			<input class="upbtn" type="submit" value="提  交" onclick="re_submit()"></input>
			<input class="upbtn" type="submit" value="取消" onclick="cancel()"></input>
		<div class="clear"></div>
			</div></div>
						<div>
						<table class="new_silver_table" width="100%" border="0"
							cellspacing="0" cellpadding="">
							<thead>
								<tr>
									<th scope="col" class="new_silver_first_n">渠道名称</th>
									<th scope="col">累计下载总数
									</th>
									<th>昨日下载用户</th>
									<th>日期</th>
									<th ><!--  class="new_silver_first_n" -->操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${reptail}" var="item" varStatus="status">
									<tr
										class="${status.index%2==0?'new_silver_odd':'new_silver_even'} ${status.first?' today':''}">
										<td class="new_silver_first_n">${item.name}</td>
										<c:choose>
											<c:when test="${ item.total==0 }">
											   <td>---</td>
										      <td> ---</td>
											</c:when>
											<c:otherwise>
												<td>${item.total}</td>
										        <td>${ item.addCount }</td>
																					
											</c:otherwise>
										</c:choose>
										<td>${ item.date }</td>
										<td><a
											href="javascript:modifyReptail('${item.name}','${item.total}','${ item.addCount }','${ item.date }','${ item.id }')"
											>修改</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							</table>
							</div>
							<div style="margin-bottom: 30px;"><a
											href="javascript:addReptail()"
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