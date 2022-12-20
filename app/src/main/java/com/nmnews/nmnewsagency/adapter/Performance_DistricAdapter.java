package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.OwnVideoDetailActivity;
import com.nmnews.nmnewsagency.holder.ChildViewPerformanceHolder;
import com.nmnews.nmnewsagency.holder.SectionViewPerformnceHolder;
import com.nmnews.nmnewsagency.model.ChildPerformance;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

/**
 * Created by apple on 11/7/16.
 */

public class Performance_DistricAdapter extends SectionRecyclerViewAdapter<SectionHeaderPerformnce, ChildPerformance, SectionViewPerformnceHolder, ChildViewPerformanceHolder> {

    Context context;

    public Performance_DistricAdapter(Context context, List<SectionHeaderPerformnce> sectionHeaderItemList) {
        super(context, sectionHeaderItemList);
        this.context = context;
    }

    @Override
    public SectionViewPerformnceHolder onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_perfodistr_item, sectionViewGroup, false);
        return new SectionViewPerformnceHolder(view);
    }

    @Override
    public ChildViewPerformanceHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_perfor_district, childViewGroup, false);
        return new ChildViewPerformanceHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(SectionViewPerformnceHolder sectionViewPerformnceHolder, int i, SectionHeaderPerformnce sectionHeaderPerformnce) {
        sectionViewPerformnceHolder.name.setText(sectionHeaderPerformnce.sectionText);
    }

   /* @Override
    public void onBindSectionViewHolder(SectionViewPerformnceHolder sectionViewHolder, int sectionPosition, SectionHeader sectionHeader) {
        sectionViewHolder.name.setText(sectionHeader.sectionText);
    }*/

    @Override
    public void onBindChildViewHolder(ChildViewPerformanceHolder childViewPerformanceHolder, int sectionPosition, int childPosition,
                                      ChildPerformance childPerformance) {
        childViewPerformanceHolder.name.setText(childPerformance.getName());
        childViewPerformanceHolder.name.setTag(childPosition);
        childViewPerformanceHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) childViewPerformanceHolder.name.getTag();
                Intent intent4 = new Intent(context, OwnVideoDetailActivity.class);
                intent4.putExtra("newsid",childPerformance.getNewsId());
                intent4.putExtra("self","other");
                context.startActivity(intent4);
            }
        });
    }

   /* @Override
    public void onBindChildViewHolder(ChildViewPerformanceHolder childViewHolder, int sectionPosition, int childPosition, Child child) {
       childViewHolder.name.setText(child.getName());
    }*/
}
