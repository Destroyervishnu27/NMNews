package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.modelclass.NewVideoHashtagModelClass;

import java.util.List;

public class AutoCompleteSearchBarAdapter extends RecyclerView.Adapter<AutoCompleteSearchBarAdapter.MyViewHolder> {
    // private List<LocationModel> moviesList;
    private List<NewVideoHashtagModelClass.DataBean.PagedRecordBean> moviesList;
    public Context context;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public AutoCompleteSearchBarAdapter(Context context, List<NewVideoHashtagModelClass.DataBean.PagedRecordBean> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image_profile_hashtag;

        public MyViewHolder(View view) {
            super(view);
             title = (TextView) view.findViewById(R.id.user_name);
            image_profile_hashtag = (ImageView) view.findViewById(R.id.image_profile_hashtag);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_peoplebare, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewVideoHashtagModelClass.DataBean.PagedRecordBean movie = moviesList.get(position);
         holder.title.setText(movie.getTitle());
        /*Glide.with(context)
                .load(imgList.get(position))
                .placeholder(R.color.adbag)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(holder.image_profile_hashtag);*/
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
