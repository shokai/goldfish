var log = function(msg){
    console.log(msg);
    $('ul#log').prepend(
        $('<li>').html(msg)
    );
};

$(function(){
      log("start - tag:"+goldfish.tag());
      log("app_name:"+goldfish.app_name());
      log("id:"+goldfish.id());
      $('#info .id .value').html(goldfish.id());
      setInterval(
          function(){
              var acc = goldfish.accelerometer();
              $('#accelerometer .x .value').html(acc.x);
              $('#accelerometer .y .value').html(acc.y);
              $('#accelerometer .z .value').html(acc.z);
              var gyro = goldfish.gyroscope();
              $('#gyroscope .x .value').html(gyro.x);
              $('#gyroscope .y .value').html(gyro.y);
              $('#gyroscope .z .value').html(gyro.z);
              var magnet = goldfish.magnetic_field();
              $('#magnet .x .value').html(magnet.x);
              $('#magnet .y .value').html(magnet.y);
              $('#magnet .z .value').html(magnet.z);
              var ori = goldfish.orientation();
              $('#orientation .azimush .value').html(ori.azimush);
              $('#orientation .pitch .value').html(ori.pitch);
              $('#orientation .roll .value').html(ori.roll);
              var light = goldfish.light();
              $('#light .value').html(light);
          }, 50
      );

      $('#btn_reset_id').click(
          function(){
              var id = goldfish.id_reset();
              log('id reset:'+id);
              $('#info .id .value').html(id);
          }
      );

      $('#btn_exit').click(
          function(){
              alert('bye');
              goldfish.exit();
          }
      );
  }
 );
