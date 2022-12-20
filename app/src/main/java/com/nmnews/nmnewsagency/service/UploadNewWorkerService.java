package com.nmnews.nmnewsagency.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.MainActivity;
import com.nmnews.nmnewsagency.model.NewsUploadSuccessModel;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestAddNews;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;
import com.simform.videooperations.CallBackOfQuery;
import com.simform.videooperations.Common;
import com.simform.videooperations.FFmpegCallBack;
import com.simform.videooperations.FFmpegQueryExtension;
import com.simform.videooperations.LogMessage;
import com.simform.videooperations.Statistics;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadNewWorkerService extends Service implements Callback<Object>, FFmpegCallBack {

    private Context context;
    private Rest rest;

    NotificationCompat.Builder builder;
    NotificationManager notificationManager;
    int id = 1;

    FFmpegQueryExtension fFmpegQueryExtension;
    CallBackOfQuery callBackOfQuery;
    FFmpegCallBack fFmpegCallBack;

    @Override
    public void onCreate() {
        super.onCreate();
        rest = new Rest(this, this);
        fFmpegQueryExtension = new FFmpegQueryExtension();
        callBackOfQuery = new CallBackOfQuery();
        fFmpegCallBack = this;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        callServicenewsCount();
    }

    private void callServicenewsCount() {
        showNotificationInbag();
        String data = Prefrence.getNewsUploadData();
        Gson gson = new Gson();
        RequestAddNews.NewsObjBean messageResponseModel = gson.fromJson(data, RequestAddNews.NewsObjBean.class);
       // executeFmpegCommand(messageResponseModel.getVideoFilePath());
        rest.insertNews(messageResponseModel.getTitle(),messageResponseModel.getDescription(),
                messageResponseModel.getSuggestion(),messageResponseModel.getVideoFilePath(),
                messageResponseModel.isIsBreakingNews(),messageResponseModel.getNewsType(),
                String.valueOf(messageResponseModel.getCountryId()),messageResponseModel.getCountry_Name(),
                String.valueOf(messageResponseModel.getStateId()),messageResponseModel.getState_Name(),String.valueOf(messageResponseModel.getCityId()),
                messageResponseModel.getCity_Name(),String.valueOf(messageResponseModel.getTahsilId()),
                messageResponseModel.getTahsil_Name(),messageResponseModel.getAddressLine_1(),
                messageResponseModel.getAddressLin_2(),String.valueOf(messageResponseModel.getLat()),String.valueOf(messageResponseModel.getLong()),
                messageResponseModel.getZipCode(),messageResponseModel.getUserTags(),messageResponseModel.getHashTags());
    }


    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            if (obj instanceof NewsUploadSuccessModel) {
                NewsUploadSuccessModel loginModel = (NewsUploadSuccessModel) obj;
                //editdelete
                // nextActivityGoing(loginModel.getData());
                if (loginModel.isStatus()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(loginModel);
                    Prefrence.setBagData(json);
                    completeNotification("News upload successful . it display after sometime");
                    EventBus.getDefault().post("Success");

                } else {
                    EventBus.getDefault().post("fail");
                    completeNotification("News Uploading Failure");

                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        completeNotification("News Uploading Failure");
        EventBus.getDefault().post("fail");
        rest.dismissProgressdialog();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void showNotificationInbag() {
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        // Notification Channel is required for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("channel description");
            channel.setShowBadge(true);
            channel.canShowBadge();
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});
            notificationManager.createNotificationChannel(channel);
        }

        //  notificationManager.notify(0, notificationBuilder.build());
        builder = new NotificationCompat.Builder(this, "channel_id");
        builder.setContentTitle("News Uploading ..")
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.drawable.logo2)
                .setLights(Color.RED, 1000, 300)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)//to show content in lock screen
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        builder.setProgress(100, 0, true);
        // Displays the progress bar for the first time.
        notificationManager.notify(id, builder.build());
        CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                EventBus.getDefault().post("start");
            }
        }.start();

    }

    public void completeNotification(String msg) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        builder.setContentTitle(msg)
                .setProgress(0, 0, false)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.drawable.logo2)
                .setLights(Color.RED, 1000, 300)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_LOW);
        notificationManager.notify(id, builder.build());
    }

    @Override
    public void process(@NonNull LogMessage logMessage) {
        Log.e("Video process ", "process");
    }

    @Override
    public void statisticsProcess(@NonNull Statistics statistics) {
        Log.e("Video statisticsProcess ", "statisticsProcess");
    }

    @Override
    public void success() {
        Log.e("Video success ", "success");
    }

    @Override
    public void cancel() {
        Log.e("Video cancel ", "cancel");
    }

    @Override
    public void failed() {
        Log.e("Video failed ", "failed");
    }

    public void executeFmpegCommand(String inputVideoPath) {
        File folder = getCacheDir();

        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                MediaMetadataRetriever    retriever = new MediaMetadataRetriever();
                retriever.setDataSource(inputVideoPath);
                String outputPath = new File(folder, System.currentTimeMillis() + ".mp4").getAbsolutePath();
                String[] query = fFmpegQueryExtension.compressor(inputVideoPath, retriever.getFrameAtTime().getWidth(), retriever.getFrameAtTime().getHeight(), outputPath);


                //String[] query = fFmpegQueryExtension.addAudioOnVideoNew(inputVideo, inputAudio, outputVideo);
                callBackOfQuery.callQuery((AppCompatActivity) getApplicationContext(), query, new FFmpegCallBack() {
                    @Override
                    public void process(@NonNull LogMessage logMessage) {
                        Log.e("Video failed ", "failed");
                    }

                    @Override
                    public void statisticsProcess(@NonNull Statistics statistics) {
                        Log.e("Video failed ", "failed");
                    }

                    @Override
                    public void success() {
                        Log.e("Video failed ", "failed");
                    }

                    @Override
                    public void cancel() {
                        Log.e("Video failed ", "failed");
                    }

                    @Override
                    public void failed() {
                        Log.e("Video failed ", "failed");
                    }
                });

            }
        }) ;

          }
}
