<script src="jquery-1.4.2.min.js" type="text/javascript"></script><script src="jquery.checktree.js" type="text/javascript"></script><script>$(document).ready(function(){    $("ul.tree").checkTree({    });    });function objClick(obj){  if(obj.checked==false){  alert(obj.value);  }  }</script>