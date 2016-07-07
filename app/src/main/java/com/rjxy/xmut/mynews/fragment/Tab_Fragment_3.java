package com.rjxy.xmut.mynews.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.rjxy.xmut.mynews.Adatper.Fragment1_adapter;
import com.rjxy.xmut.mynews.Adatper.Fragment2_adapter;
import com.rjxy.xmut.mynews.Adatper.Fragment3_adapter;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.domain.FafouDomain;
import com.rjxy.xmut.mynews.domain.LatestDomain;
import com.rjxy.xmut.mynews.utilis.API;
import com.rjxy.xmut.mynews.utilis.CacheUtils;


/**
 *
 */
public class Tab_Fragment_3 extends Fragment {

    public RecyclerView recyclerView;
    public View view;
    public FafouDomain fafouDomain;
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content3, container, false);
        initView();
        iniInterData();
        return view;
    }

    private void iniInterData() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, API.FAFOU_CONTENT, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                ProcessResult(result);
                CacheUtils.setCache(API.FAFOU_CONTENT, result, getActivity());
                Log.i("News", fafouDomain.getMsgs().get(1).getMsg());
                recyclerView.setAdapter(new Fragment3_adapter(getActivity(), fafouDomain));
            }

            public void onFailure(HttpException error, String msg) {

                Snackbar.make(view, "哎呀，网络没连接上", Snackbar.LENGTH_SHORT)
                        .setAction("去设置", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                                startActivity(wifiSettingsIntent);
                            }
                        })
                        .show();
                try {
                    //获取Cache
                    String cache = CacheUtils.getCache(API.FAFOU_CONTENT, getActivity());
                    ProcessResult(cache);
                    recyclerView.setAdapter(new Fragment3_adapter(getActivity(), fafouDomain));
                } catch (Exception e) {
                } finally {
                }

            }
        });
    }

    private void ProcessResult(String result) {
        Gson gson = new Gson();
        fafouDomain = gson.fromJson(result, FafouDomain.class);

    }

    private void initView() {

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        int spacingInPixels = 20;
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_fragment3);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //设置手指在屏幕上下拉多少距离开始刷新
        mSwipeRefreshLayout.setDistanceToTriggerSync(300);
        //设置下拉刷新按钮的背景颜色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        //设置下拉刷新按钮的大小
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iniInterData();
                        recyclerView.setAdapter(new Fragment3_adapter(getActivity(), fafouDomain));
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

    }

    public static Tab_Fragment_3 newInstance() {
        Bundle args = new Bundle();
        Tab_Fragment_3 fragment = new Tab_Fragment_3();
        fragment.setArguments(args);
        return fragment;
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if (parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }
}
