package org.ubif.gynamic;

import android.content.Context;

public class JsObject {
    private Context context;
    
    public JsObject(Context context){
        this.context = context;
    }
    
    private float accX, accY, accZ;
    public void setAccelerometer(float x, float y, float z){
        this.accX = x;
        this.accY = y;
        this.accZ = z;
    }
    public String _accelerometer(){
        return "{\"x\":" + Float.toString(this.accX) +
               ",\"y\":" + Float.toString(this.accY) +
               ",\"z\":" + Float.toString(this.accZ) + "}";
    }

    private float gyroX, gyroY, gyroZ;
    public void setGyroscope(float x, float y, float z){
        this.gyroX = x;
        this.gyroY = y;
        this.gyroZ = z;
    }
    public String _gyroscope(){
        return "{\"x\":" + Float.toString(this.gyroX) +
               ",\"y\":" + Float.toString(this.gyroY) +
               ",\"z\":" + Float.toString(this.gyroZ) + "}";
    }
    
    private float light;
    public void setLight(float light){
        this.light = light;
    }
    
    public String _light(){
        return Float.toString(this.light);
    }
    
    private float magnetX, magnetY, magnetZ;
    public void setMagneticField(float x, float y, float z){
        this.magnetX = x;
        this.magnetY = y;
        this.magnetZ = z;
    }
    
    public String _magnetic_field(){
        return "{\"x\":" + Float.toString(this.magnetX) +
               ",\"y\":" + Float.toString(this.magnetY) +
               ",\"z\":" + Float.toString(this.magnetZ) + "}";
    }
    
    private float azimuth, pitch, roll;
    public void setOrientation(float azimush, float pitch, float roll){
        this.azimuth = azimush;
        this.pitch = pitch;
        this.roll = roll;
    }
    
    public String _orientation(){
        return "{\"azimush\":" + Float.toString(this.azimuth) +
               ",\"pitch\":" + Float.toString(this.pitch) +
               ",\"roll\":" + Float.toString(this.roll) + "}";
    }
    
    public String app_name(){
        return context.getResources().getString(R.string.app_name);
    }
    
    public String tag(){
        if(!this.context.getClass().getName().equals("org.ubif.gynamic.TagActivity")) return null;
        return ((TagActivity)context).getTagId();
    }

}
