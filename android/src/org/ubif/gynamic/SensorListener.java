// SensorEventを全てJsObjectに渡すListener

package org.ubif.gynamic;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

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
            case Sensor.TYPE_LIGHT:
                jsObj.setLight(e.values[0]);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                jsObj.setMagneticField(e.values[0], e.values[1], e.values[2]);
                break;
            case Sensor.TYPE_ORIENTATION:
                jsObj.setOrientation(e.values[0], e.values[1], e.values[2]);
                break;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
