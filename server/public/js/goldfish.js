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
