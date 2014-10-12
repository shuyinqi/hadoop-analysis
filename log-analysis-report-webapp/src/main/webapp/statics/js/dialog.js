/* 
*  title:   about pop up dialog 
*  rely:    jquery.blockUI 
*/

//pop a dialog and block the page
function pop_dialog(msg, width, height, left, top)
{
    var height_page = jQuery(window).height();
    var width_page = jQuery(document).width();
    if(!left)
    {
        left= width_page/2 - msg.width()/2;
    }
    if(!top)
    {
        top=height_page/2 - (msg.height() / 2);
    }
    $.blockUI({
      css: {'color': '#cccccc','border':'0','width':width, 'height':height, 'left':left, 'top':top, background:'none',padding:'0px', 'textAlign':'left'},
      message: msg
    });
}

jQuery(function(){
    jQuery(".tg_rss").click(function() {
        setTimeout($.unblockUI, 50);
    });
});