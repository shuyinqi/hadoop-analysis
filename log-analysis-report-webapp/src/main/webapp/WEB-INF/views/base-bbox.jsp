<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String ss1=(String)request.getAttribute("visits0");
String ss2=(String)request.getAttribute("visits1");
%>
<div class="bbox_con">
	<div class="tinfo">
		<div class="statsTableHeader"><strong>基本统计</strong><span class="question fr"></span><div class="clear"></div></div>
		<table class="new_silver_table" width="100%" border="0" cellspacing="0" cellpadding="">
<tbody>
			<tr>
				<th scope="col" class="new_silver_first_n">日期 </th>
				<th scope="col">启动次数 <img alt="Info_tip" class="term-tool-tip" data-term="launches" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">启动用户 <img alt="Info_tip" class="term-tool-tip" data-term="launched_users" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">新用户 <img alt="Info_tip" class="term-tool-tip" data-term="new_users" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">新用户比例 <img alt="Info_tip" class="term-tool-tip" data-term="new_users_percentage" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">升级用户 <img alt="Info_tip" class="term-tool-tip" data-term="upgrad_users" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">平均使用时长 (m:s)<img alt="Info_tip" class="term-tool-tip" data-term="seconds_per_launch" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">活跃率 <img alt="Info_tip" class="term-tool-tip" data-term="active_rate" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
			</tr>
<c:forEach items="${basicStats}" var="item" varStatus="status">
			<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'} ${status.first?' today':''}">
				<td class="new_silver_first_n">${item.date}<img alt="Info_tip" class="term-tool-tip" data-term="today_stat" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></td>
				<td>${item.visit_size}</td>
				<td>${item.visit_imei_size}</td>
				<td>${item.new_imei_size}</td>
				<td><c:set value="${(item.new_imei_size/item.visit_imei_size*1.0)*100}" var="nrate"/>${fn:substring(nrate,0,fn:indexOf(nrate,'.')+3)}%</td>
				<td>${item.upgrade_imei_size}</td>
				<td><c:set value="${item.average_duration/60.0}" var="ad"/>${fn:substring(ad,0,fn:indexOf(ad,'.'))}:${item.average_duration%60}</td>
				<td><c:set value="${(item.visit_imei_size/imeiSize*1.0)*100}" var="arate"/>${fn:substring(arate,0,fn:indexOf(arate,'.')+3)}%</td>
			</tr>
</c:forEach>
</tbody>
		</table>
		<div class="right-align-text-gray" style="margin-bottom:10px">因为存在时差和缓存等因素, 统计以客户端时间为准</div>
		<table class="new_silver_table" width="100%" border="0" cellspacing="0" cellpadding="0">
<tbody>
			<tr class="new_silver_odd">
				<th scope="col" class="new_silver_first_n">累计用户 <img alt="Info_tip" class="term-tool-tip" data-term="total_users" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">累计启动 <img alt="Info_tip" class="term-tool-tip" data-term="total_launches" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">上周活跃用户* <img alt="Info_tip" class="term-tool-tip" data-term="active_users_7days" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">上周活跃率* <img alt="Info_tip" class="term-tool-tip" data-term="active_users_7days" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">上月活跃用户* <img alt="Info_tip" class="term-tool-tip" data-term="active_users_7days" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">上月活跃率* <img alt="Info_tip" class="term-tool-tip" data-term="active_users_7days" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<%-- <th scope="col">14天活跃用户（%）* <img alt="Info_tip" class="term-tool-tip" data-term="active_users_14days" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th>
				<th scope="col">14天沉默用户（%）* <img alt="Info_tip" class="term-tool-tip" data-term="silent_users_14days" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th> --%>
				<%-- <th scope="col">回头用户（%） *<img alt="Info_tip" class="term-tool-tip" data-term="return_users" height="16" src="<%=basePath%>statics/images/info_tip.png" width="16"></th> --%>
			</tr>
			<tr>
				<td class="new_silver_first_n">${imeiSize}</td>
				<td>${visitSize}</td>
				<td><c:set value="${active7!=null?active7.active_imei_size:0}" var="act7"/>${act7}</td>
				<td><c:set value="${act7/imeiSize*100.0}" var="rate7"/> ${fn:substring(rate7,0,fn:indexOf(rate7,'.')+2)}%</td>
				<td><c:set value="${active30!=null?active30.active_imei_size:0}" var="act30"/>${act30}</td>
				<td><c:set value="${act30/imeiSize*100.0}" var="rate30"/> ${fn:substring(rate30,0,fn:indexOf(rate30,'.')+2)}%</td>
				<%-- <td><c:set value="${active14!=null?active14.active_imei_size:0}" var="act14"/>${act14}<c:set value="${act14/imeiSize*100.0}" var="rate14"/> (${fn:substring(rate14,0,fn:indexOf(rate14,'.')+2)}%)</td>
				<td>${imeiSize-act14}<c:set value="${(imeiSize-act14)/imeiSize*100.0}" var="rate14x"/> (${fn:substring(rate14x,0,fn:indexOf(rate14x,'.')+2)}%)</td> --%>
				<%-- <td><c:set value="${active2!=null?active2.active_imei_size:0}" var="act2"/>${act2}<c:set value="${act2/imeiSize*100.0}" var="rate2"/> (${fn:substring(rate2,0,fn:indexOf(rate2,'.')+2)}%)</td> --%>
			</tr>
</tbody>
		</table>
		<div class="right-align-text-gray" style="margin-bottom:10px">加*号的数据为非实时数据</div>
	</div>
	<div class="statsTableHeader"><strong>时段分析</strong></div>
	<div class="blockboxbg">
		<div class="blockbox">
			<div id="a0" onclick="changeTab(0,2,'a','ac','bitem','selbitem')" class="bitem selbitem">新用户</div>
			<div id="a1" onclick="changeTab(1,2,'a','ac','bitem','selbitem')" class="bitem">启动次数</div>
		</div>
	</div>

<div id="ac0" class="bitemcon" style="padding-bottom: 10px;">
  <div id="installations-by-hours-chart" style="height:200px; width:680px; margin:15px auto;">
<script type="text/javascript">
jQuery(function() {
	getChart(
		"installations-by-hours-chart","",
		["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"],2,
		[{"name":"今日","data":[${newImeis0}],"type":null,"visible":true},
		{"name":"昨日","data":[${newImeis1}],"type":null,"visible":false},
	//	{"name":"7\u5929\u524d","data":[8,9,3,7,3,0,0,2,2,8,12,11,7,12,10,9,18,8,8,12,15,18,13,16],"type":null,"visible":false},
		//{"name":"30\u5929\u524d","data":[7,10,5,2,5,1,0,4,9,10,9,12,9,11,5,15,11,8,12,9,20,14,14,19],"type":null,"visible":false}
		],
		function() { return parseInt(this.x,10) +':00 ~' + (parseInt(this.x,10) + 1) + ':00 新增 '+ this.y + ' 个用户';}
	);
});
</script>
<div id="installations-by-hours-chart" style="height: 300px, width:615px"></div>  </div>
      </div>


      <div id="ac1" class="bitemcon" style="display:none; padding-bottom: 10px;">
  <div id="launches-by-hours-chart" style="height:200px; width:680px; margin:15px auto;">
<script type="text/javascript">
jQuery(function() {
	getChart(
		"launches-by-hours-chart","",
		["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"],2,
		[{"name":"今日","data":[${visits0}],"type":null,"visible":true},
		{"name":"昨日","data":[${visits1}],"type":null,"visible":false},
	//	{"name":"7\u5929\u524d","data":[723,381,208,102,68,92,118,420,768,917,835,938,957,979,874,878,847,967,1088,1008,1051,1012,999,927],"type":null,"visible":false},
		//{"name":"30\u5929\u524d","data":[820,473,277,141,98,76,131,342,590,885,1003,1122,1122,1196,1130,1162,1091,1197,1225,1048,1313,1263,1290,1196],"type":null,"visible":false}
		],
		function() { return parseInt(this.x,10) +':00 ~' + (parseInt(this.x,10) + 1) + ':00 启动 '+ this.y + ' 次';}
	);
});
</script>
<div id="launches-by-hours-chart" style="height: 300px, width:615px"></div>  </div>
      </div>

	<div class="statsTableHeader">趋势分析</div>
	<c:set value="1" var="vdisdate" scope="request"/>
	<%@ include file="commons/date-form.jsp"%>
	<div class="blockboxbg">
		<div class="blockbox">
			<div id="b4" onclick="changeTab(4,5,'b','bc','bitem','selbitem')" class="bitem selbitem">新用户</div>
			<div id="b0" onclick="changeTab(0,5,'b','bc','bitem','selbitem')" class="bitem">累计用户</div>
			<div id="b1" onclick="changeTab(1,5,'b','bc','bitem','selbitem')" class="bitem">启动用户</div>
			<div id="b2" onclick="changeTab(2,5,'b','bc','bitem','selbitem')" class="bitem">启动次数</div>
			<div id="b3" onclick="changeTab(3,5,'b','bc','bitem','selbitem')" class="bitem">平均使用时长</div>
		</div>
	</div>



	<div id="bc4" class="bitemcon" style="padding-bottom: 30px; ">
        <div class="swftit2">每日新用户趋势</div>
		<div id="daily-installtions-chart" class="bitemcon" style="width:680px; height: 260px; margin:auto">
<script type="text/javascript">
jQuery(function() {
	getChart(
		"daily-installtions-chart","",
		[${trendDate}],1,
		[{"name":"每日新用户趋势","data":[${trendNewImeiSize}]}],
		function() { return this.series.name + this.x +': '+ this.y;}
	);
});
</script>
			<div id="daily-installtions-chart" style="height: 300px, width:615px"></div>
		</div>
	</div>

      <div id="bc0" class="bitemcon" style="display:none;padding-bottom: 30px; ">
        <div class="swftit">累计用户趋势</div>
        <div id="daily-total-installtions-chart" class="bitemcon" style="width:680px; height: 260px; margin:auto">
<script type="text/javascript">
jQuery(function() {
	getChart(
		"daily-total-installtions-chart","",
		[${trendDate}],1,
		[{"name":"累计用户趋势","data":[${trendImeiSize}]}],
		function() { return this.series.name + this.x +': '+ this.y;}
	);
});
</script>
			<div id="daily-total-installtions-chart" style="height: 300px, width:615px"></div>
		</div>
	</div>

      <div id="bc1" class="bitemcon" style="display:none; padding-bottom: 30px;">
        <div class="swftit">每日启动用户趋势</div>
        <div id="daily-active-users-split-chart" class="bitemcon" style="width:680px; height: 260px; margin:auto">
<script type="text/javascript">
jQuery(function() {
	getChart(
		"daily-active-users-split-chart","",
		[${trendDate}],1,
		[{"name":"当日新用户","data":[${trendNewImeiSize}],"color":"#AA4643"},
		{"name":"老用户","data":[${trendOldImeiSize}],"color":"#4572A7"}],
		function() { return this.series.name + this.x +': '+ this.y;}
	);
});
</script>
			<div id="daily-active-users-split-chart" style="height: 300px, width:615px"></div>
		</div>
        <br/><br/>
	</div>
      <div id="bc2" class="bitemcon" style="display:none; padding-bottom: 30px;">
        <div class="swftit">每日启动次数变化趋势</div>
        <div id="daily-launches-chart" class="bitemcon" style="width:680px; height: 260px; margin:auto">
<script type="text/javascript">
jQuery(function() {
	/***
	**弹出文档框选项
	***/
	   $('.question').click(function() {
		      pop_dialog($('#question_wrap'), '818px');
		    });

	getChart(
		"daily-launches-chart","",
		[${trendDate}],1,
		[{"name":"每日启动次数变化趋势","data":[${trendVisitSize}]}],
		function() { return this.series.name + this.x +': '+ this.y;}
	);
});
</script>
			<div id="daily-launches-chart" style="height: 300px, width:615px"></div>
		</div>
	</div>

      <div id="bc3" class="bitemcon" style="display:none;padding-bottom: 30px;">
        <div class="swftit">每日平均使用时间变化趋势（单位：秒）</div>
        <div id="daily-average-durations-chart" class="bitemcon" style="width:680px; height: 260px; margin:auto">
<script type="text/javascript">
jQuery(function() {
	getChart(
		"daily-average-durations-chart","",
		[${trendDate}],1,
		[{"name":"每日平均使用时间变化趋势","data":[${averageDuration}]}],
		function() { return this.series.name + this.x +': '+ this.y;}
	);
});


</script>
			<div id="daily-average-durations-chart" style="height: 300px, width:615px"></div>
		</div>
	</div>
<script type="text/javascript">
$("div.digg_pagination a").live('click', function(e){
	var $dailyStatsDetails = $('#daily_stats_details');
    var initialStatsDetailsUrl = window.location.href;
    initialStatsDetailsUrl = initialStatsDetailsUrl.replace("/reports", "/reports/daily_stats_details");
    $dailyStatsDetails.load(initialStatsDetailsUrl);
    $("div.digg_pagination a").live('click', function(e){
      var $target = $(e.target);
      var link = $target.attr("href");
      $('<img>', { src : '/images/ajax-loader.gif',
        style : 'float: right; width:20px;margin-right:10px;margin-top:10px;'}
    ).insertAfter("div.digg_pagination");
      $.get(link, {}, function(response) {
        $dailyStatsDetails.html(response);
      })
      return false;
    });
});
</script>


	<div class="statsTableHeader">
		<div class="fr">
			<span><a href="#pager" onclick="vsubm2('exp_list=true')">输出EXCEL文件</a></span>
			<a href="#pager"  onclick="vsubm2('exp_list=true')">
				<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20">
			</a>
		</div>
		<strong>每日明细</strong>
	</div>

	<div id="daily_stats_details" style="margin-bottom: 40px;">
		<table class="infotab new_silver_table" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<th scope="col" class="new_silver_first_n" style="width: 120px;">日期</th>
					<th scope="col">新用户</th>
					<th scope="col">累计用户</th>
					<th scope="col">启动用户</th>
					<th scope="col">启动次数</th>
					<th scope="col" style="width: 120px;">平均使用时长</th>
				</tr>
<c:forEach items="${stats}" var="item" varStatus="status">
				<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
					<td class="new_silver_first_n">${item.date}</td>
					<td>${item.new_imei_size}<input type=hidden value="${item.new_imei_size}" size="25" maxlength="25"  id="NEWSFER_${status.index}" class="NEWSFER"></td>
					<td>${item.imei_size}</td>
					<td>${item.visit_imei_size}<input type=hidden value="${item.visit_imei_size}" size="25" maxlength="25"  id="NEWSFER3_${status.index}" class="NEWSFER3"></td>
					<td>${item.visit_size}<input type=hidden value="${item.visit_size}" size="25" maxlength="25"  id="NEWSFER4_${status.index}" class="NEWSFER4"></td>
					<td><c:set value="${item.average_duration/60.0}" var="ad"/>${fn:substring(ad,0,fn:indexOf(ad,'.'))}分${item.average_duration%60}秒</td>
				</tr>
</c:forEach>

				
			</tbody>
		</table>
		<table>
		<tr>
				<td style="width:134px">小计：</td>
				<td style="width:95px;"><span id='RECEIVE'></span></td>
				<td style="width:119px;"><span id='RECEIVE2'></span></td>
				<td style="width:118px;"><span id='RECEIVE3'></span></td>
				<td style="width:119px;"><span id='RECEIVE4'></span></td>
				<td></td>
				</tr>
				<tr>
				<td>总计：</td>
				<td><span>${count_new}</span></td>
				<td><span></span></td>
				<td><span>${count_launch}</span></td>
				<td><span>${count_visit}</span></td>
				<td></td>
				</tr>
		</table>
		<script type="text/javascript">
$(document).ready(
   function(){
     basecolumns(".NEWSFER","#NEWSFER_","#RECEIVE");
     basecolumns(".NEWSFER3","#NEWSFER3_","#RECEIVE3");
     basecolumns(".NEWSFER4","#NEWSFER4_","#RECEIVE4");
   });

</script>

		<%@ include file="commons/pager.jsp"%>

	</div>
</div>

	  <div id="question_wrap" style="display: none;">
  <div class="question_content_top">
    <h3 class="title">
      <span class="fl"><b>基本统计</b>词汇表</span>
      <span class="fr tg_rss"><img alt="" src="<%=path%>/statics/images/report_subscribe_close_normal.png"></span>
      <div class="clear"></div>
    </h3>
  </div>
  <div class="question_content_center">
    <table cellpadding="0" cellspacing="0" border="0" width="775">
      <tbody><tr>
        <td class="title">启动</td>
        <td>一个用户对应用程序的一次使用记为一次启动</td>
      </tr>
      <tr class="question_even">
        <td class="title">启动用户</td>
        <td>启动过应用程序的用户</td>
      </tr>
      <tr>
        <td class="title">新用户</td>
        <td>新安装应用(或者从未装有友盟SDK的老版本升级到装有友盟SDK的新版本)的用户，以服务器接收到用户启动的log为准</td>
      </tr>
      <tr class="question_even">
        <td class="title">新用户比例</td>
        <td>新用户/启动用户</td>
      </tr>

      <tr>
        <td class="title">平均使用时长</td>
        <td>用户平均单次使用应用程序的时间</td>
      </tr>
      <tr class="question_even">
        <td class="title">活跃率</td>
        <td>启动用户/累计用户</td>
      </tr>

      <tr>
        <td class="title">累计用户</td>
        <td>截止当前，启动过应用程序的用户(多个不同的模拟器只会被算作为一个新用户，因为所有模拟器的IMEI都为00000000000000)</td>
      </tr>
      <tr class="question_even">
        <td class="title">累计启动</td>
        <td>截止当前，应用程序的启动次数</td>
      </tr>

      <tr>
        <td class="title">7天活跃用户（%）*</td>
        <td>截止昨天，过去7天内，启动过应用程序的用户（7天活跃用户/累计用户）</td>
      </tr>
      <tr class="question_even">
        <td class="title">14天活跃用户（%）*</td>
        <td>截止昨天，过去14天内, 启动过应用程序的用户（14天活跃用户/累计用户）</td>
      </tr>

      <tr>
        <td class="title"> 14天沉默用户（%）*</td>
        <td>截止昨天, 过去14天内，没有启动过应用程序的用户（14天沉默用户/累计用户）</td>
      </tr>
      <tr class="question_even">
        <td class="title">活跃用户</td>
        <td>即启动用户</td>
      </tr>

    </tbody></table>
  </div>
  </div>