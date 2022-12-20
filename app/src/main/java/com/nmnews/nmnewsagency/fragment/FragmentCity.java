package com.nmnews.nmnewsagency.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.LocationAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.model.CountryModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCity extends Fragment implements Callback<Object> {
    View view;
    static int state;
    static String countryName, stateName;
    static int stateId;
    RecyclerView recyclerView;
    LocationAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<CountryModel.DataBean> arrayList;
    List<String> arrayList1;
    TextView txt_loc_state, txt_loc_country;
    ImageView img_closestate, img_closecount, iamge_back_city;
    Rest rest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select_city, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            stateId = bundle.getInt("stateId");
            stateName = bundle.getString("stateNmae");
            countryName = bundle.getString("countryNmae");
            Prefrence.setStateIdd(String.valueOf(stateId));
        }
        rest = new Rest(getActivity(), this);
        iniIt();
        return view;
    }

    private void iniIt() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_loc);
        txt_loc_state = (TextView) view.findViewById(R.id.txt_loc_state);
        txt_loc_country = (TextView) view.findViewById(R.id.txt_loc_country);
        img_closestate = (ImageView) view.findViewById(R.id.img_closestate);
        img_closecount = (ImageView) view.findViewById(R.id.img_closecount);
        iamge_back_city = (ImageView) view.findViewById(R.id.iamge_back_city);
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        inItListData(state);
        img_closecount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        img_closestate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        iamge_back_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        callServicegetCityList();
    }

    private void callServicegetCityList() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getCityList(stateId);
    }

    private void inItListData(int state) {
        txt_loc_state.setText(stateName);
        txt_loc_country.setText(countryName);
    }

    private void inItItemRecycle() {
        if (arrayList != null&& arrayList.size()>0) {

        locationAdapter = new LocationAdapter(getActivity(), arrayList);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("cityId", arrayList.get(position).getId());
                bundle.putString("cityNmae", arrayList.get(position).getName());
                bundle.putString("countryNmae", countryName);
                bundle.putString("stateNmae", stateName);
                FragmentTahsil fragment2 = new FragmentTahsil();
                fragment2.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction().addToBackStack("")
                        .add(R.id.frame_loc, fragment2)
                        .commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        } else {
            Toast.makeText(getActivity(), "No City Available", Toast.LENGTH_SHORT).show();
        }
    }

    private void sgowFragmentByCon(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("countryNmae", countryName);
        bundle.putString("stateNmae", stateName);
        //  bundle.putString("city", arrayList.get(position)); // Put anything what you want
        FragmentTahsil fragment2 = new FragmentTahsil();
        fragment2.setArguments(bundle);

        getFragmentManager()
                .beginTransaction().addToBackStack("")
                .add(R.id.frame_loc, fragment2)
                .commit();
    }

    private void closefragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof CountryModel) {
                CountryModel loginModel = (CountryModel) obj;
                if (loginModel.isStatus()) {
                    arrayList = loginModel.getData();
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
