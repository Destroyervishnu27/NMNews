package com.nmnews.nmnewsagency.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.CovrageQualityAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class CovrageQualityActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView iamge_back_covrage;
    CovrageQualityAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<String> arrayList;
    List<Integer> imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_covrage_quality);
        recyclerView = (RecyclerView) findViewById(R.id.recy_covragequality);
        iamge_back_covrage = (ImageView) findViewById(R.id.iamge_back_covrage);
        arrayList = new ArrayList<>();
        imgList = new ArrayList<>();
        inItItemRecycle();
        iamge_back_covrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inItItemRecycle() {
        arrayList.add("");
        arrayList.add("");

        locationAdapter = new CovrageQualityAdapter(this,arrayList,imgList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
}