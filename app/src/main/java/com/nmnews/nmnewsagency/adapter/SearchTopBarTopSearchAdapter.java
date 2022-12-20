package com.nmnews.nmnewsagency.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.HashTagDetailActivity;
import com.nmnews.nmnewsagency.activity.OwnVideoDetailActivity;
import com.nmnews.nmnewsagency.activity.UserProfileActivity;
import com.nmnews.nmnewsagency.modelclass.SearchTopSearchModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class SearchTopBarTopSearchAdapter extends RecyclerView.Adapter<SearchTopBarTopSearchAdapter.MyViewHolder> {
    private List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList;
    private Context context;

    public SearchTopBarTopSearchAdapter(Context context, List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView search_user_name, search_subuser_name;
        public ImageView search_image;
        RelativeLayout rel_tabing;

        public MyViewHolder(View view) {
            super(view);
            search_image = (ImageView) view.findViewById(R.id.search_image);
            search_user_name = (TextView) view.findViewById(R.id.search_user_name);
            search_subuser_name = (TextView) view.findViewById(R.id.search_subuser_name);
            rel_tabing = (RelativeLayout) view.findViewById(R.id.rel_tabing);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_peopletopbartabing, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (moviesList.get(position).getSearchType().equals("HASHTAG")) {
            holder.search_user_name.setText(moviesList.get(position).getTitle());
            holder.search_subuser_name.setText(String.valueOf(moviesList.get(position).getSearchcount()));
        } else if (moviesList.get(position).getSearchType().equals("PEOPLE")) {
            holder.search_user_name.setText(moviesList.get(position).getUserName());
            holder.search_subuser_name.setText(moviesList.get(position).getTitle());
        } else if (moviesList.get(position).getSearchType().equals("VIDEO")) {
            holder.search_user_name.setText(moviesList.get(position).getUserName()+" "+moviesList.get(position).getTitle());
            holder.search_subuser_name.setVisibility(View.GONE);
        }

        Glide.with(context)
                .load(moviesList.get(position).getAvatar())
                .placeholder(R.color.adbag)
                .centerCrop()
                .into(holder.search_image);

        holder.rel_tabing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moviesList.get(position).getSearchType().equals("HASHTAG")) {
                    Intent intent = new Intent(context, HashTagDetailActivity.class);
                    intent.putExtra("hashtagid", moviesList.get(position).getId());
                    intent.putExtra("hashtagname", moviesList.get(position).getTitle());
                    context.startActivity(intent);
                } else if (moviesList.get(position).getSearchType().equals("PEOPLE")) {
                    Intent intent1 = new Intent(context, UserProfileActivity.class);
                    intent1.putExtra("userId", moviesList.get(position).getUserId());
                    context.startActivity(intent1);
                } else if (moviesList.get(position).getSearchType().equals("VIDEO")) {
                    Intent intent2 = new Intent(context, OwnVideoDetailActivity.class);
                    intent2.putExtra("newsid", moviesList.get(position).getId());
                    intent2.putExtra("self", "other");
                    context.startActivity(intent2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
