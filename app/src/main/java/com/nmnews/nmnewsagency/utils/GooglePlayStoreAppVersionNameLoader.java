package com.nmnews.nmnewsagency.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.interfaces.WSCallerVersionListener;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class GooglePlayStoreAppVersionNameLoader extends AsyncTask<String, Void, JSONObject> {

    String newVersion = "";
    String currentVersion = "";
    WSCallerVersionListener mWsCallerVersionListener;
    boolean isVersionAvailabel;
    boolean isAvailableInPlayStore;
    Context mContext;
    String mStringCheckUpdate = "";
    private String latestVersion;

    public GooglePlayStoreAppVersionNameLoader(Context mContext, WSCallerVersionListener callback) {
        mWsCallerVersionListener = callback;
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        try {
            latestVersion = Jsoup.connect(mContext.getString(R.string.playstore_url))
                    .timeout(80000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                    .first()
                    .ownText();
            Log.e("latestversion","---"+latestVersion);

            checkApplicationCurrentVersion();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if(latestVersion!=null){
            boolean isVersionAvailabel =false;
            // currentVersion = mContext.getResources().getString(R.string.CurrentVersion);
            if(!currentVersion.equalsIgnoreCase(latestVersion))
            {
                isVersionAvailabel =true;
            }
            mWsCallerVersionListener.onGetResponse(isVersionAvailabel);
        }
        super.onPostExecute(jsonObject);
    }


    /**
     * Method to check current app version
     */
    public void checkApplicationCurrentVersion() {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        currentVersion = packageInfo.versionName;
        Log.e("currentVersion", currentVersion);
    }

    /**
     * Method to check internet connection
     * @param context
     * @return
     */
//    public boolean isNetworkAvailable(Context context)
//    {
//        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
//        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
//    }
}
