package com.nmnews.nmnewsagency.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.model.CityModel;
import com.nmnews.nmnewsagency.model.CountryModel;
import com.nmnews.nmnewsagency.model.StateModel;
import com.nmnews.nmnewsagency.model.TahsilModel;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseLocationActivity extends AppCompatActivity implements Callback<Object> {
    Spinner spi_country, spi_state, spi_city, spi_tahsil;
    Rest rest;
    ArrayList<String> clountryList,stateList,cityList,tahsilList;
    ArrayList<Integer> clountryListId,stateListId,cityListId,tahsilListId;
    List<CountryModel.DataBean> arrayList;
    List<StateModel.DataBean> arrayListstate;
    List<CityModel.DataBean> arrayListcity;
    List<TahsilModel.DataBean> arrayListtahsil;
    int check = 0;
    boolean frstcountry=false;
    ImageView iamge_back_chose;
    Button but_chose_loc;
    String country,state,city,tahsil;
    int countryid,stateid,cityid,tahsilid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);
        rest = new Rest(this, this);
        inIt();
    }

    private void inIt() {
        but_chose_loc = findViewById(R.id.but_chose_loc);
        spi_country = findViewById(R.id.spi_country);
        iamge_back_chose = findViewById(R.id.iamge_back_chose);
        spi_city = findViewById(R.id.spi_city);
        spi_state = findViewById(R.id.spi_state);
        spi_tahsil = findViewById(R.id.spi_tahsil);
        clountryList = new ArrayList<>();
        stateList = new ArrayList<>();
        cityList = new ArrayList<>();
        tahsilList = new ArrayList<>();
        clountryListId = new ArrayList<>();
        stateListId = new ArrayList<>();
        cityListId = new ArrayList<>();
        tahsilListId = new ArrayList<>();

        iamge_back_chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        but_chose_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("country",country);
                returnIntent.putExtra("state",state);
                returnIntent.putExtra("city",city);
                returnIntent.putExtra("tahsil",tahsil);
                returnIntent.putExtra("countryid",countryid);
                returnIntent.putExtra("stateid",stateid);
                returnIntent.putExtra("cityid",cityid);
                returnIntent.putExtra("tahsilid",tahsilid);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        SpinnerInteractionListener listener = new SpinnerInteractionListener();
        spi_country.setOnTouchListener(listener);
        spi_country.setOnItemSelectedListener(listener);

        SpinnerInteractionListener1 listener1 = new SpinnerInteractionListener1();
        spi_state.setOnTouchListener(listener1);
        spi_state.setOnItemSelectedListener(listener1);

        SpinnerInteractionListener2 listener2 = new SpinnerInteractionListener2();
        spi_city.setOnTouchListener(listener2);
        spi_city.setOnItemSelectedListener(listener2);

        SpinnerInteractionListener3 listener3 = new SpinnerInteractionListener3();
        spi_tahsil.setOnTouchListener(listener3);
        spi_tahsil.setOnItemSelectedListener(listener3);

        callServiceGetCountry();
    }

    private void callServiceGetCountry() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getCountryList();
    }

    private void callServiceStateList(int pos) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        countryid=clountryListId.get(pos);
        rest.getStateList1(clountryListId.get(pos));
    }
    private void callServiceCiyList(int pos) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        stateid= stateListId.get(pos);
        rest.getCityList1(stateListId.get(pos));
    }
    private void callServiceTahsilList(int pos) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        cityid=cityListId.get(pos);
        rest.getTahsilList1(cityListId.get(pos));
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
                    setDataOnSpinner();
                }
            }
            if (obj instanceof StateModel) {
                StateModel loginModel = (StateModel) obj;
                if (loginModel.isStatus()) {
                    arrayListstate = loginModel.getData();
                    setDataOnSpinnerState();
                }
            }if (obj instanceof CityModel) {
                CityModel loginModel = (CityModel) obj;
                if (loginModel.isStatus()) {
                    arrayListcity = loginModel.getData();
                    setDataOnSpinnerCity();
                }
            }if (obj instanceof TahsilModel) {
                TahsilModel loginModel = (TahsilModel) obj;
                if (loginModel.isStatus()) {
                    arrayListtahsil = loginModel.getData();
                    setDataOnSpinnerTahsil();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }

    public void setDataOnSpinner() {

        for (int i = 0; i < arrayList.size(); i++) {
            clountryList.add(arrayList.get(i).getName());
            clountryListId.add(arrayList.get(i).getId());
        }
        clountryList.add(0,"Choose your country");
        clountryListId.add(0,0);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(ChooseLocationActivity.this,
                android.R.layout.simple_list_item_1, clountryList);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi_country.setAdapter(myadapter);
        spi_country.setSelection(0);
    }
    public void setDataOnSpinnerState() {

        stateList.clear();
        stateListId.clear();
        for (int i = 0; i < arrayListstate.size(); i++) {
            stateList.add(arrayListstate.get(i).getName());
            stateListId.add(arrayListstate.get(i).getId());
        }
        stateList.add(0,"Choose your state");
        stateListId.add(0,0);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(ChooseLocationActivity.this,
                android.R.layout.simple_list_item_1, stateList);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi_state.setAdapter(myadapter);
    }
    public void setDataOnSpinnerCity() {
       //
        cityList.clear();
        cityListId.clear();
        for (int i = 0; i < arrayListcity.size(); i++) {
            cityList.add(arrayListcity.get(i).getName());
            cityListId.add(arrayListcity.get(i).getId());
        }
        stateList.add(0,"Choose your city");
        stateListId.add(0,0);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(ChooseLocationActivity.this,
                android.R.layout.simple_list_item_1, cityList);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi_city.setAdapter(myadapter);
    }
    public void setDataOnSpinnerTahsil() {
        tahsilList.clear();
        tahsilListId.clear();
        for (int i = 0; i < arrayListtahsil.size(); i++) {
            tahsilList.add(arrayListtahsil.get(i).getName());
            tahsilListId.add(arrayListtahsil.get(i).getId());
        }
        tahsilList.add(0,"Choose your tahsil");
        tahsilListId.add(0,0);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(ChooseLocationActivity.this,
                android.R.layout.simple_list_item_1, tahsilList);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi_tahsil.setAdapter(myadapter);
    }

    public class SpinnerInteractionListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

        boolean userSelect = false;

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (userSelect) {
                country= ""+parent.getItemAtPosition(pos);
                ;
                // Your selection handling code here
              //  Toast.makeText(ChooseLocationActivity.this, "country"+pos, Toast.LENGTH_SHORT).show();
                callServiceStateList(pos);
                userSelect = false;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            userSelect = true;
            return false;
        }
    }public class SpinnerInteractionListener1 implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

        boolean userSelect = false;

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (userSelect) {
                state= ""+parent.getItemAtPosition(pos);
                // Your selection handling code here
              //  Toast.makeText(ChooseLocationActivity.this, "state"+pos, Toast.LENGTH_SHORT).show();
                callServiceCiyList(pos);
                userSelect = false;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            userSelect = true;
            return false;
        }
    }
    public class SpinnerInteractionListener2 implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

        boolean userSelect = false;

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (userSelect) {
                city= ""+parent.getItemAtPosition(pos);
                // Your selection handling code here
              //  Toast.makeText(ChooseLocationActivity.this, "city"+pos, Toast.LENGTH_SHORT).show();
                callServiceTahsilList(pos);
                userSelect = false;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            userSelect = true;
            return false;
        }
    }
    public class SpinnerInteractionListener3 implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

        boolean userSelect = false;

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (userSelect) {
                tahsil= ""+parent.getItemAtPosition(pos);
                tahsilid=tahsilListId.get(pos);
                but_chose_loc.setVisibility(View.VISIBLE);
                // Your selection handling code here
              //  Toast.makeText(ChooseLocationActivity.this, "tahsil"+pos, Toast.LENGTH_SHORT).show();
               // callServiceTahsilList(pos);
                userSelect = false;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            userSelect = true;
            return false;
        }
    }
}