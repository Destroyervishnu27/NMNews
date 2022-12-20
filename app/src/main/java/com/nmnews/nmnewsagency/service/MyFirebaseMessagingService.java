package com.nmnews.nmnewsagency.service;

import android.content.Context;
import android.util.Log;

import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            if (remoteMessage == null)
                return;
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            //handleNotification(remoteMessage.getNotification().getBody());
        }
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Notification Body1: " + remoteMessage.getData().size() + "");
            Log.e(TAG, "Notification Body1=====: " + remoteMessage.getData() + "");
           // String Description = remoteMessage.getData().get("Description");
            String Description = remoteMessage.getData().get("Description");
            String type = remoteMessage.getData().get("Notification_Type");
            String NewsId = remoteMessage.getData().get("NewsId");
            String FromUserId = remoteMessage.getData().get("FromUserId");
            String ToUserId = remoteMessage.getData().get("ToUserId");
            String AddedOn = remoteMessage.getData().get("AddedOn");
            String UserId = remoteMessage.getData().get("UserId");
            String url = remoteMessage.getData().get("url");
            NotificationUtils.sendNotification(this, Description, type, NewsId,FromUserId,ToUserId,AddedOn,UserId,url);
        }


    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.e("Devicetoken=====", "onNewTokenDevice: " + token);
        Prefrence.setDeviceToken(token);
        // getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }

}
