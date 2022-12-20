package com.nmnews.nmnewsagency.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.SearchTopBarPeopleAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.SearchTopSearchModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearchPeopleTab extends Fragment {
    RecyclerView recycle_search_people;
    View view;
    SearchTopBarPeopleAdapter locationAdapter;
    List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> arrayList;

    public static FragmentSearchPeopleTab newInstance() {
        Bundle args = new Bundle();
        FragmentSearchPeopleTab fragment = new FragmentSearchPeopleTab();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.searchpeoplefragment, container, false);
        return view;
    }

    private void inItItemRecycle() {
        recycle_search_people = (RecyclerView) view.findViewById(R.id.recycle_search_people);
        locationAdapter = new SearchTopBarPeopleAdapter(getActivity(), arrayList);

        recycle_search_people.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_search_people.setItemAnimator(new DefaultItemAnimator());
        recycle_search_people.setAdapter(locationAdapter);

        recycle_search_people.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recycle_search_people,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        // Intent intent = new Intent(getActivity(), HashTagDetailActivity.class);
                        //startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultReceived( ArrayList<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> arrayList) {
        Log.e("Result hash",arrayList.toString());
        this.arrayList=arrayList;
        inItItemRecycle();
    }
    @Override
    public void setMenuVisibility(boolean isvisible) {
        super.setMenuVisibility(isvisible);
        if (isvisible) {
            Log.e("Viewpager", "fragment2 is visible ");
            EventBus.getDefault().register(this);
        } else {
            Log.e("Viewpager", "fragment2 is not visible ");
            EventBus.getDefault().unregister(this);
        }
    }
}