package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.UserProfileActivity;
import com.nmnews.nmnewsagency.model.DownloadVIdeoNewModel;
import com.nmnews.nmnewsagency.modelclass.AddNewsModel;
import com.nmnews.nmnewsagency.modelclass.GetFollowersModel;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsTypeAdapter extends RecyclerView.Adapter<NewsTypeAdapter.MyViewHolder>  {
    List<DownloadVIdeoNewModel.DataDTO.NewsContentTypeDTO> moviesList;
    Context context;



    public NewsTypeAdapter(Context context, List<DownloadVIdeoNewModel.DataDTO.NewsContentTypeDTO>moviesList ) {
        this.context = context;
        this.moviesList = moviesList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox check_newsType;

        public MyViewHolder(View view) {
            super(view);

            check_newsType = (CheckBox) view.findViewById(R.id.check_newsType);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Toast.makeText(context, "onCreateViewHolder", Toast.LENGTH_SHORT).show();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_type, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       holder.check_newsType.setText(moviesList.get(position).getText());
       if (moviesList.get(position).getActive())
       {
           holder.check_newsType.setChecked(true);
       }else {
           holder.check_newsType.setChecked(false);
       }

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }




}
