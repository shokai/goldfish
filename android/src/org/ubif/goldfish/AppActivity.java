package org.ubif.goldfish;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebView;

public class AppActivity extends Activity{
    protected WebView webView;
    private String base_url;

    public String getBaseUrl(){
        if(this.base_url != null) return this.base_url;
        return this.base_url = this.getResources().getString(R.string.base_url);
    }

    public void loadUrl(String url){
        webView.loadUrl(url);
    }

    public void addJavascriptInterface(JsObject jsObj){
        if(webView!=null) webView.addJavascriptInterface(jsObj, jsObj.getPath());
    }
    
    void trace(Object msg) {
        Log.v(this.getResources().getString(R.string.app_name), msg.toString());
    }

    void notify(Object msg) {
        this.setTitle(this.getResources().getString(R.string.app_name)+" - "+msg.toString());
        trace(msg);
    }
}
