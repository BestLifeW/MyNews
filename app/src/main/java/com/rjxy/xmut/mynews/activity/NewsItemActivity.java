package com.rjxy.xmut.mynews.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpCache;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.domain.LatestNewsDomain;
import com.rjxy.xmut.mynews.utilis.API;

public class NewsItemActivity extends AppCompatActivity {

    private Toolbar mTooblar;
    private TextView mItemTitle;
    private ImageView mItemImg;
    private TextView mItenmInfo;
    private int newsId;
    private WebView webViewRead;
    private LatestNewsDomain newsList;
    private LinearLayout lr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
        newsId = getIntent().getIntExtra("News", 0);
        iniInterDate();
        initView();
    }


    //初始化布局
    private void initView() {
       // mItemTitle = (TextView) findViewById(R.id.item_title); //新闻标题
        webViewRead = (WebView) findViewById(R.id.item_newsinfo);//新闻文章
        lr = (LinearLayout) findViewById(R.id.lr);
        // mItemImg = (ImageView) findViewById(R.id.item_newimg);//新闻图片
        mTooblar = (Toolbar) findViewById(R.id.item_tl);

        setSupportActionBar(mTooblar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void iniInterDate() {
        String url = API.LATEST_NEW + newsId;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                processData(result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Snackbar.make(lr,"请检查网络连接",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void processData(String result) {
        Gson gson = new Gson();
        newsList = gson.fromJson(result, LatestNewsDomain.class);
       // Log.i("解析后的数据为", newsList.getBody());

        fillData();
    }

    private void fillData() {
        // mItemTitle.setText(newsList.getTitle());
        //以下填充的是来自json返回的网页内容
        mTooblar.setTitle(newsList.getTitle());
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_master.css\" type=\"text/css\">";
        String parseByTheme = "<body>\n";
        String content = newsList.getBody();
        String html = "<!DOCTYPE html>\n"
                + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "\t<meta charset=\"utf-8\" />"
                + css
                + "\n</head>\n"
                + parseByTheme
                + content
                + "</body></html>";
        webViewRead.loadDataWithBaseURL("x-data://base", html, "text/html", "utf-8", null);
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
