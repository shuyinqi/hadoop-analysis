<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.mapbar.analyzelog.report.entity.VersionVVO"%>
<%
	StringBuffer seriesData = new StringBuffer();seriesData.append("[");
	StringBuffer xAxisData = new StringBuffer();xAxisData.append("[");
	List<VersionVVO> list=(List<VersionVVO>)request.getAttribute("versions");
	String vname=null;boolean vflag=false;
	for(int i=0;i<list.size();i++){
		VersionVVO vo=list.get(i);
		xAxisData.append("'").append(vo.getDate()).append("'");
		if(vname==null||!vname.equals(vo.getVersion())){
			if(vflag)seriesData.append("]").append("}").append(",");
			vname=vo.getVersion();
			seriesData.append("{name:'").append(vname).append("',data:[");
			vflag=true;
		}
			seriesData.append(vo.getLaunchs());
				seriesData.append(",");
		
		if(i==list.size()-1){
			xAxisData.append("]");
		}else {
			xAxisData.append(",");
		}
	}
	seriesData.append("]}]");
	%>
	
	<%-- <script type="text/javascript">
	var chart;
	$(document).ready(
			function() {
				chart = new Highcharts.Chart({
					chart : {
						renderTo : 'message-count-chart',
						type : 'spline'
					},
					title : {
						text : ''
					},
					subtitle : {
						text : ''
					},
					xAxis : {
						categories :
<%=xAxisData.toString()%>
	},
					yAxis : {
						title : {
							text : ' '
						}
					},
					tooltip : {
						formatter : function() {
							return '<b>' + this.series.name + '</b><br/>'
									+ this.x + ': ' + this.y + '';
						}
					},
					plotOptions : {
						line : {
							dataLabels : {
								enabled : true
							},
							enableMouseTracking : false
						}
					}, 
					series :
<%=seriesData.toString()%>
	});
			});
</script> --%>
<div class="bbox_con">
	<div class="statsTableHeader"><strong>渠道统计</strong> <span class="question fr"></span><div class="clear"></div></div>
	<div class="time_select_wrap">
		<div class="statsTableHeader">
			<strong>选择时段</strong>
			<div class="selbox">
				<input class="datainp first_date date" type="date" value="${fromdate}" name="start_date"><span>到</span> <input class="datainp last_date date" type="date" value="${todate}" name="end_date">
				<input type="button" class="upbtn" value="&nbsp;更新&nbsp;" onclick="subm(this)">
			</div> 
			<div class="mnum" style="color:#444">
				<a href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports?period=days_7" class="">过去一周</a> | <a class="current">过去一月</a> | <a href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports?period=days_90" class="">过去三月</a> | <a href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports?period=days_all" class="">全部</a>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="blockboxbg" style="margin: 10px 0;">
		<div class="blockbox">
			<div id="a0" onclick="changeTab(0,4,'a','ac','bitem','selbitem')" class="bitem selbitem">市场新增用户</div>
			<div id="a1" onclick="changeTab(1,4,'a','ac','bitem','selbitem')" class="bitem">市场活跃用户</div>
			<div id="a2" onclick="changeTab(2,4,'a','ac','bitem','selbitem')" class="bitem">预装新增用户</div>
			<div id="a3" onclick="changeTab(3,4,'a','ac','bitem','selbitem')" class="bitem">预装活跃用户</div>
		</div>
	</div>

<div id="ac0" class="bitemcon" style="padding-bottom: 30px;">
	<table class="datatab long new_silver_table" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<th scope="col" class="new_silver_first_n">渠道</th>
			<th scope="col">今日新用户</th>
			<th scope="col">昨日新用户</th>
			<th scope="col">前日新用户</th>
			<th scope="col">7天前新用户</th>
			<th scope="col">渠道累计新用户(%)</th>
			<th scope="col">明细</th>
		</tr>
<c:forEach items="${summaryMarketNew}" var="item" varStatus="status">
		<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
			<td class="new_silver_first_n">${item.name}</td>
			<td>${item.imei_size0}</td>
			<td>${item.imei_size1}</td>
			<td>${item.imei_size2}</td>
			<td>${item.imei_size7}</td>
			<td>${item.name_imei_size}<c:set value="${item.name_imei_size/item.type_imei_size*100.0}" var="ad"/>(${fn:substring(ad,0,fn:indexOf(ad,'.')+3)}%)</td>
			<td><a href="<%=basePath%>apps/${app.id}/daily_stats_detail_for_channel?channelname=${item.name_imei_size}&fromdate=${fromdate}&todate=${todate}" class="load_channel_detail_btn">展开</a> <img alt="Loading" class="loading" src="/images/loading.gif?1329537551" style="display: none;" /></td>
		</tr>
		<tr class="channel_detail_tr hidden">
		<td class="channel_detail_area" colspan="7"><div class="fr">
</div>
<div class="clear"></div>
  <div>
    渠道明细：${ item.name}
  </div>
  <table cellspacing="0" cellpadding="0" border="0" style="margin-bottom: 20px;" class="datatab long new_silver_table">
    <tbody><tr>
      <th class="new_silver_first_n" scope="col">日期</th>
      <th scope="col">新用户</th>
      <th scope="col">启动用户</th>
    </tr>
    <c:forEach items="${markets}" var="item" varStatus="status">
      <tr>
        <td>${item.date}</td>
        <td>${item.news}</td>
        <td>${item.launchs}</td>
      </tr>
      </c:forEach>
      
  </tbody></table>
</td>
		</tr>
		</c:forEach>

	</table>
</div>
<div id="ac1" class="bitemcon" style="display: none; padding-bottom: 30px;">
	<table class="datatab long new_silver_table" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<th scope="col" class="new_silver_first_n">渠道</th>
			<th scope="col">今日启动用户</th>
			<th scope="col">昨日启动用户</th>
			<th scope="col">前日启动用户</th>
			<th scope="col">7天前启动用户</th>
			<th scope="col">渠道累计用户(%)</th>
			<th scope="col">明细</th>
		</tr>
<c:forEach items="${summaryMarketVisit}" var="item" varStatus="status">
		<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
			<td class="new_silver_first_n">${item.name}</td>
			<td>${item.imei_size0}</td>
			<td>${item.imei_size1}</td>
			<td>${item.imei_size2}</td>
			<td>${item.imei_size7}</td>
			<td>${item.name_imei_size}<c:set value="${item.name_imei_size*100.0/item.type_imei_size}" var="ad"/>(${fn:substring(ad,0,fn:indexOf(ad,'.'))}%)</td>
			<td><a href="<%=basePath%>apps/${app.id}/daily_stats_detail_for_channel?channelname=${item.name_imei_size}&fromdate=${fromdate}&todate=${todate}" class="load_channel_detail_btn">展开</a> <img alt="Loading" class="loading" src="/images/loading.gif?1329537551" style="display: none;" /></td>
		</tr>
		<tr class="channel_detail_tr hidden">
		<td class="channel_detail_area" colspan="7"><div class="fr">
</div>
<div class="clear"></div>
  <div>
    渠道明细：${ item.name}
  </div>
  <table cellspacing="0" cellpadding="0" border="0" style="margin-bottom: 20px;" class="datatab long new_silver_table">
    <tbody><tr>
      <th class="new_silver_first_n" scope="col">日期</th>
      <th scope="col">新用户</th>
      <th scope="col">启动用户</th>
    </tr>
    <c:forEach items="${markets}" var="item" varStatus="status">
      <tr>
        <td>${item.date}</td>
        <td>${item.news}</td>
        <td>${item.launchs}</td>
      </tr>
      </c:forEach>
      
  </tbody></table>
</td>
		</tr>
</c:forEach>

	</table>
</div>
<div id="ac2" class="bitemcon" style="display: none; padding-bottom: 30px;">
	<table class="datatab long new_silver_table" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<th scope="col" class="new_silver_first_n">品牌</th>
			<th scope="col">今日新用户</th>
			<th scope="col">昨日新用户</th>
			<th scope="col">前日新用户</th>
			<th scope="col">7天前新用户</th>
			<th scope="col">渠道累计用户(%)</th>
			<th scope="col">明细</th>
		</tr>
<c:forEach items="${summaryInstalledNew}" var="item" varStatus="status">
		<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
			<td class="new_silver_first_n">${item.name}</td>
			<td>${item.imei_size0}</td>
			<td>${item.imei_size1}</td>
			<td>${item.imei_size2}</td>
			<td>${item.imei_size7}</td>
			<td>${item.name_imei_size}<c:set value="${item.name_imei_size*100.0/item.type_imei_size}" var="ad"/>(${fn:substring(ad,0,fn:indexOf(ad,'.'))}%)</td>
			<td><a href="<%=basePath%>apps/${app.id}/daily_stats_detail_for_channel?channelname=${item.name_imei_size}&fromdate=${fromdate}&todate=${todate}" class="load_channel_detail_btn">展开</a> <img alt="Loading" class="loading" src="/images/loading.gif?1329537551" style="display: none;" /></td>
		</tr>
		<tr class="channel_detail_tr hidden">
		<td class="channel_detail_area" colspan="7"><div class="fr">
</div>
<div class="clear"></div>
  <div>
    渠道明细：${ item.name}
  </div>
  <table cellspacing="0" cellpadding="0" border="0" style="margin-bottom: 20px;" class="datatab long new_silver_table">
    <tbody><tr>
      <th class="new_silver_first_n" scope="col">日期</th>
      <th scope="col">新用户</th>
      <th scope="col">启动用户</th>
    </tr>
    <c:forEach items="${instals}" var="item" varStatus="status">
      <tr>
        <td>${item.date}</td>
        <td>${item.news}</td>
        <td>${item.launchs}</td>
      </tr>
      </c:forEach>
      
  </tbody></table>
</td>
		</tr>
</c:forEach>

	</table>
</div>
<div id="ac3" class="bitemcon" style="display: none; padding-bottom: 30px;">
	<table class="datatab long new_silver_table" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<th scope="col" class="new_silver_first_n">品牌</th>
			<th scope="col">今日访问用户</th>
			<th scope="col">昨日访问用户</th>
			<th scope="col">前日访问用户</th>
			<th scope="col">7天前访问用户</th>
			<th scope="col">渠道累计用户(%)</th>
			<th scope="col">明细</th>
		</tr>
<c:forEach items="${summaryInstalledNew}" var="item" varStatus="status">
		<tr class="${status.index%2==0?'new_silver_odd':'new_silver_even'}">
			<td class="new_silver_first_n">${item.name}</td>
			<td>${item.imei_size0}</td>
			<td>${item.imei_size1}</td>
			<td>${item.imei_size2}</td>
			<td>${item.imei_size7}</td>
			<td>${item.name_imei_size}<c:set value="${item.name_imei_size*100.0/item.type_imei_size}" var="ad"/>(${fn:substring(ad,0,fn:indexOf(ad,'.'))}%)</td>
			<td><a href="<%=basePath%>apps/${app.id}/daily_stats_detail_for_channel?channelname=${item.name_imei_size}&fromdate=${fromdate}&todate=${todate}" class="load_channel_detail_btn">展开</a> <img alt="Loading" class="loading" src="/images/loading.gif?1329537551" style="display: none;" /></td>
		</tr>
		<tr class="channel_detail_tr hidden">
		<td class="channel_detail_area" colspan="7"><div class="fr">
</div>
<div class="clear"></div>
  <div>
    渠道明细：${ item.name}
  </div>
  <table cellspacing="0" cellpadding="0" border="0" style="margin-bottom: 20px;" class="datatab long new_silver_table">
    <tbody><tr>
      <th class="new_silver_first_n" scope="col">日期</th>
      <th scope="col">新用户</th>
      <th scope="col">启动用户</th>
    </tr>
    <c:forEach items="${instals}" var="item" varStatus="status">
      <tr>
        <td>${item.date}</td>
        <td>${item.news}</td>
        <td>${item.launchs}</td>
      </tr>
      </c:forEach>
      
  </tbody></table>
</td>
		</tr>
</c:forEach>

	</table>
</div>

<table cellspacing="0" cellpadding="0" border="0" style="margin: 10px auto 10px; padding-top: 5px;" class="benchmark_tips"><tbody><tr><td>注：按<b>原始安装渠道</b>来统计新增用户、启动用户、累计用户。即用户初次安装应用的来源是渠道A，后又在渠道B更新了应用版本，但该用户仍会被记为是渠道A的启动用户、累计用户。</td></tr></tbody></table>

<%-- <div id="channel_daily_stats_filter_params" class="filter_params">
	<div class="statsTableHeader">
		<strong>渠道变化趋势</strong>
		<div class="fr" style="font-weight: normal; font-size: 13px;">
			 过滤规则： <select id="ajax_method" name="ajax_method" style="margin-right: 5px;"><option value="MarketNew" selected="selected">市场新增用户</option><option value="MarketVisit">市场启动用户</option><option value="InstalledNew">预装新增用户</option><option value="InstalledVisit">预装启动用户</option></select> <a href="#" class="choose_channels">选择对比渠道</a>
			<div class="hidden channel_select question_wrap_new_a_box">
				<div class="question_content_top_new_js_a"><h3 class="title"><span class="fl">选择对比渠道</span> <span class="fr tg_rss"><img alt="" src="<%=basePath%>/statics/images/report_subscribe_close_normal.png" /></span><div class="clear"></div></h3></div>
				<div class="question_content_center_new_b">
					<ul style="padding: 15px; max-height: 300px; overflow: auto;">
						<li style="float: left; margin-right: 20px;"><input type="checkbox" name="channels" value="android markets" checked> android markets</input></li>
						<li style="float: left; margin-right: 20px;"><input type="checkbox" name="channels" value="other" checked> other</input></li>
						<li style="float: left; margin-right: 20px;"><input type="checkbox" name="channels" value="bbs" checked> bbs</input></li>
						<li style="float: left; margin-right: 20px;"><input type="checkbox" name="channels" value="market" checked> market</input></li>
						<li style="float: left; margin-right: 20px;"><input type="checkbox" name="channels" value="homepage" checked> homepage</input></li>
						<li style="float: left; margin-right: 20px;"><input type="checkbox" name="channels" value="huawei" checked> huawei</input></li>
					</ul>
					<div class="clear"></div>
					<div style="padding: 10px;">
						<span class="fl" style="margin: 5px 10px 0 0;"><input type="checkbox" class="select_all_btn"> 选择全部</input></span> <a href="#update_channel_trends_btn" class="channel_select_commit certain_btn fr">确定</a> <span class="fr" style="margin: 5px 10px 0 0;"><input type="checkbox" name="set_default" value="true">将所选项设置为默认显示</input></span>
						<div class="clear"></div>
					</div>
				</div>
				<div class="question_content_bottom_new_c"></div>
			</div>
			<input id="update_channel_trends_btn" type="button" class="upbtn" value="&nbsp;更新&nbsp;" onclick="subm(this)"/>
		</div>
		<div class="clear"></div>
	</div>
	<div class="time_select_wrap">
		<div class="statsTableHeader">
			<strong>选择时段</strong>
			<div class="selbox">
				<input class="datainp first_date" type="date" value="${fromdate}" name="fromdate"/> <span>到</span> <input class="datainp last_date" type="date" value="${todate}" name="todate"/>
			</div>
			<div class="mnum" style="color:#444">
				<a href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports?period=days_7" class="period_selector">过去一周</a> | <a class="current">过去一月</a> | <a href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports?period=days_90" class="period_selector">过去三月</a> | <a href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports?period=days_all" class="period_selector">全部</a>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div> --%>
<div class="meassage_contents">

										<div id="message-count-tab" class="meassage_contents_list">
											<div id="message-count-chart" class="line_chart_common"
												style="height: 0px; width: 95%;"></div>
										</div>
									</div>
									
<div style="margin-bottom: 30px;"></div>
</div>
<script type="text/javascript">
  jQuery(function() {
    jQuery('a.load_channel_detail_btn').click(function(){
      var target = jQuery(this).closest('tr').next('tr.channel_detail_tr').children('td');
      if ( jQuery.trim(target.html()) == '' ){
        var loadingImg = jQuery(this).next('img').show();
        var link = jQuery(this);
        target.load(this.href, function(){
          loadingImg.hide();
          target.closest('tr').show();
          link.html('收起');
        });
      }else{
        if( jQuery(this).html() == '展开'){
          jQuery(this).html('收起');
        }else{
          jQuery(this).html('展开');
        }
        jQuery(this).closest('tr').next('tr.channel_detail_tr').toggle();
      }
      return false;
    });

    jQuery('.select_all_btn').click(function(){
      if( jQuery(this).attr('checked')){
        jQuery(this).closest('div.question_content_center_new_b').find(":checkbox[name='channels[]']").attr('checked', 'checked');
      }else{
        jQuery(this).closest('div.question_content_center_new_b').find(":checkbox[name='channels[]']").removeAttr('checked');
      }
    });

    jQuery('a.choose_channels').click(function(){
      var channels_select = jQuery(this).siblings('div.channel_select');
      jQuery.blockUI({
        css: {color: '#cccccc',border:0,width:'500px',top:'20%',textAlign:'left',background:'none'},
        message: channels_select
      });
    });

    jQuery('a.channel_select_commit').click(function(){
      var upbtn = jQuery(this.getAttribute("href"));
      jQuery.unblockUI({
        onUnblock: function(){upbtn.click();}
      });
      return true;
    });

    //jQuery('.filter_params .upbtn').click();
  });

</script>
