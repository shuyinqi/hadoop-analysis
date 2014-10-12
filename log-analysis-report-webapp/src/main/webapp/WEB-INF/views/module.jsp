<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<script type="text/javascript" src="<%=basePath%>statics/js/module.js"></script>
<title>地域统计 | ${app.name}</title>
</head>
<style>
.feedback_bookmark_on {
 position: absolute;
 display: block;
 width: 13px;
 height: 13px;
 margin-top: 4px;
 background-image: url('../../statics/images/star.png');
 background-position: 50% 0%;
}


.feedback_bookmark_off {
position: absolute;
display: block;
width: 13px;
height: 13px;
margin-top: 4px;
background-image: url('../../statics/images/star.png');
background-position: 50% 66%;
}

.reply {
background: url('../../statics/images/reply.jpg') no-repeat center center;
width: 67px;
height: 30px;
display: inline-block;
zoom: 1;
text-indent: -9999px;
overflow: hidden;
float: right;
margin-top: 70px;
margin-right: 5px;
cursor: pointer;
}

</style>
<body>
	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>

		<div id="b_center">
			<%@ include file="/WEB-INF/views/module-leftmenu.jsp"%>
			<div class="fr b_rightconten">
				<div class="fl b_right_menu">
					<ul>
						<li><a id="reports_tab" class="" href="#">统计分析</a></li>
						<li><a id="module_tab" class="current " href="#">组件</a>
						</li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear">&nbsp;</div>
				<div class="b_right_public">
					<div class="conright">
						<div id="main">
							<div class="b_right_public">
								<!--location begin-->
								<div class="conright">
									<div id="main">
										<div class="bbox_con">
											<div class="statsTableHeader">用户反馈</div>
											<%@ include file="commons/date-form.jsp"%>
											<div style="margin: 10px 0;" class="blockboxbg">
												<div class="blockbox">
													<div class="${isCollect!=1?'bitem selbitem':'bitem '}" onclick="feedBack_rediect('module','')">全部</div>
													<div class="${isCollect!=1?'bitem':'bitem selbitem'}" onclick="feedBack_rediect('feedBackCollect','')" >收藏</div>
												</div>
											</div>
											<div style="padding-bottom: 10px;" class="bitemcon" id="ac0">
												<div id="active_users_total_numbers" class="hidden">87990</div>
												<table cellspacing="0" cellpadding="0" border="0"
													class="datatab long new_silver_table"
													id="bartable_daily_active_users_devices">
													<thead>
														<tr>
														    <th width="40" class="new_silver_first_n" scope="col"></th>
															<th width="180" class="new_silver_first_n" scope="col">反馈内容</th>
															<th width="50" class="new_silver_first_n" scope="col">应用版本</th>
															<th width="80" class="new_silver_first_n" scope="col">设备/系统版本</th>
															<th width="80" class="new_silver_first_n" scope="col">用户信息</th>
															<th width="40" class="new_silver_first_n" scope="col">联系方式</th>
															<th width="80" class="new_silver_first_n" scope="col"> 备注</th>
														</tr>
														</thead>
														<tbody>
														<c:forEach items="${feedBack}" var="item" varStatus="status">
												<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
													<td class="new_silver_first_n">
													<a href="#" onclick="isCollect('${item.id}','${status.index}')" id="module_collect_${status.index}" 
													title="${item.collect==1?'收藏':'取消收藏'}" 
													class="${item.collect==1?'feedback_bookmark_on':'feedback_bookmark_off'}" ></a>
													</td>
													<td class="new_silver_first_n">${item.ct}</td>
													<td class="new_silver_first_n">${item.appv}</td>
													<td class="new_silver_first_n">${item.device}</br>
													${item.os}
													</td>
													<td class="new_silver_first_n">${item.sex}</br>${item.age }</td>
													<td class="new_silver_first_n">${item.phone}</td>
													<td class="new_silver_first_n">${item.note }</td>
												</tr>
												<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
												<td class="new_silver_first_n"></td>
												<td class="new_silver_first_n"></td>
												<td class="new_silver_first_n"></td>
												<td class="new_silver_first_n"></td>
												<td class="new_silver_first_n"></td>
												<td class="new_silver_first_n"></td>
												<td class="new_silver_first_n">
												<a href="#" class="module_reply_delete" onclick="deleteFeedBook('${item.id}')">删除</a>
												&nbsp;&nbsp;|&nbsp;&nbsp;
												<a href="#" class="module_reply" onclick="reply('${status.index}',this)">
												回复
												</a></td>
												</tr>
												
												 <c:forEach items="${item.answer}" var="item2" varStatus="vs">
														      <tr class="module_reply_alway_answer_${status.index} hidden">
														        <td>${item2.answerusername}</td>
														        <td colspan="4">${item2.answer}</td>
																<td colspan="2">${item2.lasttime}</td>
														      </tr>
														     </c:forEach>
														     <td></td>
												<tr class="module_reply_alway_answer_${status.index} hidden">
												<td colspan="5">
												<textarea cols="70" id="module_answer_${status.index}" rows ="6" title=""></textarea>
												</td>
												<td><input class="reply" type="button" onclick="submitReply('${item.userid}','${item.ct }','${item.qid }','${item.wid}','${item.id}','${item.appid}','${status.index }')"/></td>
												</tr>
											</c:forEach>
												
													</tbody>
												</table>
											</div>
											<%@ include file="commons/pager.jsp"%>
										</div>

									</div>
								</div>
								<div class="bs_bottom">
									<div class="bs_bl"></div>
									<div class="bs_br"></div>
								</div>
							</div>
							<!--location end-->
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


	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>