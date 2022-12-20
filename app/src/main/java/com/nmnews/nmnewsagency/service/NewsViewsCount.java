package com.nmnews.nmnewsagency.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nmnews.nmnewsagency.modelclass.NewsCountModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewsCount extends Service implements Callback<Object> {
    Rest rest;

    @Override
    public void onCreate() {
        super.onCreate();
        rest=new Rest(this,this);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        callServicenewsCount();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void callServicenewsCount() {
     //   rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.setNewsCount(Integer.parseInt(Prefrence.getnewsId()));
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof NewsCountModel) {
                NewsCountModel searchTopSearchModel = (NewsCountModel) obj;
                if (searchTopSearchModel.isStatus()) {
                   // EventBus.getDefault().post(searchTopSearchModel.getMessage());
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }
}
