<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%  %>	
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/meta.jsp"%>
<%@ include file="/WEB-INF/views/meta-stat.jsp"%>

<script type="text/javascript">

</script>
<title>分发渠道统计 | ${app.name}</title>
</head>

<body>
	<div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>

		<div id="b_center">
			<%@ include file="/WEB-INF/views/body-leftmenu.jsp"%>

			<div class="fr b_rightconten">
				<div class="fl b_right_menu">
					<ul>
						<li><a href="#" class="click current " id="reports_tab">统计分析</a>
						</li>
						<li><a href="#" id="module_tab">组件</a></li>
					</ul>
				</div>
				<div class="clear">&nbsp;</div>
				<div class="b_right_public">
					<div class="conright">
						<div id="main">
							<div class="bbox_con">
								<div class="statsTableHeader">
									<strong>渠道统计</strong> <span class="question fr"></span>
									<div class="clear"></div>
								</div>
								<%@ include file="commons/date-form.jsp"%>
								<div><span style="padding-left:78%;">选择版本:</span>
								<select style="float:right" name="channel_type" id="mySelectVersion"> 
         							<c:forEach items="${ channelVersion }" var="ss">
			         				     <c:choose>
				         				 	<c:when test="${ ss==version }">
				         						<option value="${ ss }" selected="selected">${ ss }</option>
				         				 	</c:when>
				         				 	<c:otherwise>
				         						<option value="${ ss }">${ ss }</option>
				         				 	</c:otherwise>
				         				 </c:choose>
		         				</c:forEach>
        						</select>
								</div>
								
								<div class="blockboxbg" style="margin: 10px 0;">
									<div class="blockbox">
										<div id="a0"
											onclick="changeTab(0,4,'a','ac','bitem','selbitem')"
											class="bitem selbitem">市场新增用户</div>
										<div id="a1"
											onclick="changeTab(1,4,'a','ac','bitem','selbitem')"
											class="bitem">市场活跃用户</div>
										<div id="a2"
											onclick="changeTab(2,4,'a','ac','bitem','selbitem')"
											class="bitem">预装新增用户</div>
										<div id="a3"
											onclick="changeTab(3,4,'a','ac','bitem','selbitem')"
											class="bitem">预装活跃用户</div>
									</div>
								</div>
								<div id="ac0" class="bitemcon" style="padding-bottom: 0px;">
								<div class="clear"></div>
								<div class="fr">
			<span><a href="#pager2" onclick="vsubm2('exp_list=1')">输出EXCEL文件</a></span>
			<a href="#pager2"  onclick="vsubm2('exp_list=1')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div>
									<table class="datatab long new_silver_table" border="0"
									  id="channel_table_sort1"	cellspacing="0" cellpadding="0">
									  <thead>
										<tr>
											<th scope="col" class="new_silver_first_n" style="width: 60px" >渠道</th>
											<th scope="col" style="width: 60px">今日新用户</th>
											<th scope="col" style="width: 60px">昨日新用户</th>
											<th scope="col" style="width: 60px">前日新用户</th>
											<th scope="col" style="width: 67px">7天前新用户</th>
											<th scope="col" >市场下载量（激活率）</th>
											<th scope="col" class="default_order">渠道累计新用户(%)</th>
											<th scope="col">明细</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${summaryMarketNew}" var="item"
											varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}" id="mk_new_${status.index }">
												<td class="new_silver_first_n">${item.name}<c:if test="${item.note !=null && item.note!=''}">(${item.note})</c:if></td>
												<td>${item.imei_size0}<input type=hidden value="${item.imei_size0}" size="5" maxlength="25"  id="NEWSFER_${status.index}" class="NEWSFER"></td>
												<td>${item.imei_size1}<input type=hidden value="${item.imei_size1}" size="5" maxlength="25"  id="NEWSFER2_${status.index}" class="NEWSFER2"></td>
												<td>${item.imei_size2}<input type=hidden value="${item.imei_size2}" size="5" maxlength="25"  id="NEWSFER3_${status.index}" class="NEWSFER3"></td>
												<td>${item.imei_size7}<input type=hidden value="${item.imei_size7}" size="5" maxlength="25"  id="NEWSFER4_${status.index}" class="NEWSFER4"></td>
												<td>${item.addCount }
												<c:if test="${item.addCount !=0}">
												  <c:set
													    value="${item.imei_size1/item.addCount*100.0}"
														var="ad" />(${fn:substring(ad,0,fn:indexOf(ad,'.')+3)}%)
												</c:if>
												<c:if test="${ item.addCount ==0 }">
												  (0.0%)
												</c:if>
												</td>
												<td>${item.name_imei_size}<c:set
														value="${item.name_imei_size/item.type_imei_size*100.0}"
														var="ad" />(${fn:substring(ad,0,fn:indexOf(ad,'.')+3)}%)</td>
												<td>
											 	<a href="javascript:void();"
													class="load_channel_detail_btn">展开</a>
													</td>
											</tr>
											<tr class="channel_detail_tr hidden" id="mk_new_${status.index }_det">
												<td class="channel_detail_area" colspan="7"><div
														class="fr"></div>
													<div class="clear"></div>
													<div>渠道明细：${ item.name}<div class="fr">
			<%-- <span><a href="#pager" onclick="vsubm2('exp_list1=${ item.name}')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list1=${ item.name}')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20"/>
			</a> --%>
		</div></div>
													<table cellspacing="0" cellpadding="0" border="0"
														style="margin-bottom: 20px;"
														id="channel_table_2" class="datatab long new_silver_table">
															<tr>
																<th class="new_silver_first_n" scope="col">日期</th>
																<th scope="col">新用户</th>
																<th scope="col">启动用户</th>
																<th scope="col">市场下载量（激活率）</th>
															</tr>
															<c:forEach items="${item.vlist}" var="item2" varStatus="vs">
														      <tr>
														        <td>${item2.date}</td>
														        <td>${item2.news}<input type=hidden value="${item2.news}" size="5" maxlength="25"  id="NEWSDET_${status.index}_${vs.index}" class="NEWSDET_${status.index}"></td>
																<td>${item2.launchs}<input type=hidden value="${item2.launchs}" size="5" maxlength="25"  id="NEWSDET2_${status.index}_${vs.index}" class="NEWSDET2_${status.index}"></td>
														        <td>${item2.addCount2 }
												                <c:if test="${item2.addCount2 !=0}">
												               <c:set
													            value="${item2.news/item2.addCount2*100.0}"
														       var="ad" />(${fn:substring(ad,0,fn:indexOf(ad,'.')+3)}%)
										                 		</c:if>
										                 		<c:if test="${item2.addCount2 ==0}">
										                 		     (0.0%)
										                 		</c:if>
												             </td>
														      </tr>
														     </c:forEach>
														     <tr>
										<td>小计：</td>
										<td><span id='NEWSDET_${status.index}'></span></td>
										<td><span id='NEWSDET2_${status.index}'></span></td>
										</tr>
													</table>
												</td>
											</tr>
										</c:forEach>
										
										<!-- <tr class="hidden">
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
											</tr> -->
</tbody>
									</table>
									<table border="0" cellspacing="0" cellpadding="0">
									  <tr>
										<td style='width:112px'>小计：</td>
										<td style='width:75px'><span id='RECEIVE'></span></td>
										<td style='width:75px'><span id='RECEIVE2'></span></td>
										<td style='width:75px'><span id='RECEIVE3'></span></td>
										<td style="width:82px"><span id='RECEIVE4'></span></td>
										<td style="width:139px"></td>
										<td style="width:121px"></td><td style="width:41px;"></td>
										</tr>
									</table>
								</div>
								<div id="ac1" class="bitemcon" style="display: none; padding-bottom: 30px;"></div>
								<div id="ac2" class="bitemcon" style="display: none; padding-bottom: 30px;"></div>
								<div id="ac3" class="bitemcon" style="display: none; padding-bottom: 30px;"></div>
								<%-- <div id="ac1" class="bitemcon"
									style="display: none; padding-bottom: 30px;">
									<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list=2')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list=2')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div>
									<table class="datatab long new_silver_table" border="0"
										id="channel_table_sort2" cellspacing="0" cellpadding="0">
										<thead>
										<tr>
											<th scope="col" class="new_silver_first_n">渠道</th>
											<th scope="col">今日启动用户</th>
											<th scope="col">昨日启动用户</th>
											<th scope="col">前日启动用户</th>
											<th scope="col">7天前启动用户</th>
											<th scope="col" class="default_order">渠道累计用户(%)</th>
											<th scope="col">明细</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${summaryMarketVisit}" var="item"
											varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}" id="mk_silver_${status.index }">
												<td class="new_silver_first_n">${item.name}(${item.note})</td>
												<td>${item.imei_size0}<input type=hidden value="${item.imei_size0}" size="5" maxlength="25"  id="RLAUNCH_${status.index}" class="RLAUNCH"></td>
												<td>${item.imei_size1}<input type=hidden value="${item.imei_size1}" size="5" maxlength="25"  id="RLAUNCH2_${status.index}" class="RLAUNCH2"></td>
												<td>${item.imei_size2}<input type=hidden value="${item.imei_size2}" size="5" maxlength="25"  id="RLAUNCH3_${status.index}" class="RLAUNCH3"></td>
												<td>${item.imei_size7}<input type=hidden value="${item.imei_size7}" size="5" maxlength="25"  id="RLAUNCH4_${status.index}" class="RLAUNCH4"></td>
												<td>${item.name_imei_size}<c:set
														value="${item.name_imei_size*100.0/item.type_imei_size}"
														var="ad" />(${fn:substring(ad,0,fn:indexOf(ad,'.'))}%)</td>
												<td><a
													href="<%=basePath%>apps/${app.id}/daily_stats_detail_for_channel?channelname=${item.name_imei_size}&fromdate=${fromdate}&todate=${todate}"
													class="load_channel_detail_btn">展开</a> <img alt="Loading"
													class="loading" src="/images/loading.gif?1329537551"
													style="display: none;" /></td>
											</tr>
											<tr class="channel_detail_tr hidden" id="mk_silver_${status.index }_det">
												<td class="channel_detail_area" colspan="7"><div
														class="fr"></div>
													<div class="clear"></div>
													<div>渠道明细：${ item.name}<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list1=${ item.name}')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list1=${ item.name}')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div></div></div>
													<table cellspacing="0" cellpadding="0" border="0"
														style="margin-bottom: 20px;"
														class="datatab long new_silver_table">
														<tbody>
															<tr>
															<th class="new_silver_first_n" scope="col">日期</th>
																<th scope="col">新用户</th>
																<th scope="col">启动用户</th>
															</tr>
															 <c:forEach items="${item.vlist}" var="item2" varStatus="vs">
														      <tr>
														        <td>${item2.date}</td>
														        <td>${item2.news}<input type=hidden value="${item2.news}" size="5" maxlength="25"  id="LAUNCHDET_${status.index}_${vs.index}" class="LAUNCHDET_${status.index}"></td>
														        <td>${item2.launchs}<input type=hidden value="${item2.launchs}" size="5" maxlength="25"  id="LAUNCHDET2_${status.index}_${vs.index}" class="LAUNCHDET2_${status.index}"></td>
														      </tr>
														      </c:forEach>
															<tr>
															<td>小计：</td>
															<td><span id='LAUNCHDET_${status.index}'></span></td>
															<td><span id='LAUNCHDET2_${status.index}'></span></td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</c:forEach>
										<tr>
										<td>小计：</td>
										<td><span id='RLAUNCH'></span></td>
										<td><span id='RLAUNCH2'></span></td>
										<td><span id='RLAUNCH3'></span></td>
										<td><span id='RLAUNCH4'></span></td>
										<td></td>
										<td></td>
										</tr>
										<tr class="hidden">
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
											</tr>
									  </tbody>
									</table>
								</div>
								<div id="ac2" class="bitemcon"
									style="display: none; padding-bottom: 30px;">
									<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list=3')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list=3')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div>
									<table class="datatab long new_silver_table" border="0"
										id="channel_table_sort3" cellspacing="0" cellpadding="0">
										<thead>
										<tr>
											<th scope="col" class="new_silver_first_n">品牌</th>
											<th scope="col">今日新用户</th>
											<th scope="col">昨日新用户</th>
											<th scope="col">前日新用户</th>
											<th scope="col">7天前新用户</th>
											<th scope="col" class="default_order">渠道累计用户(%)</th>
											<th scope="col">明细</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${summaryInstalledNew}" var="item"
											varStatus="status">
											
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}" id="mk_install_${status.index }">
												<td class="new_silver_first_n">${item.name}(${item.note})</td>
												<td>${item.imei_size0}<input type=hidden value="${item.imei_size0}" size="5" maxlength="25"  id="ONSETUP_${status.index}" class="ONSETUP"></td>
												<td>${item.imei_size1}<input type=hidden value="${item.imei_size1}" size="5" maxlength="25"  id="ONSETUP2_${status.index}" class="ONSETUP2"></td>
												<td>${item.imei_size2}<input type=hidden value="${item.imei_size2}" size="5" maxlength="25"  id="ONSETUP3_${status.index}" class="ONSETUP3"></td>
												<td>${item.imei_size7}<input type=hidden value="${item.imei_size7}" size="5" maxlength="25"  id="ONSETUP4_${status.index}" class="ONSETUP4"></td>
												<td>${item.name_imei_size}<c:set
														value="${item.name_imei_size*100.0/item.type_imei_size}"
														var="ad" />(${fn:substring(ad,0,fn:indexOf(ad,'.'))}%)</td>
												<td><a
													href="<%=basePath%>apps/${app.id}/daily_stats_detail_for_channel?channelname=${item.name_imei_size}&fromdate=${fromdate}&todate=${todate}"
													class="load_channel_detail_btn">展开</a> <img alt="Loading"
													class="loading" src="/images/loading.gif?1329537551"
													style="display: none;" /></td>
											</tr>
											<tr class="channel_detail_tr hidden" id="mk_install_${status.index }_det">
												<td class="channel_detail_area" colspan="7"><div
														class="fr"></div>
													<div class="clear"></div>
													<div>渠道明细：${ item.name}<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list2=${ item.name}')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list2=${ item.name}')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div></div></div>
													<table cellspacing="0" cellpadding="0" border="0"
														style="margin-bottom: 20px;"
														class="datatab long new_silver_table">
														<tbody>
															<tr>
																<th class="new_silver_first_n" scope="col">日期</th>
																<th scope="col">新用户</th>
																<th scope="col">启动用户</th>
															</tr>
															<c:forEach items="${item.vlist}" var="item2" varStatus="vs">
														      <tr>
														        <td>${item2.date}</td>
														        <td>${item2.news}<input type=hidden value="${item2.news}" size="5" maxlength="25"  id="SETUPDET_${status.index}_${vs.index}" class="SETUPDET_${status.index}"></td>
														        <td>${item2.launchs}<input type=hidden value="${item2.launchs}" size="5" maxlength="25"  id="SETUPDET2_${status.index}_${vs.index}" class="SETUPDET2_${status.index}"></td>
														      </tr>
														      </c:forEach>
														    <tr>
															<td>小计：</td>
															<td><span id='SETUPDET_${status.index}'></span></td>
															<td><span id='SETUPDET2_${status.index}'></span></td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</c:forEach>
										<tr>
										<td>小计：</td>
										<td><span id='ONSETUP'></span></td>
										<td><span id='ONSETUP2'></span></td>
										<td><span id='ONSETUP3'></span></td>
										<td><span id='ONSETUP4'></span></td>
										<td></td>
										<td></td>
										</tr>
										<tr class="hidden">
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
											</tr>
                                       </tbody>
									</table>
								</div>
								<div id="ac3" class="bitemcon"
									style="display: none; padding-bottom: 30px;">
									<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list=4')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list=4')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div>
									<table class="datatab long new_silver_table" border="0"
										id="channel_table_sort4" cellspacing="0" cellpadding="0">
										<thead>
										<tr>
											<th scope="col" class="new_silver_first_n">品牌</th>
											<th scope="col">今日访问用户</th>
											<th scope="col">昨日访问用户</th>
											<th scope="col">前日访问用户</th>
											<th scope="col">7天前访问用户</th>
											<th scope="col" class="default_order">渠道累计用户(%)</th>
											<th scope="col">明细</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${summaryInstalledNew}" var="item"
											varStatus="status">
											<tr
												class="${status.index%2==0?'new_silver_odd':'new_silver_even'}" id="mk_installNew_${status.index }">
												<td class="new_silver_first_n">${item.name}(${item.note})</td>
												<td>${item.imei_size0}<input type=hidden value="${item.imei_size0}" size="5" maxlength="25"  id="ONLAUNCH_${status.index}" class="ONLAUNCH"></td>
												<td>${item.imei_size1}<input type=hidden value="${item.imei_size1}" size="5" maxlength="25"  id="ONLAUNCH2_${status.index}" class="ONLAUNCH2"></td>
												<td>${item.imei_size2}<input type=hidden value="${item.imei_size2}" size="5" maxlength="25"  id="ONLAUNCH3_${status.index}" class="ONLAUNCH3"></td>
												<td>${item.imei_size7}<input type=hidden value="${item.imei_size7}" size="5" maxlength="25"  id="ONLAUNCH4_${status.index}" class="ONLAUNCH4"></td>
												<td>${item.name_imei_size}<c:set
														value="${item.name_imei_size*100.0/item.type_imei_size}"
														var="ad" />(${fn:substring(ad,0,fn:indexOf(ad,'.'))}%)</td>
												<td><a
													href="<%=basePath%>apps/${app.id}/daily_stats_detail_for_channel?channelname=${item.name_imei_size}&fromdate=${fromdate}&todate=${todate}"
													class="load_channel_detail_btn">展开</a> <img alt="Loading"
													class="loading" src="/images/loading.gif?1329537551"
													style="display: none;" /></td>
											</tr>
											<tr class="channel_detail_tr hidden" id="mk_installNew_${status.index }_det">
												<td class="channel_detail_area" colspan="7"><div
														class="fr"></div>
													<div class="clear"></div>
													<div>渠道明细：${ item.name}<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list2=${ item.name}')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list2=${ item.name}')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div></div>
													<table cellspacing="0" cellpadding="0" border="0"
														style="margin-bottom: 20px;"
														class="datatab long new_silver_table">
														<tbody>
															<tr>
																<th class="new_silver_first_n" scope="col">日期</th>
																<th scope="col">新用户</th>
																<th scope="col">启动用户</th>
															</tr>
															<c:forEach items="${item.vlist}" var="item2" varStatus="vs">
															      <tr>
															        <td>${item2.date}</td>
															        <td>${item2.news}<input type=hidden value="${item2.news}" size="5" maxlength="25"  id="ONLADET_${status.index}_${vs.index}" class="ONLADET_${status.index}"></td>
															        <td>${item2.launchs}<input type=hidden value="${item2.launchs}" size="5" maxlength="25"  id="ONLADET2_${status.index}_${vs.index}" class="ONLADET2_${status.index}"></td>
															      </tr>
															      </c:forEach>
																<tr>
															<td>小计：</td>
															<td><span id='ONLADET_${status.index}'></span></td>
															<td><span id='ONLADET2_${status.index}'></span></td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</c:forEach>
										<tr>
										<td>小计：</td>
										<td><span id='ONLAUNCH'></span></td>
										<td><span id='ONLAUNCH2'></span></td>
										<td><span id='ONLAUNCH3'></span></td>
										<td><span id='ONLAUNCH4'></span></td>
										<td></td>
										<td></td>
										</tr>
										<tr class="hidden">
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
												<td class="hidden"></td>
											</tr>
									</tbody>
									</table>
								</div> --%>
								<!-- 新增爬虫统计 
                      <div  class="bitemcon" style=" padding-bottom: 30px;">
	<table class="datatab long new_silver_table" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<th scope="col" class="new_silver_first_n">渠道</th>
			<th scope="col">昨日用户总数</th>
			<th scope="col">昨日访问用户</th>
			<th scope="col">激活率(%)</th>
		</tr>
		<c:forEach items="${reptitleSum }" var="item" varStatus="status">
		<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
			<td class="new_silver_first_n">${item.name}</td>
			<td>${item.total}</td>
			<td>${item.addCount}</td>
			<td><c:set value="${item.proportion/item.addCount}" var="ad" />${fn:substring(ad,0,fn:indexOf(ad,'.'))}%</td>
		</tr>
		</c:forEach>
		</table>
-->
									<!-- 新增爬虫统计结束 -->

								<table cellspacing="0" cellpadding="0" border="0"
									style="margin: 10px auto 10px; padding-top: 5px;"
									class="benchmark_tips">
									<tbody>
										<tr>
											<td>注：按<b>原始安装渠道</b>来统计新增用户、启动用户、累计用户。即用户初次安装应用的来源是渠道A，后又在渠道B更新了应用版本，但该用户仍会被记为是渠道A的启动用户、累计用户。</td>
										</tr>
									</tbody>
								</table>

								<div class="meassage_contents">

									<div id="message-count-tab" class="meassage_contents_list">
										<div id="message-count-chart" class="line_chart_common"
											style="height: 0px; width: 95%;"></div>
									</div>
								</div>

								<div style="margin-bottom: 30px;"></div>
							</div>
							<script type="text/javascript">

							</script>
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
	<script type="text/javascript">
$(document).ready(function(){
     basecolumns(".NEWSFER","#NEWSFER_","#RECEIVE");
     basecolumns(".NEWSFER2","#NEWSFER2_","#RECEIVE2");
     basecolumns(".NEWSFER3","#NEWSFER3_","#RECEIVE3");
     basecolumns(".NEWSFER4","#NEWSFER4_","#RECEIVE4");
     base2columns(".NEWSFER","#NEWSDET_","#NEWSDET_");
     base2columns(".NEWSFER","#NEWSDET2_","#NEWSDET2_");
   });

</script>
<div class="hidden"><%@ include file="commons/pager2.jsp"%></div>
	<div id="question_wrap" style="display: none;">
	 <div class="question_content_top">
    <h3 class="title">
      <span class="fl"><b>分发渠道分析</b>词汇表</span>
      <span class="fr tg_rss"><img alt="" src="<%=path%>/statics/images/report_subscribe_close_normal.png"></span>
      <div class="clear"></div>
    </h3>
  </div>
  <div class="question_content_center">
    <table cellpadding="0" cellspacing="0" border="0" width="775">
      <tbody><tr>
        <td class="title">选择时段</td>
        <td>这里选择的时段会影响累计新用户以及渠道新增用户对比</td>
      </tr>
      <tr class="question_even">
        <td class="title">渠道累计新用户（%）</td>
        <td>所选时间段内，该渠道的新用户（所选时段内，渠道累计新用户/全体新用户）</td>
      </tr>
      <tr>
        <td class="title">渠道累计用户（%）</td>
        <td>截止当前，该渠道的全体用户（渠道累计用户/累计用户）</td>
      </tr>
    </tbody></table>
  </div>
  <div class="question_content_bottom"></div>
  </div>
<script src="<%=path %>/statics/js/channelChild.js" type="text/javascript"></script>
	<%@ include file="/WEB-INF/views/body-copyright.jsp"%>
</body>
</html>