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
import com.nmnews.nmnewsagency.adapter.LikesListAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.model.LikesListModel;
import com.nmnews.nmnewsagency.modelclass.GetFollowersModel;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikesActivity extends AppCompatActivity implements
        Callback<Object> {
    RecyclerView recyclerView;
    LikesListAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<LikesListModel.DataDTOX.DataDTO> arrayList;
    ImageView iamge_back_folowers;
    Rest rest;
    int newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            newsId = bundle.getInt("newsId");
        }
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
                LikesActivity.this.finish();
            }
        });
    }

    private void callserviceFollowers() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getNewLikeUserList(newsId);
    }
    private void inItItemRecycle() {
        locationAdapter = new LikesListAdapter(arrayList,LikesActivity.this);

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
            if (obj instanceof LikesListModel) {
                LikesListModel likesListModel = (LikesListModel) obj;
                if (likesListModel.getStatus()) {
                    arrayList=likesListModel.getData().getData();
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