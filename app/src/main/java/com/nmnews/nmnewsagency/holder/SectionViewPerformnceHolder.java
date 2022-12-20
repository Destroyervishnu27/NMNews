package com.nmnews.nmnewsagency.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;

/**
 * Created by shanky on 11/12/2016.
 */

public class SectionViewPerformnceHolder extends RecyclerView.ViewHolder {

   public TextView name;
    public SectionViewPerformnceHolder(View itemView) {
        super(itemView);
         name = (TextView) itemView.findViewById(R.id.sectionHeader);
    }
}
