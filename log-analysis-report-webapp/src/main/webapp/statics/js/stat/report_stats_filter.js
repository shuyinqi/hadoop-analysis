jQuery(function(){
    jQuery('.filter_params .upbtn').live('click', function(){
        var filter_params_div = jQuery(this).closest('.filter_params');
        var renderTo = filter_params_div.attr('id').replace('_filter_params','');
        var title = ''
        jQuery('option[selected]', filter_params_div).each(function(){title = jQuery(this).html();});
        loadFilteredContent(renderTo, title + '变化趋势', getFilterParams(filter_params_div));
    });
});
function getFilterParams(params_div_container){
    var params = '';
    var url_params = location.href.split('?')[1];
    if( url_params !== undefined){
        params += url_params.split('#')[0] + "&";
    }
    jQuery("input[checked], input.date, select", params_div_container).each(function(){
        if( this.name != undefined){
            name = this.name;
            value = jQuery.trim(this.value);
            if( name !== '' && value !== ''){
                params += name + "=" + value + "&";
            }
        }
    });
    return params;
}
function loadFilteredContent(renderTo, title, params) {
    var chart_canvas = jQuery('#'+renderTo);
  /*  chart_canvas.block({
        message: jQuery("<img src='../../statics/images/ajax-loader.gif'/>"),
        css:{width:'32px',border:'none',background: 'none'},
        overlayCSS:{backgroundColor: '#FFF',opacity: 0.8}
    });*/
    var base_url = window.location.href + "/ajax";
    alert(params+"o=o");alert("aaa");
    
    
	jQuery.ajax({
		contentType : "text/html;charset=UTF-8",
		type : 'GET',
		data : params,
		dataType : 'JSON',
		url : base_url,
		async : true,
		success : function(xmhr, textStatus) {
			alert("asdfasdfasdf");
		},
		error : function(xmhr, textStatus, errorThrown) {
			alert(xmhr.status);
		},
		complete : function(xmhr, textStatus) {
			alert(xmhr.status);
		}
	});
  
    
    /*jQuery.get(base_url, params, function(response,status,xhr){
    	alert("11111");
    	 if( response.result == 'success'){
            var chartCanvas = jQuery('<div></div>').attr('id', renderTo+'_canvas');
            jQuery('#'+renderTo).empty().append(chartCanvas);
            renderStatsChart(renderTo+'_canvas', title, response.dates, response.stats);
        }
        chart_canvas.unblock();
    });*/
}
function renderStatsChart(element, title, dates, data) {
	alert(dates);alert(data);
	return ;
    dates = dates === undefined ? [] : dates;
    var options = {
        title:{text: title},
        chart: {defaultSeriesType: "spline",renderTo : element,animation : false},
        xAxis: {
            labels : {
                align : "right",
                rotation : -45,
                formatter : function(){return this.value.substring(5);},
                step : parseInt(dates.length / 10)
            },
            categories : dates
        },
        yAxis: {min : 0,title :""},
        tooltip:  {enabled : true},
        credits: {enabled : false},
        legend: {margin: 25,enabled: true},
        plotOptions: {areaspline : {fillOpacity : 0.5},"series":{animation: false}}
    };
    options.tooltip.formatter = function() {return this.x +': '+ this.y;}
    var chart = new Highcharts.Chart(options);
/*    var orderedSeries = new Array();
    for(var item in data){
        if( typeof(data[item])=="object"){
            var total = 0;
            jQuery.each(data[item], function(i,e){total += e;});
            orderedSeries.push({name:item,total: total,data: data[item]});
        }
    }
    jQuery.each(
		orderedSeries.sort(function(a,b){return b.total - a.total}),
		function(i,e){chart.addSeries({name:e.name,data:e.data});}
    );*/
}
