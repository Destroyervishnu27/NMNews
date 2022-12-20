package com.nmnews.nmnewsagency.fragment;

import android.content.Intent;
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
import com.nmnews.nmnewsagency.activity.UserProfileActivity;
import com.nmnews.nmnewsagency.adapter.Performance_SelfAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.Performence_SelfModel;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Performance_SelfFragment extends Fragment implements Callback<Object> {
    View view;
    RecyclerView recyclerView;
    Performance_SelfAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<Performence_SelfModel.DataBean> arrayList;
    List<Integer> imgList;
    Rest rest;

    public static Performance_SelfFragment newInstance() {
        Bundle args = new Bundle();
        Performance_SelfFragment fragment = new Performance_SelfFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_performance__self, container, false);
        rest=new Rest(getActivity(),this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_performance);
      //  arrayList = new ArrayList<>();
        imgList = new ArrayList<>();
callServicegetPerforSelf();
        return view;
    }
    private void callServicegetPerforSelf() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getPerformnceSelf();
    }
    private void inItItemRecycle() {

        locationAdapter = new Performance_SelfAdapter(getActivity(), arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                intent.putExtra("userId",arrayList.get(position).getUserId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof Performence_SelfModel) {
                Performence_SelfModel loginModel = (Performence_SelfModel) obj;
                if (loginModel.isStatus()) {
                  arrayList=loginModel.getData();
                  if(arrayList.size()>0){
                      inItItemRecycle();
                  }
                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }
}