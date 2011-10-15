// SensorEventを全てJsObjectに渡すListener

package org.ubif.gynamic;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SensorListener implements SensorEventListener{
    
    private JsObject jsObj;
    
    public SensorListener(JsObject jsObj){
        this.jsObj = jsObj;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {        
    }

    public void onSensorChanged(SensorEvent e) {
        try{
            switch(e.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                jsObj.setAccelerometer(e.values[0], e.values[1], e.values[2]); // x,y,z
                break;
            case Sensor.TYPE_GYROSCOPE:
                jsObj.setGyroscope(e.values[0], e.values[1], e.values[2]); // x,y,z
                break;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
