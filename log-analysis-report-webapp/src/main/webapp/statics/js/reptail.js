/***
 * 修改爬出渠道总数及新增
 * @param name     渠道名称
 * @param total    爬出总数
 * @param addCount 算出的新增数
 * @param date     日期
 * @param id       
 */
function modifyReptail(name,total,addCount,date,id){
	$("#ch_re").removeClass("hidden");
	$("#uid").val(id);
	$("#uname").val(name);
	$("#uname").attr("readonly","readonly"); 
    $("#total").val(total);
    $("#newCount").val(addCount);
    $("#re_stat_date").val(date);
    $("#re_stat_date").attr("readonly","readonly");
}
/***
 * 取消按钮
 */
function cancel(){
	$("#ch_re").addClass("hidden");
	$("#uid").val("");
	$("#uname").removeAttr("readonly"); 
	$("#re_stat_date").removeAttr("readonly");
}
/***
 * 添加按钮
 */
function addReptail(){
	$("#ch_re").removeClass("hidden");
	$("#uid").val("");
	$("#uname").val("");
	$("#uname").removeAttr("readonly");
    $("#total").val("");
    $("#newCount").val("");
    $("#re_stat_date").removeAttr("readonly");
    $("#re_stat_date").val("");
}
/****
 *  查询按钮
 */
function date_submit(){
   cancel();
  var date = $("#re_sel_date").val();
  if(date==""){
  	alert("请选择日期查询");
  	return;
  }
	var url = document.location.href;
	var path = url.split('channel')[0];
	window.location.href=path+'channel/reptailDate?date='+date;
}
/****
 * 修改和添加按钮，ajax触发之后自动刷新页面.
 */
function re_submit(){
	var uname=$("#uname").val();
	var uid=$("#uid").val();
	var total=$("#total").val();
	var addCount=$("#newCount").val();
	var date=$("#re_stat_date").val();
	if(uname==""||total==""||addCount==""||date==""){
		alert("渠道名称,今日用户，总用户，时间均不允许为空");
		return;
	}
	$.ajax({
		   type: "POST",
		   url: "reptailAjax",
		   data: "name="+uname+"&id="+uid+"&total="+total+"&addCount="+addCount+"&date="+date,
		   success: function(msg){
		     cancel();
             location.reload();
		    }
		});
}