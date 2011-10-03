package org.shokai.goldfish2;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.*;

import org.shokai.goldfish2.R;

import com.google.common.primitives.UnsignedBytes;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.*;
import android.webkit.WebView;
import android.widget.*;

public class TagActivity extends Activity {

    private TextView textViewTag;
    private WebView webView;
    private String tag_id = null;
    private String url = "http://dev.shokai.org/g/tag/";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag);
        trace("start");
        
        this.textViewTag = (TextView)findViewById(R.id.textViewTag);
        textViewTag.setText("please touch NFC tag");
        this.webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
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
            textViewTag.setText("TAG : " + tag_id);
            trace(tag_id);
            this.webView.loadUrl(url+tag_id);
        }
        catch (Exception e) {
            e.printStackTrace();
             textViewTag.setText("TAG error");
        }
        
    }

    void trace(Object msg) {
        Log.v(this.getResources().getString(R.string.app_name), msg.toString());
    }
}