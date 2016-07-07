package com.rjxy.xmut.mynews.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.domain.LatestNewsDomain;
import com.rjxy.xmut.mynews.utilis.API;
import com.rjxy.xmut.mynews.utilis.PrefUtils;

public class NewsItemActivity2 extends AppCompatActivity {
    private Toolbar mTooblar;
    private ImageView mItemImg;     //图片
    private TextView mItenmInfo;
    private WebView webViewRead;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private CollapsingToolbarLayout toolbarLayout;
    private String html;
    private int newsId;
    private BitmapUtils bitmaputils;
    private LatestNewsDomain latestNewsDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeThemes();
        super.onCreate(savedInstanceState);
        bitmaputils = new BitmapUtils(getApplicationContext());
        setContentView(R.layout.activity_news_item);
        //获取新闻的ID；
        newsId = getIntent().getIntExtra("News", 0);
        Log.i("test", "onCreate: "+newsId);
        //初始化控件
        initView();
        initInterData();
    }

    /*
    * 初始化网络数据
    * */
    private void initInterData() {
        String url = API.LATEST_NEW + newsId;
        HttpUtils httputils = new HttpUtils();
        httputils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("test", "onSuccess:" + "连接成功了");
                String result = responseInfo.result;
                ProcessData(result);
                fillData();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Snackbar.make(coordinatorLayout, "哎呀，网络没连接上", Snackbar.LENGTH_SHORT)
                        .setAction("去设置", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                                startActivity(wifiSettingsIntent);
                            }
                        })
                        .show();
            }
        });
    }

    /*
    * 填充数据
    *
    * */
    private void fillData() {
        bitmaputils.display(mItemImg, latestNewsDomain.getImage());
        webViewRead.getSettings().setAppCacheEnabled(false);
        webViewRead.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webViewRead.canGoBack()) {
                        webViewRead.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        webViewRead.getSettings().setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        webViewRead.getSettings().setBuiltInZoomControls(false);
        //缓存
        webViewRead.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        webViewRead.getSettings().setDomStorageEnabled(true);
        //开启application Cache功能
        webViewRead.getSettings().setAppCacheEnabled(false);
        //webViewRead
        webViewRead.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webViewRead.loadUrl(url);
                return true;
            }
        });


        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_master.css\" type=\"text/css\">";
        String parseByTheme = "<body>\n";
        String content = latestNewsDomain.getBody();
        html = "<!DOCTYPE html>\n"
                + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "\t<meta charset=\"utf-8\" />"
                + css
                + "\n</head>\n"
                + parseByTheme
                + content
                + "</body></html>";
        webViewRead.loadDataWithBaseURL("x-data://base", html, "text/html", "utf-8", null);

        //设置页面的标题
        setCollapsingToolbarLayoutTitle();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这是分享按钮！
                Intent shareIntent = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
                String shareText = "新闻标题" + latestNewsDomain.getTitle() + "," + latestNewsDomain.getShare_url() + "(来自我的新闻)";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(shareIntent, "分享至"));
            }
        });
    }

    /*
    * 处理返回的数据
    *
    * */
    private void ProcessData(String result) {
        Gson gson = new Gson();
        latestNewsDomain = gson.fromJson(result, LatestNewsDomain.class);
        Log.i("", "ProcessData: ");
    }

    private void initView() {
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.collapseActionView);
        mItemImg = (ImageView) findViewById(R.id.news_header);//新闻图片
        mTooblar = (Toolbar) findViewById(R.id.item_tl);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        setSupportActionBar(mTooblar);
        //设置toolbar 返回时间
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webViewRead = (WebView) findViewById(R.id.item_newsinfo);
        webViewRead.setScrollbarFadingEnabled(true);
        //设置分享按钮点击

    }

    private void changeThemes() {
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
    }

    private void setCollapsingToolbarLayoutTitle() {
        toolbarLayout.setTitle(latestNewsDomain.getTitle());
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
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
}
