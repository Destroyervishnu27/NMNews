package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.UserProfileActivity;
import com.nmnews.nmnewsagency.modelclass.AddNewsModel;
import com.nmnews.nmnewsagency.modelclass.GetFollowingModel;
import com.nmnews.nmnewsagency.rest.Rest;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.MyViewHolder> implements Callback<Object> {
    private List<GetFollowingModel.DataBean> moviesList;
    Context context;
    int folow;
    Rest rest;

    public FollowingAdapter(List<GetFollowingModel.DataBean> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_folwingusername,txt_folwingname,txt_folowing;
        public CircleImageView img_folow;
        public LinearLayout lin_folower;
        RelativeLayout rel_folowing_but;

        public MyViewHolder(View view) {
            super(view);
            img_folow = (CircleImageView) view.findViewById(R.id.img_folow);
            lin_folower = (LinearLayout) view.findViewById(R.id.lin_folower);
            txt_folwingname = (TextView) view.findViewById(R.id.txt_folwingname);
            txt_folwingusername = (TextView) view.findViewById(R.id.txt_folwingusername);
            txt_folowing = (TextView) view.findViewById(R.id.txt_folowing);
            rel_folowing_but = (RelativeLayout) view.findViewById(R.id.rel_folowing_but);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_following, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        rest=new Rest(context,this);
        GetFollowingModel.DataBean movie = moviesList.get(position);
        holder.txt_folwingusername.setText(movie.getUserName());
        holder.txt_folwingname.setText(movie.getFullName());
        holder.rel_folowing_but.setTag(position);
        holder.lin_folower.setTag(position);
        if (movie.isIsFollowing()) {
            holder.txt_folowing.setText("UNFOLLOW");
        } else {
            holder.txt_folowing.setText("FOLLOW");
        }
        Glide.with(context)
                .load(movie.getAvatar())
                .into(holder.img_folow);
        holder.lin_folower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                Intent intent=new Intent(context, UserProfileActivity.class);
                intent.putExtra("userId",moviesList.get(pos).getUserId());
                context.startActivity(intent);
            }
        });
        holder.rel_folowing_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                folow = (int) v.getTag();
                if( holder.txt_folowing.getText().equals("FOLLOW")){
                    holder.txt_folowing.setText("UNFOLLOW");
                }
                else{
                    holder.txt_folowing.setText("FOLLOW");
                }
                callServicegetFollows(String.valueOf(moviesList.get(folow).getUserId()), moviesList.get(folow).isIsFollowing());
            }
        });
    }
    private void callServicegetFollows(String folow, boolean isfollow) {
        rest.ShowDialogue(context.getResources().getString(R.string.pleaseWait));
        if (isfollow) {
            rest.UNfollowUser(folow);
        } else {
            rest.likeUser(folow);
        }
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof AddNewsModel) {
                AddNewsModel loginModel = (AddNewsModel) obj;
                if (loginModel.isStatus()) {
                    if (moviesList.get(folow).isIsFollowing()) {
                        moviesList.get(folow).setIsFollowing(false);
                    } else {
                        moviesList.get(folow).setIsFollowing(true);
                    }
                    // notifyDataSetChanged();
                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }

}
