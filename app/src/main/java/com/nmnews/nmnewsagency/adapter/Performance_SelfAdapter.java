package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.modelclass.Performence_SelfModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class Performance_SelfAdapter extends RecyclerView.Adapter<Performance_SelfAdapter.MyViewHolder> {
   // private List<LocationModel> moviesList;
    private List<Performence_SelfModel.DataBean> moviesList;
   // private List<Integer> imgList;
    Context context;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public Performance_SelfAdapter(Context context, List<Performence_SelfModel.DataBean> moviesList) {
        this.moviesList = moviesList;
    //  this.imgList = imgList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name,txt_username,txt_count,txt_score;
        public ImageView img_perf_self;

        public MyViewHolder(View view) {
            super(view);
           txt_name = (TextView) view.findViewById(R.id.txt_name);
            txt_username = (TextView) view.findViewById(R.id.txt_username);
            img_perf_self = (ImageView) view.findViewById(R.id.img_perf_self);
            txt_count = (TextView) view.findViewById(R.id.txt_count);
            txt_score = (TextView) view.findViewById(R.id.txt_score);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_permce_self, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*LocationModel movie = moviesList.get(position);
        holder.title.setText(movie.getmName());*/
        holder.txt_name.setText(moviesList.get(position).getFullName());
        holder.txt_username.setText(moviesList.get(position).getUserName());
        holder.txt_count.setText(""+moviesList.get(position).getNewsCount());
        holder.txt_score.setText(""+moviesList.get(position).getScore());
        Glide.with(context)
                .load(moviesList.get(position).getAvatar())
                .placeholder(R.color.adbag)
                .centerCrop()
               // .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(holder.img_perf_self);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
