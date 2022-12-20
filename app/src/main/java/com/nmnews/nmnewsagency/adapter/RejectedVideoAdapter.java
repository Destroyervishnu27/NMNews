package com.nmnews.nmnewsagency.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;

import java.util.List;

public class RejectedVideoAdapter extends RecyclerView.Adapter<RejectedVideoAdapter.MyViewHolder> {
    // private List<LocationModel> moviesList;
    private List<String> moviesList;
    Context context;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public RejectedVideoAdapter(Context context, List<String> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img_close_reject;
        public Button but_rejected;
        public LinearLayout lin_report,lin_chat_reject;
        public FrameLayout frame_rejected_close,frame_rejected_open;

        public MyViewHolder(View view) {
            super(view);
            img_close_reject = (ImageView) view.findViewById(R.id.img_close_reject);
            but_rejected = (Button) view.findViewById(R.id.but_rejected);
            lin_report = (LinearLayout) view.findViewById(R.id.lin_report);
            lin_chat_reject = (LinearLayout) view.findViewById(R.id.lin_chat_reject);
            frame_rejected_close = (FrameLayout) view.findViewById(R.id.frame_rejected_close);
            frame_rejected_open = (FrameLayout) view.findViewById(R.id.frame_rejected_open);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rejected_video, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*LocationModel movie = moviesList.get(position);
        holder.title.setText(movie.getmName());*/
        // holder.title.setText(moviesList.get(position));
        holder.but_rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.frame_rejected_open.setVisibility(View.VISIBLE);
                holder.frame_rejected_close.setVisibility(View.GONE);
            }
        });
        holder.img_close_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.frame_rejected_open.setVisibility(View.GONE);
                holder.frame_rejected_close.setVisibility(View.VISIBLE);
            }
        });
        holder.lin_chat_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent(context, MessageDetailActivity.class);
               // context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    private void openDialogBox() {
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_report);
        dialog.show();
    }

    }

