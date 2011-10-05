package org.ubif.gynamic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity {
    
    private EditText editTextTag;
    private Button btnOpenTag;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        trace("MainActivity start");
        
        this.editTextTag = (EditText) this.findViewById(R.id.editTextTag);
        this.btnOpenTag = (Button) this.findViewById(R.id.buttonOpenTag);
        final MainActivity that = this;
        this.btnOpenTag.setOnClickListener(new OnClickListener() {
           public void onClick(View v){
               String tag = editTextTag.getText().toString();
               if (tag.length() < 1) return;
               trace("input tag:"+tag);
               Intent it = new Intent(that, TagActivity.class);
               it.setAction(Action.ACTION_MAIN);
               it.putExtra("tag_id", tag);
               startActivityForResult(it, 1);
           }
        });
    }

    void trace(Object msg) {
        Log.v(this.getResources().getString(R.string.app_name), msg.toString());
    }
    void notify(Object msg){
        this.setTitle(this.getResources().getString(R.string.app_name)+" - "+msg.toString());
        trace(msg);
    }
}