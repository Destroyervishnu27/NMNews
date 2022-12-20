package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nmnews.nmnewsagency.fragment.FragmentSearchTopTab;

public class PagerTabAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    Context context;

    //Constructor to the class
    public PagerTabAdapter(Context context,FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.context=context;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        Fragment  fragment=null;
        switch (position) {
            case 0:
                fragment = new FragmentSearchTopTab();
                Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
            case 1:
              //  fragment = new FragmentSearchPeopleTab();
                fragment = new FragmentSearchTopTab();
                Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
            case 2:
              //  fragment = new FragmentSearchHashTagTab();
                fragment = new FragmentSearchTopTab();
              //  Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
            default:
                return fragment;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
