package com.nmnews.nmnewsagency.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.nmnews.nmnewsagency.R;

public class TermsAndConditionsActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView simpleWebView;
    private ImageView iamge_back_terms;

    private String termsAndCondition = "https://nmnews.in/terms-conditions";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        initView();
    }

    private void initView() {
        iamge_back_terms = (ImageView) findViewById(R.id.iamge_back_terms);
        simpleWebView = (WebView) findViewById(R.id.simpleWebView);

        iamge_back_terms.setOnClickListener(this);

        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.setWebChromeClient(new WebChromeClient());

        simpleWebView.setWebViewClient(new MyWebViewClient());
        simpleWebView.loadUrl(termsAndCondition);
        simpleWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                  Toast.makeText(TermsAndConditionsActivity.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("Payment Url:: ", url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                Log.e("Payment Url:: ", url);


            }
        });



    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url2) {
            view.loadUrl(url2);
            //  Toast.makeText(getApplicationContext(),url2,Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iamge_back_terms:
                onBackPressed();
                break;

            default:
                break;
        }
    }
}