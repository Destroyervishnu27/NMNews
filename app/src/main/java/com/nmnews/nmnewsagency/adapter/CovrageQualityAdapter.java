package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

public class CovrageQualityAdapter extends RecyclerView.Adapter<CovrageQualityAdapter.MyViewHolder> {
   // private List<LocationModel> moviesList;
    private List<String> moviesList;
    private List<Integer> imgList;
    private Context context;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public CovrageQualityAdapter(Context context,List<String> moviesList,List<Integer> imgList) {
        this.moviesList = moviesList;
        this.imgList = imgList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img_cov_qu;

        public MyViewHolder(View view) {
            super(view);
          // title = (TextView) view.findViewById(R.id.hashtag_name);
           img_cov_qu = (ImageView) view.findViewById(R.id.img_cov_qu);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_covrage_quality, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*LocationModel movie = moviesList.get(position);
        holder.title.setText(movie.getmName());*/
      //  holder.title.setText(moviesList.get(position));
        Glide.with(context)
                .load(imgList.get(position))
                .placeholder(R.color.adbag)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(holder.img_cov_qu);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }
}
