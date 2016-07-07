package com.rjxy.xmut.mynews.Adatper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.activity.NewsItemActivity2;
import com.rjxy.xmut.mynews.domain.LatestDomain;
import com.rjxy.xmut.mynews.domain.ThemesDomain;

/**
 * Created by Administrator on 2016/7/4.
 */
public class Fragment2_adapter extends RecyclerView.Adapter<Fragment2_adapter.ThemesItemViewHolder> {

    private Context context;
    private ThemesDomain themesDomain;
    private BitmapUtils bitmapUtils;

    public Fragment2_adapter(Context context, ThemesDomain themesDomain) {
        this.context = context;
        this.themesDomain = themesDomain;
    }


    @Override
    public ThemesItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bitmapUtils = new BitmapUtils(context);
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,
                parent, false);
        ThemesItemViewHolder Holder = new ThemesItemViewHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(ThemesItemViewHolder holder, final int position) {
        final int i = position;
        //String imgUrl = themesDomain.getEditors().get(position).getAvatar();
        holder.NewsTitle.setText(themesDomain.getStories().get(position).getTitle());
        //bitmapUtils.display(holder.NewsImg, imgUrl);
        //设置点击时间
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsItemActivity2.class);
                intent.putExtra("News", themesDomain.getStories().get(position).getId());
                context.startActivity(intent);

            }
        });
        // Log.i("imgUrl", "onBindViewHolder: " + imgUrl);
    }

    @Override
    public int getItemCount() {
        return themesDomain.getStories().size();
    }

    public class ThemesItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView NewsImg;
        private TextView NewsTitle;
        private CardView cardView;

        public ThemesItemViewHolder(View itemView) {
            super(itemView);
            NewsImg = (ImageView) itemView.findViewById(R.id.iv_newsimg);
            NewsTitle = (TextView) itemView.findViewById(R.id.tv_newstitle);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
