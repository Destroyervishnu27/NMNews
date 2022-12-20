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
import com.nmnews.nmnewsagency.modelclass.GetFollowersModel;
import com.nmnews.nmnewsagency.rest.Rest;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.MyViewHolder> implements Callback<Object> {
    private List<GetFollowersModel.DataBean> moviesList;
    Context context;
    boolean isFolow;
    Rest rest;
    int folow;


    public FollowersAdapter(List<GetFollowersModel.DataBean> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_folowname, txt_folowusername,hashtag_name;
        public CircleImageView img_folow;
        public LinearLayout lin_folower;
        RelativeLayout rel_folow_but;

        public MyViewHolder(View view) {
            super(view);
          //  Toast.makeText(context, "MyViewHolder", Toast.LENGTH_SHORT).show();
            // title = (TextView) view.findViewById(R.id.hashtag_name);
            img_folow = (CircleImageView) view.findViewById(R.id.img_folow);
            lin_folower = (LinearLayout) view.findViewById(R.id.lin_folower);
            rel_folow_but = (RelativeLayout) view.findViewById(R.id.rel_folow);
            txt_folowname = (TextView) view.findViewById(R.id.txt_folowname);
            txt_folowusername = (TextView) view.findViewById(R.id.txt_folowusername);
            hashtag_name = (TextView) view.findViewById(R.id.hashtag_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Toast.makeText(context, "onCreateViewHolder", Toast.LENGTH_SHORT).show();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_followers, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       // Toast.makeText(context, "onBindViewHolder", Toast.LENGTH_SHORT).show();
        rest = new Rest(context, this);
        GetFollowersModel.DataBean movie = moviesList.get(position);
        holder.txt_folowname.setText(movie.getFullName());
        holder.txt_folowusername.setText(movie.getUserName());
        holder.rel_folow_but.setTag(position);
        holder.lin_folower.setTag(position);
        if (movie.isIsFollowing()) {
            holder.hashtag_name.setText("UNFOLLOW");
        } else {
            holder.hashtag_name.setText("FOLLOW");
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
        holder.rel_folow_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 folow = (int) v.getTag();
                if( holder.hashtag_name.getText().equals("FOLLOW")){
                    holder.hashtag_name.setText("UNFOLLOW");
                }
                else{
                    holder.hashtag_name.setText("FOLLOW");
                }
                callServicegetFollows(String.valueOf(moviesList.get(folow).getUserId()), moviesList.get(folow).isIsFollowing());
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
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
