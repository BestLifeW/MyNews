package com.rjxy.xmut.mynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.domain.LatestDomain;


/**
 *
 */
public class Tab_Fragment_3 extends Fragment {
    public RecyclerView recyclerView;
    public View  viewTab_3;
    public LatestDomain latestDomain;
    public SwipeRefreshLayout mSwipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewTab_3 = inflater.inflate(R.layout.fragment_content3, container, false);
        return viewTab_3;
    }

    public static Tab_Fragment_3 newInstance() {
        
        Bundle args = new Bundle();
        
        Tab_Fragment_3 fragment = new Tab_Fragment_3();
        fragment.setArguments(args);
        return fragment;
    }

}
