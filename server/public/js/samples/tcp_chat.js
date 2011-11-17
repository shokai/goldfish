$(
    function(){
        log("start - tag:"+goldfish.tag());
        log("app_name:"+goldfish.app_name());
        
        $('input#btn_send').click(
            function(){
                var name = $('input#name').val();
                var msg = $('input#msg').val();
                goldfish.tcp.send("<"+name+"> "+msg);
                $('input#msg').val('');
            }
        );
        
        goldfish.tcp.onOpen = function(){
            log("* tcp connected! "+host+":"+port);
        };

        goldfish.tcp.onClose = function(){
            log("* tcp closed");
            setTimeout(
                function(){ // reConnect
                    goldfish.tcp.connect(host, port);
                },
                3000
            );
        };
        
        // on receive message
        goldfish.tcp.onMessage = function(msg){
            log(msg);
        };
        
        goldfish.tcp.connect(host, port);
    }
);

var log = function(msg){
    console.log(msg);
    $('ul#log').prepend(
        $('<li>').html(msg.htmlEscape())
    );
};
