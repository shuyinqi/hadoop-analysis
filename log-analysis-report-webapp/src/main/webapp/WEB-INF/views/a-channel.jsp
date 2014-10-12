<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
	<%@ include file="commons/meta.jsp"%>
	<%@ include file="/WEB-INF/views/meta-stat.jsp"%>
    <title>Android Demo App 分发渠道分析</title>
</head>

<body>
    <div id="b_wrap" class="web_width">
		<%@ include file="commons/top.jsp"%>

          <div id="b_center">
 		<%@ include file="/WEB-INF/views/body-leftmenu.jsp"%>

<div class="fr b_rightconten">
	<div class="fl b_right_menu">
		<ul>
			<li><a href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports" class="click current " id="reports_tab">统计分析</a></li>
			<li><a href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/error_types" class="click " id="developer_tools_tab">开发工具</a></li>
			<div class="clear"></div>
		</ul>
	</div>
	<span class="fr b_docu_link"><a href="http://www.umeng.com/doc/home.html" target="_blank">文档中心</a></span>
	<div class="clear">&nbsp;</div>
	<div class="b_right_public">
		<div class="conright">
			<div id="main">
				<h3 class="title" style="display:none;">
					<span class="text fl">Android Demo App</span>
					<span class="sub_text fl"><span class="days">统计第<span class="daynums">&nbsp;604&nbsp;</span>天</span></span>
					<div class="clear"></div>
				</h3>
<%@ include file="/WEB-INF/views/a-channel-bbox.jsp"%>
			</div>
			<div class="bs_bottom"><div class="bs_bl"></div><div class="bs_br"></div></div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/a-channel-question.jsp"%>


<script type="text/javascript" src="/javascripts/report_stats_filter.js"></script>
<script type="text/javascript">
  $(function() {
    $('a.load_channel_detail_btn').click(function(){
      var target = $(this).closest('tr').next('tr.channel_detail_tr').children('td');
      if ( $.trim(target.html()) == '' ){
        var loadingImg = $(this).next('img').show();
        var link = $(this);
        target.load(this.href, function(){
          loadingImg.hide();
          target.closest('tr').show();
          link.html('收起');
        });
      }else{
        if( $(this).html() == '展开'){
          $(this).html('收起');
        }else{
          $(this).html('展开');
        }
        $(this).closest('tr').next('tr.channel_detail_tr').toggle();
      }
      return false;
    });

    $('.select_all_btn').click(function(){
      if( $(this).attr('checked')){
        $(this).closest('div.question_content_center_new_b').find(":checkbox[name='channels[]']").attr('checked', 'checked');
      }else{
        $(this).closest('div.question_content_center_new_b').find(":checkbox[name='channels[]']").removeAttr('checked');
      }
    });

    $('a.choose_channels').click(function(){
      var channels_select = $(this).siblings('div.channel_select');
      $.blockUI({
        css: {color: '#cccccc',border:0,width:'500px',top:'20%',textAlign:'left',background:'none'},
        message: channels_select
      });
    });

    $('a.channel_select_commit').click(function(){
      var upbtn = $(this.getAttribute("href"));
      $.unblockUI({
        onUnblock: function(){upbtn.click();}
      });
      return true;
    });

    $('.filter_params .upbtn').click();
  });

</script>

          <div class="clear"></div>
        </div>

      </div>

<%@ include file="/WEB-INF/views/body-copyright.jsp"%>  
</body>
</html>