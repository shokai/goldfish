package org.ubif.goldfish;

import java.lang.reflect.Field;
import java.util.*;

import com.google.common.primitives.UnsignedBytes;

import android.content.Intent;
import android.hardware.*;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.*;
import android.widget.*;

public class TagActivity extends AppActivity {
    private String tag_id = null;

    public String getTagId() {
        return this.tag_id;
    }

    private JsObject jsObj;
    private SensorManager sm;
    private SensorListener sl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag);
        trace("TagActivity start");

        final TagActivity that = this;
        this.webView = (WebView)findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                trace(cm.message()+" -- line:"+cm.lineNumber()+" of "+cm.sourceId());
                return true;
            }

            public void onProgressChanged(WebView view, int count) {
                android.view.View progBarWrapper = that.findViewById(R.id.progressBarWrapper);
                ProgressBar bar = (ProgressBar) that.findViewById(R.id.progressBar);
                bar.setProgress(count);
                if(count >= 100) progBarWrapper.setVisibility(View.GONE);
                else progBarWrapper.setVisibility(View.VISIBLE);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                trace("WebView.loadUrl : " + url);
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        this.jsObj = new JsObject(this, "goldfish");
        this.addJavascriptInterface(jsObj);

        this.sm = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        this.sl = new SensorListener(this.jsObj);

        resolveIntent(this.getIntent());
    }

    void resolveIntent(Intent intent) {
        String action = intent.getAction();
        trace(action);
        try {
            if (action.equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
                Parcelable tag = intent
                        .getParcelableExtra("android.nfc.extra.TAG");
                Field f = tag.getClass().getDeclaredField("mId");
                f.setAccessible(true);
                byte[] mId = (byte[]) f.get(tag);
                StringBuilder sb = new StringBuilder();
                for (byte id : mId) {
                    String hexString = Integer.toHexString(UnsignedBytes
                            .toInt(id));
                    if (hexString.length() == 1) sb.append("0");
                    sb.append(hexString);
                }
                String id = sb.toString();
                this.tag_id = id;
            }
            else if (action.equals(Intent.ACTION_MAIN)) {
                this.tag_id = intent.getExtras().getString("tag_id");
            }
            notify("TAG:" + tag_id);
            String url = getBaseUrl() + "/tag/" + tag_id;
            trace("open " + url);
            this.webView.loadUrl(url);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        int[] arr = { Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_GYROSCOPE,
                      Sensor.TYPE_LIGHT, Sensor.TYPE_MAGNETIC_FIELD,
                      Sensor.TYPE_ORIENTATION };
        for (int i = 0; i < arr.length; i++) {
            int type = arr[i];
            List<Sensor> sensors = sm.getSensorList(type);
            if (sensors.size() > 0) {
                sm.registerListener(this.sl, sensors.get(0), SensorManager.SENSOR_DELAY_FASTEST);
            }
        }
        this.webView.resumeTimers();
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this.sl);
        this.jsObj.stop();
        this.webView.pauseTimers();
        super.onPause();
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean supRetVal = super.onCreateOptionsMenu(menu);
        menu.add(0, MenuId.RELOAD, 0, "Reload");
        menu.add(0, MenuId.ABOUT, 0, "About");
        return supRetVal;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case MenuId.RELOAD:
            webView.clearCache(true);
            webView.loadUrl(webView.getUrl());
            return true;
        case MenuId.ABOUT:
            webView.loadUrl(getBaseUrl() + "/about");
            return true;
        }
        return false;
    }

}