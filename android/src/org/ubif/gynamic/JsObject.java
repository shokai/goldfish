package org.ubif.gynamic;

import android.content.Context;

public class JsObject {
    private Context context;
    
    public JsObject(Context context){
        this.context = context;
    }
    
    public String app_name(){
        return context.getResources().getString(R.string.app_name);
    }

}
