package com.nmnews.nmnewsagency.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.modelclass.AddUserDocumentModel;
import com.nmnews.nmnewsagency.modelclass.GetDocumentModel;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.ExpandableTextView;
import com.nmnews.nmnewsagency.utils.FileUtilsss;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadDocumentActivity extends AppCompatActivity implements View.OnClickListener,
        Callback<Object> {
    ImageView iamge_back_uploaddoc, img_bank, img_pancard, img_presid;
    static int count = 0;
    int REQUEST_GET_SINGLE_FILE = 99;
    LinearLayout lin_bank, lin_pancard, lin_presid;
    String cliocl, sendFile, type;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    Rest rest;
    List<GetDocumentModel.DataBean> getdocModel;
    ExpandableTextView expandableTextView,expan_txt1,expan_txt2;
    String yourText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Ut volutpat interdum interdum. Nulla laoreet lacus diam, vitae " +
            "sodales sapien commodo faucibus. Vestibulum et feugiat enim. Donec " +
            "semper mi et euismod tempor. Sed sodales eleifend mi id varius. Nam " +
            "et ornare enim, sit amet gravida sapien. Quisque gravida et enim vel " +
            "volutpat. Vivamus egestas ut felis a blandit. Vivamus fringilla " +
            "dignissim mollis. Maecenas imperdiet interdum hendrerit. Aliquam" +
            " dictum hendrerit ultrices. Ut vitae vestibulum dolor. Donec auctor ante" +
            " eget libero molestie porta. Nam tempor fringilla ultricies. Nam sem " +
            "lectus, feugiat eget ullamcorper vitae, ornare et sem. Fusce dapibus ipsum" +
            " sed laoreet suscipit. ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_upload_document);
        rest = new Rest(this, this);
        INIT();
        callservicegetDocument();
    }

    private void INIT() {
        iamge_back_uploaddoc = (ImageView) findViewById(R.id.iamge_back_uploaddoc);
        expandableTextView = findViewById(R.id.expan_txt);
        expan_txt1 = findViewById(R.id.expan_txt1);
        expan_txt2 = findViewById(R.id.expan_txt2);
        img_presid = (ImageView) findViewById(R.id.img_presid);
        img_pancard = (ImageView) findViewById(R.id.img_pancard);
        img_bank = (ImageView) findViewById(R.id.img_bank);
        lin_presid = (LinearLayout) findViewById(R.id.lin_presid);
        lin_bank = (LinearLayout) findViewById(R.id.lin_bank);
        lin_pancard = (LinearLayout) findViewById(R.id.lin_pancard);

        iamge_back_uploaddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadDocumentActivity.this.finish();
            }
        });
        lin_bank.setOnClickListener(this);
        lin_pancard.setOnClickListener(this);
        lin_presid.setOnClickListener(this);

    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GET_SINGLE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_GET_SINGLE_FILE) {
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        sendFile = FileUtilsss.getRealPath(this, selectedImageUri);

                        callserviceUploadDoc(sendFile);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }

    private void callserviceUploadDoc(String sendFile) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.addUserDocument(cliocl, sendFile);
    }

    private void callservicegetDocument() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getDocument();
    }

    private void changeImageBagroundfile() {
        if (cliocl.equals("PRESSIDCARD")) {
            changebagroundimagebycondition(img_presid);
        } else if (cliocl.equals("BANKPASSBOOK")) {
            changebagroundimagebycondition(img_bank);
        } else if (cliocl.equals("PANCARD")) {
            changebagroundimagebycondition(img_pancard);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_presid:
                cliocl = "PRESSIDCARD";
                openGallery();
                break;
            case R.id.lin_bank:
                cliocl = "BANKPASSBOOK";
                openGallery();
                break;
            case R.id.lin_pancard:
                cliocl = "PANCARD";
                openGallery();
                break;
        }
    }

    public void changebagroundimagebycondition(View view) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable
                    (ContextCompat.getDrawable(UploadDocumentActivity.this, R.drawable.ic_tick_square));
        } else {
            view.setBackground
                    (ContextCompat.getDrawable(UploadDocumentActivity.this, R.drawable.ic_tick_square));
        }

    }

    public void changebagroundimagebycondition1() {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            img_presid.setBackgroundDrawable
                    (ContextCompat.getDrawable(UploadDocumentActivity.this, R.drawable.ic_tick_square));
            img_bank.setBackgroundDrawable
                    (ContextCompat.getDrawable(UploadDocumentActivity.this, R.drawable.ic_tick_square));
            img_pancard.setBackgroundDrawable
                    (ContextCompat.getDrawable(UploadDocumentActivity.this, R.drawable.ic_tick_square));
        } else {
            img_presid.setBackground
                    (ContextCompat.getDrawable(UploadDocumentActivity.this, R.drawable.ic_tick_square));
            img_bank.setBackground
                    (ContextCompat.getDrawable(UploadDocumentActivity.this, R.drawable.ic_tick_square));
            img_pancard.setBackground
                    (ContextCompat.getDrawable(UploadDocumentActivity.this, R.drawable.ic_tick_square));
        }

    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof GetDocumentModel) {
                GetDocumentModel loginModel = (GetDocumentModel) obj;
                if (loginModel.isStatus()) {
                    getdocModel = loginModel.getData();
                    setDocumentDataBaseonResponce();
                }
            }
            if (obj instanceof AddUserDocumentModel) {
                AddUserDocumentModel loginModel = (AddUserDocumentModel) obj;
                if (loginModel.isStatus()) {
                    Toast.makeText(this, "Document Upload Successfully", Toast.LENGTH_SHORT).show();
                    changeImageBagroundfile();
                }
            }

        }
    }

    private void setDocumentDataBaseonResponce() {
        int i1;
        if (getdocModel.size() > 0) {
            for (i1 = 0; i1 < getdocModel.size(); i1++) {
                if (getdocModel.get(i1).getDocumentType().equals("PRESSIDCARD")) {
                    changebagroundimagebycondition(img_presid);
                } else if (getdocModel.get(i1).getDocumentType().equals("BANKPASSBOOK")) {
                    changebagroundimagebycondition(img_bank);
                } else if (getdocModel.get(i1).getDocumentType().equals("PANCARD")) {
                    changebagroundimagebycondition(img_pancard);
                }
               // expandableTextView.setText(getdocModel.get(i1).getComment());
                if(i1==0) {
                    expandableTextView.setText(getdocModel.get(i1).getComment());
                }else if(i1==1) {
                    expandableTextView.setText(getdocModel.get(i1).getComment());
                } else if(i1==2) {
                    expandableTextView.setText(getdocModel.get(i1).getComment());
                }
            }
        }


    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }
}