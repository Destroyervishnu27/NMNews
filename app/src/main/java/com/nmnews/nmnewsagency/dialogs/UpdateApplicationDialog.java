package com.nmnews.nmnewsagency.dialogs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nmnews.nmnewsagency.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vishnu Saini .
 * vishnusainideveloper27@gmail.com
 */

public class UpdateApplicationDialog extends BottomSheetDialog implements View.OnClickListener, Callback<Object> {

    Context context;
    private Button btn_cancle;
    private Button btn_ok;

    private RelativeLayout rel_update;
    private RelativeLayout rel_notNow;



    public UpdateApplicationDialog(Context context) {
        super(context);
        this.context = context;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.color.transprent);
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflate.inflate(R.layout.dialog_update_application, null);
        setContentView(layout);
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.windowAnimations = R.style.dialogue_error;
       // wlmp.gravity = Gravity.CENTER;
     //   wlmp.dimAmount = 0.8f;
        wlmp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wlmp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        setTitle(null);




        btn_cancle = (Button) layout.findViewById(R.id.btn_cancle);
        btn_ok = (Button) layout.findViewById(R.id.btn_ok);
        rel_update = (RelativeLayout) layout.findViewById(R.id.rel_update);
        rel_notNow = (RelativeLayout) layout.findViewById(R.id.rel_notNow);

        btn_cancle.setOnClickListener(this);
        btn_ok.setOnClickListener(this);

        /*if (data.getIsForcefullyUpdate().equalsIgnoreCase("Yes"))
        {
            setCancelable(false);
            setCanceledOnTouchOutside(false);
            setOnCancelListener(null);
            rel_notNow.setVisibility(View.GONE);
        }*/
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_cancle:
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(context.getResources().getString(R.string.playstore_url))));

                dismiss();
                break;

            case R.id.btn_ok:
                dismiss();
                break;


            default:
                break;
        }

    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {

    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {

    }
}

