goldfish.accelerometer = function(){
    return JSON.parse(goldfish._accelerometer());
};

goldfish.gyroscope = function(){
    return JSON.parse(goldfish._gyroscope());
};

goldfish.magnetic_field = function(){
    return JSON.parse(goldfish._magnetic_field());
};

goldfish.orientation = function(){
    return JSON.parse(goldfish._orientation());
};

goldfish.light = function(){
    return goldfish._light();
};

goldfish.socket = {};
goldfish.socket.connect = function(host, port){
    goldfish._socket_connect(JSON.stringify({host:host, port:port}));
};

goldfish.socket.send = function(msg){
    goldfish._socket_send(msg);  
};

goldfish.socket.close = function(){
    goldfish._socket_close();
};

goldfish.socket.onOpen = function(){
};
goldfish.socket.onClose = function(){
};
goldfish.socket.onMessage = function(msg){
};

goldfish.eval_script = function(encoded_script){
    var script = decodeURI(encoded_script);
    eval(script);
};


