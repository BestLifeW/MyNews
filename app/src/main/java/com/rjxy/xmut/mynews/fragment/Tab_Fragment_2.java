package com.rjxy.xmut.mynews.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.domain.LatestDomain;
import com.rjxy.xmut.mynews.domain.ThemesDomain;
import com.rjxy.xmut.mynews.utilis.API;
import com.rjxy.xmut.mynews.utilis.CacheUtils;


/**
 *
 */
public class Tab_Fragment_2 extends Fragment {


    private ThemesDomain themesDomain;
    private RecyclerView recyclerView;
    private View view;
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content2, container, false);
        initView();
        initInterData();
        return view;
    }

    private void initInterData() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, API.THEME, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                ProcessResult(result);
                //Log.i("News", themesDomain.getOthers().get(0).getDescription());
                CacheUtils.setCache(API.THEME, result, getActivity());
            }

            @Override
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
                    String cache = CacheUtils.getCache(API.THEME, getActivity());
                    ProcessResult(cache);
                    // Log.i("cache", "缓存里面的数据是:" + latestDomain.getStories().get(0).getTitle());
                    recyclerView.setAdapter(new Fragment2_adapter(getActivity(), themesDomain));
                    //iniInterData();
                } catch (Exception e) {
                    //Toast.makeText(getActivity(), "数据获取失败，请检查网络", Toast.LENGTH_SHORT).show();
                } finally {
                    Snackbar.make(view, "哎呀，网络没连接上", Snackbar.LENGTH_SHORT)
                            .setAction("去设置", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                                    startActivity(wifiSettingsIntent);
                                }
                            })
                            .show();
                }

            }
        });
    }

    private void ProcessResult(String result) {
        Gson gson = new Gson();
        themesDomain = gson.fromJson(result, ThemesDomain.class);
        recyclerView.setAdapter(new Fragment2_adapter(getActivity(), themesDomain));
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_fragment2);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        int spacingInPixels = 20;
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //设置下拉刷新
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initInterData();
                recyclerView.setAdapter(new Fragment2_adapter(getActivity(), themesDomain));
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public static Tab_Fragment_2 newInstance() {

        Bundle args = new Bundle();

        Tab_Fragment_2 fragment = new Tab_Fragment_2();
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
