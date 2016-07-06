package com.rjxy.xmut.mynews.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.makeramen.roundedimageview.RoundedImageView;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.utilis.PrefUtils;

public class SettingActivity extends AppCompatActivity {

    private Toolbar mTooblar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String theme = PrefUtils.getString(this, "theme", "");
        if (!"".equals(theme)) {
            switch (theme) {
                case "1":
                    setTheme(R.style.AppTheme1);
                    break;
                case "2":
                    setTheme(R.style.AppTheme2);
                    break;
                case "3":
                    setTheme(R.style.AppTheme3);
                    break;
                case "4":
                    setTheme(R.style.AppTheme4);
                    break;
                case "5":
                    setTheme(R.style.AppTheme5);
                    break;
                case "6":
                    setTheme(R.style.AppTheme6);
                    break;
                case "7":
                    setTheme(R.style.AppTheme7);
                    break;
                case "8":
                    setTheme(R.style.AppTheme8);
                    break;
                default:
                    break;
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mTooblar = (Toolbar) findViewById(R.id.tl_custom);
        setSupportActionBar(mTooblar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTooblar.setTitle("设置");

        if (!"".equals(theme)) {
            switch (theme) {
                case "1":
                    findViewById(R.id.imageView1).setVisibility(View.VISIBLE);
                    break;
                case "2":
                    findViewById(R.id.imageView2).setVisibility(View.VISIBLE);
                    break;
                case "3":
                    findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
                    break;
                case "4":
                    findViewById(R.id.imageView4).setVisibility(View.VISIBLE);
                    break;
                case "5":
                    findViewById(R.id.imageView5).setVisibility(View.VISIBLE);
                    break;
                case "6":
                    findViewById(R.id.imageView6).setVisibility(View.VISIBLE);
                    break;
                case "7":
                    findViewById(R.id.imageView7).setVisibility(View.VISIBLE);
                    break;
                case "8":
                    findViewById(R.id.imageView8).setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        findViewById(R.id.imageView1).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView3).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView4).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView5).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView6).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView7).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView8).setVisibility(View.INVISIBLE);
        String str = "";
        switch (view.getId()) {
            case R.id.color1:
                findViewById(R.id.imageView1).setVisibility(View.VISIBLE);
                RoundedImageView roundedImageView = (RoundedImageView) view;
                str = "1";

                break;
            case R.id.color2:
                findViewById(R.id.imageView2).setVisibility(View.VISIBLE);
                str = "2";
                break;
            case R.id.color3:
                findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
                str = "3";
                break;
            case R.id.color4:
                findViewById(R.id.imageView4).setVisibility(View.VISIBLE);
                str = "4";
                break;
            case R.id.color5:
                findViewById(R.id.imageView5).setVisibility(View.VISIBLE);
                str = "5";
                break;
            case R.id.color6:
                findViewById(R.id.imageView6).setVisibility(View.VISIBLE);
                str = "6";
                break;
            case R.id.color7:
                findViewById(R.id.imageView7).setVisibility(View.VISIBLE);
                str = "7";
                break;
            case R.id.color8:
                findViewById(R.id.imageView8).setVisibility(View.VISIBLE);
                str = "8";
                break;
            default:
                break;
        }
        PrefUtils.setString(this, "theme", str);
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }


}
