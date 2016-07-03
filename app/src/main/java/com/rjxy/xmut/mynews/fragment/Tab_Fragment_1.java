package com.rjxy.xmut.mynews.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.activity.WelcomeActivity;
import com.rjxy.xmut.mynews.domain.LatestDomain;
import com.rjxy.xmut.mynews.utilis.API;
import com.rjxy.xmut.mynews.utilis.CacheUtils;


/**
 *
 */
public class Tab_Fragment_1 extends Fragment {



    public  RecyclerView recyclerView;
    public  View view;
    public  LatestDomain latestDomain;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content1, container, false);
        iniInterData();
        initView();
        return view;
    }

    private void iniInterData() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, API.LATEST, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                ProcessResult(result);
                CacheUtils.setCache(API.LATEST, result, getActivity());
                recyclerView.setAdapter(new Fragment1_adapter(getActivity(),latestDomain));
            }
            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(getActivity(), "发现网络连接失败,请检查网络", Toast.LENGTH_SHORT).show();
                //initData();
            }
        });
    }

    private void ProcessResult(String result) {
        Gson gson = new Gson();
         latestDomain = gson.fromJson(result, LatestDomain.class);

    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_fragment1);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        int spacingInPixels =20;
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    public static Tab_Fragment_1 newInstance() {
        Bundle args = new Bundle();
        Tab_Fragment_1 fragment = new Tab_Fragment_1();
        fragment.setArguments(args);
        return fragment;
    }
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if(parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }
}
