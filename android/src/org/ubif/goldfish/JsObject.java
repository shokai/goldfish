package org.ubif.goldfish;

import org.shokai.evmsg.*;
import java.net.URLEncoder;
import java.util.Date;

import org.json.JSONObject;

import android.content.SharedPreferences;
import android.provider.Settings;

public class JsObject {
    private AppActivity activity;
    private org.shokai.evmsg.TcpClient tcp;
    private org.shokai.evmsg.Udp udp;
    private String udp_host;
    private int udp_port;
    private String path;
    public String getPath(){return this.path;}
    
    public JsObject(AppActivity context, String path){
        this.activity = context;
        this.path = path;
    }
    
    public boolean exec(String javascript){
        try{
            String encoded = URLEncoder.encode(javascript);
            activity.loadUrl("javascript:goldfish.eval_script(\""+encoded+"\");");
        }
        catch(Exception ex){
            return false;
        }
        return true;
    }
    
    public boolean exec(String javascript, JsExecCallback callback){
        return this.exec(javascript);
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
    
    public void _tcp_connect(String host_port){
        if(this.tcp != null){
            this.tcp.close();
            this.tcp = null;
        }
        try{
            JSONObject json = new JSONObject(host_port);
            this.tcp = new TcpClient(json.getString("host"), json.getInt("port"));
            this.tcp.setReadInterval(100);
            final JsObject that = this;
            this.tcp.addEventHandler(new TcpClientEventHandler(){
                public void onMessage(String line) {
                    that.exec(path+".tcp.onMessage(\""+line+"\");");
                }

                public void onOpen() {
                    new Thread(){
                        public void run(){
                            that.exec(path+".tcp.onOpen();");
                        }
                    }.start();
                }

                public void onClose() {
                    new Thread(){
                        public void run(){
                            that.exec(path+".tcp.onClose();");
                        }
                    }.start();
                }
            });
            this.tcp.connect();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void _tcp_send(String msg){
        this.tcp.send(msg);
    }
    
    public void _tcp_close(){
        if(this.tcp != null){
            this.tcp.close();
            this.tcp = null;
        }
    }
    
    public void _udp_open(String host_port){
        try{
            JSONObject json = new JSONObject(host_port);
            if(this.udp != null){
                this.udp.close();
                this.udp = null;
            }
            this.udp = new Udp(json.getString("host"), json.getInt("port"));
            final JsObject that = this;
            this.udp.addEventHandler(new UdpEventHandler(){
                public void onMessage(String host, int port, String line) {
                    String data = "{host:\""+host+"\""+
                                  ",port:"+port+
                                  ",data:\""+line+"\" }";
                    that.exec(path+".udp._onMessage("+data+");");
                }
            });
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void _udp_send(String msg){
        if(this.udp == null) this._udp_open("{host:\""+udp_host+"\" ,port:"+udp_port+"}");
        this.udp.send(msg);
    }
    
    public String app_name(){
        return activity.getResources().getString(R.string.app_name);
    }
    
    public String tag(){
        if(!this.activity.getClass().getName().equals("org.ubif.goldfish.TagActivity")) return null;
        return ((TagActivity)activity).getTagId();
    }
    
    public String id(){
        SharedPreferences pref = activity.getPreferences(activity.MODE_PRIVATE);
        String id = pref.getString("goldfish.id", "");
        if(id.length() < 1){
            String android_id = Settings.Secure.getString(activity.getContentResolver(), Settings.System.ANDROID_ID);
            long now = (long)(new Date().getTime()/1000);
            id = MD5.hexdigit(android_id+"_"+now);
            pref.edit().putString("goldfish.id", id).commit();
        }
        return id;
    }
    
    public String id_reset(){
        activity.getPreferences(activity.MODE_PRIVATE).edit().putString("goldfish.id", "").commit();
        return id();
    }

    protected void stop(){
        if(this.tcp != null){
            this.tcp.close();
            this.tcp = null;
        }
        if(this.udp != null){
            this.udp.close();
            this.udp = null;
        }
    }
    
    public void exit(){
        this.stop();
        activity.finish();
    }
}
