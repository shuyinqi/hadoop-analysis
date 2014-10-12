var trace_items =
[ {id:'100',sel: 'div.demonotice a'},
  {id:'101',sel: 'a.feed_back_all'},
  {id:'102',sel: '#b_pro_status_home span.question'}
],
 server = 'http://op.umeng.us/clicks';

function Capture(url,data){
  this.url = url;
  this.data = data;

  this.send = function(){
    var data_send = encodeURIComponent(JSON.stringify(data));
    time = new Date().getTime();
    var src = this.url + "?t=" + time +  "&data=" + data_send;

    var img = new Image(1,1);

    img.onload = img.onerror = function(){
     img.onload = null;
     img.onerror = null;
    };
    img.src = src;
    wait(100);

    return true;

  }
}


function wait(millis){
    var date = new Date();
    var curDate = null;
    do {curDate = new Date();}
    while(curDate-date < millis);
}

$(function(){
  $('.click').click(function(e){
    $this = $(this);
    var data = {
      'navigator' : navigator.userAgent,
      'screen' : [screen.width,screen.height],
      'location' : document.location.href,
      'ele_id' : $this.attr('id'),
      'href' : $this.attr('href'),
      'user_id' : user_id,
      'is_today_user' : is_today_user,
      'is_demo_user' : is_demo_user
    };

      capture = new Capture(server,data);
      capture.send();


  })

})
