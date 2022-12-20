package com.nmnews.nmnewsagency.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.Performance_DistricAdapter;
import com.nmnews.nmnewsagency.adapter.SectionHeaderPerformnce;
import com.nmnews.nmnewsagency.model.ChildPerformance;
import com.nmnews.nmnewsagency.modelclass.PerformceDistrctModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Performce_DistrictFragment extends Fragment implements Callback<Object> {

    View view;
    RecyclerView recyclerView;
    Performance_DistricAdapter adapterRecycler;
    List<SectionHeaderPerformnce> sectionHeaders;
    List<ChildPerformance> childListCity, childListState;
    List<PerformceDistrctModel.DataBean.CityNewsBean> performnceCityList;
    List<PerformceDistrctModel.DataBean.StateNewsBean> performnceStateList;
    Rest rest;
    TextView txt_percount,txt_owncity;

    public static Performce_DistrictFragment newInstance() {
        Bundle args = new Bundle();
        Performce_DistrictFragment fragment = new Performce_DistrictFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_performce__district, container, false);
        rest = new Rest(getActivity(), this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_perf_district);
        txt_percount =view.findViewById(R.id.txt_percount);
        txt_owncity =view.findViewById(R.id.txt_owncity);
        childListCity = new ArrayList<>();
        childListState = new ArrayList<>();
        callServicegetPerforSelf();
        return view;
    }

    private void callServicegetPerforSelf() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getPerformnceDistrct();
    }

    private void inItRecycle() {
        //setLayout Manager
        txt_owncity.setText(Prefrence.getCityName());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        for (int i = 0; i < performnceCityList.size(); i++) {
            childListCity.add(new ChildPerformance(i+1+". "+performnceCityList.get(i).getTitle(),
                    performnceCityList.get(i).getNewsId()));
           // childListState.add(new ChildPerformance(performnceCityList.get(i).getStateNews().ge)t(i).getTitle()));
            Log.e("cityperf====",""+performnceCityList.get(i).getTitle());
        }
        for (int i = 0; i < performnceStateList.size(); i++) {
          //  childListCity.add(new ChildPerformance(performnceCityList.get(i).getTitle();
           childListState.add(new ChildPerformance(i+1+". "+performnceStateList.get(i).getTitle(),
                   performnceStateList.get(i).getNewsId()));
            Log.e("stateperf====",""+performnceStateList.get(i).getTitle());
        }
        //Create a List of SectionHeader DataModel implements SectionHeader
        sectionHeaders = new ArrayList<>();
        sectionHeaders.add(new SectionHeaderPerformnce(childListCity, "Your City Top Stories", 6));

        sectionHeaders.add(new SectionHeaderPerformnce(childListState, "Your State Top Stories", 2));

        adapterRecycler = new Performance_DistricAdapter(getActivity(), sectionHeaders);
        recyclerView.setAdapter(adapterRecycler);
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof PerformceDistrctModel) {
                PerformceDistrctModel loginModel = (PerformceDistrctModel) obj;
                if (loginModel.isStatus()) {
                    txt_percount.setText(""+loginModel.getData().getNEWSCNT());
                    performnceCityList = loginModel.getData().getCityNews();
                    performnceStateList = loginModel.getData().getStateNews();
                    if (performnceCityList.size() > 0 || performnceStateList.size()>0) {
                        inItRecycle();
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