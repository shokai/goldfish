
gynamic.accelerometer = function(){
    return JSON.parse(gynamic._accelerometer());
};

gynamic.gyroscope = function(){
    return JSON.parse(gynamic._gyroscope());
};

gynamic.magnetic_field = function(){
    return JSON.parse(gynamic._magnetic_field());
}

gynamic.orientation = function(){
    return JSON.parse(gynamic._orientation());
}