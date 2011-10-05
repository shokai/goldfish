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
    
    public String tag(){
        if(!this.context.getClass().getName().equals("org.ubif.gynamic.TagActivity")) return null;
        return ((TagActivity)context).getTagId();
    }

}
