/***
 * 切回复和收起，显示和隐藏回复信息
 */
function reply(index,item){
	var target = item.innerText;
	if(target=="回复"){
	$(".module_reply_alway_answer_"+index).removeClass("hidden");
	item.innerText="收起";
	}else{
		$(".module_reply_alway_answer_"+index).addClass("hidden");
	item.innerText="回复";
	}
}
/***
 * 删除反馈
 * @param {} fid
 */
function deleteFeedBook(fid){
	feedBack_rediect('feedBackDelete','&fid='+fid);
}
/***
 * 提交回复
 * @param {} userid
 * @param {} ct  预留
 * @param {} qid
 * @param {} wid
 * @param {} fid
 * @param {} appid
 * @param {} index
 */
function submitReply(userid,ct,qid,wid,fid,appid,index){
  var answer = $("#module_answer_"+index).val();
  var parm ="&userid="+userid+"&qid="+qid+"&wid="+wid+"&fid="+fid+"&answer="+encodeURI(encodeURI(answer))+"&answerusername="+$("#module_username").val();
  feedBack_rediect('feedBackReply',parm);
  console.log(answer);
}
/**
 * 更改收藏
 * @param {} fid
 * @param {} index
 */
function isCollect(fid,index){
	var isc=0;
	if($("#module_collect_"+index).attr("title")=='收藏'){
		$("#module_collect_"+index).removeClass("feedback_bookmark_on");
		$("#module_collect_"+index).addClass("feedback_bookmark_off");
		$("#module_collect_"+index).removeAttr("title");
		$("#module_collect_"+index).attr("title","取消收藏");
		isc=0;
	}else{
		$("#module_collect_"+index).removeClass("feedback_bookmark_off");
		$("#module_collect_"+index).addClass("feedback_bookmark_on");
		$("#module_collect_"+index).removeAttr("title");
		$("#module_collect_"+index).attr("title","收藏");
		isc=1;
	}
		$.ajax({
		   type: "POST",
		   url: "fbChangeCollect",
		   data: "fid="+fid+"&isc="+isc,
		   success: function(msg){
				
		   	}
		});
	
}
/**
 * 跳转
 * @param {} appName   跳转路径
 * @param {} param     参数名称
 */
function feedBack_rediect(appName,param){
	var url = document.location.href;
	var path = url.split('?')[0];
	var realPathIndex = path.lastIndexOf('/');
    var realPath = path.substring(0,realPathIndex);
    var weekdate = $('#week_time').val();
    url = realPath+'/'+appName+'?'+weekdate+param;
    window.location.href=url;
}

