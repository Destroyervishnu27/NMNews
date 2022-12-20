package com.nmnews.nmnewsagency.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.HashTagDetailAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class UserOwnDegtailProfileActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    HashTagDetailAdapter locationAdapter;
    //  List<LocationModel> arrayList;
    List<String> arrayList;
    List<Integer> imAGE;
    BottomSheetDialog bottomSheetDialog;
    ImageView iamge_back_userdetail, img_repo_userprofile, img_contact, img_video_video, img_video;
    LinearLayout lin_folowing_userprofile, lin_folowers_userprofile, lin_ownvideo, lin_owncontact, lin_ownvideo_video;
    TextView txt_nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_user_own_detail_profile);
        recyclerView = (RecyclerView) findViewById(R.id.recy_user_detail);
        lin_folowers_userprofile = (LinearLayout) findViewById(R.id.lin_folowers_userprofile);
        lin_folowing_userprofile = (LinearLayout) findViewById(R.id.lin_folowing_userprofile);
        lin_ownvideo_video = (LinearLayout) findViewById(R.id.lin_ownvideo_video);
        lin_owncontact = (LinearLayout) findViewById(R.id.lin_owncontact);
        lin_ownvideo = (LinearLayout) findViewById(R.id.lin_ownvideo);
        lin_folowers_userprofile.setOnClickListener(this);
        lin_folowing_userprofile.setOnClickListener(this);
        lin_owncontact.setOnClickListener(this);
        lin_ownvideo_video.setOnClickListener(this);
        lin_ownvideo.setOnClickListener(this);
        iamge_back_userdetail = (ImageView) findViewById(R.id.iamge_back_userdetail);
        txt_nodata = (TextView) findViewById(R.id.txt_nodata);
        img_repo_userprofile = (ImageView) findViewById(R.id.img_repo_userprofile);
        img_contact = (ImageView) findViewById(R.id.img_contact);
        img_video_video = (ImageView) findViewById(R.id.img_video_video);
        img_video = (ImageView) findViewById(R.id.img_video);
        iamge_back_userdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserOwnDegtailProfileActivity.this.finish();
            }
        });
        img_repo_userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogBox();
            }
        });
        inItItemRecycle();
    }

    private void inItItemRecycle() {
        arrayList = new ArrayList<>();
        imAGE = new ArrayList<>();
        arrayList.add("");
        arrayList.add("");
        imAGE = new ArrayList<>();

       // locationAdapter = new HashTagDetailAdapter(UserOwnDegtailProfileActivity.this, arrayList, imAGE);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(locationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Movie movie = movieList.get(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void openDialogBox() {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_userprofile);
        LinearLayout lin_dia_cancel = bottomSheetDialog.findViewById(R.id.lin_dia_cancel);
        LinearLayout lin_dia_report = bottomSheetDialog.findViewById(R.id.lin_dia_report);
        lin_dia_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogBoxReport();
                bottomSheetDialog.cancel();
            }
        });
        lin_dia_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.cancel();
            }
        });
        bottomSheetDialog.show();
    }

    private void openDialogBoxReport() {
        Dialog dialog = new Dialog(UserOwnDegtailProfileActivity.this);
        dialog.setContentView(R.layout.dialog_report);
        ImageView img_close_dialog = dialog.findViewById(R.id.img_close_dialog);
        img_close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button but_submit = dialog.findViewById(R.id.but_submit);
        but_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_folowing_userprofile:
                Intent intent = new Intent(UserOwnDegtailProfileActivity.this, FollowingActivituy.class);
                startActivity(intent);
                break;
            case R.id.lin_folowers_userprofile:
                Intent intent1 = new Intent(UserOwnDegtailProfileActivity.this, FollowersActivituy.class);
                startActivity(intent1);
                break;
            case R.id.lin_owncontact:
                txt_nodata.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                changebagroundimagebycondition2(img_video, img_contact, img_video_video);
                break;
            case R.id.lin_ownvideo_video:
                txt_nodata.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                changebagroundimagebycondition1(img_video, img_contact, img_video_video);
                break;
            case R.id.lin_ownvideo:
                txt_nodata.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                changebagroundimagebycondition(img_video, img_contact, img_video_video);
                break;
        }
    }

    public void changebagroundimagebycondition(ImageView view1, ImageView view2, ImageView view3) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view1.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserOwnDegtailProfileActivity.this, R.drawable.ic_group_7830));
            view2.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserOwnDegtailProfileActivity.this, R.drawable.ic_group_7829));
            view3.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserOwnDegtailProfileActivity.this, R.drawable.ic_video));
        } else {
            view1.setImageResource
                    (R.drawable.ic_group_7830);
            view2.setImageResource
                    (R.drawable.ic_group_7829);
            view3.setImageResource
                    (R.drawable.ic_video);
        }

    }

    public void changebagroundimagebycondition1(ImageView view1, ImageView view2, ImageView view3) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view1.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserOwnDegtailProfileActivity.this, R.drawable.ic_group_78301));
            view2.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserOwnDegtailProfileActivity.this, R.drawable.ic_group_7829));
            view3.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserOwnDegtailProfileActivity.this, R.drawable.ic_video11));
        } else {
            view1.setImageResource
                    (R.drawable.ic_group_78301);
            view2.setImageResource
                    (R.drawable.ic_group_7829);
            view3.setImageResource
                    (R.drawable.ic_video11);
        }

    }

    public void changebagroundimagebycondition2(ImageView view1, ImageView view2, ImageView view3) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view1.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserOwnDegtailProfileActivity.this, R.drawable.ic_group_78301));
            view2.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserOwnDegtailProfileActivity.this, R.drawable.ic_group_78291));
            view3.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserOwnDegtailProfileActivity.this, R.drawable.ic_video));
        } else {
            view1.setImageResource
                    (R.drawable.ic_group_78301);
            view2.setImageResource
                    (R.drawable.ic_group_78291);
            view3.setImageResource
                    (R.drawable.ic_video);
        }

    }
}