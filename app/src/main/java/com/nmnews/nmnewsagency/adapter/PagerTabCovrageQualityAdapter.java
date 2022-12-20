package com.nmnews.nmnewsagency.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.athbk.slidingtablayout.SlidingTabAdapter;
import com.athbk.slidingtablayout.model.TabInfo;

import java.util.ArrayList;

public class PagerTabCovrageQualityAdapter extends SlidingTabAdapter {

    private ArrayList<Fragment> listFragment;
    private ArrayList<TabInfo> listTabInfo;


    public PagerTabCovrageQualityAdapter(FragmentManager fm, ArrayList<Fragment> listFragment, ArrayList<TabInfo> listTabInfo) {
        super(fm);
        this.listFragment = listFragment;
        this.listTabInfo = listTabInfo;
    }

    @Override
    protected String getTitle(int position) {
        return listTabInfo.get(position).getTitle();
    }

    @Override
    protected int getIcon(int position) {
        /*if (position == 0) return R.drawable.tab_1_selected;
        else if (position == 1) return R.drawable.tab_2_selected;
//        else if (position == 2) return R.drawable.tab_3_selected;
        else return R.drawable.tab_4_selected;*/
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}