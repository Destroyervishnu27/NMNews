package com.nmnews.nmnewsagency.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.RejectedVideoAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class RejectedVideoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RejectedVideoAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_rejected_video);
        recyclerView = (RecyclerView) findViewById(R.id.recy_rejctdvidwo);
        arrayList = new ArrayList<>();
        inItItemRecycle();
    }

    private void inItItemRecycle() {
        arrayList.add("");
        locationAdapter = new RejectedVideoAdapter(this, arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Movie movie = movieList.get(position);
                //  Toast.makeText(getActivity(), arrayList.get(position) + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}