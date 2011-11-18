$(
    function(){
        log("start - tag:"+goldfish.tag());
        log("app_name:"+goldfish.app_name());

        
        goldfish.udp.onMessage = function(msg){
            log("> "+msg);  
        };
        
        goldfish.udp.open(host, port);
        var mouse = { x : 0, y : 0 };

        setInterval(
            function(){
                goldfish.udp.send(mouse.x+","+mouse.y);
            }, 1
        );

        setInterval(
            function(){
                var acc = goldfish.accelerometer();
                mouse.x = acc.x*-1;
                mouse.y = acc.y*-1;
            }, 10
        );

        $('#btn_left').bind('touchstart', function(){
                                goldfish.udp.send('left_press');
                                log('left_press');
                            });
        $('#btn_left').bind('touchend', function(){
                                goldfish.udp.send('left_release');
                                log('left_release');
                           });
        $('#btn_right').bind('touchstart', function(){
                                 goldfish.udp.send('right_press');
                                 log('right_press')
                             });
        $('#btn_right').bind('touchend', function(){
                                 goldfish.udp.send('right_release');
                                 log('right_release')
                             });
        $('#btn_center').bind('touchstart', function(){
                                 goldfish.udp.send('center_press');
                                 log('center_press')
                             });
        $('#btn_center').bind('touchend', function(){
                                 goldfish.udp.send('center_release');
                                 log('center_release')
                             });

        
    }
);

var log = function(msg){
    console.log(msg);
    $('ul#log').prepend(
        $('<li>').html(msg.htmlEscape())
    );
};
