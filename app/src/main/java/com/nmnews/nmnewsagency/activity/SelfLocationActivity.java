package com.nmnews.nmnewsagency.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelfLocationActivity extends AppCompatActivity implements Callback<Object> {
    static String city;
    TextView txt_city;
    TextView txt_subcity;
    Button but_self_no, but_self_yes;
    Rest rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_location);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            city = bundle.getString("city");
        }
        iniIt();
    }

    private void iniIt() {
        txt_city = (TextView) findViewById(R.id.txt_city);
        txt_subcity = (TextView) findViewById(R.id.txt_subcity);
        but_self_yes = (Button) findViewById(R.id.but_self_yes);
        but_self_no = (Button) findViewById(R.id.but_self_no);
        if(city.isEmpty()){
            txt_city.setText(Prefrence.getCityName());
            txt_subcity.setText(Prefrence.getCityName());
        }
        else {
            txt_city.setText(city);
            txt_subcity.setText(city);
        }
        but_self_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelfLocationActivity.this, SelectLocationActivity.class);
                startActivity(i);
            }
        });
        but_self_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefrence.settahsil(city);
                Intent i = new Intent(SelfLocationActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {

    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }
}