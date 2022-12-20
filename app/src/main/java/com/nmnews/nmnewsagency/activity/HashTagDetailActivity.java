package com.nmnews.nmnewsagency.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.HashTagDetailAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.AddNewsModel;
import com.nmnews.nmnewsagency.modelclass.HashTagDetailModel;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HashTagDetailActivity extends AppCompatActivity implements Callback<Object> {
    RecyclerView recyclerView;
    ImageView iamge_back_hashtagdeatil;
    TextView txt_totalnews, hashtagh_title;
    Button rel_folow;
    HashTagDetailAdapter locationAdapter;
    List<HashTagDetailModel.DataBeanX.DataBean.PagedRecordBean> arrayList;
    Rest rest;
    int hshId;
    String hashtagName;
    boolean isFoloow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.search_hashtag_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hshId = (int) bundle.get("hashtagid");
            hashtagName = (String) bundle.get("hashtagname");
        }
        rest=new Rest(this,this);
        iniT();
        iamge_back_hashtagdeatil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashTagDetailActivity.this.finish();
            }
        });
        rel_folow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFoloow) {
                    //delete
                    callServicegetFollows(String.valueOf(hshId), true);
                } else {
                    callServicegetFollows(String.valueOf(hshId), false);
                }
            }
        });
        callServicegetHashTag();
    }
    private void callServicegetFollows(String folow, boolean isfollow) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        if (isfollow) {
            rest.UNfollowUser(folow);
        } else {
            rest.likeUser(folow);
        }
    }
    private void iniT() {
        recyclerView = (RecyclerView) findViewById(R.id.recy_hashtag_detail);
        iamge_back_hashtagdeatil = (ImageView) findViewById(R.id.iamge_back_hashtagdeatil);
        hashtagh_title = (TextView) findViewById(R.id.hashtagh_title);
        txt_totalnews = (TextView) findViewById(R.id.txt_totalnews);
        rel_folow = (Button) findViewById(R.id.rel_folow);
        hashtagh_title.setText(hashtagName);
//        if (!aspNetUser.isIsFollowed()) {
//            isFoloow = false;
//            but_folow.setBackgroundResource(R.drawable.hashtagdetailfollow);
//            but_folow.setTextColor(Color.parseColor("#FFFFFF"));
//            but_folow.setText("FOLLOW");
//
//        } else {
//            but_folow.setBackgroundResource(R.drawable.userdetail_button);
//            but_folow.setTextColor(Color.parseColor("#333333"));
//            but_folow.setText("FOLLOWING");
//            isFoloow = true;
//        }
    }

    private void callServicegetHashTag() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getHashTagById(hshId);
    }

    private void inItItemRecycle() {
        if (arrayList.size() > 0) {
            txt_totalnews.setText(String.valueOf(arrayList.size()));
        }
        locationAdapter = new HashTagDetailAdapter(HashTagDetailActivity.this, arrayList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Movie movie = movieList.get(position);
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
            if (obj instanceof HashTagDetailModel) {
                HashTagDetailModel searchTopSearchModel = (HashTagDetailModel) obj;
                if (searchTopSearchModel.getData().isStatus()) {
                    arrayList = searchTopSearchModel.getData().getData().getPagedRecord();
                    inItItemRecycle();

                }
            }
            if (obj instanceof AddNewsModel) {
                AddNewsModel loginModel = (AddNewsModel) obj;
                if (loginModel.isStatus()) {
                    if (isFoloow) {
                        isFoloow = false;
                        rel_folow.setBackgroundResource(R.drawable.hashtagdetailfollow);
                        rel_folow.setTextColor(Color.parseColor("#FFFFFF"));
                        rel_folow.setText("FOLLOW");

                    } else {
                        rel_folow.setBackgroundResource(R.drawable.userdetail_button);
                        rel_folow.setTextColor(Color.parseColor("#333333"));
                        rel_folow.setText("FOLLOWING");
                        isFoloow = true;
                    }
                    // notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }
}