package com.nmnews.nmnewsagency.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.extras.Constants;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.utils.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    String newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        Utils.setStatusBar(this);
        setContentView(R.layout.activity_splash);

        Uri uri = getIntent().getData();
        if (uri!=null && uri.toString()!=null && !uri.toString().equals(""))
        {
             newsId = uri.toString().substring(uri.toString().lastIndexOf('/') + 1, uri.toString().length());
        }

        keyHash();
    }

    private void setDataOnLocation() {
        if (Prefrence.isLogin() ) {

            if (newsId!=null && !newsId.equalsIgnoreCase(""))
            {
                Intent intent4 = new Intent(SplashScreen.this, ShairedVideoActivity.class);
                intent4.putExtra("newsid",Integer.parseInt(newsId));
                intent4.putExtra("self","other");
                startActivity(intent4);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
            }else {
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
            }




        } /*else if (Prefrence.isLogin() && !Prefrence.isFullLogin()) {
            Intent mainIntent = new Intent(SplashScreen.this, LocationReqActivity.class);
            startActivity(mainIntent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            finish();
        }*/ else {
            Intent mainIntent = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(mainIntent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermisionStatus();
    }

    public void checkPermisionStatus() {
        if (isLocationAllowed()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            initView();
        } else {
            requestLocationPermission();
        }
    }



    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setDataOnLocation();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private boolean isLocationAllowed() {
        //Getting the permission status
        int result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int result3 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int result4 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result5 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int result6 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int result7 = ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS);
         int result8 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        //int result7 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        //int result8 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        //If permission is granted returning true
        if (result1 == PackageManager.PERMISSION_GRANTED
                && result2 == PackageManager.PERMISSION_GRANTED
                && result3 == PackageManager.PERMISSION_GRANTED
                && result4 == PackageManager.PERMISSION_GRANTED
                && result5 == PackageManager.PERMISSION_GRANTED
                && result6 == PackageManager.PERMISSION_GRANTED
                && result7 == PackageManager.PERMISSION_GRANTED
              && result8 == PackageManager.PERMISSION_GRANTED
            //&& result7 == PackageManager.PERMISSION_GRANTED
            //&& result8 == PackageManager.PERMISSION_GRANTED
        )
            return true;

        //If permission is not granted returning false
        return false;
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Toast.makeText(this, "We need permission to show better result.", Toast.LENGTH_SHORT).show();
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE,
                        // Manifest.permission.CALL_PHONE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS,
                         Manifest.permission.CAMERA//, Manifest.permission.RECEIVE_SMS
                },
                Constants.LOCATION_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.LOCATION_PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } else if (grantResults.length > 0 && grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } else if (grantResults.length > 0 && grantResults[2] != PackageManager.PERMISSION_GRANTED) {
                //checkPermisionStatus();
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } else if (grantResults.length > 0 && grantResults[3] != PackageManager.PERMISSION_GRANTED) {
                //checkPermisionStatus();
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } else if (grantResults.length > 0 && grantResults[4] != PackageManager.PERMISSION_GRANTED) {
                //checkPermisionStatus();
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } else if (grantResults.length > 0 && grantResults[5] != PackageManager.PERMISSION_GRANTED) {
                //checkPermisionStatus();
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } else if (grantResults.length > 0 && grantResults[6] != PackageManager.PERMISSION_GRANTED) {
                //checkPermisionStatus();
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } else if (grantResults.length > 0 && grantResults[7] != PackageManager.PERMISSION_GRANTED) {
                //checkPermisionStatus();
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            }  /*else if (grantResults.length > 0 && grantResults[5] != PackageManager.PERMISSION_GRANTED) {
                //checkPermisionStatus();
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            }else if (grantResults.length > 0 && grantResults[6] != PackageManager.PERMISSION_GRANTED) {
                //checkPermisionStatus();
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } else if (grantResults.length > 0 && grantResults[7] != PackageManager.PERMISSION_GRANTED) {
                //checkPermisionStatus();
                Toast.makeText(this, getResources().getString(R.string.permissionString), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            } */ else {
                checkPermisionStatus();
            }
        }
    }
    public void keyHash() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.nmnews.nmnewsagency", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }
}