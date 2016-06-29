package com.rjxy.xmut.mynews;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;

public class GuideAcitivty extends Activity {
    private ArrayList<ImageView> mImageViewList;
    private ViewPager viewPager;
    private ImageView imageViews [];
    private int Pointview[];
    private Button bt_enter;
    //引导页图片Id
    private int[] mImageIds = new int[]{R.drawable.guide_1,
            R.drawable.guide_2, R.drawable.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.vp_guide);
        bt_enter = (Button) findViewById(R.id.bt_enter);
        initData();
        viewPager.setAdapter(new GuideAdapter());
        initPointView();
        initPoint();

        bt_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initPointView() {
        Pointview =new int[] {R.id.img1, R.id.img2, R.id.img3};
        imageViews=new ImageView[Pointview.length];
        for (int i = 0; i < Pointview.length; i++) {
            imageViews[i]=(ImageView)findViewById(Pointview[i]);
        }
    }

    private void initPoint() {
        imageViews[0].setImageResource(R.drawable.login_point_selected);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position==2){
                    bt_enter.setVisibility(View.VISIBLE);

                }else {
                    bt_enter.setVisibility(View.GONE);
                }
                for (int i = 0; i <imageViews.length; i++) {
                    if(i==position){
                        imageViews[i].setImageResource(R.drawable.login_point_selected);

                    }else {
                        imageViews[i].setImageResource(R.drawable.login_point);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);// 通过设置背景,可以让宽高填充布局
            mImageViewList.add(view);
        }
    }
    class GuideAdapter extends PagerAdapter {

        // item的个数
        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // 初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            return view;
        }

        // 销毁item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
