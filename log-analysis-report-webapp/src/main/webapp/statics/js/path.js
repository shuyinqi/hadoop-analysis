/*$(function() {
      $(window).load(function(){
      	jQuery.ajax({
		   type: "get",
		   url: "path/pathAjax",
		   async:false,
		   success: function(msg){
		   	render_flow_chart(msg);
		   	}
		});
       });
   });
   
   */
var vsubm = function(upbtn) {
	var par1 = $('#start_date').get(0).value;
	var par2 = $('#end_date').get(0).value;
	var type1 = $('#app_version_selector').get(0).value;
	window.location.href = vtrimUrl(window.location.href) + "?fromdate="
			+ par1 + "&todate=" + par2 + "&version=" + type1;
};
function vtrimUrl(url) {
	if (url != null && url.length > 1) {
		var temp = url[url.length - 1] == '#' ? url.substring(0,
				url.length - 1) : url;
		var pos = temp.indexOf("?", 0);
		if (pos != -1)
			temp = url.substring(0, pos);
		return temp[temp.length - 1] == '/' ? temp.substring(0,
				temp.length - 1) : temp;
	} else
		return url;
}


$.tablesorter.addParser({
				id: "const",
			is: function(s){
			   return false;
			},
			format: function(s){
			   return s.toLowerCase().replace(/分/,0).replace(/秒/,0).replace(/ /,0);
			},
			type: "numeric"
						});
$(document).ready(function() {
    $("#bartable_daily_active_users_devices").tablesorter({
cssDesc: "sort-header-desc",
cssAsc:"headerSortAsc",
cssDesc:"headerSortDesc",
cancelSelection: false,
headers: {
	0:{sorter:false}
	,1:{
		sorter:"currency"
}
,2:{sorter: "currency"}
,3:{sorter: 'const'}
,4:{sorter: "currency"}
		},
    	sortList:[[2,1]]
    });
});