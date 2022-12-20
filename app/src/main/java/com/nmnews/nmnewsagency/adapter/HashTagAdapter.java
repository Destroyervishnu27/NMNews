package com.nmnews.nmnewsagency.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.HashTagDetailActivity;
import com.nmnews.nmnewsagency.modelclass.SearchTopSearchModel;

import java.util.List;

public class HashTagAdapter extends RecyclerView.Adapter<HashTagAdapter.MyViewHolder> {
    private List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList;
    Context context;

    public HashTagAdapter(Context context,List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList) {
        this.moviesList = moviesList;
        this.context=context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        LinearLayout lin_hashtaghhh;

        public MyViewHolder(View view) {
            super(view);
           title = (TextView) view.findViewById(R.id.hashtag_name);
            lin_hashtaghhh = (LinearLayout) view.findViewById(R.id.lin_hashtaghhh);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hashtag, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(moviesList.get(position).getTitle());
        holder.lin_hashtaghhh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, HashTagDetailActivity.class);
                intent.putExtra("hashtagid",moviesList.get(position).getId());
                intent.putExtra("hashtagname",moviesList.get(position).getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
