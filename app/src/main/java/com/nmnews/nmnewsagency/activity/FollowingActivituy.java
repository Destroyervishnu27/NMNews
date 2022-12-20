package com.nmnews.nmnewsagency.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.FollowingAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.GetFollowingModel;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingActivituy extends AppCompatActivity implements
        Callback<Object> {
    RecyclerView recyclerView;
    FollowingAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<GetFollowingModel.DataBean> arrayList;
    ImageView iamge_back_folowing;
    Rest rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_following_activituy);
        rest=new Rest(this,this);
        iniIT();
        callserviceFollowing();
    }

    private void callserviceFollowing() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getFOloowing();
    }

    private void iniIT() {
        recyclerView = (RecyclerView) findViewById(R.id.recy_folowing);
        iamge_back_folowing = (ImageView) findViewById(R.id.iamge_back_folowing);
        iamge_back_folowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FollowingActivituy.this.finish();
            }
        });
    }

    private void inItItemRecycle() {

        locationAdapter = new FollowingAdapter(arrayList,FollowingActivituy.this);
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
            if (obj instanceof GetFollowingModel) {
                GetFollowingModel loginModel = (GetFollowingModel) obj;
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