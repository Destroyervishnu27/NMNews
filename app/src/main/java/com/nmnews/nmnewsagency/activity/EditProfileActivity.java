package com.nmnews.nmnewsagency.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.modelclass.UpdateProfileModel;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.FileUtilsss;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity implements Callback<Object> {
    ImageView iamge_back_editprofile, img_editprofile;
    TextView txt_editprofile_save;
    String image, frstanem, lastname, aboutme;
    TextInputEditText edit_aboutme, edit_surname, edit_frstname;
    private static final int IMAGE_PICKER_SELECT = 111;
    boolean changeProfile = false, setImageAvtar = false;
    String sendFile;
    Rest rest;
    Animation myAnim;
    LinearLayout lin_full;
    final int CROP_PIC = 2;
    private static final String SAMPLE_CROPPED_IMAGE_NAME = "SampleCropImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_edit_profile);
        rest = new Rest(this, this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            image = bundle.getString("profileimage");
            frstanem = bundle.getString("firstname");
            lastname = bundle.getString("lastname");
            aboutme = bundle.getString("aboutme");
        }
        inIt();

    }

    private void inIt() {
        myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        edit_frstname = (TextInputEditText) findViewById(R.id.edit_frstname);
        edit_surname = (TextInputEditText) findViewById(R.id.edit_surname);
        edit_aboutme = (TextInputEditText) findViewById(R.id.edit_aboutme);
        iamge_back_editprofile = (ImageView) findViewById(R.id.iamge_back_editprofile);
        img_editprofile = (ImageView) findViewById(R.id.img_editprofile);
        txt_editprofile_save = (TextView) findViewById(R.id.txt_editprofile_save);
        lin_full = (LinearLayout) findViewById(R.id.lin_full);
        txt_editprofile_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_editprofile_save.startAnimation(myAnim);
                String fname = edit_frstname.getText().toString();
                String lname = edit_surname.getText().toString();
                if (fname.equals("")) {
                    edit_frstname.setError("Please enter First Name");
                    return;
                }
                if (lname.equals("")) {
                   // edit_surname.setError("Please enter Username");
                   // return;
                }
                if (fname.concat(lname).length() > 20) {

                    Snackbar.make(EditProfileActivity.this, lin_full, fname.concat(lname).length() + "Please enter First and Last name below 20 charactor ..",
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (changeProfile) {
                    callServiceEditProfiel(fname, lname, edit_aboutme.getText().toString(), sendFile, image);
                } else {
                    callServiceEditProfiel(fname, lname, edit_aboutme.getText().toString(), "", image);
                }
            }
        });
        iamge_back_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileActivity.this.finish();
            }
        });
        img_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromAlbum();
            }
        });
        setDataOnViews();
    }

    private void callServiceEditProfiel(String fname, String lname, String toString, String sendFile, String image) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.updateProfile(fname, lname, toString, sendFile, image);
    }

    private void getImageFromAlbum() {

        try {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, IMAGE_PICKER_SELECT);
        } catch (Exception exp) {
            Log.i("Error", exp.toString());
        }
    }

    public void setDataOnViews() {
        edit_frstname.setText(frstanem);
        edit_surname.setText(lastname);
        if (aboutme != null) {
            edit_aboutme.setText((String) aboutme);
        }
        if (image != null) {
            if (!image.equals("null")) {
                setImageAvtar = true;
                new Handler().post(new Runnable() {
                    public void run() {
                        Glide.with(EditProfileActivity.this)
                                .load(image)
                                .into(img_editprofile);
                    }
                });
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICKER_SELECT) {
                final Uri imageUri = data.getData();
                performCrop(imageUri);
                /*sendFile = FileUtilsss.getRealPath(this, imageUri);
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Glide.with(EditProfileActivity.this)
                        .asBitmap()
                        .load(selectedImage)
                        .into(img_editprofile);*/
                //  img_editprofile.setImageBitmap(selectedImage);
                changeProfile = true;
            } else if (requestCode == CROP_PIC) {
                // get the returned data
                final Uri imageUri1 = data.getData();
                sendFile = FileUtilsss.getRealPath(this, imageUri1);
                final InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(imageUri1);

                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Glide.with(getApplicationContext())
                        .asBitmap()
                        .load(selectedImage)
                        .into(img_editprofile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if (requestCode == UCrop.REQUEST_CROP) {
                handleCropResult(data);
            }
        }


    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof UpdateProfileModel) {
                UpdateProfileModel loginModel = (UpdateProfileModel) obj;
                if (loginModel.isStatus()) {
                    Toast.makeText(this, "Profile successfully updated", Toast.LENGTH_SHORT).show();
                    EditProfileActivity.this.finish();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }

    private void performCrop(Uri imageUri) {
        // take care of exceptions
        String destinationFileName = SAMPLE_CROPPED_IMAGE_NAME;
        destinationFileName += ".png";
        UCrop.of(imageUri, Uri.fromFile(new File(getCacheDir(), destinationFileName)))
                .withAspectRatio(12, 9)
                .withMaxResultSize(256, 256)
                .start(EditProfileActivity.this);
       /* try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(imageUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 2);
            cropIntent.putExtra("aspectY", 2);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, CROP_PIC);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }*/
    }
    private void handleCropResult(@NonNull Intent result) {
        final Uri resultUri = UCrop.getOutput(result);
        if (resultUri != null) {
            sendFile = FileUtilsss.getRealPath(this, resultUri);
            final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(resultUri);

                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Glide.with(EditProfileActivity.this)
                        .asBitmap()
                        .load(selectedImage)
                        .into(img_editprofile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
           // Toast.makeText(SampleActivity.this, R.string.toast_cannot_retrieve_cropped_image, Toast.LENGTH_SHORT).show();
        }
    }

   /* @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    private void handleCropError(@NonNull Intent result) {
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            Log.e(TAG, "handleCropError: ", cropError);
            Toast.makeText(SampleActivity.this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SampleActivity.this, R.string.toast_unexpected_error, Toast.LENGTH_SHORT).show();
        }
    }*/

}