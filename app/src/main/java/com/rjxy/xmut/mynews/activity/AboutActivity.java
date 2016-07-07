package com.rjxy.xmut.mynews.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjxy.xmut.mynews.R;


public class AboutActivity extends AppCompatActivity {


    private LinearLayout layoutRate;
    private LinearLayout layoutFeedback;
    private LinearLayout layoutCoffee;
    private TextView tvGitHub;
    private TextView tvZhihu;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initViews();

        layoutRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                try {
                    Uri uri = Uri.parse("market://details?id="+getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex){
                    Snackbar.make(layoutRate, R.string.no_app_store_found,Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        layoutFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Uri uri = Uri.parse(getString(R.string.sendto));
                    Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                    intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.mail_topic));
                    intent.putExtra(Intent.EXTRA_TEXT,
                            getString(R.string.device_model) + Build.MODEL
                                    + "\n" + getString(R.string.sdk_version) + Build.VERSION.RELEASE + "\n");
                    startActivity(intent);
                }catch (android.content.ActivityNotFoundException ex){
                    Snackbar.make(layoutFeedback, R.string.no_mail_app,Snackbar.LENGTH_SHORT).show();
                }
            }
        });



        tvGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(getString(R.string.github_url))));
            }
        });

        tvZhihu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(getString(R.string.zhihu_url))));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initViews() {

        layoutRate = (LinearLayout) findViewById(R.id.LL_rate);
        layoutFeedback = (LinearLayout) findViewById(R.id.LL_feedback);
        layoutCoffee = (LinearLayout) findViewById(R.id.LL_coffee);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvGitHub = (TextView) findViewById(R.id.tv_GitHub);
        tvZhihu = (TextView) findViewById(R.id.tv_zhihu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
