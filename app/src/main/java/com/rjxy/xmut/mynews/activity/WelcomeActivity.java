package com.rjxy.xmut.mynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.domain.StartDomain;
import com.rjxy.xmut.mynews.utilis.API;
import com.rjxy.xmut.mynews.utilis.CacheUtils;
import com.rjxy.xmut.mynews.utilis.PrefUtils;

import java.io.File;

public class WelcomeActivity extends Activity {


    //boolean  isFirstIn=false;

    private static final int GOHOME = 1000;
    private static final int GOGUIDE = 1001;
    private StartDomain startDomain;
    private ImageView startImg;
    private TextView startText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        checkFristin();
        initView();

        initData();


    }

    /*
    * 判断是否是第一次进入。并且初始化网络数据
    * */
    private void checkFristin() {
        boolean isFirstIn = PrefUtils.getBoolean(getApplicationContext(), "isFirstIn", true);
        if (isFirstIn) {
            handler.sendEmptyMessageDelayed(GOGUIDE, 2000);

            PrefUtils.setBoolean(getApplicationContext(), "isFirstIn", false);
        } else {
            handler.sendEmptyMessageDelayed(GOHOME, 2000);
        }
        initIntertinfo();
    }

    /*
    * 判断是否有上一次获取数据的缓存，如果有的话，进行图片和text的设置，不过现在text的设置不知道为什么没有
    * 实现；
    * 如果缓存为空的话那么再更新一次网络数据
    * */
    private void initData() {

        //获取缓存路径
        File cacheDir = getCacheDir();
        String cachePath = cacheDir.getPath() + "/";
        final BitmapUtils bitmapUtils = new BitmapUtils(getApplicationContext(), cachePath);
        BitmapDisplayConfig bigPicDisplayConfig = new BitmapDisplayConfig();

        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(1000);
        bigPicDisplayConfig.setAnimation(animation);
        String cache = CacheUtils.getCache(API.START_IMAGE, getApplication());
        if (!TextUtils.isEmpty(cache)) {
            ProcessResult(cache);
            startText.setText(startText.getText());
            bitmapUtils.display(startImg, startDomain.getImg(),bigPicDisplayConfig);
        } else {
            startImg.setImageResource(R.drawable.welcome);
            //startText.setText("向开源致敬！");
            initIntertinfo();
        }

    }
    /*
    * 获取网络数据，并用Gson解析;
    *
    * */
    private void initIntertinfo() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, API.START_IMAGE, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                ProcessResult(result);
                CacheUtils.setCache(API.START_IMAGE, result, getApplicationContext());
            }
            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(WelcomeActivity.this, "发现网络连接失败,请检查网络", Toast.LENGTH_SHORT).show();
                initData();
            }
        });

    }

    //解析网络请求回来的数据的数据
    public void ProcessResult(String result) {
        Gson gson = new Gson();
        startDomain = gson.fromJson(result, StartDomain.class);
    }

    private void initView() {
        startImg = (ImageView) findViewById(R.id.startImg);
        startText = (TextView) findViewById(R.id.startText);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == GOHOME) {
                goHome();
            } else {
                goGuide();
            }
        }
    };

    private void goHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goGuide() {
        Intent intent = new Intent(getApplicationContext(), GuideAcitivty.class);
        startActivity(intent);
        finish();
    }


}
