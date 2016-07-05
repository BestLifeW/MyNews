package com.rjxy.xmut.mynews.Adatper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.activity.NewsItemActivity;
import com.rjxy.xmut.mynews.domain.CollectionDomain;
import com.rjxy.xmut.mynews.domain.LatestDomain;

import static android.view.View.inflate;

/**
 * Created by lovec on 2016/7/3.
 */
public class Fragment1_adapter extends RecyclerView.Adapter<Fragment1_adapter.LatestItemViewHolder> {

    private Context context;
    private LatestDomain latestDomain;
    private BitmapUtils bitmapUtils;
    private CollectionDomain collectionDomain = new CollectionDomain();

    public Fragment1_adapter(Context context, LatestDomain latestDomain) {
        this.context = context;
        this.latestDomain = latestDomain;
    }

    @Override
    public LatestItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bitmapUtils = new BitmapUtils(context);
        View view = View.inflate(context, R.layout.recyclerview_item, null);
        LatestItemViewHolder holder = new LatestItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final LatestItemViewHolder holder, final int position) {
        int i = position;
        String imageUrl = latestDomain.getStories().get(position).getImages().get(0);
        holder.NewsTitle.setText(latestDomain.getStories().get(position).getTitle());
        bitmapUtils.display(holder.NewsImg, imageUrl);
        //设置点击时间
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsItemActivity.class);
                intent.putExtra("News", latestDomain.getStories().get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return latestDomain.getStories().size();
    }

    public class LatestItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView NewsImg;
        private ImageView favorite;
        private TextView NewsTitle;
        private CardView cardView;
        private RelativeLayout item_rl;

        public LatestItemViewHolder(View itemView) {
            super(itemView);
            NewsImg = (ImageView) itemView.findViewById(R.id.iv_newsimg);
            NewsTitle = (TextView) itemView.findViewById(R.id.tv_newstitle);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
           // favorite = (ImageView) itemView.findViewById(R.id.favorite);
            item_rl = (RelativeLayout) itemView.findViewById(R.id.item_rl);
        }
    }
}
