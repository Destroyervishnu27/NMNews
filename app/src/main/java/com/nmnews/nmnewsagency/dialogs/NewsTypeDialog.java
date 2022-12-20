package com.nmnews.nmnewsagency.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.PrivacyTermsActivity;
import com.nmnews.nmnewsagency.adapter.NewsTypeAdapter;
import com.nmnews.nmnewsagency.interfaces.DownloadClickInterface;
import com.nmnews.nmnewsagency.interfaces.LoginClickInterface;
import com.nmnews.nmnewsagency.model.DownloadVIdeoNewModel;
import com.nmnews.nmnewsagency.modelclass.GetNewsListModel;
import com.nmnews.nmnewsagency.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vishnu Saini .
 * vishnusainideveloper27@gmail.com
 */

public class NewsTypeDialog extends Dialog implements View.OnClickListener, Callback<Object> {

    private Context context;
    private RecyclerView recy_newsTypeList;
    private NewsTypeAdapter newsTypeAdapter;
    private DownloadVIdeoNewModel idDownload;
    private DownloadClickInterface downloadClickInterface;
    private Button btn_startDownload;


    public NewsTypeDialog(Context context, DownloadClickInterface downloadClickInterface, DownloadVIdeoNewModel idDownload) {
        super(context);
        this.context = context;
        this.idDownload = idDownload;
        this.downloadClickInterface = downloadClickInterface;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.color.transprent);
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflate.inflate(R.layout.dialog_news_type, null);
        setContentView(layout);
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.windowAnimations = R.style.dialogue_error;
        wlmp.gravity = Gravity.CENTER;
        wlmp.dimAmount = 0.8f;
        wlmp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        setTitle(null);
        // setCancelable(false);
        setCanceledOnTouchOutside(true);
        btn_startDownload = (Button) layout.findViewById(R.id.btn_startDownload);
        recy_newsTypeList = (RecyclerView) layout.findViewById(R.id.recy_newsTypeList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recy_newsTypeList.setLayoutManager(linearLayoutManager);
        recy_newsTypeList.setHasFixedSize(true);
        newsTypeAdapter=new NewsTypeAdapter(context,idDownload.getData().getNewsContentType());
        recy_newsTypeList.setAdapter(newsTypeAdapter);

        btn_startDownload.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_startDownload:
                downloadClickInterface.onClickDownload(idDownload);
                dismiss();
                break;

            default:
                break;
        }

    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {

    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {

    }
}

