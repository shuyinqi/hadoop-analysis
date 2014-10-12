<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/base.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/pagination.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/jquery-ui.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/jquery.qtip.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/dateinput.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/header.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/remind.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/nyroModal.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/css_fix.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/user_event.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/stat/myproduct_layout.css" media="screen"/>
<script type="text/javascript" src="<%=basePath%>statics/js/stat/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/stat/jquery.qtip.pack.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/stat/jquery.jeditable.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/dialog.js"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/layout.css" media="screen"/>

<script type="text/javascript" src="<%=basePath%>statics/js/switchHeader.js"></script>

<style type="text/css">
.b_home_tips{
  display: block;
  background-position:15px -4px;

}
.b_home_tips .b_home_tips_con{
  width: 91%;
  font-size: 14px;
}
.b_home_tips .b_home_tips_con{
  line-height: 120%;
  font-size: 14px;
  width: 91%;
}
.b_home_tips .b_ht_close{

}
#salonForm_form .status{
  width: 68px;
}
#salonForm_wrap input.in{
  width: 220px;
}
</style>

<script type="text/javascript">
</script>
<script type="text/javascript">


      jQuery(function() {
          	
        if ((cookies_setter.get('tip_closed') == 'true')) {
          jQuery(".b_home_tips").hide();
          jQuery('#b_center').removeAttr('style');
          jQuery('body').removeAttr('style');
        };

        jQuery(".event_tip_close").click(function(){
          jQuery(".event_show_tips").hide();
          cookies_setter.set('event_show_tips', true);
        });
        if (!(cookies_setter.get('event_show_tips') == 'true')) {
          jQuery(".event_show_tips").show();
        }

        /*给tr 的 hover 添加背景颜色 begin*/
        jQuery(".new_silver_table tr").mouseover(
        function() {
          jQuery(this).addClass("current");
        }).mouseout(function() {
          jQuery(this).removeClass("current");
        });
        /*给tr 的 hover 添加背景颜色 end*/


        //给tr 的 hover 添加背景颜色
        jQuery(".b_rightconten table thead tr").mouseover(function() {
          jQuery(this).children().addClass("tr_hover");
        });
        jQuery(".b_rightconten table thead tr").mouseout(function() {
          jQuery(this).children().removeClass("tr_hover");
        });


        jQuery('a.apply_promotion,a.apply_promotion_old').click(function() {

        jQuery.blockUI({
            css: {color: '#cccccc',border:0,width:'460px',height:'500px',top:'20%',textAlign:'left',background:'none'},
            message: jQuery('#salonForm_wrap')
          });
          return false;
        });


    jQuery('.b_home_tips a.click_link_url').live('click', function() {

    jQuery.blockUI({
        css: {color: '#cccccc',border:0,width:'460px',height:'500px',top:'20%',textAlign:'left',background:'none'},
        message: jQuery('#salonForm_wrap')
      });
      return false;
    });

});

</script>
