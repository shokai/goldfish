var touch = { x : 0, y : 0 };
var moves = [];
var last_click = 0;

$(
    function(){
        console.log("start - tag:"+goldfish.tag());
        console.log("app_name:"+goldfish.app_name());

        
        goldfish.udp.onMessage = function(msg){
            console.log("> "+msg);  
        };
        
        goldfish.udp.open(host, port);
        var mouse = { x : 0, y : 0 };

        setInterval(
            function(){
                if(moves.length > 0){
                    var move = moves.shift();
                    goldfish.udp.send(move.x+','+move.y);
                }
            }, 10
        );

        $('#touch').bind('touchstart', function(e){
                             var t = e.originalEvent.touches[0];
                             touch = {x : t.pageX, y : t.pageY};
                         });

        $('#touch').bind('touchmove', function(e){
                             var t = e.originalEvent.touches[0];
                             var tmp = {x : t.pageX, y : t.pageY};
                             var msg = (tmp.x-touch.x)+','+(tmp.y-touch.y);
                             moves.push({x : tmp.x-touch.x, y : tmp.y-touch.y});
                             touch = tmp;
        });

        $('#btn_left').bind('touchstart', function(){
                                goldfish.udp.send('left_press');
                                console.log('left_press');
                                last_click = now();
                            });
        $('#btn_left').bind('touchend', function(){
                                if(now()-last_click < 1){
                                    console.log("now : "+(now()-last_click));
                                    goldfish.udp.send('left_release');
                                    console.log('left_release');
                                    $('#btn_left').removeClass('button_locked');
                                }
                                else{ // drag
                                    $('#btn_left').addClass('button_locked');
                                }
                           });
        $('#btn_right').bind('touchstart', function(){
                                 goldfish.udp.send('right_press');
                                 console.log('right_press');
                                 last_click = now();
                             });
        $('#btn_right').bind('touchend', function(){
                                 if(now()-last_click < 1){
                                     goldfish.udp.send('right_release');
                                     console.log('right_release');
                                     $('#btn_right').removeClass('button_locked');
                                 }
                                 else{ // drag
                                     $('#btn_right').addClass('button_locked');
                                 }
                             });
        $('#btn_center').bind('touchstart', function(){
                                 goldfish.udp.send('center_press');
                                 console.log('center_press')
                             });
        $('#btn_center').bind('touchend', function(){
                                 goldfish.udp.send('center_release');
                                 console.log('center_release')
                             });

        
    }
);

var now = function(){
    return parseInt((new Date)/1000);
}

