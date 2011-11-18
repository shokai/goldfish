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

goldfish.tcp = {};
goldfish.tcp.connect = function(host, port){
    goldfish._tcp_connect(JSON.stringify({host:host, port:port}));
};

goldfish.tcp.send = function(msg){
    goldfish._tcp_send(msg);
};

goldfish.tcp.close = function(){
    goldfish._tcp_close();
};

goldfish.tcp.onOpen = function(){
};
goldfish.tcp.onClose = function(){
};
goldfish.tcp.onMessage = function(msg){
};

goldfish.udp = {};
goldfish.udp.open = function(host, port){
    goldfish._udp_open(JSON.stringify({host:host,port:port}));
};
goldfish.udp.send = function(msg){
    goldfish._udp_send(msg);
};
goldfish.udp._onMessage = function(data){
    console.log(data);
    goldfish.udp.onMessage(JSON.parse(msg));
};
goldfish.udp.onMessage = function(msg){
};

goldfish.eval_script = function(encoded_script){
    var script = decodeURI(encoded_script).replace('%3B',';');
    eval(script);
};
