package com.nmnews.nmnewsagency.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.FollowersAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.GetFollowersModel;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersActivituy extends AppCompatActivity implements
        Callback<Object> {
    RecyclerView recyclerView;
    FollowersAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<GetFollowersModel.DataBean> arrayList;
    ImageView iamge_back_folowers;
    Rest rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_followers_activituy);
        rest=new Rest(this,this);
        inIt();
        callserviceFollowers();
    }

    private void inIt() {
        recyclerView = (RecyclerView) findViewById(R.id.recy_folowers);
        iamge_back_folowers = (ImageView) findViewById(R.id.iamge_back_folowers);
        iamge_back_folowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FollowersActivituy.this.finish();
            }
        });
    }

    private void callserviceFollowers() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getFOloowers();
    }
    private void inItItemRecycle() {
        locationAdapter = new FollowersAdapter(arrayList,FollowersActivituy.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

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
            if (obj instanceof GetFollowersModel) {
                GetFollowersModel loginModel = (GetFollowersModel) obj;
                if (loginModel.isStatus()) {
                    arrayList=loginModel.getData();
                    inItItemRecycle();
                }
            }


        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }
}