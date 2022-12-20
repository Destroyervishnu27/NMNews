package com.nmnews.nmnewsagency.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.OwnVideoDetailActivity;
import com.nmnews.nmnewsagency.activity.UserProfileActivity;
import com.nmnews.nmnewsagency.modelclass.AddNewsModel;
import com.nmnews.nmnewsagency.modelclass.SearchTopSearchModel;
import com.nmnews.nmnewsagency.rest.Rest;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> implements
         Callback<Object> {
    private List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList;
    Context context;
    Rest rest;
    int folow;

    public UsersAdapter(Context context, List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList) {
        this.moviesList = moviesList;
        this.context=context;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user_name_user,user_folower;
        public ImageView image_search_profile;
        Button rel_folow_user;
        LinearLayout lin_userpp;

        public MyViewHolder(View view) {
            super(view);
            image_search_profile = (ImageView) view.findViewById(R.id.image_search_profile);
            user_name_user = (TextView) view.findViewById(R.id.user_name_user);
            user_folower = (TextView) view.findViewById(R.id.user_folower);
            rel_folow_user = (Button) view.findViewById(R.id.rel_folow_user);
            lin_userpp = (LinearLayout) view.findViewById(R.id.lin_userpp);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usears, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        rest=new Rest(context,this);
        holder.user_name_user.setText(moviesList.get(position).getUserName());
        holder.user_folower.setText(moviesList.get(position).getFollowersSuffix()+" Followers");
        if(!moviesList.get(position).isIsFollowed()){
            holder.rel_folow_user.setBackgroundResource(R.drawable.hashtagdetailfollow);
            holder.rel_folow_user.setTextColor(Color.parseColor("#FFFFFF"));
            holder.rel_folow_user.setText("FOLLOW");
        }else{
            holder.rel_folow_user.setBackgroundResource(R.drawable.userdetail_button);
            holder.rel_folow_user.setTextColor(Color.parseColor("#333333"));
            holder.rel_folow_user.setText("FOLLOWING");
        }
        Glide.with(context)
                .load(moviesList.get(position).getAvatar())
                .placeholder(R.color.adbag)
                .centerCrop()
                /*.transition(DrawableTransitionOptions.withCrossFade(500))*/
                .into(holder.image_search_profile);
        holder.lin_userpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UserProfileActivity.class);
                intent.putExtra("userId",moviesList.get(position).getUserId());
                context.startActivity(intent);
            }
        });
        holder.rel_folow_user.setTag(position);
        holder.rel_folow_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 folow= (int) v.getTag();
                if (moviesList.get(position).isIsFollowed()) {
                    //delete
                    holder.rel_folow_user.setBackgroundResource(R.drawable.hashtagdetailfollow);
                    holder.rel_folow_user.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.rel_folow_user.setText("FOLLOW");
                    callServicegetFollows(moviesList.get(folow).getUserId(), true);
                } else {
                    holder.rel_folow_user.setBackgroundResource(R.drawable.userdetail_button);
                    holder.rel_folow_user.setTextColor(Color.parseColor("#333333"));
                    holder.rel_folow_user.setText("FOLLOWING");


                    callServicegetFollows(moviesList.get(folow).getUserId(), false);
                }
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
                if (moviesList.get(folow).isIsFollowed()) {
                    moviesList.get(folow).setIsFollowed(false);
                } else {
                    moviesList.get(folow).setIsFollowed(true);

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
