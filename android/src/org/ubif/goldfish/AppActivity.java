package org.ubif.goldfish;

import android.app.Activity;
import android.webkit.WebView;

public class AppActivity extends Activity{
    protected WebView webView;
    
    public void loadUrl(String url){
        webView.loadUrl(url);
    }
}
