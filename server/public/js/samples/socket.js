$(
    function(){
        log("start - tag:"+goldfish.tag());
        log("app_name:"+goldfish.app_name());
        
        $('input#btn_send').click(
            function(){
                var name = $('input#name').val();
                var msg = $('input#msg').val();
                goldfish.socket.send("<"+name+"> "+msg);
            }
        );
        
        goldfish.socket.onOpen = function(){
            log("* socket connected!");
        };

        goldfish.socket.onClose = function(){
            log("* socket closed");
            setTimeout(
                function(){ // 再接続
                    goldfish.socket.connect(host, 8082);
                },
                3000
            );
        };
        
        // 受信イベント
        goldfish.socket.onMessage = function(msg){
            log(msg);
        };
        
        log("goldfish.socket.connect("+host+", "+port+")");
        goldfish.socket.connect(host, 8082);
    }
);

var log = function(msg){
    console.log(msg);
    $('ul#log').prepend(
        $('<li>').html(msg)
    );
};
