
package com.nmnews.nmnewsagency.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.chat.ChatHelper;
import com.nmnews.nmnewsagency.dialogs.AgreeExamDialog;
import com.nmnews.nmnewsagency.interfaces.LoginClickInterface;
import com.nmnews.nmnewsagency.modelclass.ChatIdModerl;
import com.nmnews.nmnewsagency.modelclass.LoginModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.pref.SharedPrefsHelper;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.ErrorUtils;
import com.nmnews.nmnewsagency.utils.Utils;
import com.nmnews.nmnewsagency.utils.qb.QbUsersHolder;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements Callback<Object>,
        View.OnClickListener, LoginClickInterface {
    RelativeLayout but_user, but_reporter, lin_toplogin;
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 100;
    CallbackManager callbackManager;
    Rest rest;
    String provider, prouserid, name, email, image = "", tokenSend = "";
    LoginButton login_button;
    QBUser user;
    private static final int UNAUTHORIZED = 401;
    private boolean isLocation;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_login);
        // Firebase.setAndroidContext(this);

        //  QBSettings.getInstance().setEndpoints(Constants.API_DOMAIN,Constants.CHAT_DOMAIN, ServiceZone.PRODUCTION);
        // QBSettings.getInstance().setZone(ServiceZone.PRODUCTION);

        // QBSettings.getInstance().setLogLevel(LogLevel.DEBUG);
        //  QBChatService.setDebugEnabled(true);

        //   QBChatService.getInstance().setReconnectionAllowed(true);
        user = new QBUser();
        String deviceId = getDeviceId(LoginActivity.this);
        Log.e("DeviceId=======", String.valueOf(deviceId));
        Prefrence.setDeviceId(deviceId);
        iniIt();
        rest = new Rest(this, this);
    }

    private void iniIt() {
        login_button = (LoginButton) findViewById(R.id.login_button);
        but_reporter = (RelativeLayout) findViewById(R.id.but_reporter);
        but_user = (RelativeLayout) findViewById(R.id.but_user);
        lin_toplogin = (RelativeLayout) findViewById(R.id.lin_toplogin);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();
        but_reporter.setOnClickListener(this);
        but_user.setOnClickListener(this);


        login_button.setReadPermissions(Arrays.asList("email", "public_profile", "user_friends"));
        callbackManager = CallbackManager.Factory.create();
        /*Register facebook call back with button click that is use to generate Access token */
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                //loginResult.getAccessToken();
                //loginResult.getRecentlyDeniedPermissions()
                //loginResult.getRecentlyGrantedPermissions()
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.d("API123", loggedIn + " ??");
                //if (loggedIn)
                //  {
                /*Get all facebook profile public details for login into app */
                getUserProfile(loginResult.getAccessToken());
                //  }
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("API123", "" + " ??");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("API123", "" + " ??");
            }
        });
    }

    private void setDataOnLocation(boolean isAdress) {
        // Prefrence.setlogin("yes");
        if (!isAdress) {
            Intent mainIntent = new Intent(LoginActivity.this, LocationReqActivity.class);
            startActivity(mainIntent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            finish();
        } else {
            Intent Intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(Intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            finish();
        }
    }

    private void signIn() {
        mGoogleSignInClient.signOut();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            // setDataOnLocation();
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            name = account.getDisplayName();
            Prefrence.setFirstName(name);
            email = account.getEmail();
            provider = "GOOGLE";
            prouserid = account.getId();
            Log.e("image", String.valueOf(account.getPhotoUrl()));
            if (String.valueOf(account.getPhotoUrl()) != null) {
                image = String.valueOf(account.getPhotoUrl());
            }
            createLogin();
        } catch (ApiException e) {
            Log.e("Google Sign in Error ", e.getMessage());
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //   Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            // updateUI(null);
        }
    }


    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {

            Object obj = response.body();
            if (obj instanceof LoginModel) {

                LoginModel loginModel = (LoginModel) obj;
                if (loginModel.isStatus() && loginModel.getData().getUserInfo().isUserActive()) {
                    Prefrence.setUserId(loginModel.getData().getUserInfo().getUserId());
                   /* prepareUser(loginModel.getData().getUserInfo().getEmail(), loginModel.getData().getUserInfo().getName(),
                            loginModel.getData().getUserInfo().getProfileImage(), loginModel.getData().isIsAddressFound(),
                            loginModel.getData().getUserInfo().getUserId());*/
                    Prefrence.setLogin(true);

                    Prefrence.setName(loginModel.getData().getUserInfo().getName());
                    Prefrence.setEmail(loginModel.getData().getUserInfo().getEmail());
                    Prefrence.setProfileImage(loginModel.getData().getUserInfo().getProfileImage());
                    if (loginModel.getData().isIsAddressFound()) {
                        Prefrence.setCountryName(loginModel.getData().getUserAddress().getGCountry());
                        Prefrence.setCityName(loginModel.getData().getUserAddress().getGCity());
                        Prefrence.settahsil(loginModel.getData().getUserAddress().getGThasil());
                        Prefrence.setStateName(loginModel.getData().getUserAddress().getGState());
                    }
                    isLocation = loginModel.getData().isIsAddressFound();
                    setDataOnLocation(isLocation);
                }else {
                    Utils.showSnakBarDialog(this, lin_toplogin,
                            loginModel.getMessage(), R.color.alert);

                }
            }
            if (obj instanceof ChatIdModerl) {

                ChatIdModerl chatIdModerl = (ChatIdModerl) obj;
                if (chatIdModerl.isStatus()) {
                    Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
                    setDataOnLocation(isLocation);
                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

        Log.e("Sign in Error", t.getMessage());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_reporter:
                new AgreeExamDialog(LoginActivity.this, 2, LoginActivity.this).show();
                break;

            case R.id.but_user:
                new AgreeExamDialog(LoginActivity.this, 1, LoginActivity.this).show();
                break;
        }
    }

    private void createLogin() {
        String deviceType = "ANDROID";
        String deviceId = Prefrence.getDeviceId();
        String deviceToken = Prefrence.getDeviceToken();
        if (deviceToken != null && !deviceToken.equals("")) {
            rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
            rest.loginUser(name, email, deviceType, deviceId, deviceToken, image, prouserid, provider);
        } else {
            String token = getDevicetoken();
            if (!token.equals("")) {
                rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
                rest.loginUser(name, email, deviceType, deviceId, deviceToken, image, prouserid, provider);
            } else {
                Utils.showSnakBarDialog(this, lin_toplogin,
                        "We are not getting your device token. Please Reinstalled this application", R.color.alert);
            }
        }
        // rest.forgotPassword();
    }

    public String getDevicetoken() {

        /*FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {

        }).addOnFailureListener(e -> {

        }).addOnCanceledListener(() -> {

        }).addOnCompleteListener(task -> {
            String token = task.getResult();
            tokenSend = token;


        });*/
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.e("token=====", "", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        tokenSend = token;
                        Prefrence.setDeviceToken(token);
                    }
                });
        return tokenSend;
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            Prefrence.setFirstName(first_name);
                            Prefrence.setLastName(last_name);
                            String email1 = object.getString("email");
                            String id = object.getString("id");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                            name = first_name + " " + last_name;
                            email = email1;
                            prouserid = id;
                            image = image_url;
                            provider = "FACEBOOK";
                            createLogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context) {

        String deviceId;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            deviceId = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } else {
            final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephony.getDeviceId() != null) {
                deviceId = mTelephony.getDeviceId();
            } else {
                deviceId = Settings.Secure.getString(
                        context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }
        }

        return deviceId;
    }

    private void prepareUser(String email, String name, String imagde, boolean location, String userid) {

        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));

        QBUser qbUser = new QBUser();
        qbUser.setLogin(email);
        qbUser.setFullName(name);
        // qbUser.set(imagde);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userid", userid);
            jsonObject.put("image", imagde);
        } catch (JSONException e) {

        }
        qbUser.setCustomData(jsonObject.toString());
        qbUser.setPassword("quickblox");

       /* qbUser.setLogin("android");
        qbUser.setFullName("android");
        qbUser.setPassword("quickblox");*/

        signIn(qbUser);
    }

    private void signIn(final QBUser user) {

        ChatHelper.getInstance().login(user, new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser userFromRest, Bundle bundle) {
                if (userFromRest.getFullName() != null && userFromRest.getFullName().equals(user.getFullName())) {
                    loginToChat(user);
                } else {
                    //Need to set password NULL, because server will update user only with NULL password
                    user.setPassword(null);
                    updateUser(user);
                }
            }

            @Override
            public void onError(QBResponseException e) {
                if (e.getHttpStatusCode() == UNAUTHORIZED) {
                    signUp(user);
                } else {
                    Log.e("erroelogin====", e.toString());
                    //hideProgressDialog();
                    /*showErrorSnackbar(R.string.login_chat_login_error, e, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            signIn(user);
                        }
                    });*/
                }
            }
        });
    }

    private void updateUser(final QBUser user) {
        ChatHelper.getInstance().updateUser(user, new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle bundle) {
                loginToChat(user);
            }

            @Override
            public void onError(QBResponseException e) {
                Log.e("erroeupdateuser====", e.toString());
                // hideProgressDialog();
                // showErrorSnackbar(R.string.login_chat_login_error, e, null);
            }
        });
    }

    private void loginToChat(final QBUser user) {
        //Need to set password, because the server will not register to chat without password
        user.setPassword("quickblox");
        ChatHelper.getInstance().loginToChat(user, new QBEntityCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid, Bundle bundle) {
                SharedPrefsHelper.getInstance().saveQbUser(user);
                Log.e("loginuserdatae====", user.toString());
                if (SharedPrefsHelper.getInstance().hasQbUser()) {
                    // Toast.makeText(LoginActivity.this, "hasqbuser", Toast.LENGTH_SHORT).show();
                }
               /* if (!chbSave.isChecked()) {
                    clearDrafts();
                }*/
                QbUsersHolder.getInstance().putUser(user);
                //  DialogsActivity.start(LoginActivity.this);
                // finish();
                // hideProgressDialog();
                rest.dismissProgressdialog();
                if (!String.valueOf(user.getId()).equals("") && String.valueOf(user.getId()) != null) {
                    callSwrviceQuickbloxId(Prefrence.getUserId(), String.valueOf(user.getId()));
                } else {
                    Utils.showSnakBarDialog(LoginActivity.this, lin_toplogin, "Server error! Please try again after some time", R.color.alert);
                }
                //setDataOnLocation(isLocation);
            }

            @Override
            public void onError(QBResponseException e) {
                Log.e("erroelogintochat====", e.toString());
                Utils.showSnakBarDialog(LoginActivity.this, lin_toplogin, "Server error! Please try again after some time", R.color.alert);
                //  hideProgressDialog();
                // showErrorSnackbar(R.string.login_chat_login_error, e, null);
            }
        });
    }

    private void signUp(final QBUser newUser) {
        SharedPrefsHelper.getInstance().removeQbUser();
        QBUsers.signUp(newUser).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle bundle) {
                // hideProgressDialog();
                signIn(newUser);
            }

            @Override
            public void onError(QBResponseException e) {
                Log.e("erroesignup====", e.toString());
                //  hideProgressDialog();
                //showErrorSnackbar(R.string.login_sign_up_error, e, null);
            }
        });
    }

    protected void showErrorSnackbar(@StringRes int resId, Exception e, View.OnClickListener clickListener) {
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        if (rootView != null) {
            ErrorUtils.showSnackbar(rootView, resId, e,
                    R.string.dlg_retry, clickListener);
        }
    }

    private void callSwrviceQuickbloxId(String userId, String chatId) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.chatId(userId, chatId);
    }

    @Override
    public void onClickFacebook() {
        login_button.performClick();
    }

    @Override
    public void onClickGoogle() {
        signIn();
    }
}