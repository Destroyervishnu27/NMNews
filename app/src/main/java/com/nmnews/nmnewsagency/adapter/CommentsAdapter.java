package com.nmnews.nmnewsagency.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.modelclass.DeleteCommentsModel;
import com.nmnews.nmnewsagency.modelclass.GetCommentsModel;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> implements Callback<Object> {
    // private List<LocationModel> moviesList;
    private List<GetCommentsModel.DataBean> moviesList;
    Context context;
    Rest rest;
    int pos;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public CommentsAdapter(Context context, List<GetCommentsModel.DataBean> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_comm_datetime, txt_comm_username, txt_comm_msg;
        CircleImageView img_coments;
        ImageView  img_deletecoments;

        public MyViewHolder(View view) {
            super(view);

            txt_comm_msg = (TextView) view.findViewById(R.id.txt_comm_msg);
            txt_comm_datetime = (TextView) view.findViewById(R.id.txt_comm_datetime);
            txt_comm_username = (TextView) view.findViewById(R.id.txt_comm_username);
            img_coments = (CircleImageView) view.findViewById(R.id.img_coments);
            img_deletecoments = (ImageView) view.findViewById(R.id.img_deletecoments);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coments_detail, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        rest=new Rest(context,this);
        GetCommentsModel.DataBean movie = moviesList.get(position);
        holder.txt_comm_username.setText(movie.getFirstName());
        holder.txt_comm_msg.setText(movie.getComment());
        holder.txt_comm_datetime.setText(Utils.parseDateToddMMyyyy(movie.getAddedOn()));
        holder.txt_comm_datetime.setSelected(true);
        holder.txt_comm_username.setSelected(true);
        if(movie.isCanDelete()){
            holder.img_deletecoments.setVisibility(View.VISIBLE);
        }else{
            holder.img_deletecoments.setVisibility(View.GONE);
        }
        holder.img_deletecoments.setTag(position);
        holder.img_deletecoments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 pos = (int) v.getTag();
               callServiceGetComments( moviesList.get(pos).getId());

            }
        });
        new Handler().post(new Runnable() {
            public void run() {
                Glide.with(context)
                        .load(moviesList.get(position).getAvatar())
                        .into(holder.img_coments);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
    private void callServiceGetComments(int comId) {
        rest.ShowDialogue(context.getResources().getString(R.string.pleaseWait));
        rest.deleteComments(comId);
    }
    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof DeleteCommentsModel) {
                DeleteCommentsModel loginModel = (DeleteCommentsModel) obj;
                if (loginModel.isStatus()) {
                    Toast.makeText(context, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
                    moviesList.remove(pos);
                    notifyDataSetChanged();
                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }
}
