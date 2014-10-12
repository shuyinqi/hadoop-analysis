$(function() {
/**
 * 组件
 */
$("#module_tab").click(function(){
	header_redirect('module');
})
/**
 * 统计分析
 */
$("#reports_tab").click(function(){
   header_redirect('base');
})
   });
/***
 * 负责调整并给时间段赋值
 * @param {} appName 控制器名称
 */
function header_redirect(appName){
	var url = document.location.href;
	var path = url.split('?')[0];
	var realPathIndex = path.lastIndexOf('/');
    var realPath = path.substring(0,realPathIndex);
    var weekdate = $('#week_time').val();
    url = realPath+'/'+appName+'?'+weekdate;
    window.location.href=url;
}