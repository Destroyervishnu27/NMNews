package com.nmnews.nmnewsagency.utils;

import static android.content.Context.DOWNLOAD_SERVICE;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static void setStatusBar(Activity activity) {
        // Hide Status Bar
        if (Build.VERSION.SDK_INT < 16) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = activity.getWindow().getDecorView();
            // Hide Status Bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String getUserIdfromdata() {
        String userid = "";
        int iend = Prefrence.getEmail().indexOf("@"); //this finds the first occurrence of "."
        if (iend != -1) {
            userid = Prefrence.getEmail().substring(0, iend); //this will give abc
        }
        return "@" + userid;
    }

    public static String parseDateToddMMyyyy(String time) {
        String[] parts = time.split("T");
        // String inputPattern = "yyyy-MM-dd";
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SS";
        String outputPattern = "MMM dd, yyyy hh:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String parseDateToddMMyyyy1(String time) {
        String[] parts = time.split("T");
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "MMM dd, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(parts[0]);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parts[0];
    }

    public static String timeConversion(long value) {
        String songTime;
        int dur = (int) value;
        int hrs = (dur / 3600000);
        int mns = (dur / 60000) % 60000;
        int scs = dur % 60000 / 1000;

        if (hrs > 0) {
            songTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            songTime = String.format("%02d:%02d", mns, scs);
        }
        return songTime;
    }

    public static void showSnakBarDialog(Context context, View view, String msg, int colu) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, colu));
        snackbar.show();
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


    public static boolean checkStatus(Context context, int status) {
        DownloadManager downloadManager = (DownloadManager)
                context.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();

        query.setFilterByStatus(status);
        Cursor c = downloadManager.query(query);
        if (c!=null && c.moveToFirst()) {
            c.close();
            Log.i("DOWNLOAD_STATUS", String.valueOf(status));
            return true;
        }
        Log.i("AUTOMATION_DOWNLOAD", "DEFAULT");
        return false;
    }

    public static boolean checkFileIsExist(Context context, String pdfURL) {
        boolean isExist = false;

        String fileName = pdfURL.substring(pdfURL.lastIndexOf('/') + 1, pdfURL.length());
        //String folder = Environment.getExternalStorageDirectory() + File.separator + context.getResources().getString(R.string.app_name) + File.separator;
        String folder = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator;
        File directory = new File(folder);

        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (new File(folder + fileName).exists()) {
            isExist = true;
        } else {
            isExist = false;
        }

        return isExist;
    }

    public static void startDownload(String productId, Context context) {

        String fileName = productId.substring(productId.lastIndexOf('/') + 1, productId.length());
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + context.getResources().getString(R.string.app_name));
        Uri music_uri = Uri.parse(productId);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(music_uri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        // .setDestinationInExternalFilesDir(DetailsPdf.this,File.separator + getResources().getString(R.string.app_name), fileName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long ref = downloadManager.enqueue(request);
    }

    public static void shareApp(Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Download NmNews application from play-store using this link https://play.google.com/store/apps/details?id=" + context.getPackageName());
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);
    }

    public static void shareNews(Context context,int newsId,String title) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, title+" https://nmnews.in/news/post/" + newsId+"\n \nTo get the latest news in your area. Download NM news https://play.google.com/store/apps/details?id=" + context.getPackageName());
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);
    }
}
