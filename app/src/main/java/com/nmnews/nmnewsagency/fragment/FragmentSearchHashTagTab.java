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
import com.nmnews.nmnewsagency.adapter.SearchTopBarHashtagAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.SearchTopSearchModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearchHashTagTab extends Fragment {
    RecyclerView recyclerView;
    SearchTopBarHashtagAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> arrayList;
    View view;

    public static FragmentSearchHashTagTab newInstance() {
        Bundle args = new Bundle();
        FragmentSearchHashTagTab fragment = new FragmentSearchHashTagTab();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.searchhashtagfragment, container, false);
        return view;
    }

    private void inItItemRecycle() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_search_hashtag);
        locationAdapter = new SearchTopBarHashtagAdapter(getActivity(), arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Movie movie = movieList.get(position);

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
        if (isvisible){
            Log.e("Viewpager", "fragment3 is visible ");
            EventBus.getDefault().register(this);
        }else {
            Log.e("Viewpager", "fragment3 is not visible ");
            EventBus.getDefault().unregister(this);
        }
    }
}