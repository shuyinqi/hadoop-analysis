/**
 * 拿到xmlhttp对象
 */
var xmlhttp;
//创建AJAX引擎     
function createXmlhttp () {  
    var xhr;
	if (window.XMLHttpRequest) {     
        //针对FireFox，Mozillar，Opera，Safari，IE7，IE8     
        xhr = new XMLHttpRequest();
        //针对某些特定版本的mozillar浏览器的BUG进行修正     
        if (xhr.overrideMimeType) {     
            xhr.overrideMimeType("text/xml");     
        }     
    } else if (window.ActiveXObject) {     
         //针对IE6，IE5.5，IE5     
        //两个可以用于创建XMLHTTPRequest对象的控件名称，保存在一个js的数组中     
        //排在前面的版本较新     
        var activexName = ["MSXML2.XMLHTTP","Microsoft.XMLHTTP"];     
        for (var i = 0; i < activexName.length; i++) {     
            try{     
                //取出一个控件名进行创建，如果创建成功就终止循环     
                //如果创建失败，回抛出异常，然后可以继续循环，继续尝试创建     
                xhr = new ActiveXObject(activexName[i]);     
                break;     
            } catch(e){     
            }     
        }     
    }   
    return xhr;   
} 
/***
 * 发送post请求
 * @param {} url  url地址
 * @param {} content  参数
 */
function sendRequest(url ,content){
    xmlhttp = createXmlhttp();  
    xmlhttp.onreadystatechange = processResponse;  
    xmlhttp.open("post",url,true);  
    xmlhttp.setRequestHeader("Content-Type","application/xml");     
    xmlhttp.send(content);     
}  
/***
 * ajax成功之后返回，回调函数
 */
function processResponse(){  
    if (xmlhttp.readyState == 4 ) {  
        if (xmlhttp.status == 200 ){ 
        	//alert("这里是回调函数！");
        }  
    }  
}
/***
 * 无线入口方法
 */
function wirelesslogs(){
   var host = getHost();
   getRequest(host);
}
/****
 * 获取域名,拼接的域名地址，根据实际情况可以相应更改
 * @return {}
 */
function getHost(){
return "http://"+location.host+"/reports";
}
/**
*获得浏览器url路径的参数
*/
function getRequest(host){
    /***
     * url传递的参数名称key名称，可以根据需求更改
     * @type String
     */
    var appid="appid",userid="userid",eventid="eventid",appversion="appversion",lab="lab";
    /***
     * 解析出来的value数值
     * @type String
     */
    var vappid="",vuserid="",veventid="",vappversion="",vlab="";
    var url = location.search; //获取url中"?"符后的字串 
    var weed = url.split("?");
    var map =weed[1].split("&");
     for(var i=0;i<map.length;i++){
    	var kv=map[i].split("=");
    	if(kv[0]==appid){
    	  vappid=kv[1];
    	}else if(kv[0]==userid){
    	  vuserid=kv[1];
    	}else if(kv[0]==eventid){
    	  veventid=kv[1];
    	}else if(kv[0]==appversion){
    	  vappversion=kv[1];
    	}else if(kv[0]==lab){
    	  vlab=kv[1];
    	}
    }
	if(vappid!=""&&vuserid!=""&&veventid!=""&&vappversion!=""){
	   logsAjax(vappid,vuserid,host,logdata(vappversion,veventid,vlab)); 
	}
}
/***
 * 日志的ajax协议
 * @param {} appid  应用标示
 * @param {} userid 用户标示，唯一的，web端用ip，安卓端用imei
 * @param {} host   发送日志的主机地址端口。例如：http://192.168.53.70:8080,暂时是本地
 * @param {} data   附加参数，定义日志格式
 */
function logsAjax(appid,userid,host,data){
  var url = host+"/logs/app/"+appid+"/user/"+userid;
  sendRequest(url,"content="+data);
}
/***
 * 拼接日志格式
 * @param {} vappversion  版本号
 * @param {} veventid     事件id
 */
function logdata(vappversion,veventid,vlab){
  var myDate=new Date();
  var mytime=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate()+" "+myDate.toLocaleTimeString();
  var logdata;
  if(vlab==""){
   logdata='{"head":{"appv":"'+vappversion+'","lon":"","osv":"","req_time":0,' +
  		'"carrier":"","chn":"","access":"","cht":"","lat":"","country":"Unknown","version_code":1},' +
  		'"body":{"event":[{"eid":"'+veventid+'","sid":"","acc":1,"t":"'+mytime+'"}]}}';
  }else{
   logdata='{"head":{"appv":"'+vappversion+'","lon":"","osv":"","req_time":0,' +
  		'"carrier":"","chn":"","access":"","cht":"","lat":"","country":"Unknown","version_code":1},' +
  		'"body":{"event":[{"eid":"'+veventid+'","sid":"","lab":"'+vlab+'","acc":1,"t":"'+mytime+'"}]}}';
  }
  return logdata;
}
