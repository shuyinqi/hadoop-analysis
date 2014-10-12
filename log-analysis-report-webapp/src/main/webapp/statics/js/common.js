/*
 *
 * Purpose: 
 * Rely on: jquery.tools.js
 *
 **/

// cookies
function cookie_get(k) {
    var h = document.cookie.split("; ");
    g = h.length;
    f = [];
    for (var j = 0; j < g; j++) {
        f = h[j].split("=");
        if (k === f[0]) {
            return unescape(f[1]);
        }
    }
    return null;
}

function cookie_set(f, g) {
    var h = new Date();
    h.setDate(h.getDate() + 1);
    document.cookie = f + "=" + escape(g) + "; expires=" + h.toGMTString();
}

// show & hide tips
jQuery(function(){
    jQuery('.event_show_tips').each(function(){
        var tip_id = jQuery(this).attr('id');
     //   if (!(cookie_get('show_tips#'+tip_id) == 'true')) {
            jQuery(this).show();
      //  }
    });
    jQuery(".event_tip_close").click(function(){
        var tip_box = jQuery(this).closest(".event_show_tips").hide();
        var tip_id = tip_box.attr('id');
       // cookie_set('show_tips#'+tip_id, true);
    });
});

// region url ---------------------
function trimUrl(url)
{
if(url != null && url.length > 1)
{
  var temp = url[url.length - 1] == '#' ? url.substring(0,url.length - 1) : url;
  var pos = temp.indexOf("?",0);
  if (pos!=-1)
  {
    temp = url.substring(0,pos)
  }
  return temp[temp.length - 1] == '/' ? temp.substring(0,temp.length - 1) : temp;
}
else
  return url;
}
// region url end -------------------


//
function getQueryString(name)
{
    if(location.href.indexOf("?")==-1 || location.href.indexOf(name+'=')==-1)
    {
        return '';
    }
    var queryString = location.href.substring(location.href.indexOf("?")+1);
    var parameters = queryString.split("&");
    var pos, paraName, paraValue;
    for(var i=0; i<parameters.length; i++)
    {
        pos = parameters[i].indexOf('=');
        if(pos == -1) {
            continue;
        }
        paraName = parameters[i].substring(0, pos);
        paraValue = parameters[i].substring(pos + 1);
        if(paraName == name)
        {
            return unescape(paraValue.replace(/\+/g, " "));
        }
    }
    return '';
}

 
 // tab click actions
var _gel = function(id){return document.getElementById(id)}
function changeTab(n,len,tab,con,cln,sel, callback, callbackParams) {
    for (var i = 0;i < len; i++) {
        try {
            if (n == i) {
                _gel(tab + i).className = cln + ' ' + sel;
                _gel(con + i).style.display = 'block';
            } else {
                _gel(tab + i).className = cln;
                _gel(con + i).style.display = 'none';
            }
        } catch(e) {

        }
    }
    if(callback != undefined){
        callback.apply(this, callbackParams);
    }
}

// show number bars
function createNumBar(tabcon,num,numbar,total) {
    var len = _gel(tabcon).getElementsByTagName('tr').length - 1;
    if(len <= 0 ) {return;}
    var sumValue = 0;
    var valueArr = [];
    for(var i=0; i < len ; i ++) {
        sumValue += _gel(num + i).innerHTML-0;
        valueArr.push(_gel(num + i).innerHTML-0);
    }
    if( total != undefined ){
        sumValue = total;
    }
    var maxValue = valueArr.sort( function(a,b){return a-b;})[valueArr.length-1];
    var boxW = _gel(numbar + '0').offsetWidth - 60;

    for(var i=0; i < len ; i ++) {
        var divBox = document.createElement('div');
        divBox.style.cssText = 'position:relative;';
        var div = document.createElement('div');
        var p = (_gel(num + i).innerHTML-0) * boxW / maxValue;
        div.style.backgroundColor = '#7477D5';  // #CACFE0
        div.style.height = 15 + 'px';
        var barWidth = isNaN(p) ? 0 : p;
        div.style.width = barWidth + 'px';
        var divTxt = document.createElement('div');
        divTxt.style.cssText = 'position:absolute;right:0;top:0px;'
        var percentage = ((_gel(num + i).innerHTML-0) / sumValue * 100).toFixed(1);
        percentage = isNaN(percentage)? 0 : percentage;
        divTxt.innerHTML = percentage + '%';
        divBox.appendChild(div);
        divBox.appendChild(divTxt);
        _gel(numbar + i).appendChild(divBox);
    }
}

// selected app change action
function showSelect(app_title) {
    var base_url = window.location.href;
    var url = "apps/" + app_title + "/";
    var redirect_url = base_url.replace(/apps\/([^\/]+\/)/, url);
    redirect_url = redirect_url.replace(/events\/.*/, 'events');
    window.location.href = redirect_url;   
}

// adjust page header background position
function adjust() {
    var h = jQuery(".conleft")[0].offsetHeight;
    var main = document.getElementById("main");
    main.style.minHeight = (h - 5) + "px";
}

// get start date param
function getStartDate(){
  return jQuery("input[name='start_date']:first").val();
}

// get end date parma
function getEndDate(){
  return jQuery("input[name='end_date']:first").val();
}

    
function getURLParameter(name) {
    return unescape(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]);
}