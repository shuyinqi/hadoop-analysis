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

<script type="text/javascript" src="<%=basePath%>statics/js/switchHeader.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/dialog.js"></script>
<script type="text/javascript" src="<%=basePath%>statics/js/date.js"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/css/layout.css" media="screen"/>

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
jQuery(function() {
	jQuery('.period_selector').each(function() {
		var period_param = jQuery(this).attr('href').replace('?', '');
		var base_url = window.location.href;
		base_url.replace(/[\?&]start_date=[^&]+/, '').replace(/[\?&]end_date=[^&]+/, '');
		if (base_url.indexOf('?') > 0) {
			if (base_url.indexOf('period=') > 0)
				this.href = base_url.replace(/period=[^&]+/, period_param);
			else
				this.href = base_url + '&' + period_param;
		} else {
			this.href = base_url + '?' + period_param;
		}
		jQuery(this).removeClass('period_selector');
	});
});

function setDate(dates) {
	var dates_array = dates.split(";");
	var first_date = dates_array[0];
	var last_date = dates_array[1];
	jQuery('#first_date').val(first_date);
	jQuery('#last_date').val(last_date);
}
var subm = function(upbtn) {
	var par1 = jQuery(upbtn).siblings('.first_date').first().val();
	var par2 = jQuery(upbtn).siblings('.last_date').first().val();
	window.location.href = trimUrl(window.location.href) + "?fromdate=" + par1 + "&todate=" + par2;
};
function trimUrl(url) {
	if (url != null && url.length > 1) {
		var temp = url[url.length - 1] == '#' ? url.substring(0, url.length - 1) : url;
		var pos = temp.indexOf("?", 0);
		if (pos != -1)temp = url.substring(0, pos);
		return temp[temp.length - 1] == '/' ? temp.substring(0, temp.length - 1) : temp;
	} else
		return url;
}
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

var basecolumns=function(class1,colid,textid){
	var s=0;
    var n=$(class1).size();
    for (var i=0 ; i <n ; i++){
   a=jQuery.trim($(colid+i).val());
   if(a)
    {s +=parseFloat(a);}
    }
    $(textid).text(s);
}

var base2columns=function(class1,colid,textid){
	var c=0;
	var s=0;
    var n=$(class1).size();
    for (var i=0 ; i <n ; i++){
    	var b=0; var temp;
    	var pos = colid.indexOf("#");
		if (pos != -1)temp = colid.substring(pos+1,colid.length);
  	 var n2=$("."+temp+i).size();
	  	for (var j=0 ; j <n2 ; j++){
	  		c=jQuery.trim($(colid+i+"_"+j).val());
	  		if(c)b+=parseFloat(c);
	  	}
	  	$(textid+i).text(b);
    }
    
}
</script>
