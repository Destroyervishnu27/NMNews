package com.nmnews.nmnewsagency.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.AutoCompleteSearchBarAdapter;
import com.nmnews.nmnewsagency.adapter.AutoCompleteSearchBarAdapter1;
import com.nmnews.nmnewsagency.adapter.SearchTopBarHashtagAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.AddNewsModel;
import com.nmnews.nmnewsagency.modelclass.NewVideoHashtagModelClass;
import com.nmnews.nmnewsagency.modelclass.NewVideoMentionModelClass;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestAddNews;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.Utils;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vimeoextractor.OnVimeoExtractionListener;
import vimeoextractor.VimeoExtractor;
import vimeoextractor.VimeoVideo;

public class NewVideoActivity extends AppCompatActivity implements TextWatcher, Callback<Object> {
    LinearLayout lin_brekingnews, lin_showlayout, lin_toplin;
    ImageView iamge_back_newvideo, img_newvideo_url;
    int count = 0,sendCountryId,sendStateId,sendCityId,sendTahsilId;
    TextView txt_newvideo_submit, txt_remainin, txt_userid, txt_chooselocation;
    MultiAutoCompleteTextView autoCompleteTextView;
    AutoCompleteSearchBarAdapter autoCompleteSearchBarAdapter;
    AutoCompleteSearchBarAdapter1 autoCompleteSearchBarAdapter1;
    SearchTopBarHashtagAdapter topBarHashtagAdapter;
    List<NewVideoHashtagModelClass.DataBean.PagedRecordBean> arrayHashTag;
    List<NewVideoMentionModelClass.DataBean.PagedRecordBean> arrayMention;
    JSONObject newstypeList;
    RecyclerView recy_newVideo;
    RelativeLayout lin_attherate, lin_hashtag, rel_addvoice;
    Rest rest;
    private UploadNewsModel.DataBean dataBean = null;
    private RequestAddNews.NewsObjBean newsObjBean = new RequestAddNews.NewsObjBean();
    private boolean isBreakingNews = false;
    CheckBox radio_frst, radio_second, radio_thrd, radio_fourth;
    FrameLayout frame_video_open;
    boolean notCall, recycleClick = false, which, backspace = false, space = false, chooseLoaction = false;
    static int index;
    String thumbanilVimeo, hashTag = "", mention = "",sendCountry,sendState,sendCity,sendTahsil;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    String[] adressArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_new_video);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dataBean = (UploadNewsModel.DataBean) bundle.get("video");
            if(dataBean!=null)
            {
                Log.e("datavideo==", String.valueOf(dataBean.toString()));
            }


        }
        rest = new Rest(NewVideoActivity.this, this);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.api_key), Locale.US);
        }
        iniiT();
    }

    private void iniiT() {
        arrayHashTag = new ArrayList<>();
        arrayMention = new ArrayList<>();
        newstypeList = new JSONObject();
        frame_video_open = (FrameLayout) findViewById(R.id.frame_video_open);
        radio_frst = (CheckBox) findViewById(R.id.radio_frst);
        radio_second = (CheckBox) findViewById(R.id.radio_second);
        radio_thrd = (CheckBox) findViewById(R.id.radio_thrd);
        radio_fourth = (CheckBox) findViewById(R.id.radio_fourth);
        // radio_frst.setChecked(true);
        lin_brekingnews = (LinearLayout) findViewById(R.id.lin_brekingnews);
        lin_showlayout = (LinearLayout) findViewById(R.id.lin_showlayout);
        lin_toplin = (LinearLayout) findViewById(R.id.lin_toplin);
        lin_hashtag = (RelativeLayout) findViewById(R.id.lin_hashtag);
        rel_addvoice = (RelativeLayout) findViewById(R.id.rel_addvoice);
        lin_attherate = (RelativeLayout) findViewById(R.id.lin_attherate);
        iamge_back_newvideo = (ImageView) findViewById(R.id.iamge_back_newvideo);
        img_newvideo_url = (ImageView) findViewById(R.id.img_newvideo_url);
        txt_newvideo_submit = (TextView) findViewById(R.id.txt_newvideo_submit);
        txt_remainin = (TextView) findViewById(R.id.txt_remainin);
        txt_userid = (TextView) findViewById(R.id.txt_userid);
        txt_chooselocation = (TextView) findViewById(R.id.txt_chooselocation);
        autoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(3);
        autoCompleteTextView.addTextChangedListener(this);
        recy_newVideo = (RecyclerView) findViewById(R.id.recy_newVideo);
        iamge_back_newvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewVideoActivity.this.finish();
            }
        });
        txt_newvideo_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callServiceddNews();
            }

        });
        lin_brekingnews.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    lin_brekingnews.setBackground(NewVideoActivity.this.getResources().getDrawable(R.drawable.newvideo1));
                    newsObjBean.setIsBreakingNews(true);
                    count = 1;
                } else {
                    lin_brekingnews.setBackground(NewVideoActivity.this.getResources().getDrawable(R.drawable.newvideo));
                    newsObjBean.setIsBreakingNews(false);
                    count = 0;
                }
            }
        });
        lin_attherate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.setText(autoCompleteTextView.getText().toString() + "@");
                autoCompleteTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        autoCompleteTextView.setSelection(autoCompleteTextView.getText().toString().length());
                    }
                });
            }
        });
        lin_hashtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.setText(autoCompleteTextView.getText().toString() + "#");
                autoCompleteTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        autoCompleteTextView.setSelection(autoCompleteTextView.getText().toString().length());
                    }
                });
            }
        });

        txt_userid.setText(Utils.getUserIdfromdata());
        if (dataBean !=null && dataBean.getImageUrl()!=null)
        {
            if (dataBean.getImageUrl().equals("")) {
                getThumbnail(dataBean.getVideoId());
            } else {
                new ImageLoadTask(dataBean.getImageUrl(), img_newvideo_url).execute();
            }
        }

        frame_video_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewVideoActivity.this, RecordingPreviewActivity.class);
                intent.putExtra("videopath", dataBean.getVideoUrl());
                startActivity(intent);
            }
        });
        rel_addvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewVideoActivity.this, AudioRecordPreviews.class);
                intent.putExtra("video", dataBean);
                startActivityForResult(intent, 999);
            }
        });
        autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    backspace = true;
                }
                if (keyCode == KeyEvent.KEYCODE_SPACE) {
                    space = true;
                }
                return false;
            }
        });
        txt_chooselocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS_COMPONENTS, Place.Field.NAME, Place.Field.LAT_LNG,
                        Place.Field.ADDRESS);

                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(NewVideoActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);*/
                Intent intent = new Intent(NewVideoActivity.this, ChooseLocationActivity.class);
                startActivityForResult(intent, 888);


            }
        });
    }

    private void nextActivityCall() {
        Intent i = new Intent(NewVideoActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }


    private void callServiceddNews() {

        int newsType = 0;
        String newsTypeSend = "";
        // newsObjBean.setNewsType(1);
        if (radio_frst.isChecked()) {
            newsTypeSend = "1";
            newsType = 1;
        }
        if (radio_second.isChecked()) {
            if (!newsTypeSend.equals("")) {
                newsTypeSend = newsTypeSend + "," + "2";
            } else {
                newsTypeSend = "2";
            }
            newsType = 2;
        }
        if (radio_thrd.isChecked()) {
            if (!newsTypeSend.equals("")) {
                newsTypeSend = newsTypeSend + "," + "3";
            } else {
                newsTypeSend = "3";
            }
            newsType = 3;
        }
        if (radio_fourth.isChecked()) {
            if (!newsTypeSend.equals("")) {
                newsTypeSend = newsTypeSend + "," + "4";
            } else {
                newsTypeSend = "4";
            }
            newsType = 4;
        }
        if (!newsTypeSend.equals("")) {
            newsObjBean.setNewsType(newsTypeSend);
        }
        String val = "" + autoCompleteTextView.getText();
        if (!val.equals("") && newsType > 0) {
            newsObjBean.setTitle(String.valueOf(autoCompleteTextView.getText()));
            newsObjBean.setUserId(Prefrence.getUserId());
            newsObjBean.setZipCode("");
            newsObjBean.setSuggestion("News Updated");
            if(chooseLoaction){
                newsObjBean.setTahsilId(sendTahsilId);
                newsObjBean.setStateId(sendStateId);
                newsObjBean.setCountryId(sendCountryId);
                newsObjBean.setCityId(sendCityId);

                newsObjBean.setTahsil_Name(sendTahsil);
                newsObjBean.setCountry_Name(sendCountry);
                newsObjBean.setState_Name(sendState);
                newsObjBean.setCity_Name(sendCity);
            }
            else {
                if (!Prefrence.gettahsilIdd().equals("")) {
                    newsObjBean.setTahsilId(Integer.parseInt(Prefrence.gettahsilIdd()));
                    newsObjBean.setStateId(Integer.parseInt(Prefrence.getStateIdd()));
                    newsObjBean.setCountryId(Integer.parseInt(Prefrence.getCountryIdd()));
                    newsObjBean.setCityId(Integer.parseInt(Prefrence.getCityIdd()));
                } else {
                    newsObjBean.setTahsilId(0);
                    newsObjBean.setStateId(0);
                    newsObjBean.setCountryId(0);
                    newsObjBean.setCityId(0);
                }

                if (Prefrence.gettahsil().isEmpty()) {
                    Utils.showSnakBarDialog(this, lin_toplin, "Please select your location in choose location", R.color.alert);
                } else {
                    newsObjBean.setTahsil_Name(Prefrence.gettahsil());
                }
                if (Prefrence.getStateName().isEmpty()) {
                    Utils.showSnakBarDialog(this, lin_toplin, "Please select your location in choose location", R.color.alert);
                } else {
                    newsObjBean.setState_Name(Prefrence.getStateName());
                }
                if (Prefrence.getCountryName().isEmpty()) {
                    Utils.showSnakBarDialog(this, lin_toplin, "Please select your location in choose location", R.color.alert);
                } else {
                    newsObjBean.setCountry_Name(Prefrence.getCountryName());
                }
                if (Prefrence.getCityName().isEmpty()) {
                    Utils.showSnakBarDialog(this, lin_toplin, "Please select your location in choose location", R.color.alert);
                } else {
                    newsObjBean.setCity_Name(Prefrence.getCityName());
                }
            }
           /* if(adressArray.length==3){
                Toast.makeText(this, adressArray[0]+""+adressArray[1]+""+adressArray[2], Toast.LENGTH_SHORT).show();
                newsObjBean.setTahsil_Name("");
                newsObjBean.setCity_Name(adressArray[0]);
                newsObjBean.setState_Name(adressArray[1]);
                newsObjBean.setCountry_Name(adressArray[2]);
            }else if(adressArray.length==2){
                Toast.makeText(this, adressArray[0]+""+adressArray[1], Toast.LENGTH_SHORT).show();
                newsObjBean.setTahsil_Name("");
                newsObjBean.setCity_Name("");
                newsObjBean.setState_Name(adressArray[0]);
                newsObjBean.setCountry_Name(adressArray[1]);
            }else if(adressArray.length==4){
                Toast.makeText(this, adressArray[0]+""+adressArray[1]+""+adressArray[2]+""+adressArray[3], Toast.LENGTH_SHORT).show();
                newsObjBean.setTahsil_Name(adressArray[0]);
                newsObjBean.setCity_Name(adressArray[1]);
                newsObjBean.setState_Name(adressArray[2]);
                newsObjBean.setCountry_Name(adressArray[3]);
            }*/


            newsObjBean.setHashTags(hashTag);
            newsObjBean.setUserTags(mention);
            newsObjBean.setLong(0.0);
            newsObjBean.setLat(0.0);
            newsObjBean.setId(0);
            newsObjBean.setDescription("News");
            newsObjBean.setAddressLine_1(Prefrence.getCityName());
            newsObjBean.setAddressLin_2("");
            if (dataBean.getImageUrl().equals("")) {
                dataBean.setImageUrl(thumbanilVimeo);
            }
            rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
            rest.addNews(dataBean, newsObjBean);
        } else if (val.equals("")) {
            autoCompleteTextView.setError("Please enter a proper heading");
        } else if (newsType == 0) {
            // Toast.makeText(this, "Please enter a post type", Toast.LENGTH_SHORT).show();
            Utils.showSnakBarDialog(this, lin_toplin, "Please enter a post type", R.color.alert);
        }


    }

    public void callServiceMention(String query) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.menTion(query);
    }

    public void callServiceHashtag(String query) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.HashTag(query);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!notCall) {
            if (!autoCompleteTextView.getText().toString().equals("")) {
                char first = autoCompleteTextView.getText().toString().charAt(0);
                int adtheate = Character.compare(first, '@');
                int hashtag = Character.compare(first, '#');
                if (adtheate == 0) {
                    callServiceMention(autoCompleteTextView.getText().toString());

                } else if (hashtag == 0) {
                    callServiceHashtag(autoCompleteTextView.getText().toString());

                } else if (reversearray(autoCompleteTextView.getText().toString()).equals("#")) {
                    String substr = autoCompleteTextView.getText().toString().
                            substring(index, autoCompleteTextView.getText().toString().length());
                    if (!substr.equals("")) {
                        callServiceHashtag(substr);
                    } else {
                        return;
                    }
                } else if (reversearray(autoCompleteTextView.getText().toString()).equals("@")) {
                    String substr = autoCompleteTextView.getText().toString().
                            substring(index, autoCompleteTextView.getText().toString().length());
                    if (!substr.equals("")) {
                        callServiceMention(substr);
                    } else {
                        return;
                    }
                }
            }
        } else {
            recy_newVideo.setVisibility(View.GONE);
            lin_showlayout.setVisibility(View.VISIBLE);
            notCall = false;
        }

    }

    public static String reverse(String input) {
        StringBuilder input1 = new StringBuilder();
        // append a string into StringBuilder input1
        input1.append(input);
        // reverse StringBuilder input1
        input1.reverse();
        return new String(input1);
    }

    public static String reversearray(String input) {
        // convert String to character array
        // by using toCharArray
        String send = "";
        char[] try1 = input.toCharArray();

        for (index = try1.length - 1; index >= 0; index--) {
            System.out.print(try1[index]);
            if (try1[index] == '@') {
                send = "@";
                break;
            } else if (try1[index] == '#') {
                send = "#";
                break;
            }
        }

        return send;
    }

    public void afterServiceCallHashTag() {
        recy_newVideo.setVisibility(View.VISIBLE);
        lin_showlayout.setVisibility(View.GONE);
    }

    @Override
    public void afterTextChanged(Editable s) {
        //Toast.makeText(NewVideoActivity.this, "afterTextChanged", Toast.LENGTH_SHORT).show();
        if (autoCompleteTextView.getText().toString().equals("")) {
            recy_newVideo.setVisibility(View.GONE);
            lin_showlayout.setVisibility(View.VISIBLE);
        }
        int charLength = autoCompleteTextView.getText().toString().length();
        if (charLength <= 120) {
            txt_remainin.setText("Remaining : " + String.valueOf(120 - charLength));
        } else if (charLength > 120) {
            //autoCompleteTextView.setEnabled(false);
            // autoCompleteTextView.setText(autoCompleteTextView.getText().toString()+"");

        }
    }


    private void inItItemRecycle(String type) {

        afterServiceCallHashTag();
        Log.e("type==", type);
        if (type.equals("hashtag")) {
            which = true;
            Log.e("whisch==", "" + which);
            autoCompleteSearchBarAdapter = new AutoCompleteSearchBarAdapter(NewVideoActivity.this, arrayHashTag);
            recy_newVideo.setAdapter(autoCompleteSearchBarAdapter);
        } else {
            which = false;
            Log.e("whisch==", "" + which);
            autoCompleteSearchBarAdapter1 = new AutoCompleteSearchBarAdapter1(NewVideoActivity.this, arrayMention);
            recy_newVideo.setAdapter(autoCompleteSearchBarAdapter1);
        }

        recy_newVideo.setLayoutManager(new LinearLayoutManager(NewVideoActivity.this));
        recy_newVideo.setItemAnimator(new DefaultItemAnimator());
        recy_newVideo.addOnItemTouchListener(new RecyclerTouchListener(NewVideoActivity.this, recy_newVideo, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (!recycleClick) {
                    notCall = true;
                    if (which) {
                        Log.e("whichclick==", "" + which);
                        Log.e("positionhashtag==", String.valueOf(position));
                        //Log.e("hashtag==",arrayHashTag.get(position).getTitle());
                        if (autoCompleteTextView.getText().toString()!=null &&!autoCompleteTextView.getText().toString().equalsIgnoreCase(""))
                        {
                            String newStr = autoCompleteTextView.getText().toString().substring(0, index) + ""
                                    + arrayHashTag.get(position).getTitle() + " ";
                            autoCompleteTextView.setText(newStr);
                        }

                        if (hashTag.isEmpty()) {
                            hashTag = arrayHashTag.get(position).getTitle();
                        } else {
                            hashTag = hashTag + "," + arrayHashTag.get(position).getTitle();
                        }
                    } else {
                        //   Log.e("lasttesrt==",autoCompleteTextView.getText().toString());
                        //   Log.e("index====",String.valueOf(index));
                        //  Log.e("araymension==",String.valueOf(arrayMention.size()));
                        Log.e("whichclick==", "" + which);
                        Log.e("positionmension==", String.valueOf(position));
                        // Log.e("mension==",arrayMention.get(position).getTitle());
                        String newStr = autoCompleteTextView.getText().toString().substring(0, index) + ""
                                + arrayMention.get(position).getTitle() + " ";
                        autoCompleteTextView.setText(newStr);
                        if (mention.isEmpty()) {
                            mention = arrayMention.get(position).getTitle();
                        } else {
                            mention = mention + "," + arrayMention.get(position).getTitle();
                        }
                    }
                    autoCompleteTextView.post(new Runnable() {
                        @Override
                        public void run() {
                            autoCompleteTextView.setSelection(autoCompleteTextView.getText().toString().length());
                        }
                    });
                    recy_newVideo.setVisibility(View.GONE);
                    lin_showlayout.setVisibility(View.VISIBLE);
                    recycleClick = true;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {

            Object obj = response.body();
            if (obj instanceof AddNewsModel) {
                AddNewsModel loginModel = (AddNewsModel) obj;
                if (loginModel.isStatus()) {
                    Toast.makeText(this, "News successfully added", Toast.LENGTH_SHORT).show();
                    Prefrence.setisUpload(true);
                    finish();
                }
            }
            if (obj instanceof NewVideoHashtagModelClass) {

                NewVideoHashtagModelClass loginModel = (NewVideoHashtagModelClass) obj;
                if (loginModel.isStatus()) {
                    arrayHashTag.clear();
                    arrayHashTag = (ArrayList<NewVideoHashtagModelClass.DataBean.PagedRecordBean>) loginModel.getData().getPagedRecord();
                    if (arrayHashTag != null && arrayHashTag.size() > 0) {
                        Log.e("respce==", "" + arrayHashTag.size());
                        recycleClick = false;
                        inItItemRecycle("hashtag");
                    } else {
                        recy_newVideo.setVisibility(View.GONE);
                        lin_showlayout.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (obj instanceof NewVideoMentionModelClass) {

                NewVideoMentionModelClass loginModel = (NewVideoMentionModelClass) obj;
                if (loginModel.isStatus()) {
                    arrayMention.clear();
                    arrayMention = (ArrayList<NewVideoMentionModelClass.DataBean.PagedRecordBean>) loginModel.getData().getPagedRecord();
                    if (arrayMention != null && arrayMention.size() > 0) {
                        recycleClick = false;
                        inItItemRecycle("mention");
                    } else {
                        recy_newVideo.setVisibility(View.GONE);
                        lin_showlayout.setVisibility(View.VISIBLE);
                    }

                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }

    //setimage on imageview
    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                //  String result=data.getStringExtra("result");
                //  dataBean = (UploadNewsModel.DataBean) data.getSerializableExtra("video");
                //  Log.e("videoinactivityresult==", String.valueOf(dataBean.getVideoUrl()));
                NewVideoActivity.this.finish();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
                // NewVideoActivity.this.finish();

            }
        }
        if (requestCode == 888) {
            if (resultCode == Activity.RESULT_OK) {
                chooseLoaction = true;
                 sendCountry = data.getStringExtra("country");
                 sendState = data.getStringExtra("state");
                 sendCity = data.getStringExtra("city");
                 sendTahsil = data.getStringExtra("tahsil");

                 sendCountryId = data.getIntExtra("countryid",0);
                 sendStateId = data.getIntExtra("stateid",0);
                 sendCityId = data.getIntExtra("cityid",0);
                 sendTahsilId = data.getIntExtra("tahsilid",0);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
                // NewVideoActivity.this.finish();
            }
        }
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //Toast.makeText(this, "resultok", Toast.LENGTH_SHORT).show();lat/lng: (26.9124336,75.7872709)
                // Dausa, Rajasthan 303303, India
                chooseLoaction = true;
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.e("locationfrstchose====", place.getAddress());
                String adr = place.getAddress();
                adressArray = adr.split(",");
                if (adressArray.length == 3) {
                    Toast.makeText(this, adressArray[0] + "" + adressArray[1] + "" + adressArray[2], Toast.LENGTH_SHORT).show();

                } else if (adressArray.length == 2) {
                    Toast.makeText(this, adressArray[0] + "" + adressArray[1], Toast.LENGTH_SHORT).show();
                } else if (adressArray.length == 4) {
                    Toast.makeText(this, adressArray[0] + "" + adressArray[1] + "" + adressArray[2] + "" + adressArray[3], Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                // Toast.makeText(this, "resulterror", Toast.LENGTH_SHORT).show();
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.e("locationchooseee====", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
                //  Toast.makeText(this, "resultcancel", Toast.LENGTH_SHORT).show();
                Log.e("locationchooseee====", "resultcancel");
            }
            return;
        }
    }

    public void getThumbnail(String vimeoUrl) {
        // Log.e("vimeothumb====","https://vimeo.com/"+vimeoUrl+".xml");
        VimeoExtractor.getInstance().fetchVideoWithURL("https://vimeo.com/api/v2/video/" + vimeoUrl, null, new OnVimeoExtractionListener() {
            @Override
            public void onSuccess(VimeoVideo video) {
                String hdStream = video.getThumbs().get("640");
                thumbanilVimeo = hdStream;
                Log.e("vimeothumb====", hdStream);
                try {
                    ContextCompat.getMainExecutor(NewVideoActivity.this).execute(new Runnable() {
                        @Override
                        public void run() {
                            // Utils.loadImageUsingGlidePlaceHolder(context, hdStream, holder.img_videoThumb, R.mipmap.ic_launcher_foreground);
                            new ImageLoadTask(hdStream, img_newvideo_url).execute();
                        }
                    });
                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("error", throwable.getMessage());
                rest.dismissProgressdialog();

            }
        });
    }
    //helllo java
}