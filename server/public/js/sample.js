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
              $('#accelerometer .x .value').html(acc.x);
              $('#accelerometer .y .value').html(acc.y);
              $('#accelerometer .z .value').html(acc.z);
              var gyro = gynamic.gyroscope();
              $('#gyroscope .x .value').html(gyro.x);
              $('#gyroscope .y .value').html(gyro.y);
              $('#gyroscope .z .value').html(gyro.z);
          }, 50
      );
  }
 );
