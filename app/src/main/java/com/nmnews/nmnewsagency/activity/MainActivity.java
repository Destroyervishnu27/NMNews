package com.nmnews.nmnewsagency.activity;

import static java.lang.Thread.sleep;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.dialogs.UpdateApplicationDialog;
import com.nmnews.nmnewsagency.fragment.FragmentNotification;
import com.nmnews.nmnewsagency.fragment.FragmentSearch;
import com.nmnews.nmnewsagency.fragment.HomeFragment;
import com.nmnews.nmnewsagency.fragment.ProfileFragment;
import com.nmnews.nmnewsagency.interfaces.WSCallerVersionListener;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.service.UploadNewWorkerService;
import com.nmnews.nmnewsagency.utils.ToastUtils;
import com.nmnews.nmnewsagency.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        Callback<Object>, WSCallerVersionListener {
    private static final int IMAGE_PICKER_SELECT = 111;
    ImageView botom_home, botom_search, botom_add, botom_noti, botom_profile, iamge_back;
    TextView botom_home_text, botom_search_text, botom_noti_text, botom_profile_text, txt_videouplodinbg;
    LinearLayout lin_home, lin_search, lin_add, lin_noti, lin_profile, lin_top, lin_onlysearch, lin_searchwithedit, lin_topmain,
            lin_uploadingvideo, lin_uplodingperctage;
    EditText search_edit;
    static final int REQUEST_VIDEO_CAPTURE = 100;
    BottomSheetDialog bottomSheetDialog;
    Animation myAnim;
    Rest rest;
    boolean statusCompress = false, homeFragmentOpen = false, homeclick = false;
    String outputPaTH = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + System.nanoTime() + "compress.mp4";
    Fragment currentFragment;
    ProgressDialog dialog;
    protected static final long TIME_DELAY = 10000;
    Handler handler=new Handler();
    int count =0;
    UploadNewsModel uploadNewsModel;

    private boolean activityIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //new GooglePlayStoreAppVersionNameLoader(getApplicationContext(), this).execute();

        Gson gson = new Gson();
        String json = Prefrence.getBagData();
        if(json!=null && !json.isEmpty()) {
            uploadNewsModel = gson.fromJson(json, UploadNewsModel.class);
            if (!uploadNewsModel.isStatus())
            {
                Prefrence.removeBagroundDate();
                Intent intent = new Intent(MainActivity.this, NewVideoActivity.class);
                intent.putExtra("video", uploadNewsModel.getData());
                startActivity(intent);
            }
        }

        rest = new Rest(this, this);
        inItViews();
        setOnClickListner();
        myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        if (Prefrence.getCountryName().isEmpty()) {
            Utils.showToast(this, "Please select your location in choose location and login again");
            closrAllActivity();
        }

        loadFragment(new HomeFragment());
        homeFragmentOpen = true;
        search_edit = (EditText) findViewById(R.id.search_edit);
        lin_top.setVisibility(View.GONE);
        search_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // search_edit.setEnabled(true);
                // loadFragment1(new FragmentSearchTab());
                /*Intent intent = new Intent(MainActivity.this, SearchTopBarTabingActivity.class);
                startActivity(intent);*/
            }
        });
        search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                    hideKeyboard(MainActivity.this);
                    lin_searchwithedit.setVisibility(View.GONE);
                    lin_onlysearch.setVisibility(View.VISIBLE);
                    search_edit.setText("");
                    return true;
                }
                return false;
            }


        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityIsActive = true;
        if (EventBus.getDefault().isRegistered(this)) {
        } else {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void setOnClickListner() {
        lin_home.setOnClickListener(this);
        lin_search.setOnClickListener(this);
        lin_add.setOnClickListener(this);
        lin_noti.setOnClickListener(this);
        lin_profile.setOnClickListener(this);
        lin_top.setOnClickListener(this);
        lin_onlysearch.setOnClickListener(this);
        lin_searchwithedit.setOnClickListener(this);
        iamge_back.setOnClickListener(this);

        botom_home.setImageResource(R.drawable.ic_home_active);
        botom_search.setImageResource(R.drawable.ic_contest);
        botom_noti.setImageResource(R.drawable.ic_wallet);
        botom_profile.setImageResource(R.drawable.ic_more);

        botom_home_text.setTextColor(getResources().getColor(R.color.locbutonbag));
        botom_search_text.setTextColor(getResources().getColor(R.color.loctitle));
        botom_noti_text.setTextColor(getResources().getColor(R.color.loctitle));
        botom_profile_text.setTextColor(getResources().getColor(R.color.loctitle));
    }

    private void inItViews() {
        iamge_back = (ImageView) findViewById(R.id.iamge_back);
        botom_home = (ImageView) findViewById(R.id.botom_home);
        botom_search = (ImageView) findViewById(R.id.botom_search);
        botom_add = (ImageView) findViewById(R.id.botom_add);
        botom_noti = (ImageView) findViewById(R.id.botom_noti);
        botom_profile = (ImageView) findViewById(R.id.botom_profile);
        botom_home_text = (TextView) findViewById(R.id.botom_home_text);
        botom_search_text = (TextView) findViewById(R.id.botom_search_text);
        botom_noti_text = (TextView) findViewById(R.id.botom_noti_text);
        botom_profile_text = (TextView) findViewById(R.id.botom_profile_text);
        txt_videouplodinbg = (TextView) findViewById(R.id.txt_videouplodinbg);
        lin_home = (LinearLayout) findViewById(R.id.lin_home);
        lin_search = (LinearLayout) findViewById(R.id.lin_search);
        lin_add = (LinearLayout) findViewById(R.id.lin_add);
        lin_noti = (LinearLayout) findViewById(R.id.lin_noti);
        lin_profile = (LinearLayout) findViewById(R.id.lin_profile);
        lin_top = (LinearLayout) findViewById(R.id.lin_top);
        lin_searchwithedit = (LinearLayout) findViewById(R.id.lin_searchwithedit);
        lin_topmain = (LinearLayout) findViewById(R.id.lin_topmain);
        lin_uploadingvideo = (LinearLayout) findViewById(R.id.lin_uploadingvideo);
        lin_uplodingperctage = (LinearLayout) findViewById(R.id.lin_uplodingperctage);
        lin_onlysearch = (LinearLayout) findViewById(R.id.lin_onlysearch);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_home:

                lin_home.startAnimation(myAnim);
                lin_top.setVisibility(View.GONE);
                botom_home.setImageResource(R.drawable.ic_home_active);
                botom_search.setImageResource(R.drawable.ic_contest);
                botom_noti.setImageResource(R.drawable.ic_wallet);
                botom_profile.setImageResource(R.drawable.ic_more);

                botom_home_text.setTextColor(getResources().getColor(R.color.locbutonbag));
                botom_search_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_noti_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_profile_text.setTextColor(getResources().getColor(R.color.loctitle));
                if (homeFragmentOpen) {
                    Prefrence.setisUpload(true);
                    loadFragment(new HomeFragment());
                } else if (homeclick) {
                    Prefrence.setisUpload(true);
                    loadFragment(new HomeFragment());
                } else {
                    loadFragment(new HomeFragment());
                }
                homeclick = true;
                break;
            case R.id.lin_search:
                lin_search.startAnimation(myAnim);
                lin_top.setVisibility(View.VISIBLE);
                botom_search.setImageResource(R.drawable.ic_contest__1_);
                botom_home.setImageResource(R.drawable.ic_home_active__1_);
                botom_noti.setImageResource(R.drawable.ic_wallet);
                botom_profile.setImageResource(R.drawable.ic_more);

                botom_home_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_search_text.setTextColor(getResources().getColor(R.color.locbutonbag));
                botom_noti_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_profile_text.setTextColor(getResources().getColor(R.color.loctitle));
                homeFragmentOpen = false;
                homeclick = false;
                loadFragment(new FragmentSearch());
                break;
            case R.id.lin_add:
                lin_add.startAnimation(myAnim);
                lin_top.setVisibility(View.GONE);
                botom_search.setImageResource(R.drawable.ic_contest);
                botom_home.setImageResource(R.drawable.ic_home_active__1_);
                botom_noti.setImageResource(R.drawable.ic_wallet);
                botom_profile.setImageResource(R.drawable.ic_more);

                botom_home_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_search_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_noti_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_profile_text.setTextColor(getResources().getColor(R.color.loctitle));
                homeFragmentOpen = false;
                homeclick = false;
                openDialogBox();
                /*Intent intentn = new Intent(MainActivity.this, NewVideoActivity.class);
                startActivity(intentn);*/
                break;
            case R.id.lin_noti:
                lin_noti.startAnimation(myAnim);
                lin_top.setVisibility(View.GONE);
                botom_noti.setImageResource(R.drawable.ic_wallet__1_);
                botom_home.setImageResource(R.drawable.ic_home_active__1_);
                botom_search.setImageResource(R.drawable.ic_contest);
                botom_profile.setImageResource(R.drawable.ic_more);

                botom_home_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_search_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_noti_text.setTextColor(getResources().getColor(R.color.locbutonbag));
                botom_profile_text.setTextColor(getResources().getColor(R.color.loctitle));
                homeFragmentOpen = false;
                homeclick = false;
                loadFragment(new FragmentNotification());
                break;
            case R.id.lin_profile:
                lin_profile.startAnimation(myAnim);
                lin_top.setVisibility(View.GONE);
                botom_profile.setImageResource(R.drawable.ic_more__2_);
                botom_home.setImageResource(R.drawable.ic_home_active__1_);
                botom_search.setImageResource(R.drawable.ic_contest);
                botom_noti.setImageResource(R.drawable.ic_wallet);

                botom_home_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_search_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_noti_text.setTextColor(getResources().getColor(R.color.loctitle));
                botom_profile_text.setTextColor(getResources().getColor(R.color.locbutonbag));
                homeFragmentOpen = false;
                homeclick = false;
                loadFragment(new ProfileFragment());
                break;
            case R.id.lin_onlysearch:
                // lin_onlysearch.startAnimation(myAnim);
               /* lin_searchwithedit.setVisibility(View.VISIBLE);
                search_edit.setFocusable(true);
                lin_onlysearch.setVisibility(View.GONE);*/
                // Intent intent = new Intent(MainActivity.this, SearchTopBarTabingActivity.class);
                //startActivity(intent);
                break;
            case R.id.iamge_back:
                iamge_back.startAnimation(myAnim);
                lin_searchwithedit.setVisibility(View.GONE);
                lin_onlysearch.setVisibility(View.VISIBLE);
                break;

        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        currentFragment = fm.findFragmentById(R.id.frame_loc);

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_loc, fragment, fragment.getClass().toString());
        fragmentTransaction.commit(); // save the changes
    }

    private void loadFragment1(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frame_loc, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideKeyboard(MainActivity.this);
        lin_searchwithedit.setVisibility(View.GONE);
        lin_onlysearch.setVisibility(View.VISIBLE);
        search_edit.setText("");
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            String videoUri=intent.getStringExtra("video");
            compressVIdeoandGoingNextActivity(videoUri);
          /*  Intent intentn = new Intent(MainActivity.this, NewVideoActivity.class);
            intentn.putExtra("video", String.valueOf(videoUri));
            startActivity(intentn);*/
        }//
        // delete
        if (requestCode == IMAGE_PICKER_SELECT && resultCode == RESULT_OK) {
            Uri selectedImageUri = intent.getData();
            String filemanagerstring = selectedImageUri.getPath();
            Log.e("filemanagerstring==", filemanagerstring);
            String selectedImagePath = getPath(selectedImageUri);
            Log.e("selectedImagePath==", selectedImagePath);
            if (selectedImagePath != null) {
                //forDemoFunctionANdDelete(selectedImagePath);
                compressVIdeoandGoingNextActivity(selectedImagePath);
            }
        }
    }

/*
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();

                    }
                }
            });
*/


    public void forDemoFunctionANdDelete(String pah) {
        Prefrence.setVideoFIle(pah);
        UploadNewsModel.DataBean data = new UploadNewsModel.DataBean();
        nextActivityGoing(data);
    }

    public void compressVIdeoandGoingNextActivity(String selectedImagePath) {
        File file = new File(selectedImagePath);
        long length = file.length();
        length = length / 1024;
        Log.e("length====", "" + length);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        Log.e("uri====", Uri.fromFile(file).toString());
        retriever.setDataSource(selectedImagePath);
        //  retriever.setDataSource(this, Uri.fromFile(file));
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long timeInMillisec = Long.parseLong(time);
        retriever.release();
        Log.e("timeInMillisec==", String.valueOf(timeInMillisec));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMillisec);
        Log.e("seconds==", String.valueOf(seconds));

        if (seconds > 300) {
            showalertDialog("Please select video length less then '5 min' ");
        } else if (length > 50400) {
            showalertDialog("Please select video size less then '50 MB' ");
        } else {
            callServiceUploadNews(selectedImagePath);
        }
    }

    private void callServiceUploadNews(String selectedImagePath) {
        if (rest.isInterentAvaliable()) {
            File file = new File(selectedImagePath);
            Prefrence.setVideoFIle(file.getAbsolutePath());
            // setProgressSet();
            //rest.ShowDialogue("jgjhfjhfgjhfjyh");
            // rest.uploadNews(selectedImagePath);

            Intent intentn = new Intent(MainActivity.this, UploadNewsActivity.class);
            intentn.putExtra("videoUrl", selectedImagePath);
            startActivity(intentn);

           // startService(new Intent(this, NewsUplaodInBagroundService.class));
        } else {
            rest.AlertForInternet();
        }

    }

    public void setProgressSet() {
        final int totalProgressTime = 100;
        runOnUiThread(new Runnable() {
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(1000);
                        jumpTime += 1;
                        txt_videouplodinbg.setText("" + jumpTime + " %");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
       /* final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(1000);
                        jumpTime += 1;
                        txt_videouplodinbg.setText("" + jumpTime + " %");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();*/
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    private void openDialogBox() {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_createvideo);
        LinearLayout lin_addVideo = bottomSheetDialog.findViewById(R.id.lin_addVideo);
        lin_addVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //throw new RuntimeException("Test Crash"); // Force a crash
                Intent intent = new Intent(MainActivity.this, VideoViewActivity22.class);
                startActivityForResult(intent,REQUEST_VIDEO_CAPTURE);
                bottomSheetDialog.dismiss();
            }
        });
        LinearLayout lin_pickVideo = bottomSheetDialog.findViewById(R.id.lin_pickVideo);
        lin_pickVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("video/*");
                startActivityForResult(pickIntent, IMAGE_PICKER_SELECT);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }


    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {

            Object obj = response.body();
            if (obj instanceof UploadNewsModel) {

                UploadNewsModel loginModel = (UploadNewsModel) obj;
                if (loginModel.isStatus()) {
                    // dialog.setProgress(100);
                    ///dialog.dismiss();
                    nextActivityGoing(loginModel.getData());
                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        Log.e("error", t.toString());
        rest.dismissProgressdialog();
        Utils.showSnakBarDialog(this, lin_topmain, t.toString(), R.color.alert);
    }

    private void nextActivityGoing(UploadNewsModel.DataBean data) {
        Intent intent = new Intent(MainActivity.this, NewVideoActivity.class);
        intent.putExtra("video", data);
        startActivity(intent);
    }

    public void showalertDialog(String msg) {

        new AlertDialog.Builder(this)
                .setTitle("Select Video")
                .setMessage(msg)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        pickIntent.setType("video/*");
                        startActivityForResult(pickIntent, IMAGE_PICKER_SELECT);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    private void callmethod() {
        callServiceUploadNews(outputPaTH);
    }

    //6.21---2.17
    //7.22---1.06
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultReceived(UploadNewsModel.DataBean dataBean) {
        Prefrence.removeBagroundDate();
        lin_uplodingperctage.setVisibility(View.GONE);
        Intent intent = new Intent(MainActivity.this, NewVideoActivity.class);
        intent.putExtra("video", dataBean);
        startActivity(intent);
        // finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultReceived(String type) {
       // Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
        if (type.equalsIgnoreCase("start")) {
            lin_uploadingvideo.setVisibility(View.VISIBLE);
            //showtimerVideoCapture();
        }else{
            lin_uplodingperctage.setVisibility(View.GONE);
            lin_uplodingperctage.setVisibility(View.GONE);
            lin_uploadingvideo.setVisibility(View.GONE);
           // ToastUtils.longToast("News upload successful . it display after sometime");
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Success")
                    .setContentText("You have uploaded the video of your choice. Your video has been uploaded successfully. It will appear in your profile shortly")
                    .show();
        }

    }

    public void showtimerVideoCapture() {

        CountDownTimer countDownTimer = new CountDownTimer(50000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                lin_uploadingvideo.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_slide_out_right));
                lin_uploadingvideo.setVisibility(View.GONE);
                lin_uplodingperctage.setVisibility(View.VISIBLE);
                // setProgressSet();
            }
        }.start();
        count=0;
        handler.post(updateTextRunnable);
    }

    Runnable updateTextRunnable=new Runnable(){
        public void run() {
            count++;
            txt_videouplodinbg.setText("" +count+"%");
            handler.postDelayed(this, TIME_DELAY);
        }
    };

    private void closrAllActivity() {
        Prefrence.removeOrClearAllPerferanceData();
        QBUsers.signOut().performAsync(new QBEntityCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid, Bundle bundle) {
                Intent intent = new Intent(MainActivity.this,
                        LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onError(QBResponseException e) {

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        activityIsActive = false;
    }

    @Override
    public void onGetResponse(boolean isUpdateAvailable) {
        Log.e("ResultAPPMAIN", String.valueOf(isUpdateAvailable));
        if (isUpdateAvailable) {
            if (activityIsActive) {
                new UpdateApplicationDialog(this).show();

            }
        }
    }
}