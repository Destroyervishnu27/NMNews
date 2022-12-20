package com.nmnews.nmnewsagency.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.modelclass.GetTahsilModel;
import com.nmnews.nmnewsagency.modelclass.SetAddressModelClass;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationReqActivity extends AppCompatActivity implements Callback<Object> {
    private static final int MY_CAMERA_REQUEST_CODE = 22222;
    Button but_loc_manually, but_selfloc;
    TextView txt_loc_manually;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 200;
    LocationCallback mLocationCallback;
    protected Location mLastLocation;
    ProgressDialog dlg = null;
    static String city, address, state, country, postalCode, knownName, houseno, addrs1,tahsil;
    double latsend, lngsend;
    Rest rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_location_req);
        rest = new Rest(this, this);
        iniIt();
    }

    private void iniIt() {
        but_loc_manually = (Button) findViewById(R.id.but_loc_manually);
        but_selfloc = (Button) findViewById(R.id.but_selfloc);
        but_loc_manually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationReqActivity.this, SelectLocationActivity.class);
                startActivity(intent);
            }
        });
        but_selfloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    showProgress();
                    getLastLocation();
                } else {
                    requestCameraPermission();
                }

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    protected void onRestart() {
        Utils.setStatusBar(this);
        super.onRestart();
    }

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(LocationReqActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationReqActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void getLastLocation() {

        final LocationManager locationManager = (LocationManager) LocationReqActivity.this.getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (dlg != null) {
                dlg.show();
            }


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            fusedLocationProviderClient.getLastLocation()
                    .addOnCompleteListener(LocationReqActivity.this, new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                mLastLocation = task.getResult();
                                // Toast.makeText(LocationReqActivity.this, "location", Toast.LENGTH_SHORT).show();
                                // txt_loc_manually.setText(String.valueOf(mLastLocation.getLatitude()));
                                // dlg.dismiss();
                                // longitudeValue = mLastLocation.getLongitude();
                                getAddresFromLatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());

                            } else {
                                // Toast.makeText(LocationReqActivity.this, "location1", Toast.LENGTH_SHORT).show();
                                try {
                                    LocationRequest mFusedLocationRquest = new LocationRequest();
                                    mFusedLocationRquest.setInterval(100);
                                    mFusedLocationRquest.setFastestInterval(100);
                                    mFusedLocationRquest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                                    mLocationCallback = new LocationCallback() {
                                        @Override
                                        public void onLocationResult(LocationResult locationResult) {
                                            super.onLocationResult(locationResult);

                                            fusedLocationProviderClient.removeLocationUpdates(mLocationCallback);

                                            mLastLocation = locationResult.getLastLocation();

                                            // txt_loc_manually.setText(String.valueOf(mLastLocation.getLatitude()));
                                            getAddresFromLatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                                            // longitudeValue = mLastLocation.getLongitude();
                                        }
                                    };

                                    if (ActivityCompat.checkSelfPermission(LocationReqActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationReqActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                        return;
                                    }
                                    fusedLocationProviderClient.requestLocationUpdates(mFusedLocationRquest, mLocationCallback, null);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(LocationReqActivity.this);
            builder.setMessage("No location detected. Make sure location is enabled and set to high accuracy on the device.")
                    .setTitle("GPS Settings");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    /*Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(settingsIntent);*/
                    startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0) {
            switch (requestCode) {
                case 1:
                    // Toast.makeText(LocationReqActivity.this,"location",Toast.LENGTH_SHORT).show();
                    // dlg.show();
                    getLastLocation();
                    break;
            }
        }
    }

    public void showProgress() {
        final int THREE_SECONDS = 3 * 1000;
        dlg = new ProgressDialog(this);
        dlg.setMessage("Please Wait...");
        dlg.setCancelable(false);
        //  dlg.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // dlg.dismiss();
            }
        }, THREE_SECONDS);
    }

    public void getAddresFromLatLng(double lat, double lng) {
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (Exception e) {
        }
        if (addresses != null) {
            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
            postalCode = addresses.get(0).getPostalCode();
            knownName = addresses.get(0).getSubLocality();
            houseno = addresses.get(0).getFeatureName();
            addrs1 = addresses.get(0).getSubLocality();
            latsend = addresses.get(0).getLatitude();
            lngsend = addresses.get(0).getLongitude();

            Prefrence.setCityName(city);
            Prefrence.setCountryName(country);
            Prefrence.setStateName(state);
            //  Prefrence.settahsil("");
            Prefrence.setCountryId("");
            Prefrence.setCityIdd("");
            Prefrence.setStateIdd("");
            Prefrence.settahsilIdd("");
            String knownName1 = addresses.get(0).getAdminArea();
            String knownName2 = addresses.get(0).getSubAdminArea();
            dlg.dismiss();
            callServicesetgetTahsil(city, latsend, lngsend,country,state);
            //  callServicesetAddress(address,city,state,country,postalCode,houseno,addrs1,latsend,lngsend);
            // goNextAct();
        } else {
            Toast.makeText(LocationReqActivity.this, "Sorry we are not getting your current location !", Toast.LENGTH_SHORT).show();
        }// Only if available else return NULL
    }

    private void callServicesetAddress(String address, String city, String state, String country,
                                       String postalCode, String houseno, String addrs1, double latSend, double lngSend, String tahsil) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.setUserAddress(addrs1, "", "APP", "", 0, "", 0, Prefrence.getFirstName(),
                city, country, address, state, houseno, String.valueOf(Prefrence.getisLocationMatch()), "true", Prefrence.getLastName(),
                latSend, lngSend, 0, 0,
                Prefrence.getUserId(), postalCode,tahsil);
    }

    private void callServicesetgetTahsil(String city, double lat, double lng, String country, String state) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.setUserTahsil(city, lat, lng,country,state);
    }


    public void goNextAct() {
        Intent intent = new Intent(LocationReqActivity.this, SelfLocationActivity.class);
        intent.putExtra("city", tahsil);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_CAMERA_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //   Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    showProgress();
                    getLastLocation();
                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                        != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestCameraPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new androidx.appcompat.app.AlertDialog.Builder(LocationReqActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof SetAddressModelClass) {
                SetAddressModelClass loginModel = (SetAddressModelClass) obj;
                if (loginModel.isStatus()) {
                    Toast.makeText(this, "Location successfully saved", Toast.LENGTH_SHORT).show();
                    goNextAct();
                }
            }
            if (obj instanceof GetTahsilModel) {
                GetTahsilModel loginModel = (GetTahsilModel) obj;
                if (loginModel.isStatus()) {
                    tahsil=loginModel.getData();
                    Prefrence.setisLocationMatch(loginModel.isStatus());
                    Prefrence.settahsil(loginModel.getData());
                    callServicesetAddress(address,city,state,country,postalCode,houseno,addrs1,latsend,lngsend,loginModel.getData());
                }else{
                    tahsil="";
                    Prefrence.setisLocationMatch(loginModel.isStatus());
                    callServicesetAddress(address,city,state,country,postalCode,houseno,addrs1,latsend,lngsend,"");
                }
            }
        }

    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }
}