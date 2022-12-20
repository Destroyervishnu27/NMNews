package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.OwnVideoDetailActivity;
import com.nmnews.nmnewsagency.activity.RejectedVideoActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {
    // private List<LocationModel> moviesList;
    private List<String> moviesList;
    private List<Integer> imgeList;
    Context context;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public ProfileAdapter(Context activity, List<String> moviesList, List<Integer> imAGE) {
        this.moviesList = moviesList;
        this.imgeList = imAGE;
        this.context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img_profile_profileimage;
        public Button but_rejected_profile;
        public FrameLayout frame_profile;

        public MyViewHolder(View view) {
            super(view);
            // title = (TextView) view.findViewById(R.id.hashtag_name);
            img_profile_profileimage = (ImageView) view.findViewById(R.id.img_profile_profileimage);
            but_rejected_profile = (Button) view.findViewById(R.id.but_rejected_profile);
            frame_profile = (FrameLayout) view.findViewById(R.id.frame_profile);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profilel, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*LocationModel movie = moviesList.get(position);
        holder.title.setText(movie.getmName());*/
        //  holder.title.setText(moviesList.get(position));
        holder.frame_profile.setTag(position);
        if (position == 1) {
            holder.but_rejected_profile.setVisibility(View.VISIBLE);
        } else {
            holder.but_rejected_profile.setVisibility(View.GONE);
        }
        Glide.with(context)
                .load(imgeList.get(position))
                .placeholder(R.color.adbag)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(holder.img_profile_profileimage);
        holder.but_rejected_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, RejectedVideoActivity.class);
                context.startActivity(intent);
            }
        });
        holder.frame_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.frame_profile.getTag().equals(1)){
                    Intent intent=new Intent(context, RejectedVideoActivity.class);
                    context.startActivity(intent);
                }else{
                    Intent intent1=new Intent(context, OwnVideoDetailActivity.class);
                    context.startActivity(intent1);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return imgeList.size();
    }
}
