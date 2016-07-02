package com.rjxy.xmut.mynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import com.rjxy.xmut.mynews.R;

public class WelcomeActivity extends Activity {


    boolean  isFirstIn=false;

    private  static final  int GOHOME=1000;
    private  static  final  int GOGUIDE=1001;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==GOHOME){
                goHome();
            }else {
                goGuide();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SharedPreferences preferences = getSharedPreferences("hhj", MODE_PRIVATE);

        isFirstIn=preferences.getBoolean("isFirstIn",true);
        if(isFirstIn){
            handler.sendEmptyMessageDelayed(GOGUIDE,2000);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putBoolean("isFirstIn",false);
            editor.commit();
        }else {
            handler.sendEmptyMessageDelayed(GOHOME,2000);
        }
    }

    private  void goHome(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void goGuide(){
        Intent intent = new Intent(getApplicationContext(),GuideAcitivty.class);
        startActivity(intent);
        finish();
    }
}
