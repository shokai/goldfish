package org.ubif.goldfish;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class AppActivity extends Activity{
    protected WebView webView;

    public void loadUrl(String url){
        webView.loadUrl(url);
    }

    public void addJavascriptInterface(JsObject jsObj){
        if(webView!=null) webView.addJavascriptInterface(jsObj, jsObj.getPath());
    }

    private static class MenuId{
        private static final int RELOAD = 1;
        private static final int HELP = 2;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean supRetVal = super.onCreateOptionsMenu(menu);
        menu.add(0, MenuId.RELOAD, 0, "RELOAD");
        return supRetVal;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MenuId.RELOAD:
                webView.clearCache(true);
                webView.loadUrl(webView.getUrl());
                return true;
            case MenuId.HELP:
                return true;
        }
        return false;
    }
}
