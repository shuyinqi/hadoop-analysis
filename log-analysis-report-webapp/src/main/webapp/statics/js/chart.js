// JavaScript Document
var _gel = function(id){return document.getElementById(id)}
var flashPath = '/';
var flashName = document.all ? ('adChart.swf?'+Math.random()) : 'adChart.swf';
var Chart = function(path,w,h,flcon){
  this.path = path;
  this.w = w;
  this.h = h;
  this.flcon = flcon;
    this.flashvars = '';
}
Chart.prototype = {
  init: function(){
    this.createSwf();
  },
  createSwf: function(){
    swfobject.embedSWF(this.path, this.flcon, this.w, this.h, "10.0.0", null,{v: this.flashvars},{wmode: 'transparent'});
  }
}

var changeTab = function (n,len,tab,con,cln,sel, callback, callbackParams) {
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

var createNumBar = function(tabcon,num,numbar,total) {
    var len = _gel(tabcon).getElementsByTagName('tr').length - 1;
    if(len <= 0 ) { return; }
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
