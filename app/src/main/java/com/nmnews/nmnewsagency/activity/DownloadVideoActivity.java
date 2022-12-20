package com.nmnews.nmnewsagency.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadVideoActivity extends AppCompatActivity implements Callback<Object> {
    WebView simpleWebView;
    String urls;
    String name;
    Rest rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_video);
        rest=new Rest(this,this);
        rest.ShowDialogue(getResources().getString(R.string.pleaseWaitForDownloading));
        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey("url")) {
            urls = b.getString("url");
            name = b.getString("name");
        }

        simpleWebView = findViewById(R.id.simpleWebView);
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.getSettings().setLoadWithOverviewMode(true);
        simpleWebView.getSettings().setUseWideViewPort(true);
        simpleWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                Log.e("Error Url", url);
            }
        });

        simpleWebView.loadUrl(urls);

        simpleWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
               /* Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);*/
                //Utils.startDownload(url,DownloadVideoActivity.this,name);
                rest.dismissProgressdialog();
                finish();
            }
        });

    }


    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {

    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }
}