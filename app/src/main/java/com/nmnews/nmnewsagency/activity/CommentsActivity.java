package com.nmnews.nmnewsagency.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.CommentsAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.GetCommentsModel;
import com.nmnews.nmnewsagency.modelclass.ReportModelClass;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity implements Callback<Object> {
    RecyclerView recyclerView;
    CommentsAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<GetCommentsModel.DataBean> arrayList;
    ImageView iamge_back_msgdetail,img_send;
    EditText edittext_coments;
    Rest rest;
    int newsId;
    ProgressDialog dialog;
    LinearLayout lin_topcomm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_comments);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            newsId = bundle.getInt("newsId");
        }
        rest=new Rest(this,this);
        iniIt();
        callServiceGetComments();
    }

    private void callServiceGetComments() {
        rest.ShowDialogue("Please Wait ..");
        rest.CommentsUser(newsId);
    }

    private void callServiceAddComments(String comments,String parentCommentId) {
       rest.ShowDialogue("Please Wait ..");
        rest.AddCommentsUser(newsId,comments,parentCommentId);
    }
    public void setProgressSet() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait for a sec ..");
        dialog.setProgress(0);
        dialog.setMax(100);
        dialog.show();
        final int totalProgressTime = 95;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(2000);
                        jumpTime += 2;
                        dialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
    private void iniIt() {
        recyclerView = (RecyclerView) findViewById(R.id.recy_comment_detail);
        lin_topcomm =  findViewById(R.id.lin_topcomm);
        iamge_back_msgdetail = (ImageView) findViewById(R.id.iamge_back_comment);
        img_send = (ImageView) findViewById(R.id.img_send);
        edittext_coments = (EditText ) findViewById(R.id.edittext_coments);
        iamge_back_msgdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edittext_coments.getText().toString().equals("")){
                    edittext_coments.setError("Please enter comments");
                    return;
                }else {
                    callServiceAddComments(edittext_coments.getText().toString(), "1");
                }
            }
        });
    }

    private void inItItemRecycle() {
        locationAdapter = new CommentsAdapter(this,arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof GetCommentsModel) {
                GetCommentsModel loginModel = (GetCommentsModel) obj;
                if (loginModel.isStatus()) {

                    arrayList = loginModel.getData();
                    if (arrayList != null) {
                       inItItemRecycle();
                    }
                }
            }
            if (obj instanceof ReportModelClass) {
                ReportModelClass loginModel = (ReportModelClass) obj;
                if (loginModel.isStatus()) {
                    Utils.hideKeyboard(this);
                    Utils.showSnakBarDialog(this,lin_topcomm,"Comments successfully added",R.color.msgresponce);
                    edittext_coments.setText("");
                   callServiceGetComments();
                }
            }
            if (obj instanceof ReportModelClass) {
                ReportModelClass loginModel = (ReportModelClass) obj;
                if (loginModel.isStatus()) {

                    //Toast.makeText(context, "Report successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultReceived(UploadNewsModel.DataBean dataBean) {
        Intent intent = new Intent(CommentsActivity.this, NewVideoActivity.class);
        intent.putExtra("video", dataBean);
        startActivity(intent);
        // finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(EventBus.getDefault().isRegistered(this)){}
        else{
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}