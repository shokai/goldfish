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
    
    public String app_name(){
        return context.getResources().getString(R.string.app_name);
    }
    
    public String tag(){
        if(!this.context.getClass().getName().equals("org.ubif.gynamic.TagActivity")) return null;
        return ((TagActivity)context).getTagId();
    }

}
