package org.ubif.gynamic;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.*;

import com.google.common.primitives.UnsignedBytes;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.*;
import android.webkit.*;
import android.widget.*;

public class TagActivity extends Activity {
    private WebView webView;
    private String tag_id = null;
    private String base_url;
    private JsObject jsObj;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.base_url = this.getResources().getString(R.string.base_url);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag);
        trace("start");
        
        this.webView = (WebView)findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm){
                trace(cm.message()+" -- line:"+cm.lineNumber()+" of "+cm.sourceId());
                return true;
            }
            
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                trace("WebView.loadUrl : "+url);
                view.loadUrl(url);
                return true;
            }
        });
        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
        this.jsObj = new JsObject(this);
        webView.addJavascriptInterface(jsObj, "device");
        
        resolveIntent(this.getIntent());
    }
    

    void resolveIntent(Intent intent) {
        String action = intent.getAction();
        trace(action);
        try {
            Parcelable tag = intent.getParcelableExtra("android.nfc.extra.TAG");
            Field f = tag.getClass().getDeclaredField("mId");
            f.setAccessible(true);
            byte[] mId = (byte[]) f.get(tag);
            StringBuilder sb = new StringBuilder();
            for (byte id : mId) {
                String hexString = Integer.toHexString(UnsignedBytes.toInt(id));
                if (hexString.length() == 1) sb.append("0");
                sb.append(hexString);
            }
            String id = sb.toString();
            this.tag_id = id;
            notify("TAG:"+tag_id);
            this.webView.loadUrl(base_url+tag_id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    void trace(Object msg) {
        Log.v(this.getResources().getString(R.string.app_name), msg.toString());
    }
    void notify(Object msg){
        this.setTitle(this.getResources().getString(R.string.app_name)+" - "+msg.toString());
        trace(msg);
    }
    
}