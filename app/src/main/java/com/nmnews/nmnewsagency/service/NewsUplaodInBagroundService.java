package com.nmnews.nmnewsagency.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.MainActivity;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsUplaodInBagroundService extends Service implements Callback<Object> {
    Rest rest;
    ProgressDialog mProgressDialog;
    NotificationCompat.Builder builder;
    NotificationManager notificationManager;
    int id = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        rest = new Rest(this, this);
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

// instantiate it within the onCreate method
        showNotificationInbag();

        rest.uploadNews(Prefrence.getVideoFile(),false);

    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            if (obj instanceof UploadNewsModel) {
                UploadNewsModel loginModel = (UploadNewsModel) obj;
                //editdelete
                // nextActivityGoing(loginModel.getData());
                if (loginModel.isStatus()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(loginModel);
                    Prefrence.setBagData(json);
                    completeNotification("News Uploaded successfully");
                    EventBus.getDefault().post(loginModel.getData());
                } else {
                    EventBus.getDefault().post("fail");
                    completeNotification("News Uploading Failure");
                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

        completeNotification("News Uploading Failure");
        EventBus.getDefault().post("fail");
        //  mProgressDialog.dismiss();
        // Toast.makeText(this, t.toString(), Toast.LENGTH_SHORT).show();;
        // Log.e("erroeservicecsll=====",t.toString());
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
        Intent intent=new Intent(this, MainActivity.class);
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


}
