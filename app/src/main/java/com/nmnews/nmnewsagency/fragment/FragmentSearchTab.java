package com.nmnews.nmnewsagency.fragment;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.nmnews.nmnewsagency.R;
import com.athbk.slidingtablayout.TabLayout;
import com.athbk.slidingtablayout.model.TabInfo;

import java.util.ArrayList;

public class FragmentSearchTab extends Fragment {
    View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_tab, container, false);
        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        tabLayout = view. findViewById(R.id.tabLayout);
        ArrayList<TabInfo> listTab = new ArrayList<>();
        TabInfo tabInfo1 = new TabInfo("Top");
        TabInfo tabInfo2 = new TabInfo("People");
        TabInfo tabInfo3 = new TabInfo("Hashtag");

        listTab.add(tabInfo1);
        listTab.add(tabInfo2);
        listTab.add(tabInfo3);

        ArrayList<Fragment> listFragment = new ArrayList<>();
        listFragment.add(FragmentSearchTopTab.newInstance());
        listFragment.add(FragmentSearchTopTab.newInstance());
        listFragment.add(FragmentSearchTopTab.newInstance());

        /*PagerTabHashTagAdapter adapter = new PagerTabHashTagAdapter(getActivity().getSupportFragmentManager(), listFragment, listTab);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager, adapter);  */
        return view;
    }


}