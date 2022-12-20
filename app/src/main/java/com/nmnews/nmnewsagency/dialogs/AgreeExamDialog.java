package com.nmnews.nmnewsagency.dialogs;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.PrivacyTermsActivity;
import com.nmnews.nmnewsagency.activity.SettingActivity;
import com.nmnews.nmnewsagency.activity.TermsAndConditionsActivity;
import com.nmnews.nmnewsagency.interfaces.LoginClickInterface;
import com.nmnews.nmnewsagency.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vishnu Saini .
 * vishnusainideveloper27@gmail.com
 */

public class AgreeExamDialog extends Dialog implements View.OnClickListener, Callback<Object> {

    private Context context;
    private CheckBox check_agreeTerms;
    private TextView tv_termsConditions;
    private TextView tv_cancel;
    private TextView tv_agree;
    private int clickId;
    private LoginClickInterface loginClickInterface;


    public AgreeExamDialog(Context context, int clickId, LoginClickInterface loginClickInterface) {
        super(context);
        this.context = context;
        this.clickId = clickId;
        this.loginClickInterface = loginClickInterface;

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
        setTitle(null);
        // setCancelable(false);
        setCanceledOnTouchOutside(true);

        check_agreeTerms = layout.findViewById(R.id.check_agreeTerms);
        tv_termsConditions = layout.findViewById(R.id.tv_termsConditions);
        tv_cancel = layout.findViewById(R.id.tv_cancel);
        tv_agree = layout.findViewById(R.id.tv_agree);

        tv_cancel.setOnClickListener(this);
        tv_agree.setOnClickListener(this);
        tv_termsConditions.setOnClickListener(this);

        Spannable wordtoSpan = new SpannableString(tv_termsConditions.getText().toString());

        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 16, 34, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_termsConditions.setText(wordtoSpan);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_cancel:
                dismiss();
                break;

            case R.id.tv_termsConditions:
                dismiss();
               /* Intent i = new Intent(context, TermsAndConditionsActivity.class);
                context.startActivity(i);*/
                Intent intent1 = new Intent(context, PrivacyTermsActivity.class);
                intent1.putExtra("pageId", 1);
                context.startActivity(intent1);
                break;

            case R.id.tv_agree:
                if (check_agreeTerms.isChecked()) {
                    dismiss();
                    if (clickId == 1) {
                        loginClickInterface.onClickFacebook();
                    } else {
                        loginClickInterface.onClickGoogle();
                    }

                } else {
                    Utils.showToast(context, context.getResources().getString(R.string.please_agree_with_terms_Ans_condiotions));
                }
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

