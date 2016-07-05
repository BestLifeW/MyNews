package com.rjxy.xmut.mynews.fragment;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
    private View viewTab_2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewTab_2 = inflater.inflate(R.layout.fragment_content2, container, false);
        initView();
        initInterData();
        return viewTab_2;
    }

    private void initInterData() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, API.THEME, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                ProcessResult(result);
                Log.i("News", themesDomain.getOthers().get(0).getDescription());
                CacheUtils.setCache(API.LATEST, result, getActivity());
            }
            @Override
            public void onFailure(HttpException error, String msg) {
                // Toast.makeText(getActivity(),"网络连接失败，请检查网络",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void ProcessResult(String result) {
        Gson gson = new Gson();
        themesDomain = gson.fromJson(result, ThemesDomain.class);
        recyclerView.setAdapter(new Fragment2_adapter(getActivity(), themesDomain));
    }

    private void initView() {
        recyclerView = (RecyclerView) viewTab_2.findViewById(R.id.rl_fragment2);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        int spacingInPixels = 20;
        //recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //设置下拉刷新
//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                iniInterData();
//                recyclerView.setAdapter(new Fragment1_adapter(getActivity(), latestDomain));
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });
    }

    public static Tab_Fragment_2 newInstance() {

        Bundle args = new Bundle();

        Tab_Fragment_2 fragment = new Tab_Fragment_2();
        fragment.setArguments(args);
        return fragment;
    }

}
