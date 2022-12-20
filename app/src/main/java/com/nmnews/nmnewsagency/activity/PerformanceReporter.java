package com.nmnews.nmnewsagency.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.PagerTabCovrageQualityAdapter;
import com.nmnews.nmnewsagency.fragment.Performance_SelfFragment;
import com.nmnews.nmnewsagency.fragment.Performce_DistrictFragment;
import com.athbk.slidingtablayout.TabLayout;
import com.athbk.slidingtablayout.model.TabInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PerformanceReporter extends AppCompatActivity {
    private ViewPager viewPager;
   // private TabLayout tabLayout;
   private TabLayout tabLayout;
   ImageView iamge_back_performnce;
   TextView txt_perdatetime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_performance_reporter);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        txt_perdatetime = (TextView) findViewById(R.id.txt_perdatetime);
        iamge_back_performnce = (ImageView) findViewById(R.id.iamge_back_performnce);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ArrayList<TabInfo> listTab = new ArrayList<>();
        TabInfo tabInfo1 = new TabInfo("Self");
        TabInfo tabInfo2 = new TabInfo("District");

        listTab.add(tabInfo1);
        listTab.add(tabInfo2);

        ArrayList<Fragment> listFragment = new ArrayList<>();
        listFragment.add(Performance_SelfFragment.newInstance());
        listFragment.add(Performce_DistrictFragment.newInstance());

        PagerTabCovrageQualityAdapter adapter = new PagerTabCovrageQualityAdapter(getSupportFragmentManager(), listFragment, listTab);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager, adapter);
        iamge_back_performnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        txt_perdatetime.setText(currentDateandTime);
    }
}