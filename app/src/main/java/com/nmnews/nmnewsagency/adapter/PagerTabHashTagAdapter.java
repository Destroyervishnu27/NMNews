package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.athbk.slidingtablayout.SlidingTabAdapter;
import com.athbk.slidingtablayout.model.TabInfo;

import java.util.ArrayList;

public class PagerTabHashTagAdapter extends SlidingTabAdapter {

    private ArrayList<Fragment> listFragment;
    private ArrayList<TabInfo> listTabInfo;
    Context context;


    public PagerTabHashTagAdapter(Context context,FragmentManager fm, ArrayList<Fragment> listFragment, ArrayList<TabInfo> listTabInfo) {
        super(fm);
        this.context=context;
        this.listFragment = listFragment;
        this.listTabInfo = listTabInfo;
    }

    @Override
    protected String getTitle(int position) {
        return listTabInfo.get(position).getTitle();
    }

    @Override
    protected int getIcon(int position) {
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
       // Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}