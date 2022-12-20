package com.nmnews.nmnewsagency.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.LocationAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.model.CountryModel;
import com.nmnews.nmnewsagency.rest.Rest;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCountry extends Fragment implements Callback<Object> {
    View view;
    RecyclerView recyclerView;
    LocationAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<CountryModel.DataBean> arrayList;
    ImageView iamge_back_country;
    EditText search_edit;
    String apiKey = "AIzaSyAlVPWqpH2XbSPRBAuxYmlCougC0k5-stA";
    LinearLayout lin_recycled;
    FrameLayout frame_search;
    Rest rest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_select_location, container, false);
        iniIt();

        return view;
    }

    private void iniIt() {
        rest = new Rest(getActivity(), this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_loc);
        iamge_back_country = (ImageView) view.findViewById(R.id.iamge_back_country);
        search_edit = (EditText) view.findViewById(R.id.search_edit);
        frame_search = (FrameLayout) view.findViewById(R.id.frame_search);
        lin_recycled = (LinearLayout) view.findViewById(R.id.lin_recycled);
      //  arrayList = new ArrayList<>();
        callSefrvice();
        //inItListData();
        // inItItemRecycle();
        iamge_back_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        search_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniLizedSearch();
                // frame_search.setVisibility(View.VISIBLE);
                // lin_recycled.setVisibility(View.GONE);
            }
        });
    }

    private void callSefrvice() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getCountryList();
    }

    private void inItItemRecycle() {
        if (arrayList != null) {

        locationAdapter = new LocationAdapter(getActivity(), arrayList);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("countryId",arrayList.get(position).getId());
                bundle.putString("countryNmae",arrayList.get(position).getName());
                FragmentState fragment2 = new FragmentState();
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
            Toast.makeText(getActivity(), "No Country Available", Toast.LENGTH_SHORT).show();
        }
    }

    private void closefragment() {
        getActivity().finish();
    }


    public void iniLizedSearch() {
        if (!Places.isInitialized()) {
            Places.initialize(getActivity().getApplicationContext(), apiKey);
        }
        PlacesClient placesClient = Places.createClient(getActivity());

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("searchcountry", "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("searchcountry error", "An error occurred: " + status);
            }
        });
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

