package com.rjxy.xmut.mynews.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.makeramen.roundedimageview.RoundedImageView;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.utilis.PrefUtils;

public class SettingActivity extends AppCompatActivity {

    private Toolbar mTooblar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mTooblar = (Toolbar) findViewById(R.id.tl_custom);

    }
    
    public void onClick(View view){
        findViewById(R.id.imageView1).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView3).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView4).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView5).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView6).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView7).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView8).setVisibility(View.INVISIBLE);
        String str="";
        switch (view.getId()){
            case R.id.color1 :
                findViewById(R.id.imageView1).setVisibility(View.VISIBLE);
                RoundedImageView roundedImageView=(RoundedImageView)view;
                mTooblar.setBackgroundColor(Color.parseColor("#FF6347"));
                str="1";

                break;
            case R.id.color2 :
                findViewById(R.id.imageView2).setVisibility(View.VISIBLE);
                mTooblar.setBackgroundColor(Color.parseColor("#FFE4C4"));
                PrefUtils.setString(this,"theme","#FF6347");
                str="2";
                break;
            case R.id.color3 :
                findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
                mTooblar.setBackgroundColor(Color.parseColor("#DAA520"));
                str="3";
                break;
            case R.id.color4 :
                findViewById(R.id.imageView4).setVisibility(View.VISIBLE);
                mTooblar.setBackgroundColor(Color.parseColor("#EE82EE"));
                str="4";
                break;
            case R.id.color5 :
                findViewById(R.id.imageView5).setVisibility(View.VISIBLE);
                mTooblar.setBackgroundColor(Color.parseColor("#DCDCDC"));
                str="5";
                break;
            case R.id.color6 :
                findViewById(R.id.imageView6).setVisibility(View.VISIBLE);
                mTooblar.setBackgroundColor(Color.parseColor("#D2B48C"));
                str="6";
                break;
            case R.id.color7 :
                findViewById(R.id.imageView7).setVisibility(View.VISIBLE);
                mTooblar.setBackgroundColor(Color.parseColor("#9370DB"));
                str="7";
                break;
            case R.id.color8 :
                findViewById(R.id.imageView8).setVisibility(View.VISIBLE);
                mTooblar.setBackgroundColor(Color.parseColor("#3CB371"));
                str="8";
                break;
            default: break;
        }
        PrefUtils.setString(this,"theme",str);
        Intent intent=new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        this.finish();

    }

    
}