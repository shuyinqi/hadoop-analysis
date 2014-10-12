/***
 * 市场活跃用户小计功能
 */
function summaryMarketVisitTotal(){
     basecolumns(".RLAUNCH","#RLAUNCH_","#RLAUNCH");
     basecolumns(".RLAUNCH2","#RLAUNCH2_","#RLAUNCH2");
     basecolumns(".RLAUNCH3","#RLAUNCH3_","#RLAUNCH3");
     basecolumns(".RLAUNCH4","#RLAUNCH4_","#RLAUNCH4");
     base2columns(".RLAUNCH","#LAUNCHDET_","#LAUNCHDET_");
     base2columns(".RLAUNCH","#LAUNCHDET2_","#LAUNCHDET2_");
}
function summaryInstalledNewTotal(){
	
     basecolumns(".ONSETUP","#ONSETUP_","#ONSETUP");
     basecolumns(".ONSETUP2","#ONSETUP2_","#ONSETUP2");
     basecolumns(".ONSETUP3","#ONSETUP3_","#ONSETUP3");
     basecolumns(".ONSETUP4","#ONSETUP4_","#ONSETUP4");
     base2columns(".ONSETUP","#SETUPDET_","#SETUPDET_");
     base2columns(".ONSETUP","#SETUPDET2_","#SETUPDET2_");
}
function summaryInstalledVisitTotal(){
	  basecolumns(".ONLAUNCH","#ONLAUNCH_","#ONLAUNCH");
     basecolumns(".ONLAUNCH2","#ONLAUNCH2_","#ONLAUNCH2");
     basecolumns(".ONLAUNCH3","#ONLAUNCH3_","#ONLAUNCH3");
     basecolumns(".ONLAUNCH4","#ONLAUNCH4_","#ONLAUNCH4");
     base2columns(".ONLAUNCH","#ONLADET_","#ONLADET_");
     base2columns(".ONLAUNCH","#ONLADET2_","#ONLADET2_");
}
/***
 * 市场活跃用户拼接html
 * @param {} json  获得数据的
 */
function summaryMarketVisitAppendJson(json,id,exlParameter,exlParameter2,exlClass,exlClass2,tableid,trid,headName){
	
		var channelChild ='<div class="clear"></div><div class="fr">' +
				'<span><a href="#pager" onclick="vsubm2(\''+exlParameter+'\')">输出EXCEL文件</a></span>' +
				'<a href="#pager"  onclick="vsubm2(\''+exlParameter+'\')"><img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20"></a>' +
				'</div>' +
				'<table class="datatab long new_silver_table" border="0" id="'+tableid+'" cellspacing="0" cellpadding="0">' +
				'<thead><tr>' +
				'<th scope="col" class="new_silver_first_n" onclick="appendSort();" >渠道</th>' +
				'<th scope="col" onclick="appendSort();">今日'+headName+'用户</th>' +
				'<th scope="col" onclick="appendSort();">昨日'+headName+'用户</th>' +
				'<th scope="col" onclick="appendSort();">前日'+headName+'用户</th>' +
				'<th scope="col" onclick="appendSort();">7天前'+headName+'用户</th>' +
				'<th scope="col" onclick="appendSort();" class="default_order">渠道累计用户(%)</th>' +
				'<th scope="col" onclick="appendSort();">明细</th>' +
				'</tr></thead>' +
				'<tbody>';
				for(var i=0;i<json.length;i++){
					var ch_fl;
					if(json[i].type_imei_size==0){
						ch_fl="0";
					}else{
					ch_fl=(json[i].name_imei_size*100.0)/json[i].type_imei_size+"";
					}
					var sti = ch_fl.indexOf(".")==-1?0:ch_fl.indexOf(".");
					var ch_sf=i%2==0?'new_silver_odd':'new_silver_even';
					channelChild+='<tr class="'+ch_sf+'" id="'+trid+i+'">'+
					    '<td >'+json[i].name;
					    if(json[i].note!=null&&json[i].note!=""){
					       channelChild+='('+json[i].note+')';    
					    }
					channelChild+='</td>'+	
					    '<td>'+json[i].imei_size0+'<input type=hidden value="'+json[i].imei_size0+'" size="5" maxlength="25"  id="'+exlClass+'_'+i+'" class="'+exlClass+'"></td>' +
					    '<td>'+json[i].imei_size1+'<input type=hidden value="'+json[i].imei_size1+'" size="5" maxlength="25"  id="'+exlClass+'2_'+i+'" class="'+exlClass+'2"></td>' +
					    '<td>'+json[i].imei_size2+'<input type=hidden value="'+json[i].imei_size2+'" size="5" maxlength="25"  id="'+exlClass+'3_'+i+'" class="'+exlClass+'3"></td>' +
					    '<td>'+json[i].imei_size7+'<input type=hidden value="'+json[i].imei_size7+'" size="5" maxlength="25"  id="'+exlClass+'4_'+i+'" class="'+exlClass+'4"></td>'+
					    '<td>'+json[i].name_imei_size+'('+ch_fl.substring(0,sti + 3)+'%)</td>'+
					    '<td><a style="cursor:pointer"   class="load_channel_detail_btn">展开</a></td>'+
					    '</tr>'+
					  '<tr class="channel_detail_tr hidden" id="'+trid+i+'_det">'+
					    '<td class="channel_detail_area" colspan="7">' +
					    '<div class="fr"></div>' +
					    '<div class="clear"></div>' +
					    '<div>渠道明细：'+json[i].name+
					    '<div class="fr">' +
					    /*'<span><a href="#pager" onclick="vsubm2(\''+exlParameter2+'='+json[i].name+'\')">输出EXCEL文件</a></span>' +
					    '<a href="#pager"  onclick="vsubm2(\''+exlParameter2+'='+json[i].name+'\')">'+'<img alt="输出EXCEL文件" height="20" src="../../statics/images/csv.png?1326712342" title="输出EXCEL文件" width="20"></a>' +
					    */
					    '</div></div>'+							
					    '<table cellspacing="0" cellpadding="0" border="0" style="margin-bottom: 20px;" class="datatab long new_silver_table">' +
					    '<thead> ' +
					    '<tr>' +
					    '<th class="new_silver_first_n" scope="col">日期</th>'+
					    '<th scope="col">新用户</th><th scope="col">启动用户</th>' +
					    '</tr></thead><tbody>';				
						for(var j=0;j<json[i].vlist.length;j++){
						     channelChild+='<tr><td>'+json[i].vlist[j].date+'</td>' +
						  		 '<td>'+json[i].vlist[j].news+'<input type=hidden value="'+json[i].vlist[j].news+'" size="5" maxlength="25"  id="'+exlClass2+'_'+i+'_'+j+'" class="'+exlClass2+'_'+i+'"></td>'+						
						         ' <td>'+json[i].vlist[j].launchs+'<input type=hidden value="'+json[i].vlist[j].launchs+'" size="5" maxlength="25"  id="'+exlClass2+'2_'+i+'_'+j+'" class="'+exlClass2+'2_'+i+'"></td>'+							      
						      '</tr>';
						}
					    channelChild+='<tr><td>小计：</td>' +
					    	'<td><span id="'+exlClass2+'_'+i+'"></span></td>' +
					    	'<td><span id="'+exlClass2+'2_'+i+'"></span></td></tr>' +
					    	'</tbody></table>';
				}
				channelChild+='' +
						/*'<tr>' +
						'<td>小计：</td>' +
						'<td><span id=\''+exlClass+'\'></span></td>' +
						'<td><span id=\''+exlClass+'2\'></span></td>' +
						'<td><span id=\''+exlClass+'3\'></span></td>' +
						'<td><span id=\''+exlClass+'\'></span></td>' +
						'<td></td><td></td>'+
						'</tr>'+*/
				        /*'<tr class="hidden">' +
				        '<td class="hidden"></td>' +
				        '<td class="hidden"></td>' +
				        '<td class="hidden"></td>' +
				        '<td class="hidden"></td>' +
				        '<td class="hidden"></td>' +
				        '<td class="hidden"></td>' +
				        '<td class="hidden"></td>' +
				        '</tr>' +*/
				        '</tbody>' +
				        '</table>';
				      
				 if(id=='ac1'){
				 	channelChild+=""+
				 	  '<table border="0" cellspacing="0" cellpadding="0">'+
				        '<tr>'+
				        '<td style="width:132px;">小计：</td>' +
						'<td style="width:102px;"><span id=\''+exlClass+'\'></span></td>' +
						'<td style="width:102px;"><span id=\''+exlClass+'2\'></span></td>' +
						'<td style="width:100px;"><span id=\''+exlClass+'3\'></span></td>' +
						'<td style="width:115px;"><span id=\''+exlClass+'4\'></span></td>' +
						'<td style="width:126px;"></td>' +
						'<td style="width:24px;"></td>'+
						''+
					    '</tr>'+
					    '</table>';
				$("#ac1").append(channelChild);						
				 }
				 if(id=='ac2'){
				 	channelChild+=""+
				 	  '<table border="0" cellspacing="0" cellpadding="0">'+
				        '<tr>'+
				        '<td style="width:94px;">小计：</td>' +
						'<td style="width:103px;"><span id=\''+exlClass+'\'></span></td>' +
						'<td style="width:104px;"><span id=\''+exlClass+'2\'></span></td>' +
						'<td style="width:104px;"><span id=\''+exlClass+'3\'></span></td>' +
						'<td style="width:113px;"><span id=\''+exlClass+'4\'></span></td>' +
						'<td style="width:147px;"></td>' +
						'<td style="width:55px;"></td>'+
						''+
					    '</tr>'+
					    '</table>';
				 $("#ac2").append(channelChild);
				 }
				 if(id=='ac3'){
				 	channelChild+=""+
				 	  '<table border="0" cellspacing="0" cellpadding="0">'+
				        '<tr>'+
				        '<td style="width:86px;">小计：</td>' +
						'<td style="width:110px;"><span id=\''+exlClass+'\'></span></td>' +
						'<td style="width:110px;"><span id=\''+exlClass+'2\'></span></td>' +
						'<td style="width:110px;"><span id=\''+exlClass+'3\'></span></td>' +
						'<td style="width:119px;"><span id=\''+exlClass+'4\'></span></td>' +
						'<td style="width:135px;"></td>' +
						'<td style="width:50px;"></td>'+
						''+
					    '</tr>'+
					    '</table>';
				 $("#ac3").append(channelChild);
				 }
	}
/***
  * 插件排序,规则：从0开始，默认1是正序，0是倒序
  * 默认设置为1，因为默认会触发一次排序事件
  */
 $(function(){
  /***
	**弹出文档框选项
	***/
	
 	$('.question').click(function(){
      pop_dialog($('#question_wrap'), '818px');
    });
     try{
	  // 文档就绪
  		$("#channel_table_sort1").tablesorter({
		/*cssDesc: "sort-header-desc",
    	cssAsc:"headerSortAsc",
    	cssDesc:"headerSortDesc",*/
    	cancelSelection: false,
    	headers: {
    		0:{sorter:false},
    		7:{sorter:false},
			6:{sorter:"currency"}
		},
    	sortList:[[6,0]]
	});
	}catch(err){}
	/***
	 * 获取市场活跃用户
	 */
	var start_date=$("#start_date").val();
	var end_date=$("#end_date").val();
	var version=$("#mySelectVersion").val();
	jQuery.ajax({
		   type: "get",
		   url: "channel/summaryMarketVisit?fromdate="+start_date+"&todate="+end_date+"&version="+version,
		   dataType: "json",
		   success: function(msg){
		   	summaryMarketVisitAppendJson(msg,'ac1',"exp_list=2","exp_list1","RLAUNCH","LAUNCHDET","channel_table_sort2","mk_silver_","启动");
		   summaryMarketVisitTotal();
		   try{
		  $("#channel_table_sort2").tablesorter({
		   /* cssDesc: "sort-header-desc",
	    	cssAsc:"headerSortAsc",
	    	cssDesc:"headerSortDesc",*/
	    	cancelSelection: false,
	    	headers: {
	    	0:{sorter:false},
	    	6:{sorter:false},
			5:{sorter:"currency"}
		    },
	    	   sortList:[[5,1]]
	        });
	        }catch(err){}
	        chSetTime2();
		   	}
		});
		/***
		 * 获取预装新增用户
		 */
		jQuery.ajax({
		   type: "get",
		   url: "channel/summaryInstalledNew?fromdate="+start_date+"&todate="+end_date+"&version="+version,
		   dataType: "json",
		   success: function(msg){
		   	summaryMarketVisitAppendJson(msg,'ac2',"exp_list=3","exp_list2","ONSETUP","SETUPDET","channel_table_sort3","mk_install_","新");
		   	summaryInstalledNewTotal();
		   	try{
		   	$("#channel_table_sort3").tablesorter({
		   /* cssDesc: "sort-header-desc",
	    	cssAsc:"headerSortAsc",
	    	cssDesc:"headerSortDesc",*/
	    	cancelSelection: true,
	    	headers: {
	    	0:{sorter:false},
	    	6:{sorter:false},
			5:{sorter:"currency"}
		    },
	    	sortList:[[5,1]]
	        });
	         }catch(err){}
	        chSetTime3();
		   }
		});
		/***
		 * 获取访问用户
		 */
		jQuery.ajax({
		   type: "get",
		   url: "channel/summaryInstalledVisit?fromdate="+start_date+"&todate="+end_date+"&version="+version,
		   dataType: "json",
		   success: function(msg){
		   summaryMarketVisitAppendJson(msg,'ac3',"exp_list=4","exp_list2","ONLAUNCH","ONLADET","channel_table_sort4","mk_installNew_","启动");
		   summaryInstalledVisitTotal();
		   try{
		   $("#channel_table_sort4").tablesorter({
		   /* cssDesc: "sort-header-desc",
	    	cssAsc:"headerSortAsc",
	    	cssDesc:"headerSortDesc",*/
	    	cancelSelection: false,
	    	headers: {
			0:{sorter:false}, 
			6:{sorter:false},
			5:{sorter:"currency"}
		    },
	    	sortList:[[5,1]]
	       });
	       }catch(err){}
	       chSetTime4();
		   }
		});
	});
/***
 * 后拼接的排序地址
 */
	function appendSort(){
	$(".channel_detail_tr").addClass("hidden").css("display", "none");
	$(".load_channel_detail_btn").html('展开');
	chSetTime();
	}
/***
 * 调整样式蓝白相间chsetTime,用定时器的方式，因为无法获得tablesorter的动作
 */
function chSetTime1(){
	var rowID = 0;
	setTimeout(function(){
		/***
	 * 市场新增用户的样式相间
	 */
	$("#channel_table_sort1>tbody>tr").each(function(i){
		/***
		 * 不是隐藏菜单时，设置样式相间
		 */
		if(!$(this).hasClass("channel_detail_tr")){
			$(this).removeClass();
			$(this).addClass(rowID%2==0?"new_silver_odd":"new_silver_even");
			rowID++;
		}
	});
		
	},1000);
}
function chSetTime2(){
	var rowID = 0;
	setTimeout(function(){
		/***
	 * 市场新增用户的样式相间
	 */
	$("#channel_table_sort2>tbody>tr").each(function(i){
		/***
		 * 不是隐藏菜单时，设置样式相间
		 */
		if(!$(this).hasClass("channel_detail_tr")){
			$(this).removeClass();
			$(this).addClass(rowID%2==0?"new_silver_odd":"new_silver_even");
			rowID++;
		}
	});
		
	},1000);
}
function chSetTime3(){
	var rowID = 0;
	setTimeout(function(){
		/***
	 * 市场新增用户的样式相间
	 */
	$("#channel_table_sort3>tbody>tr").each(function(i){
		/***
		 * 不是隐藏菜单时，设置样式相间
		 */
		if(!$(this).hasClass("channel_detail_tr")){
			$(this).removeClass();
			$(this).addClass(rowID%2==0?"new_silver_odd":"new_silver_even");
			rowID++;
		}
	});
		
	},1000);
}
function chSetTime4(){
	var rowID = 0;
	setTimeout(function(){
		/***
	 * 市场新增用户的样式相间
	 */
	$("#channel_table_sort4>tbody>tr").each(function(i){
		/***
		 * 不是隐藏菜单时，设置样式相间
		 */
		if(!$(this).hasClass("channel_detail_tr")){
			$(this).removeClass();
			$(this).addClass(rowID%2==0?"new_silver_odd":"new_silver_even");
			rowID++;
		}
	});
		
	},1000);
}
function chSetTime(){
chSetTime1();
chSetTime2();
chSetTime3();
chSetTime4();
}
/***
 * 单击展开触发事件
 */
jQuery(function() {
	
/****
 * 单击排序时，需要收起二级子项
 */
$(".header").click(function(){
/**
 * 循环将2级子菜单隐藏
 */
$(".channel_detail_tr").each(function(){
	$(this).addClass("hidden").css("display", "none");
});
/**
 * 重新排序之后将明细显示为展开
 */
$(".load_channel_detail_btn").html('展开');
/**
 * 排序插件重新排序之后，重新设置样式，保持蓝白相间
 */
chSetTime1();
/*setTimeout(function(){
	var rowID = 0;
	*//***
	 * 市场新增用户的样式相间
	 *//*
	$("#channel_table_sort1>tbody>tr").each(function(i){
		*//***
		 * 不是隐藏菜单时，设置样式相间
		 *//*
		if(!$(this).hasClass("channel_detail_tr")){
			$(this).removeClass();
			$(this).addClass(rowID%2==0?"new_silver_odd":"new_silver_even");
			rowID++;
		}
	});
	$("#channel_table_sort4>tbody>tr").each(function(i){
		*//***
		 * 不是隐藏菜单时，设置样式相间
		 *//*
		if(!$(this).hasClass("channel_detail_tr")){
			$(this).removeClass();
			$(this).addClass(rowID%2==0?"new_silver_odd":"new_silver_even");
			rowID++;
		}
	});
	$("#channel_table_sort2>tbody>tr").each(function(i){
		*//***
		 * 不是隐藏菜单时，设置样式相间
		 *//*
		if(!$(this).hasClass("channel_detail_tr")){
			$(this).removeClass();
			$(this).addClass(rowID%2==0?"new_silver_odd":"new_silver_even");
			rowID++;
		}
	});
	$("#channel_table_sort3>tbody>tr").each(function(i){
		*//***
		 * 不是隐藏菜单时，设置样式相间
		 *//*
		if(!$(this).hasClass("channel_detail_tr")){
			$(this).removeClass();
			$(this).addClass(rowID%2==0?"new_silver_odd":"new_silver_even");
			rowID++;
		}
	});
}, 100);*/
});
/***
 * 默认触发一次单击事件，这样能继续触发排序之后设置样式的动作
 */
$(".default_order").each(function(){
		$(this).trigger("click");
	});

jQuery('a.load_channel_detail_btn').live("click",function() {
	/**
	 * 通过当前的a标签拿到tr
	 */
	var clk_tr = $(this).parent().parent();
	/***
	 * 通过tr的id拿到对应的隐藏tr的id
	 */
	var detail_tr = $("#"+clk_tr.attr("id")+"_det");
	/**
	 * 将隐藏tr添加到tr下
	 */
	clk_tr.after(detail_tr);
	var target = jQuery(this).closest('tr').next('tr.channel_detail_tr').children('td');
	if (jQuery.trim(target.html()) == '') {
		var loadingImg = jQuery(this).next('img').show();
		var link = jQuery(this);
		target.load(function() {
			loadingImg.hide();
			target.closest('tr').show();
			link.html('收起');
		});
	} else {
		if (jQuery(this).html() == '展开') {
			jQuery(this).html('收起');
		} else {
			jQuery(this).html('展开');
		}
		jQuery(this).closest('tr').next('tr.channel_detail_tr').toggle();
		}
		return false;
});

jQuery('.select_all_btn')
	.click(
			function() {
				if (jQuery(this).attr(
						'checked')) {
					jQuery(this)
							.closest(
									'div.question_content_center_new_b')
							.find(
									":checkbox[name='channels[]']")
							.attr(
									'checked',
									'checked');
				} else {
					jQuery(this)
							.closest(
									'div.question_content_center_new_b')
							.find(
									":checkbox[name='channels[]']")
							.removeAttr(
									'checked');
					}
				});

jQuery('a.choose_channels').click(
	function() {
		var channels_select = jQuery(
				this).siblings(
				'div.channel_select');
		jQuery.blockUI({
			css : {
				color : '#cccccc',
				border : 0,
				width : '500px',
				top : '20%',
				textAlign : 'left',
				background : 'none'
				},
				message : channels_select
			});
		});

jQuery('a.channel_select_commit').click(
	function() {
		var upbtn = jQuery(this
				.getAttribute("href"));
		jQuery.unblockUI({
			onUnblock : function() {
				upbtn.click();
			}
		});
		return true;
	});

	//jQuery('.filter_params .upbtn').click();
});