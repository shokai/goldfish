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
        if (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            try{
                jsObj.setAccelerometer(e.values[0], e.values[1], e.values[2]); // x,y,z
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
