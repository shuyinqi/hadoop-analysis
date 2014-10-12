<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="commons/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
<title>设备统计 | ${app.name}</title>
</head>

<body>
	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>
		<div id="b_center">
			<%@ include file="/WEB-INF/views/body-leftmenu.jsp"%>

			<div class="fr b_rightconten">
				<div class="fl b_right_menu">
					<ul>
						<li><a id="reports_tab" class="click current "
							href="#">统计分析</a>
						</li>
						<li><a href="#" id="module_tab">组件</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear">&nbsp;</div>
<!-- <script type="text/javascript">
$(document).ready(function() { 
    // call the tablesorter plugin 
    $("table").tablesorter({
    	sortForce: [[1,1]],
        sortList: [[1,1]]
    });
});

</script> -->
				<div class="b_right_public">
					<div class="conright">
						<div id="main">
							<%@ include file="commons/date-form.jsp"%>
											

											<div class="statsTableHeader">
											<!-- 
												<div id="csv_link" class="fr">
													<span> <a target="_blank"
														href="/apps/b2700046c68c14d1744d41c4/reports/device_stats_details.csv?start_date=2012-01-21&amp;end_date=2012-02-20">输出EXCEL文件</a>
													</span> <a target="_blank"
														href="/apps/b2700046c68c14d1744d41c4/reports/device_stats_details.csv?start_date=2012-01-21&amp;end_date=2012-02-20"><img
														width="20" height="20" title="输出EXCEL文件"
														src="/images/csv.png?1329537551" alt="输出EXCEL文件">
													</a>
												</div> -->
												设备型号分布明细
											</div>
											<div class="statsTableHeader">
		<strong>关键字</strong>
		<div class="selbox">
			<input type="text" id="queryid" name="queryname" value="${queryname=='undefined'?'':queryname}"
				class="input_205">
				 <input type="button" onclick="vsubm2()"
				value="&nbsp;查询&nbsp;" class="upbtn">
		</div>
		<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list=true')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list=true')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div>
		<div class="clear"></div>
	</div>
											<div style="margin-bottom: 40px;" class="stats_content"
												id="device_stats_details">
												<table width="100%" cellspacing="0" border="0"
													class="new_silver_table">
													<tbody>
														<tr>
															<th class="new_silver_first_n" scope="col">品牌/型号</th>
															<th scope="col">用户比例</th>
															<th scope="col">明细</th>
														</tr>

														<c:forEach items="${stats}" var="item" varStatus="status">
															<tr
																class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
																<td class="new_silver_first_n">${item.device}</td>
																<td>${item.newcount}(
																<c:if test="${fn:indexOf((item.newcount/newsRatio)*100,'E')==-1}">
																<fmt:formatNumber type="number" pattern="###.##"  value="${(item.newcount/newsRatio)*100}" />
																</c:if>
																<c:if test="${fn:indexOf((item.newcount/newsRatio)*100,'E')!=-1}">
																0.0
																</c:if>
																%)</td>
																<td><a
													href=""
													class="load_channel_detail_btn">展开</a> <img alt="Loading"
													class="loading" src="/images/loading.gif?1329537551"
													style="display: none;" /></td>
															</tr>
															
															<tr class="channel_detail_tr hidden">
												<td class="channel_detail_area" colspan="7"><div
														class="fr"></div>
													<div class="clear"></div>
													<div>设备明细：${item.device}</div>
													<table cellspacing="0" cellpadding="0" border="0"
														style="margin-bottom: 20px;"
														class="datatab long new_silver_table">
														<tbody>
															<tr>
																<th class="new_silver_first_n" scope="col">日期</th>
																<th scope="col">品牌/型号</th>
																<th scope="col">用户量</th>
															</tr>
															<c:forEach items="${item.vlist}" var="item2" varStatus="vs">
														      <tr>
														        <td>${item2.date}</td>
														        <td>${item2.device}</td>
														        <td>${item2.newcount}</td>
														      </tr>
														      </c:forEach>
														</tbody>
													</table>
												</td>
											</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>

										</div>
									</div>
									<div class="bs_bottom">
										<div class="bs_bl"></div>
										<div class="bs_br"></div>
									</div>
								</div>
								<!--location end-->
								<%@ include file="commons/pager.jsp"%>
						</div>
						<script type="text/javascript">
								jQuery(function() {
									jQuery('a.load_channel_detail_btn')
											.click(
													function() {
														var target = jQuery(
																this)
																.closest('tr')
																.next(
																		'tr.channel_detail_tr')
																.children('td');
														if (jQuery.trim(target
																.html()) == '') {
															var loadingImg = jQuery(
																	this).next(
																	'img')
																	.show();
															var link = jQuery(this);
															target
																	.load(
																			this.href,
																			function() {
																				loadingImg
																						.hide();
																				target
																						.closest(
																								'tr')
																						.show();
																				link
																						.html('收起');
																			});
														} else {
															if (jQuery(this)
																	.html() == '展开') {
																jQuery(this)
																		.html(
																				'收起');
															} else {
																jQuery(this)
																		.html(
																				'展开');
															}
															jQuery(this)
																	.closest(
																			'tr')
																	.next(
																			'tr.channel_detail_tr')
																	.toggle();
														}
														return false;
													});
								});
							</script>
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

	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>