package com.nmnews.nmnewsagency.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.OwnVideoDetailActivity;
import com.nmnews.nmnewsagency.modelclass.HashTagDetailModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class HashTagDetailAdapter extends RecyclerView.Adapter<HashTagDetailAdapter.MyViewHolder> {
   // private List<LocationModel> moviesList;
    private List<HashTagDetailModel.DataBeanX.DataBean.PagedRecordBean> moviesList;
    Context context;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public HashTagDetailAdapter(Context context, List<HashTagDetailModel.DataBeanX.DataBean.PagedRecordBean> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img_hashtag;
        public LinearLayout lin_hashtagdetail;

        public MyViewHolder(View view) {
            super(view);
          // title = (TextView) view.findViewById(R.id.hashtag_name);
           img_hashtag = (ImageView) view.findViewById(R.id.img_hashtag);
          //  lin_hashtagdetail = (LinearLayout) view.findViewById(R.id.lin_hashtagdetail);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hashtag_detail, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context)
                .load(moviesList.get(position).getImageUrl())
                .placeholder(R.color.adbag)
                .centerCrop()
                .into(holder.img_hashtag);
        holder.img_hashtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OwnVideoDetailActivity.class);
                intent.putExtra("newsid",moviesList.get(position).getNewsId());
                intent.putExtra("self","other");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
