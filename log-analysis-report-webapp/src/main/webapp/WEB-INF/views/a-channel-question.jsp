<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="question_wrap" style="display:none;">
	<div class="question_content_top">
		<h3 class="title">
			<span class="fl"><b>ååæ¸ éåæ</b>è¯æ±è¡¨</span> <span class="fr tg_rss"><img
				alt="" src="/images/new_ui/report/report_subscribe_close_normal.png">
			</span>
			<div class="clear"></div>
		</h3>
	</div>
	<div class="question_content_center">
		<table cellpadding="0" cellspacing="0" border="0" width="775">
			<tbody>
				<tr>
					<td class="title">éæ©æ¶æ®µ</td>
					<td>è¿ééæ©çæ¶æ®µä¼å½±åç´¯è®¡æ°ç¨æ·ä»¥åæ¸ éæ°å¢ç¨æ·å¯¹æ¯</td>
				</tr>
				<tr class="question_even">
					<td class="title">æ¸ éç´¯è®¡æ°ç¨æ·ï¼%ï¼</td>
					<td>æéæ¶é´æ®µåï¼è¯¥æ¸ éçæ°ç¨æ·ï¼æéæ¶æ®µåï¼æ¸ éç´¯è®¡æ°ç¨æ·/å¨ä½æ°ç¨æ·ï¼</td>
				</tr>
				<tr>
					<td class="title">æ¸ éç´¯è®¡ç¨æ·ï¼%ï¼</td>
					<td>æªæ­¢å½åï¼è¯¥æ¸ éçå¨ä½ç¨æ·ï¼æ¸ éç´¯è®¡ç¨æ·/ç´¯è®¡ç¨æ·ï¼</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="question_content_bottom"></div>
</div>
<script type="text/javascript">
$(function(){
	$('.question').click(function() {
		var msg = $('#question_wrap');
		var height = $(window).height();
		var width = $(document).width();
		$.blockUI({
			css: {color: '#cccccc',border:'0',width:'818px','left' : width/2 - (msg.width() / 2),'top' : height/2 - (msg.height() / 2),background:'none',padding:'0px'},
			message: $('#question_wrap')
		});
	});
	$(".tg_rss").click(function() {setTimeout($.unblockUI, 50);});
	
	change_input_hover_bg("#question_wrap .tg_rss img", "/images/new_ui/report/report_subscribe_close_normal.png", "/images/new_ui/report/report_subscribe_close_hover.png");
});
</script>