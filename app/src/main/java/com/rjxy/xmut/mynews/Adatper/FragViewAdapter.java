package com.rjxy.xmut.mynews.Adatper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lovec on 2016/7/1.
 */
 public class FragViewAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    List<String> titleList;

    public FragViewAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override//fragment匹配
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override//获取fragment的数量
    public int getCount() {
        return titleList.size();
    }

    @Override//对标题进行匹配
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override//销毁
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragmentList.get(position).onDestroy();
    }
}