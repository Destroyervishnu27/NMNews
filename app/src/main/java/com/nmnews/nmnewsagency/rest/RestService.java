package com.nmnews.nmnewsagency.rest;


import com.nmnews.nmnewsagency.model.CityModel;
import com.nmnews.nmnewsagency.model.CountryModel;
import com.nmnews.nmnewsagency.model.DownloadVIdeoNewModel;
import com.nmnews.nmnewsagency.model.LikesListModel;
import com.nmnews.nmnewsagency.model.NewsUploadSuccessModel;
import com.nmnews.nmnewsagency.model.StateModel;
import com.nmnews.nmnewsagency.model.TahsilModel;
import com.nmnews.nmnewsagency.modelclass.AddNewsModel;
import com.nmnews.nmnewsagency.modelclass.AddUserDocumentModel;
import com.nmnews.nmnewsagency.modelclass.ChatIdModerl;
import com.nmnews.nmnewsagency.modelclass.DeleteCommentsModel;
import com.nmnews.nmnewsagency.modelclass.DeleteNewsByIdModel;
import com.nmnews.nmnewsagency.modelclass.DownloadModel;
import com.nmnews.nmnewsagency.modelclass.GetCommentsModel;
import com.nmnews.nmnewsagency.modelclass.GetDocumentModel;
import com.nmnews.nmnewsagency.modelclass.GetFollowersModel;
import com.nmnews.nmnewsagency.modelclass.GetFollowingModel;
import com.nmnews.nmnewsagency.modelclass.GetNewsByIdModel;
import com.nmnews.nmnewsagency.modelclass.GetNewsListModel;
import com.nmnews.nmnewsagency.modelclass.GetProfileDataModel;
import com.nmnews.nmnewsagency.modelclass.GetTahsilModel;
import com.nmnews.nmnewsagency.modelclass.GetUserHashTagModel;
import com.nmnews.nmnewsagency.modelclass.GetUserOwnNewsModel;
import com.nmnews.nmnewsagency.modelclass.GetUserSaveNewsModel;
import com.nmnews.nmnewsagency.modelclass.HashTagDetailModel;
import com.nmnews.nmnewsagency.modelclass.LikeModelClass;
import com.nmnews.nmnewsagency.modelclass.LoginModel;
import com.nmnews.nmnewsagency.modelclass.NewVideoHashtagModelClass;
import com.nmnews.nmnewsagency.modelclass.NewVideoMentionModelClass;
import com.nmnews.nmnewsagency.modelclass.NewsCountModel;
import com.nmnews.nmnewsagency.modelclass.NotificationModel;
import com.nmnews.nmnewsagency.modelclass.NotificationSet;
import com.nmnews.nmnewsagency.modelclass.PagesModel;
import com.nmnews.nmnewsagency.modelclass.PerformceDistrctModel;
import com.nmnews.nmnewsagency.modelclass.Performence_SelfModel;
import com.nmnews.nmnewsagency.modelclass.ReportModelClass;
import com.nmnews.nmnewsagency.modelclass.ReportUserModel;
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
import com.nmnews.nmnewsagency.modelclass.SaveModelClass;
import com.nmnews.nmnewsagency.modelclass.SearchTopSearchModel;
import com.nmnews.nmnewsagency.modelclass.SetAddressModelClass;
import com.nmnews.nmnewsagency.modelclass.UpdateProfileModel;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface RestService {
    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_LOGIN)
    Call<LoginModel> loginUser(@Header("TokenId") String auth,
                               @Body RequestLoginModel requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_CHATID)
    Call<ChatIdModerl> chatId(@Header("TokenId") String auth,
                              @Body RequestChatId requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_fOLLOW)
    Call<AddNewsModel> followUser(@Header("TokenId") String auth,
                                  @Body RequestFollow requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETCOMMENTS)
    Call<GetCommentsModel> commentsUser(@Header("TokenId") String auth,
                                        @Body RequestGetComments requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_ADDCOMMENTS)
    Call<ReportModelClass> AddcommentsUser(@Header("TokenId") String auth,
                                           @Body RequestAddComents requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_REPORT)
    Call<ReportModelClass> reportUser(@Header("TokenId") String auth,
                                      @Body RequestReportModel requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_USERREPORT)
    Call<ReportUserModel> reportAUser(@Header("TokenId") String auth,
                                      @Body RequestUserReport requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_LIKE)
    Call<LikeModelClass> LikeUser(@Header("TokenId") String auth,
                                  @Body RequestLike requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_SAVE)
    Call<SaveModelClass> SaveUser(@Header("TokenId") String auth,
                                  @Body RequestLike requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_DELETESAVE)
    Call<SaveModelClass> DeleteSaveUser(@Header("TokenId") String auth,
                                  @Body RequestLike requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_DISLIKE)
    Call<LikeModelClass> disLikeUser(@Header("TokenId") String auth,
                                      @Body RequestLike requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_UNfOLLOW)
    Call<AddNewsModel> UNfollowUser(@Header("TokenId") String auth,
                               @Body RequestFollow requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_HASHtAG)
    Call<NewVideoHashtagModelClass> HashTag(@Header("TokenId") String auth,
                                            @Body RequestHashTag requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_USERPROFILE)
    Call<GetProfileDataModel> getProfile(@Header("TokenId") String auth,
                                         @Body RequestGetProfile requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_PERFORMNESELF)
    Call<Performence_SelfModel> getPerformnceSelf(@Header("TokenId") String auth,
                                                  @Body RequestPerforSelf requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_PERFORMNEDISTRCT)
    Call<PerformceDistrctModel> getPerformnceDistrct(@Header("TokenId") String auth,
                                                     @Body RequestPerfDistrct requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_NOTIFICATIONSET)
    Call<NotificationSet> setNoti(@Header("TokenId") String auth,
                                  @Body RequestNotificationSet requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_AUTOPLAY)
    Call<NotificationSet> setAutoPlay(@Header("TokenId") String auth,
                                  @Body RequestNotificationSet requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETUSEROWNNEWS)
    Call<GetUserOwnNewsModel> getuserOwnVideo(@Header("TokenId") String auth,
                                              @Body RequestuserOwnVideo requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETUSERHASHTAGNEWS)
    Call<GetUserHashTagModel> getuserHashTagVideo(@Header("TokenId") String auth,
                                                  @Body RequestuserOwnVideo requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETUSERSAVENEWS)
    Call<GetUserSaveNewsModel> getusersaveNews(@Header("TokenId") String auth,
                                               @Body RequestGetSaveNews requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
  //  @FormUrlEncoded
    @POST(ApiUrls.URL_MENTION)
    Call<NewVideoMentionModelClass> Mention(@Header("TokenId") String auth,
                                            @Body RequestHashTag requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
    //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETLIST)
    Call<GetNewsListModel> getList(@Header("TokenId") String auth,
                                   @Body RequestGetNewsListingModel requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
    //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETUSERDOCUMENT)
    Call<GetDocumentModel> getDocument(@Header("TokenId") String auth,
                                       @Body RequestGetDocument requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
    //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETFOLLOWERS)
    Call<GetFollowersModel> getFollowers(@Header("TokenId") String auth,
                                         @Body RequestGetDocument requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
    //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETNOTIFICATION)
    Call<NotificationModel> getNotification(@Header("TokenId") String auth,
                                            @Body RequestGetNotification requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
    //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETFOLOWING)
    Call<GetFollowingModel> getFollowING(@Header("TokenId") String auth,
                                         @Body RequestGetDocument requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
    //  @FormUrlEncoded
    @POST(ApiUrls.URL_SETUSERADDRESS)
    Call<SetAddressModelClass> setAddress(@Header("TokenId") String auth,
                                          @Body RequestSetAddress requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
    //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETTAHSIL)
    Call<GetTahsilModel> getTahsil(@Header("TokenId") String auth,
                                   @Body RequestGetTahsil requestLoginModel
    );
    @Headers({"Content-Type: application/json"})
    @GET(ApiUrls.GET_COUNTRY_TRUE)
    Call<CountryModel> getCountryList(@Header("TokenId") String auth);

    @Headers({"Content-Type: application/json"})
    @GET("Region/GetStateByCountryId/true/{id}")
    Call<CountryModel> getStateList(@Header("TokenId") String auth,
                                    @Path("id") int id);

    @Headers({"Content-Type: application/json"})
    @GET("Region/GetStateByCountryId/true/{id}")
    Call<StateModel> getStateList1(@Header("TokenId") String auth,
                                   @Path("id") int id);

    @Headers({"Content-Type: application/json"})
    @GET("Common/GetMainPageByPageId/{id}")
    Call<PagesModel> getPages(@Header("TokenId") String auth,
                              @Path("id") int id);

    @Headers({"Content-Type: application/json"})
    @GET("News/IncreaseNewsViewCount/{id}")
    Call<NewsCountModel> setnewsCount(@Header("TokenId") String auth,
                                      @Path("id") int id);

    @Headers({"Content-Type: application/json"})
    @POST(ApiUrls.GET_NEWSBYID)
    Call<GetNewsByIdModel> getNewsById(@Header("TokenId") String auth,
                                       @Body RequestGetNewsById requestLoginModel);

    @Headers({"Content-Type: application/json"})
    @POST(ApiUrls.GET_SEARCHTOPSESRCH)
    Call<SearchTopSearchModel> getTopNewsSearch(@Header("TokenId") String auth,
                                                @Body RequestSearchTopSaerch requestLoginModel);

    @Headers({"Content-Type: application/json"})
    @POST(ApiUrls.GET_HASHTAGDETYAIL)
    Call<HashTagDetailModel> getHashTagDetail(@Header("TokenId") String auth,
                                              @Body RequestHashTagDetail requestLoginModel);

    @Headers({"Content-Type: application/json"})
    @GET("News/DeleteNewsByNewsId/{id}")
    Call<DeleteNewsByIdModel> getDeleteNewsById(@Header("TokenId") String auth,
                                                @Path("id") int id);

    @Headers({"Content-Type: application/json"})
    @GET("Comment/DeleteComment/{id}")
    Call<DeleteCommentsModel> deleteComments(@Header("TokenId") String auth,
                                             @Path("id") int id);

    @Headers({"Content-Type: application/json"})
    @GET("Region/GetCityByStateId/{id}/true")
    Call<CountryModel> getCityList(@Header("TokenId") String auth,
                                    @Path("id") int id);
    @Headers({"Content-Type: application/json"})
    @GET("Region/GetCityByStateId/{id}/true")
    Call<CityModel> getCityList1(@Header("TokenId") String auth,
                                 @Path("id") int id);

    @Headers({"Content-Type: application/json"})
    @GET("Region/GetTahsilByCityId/{id}/true")
    Call<CountryModel> getTahsilList(@Header("TokenId") String auth,
                                    @Path("id") int id);
    @Headers({"Content-Type: application/json"})
    @GET("Region/GetTahsilByCityId/{id}/true")
    Call<TahsilModel> getTahsilList1(@Header("TokenId") String auth,
                                     @Path("id") int id);

   // @Headers({"Content-Type: application/json"})
    @Multipart
    @POST(ApiUrls.UPLOADNEWS)
    Call<UploadNewsModel> UploadNews(@Header("TokenId") String auth,
                                     @Part MultipartBody.Part pic1);

    @Multipart
    @POST(ApiUrls.UPDATEPROFILE)
    Call<UpdateProfileModel> updateProfile(@Header("TokenId") String auth,
                                           @Part("FirstName") RequestBody fname,
                                           @Part("LastName") RequestBody lname,
                                           @Part("AboutMe") RequestBody aboutme,
                                           @Part("UserId") RequestBody userid,
                                           @Part("Avatar") RequestBody avtar,
                                           @Part MultipartBody.Part imageresume);

    @Multipart
    @POST(ApiUrls.ADDUSERDOCUMENT)
    Call<AddUserDocumentModel> addUserDoc(@Header("TokenId") String auth,
                                          @Part("DocumentType") RequestBody doctype,
                                          @Part("UserId") RequestBody userid,
                                          @Part MultipartBody.Part imageresume);




    @Headers({"Content-Type: application/json"})
    //  @FormUrlEncoded
    @POST(ApiUrls.SETADDNEWS)
    Call<AddNewsModel> addNews(@Header("TokenId") String auth,
                                 @Body RequestAddNews requestLoginModel
    );

    @Headers({"Content-Type: application/json"})
    @GET("Common/IncreaseAdsViewCount/{id}")
    Call<CountryModel> callAddViewCount(@Header("TokenId") String auth,
                                    @Path("id") int id);

 @Headers({"Content-Type: application/json"})
    @GET("News/download-news-file/{id}")
    Call<DownloadModel> callDownloadVideo(@Header("TokenId") String auth,
                                          @Path("id") String id);

    @Headers({"Content-Type: application/json"})
    @GET("News/download-news-file-new/{videoid}/{newsid}")
    Call<DownloadVIdeoNewModel> downloadNewsFileNew(@Header("TokenId") String auth,
                                                    @Path("videoid") String videoid,
                                                    @Path("newsid") String newsid);
    @Multipart
    @POST(ApiUrls.URL_INSERTNEWS)
    Call<NewsUploadSuccessModel> insertNews(@Header("TokenId") String auth,
                                            @Part("Title") RequestBody Title,
                                            @Part("Description") RequestBody Description,
                                            @Part("Suggestion") RequestBody Suggestion,
                                            @Part("UserId") RequestBody UserId,
                                            @Part("IsBreakingNews") boolean IsBreakingNews,
                                            @Part("NewsType") RequestBody NewsType,
                                            @Part("CountryId") RequestBody CountryId,
                                            @Part("Country_Name") RequestBody Country_Name,
                                            @Part("StateId") RequestBody StateId,
                                            @Part("State_Name") RequestBody State_Name,
                                            @Part("CityId") RequestBody CityId,
                                            @Part("City_Name") RequestBody City_Name,
                                            @Part("TahsilId") RequestBody TahsilId,
                                            @Part("Tahsil_Name") RequestBody Tahsil_Name,
                                            @Part("AddressLine_1") RequestBody AddressLine_1,
                                            @Part("AddressLin_2") RequestBody AddressLin_2,
                                            @Part("Lat") RequestBody Lat,
                                            @Part("Long") RequestBody Long,
                                            @Part("ZipCode") RequestBody ZipCode,
                                            @Part("UserTags") RequestBody UserTags,
                                            @Part("HashTags") RequestBody HashTags,
                                            @Part MultipartBody.Part imageresume);

    @Headers({"Content-Type: application/json"})
    //  @FormUrlEncoded
    @POST(ApiUrls.URL_GETNEWLIKEUSERLIST)
    Call<LikesListModel> getNewLikeUserList(@Header("TokenId") String auth,
                                            @Body RequestGetComments requestLoginModel
    );


}

