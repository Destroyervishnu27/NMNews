package com.nmnews.nmnewsagency.rest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nmnews.nmnewsagency.extras.Constants;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestAddComents;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestAddNews;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestChatId;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestFollow;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestGetComments;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestGetDocument;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestGetNewsById;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestGetNewsListingModel;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestGetNotification;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestGetProfile;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestGetSaveNews;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestGetTahsil;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestHashTag;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestHashTagDetail;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestLike;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestLoginModel;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestNotificationSet;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestPerfDistrct;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestPerforSelf;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestReportModel;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestSearchTopSaerch;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestSetAddress;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestUserReport;
import com.nmnews.nmnewsagency.modelclass.RequestModel.RequestuserOwnVideo;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.pref.Prefrence;

import java.io.File;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.http.Part;

/**
 * Created by Vishnu Saini on 2/20/2018.
 * vishnusainideveloper27@gmail.com
 */

public class Rest {
    public static final String MULTIPARt_FORM_DATA = "multipart/form-data";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public Context ctx;
    public ProgressDialog dialog;
    Callback callback;
    RestService restService;
    private String pDialogMessage = "Loading...";

    public Rest(Context ctx, Callback callback) {
        this.callback = callback;
        this.ctx = ctx;
        init();
    }

    public static void printLog(String msg) {
        Log.e("hommzi", msg);
    }

    public static String getExtension(String filePath) {
        File f = new File(filePath);
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf(".");

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    private void init() {
        dialog = new ProgressDialog(ctx);
        // dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        // dialog.setMessage("Please Wait for a sec ..");
        //  dialog.setProgress(0);
        // dialog.setMax(100);
        restService = RestAdapter.getAdapter();

    }

    public boolean isInterentAvaliable() {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected();
    }

    public void AlertForInternet() {
        AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setMessage("Internet Not avalible");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismissProgressdialog();
            }
        });
        alert.show();
    }

    public void ShowDialogue(String message) {

        dialog.setMessage(message);
        dialog.show();
        final int totalProgressTime = 95;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(2000);
                        jumpTime += 5;
                        // dialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    public void dismissProgressdialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            Rest.printLog("" + e);
        }
    }

    public void loginUser(String name, String email, String deviceType,
                          String deviceId, String deviceToken,
                          String profileimage, String provideruserid, String provider) {
        RequestLoginModel requestLoginModel = new RequestLoginModel();
        requestLoginModel.setDeviceId(deviceId);
        requestLoginModel.setDeviceToken(deviceToken);
        requestLoginModel.setDeviceType(deviceType);
        requestLoginModel.setEmail(email);
        requestLoginModel.setName(name);
        requestLoginModel.setLoginProvider(provider);
        requestLoginModel.setLoginProviderUserId(provideruserid);
        requestLoginModel.setProfileImage(profileimage);
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().loginUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void chatId(String userid, String chatid) {
        RequestChatId requestLoginModel = new RequestChatId();
        requestLoginModel.setId(userid);
        requestLoginModel.setChatId(chatid);

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().chatId(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void likeUser(String folow) {
        RequestFollow requestLoginModel = new RequestFollow();
        requestLoginModel.setWho_follows(Prefrence.getUserId());
        requestLoginModel.setWho_is_followed(folow);

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().followUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void CommentsUser(int newsId) {
        RequestGetComments requestLoginModel = new RequestGetComments();
        requestLoginModel.setIsVerify(true);
        requestLoginModel.setLoggedInUserId(Prefrence.getUserId());
        requestLoginModel.setNewsId(newsId);

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().commentsUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void AddCommentsUser(int newsId, String comments, String parentCommentId) {
        RequestAddComents requestLoginModel = new RequestAddComents();
        requestLoginModel.setComment(comments);
        requestLoginModel.setNewsId(newsId);
        requestLoginModel.setParentCommentId(0);
        requestLoginModel.setUserId(Prefrence.getUserId());

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().AddcommentsUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void reportUser(String subtxt, String txt, int newid) {
        RequestReportModel requestLoginModel = new RequestReportModel();
        requestLoginModel.setNewsId(newid);
        requestLoginModel.setReason(txt);
        requestLoginModel.setSubReason(subtxt);
        requestLoginModel.setUserId(Prefrence.getUserId());

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().reportUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void reportAUser(String subtxt, String txt, String orId) {
        RequestUserReport requestLoginModel = new RequestUserReport();
        requestLoginModel.setWhoseReported(orId);
        requestLoginModel.setReason(txt);
        requestLoginModel.setSubReason(subtxt);
        requestLoginModel.setUserId(Prefrence.getUserId());

        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().reportAUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void likeUser1(int newid) {
        RequestLike requestLoginModel = new RequestLike();
        requestLoginModel.setNewsId(newid);
        requestLoginModel.setUserId(Prefrence.getUserId());

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().LikeUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void SaveUser(int newid) {
        RequestLike requestLoginModel = new RequestLike();
        requestLoginModel.setNewsId(newid);
        requestLoginModel.setUserId(Prefrence.getUserId());

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().SaveUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void deleteSaveUser(int newid) {
        RequestLike requestLoginModel = new RequestLike();
        requestLoginModel.setNewsId(newid);
        requestLoginModel.setUserId(Prefrence.getUserId());

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().DeleteSaveUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void DislikeUser(int newid) {
        RequestLike requestLoginModel = new RequestLike();
        requestLoginModel.setNewsId(newid);
        requestLoginModel.setUserId(Prefrence.getUserId());

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().disLikeUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void UNfollowUser(String folow) {
        RequestFollow requestLoginModel = new RequestFollow();
        requestLoginModel.setWho_follows(Prefrence.getUserId());
        requestLoginModel.setWho_is_followed(folow);

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().UNfollowUser(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void HashTag(String query) {
        RequestHashTag requestLoginModel = new RequestHashTag();
        requestLoginModel.setDynamicQuery(query);
        requestLoginModel.setPageIndex(0);
        requestLoginModel.setPageOffset(10);

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().HashTag(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getProfileList() {
        RequestGetProfile requestLoginModel = new RequestGetProfile();
        requestLoginModel.setLogedInUserId(Prefrence.getUserId());
        requestLoginModel.setUserId(Prefrence.getUserId());

        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().getProfile(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getPerformnceSelf() {
        RequestPerforSelf requestLoginModel = new RequestPerforSelf();
        requestLoginModel.setCity_Name(Prefrence.getCityName());
        requestLoginModel.setCountry_Name(Prefrence.getCountryName());
        requestLoginModel.setState_Name(Prefrence.getStateName());
        requestLoginModel.setTahsil_Name(Prefrence.gettahsil());


        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().getPerformnceSelf(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getPerformnceDistrct() {
        RequestPerfDistrct requestLoginModel = new RequestPerfDistrct();
        requestLoginModel.setCity_Name(Prefrence.getCityName());
        requestLoginModel.setCountry_Name(Prefrence.getCountryName());
        requestLoginModel.setState_Name(Prefrence.getStateName());
        requestLoginModel.setTahsil_Name(Prefrence.gettahsil());


        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().getPerformnceDistrct(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getuserOwnVideo(int pageStart) {
        RequestuserOwnVideo requestLoginModel = new RequestuserOwnVideo();
        requestLoginModel.setPageIndex(pageStart);
        requestLoginModel.setUserId(Prefrence.getUserId());
        // requestLoginModel.setUserId("a6730420-c310-44a4-8ecf-6c8d34fc9f2c");
        requestLoginModel.setCity_Name(Prefrence.getCityName());
        requestLoginModel.setCountry_Name(Prefrence.getCountryName());
        requestLoginModel.setPageOffset(100);
        requestLoginModel.setState_Name(Prefrence.getStateName());
        if (Prefrence.gettahsil().isEmpty()) {
            requestLoginModel.setTahsil_Name(Prefrence.getCityName());
        } else {
            requestLoginModel.setTahsil_Name(Prefrence.gettahsil());
        }

        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().getuserOwnVideo(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getuserOwnVideo_userprofile(String id) {
        RequestuserOwnVideo requestLoginModel = new RequestuserOwnVideo();
        requestLoginModel.setPageIndex(0);
        requestLoginModel.setUserId(id);
        requestLoginModel.setCity_Name(Prefrence.getCityName());
        requestLoginModel.setCountry_Name(Prefrence.getCountryName());
        requestLoginModel.setPageOffset(1000);
        requestLoginModel.setState_Name(Prefrence.getStateName());
        if (Prefrence.gettahsil().isEmpty()) {
            requestLoginModel.setTahsil_Name(Prefrence.getCityName());
        } else {
            requestLoginModel.setTahsil_Name(Prefrence.gettahsil());
        }
        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().getuserOwnVideo(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getuserHashtagVideo(String id) {
        RequestuserOwnVideo requestLoginModel = new RequestuserOwnVideo();
        requestLoginModel.setPageIndex(0);
        requestLoginModel.setUserId(id);
        requestLoginModel.setCity_Name(Prefrence.getCityName());
        requestLoginModel.setCountry_Name(Prefrence.getCountryName());
        requestLoginModel.setPageOffset(1000);
        requestLoginModel.setState_Name(Prefrence.getStateName());
        if (Prefrence.gettahsil().isEmpty()) {
            requestLoginModel.setTahsil_Name(Prefrence.getCityName());
        } else {
            requestLoginModel.setTahsil_Name(Prefrence.gettahsil());
        }
        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().getuserHashTagVideo(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getFrontProfileList(String id) {
        RequestGetProfile requestLoginModel = new RequestGetProfile();
        requestLoginModel.setLogedInUserId(Prefrence.getUserId());
        requestLoginModel.setUserId(id);

        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().getProfile(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void setnotification() {
        RequestNotificationSet requestLoginModel = new RequestNotificationSet();
        requestLoginModel.setId(Prefrence.getUserId());

        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().setNoti(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void setAutoplay() {
        RequestNotificationSet requestLoginModel = new RequestNotificationSet();
        requestLoginModel.setId(Prefrence.getUserId());

        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().setAutoPlay(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getusersaveNews(String id) {
        RequestGetSaveNews requestLoginModel = new RequestGetSaveNews();
        requestLoginModel.setPageOffset(100);
        requestLoginModel.setPageIndex(0);
        requestLoginModel.setUserId(id);

        if (isInterentAvaliable()) {
            RestAdapter.getAdapter().getusersaveNews(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void menTion(String mention) {
        RequestHashTag requestLoginModel = new RequestHashTag();
        requestLoginModel.setDynamicQuery(mention);
        requestLoginModel.setPageIndex(0);
        requestLoginModel.setPageOffset(10);

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().Mention(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getNewsList(String countryname, String stateNmae, String cityName,
                            String TahsilName, String UseId,
                            String pGEINDEX, String pageoffset, String curentIndex, String tableIndex, String loopdate) {
        RequestGetNewsListingModel requestLoginModel = new RequestGetNewsListingModel();
        requestLoginModel.setCity_Name(cityName);
        requestLoginModel.setCountry_Name(countryname);
        requestLoginModel.setPageIndex(Integer.parseInt(pGEINDEX));
        requestLoginModel.setPageOffset(Integer.parseInt(pageoffset));
        requestLoginModel.setState_Name(stateNmae);
        requestLoginModel.setCurrentIndex(Integer.parseInt(curentIndex));
        requestLoginModel.setTableIndex(Integer.parseInt(tableIndex));
        requestLoginModel.setLoopDate(loopdate);
        if (TahsilName.isEmpty()) {
            requestLoginModel.setTahsil_Name(cityName);
        } else {
            requestLoginModel.setTahsil_Name(TahsilName);
        }
        requestLoginModel.setUserId(UseId);

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getList(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void setUserAddress(String addres1, String adres2, String adresstype,
                               String businessname, int cityid, String contactno, int countryid,
                               String frstname, String gcity, String gcountry, String gfuladsres,
                               String gstate, String houseno, String isadrematch, String isdefault,
                               String lastanmme, double lat, double lng, int stateid,
                               int tahsilid, String userid, String zipcode, String tahsil) {
        RequestSetAddress requestLoginModel = new RequestSetAddress();
        requestLoginModel.setAddressLine1(addres1);
        requestLoginModel.setAddressLine2(adres2);
        requestLoginModel.setAddresstype(adresstype);
        requestLoginModel.setBusinessName(businessname);
        requestLoginModel.setCityId(cityid);
        requestLoginModel.setContactNumber(contactno);
        requestLoginModel.setCountryId(countryid);
        requestLoginModel.setFirstName(frstname);
        requestLoginModel.setGCity(gcity);
        requestLoginModel.setGCountry(gcountry);
        requestLoginModel.setGFullAddress(gfuladsres);
        requestLoginModel.setGState(gstate);
        requestLoginModel.setHouseNo(houseno);
        requestLoginModel.setIsAddressMatch(isadrematch);
        requestLoginModel.setIsDefault(isdefault);
        requestLoginModel.setLastName(lastanmme);
        requestLoginModel.setLat(lat);
        requestLoginModel.setLong(lng);
        requestLoginModel.setStateId(stateid);
        requestLoginModel.setTahsilId(tahsilid);
        requestLoginModel.setUserId(userid);
        requestLoginModel.setZipCode(zipcode);
        requestLoginModel.setGThasil(tahsil);

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().setAddress(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void setUserTahsil(String city, double lat, double lng, String country, String state) {
        RequestGetTahsil requestLoginModel = new RequestGetTahsil();
        requestLoginModel.setLong(lng);
        requestLoginModel.setLat(lat);
        requestLoginModel.setCityName(city);
        requestLoginModel.setCountryName(country);
        requestLoginModel.setStateName(state);


        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getTahsil(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void addNews(UploadNewsModel.DataBean data, RequestAddNews.NewsObjBean newsObjBean) {
        RequestAddNews requestAddNews = new RequestAddNews();
        RequestAddNews.NewsMediaObjBean mediaobjbean = new RequestAddNews.NewsMediaObjBean();
        RequestAddNews.NewsObjBean objbean = new RequestAddNews.NewsObjBean();
        mediaobjbean.setAddedOn(data.getAddedOn());
        mediaobjbean.setId(data.getId());
        mediaobjbean.setImageUrl(data.getImageUrl());
        mediaobjbean.setIsActive(data.isIsActive());
        mediaobjbean.setMediaSize(data.getMediaSize());
        mediaobjbean.setMediaSource(data.getMediaSource());
        mediaobjbean.setMediaType(data.getMediaType());
        mediaobjbean.setNewsId(data.getNewsId());
        mediaobjbean.setSizeUnit(data.getSizeUnit());
        mediaobjbean.setVideoUrl(data.getVideoUrl());
        mediaobjbean.setVideoId(data.getVideoId());
        mediaobjbean.setDownloadLink(data.getDownloadLink());

        objbean.setAddressLin_2(newsObjBean.getAddressLin_2());
        objbean.setAddressLine_1(newsObjBean.getAddressLine_1());
        objbean.setCity_Name(newsObjBean.getCity_Name());
        objbean.setCityId(newsObjBean.getCityId());
        objbean.setCountry_Name(newsObjBean.getCountry_Name());
        objbean.setCountryId(newsObjBean.getCountryId());
        objbean.setDescription(newsObjBean.getDescription());
        objbean.setId(newsObjBean.getId());
        objbean.setIsBreakingNews(newsObjBean.isIsBreakingNews());
        objbean.setLat(newsObjBean.getLat());
        objbean.setLong(newsObjBean.getLong());
        objbean.setNewsType(newsObjBean.getNewsType());
        objbean.setState_Name(newsObjBean.getState_Name());
        objbean.setStateId(newsObjBean.getStateId());
        objbean.setSuggestion(newsObjBean.getSuggestion());
        objbean.setTahsil_Name(newsObjBean.getTahsil_Name());
        objbean.setTahsilId(newsObjBean.getTahsilId());
        objbean.setTitle(newsObjBean.getTitle());
        objbean.setUserId(newsObjBean.getUserId());
        objbean.setZipCode(newsObjBean.getZipCode());

        objbean.setHashTags(newsObjBean.getHashTags());
        objbean.setUserTags(newsObjBean.getUserTags());
        requestAddNews.setNewsMediaObj(mediaobjbean);
        requestAddNews.setNewsObj(objbean);
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().addNews(Constants.FLD_TOKENID,
                    requestAddNews).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    @NonNull
    public RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    public void getCountryList() {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getCountryList(Constants.FLD_TOKENID).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void setNewsCount(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().setnewsCount(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void deleteComments(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().deleteComments(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getStateList(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getStateList(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getStateList1(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getStateList1(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getpages(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getPages(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getNewsById(int id) {
        RequestGetNewsById requestLoginModel = new RequestGetNewsById();
        requestLoginModel.setNewsId(id);
        requestLoginModel.setUserId(Prefrence.getUserId());
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getNewsById(Constants.FLD_TOKENID, requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getNewsTopSearch(int id, String query) {
        RequestSearchTopSaerch requestLoginModel = new RequestSearchTopSaerch();
        requestLoginModel.setCity_Name(Prefrence.getCityName());
        requestLoginModel.setCountry_Name(Prefrence.getCountryName());
        requestLoginModel.setPageIndex(0);
        requestLoginModel.setPageOffset(100);
        requestLoginModel.setQuery(query);
        requestLoginModel.setSearchType(id);
        requestLoginModel.setState_Name(Prefrence.getStateName());
        requestLoginModel.setTahsil_Name(Prefrence.gettahsil());
        requestLoginModel.setLogedInUserId(Prefrence.getUserId());


        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getTopNewsSearch(Constants.FLD_TOKENID, requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getHashTagById(int id) {
        RequestHashTagDetail requestLoginModel = new RequestHashTagDetail();
        requestLoginModel.setCity_Name(Prefrence.getCityName());
        requestLoginModel.setCountry_Name(Prefrence.getCountryName());
        requestLoginModel.setPageIndex(0);
        requestLoginModel.setPageOffset(10);
        requestLoginModel.setState_Name(Prefrence.getStateName());
        requestLoginModel.setTahsil_Name("Snaganer");
        requestLoginModel.setHashTagId(id);


        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getHashTagDetail(Constants.FLD_TOKENID, requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getDeleteNewsById(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getDeleteNewsById(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getCityList(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getCityList(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getTahsilList(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getTahsilList(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getCityList1(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getCityList1(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void getTahsilList1(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getTahsilList1(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void uploadNews(String video, boolean isAlert) {
        if (isInterentAvaliable()) {
            MultipartBody.Part body1 = null;
            if (video != null) {
                File file = new File(video);
                try {
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    body1 = MultipartBody.Part.createFormData("file", URLEncoder.encode(file.getName(), "utf-8"), requestFile);
                } catch (Exception e) {
                }
            }

            RestAdapter.getAdapter().UploadNews(Constants.FLD_TOKENID, body1).enqueue(callback);

        } else {
            if (isAlert) {
                AlertForInternet();
            }

        }
    }

    public void getDocument() {
        if (isInterentAvaliable()) {
            RequestGetDocument requestLoginModel = new RequestGetDocument();
            requestLoginModel.setUserId(Prefrence.getUserId());

            if (isInterentAvaliable()) {

                RestAdapter.getAdapter().getDocument(Constants.FLD_TOKENID,
                        requestLoginModel).enqueue(callback);

            } else {
                AlertForInternet();
            }
        } else {
            AlertForInternet();
        }
    }

    public void getFOloowers() {
        if (isInterentAvaliable()) {
            RequestGetDocument requestLoginModel = new RequestGetDocument();
            requestLoginModel.setUserId(Prefrence.getUserId());

            if (isInterentAvaliable()) {

                RestAdapter.getAdapter().getFollowers(Constants.FLD_TOKENID,
                        requestLoginModel).enqueue(callback);

            } else {
                AlertForInternet();
            }
        } else {
            AlertForInternet();
        }
    }

    public void getNotification() {
        if (isInterentAvaliable()) {
            RequestGetNotification requestLoginModel = new RequestGetNotification();
            requestLoginModel.setToUserId(Prefrence.getUserId());
            //  requestLoginModel.setToUserId("a3a90132-5466-4ceb-a729-b042af0e4158");

            if (isInterentAvaliable()) {
                RestAdapter.getAdapter().getNotification(Constants.FLD_TOKENID,
                        requestLoginModel).enqueue(callback);

            } else {
                AlertForInternet();
            }
        } else {
            AlertForInternet();
        }
    }

    public void getFOloowing() {
        if (isInterentAvaliable()) {
            RequestGetDocument requestLoginModel = new RequestGetDocument();
            requestLoginModel.setUserId(Prefrence.getUserId());

            if (isInterentAvaliable()) {

                RestAdapter.getAdapter().getFollowING(Constants.FLD_TOKENID,
                        requestLoginModel).enqueue(callback);

            } else {
                AlertForInternet();
            }
        } else {
            AlertForInternet();
        }
    }

    public void updateProfile(String frstname, String lastname, String aboutme1, String path, String avtar1) {
        if (isInterentAvaliable()) {

  /*          RequestBody userID = createPartFromString(Prefrence.getUserId());
            RequestBody frname = createPartFromString(frstname);
            RequestBody lname = createPartFromString(lastname);
            RequestBody avtar = createPartFromString(avtar1);
            RequestBody aboutme = createPartFromString(aboutme1);

            HashMap<String, RequestBody> map = new HashMap<>();

            map.put(ParaName.KEY_FIRSTNAME, frname);
            map.put(ParaName.KEY_LASTNAME, lname);
            map.put(ParaName.KEY_USERID, userID);
            map.put(ParaName.KEY_AVTAR, avtar);
            map.put(ParaName.KEY_ABOUTME, aboutme);*/
            MultipartBody.Part body1 = null;
            if (path != null && !path.isEmpty()) {
                File file = new File(path);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                body1 = MultipartBody.Part.createFormData(ParaName.KEY_FILE, file.getName(), requestFile);
            }
            RequestBody fname =
                    RequestBody.create(MediaType.parse("multipart/form-data"), frstname);
            RequestBody lname =
                    RequestBody.create(MediaType.parse("multipart/form-data"), lastname);
            RequestBody aboutme =
                    RequestBody.create(MediaType.parse("multipart/form-data"), aboutme1);
            RequestBody userid =
                    RequestBody.create(MediaType.parse("multipart/form-data"), Prefrence.getUserId());
            RequestBody avtar =
                    RequestBody.create(MediaType.parse("multipart/form-data"), avtar1);

            RestAdapter.getAdapter().updateProfile(Constants.FLD_TOKENID, fname, lname, aboutme, userid, avtar, body1).enqueue(callback);

        } else {
            AlertForInternet();
        }


    }

    public void addUserDocument(String docType1, String path) {
        if (isInterentAvaliable()) {
            MultipartBody.Part body1 = null;
            if (path != null && !path.isEmpty()) {
                File file = new File(path);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                body1 = MultipartBody.Part.createFormData(ParaName.KEY_FILE, file.getName(), requestFile);
            }
            RequestBody docType =
                    RequestBody.create(MediaType.parse("multipart/form-data"), docType1);

            RequestBody userid =
                    RequestBody.create(MediaType.parse("multipart/form-data"), Prefrence.getUserId());


            RestAdapter.getAdapter().addUserDoc(Constants.FLD_TOKENID, docType, userid, body1).enqueue(callback);

        } else {
            AlertForInternet();
        }


    }


    public void callAddViewCount(int id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().callAddViewCount(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void callDownloadVideo(String id) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().callDownloadVideo(Constants.FLD_TOKENID, id).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

 public void downloadNewsFileNew(String videoid,String newsid) {
        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().downloadNewsFileNew(Constants.FLD_TOKENID, videoid,newsid).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

    public void insertNews(String title, String description, String suggestion, String path
            , boolean isBreakingNews,String newsType,String countryId,String country_Name,String stateId,
                           String state_Name,String cityId,String city_Name,String tahsilId,String tahsil_Name,
                           String addressLine_1,String addressLin_2,String lat,String longs,String zipCode,
                           String userTags, String hashTags ) {
        if (isInterentAvaliable()) {
            MultipartBody.Part body1 = null;
            if (path != null && !path.isEmpty()) {
                File file = new File(path);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                body1 = MultipartBody.Part.createFormData(ParaName.KEY_NEWS_FILE, file.getName(), requestFile);
            }
            RequestBody Title =
                    RequestBody.create(MediaType.parse("multipart/form-data"), title);
            RequestBody Description =
                    RequestBody.create(MediaType.parse("multipart/form-data"), description);
            RequestBody Suggestion =
                    RequestBody.create(MediaType.parse("multipart/form-data"), suggestion);
            RequestBody UserId =
                    RequestBody.create(MediaType.parse("multipart/form-data"), Prefrence.getUserId());

            RequestBody NewsType =
                    RequestBody.create(MediaType.parse("multipart/form-data"), newsType);
            RequestBody CountryId =
                    RequestBody.create(MediaType.parse("multipart/form-data"), countryId);
            RequestBody Country_Name =
                    RequestBody.create(MediaType.parse("multipart/form-data"), country_Name);
            RequestBody StateId =
                    RequestBody.create(MediaType.parse("multipart/form-data"), stateId);
            RequestBody State_Name =
                    RequestBody.create(MediaType.parse("multipart/form-data"), state_Name);
            RequestBody CityId =
                    RequestBody.create(MediaType.parse("multipart/form-data"), cityId);
            RequestBody City_Name =
                    RequestBody.create(MediaType.parse("multipart/form-data"), city_Name);
            RequestBody TahsilId =
                    RequestBody.create(MediaType.parse("multipart/form-data"), tahsilId);
            RequestBody Tahsil_Name =
                    RequestBody.create(MediaType.parse("multipart/form-data"), tahsil_Name);
            RequestBody AddressLine_1 =
                    RequestBody.create(MediaType.parse("multipart/form-data"), addressLine_1);
            RequestBody AddressLin_2 =
                    RequestBody.create(MediaType.parse("multipart/form-data"), addressLin_2);
            RequestBody Lat =
                    RequestBody.create(MediaType.parse("multipart/form-data"), lat);
            RequestBody Long =
                    RequestBody.create(MediaType.parse("multipart/form-data"), longs);
            RequestBody ZipCode =
                    RequestBody.create(MediaType.parse("multipart/form-data"), zipCode);
            RequestBody UserTags =
                    RequestBody.create(MediaType.parse("multipart/form-data"), userTags);
            RequestBody HashTags =
                    RequestBody.create(MediaType.parse("multipart/form-data"), hashTags);

            RestAdapter.getAdapter().insertNews(Constants.FLD_TOKENID, Title, Description, Suggestion, UserId,
                    isBreakingNews,NewsType,CountryId,Country_Name,StateId,State_Name,CityId,City_Name,TahsilId,
                    Tahsil_Name,AddressLine_1,AddressLin_2,Lat,Long,ZipCode,UserTags,HashTags,body1).enqueue(callback);

        } else {
            AlertForInternet();
        }

    }
    public void getNewLikeUserList(int newsId) {
        RequestGetComments requestLoginModel = new RequestGetComments();
        requestLoginModel.setIsVerify(true);
        requestLoginModel.setLoggedInUserId(Prefrence.getUserId());
        requestLoginModel.setNewsId(newsId);

        if (isInterentAvaliable()) {

            RestAdapter.getAdapter().getNewLikeUserList(Constants.FLD_TOKENID,
                    requestLoginModel).enqueue(callback);

        } else {
            AlertForInternet();
        }
    }

}
