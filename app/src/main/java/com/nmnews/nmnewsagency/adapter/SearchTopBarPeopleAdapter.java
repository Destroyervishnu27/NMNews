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
import com.nmnews.nmnewsagency.activity.UserProfileActivity;
import com.nmnews.nmnewsagency.modelclass.SearchTopSearchModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class SearchTopBarPeopleAdapter extends RecyclerView.Adapter<SearchTopBarPeopleAdapter.MyViewHolder> {
    private List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList;
    private Context context;

    public SearchTopBarPeopleAdapter(Context context, List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView search_user_name,search_subuser_name;
        public ImageView search_image;
        public RelativeLayout rel_tabing;
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
        holder.search_user_name.setText(moviesList.get(position).getUserName());
        holder.search_subuser_name.setText(moviesList.get(position).getTitle());
        Glide.with(context)
                .load(moviesList.get(position).getAvatar())
                .placeholder(R.color.adbag)
                .centerCrop()
                .into(holder.search_image);
        holder.rel_tabing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(context, UserProfileActivity.class);
                intent.putExtra("userId",moviesList.get(position).getUserId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
