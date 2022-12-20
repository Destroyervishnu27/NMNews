package com.nmnews.nmnewsagency.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;

/**
 * Created by apple on 11/7/16.
 */

public class ChildViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_user_namenoti;
    public ImageView image_profile_noti;
    public RelativeLayout rel_notichild;

    public ChildViewHolder(View itemView) {
        super(itemView);
        txt_user_namenoti = (TextView) itemView.findViewById(R.id.txt_user_namenoti);
        image_profile_noti = (ImageView) itemView.findViewById(R.id.image_profile_noti);
        rel_notichild = (RelativeLayout) itemView.findViewById(R.id.rel_notichild);
    }
}
