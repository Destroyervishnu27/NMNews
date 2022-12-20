package com.nmnews.nmnewsagency.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.GetLOtherUserOwnNewsAdapter;
import com.nmnews.nmnewsagency.adapter.GetOtherUserHashNewsAdapter;
import com.nmnews.nmnewsagency.chat.ChatHelper;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.AddNewsModel;
import com.nmnews.nmnewsagency.modelclass.GetProfileDataModel;
import com.nmnews.nmnewsagency.modelclass.GetUserHashTagModel;
import com.nmnews.nmnewsagency.modelclass.GetUserOwnNewsModel;
import com.nmnews.nmnewsagency.modelclass.ReportUserModel;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.pref.SharedPrefsHelper;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.Utils;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBDialogCustomData;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.model.QBUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity implements
        View.OnClickListener, Callback<Object> {
    RecyclerView recyclerView;
    GetLOtherUserOwnNewsAdapter locationAdapter;
    GetOtherUserHashNewsAdapter locationAdapter2;
    //  List<LocationModel> arrayList;
    List<GetUserOwnNewsModel.DataBeanX.DataBean.PagedRecordBean> arrayList;
    List<GetUserHashTagModel.DataBeanX.DataBean.PagedRecordBean> arrayListHash;
    List<Integer> imAGE;
    BottomSheetDialog bottomSheetDialog;
    ImageView iamge_back_userdetail, img_repo_userprofile, img_contact, img_video_video, img_video, img_userprofile;
    LinearLayout lin_folowing_userprofile, lin_folowers_userprofile, lin_ownvideo, lin_owncontact, lin_ownvideo_video,lin_topuser;
    TextView txt_nodata, txt_userFolowing, txt_userfolow, txt_userUname, txt_userFname, txt_userhashvideo, txt_userownvideo;
    Rest rest;
    String id;
    Button but_folow, but_msg_user;
    GetProfileDataModel.DataBean.AspNetUserBean dataBean;
    boolean isFoloow;
    private static final int UNAUTHORIZED = 401;
    ProgressDialog pd;
    QBUser currentUser;
    private static final int REQUEST_DIALOG_ID_FOR_UPDATE = 165;
    private ImageView img_userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_user_profile);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("userId");
        }
        rest = new Rest(this, this);
        if (!ChatHelper.getInstance().isLogged()) {
            reloginToChat();
        }
        if (ChatHelper.getCurrentUser() != null) {
            currentUser = ChatHelper.getCurrentUser();
            Log.e("currentuser======", currentUser.toString());
        } else {
            // Log.e(TAG, "Finishing " + TAG + ". Current user is null");
            // finish();
        }
        iniT();
        //inItItemRecycle();
    }

    private void iniT() {
        recyclerView = (RecyclerView) findViewById(R.id.recy_user_detail);
        but_folow = (Button) findViewById(R.id.but_folow);
        but_msg_user = (Button) findViewById(R.id.but_msg_user);
        lin_folowers_userprofile = (LinearLayout) findViewById(R.id.lin_folowers_userprofile);
        lin_folowing_userprofile = (LinearLayout) findViewById(R.id.lin_folowing_userprofile);
        lin_ownvideo_video = (LinearLayout) findViewById(R.id.lin_ownvideo_video);
        lin_topuser = (LinearLayout) findViewById(R.id.lin_topuser);
        lin_owncontact = (LinearLayout) findViewById(R.id.lin_owncontact);
        lin_ownvideo = (LinearLayout) findViewById(R.id.lin_ownvideo);
        lin_folowers_userprofile.setOnClickListener(this);
        lin_folowing_userprofile.setOnClickListener(this);
        lin_owncontact.setOnClickListener(this);
        lin_ownvideo_video.setOnClickListener(this);
        lin_ownvideo.setOnClickListener(this);
        iamge_back_userdetail = (ImageView) findViewById(R.id.iamge_back_userdetail);
        img_userprofile = (ImageView) findViewById(R.id.img_userprofile);
        txt_nodata = (TextView) findViewById(R.id.txt_nodata);
        img_userType = (ImageView) findViewById(R.id.img_userType);

        txt_userFname = (TextView) findViewById(R.id.txt_userFname);
        txt_userownvideo = (TextView) findViewById(R.id.txt_userownvideo);
        txt_userhashvideo = (TextView) findViewById(R.id.txt_userhashvideo);
        txt_userUname = (TextView) findViewById(R.id.txt_userUname);
        txt_userfolow = (TextView) findViewById(R.id.txt_userfolow);
        txt_userFolowing = (TextView) findViewById(R.id.txt_userFolowing);
        img_repo_userprofile = (ImageView) findViewById(R.id.img_repo_userprofile);
        img_contact = (ImageView) findViewById(R.id.img_contact);
        img_video_video = (ImageView) findViewById(R.id.img_video_video);
        img_video = (ImageView) findViewById(R.id.img_video);
        iamge_back_userdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileActivity.this.finish();
            }
        });
        img_repo_userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogBox();
            }
        });
        but_folow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFoloow) {
                    //delete
                    callServicegetFollows(dataBean.getId(), true);
                } else {
                    callServicegetFollows(dataBean.getId(), false);
                }

            }
        });
        but_msg_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //prepareUser(dataBean.getUserName(),dataBean.getFullName(),dataBean.getAvatar());
                // goingToChatActivity( new QBUser() );
                Toast.makeText(UserProfileActivity.this,"Comming soon..",Toast.LENGTH_LONG).show();
               // nextACtivityzzforChat();
            }
        });
        callServicegetProfiel();
    }

    private void callServicegetProfiel() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getFrontProfileList(id);
    }

    private void callServicegetUserSaveVideo() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getuserHashtagVideo(id);
    }

    private void callServicegetFollows(String folow, boolean isfollow) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        if (isfollow) {
            rest.UNfollowUser(folow);
        } else {
            rest.likeUser(folow);
        }
    }

    private void callServicegetReport(String folow, String isfollow, String newid) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.reportAUser(folow, isfollow, newid);
    }

    private void callServicegetUserOwnNews() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getuserOwnVideo_userprofile(id);
    }

    private void inItItemRecycle(String type) {
        if (type.equals("save")) {
            locationAdapter = new GetLOtherUserOwnNewsAdapter(UserProfileActivity.this, arrayList);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
            recyclerView.setAdapter(locationAdapter);
        } else if (type.equals("hash")) {
            locationAdapter2 = new GetOtherUserHashNewsAdapter(UserProfileActivity.this, arrayListHash);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
            recyclerView.setAdapter(locationAdapter2);
        }


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                /*if (type.equals("save")) {
                    Intent intent = new Intent(UserProfileActivity.this, OwnVideoDetailActivity.class);
                    intent.putExtra("newsid", arrayListHash.get(position).getNewsId());
                    intent.putExtra("self", "other");
                    startActivity(intent);
                }
                else if (type.equals("hash")) {
                    Intent intent = new Intent(UserProfileActivity.this, OwnVideoDetailActivity.class);
                    intent.putExtra("newsid", arrayList.get(position).getNewsId());
                    intent.putExtra("self", "other");
                    startActivity(intent);
                }*/
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
        Dialog dialog = new Dialog(UserProfileActivity.this);
        dialog.setContentView(R.layout.dialog_report);
        ImageView img_close_dialog = dialog.findViewById(R.id.img_close_dialog);
        TextView txt_report_subtitle = dialog.findViewById(R.id.txt_report_subtitle);
        RelativeLayout rel_dialogtop = dialog.findViewById(R.id.rel_dialogtop);
        RadioGroup radio_group = dialog.findViewById(R.id.radio_group);
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
                RadioButton selectedRadioButton = null;
                if (radio_group.getCheckedRadioButtonId() == -1) {
                   // Toast.makeText(UserProfileActivity.this, "Please select report reason", Toast.LENGTH_SHORT).show();
                    Utils.showSnakBarDialog(UserProfileActivity.this,rel_dialogtop,
                            "Please select report reason ",R.color.alert);
                    return;
                } else {
                    // get selected radio button from radioGroup
                    int selectedId = radio_group.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    selectedRadioButton = (RadioButton) dialog.findViewById(selectedId);
                    // Toast.makeText(context, selectedRadioButton.getText().toString() + " is selected", Toast.LENGTH_SHORT).show();
                }
                if (txt_report_subtitle.getText().toString().equals("")) {
                    txt_report_subtitle.setError("Please enter sub reason!");
                    return;
                }
                if (!txt_report_subtitle.getText().toString().equals("") &&
                        !selectedRadioButton.getText().toString().equals("")) {
                    //delete
                    callServicegetReport(txt_report_subtitle.getText().toString(),
                            selectedRadioButton.getText().toString(), id);
                }
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
                /*Intent intent = new Intent(UserProfileActivity.this, FollowingActivituy.class);
                startActivity(intent);*/
                break;
            case R.id.lin_folowers_userprofile:
               /* Intent intent1 = new Intent(UserProfileActivity.this, FollowersActivituy.class);
                startActivity(intent1);*/
                break;
            case R.id.lin_owncontact:
                // txt_nodata.setVisibility(View.VISIBLE);
                //recyclerView.setVisibility(View.GONE);
                changebagroundimagebycondition2(img_video, img_contact, img_video_video);
                callServicegetUserSaveVideo();
                break;
            case R.id.lin_ownvideo_video:
                // txt_nodata.setVisibility(View.VISIBLE);
                // recyclerView.setVisibility(View.GONE);
                changebagroundimagebycondition1(img_video, img_contact, img_video_video);
                break;
            case R.id.lin_ownvideo:
                //  txt_nodata.setVisibility(View.GONE);
                //  recyclerView.setVisibility(View.VISIBLE);
                changebagroundimagebycondition(img_video, img_contact, img_video_video);
                callServicegetUserOwnNews();
                break;
        }
    }

    public void changebagroundimagebycondition(ImageView view1, ImageView view2, ImageView view3) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view1.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserProfileActivity.this, R.drawable.ic_group_7830));
            view2.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserProfileActivity.this, R.drawable.ic_group_7829));
            view3.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserProfileActivity.this, R.drawable.ic_video));
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
                            (UserProfileActivity.this, R.drawable.ic_group_78301));
            view2.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserProfileActivity.this, R.drawable.ic_group_7829));
            view3.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserProfileActivity.this, R.drawable.ic_video11));
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
                            (UserProfileActivity.this, R.drawable.ic_group_78301));
            view2.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserProfileActivity.this, R.drawable.ic_group_78291));
            view3.setBackgroundDrawable
                    (ContextCompat.getDrawable
                            (UserProfileActivity.this, R.drawable.ic_video));
        } else {
            view1.setImageResource
                    (R.drawable.ic_group_78301);
            view2.setImageResource
                    (R.drawable.ic_group_78291);
            view3.setImageResource
                    (R.drawable.ic_video);
        }

    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof GetProfileDataModel) {
                GetProfileDataModel loginModel = (GetProfileDataModel) obj;
                if (loginModel.isStatus()) {
                    this.dataBean = loginModel.getData().getAspNetUser();
                    setDTaontextView(loginModel.getData().getAspNetUser());
                    isFoloow = loginModel.getData().getAspNetUser().isIsFollowed();
                }
            }
            if (obj instanceof AddNewsModel) {
                AddNewsModel loginModel = (AddNewsModel) obj;
                if (loginModel.isStatus()) {

                    if (isFoloow) {
                        isFoloow = false;
                        but_folow.setBackgroundResource(R.drawable.hashtagdetailfollow);
                        but_folow.setTextColor(Color.parseColor("#FFFFFF"));
                        but_folow.setText("FOLLOW");
                        Utils.showSnakBarDialog(UserProfileActivity.this,lin_topuser,
                                "UnFollow successfully ",R.color.msgresponce);
                    } else {
                        but_folow.setBackgroundResource(R.drawable.userdetail_button);
                        but_folow.setTextColor(Color.parseColor("#333333"));
                        but_folow.setText("FOLLOWING");
                        isFoloow = true;
                        Utils.showSnakBarDialog(UserProfileActivity.this,lin_topuser,
                                "Follow successfully ",R.color.msgresponce);
                    }
                    callServicegetProfiel();
                }
            }
            if (obj instanceof ReportUserModel) {
                ReportUserModel loginModel = (ReportUserModel) obj;
                if (loginModel.isStatus()) {
                   // Toast.makeText(UserProfileActivity.this, "Report successfully added", Toast.LENGTH_SHORT).show();
                    Utils.showSnakBarDialog(UserProfileActivity.this,lin_topuser,
                            "Report successfully added ",R.color.msgresponce);
                }
            }
            if (obj instanceof GetUserOwnNewsModel) {
                GetUserOwnNewsModel loginModel = (GetUserOwnNewsModel) obj;
                if (loginModel.isStatus()) {
                    arrayList = loginModel.getData().getData().getPagedRecord();
                    inItItemRecycle("save");

                }
            }
            if (obj instanceof GetUserHashTagModel) {
                GetUserHashTagModel loginModel = (GetUserHashTagModel) obj;
                if (loginModel.isStatus()) {
                    arrayListHash = loginModel.getData().getData().getPagedRecord();
                    inItItemRecycle("hash");
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }

    public void setDTaontextView(GetProfileDataModel.DataBean.AspNetUserBean aspNetUser) {
        if (aspNetUser.getUserType() != null && aspNetUser.getUserType().toString().equalsIgnoreCase("REPORTER")) {
            img_userType.setBackground(getResources().getDrawable(R.drawable.ic_group_7756));
        } else
        {
            img_userType.setBackground(getResources().getDrawable(R.drawable.ic_check));

        }
        txt_userFname.setText(aspNetUser.getFullName());
        txt_userUname.setText("@" + aspNetUser.getUserName());
        txt_userfolow.setText(aspNetUser.getFollowersSuffix());
        txt_userFolowing.setText(aspNetUser.getFollowingsSuffix());
        txt_userownvideo.setText(String.valueOf(aspNetUser.getNewsCount()));
        txt_userhashvideo.setText(String.valueOf(aspNetUser.getUserTagVideo()));

        if (aspNetUser.getAvatar() != null) {
            if (!aspNetUser.getAvatar().equals("null")) {
                Glide.with(UserProfileActivity.this)
                        .load(aspNetUser.getAvatar())
                        .into(img_userprofile);
               /* new Handler().post(new Runnable() {
                    public void run() {

                    }
                });*/
            }
        }
        if (!aspNetUser.isIsFollowed()) {
            isFoloow = false;
            but_folow.setBackgroundResource(R.drawable.hashtagdetailfollow);
            but_folow.setTextColor(Color.parseColor("#FFFFFF"));
            but_folow.setText("FOLLOW");

        } else {
            but_folow.setBackgroundResource(R.drawable.userdetail_button);
            but_folow.setTextColor(Color.parseColor("#333333"));
            but_folow.setText("FOLLOWING");
            isFoloow = true;
        }
        callServicegetUserOwnNews();
    }

    private void reloginToChat() {
        // showProgressDialog(R.string.dlg_relogin);
        pd=new ProgressDialog(this);
        pd.setMessage("Please Wait ...");
        pd.show();
        if (SharedPrefsHelper.getInstance().hasQbUser()) {
            ChatHelper.getInstance().loginToChat(SharedPrefsHelper.getInstance().getQbUser(), new QBEntityCallback<Void>() {
                @Override
                public void onSuccess(Void aVoid, Bundle bundle) {
                   pd.dismiss();
                }

                @Override
                public void onError(QBResponseException e) {
                    Log.e("error", "Relogin Failed " + e.getMessage());
                    pd.dismiss();
                    //  hideProgressDialog();

                }
            });
        }
    }

    public void nextACtivityzzforChat() {
        pd=new ProgressDialog(this);
        pd.setMessage("Please Wait ...");
        pd.setCancelable(false);
        pd.show();
        ArrayList<Integer> occupantIdsList = new ArrayList<Integer>();
        if(dataBean.getChatId()!=null) {
            occupantIdsList.add(Integer.valueOf(dataBean.getChatId()));
        }

        QBDialogCustomData jsonObject = new QBDialogCustomData("jiya");
       // jsonObject.putString("userid",dataBean.getId());
        jsonObject.putString("usertag",dataBean.getUserName());

        QBChatDialog dialog = new QBChatDialog();
        dialog.setType(QBDialogType.PRIVATE);
        dialog.setOccupantsIds(occupantIdsList);
        dialog.setPhoto(dataBean.getAvatar());
        dialog.setCustomData(jsonObject);
        dialog.setRoomJid("jiya");

        // or just use DialogUtils
        //QBChatDialog dialog = DialogUtils.buildPrivateDialog(recipientId);

        QBRestChatService.createChatDialog(dialog).performAsync(new QBEntityCallback<QBChatDialog>() {
            @Override
            public void onSuccess(QBChatDialog result, Bundle params) {
                pd.dismiss();
                Log.e("createChatDialog======", result.toString());
                ChatActivity.startForResult(UserProfileActivity.this, REQUEST_DIALOG_ID_FOR_UPDATE, result);
            }

            @Override
            public void onError(QBResponseException responseException) {

            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultReceived(UploadNewsModel.DataBean dataBean) {
        Intent intent = new Intent(UserProfileActivity.this, NewVideoActivity.class);
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