var log = function(msg){
    console.log(msg);
    $('ul#log').prepend(
        $('<li>').html(msg)
    );
};

$(function(){
      log("start - tag:"+gynamic.tag());
      log("app_name:"+gynamic.app_name());
      setInterval(
          function(){
              var acc = gynamic.accelerometer();
              $('#accelerometer').html('x:'+acc.x+',y:'+acc.y+',z:'+acc.z);
          }, 500
      );
  }
 );
