package com.nmnews.nmnewsagency.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;

import java.util.List;

public class MessageDetailAdapter extends RecyclerView.Adapter<MessageDetailAdapter.MyViewHolder> {
   // private List<LocationModel> moviesList;
    private List<String> moviesList;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public MessageDetailAdapter(List<String> moviesList) {
        this.moviesList = moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
          // title = (TextView) view.findViewById(R.id.hashtag_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mesgae_detail, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*LocationModel movie = moviesList.get(position);
        holder.title.setText(movie.getmName());*/
      //  holder.title.setText(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
