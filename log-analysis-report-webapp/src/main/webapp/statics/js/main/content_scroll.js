//		var theInt = null;
//        var $crosslink, $navthumb;
//        var curclicked = 0;
//
//        theInterval = function(cur) {
//            clearInterval(theInt);
//
//            if (typeof cur != 'undefined')
//                curclicked = cur;
//
            //$crosslink.removeClass("active-thumb");
            //$navthumb.eq(curclicked).parent().addClass("active-thumb");
//            $(".stripNav ul li a").eq(curclicked).trigger('click');
//
//            theInt = setInterval(function() {
                //$crosslink.removeClass("active-thumb");
                //$navthumb.eq(curclicked).parent().addClass("active-thumb");
//                $(".stripNav ul li a").eq(curclicked).trigger('click');
//                curclicked++;
//                if (5 == curclicked)
//                    curclicked = 0;
//
//            }, 999999);
//        };

		
		var hash = window.location.hash; 
		
		/* 自动使用panel高度 */
		function adjustHeight(item) {
			var new_height = $(".panelContainer .panel").eq(item).height();  //get new height by tag
			//alert(item + " " + new_height);
			$(".stripViewer .panelContainer .panel").height(new_height + "px");  //set new height
		}
		
        $(function() {
			$("#main-photo-slider").codaSlider();
			
            $("a.cross-link").click(function() {
                $("a.cross-link").removeClass("active-thumb");
                $(this).addClass("active-thumb");
				$(".stripViewer .panelContainer .panel").height("100%");  //init height
	
				if($(this).parents("li.develop_guide").index()==0){
					$('#salon_history_nav,div.corperation_company_list').hide();
				}else{
					$('#salon_history_nav,div.corperation_company_list').show();
					window.frames['salon_avtivities'].document.location = "http://tip.umeng.com/uploads/salon/salon_activities.html";
					
				}
				adjustHeight(parseInt($(this).attr("href").replace("#", "")) - 1);
            });
			
            $(".drop_item").click(function() {
            	current_href = window.location.href;
            	target_href = $(this).find('a').attr('href');
            	current_arr = current_href.split('#')[0].split('/');
            	target_item = target_href.split('#')[0].split('/')[1];
            	if(current_arr[current_arr.length-1] == target_item) {
	            	window.location.href = target_href;
	            	window.location.reload();
            	}	
//            	return false;
	            	            	
            })
            
    		//var hash = window.location.hash;
//    		var left =  (1 - parseInt(hash.split('#')[1])) * 950;
    		
//    		$('div.panelContainer').css('left',left + 'px');

    		
    		if(hash){
            	$('li.develop_guide a').removeClass('active-thumb');
            	$('li.develop_guide a[href=' + hash + ']').addClass('active-thumb');
            }else{
	            $('li.develop_guide a[href=#1]').addClass('active-thumb');
            }
            
//
//            $navthumb = $(".nav-thumb");
//            $crosslink = $(".cross-link");
//
//            $navthumb
//                    .click(function() {
//                var $this = $(this);
				//$test = $this.parent().attr('href').slice(1);
				//echo $test;
//                theInterval($this.parent().attr('href').slice(1) - 1);
//                return false;
//            });
//            theInterval();
//			
			
			/*��ȡ��ߵ�ֵ�������ֵ���� salong_wrap*/
//			function sortNumber(a, b){
//				return b - a
//			}
//			var a=[];
//			$(".panel").each(function(i){
//				var b=$(".panel")[i];
//				a[i]=b.scrollHeight;
//			});
//			a.sort(sortNumber)
//			var r_c = a[0];
			//alert(r_c);
			
//			var l_c = $(".developer_leftmenu").height();
//			if(r_c>l_c){
//				$(".salong_wrap").height(r_c);
//			}else{
//				$(".salong_wrap").height(l_c);
//			}
			
        });
		
//		$(document).ready(function(){
			//$(".stripNav ul li a").eq(2).trigger('click');
//			/*var $this = $(this);
//                theInterval($this.parent().attr('href').slice(1) - 1);
//                return false;
//            $("a.cross-link").parent().attr('href').slice(1) = 2;
//		theInterval($("a.cross-link").parent().attr('href').slice(1));*/
//        });
//		

		/* 使用window.onload为了确保DOM和图片都加载完毕时才调整高度 */
		window.onload = function() {
			if(hash){
				adjustHeight(parseInt(hash.replace("#", "")) - 1);
			}else{
				adjustHeight(0);
			}
		}