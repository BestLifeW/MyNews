package com.rjxy.xmut.mynews;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mTooblar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

    }




    private  void initData(){
        //设置drawerLayout的事件
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.i("test","当前状态是"+newState);
            }
        });
        //toolbar开始

        mTooblar = (Toolbar) findViewById(R.id.tl_custom);
        setSupportActionBar(mTooblar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mTooblar,R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
    }




}
