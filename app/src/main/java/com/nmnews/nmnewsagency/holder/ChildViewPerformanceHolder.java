package com.nmnews.nmnewsagency.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;

/**
 * Created by apple on 11/7/16.
 */

public class ChildViewPerformanceHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public ImageView image_profile_noti;

    public ChildViewPerformanceHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.text_num_district);
       // image_profile_noti = (ImageView) itemView.findViewById(R.id.image_profile_noti);
    }
}
