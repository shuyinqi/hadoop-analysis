$(document).ready(function() {
	//$(".salon_register_center_class .salon_register_center_intro_wrap").hide();
	/*
	$("#salon_register_center_id_03 .salon_register_center_intro_wrap").show();
	$("#salon_register_center_id_03 h3.sr_title span.fr").show();
	$("#salon_register_center_id_03 h3.sr_title span.click_tosee").hide();
	*/

	$(".salon_register_center_packup span").click(function(){
		$(this).parent().parent().hide();
		var d = $(this).parent().parent();
		var h = $(d).siblings("h3.open");
		$(h).removeClass("open");
		
	});
//只显示一个
//	$(".salon_register_center_class h3").click(function(){
//		 if ( $(this).hasClass("open")){
//			$(".salon_register_center_intro_wrap").hide();
//			var d = $(this).parent()[0].id;
//			$("#"+d+" .salon_register_center_intro_wrap").hide();
//			$("h3").removeClass("open");;
//			//$(this).toggleClass("open");
//		 }else{
//			var d = $(this).parent()[0].id;
//			$(".salon_register_center_intro_wrap").hide();
//			$("#"+d+" .salon_register_center_intro_wrap").show();
//			$("h3").removeClass("open");;
//			$(this).toggleClass("open");
//		 }
//
//	});
//	$(".salon_register_center_class h3").click(function(){
//		 if ( $(this).hasClass("open")){
//			//$(".salon_register_center_intro_wrap").hide();
//			var d = $(this).parent()[0].id;
//			$("#"+d+" .salon_register_center_intro_wrap").hide();
//			//$("h3").removeClass("open");;
//             $(this).toggleClass("open");
//			//$(this).toggleClass("open");
//		 }else{
//			var d = $(this).parent()[0].id;
//			//$(".salon_register_center_intro_wrap").hide();
//			$("#"+d+" .salon_register_center_intro_wrap").show();
//			//$("h3").removeClass("open");;
//			$(this).toggleClass("open");
//		 }
//
//	});
});

