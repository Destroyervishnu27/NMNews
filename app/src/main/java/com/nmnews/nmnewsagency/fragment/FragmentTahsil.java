package com.nmnews.nmnewsagency.fragment;

import android.content.Intent;
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
import com.nmnews.nmnewsagency.activity.MainActivity;
import com.nmnews.nmnewsagency.adapter.LocationAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.model.CountryModel;
import com.nmnews.nmnewsagency.modelclass.SetAddressModelClass;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTahsil extends Fragment implements Callback<Object> {
    View view;
    static String state, country;
    static String cityName, stateName, countryName;
    static int cityId;
    RecyclerView recyclerView;
    LocationAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<CountryModel.DataBean> arrayList;
    TextView txt_loc_country, txt_loc_state, txt_loc_city;
    ImageView img_cou, img_sta, img_city, iamge_back_tahsil;
    Rest rest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select_tahsil, container, false);
        rest = new Rest(getActivity(), this);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            countryName = bundle.getString("countryNmae");
            stateName = bundle.getString("stateNmae");
            cityName = bundle.getString("cityNmae");
            cityId = bundle.getInt("cityId");
            Prefrence.setCityIdd(String.valueOf(cityId));
            Prefrence.setCountryName(countryName);
            Prefrence.setStateName(stateName);
            Prefrence.setCityName(cityName);
        }
        iniIT();
        return view;
    }

    private void iniIT() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_loc);
        txt_loc_country = (TextView) view.findViewById(R.id.txt_loc_country);
        txt_loc_state = (TextView) view.findViewById(R.id.txt_loc_state);
        txt_loc_city = (TextView) view.findViewById(R.id.txt_loc_city);
        img_cou = (ImageView) view.findViewById(R.id.img_cou);
        iamge_back_tahsil = (ImageView) view.findViewById(R.id.iamge_back_tahsil);
        img_sta = (ImageView) view.findViewById(R.id.img_sta);
        img_city = (ImageView) view.findViewById(R.id.img_city);
        arrayList = new ArrayList<>();
        inItListData("");
        img_cou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        img_sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        img_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        iamge_back_tahsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        callServiceGetTahsilList();
    }

    private void callServiceGetTahsilList() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getTahsilList(cityId);
    }

    private void callServicesetAddress(String address, String city, String state, String country,
                                       String postalCode, String houseno, String addrs1, double latSend, double lngSend, String tahsil) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.setUserAddress(addrs1, "", "APP", "", Integer.parseInt(Prefrence.getCityIdd()), "",
                Integer.parseInt(Prefrence.getCountryIdd()), Prefrence.getFirstName(),
                city, country, address, state, houseno, "true", "true", Prefrence.getLastName(), latSend, lngSend,
                Integer.parseInt(Prefrence.getStateIdd()), Integer.parseInt(Prefrence.gettahsilIdd()),
                Prefrence.getUserId(), postalCode,tahsil);
    }

    private void inItListData(String city) {
        txt_loc_city.setText(cityName);
        txt_loc_country.setText(countryName);
        txt_loc_state.setText(stateName);

    }

    private void inItItemRecycle() {
        if (arrayList != null && arrayList.size() > 0) {
            locationAdapter = new LocationAdapter(getActivity(), arrayList);

            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(locationAdapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Prefrence.settahsil(arrayList.get(position).getName());
                    Prefrence.settahsilIdd(String.valueOf(arrayList.get(position).getId()));
                    Prefrence.setLogin(true);
                    callServicesetAddress("", Prefrence.getCityName(), Prefrence.getStateName(),
                            Prefrence.getCountryName(), "", "", "", 0.0,
                            0.0,arrayList.get(position).getName());
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));
        } else {
            Toast.makeText(getActivity(), "No Tahsil Available", Toast.LENGTH_SHORT).show();
        }
    }

    private void closefragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(FragmentTahsil.this).commit();
    }

    public void goNextActiviyy() {
        Intent intent = new Intent(getActivity(),
                MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getActivity().startActivity(intent);
        getActivity().finish();
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
            if (obj instanceof SetAddressModelClass) {
                SetAddressModelClass loginModel = (SetAddressModelClass) obj;
                if (loginModel.isStatus()) {
                    Toast.makeText(getActivity(), "Location successfully saved", Toast.LENGTH_SHORT).show();
                   goNextActiviyy();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }
}
