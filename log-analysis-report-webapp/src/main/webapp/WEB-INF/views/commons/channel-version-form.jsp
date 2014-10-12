<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="time_select_wrap">
	<div class="statsTableHeader">
		<strong>选择版本:</strong>
		<div class="selbox">
			<select id="mySelectVersion" name="version" onchange="click_channelType()">
			
         				<c:forEach items="${ selectDurationByVersion }" var="ss">
         					<c:choose>
         				 	<c:when test="${ ss==version }">
         						<option value="${ ss }" selected="selected">${ ss }</option>
         				 	</c:when>
         				 	<c:otherwise>
         						<option value="${ ss }">${ ss }</option>
         				 	</c:otherwise>
         				 </c:choose>
         				</c:forEach>
        	</select>
        	</div>&nbsp;&nbsp;&nbsp;
		<strong style="margin:0 5px 0 15px;">渠道类型:</strong>
		<div class="selbox">
			<select id="mySelectChannelType" name="channel_type"  onchange="click_channelType()">
         				 	<c:choose>
         				 	<c:when test="${'all'==channelType }">
         						<option value="all" selected="selected">all</option>
         						<option value="market">market</option>
         				 		<option value="pre_installed">pre_installed</option>
         				 	</c:when>
         				 	<c:when test="${'pre_installed'==channelType }">
         						<option value="all">all</option>
         						<option value="market">market</option>
         				 		<option value="pre_installed" selected="selected">pre_installed</option>
         				 	</c:when>
         				 	<c:when test="${'market'==channelType }">
         						<option value="all" >all</option>
         						<option value="market" selected="selected">market</option>
         				 		<option value="pre_installed">pre_installed</option>
         				 	</c:when>
         				 </c:choose>
        	</select>
        	</div>&nbsp;&nbsp;&nbsp;
		    <strong style="margin:0 5px 0 15px;">渠道名称:</strong>
		<div class="selbox">
			<select id="mySelectChannelName" name="channel_name">
     				<c:forEach items="${ selectDurationByChannel }" var="ss">
     				 	<c:choose>
     				 	<c:when test="${ ss==channelName }">
     						<option value="${ ss }" selected="selected">${ ss }</option>
     				 	</c:when>
     				 	<c:otherwise>
     						<option value="${ ss }">${ ss }</option>
     				 	</c:otherwise>
     				 </c:choose>
     				</c:forEach>
        	</select>
        	</div>
		</div>
		<div class="clear"></div>
	</div>

<script type="text/javascript">
function click_channelType(){
	var version=$("#mySelectVersion").val();
	var channelType=$("#mySelectChannelType").val();
	$.ajax({
		   type: "GET",
		   dataType: "json",
		   url: "durationCondition",
		   data: "version="+version+"&channelType="+channelType,
		   success: function(msg){
		 	 var options="";
		 	 if(msg.length==0){
		 		options+="<option value='all' selected>not exist</>";
		 	 }
		 	 else if(msg.length>1){
		 		options+="<option value='all' selected>all</>";
		 	 }
		 	 for(var i=0;i<msg.length;i++){
		 		options+="<option value='"+msg[i]+"'>"+msg[i]+"</>";
		 	 }  
			 $("#mySelectChannelName").find("option").remove();
			$("#mySelectChannelName").append(options); 
		   }
		});	
}

</script>