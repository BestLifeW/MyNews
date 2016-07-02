package com.rjxy.xmut.mynews.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rjxy.xmut.mynews.Adatper.FragViewAdapter;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.fragment.Tab_Fragment_1;
import com.rjxy.xmut.mynews.fragment.Tab_Fragment_2;
import com.rjxy.xmut.mynews.fragment.Tab_Fragment_3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private TabLayout tabLayout;
    private List<Fragment> fragmentsList;//fragment容器
    private List<String> titleList;//标签容器
    private DrawerLayout mDrawerLayout;
    private Toolbar mTooblar;
    private SearchView mSearchView;
    private ShareActionProvider mShareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initFragment();
    }

    private void initFragment() {
            tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            viewPager = (ViewPager) findViewById(R.id.viewPager);

            fragmentsList = new ArrayList<>();
            titleList = new ArrayList<>();

            fragmentsList.add(new Tab_Fragment_1());
            fragmentsList.add(new Tab_Fragment_2());
             fragmentsList.add(new Tab_Fragment_3());
           //将fragment添加到fragmentList的list容器里
//            for (int i = 0; i < 2; i++) { //重复添加 只是填充  没什么用
//                fragmentsList.add(new Tab_Fragment_1());
//            }
            titleList.add("新闻");
            titleList.add("体育");
            titleList.add("娱乐");

            tabLayout.setTabMode(TabLayout.MODE_FIXED);//tab的模式如果标签多的话用MODE_SCROLLABLE  少的话用MODE_FIXED
            //tabLayout.setBackgroundColor(Color.BLUE);

             FragViewAdapter adapter = new FragViewAdapter(getSupportFragmentManager(), fragmentsList, titleList);
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setTabsFromPagerAdapter(adapter);

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

    //这是测试
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        final MenuItem item1 = menu.findItem(R.id.search);
        MenuItem item2 = menu.findItem(R.id.share);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item1);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item2);
        return true;
    }



}
