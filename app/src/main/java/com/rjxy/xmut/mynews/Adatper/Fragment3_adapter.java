package com.rjxy.xmut.mynews.Adatper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.rjxy.xmut.mynews.R;
import com.rjxy.xmut.mynews.activity.CircleImageView;
import com.rjxy.xmut.mynews.domain.FafouDomain;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Fragment3_adapter extends RecyclerView.Adapter<Fragment3_adapter.FafouViewHolder> {

    private Context context;
    private FafouDomain fafouDomain;
    private BitmapUtils bitmapUtils;

    public Fragment3_adapter(Context context, FafouDomain fafouDomain) {
        this.context = context;
        this.fafouDomain = fafouDomain;
    }

    @Override
    public Fragment3_adapter.FafouViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bitmapUtils = new BitmapUtils(context);
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_fafou,
                parent, false);
        FafouViewHolder Holder = new FafouViewHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(Fragment3_adapter.FafouViewHolder holder, int position) {
        holder.fanfouzhuozhe.setText(fafouDomain.getMsgs().get(position).getRealname());
        holder.fafouneirong.setText(fafouDomain.getMsgs().get(position).getMsg());
        holder.fafouriqi.setText(fafouDomain.getMsgs().get(position).getTime());
        String avatar = fafouDomain.getMsgs().get(position).getAvatar();
        Log.i("test", "图片地址是"+avatar);
        bitmapUtils.display(holder.fafoutouxiang, avatar);
    }

    @Override
    public int getItemCount() {
        return fafouDomain.getMsgs().size();
    }

    public class FafouViewHolder extends RecyclerView.ViewHolder {

        private TextView fanfouzhuozhe;
        private TextView fafouneirong;
        private TextView fafouriqi;
        private CircleImageView fafoutouxiang;

        public FafouViewHolder(View itemView) {
            super(itemView);
            fafoutouxiang = (CircleImageView) itemView.findViewById(R.id.fafoutouxiang);
            fanfouzhuozhe = (TextView) itemView.findViewById(R.id.fanfouzhuozhe);
            fafouneirong = (TextView) itemView.findViewById(R.id.fafouneirong);
            fafouriqi = (TextView) itemView.findViewById(R.id.fafouriqi);

        }
    }
}
