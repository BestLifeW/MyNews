package com.rjxy.xmut.mynews.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rjxy.xmut.mynews.R;


/**
 *
 */
public class Tab_Fragment_1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewTab_1 = inflater.inflate(R.layout.fragment_content1, container, false);
        return viewTab_1;
    }
}