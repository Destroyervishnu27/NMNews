package com.nmnews.nmnewsagency.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vishnu Saini .
 * vishnusainideveloper27@gmail.com
 */

public class DownloadStartDialog extends Dialog implements View.OnClickListener, Callback<Object> {

    private Context context;

    WebView simpleWebView;
    String urls;
    private String downloadLink;

    public DownloadStartDialog(Context context, String downloadLink, String urls) {
        super(context);
        this.context = context;
        this.urls=urls;
        this.downloadLink=downloadLink;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.color.transprent);
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflate.inflate(R.layout.dialog_agree_exam, null);
        setContentView(layout);
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.windowAnimations = R.style.dialogue_error;
        wlmp.gravity = Gravity.CENTER;
        wlmp.dimAmount = 0.8f;
        wlmp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wlmp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        setTitle(null);
        // setCancelable(false);
        setCanceledOnTouchOutside(true);

        simpleWebView=layout.findViewById(R.id.simpleWebView);
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
              //  Utils.startDownload(url, context,downloadLink);

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {



            default:
                break;
        }

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
    public void onResponse(Call<Object> call, Response<Object> response) {

    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {

    }
}

