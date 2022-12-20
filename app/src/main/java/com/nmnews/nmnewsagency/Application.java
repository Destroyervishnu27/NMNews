package com.nmnews.nmnewsagency;

import com.nmnews.nmnewsagency.extras.Constants;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.quickblox.auth.session.QBSettings;
import com.quickblox.chat.QBChatService;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

public class Application extends android.app.Application {
    private static Application instance;
    @Override
    public void onCreate() {
        super.onCreate();
        Prefrence.init(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        initApplication();
    }

    public static Application getInstance() {
        return instance;
    }
    private void initApplication() {
        instance = this;
        QBSettings.getInstance().init(getApplicationContext(), Constants.APPLICATION_ID, Constants.AUTH_KEY, Constants.AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(Constants.ACCOUNT_KEY);
        QBChatService.getInstance().setReconnectionAllowed(true);

        try {
            // Google Play will install latest OpenSSL
            ProviderInstaller.installIfNeeded(getApplicationContext());
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException
                | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
