var ctx;
var reset = function(){
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
};

var draw_circle = function(x, y, radius){
    ctx.strokeStyle = '#FF0000';
    ctx.fillStyle = '#FF0000';
    ctx.lineWidth = 6;
    ctx.beginPath();
    ctx.arc(x||ctx.canvas.width/2,
            y||ctx.canvas.height/2,
            radius||20, 0, Math.PI*2, false);
    ctx.fill();
    ctx.closePath();
    ctx.stroke();
};


$(function(){
      log("start - tag:"+goldfish.tag());
      log("app_name:"+goldfish.app_name());
      var canvas_tag = $('canvas#main');
      ctx = canvas_tag[0].getContext('2d');
      x = ctx.canvas.width/2;
      y = ctx.canvas.width/2;
      setInterval(
          function(){
              var ori = goldfish.orientation();
              x += ori.roll/-2;
              if(x < 1) x = 1;
              else if(x > ctx.canvas.width) x = ctx.canvas.width-1;
              y += ori.pitch/-2;
              if(y < 1) y = 1;
              else if(y > ctx.canvas.height) y = ctx.canvas.height-1;
              reset();
              draw_circle(x, y, 50);
          },
          50
      );
  }
 );

var log = function(msg){
    console.log(msg);
    $('ul#log').prepend(
        $('<li>').html(msg)
    );
};
