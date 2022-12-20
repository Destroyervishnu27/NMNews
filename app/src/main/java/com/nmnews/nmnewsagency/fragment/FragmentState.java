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

public class FragmentState extends Fragment implements Callback<Object> {
    View view;
    RecyclerView recyclerView;
    LocationAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<CountryModel.DataBean> arrayList;
    static int country;
    static String countryName;
    TextView txt_loc_country;
    ImageView img_close_loc,iamge_back_state;
    Rest rest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select_state, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            country = (int) bundle.get("countryId");
            countryName= (String) bundle.get("countryNmae");
            Prefrence.setCountryId(String.valueOf(country));
        }
        iniIt();
        return view;
    }

    private void iniIt() {
        rest=new Rest(getActivity(),this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_loc);
        txt_loc_country = (TextView) view.findViewById(R.id.txt_loc_country);
        img_close_loc = (ImageView) view.findViewById(R.id.img_close_loc);
        iamge_back_state = (ImageView) view.findViewById(R.id.iamge_back_state);
        arrayList = new ArrayList<>();
       // inItListData(country);
       // inItItemRecycle();
        img_close_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        }); iamge_back_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        callServiceCityList();
    }

    private void callServiceCityList() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getStateList(country);
        txt_loc_country.setText(countryName);
    }


    private void inItItemRecycle() {
        if (arrayList != null && arrayList.size()>0) {

        locationAdapter = new LocationAdapter(getActivity(),arrayList);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("stateId", arrayList.get(position).getId());
                bundle.putString("stateNmae", arrayList.get(position).getName());
                bundle.putString("countryNmae",countryName);
                FragmentCity fragment2 = new FragmentCity();
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
            Toast.makeText(getActivity(), "No State Available", Toast.LENGTH_SHORT).show();
        }
    }
    private void closefragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn",String.valueOf(obj));
            if (obj instanceof CountryModel) {
                CountryModel loginModel = (CountryModel) obj;
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
