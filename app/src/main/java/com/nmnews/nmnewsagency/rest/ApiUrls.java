package com.nmnews.nmnewsagency.rest;

/**
 * Created by Vishnu Saini
 */

public class ApiUrls {
     public static final String BASE_URL = "https://nmnews.in/api/";
    // public static final String BASE_URL = "https://taxi.vlcare.com/api/";

       public static final String URL_LOGIN = "account/social-login";
       public static final String URL_CHATID = "User/SetUserChatId";
       public static final String URL_fOLLOW = "User/AddUserFollow";
       public static final String URL_GETCOMMENTS = "Comment/GetComment";
       public static final String URL_GETNEWLIKEUSERLIST = "News/GetNewLikeUserList";
       public static final String URL_ADDCOMMENTS = "Comment/AddComment";
       public static final String URL_REPORT = "News/reportnews";
       public static final String URL_USERREPORT = "User/Userreport";
       public static final String URL_LIKE = "Common/AddLikes";
       public static final String URL_SAVE = "Common/AddNewsSave";
       public static final String URL_DELETESAVE = "Common/DeleteNewsSave";
       public static final String URL_DISLIKE = "Common/DeleteLike";
       public static final String URL_UNfOLLOW = "User/DeleteUserFollow";
      // public static final String URL_GETLIST = "News/GetNewsByPagiQuery";
       public static final String URL_GETLIST = "News/GetNewsByPagiQueryWithAds";
       public static final String URL_HASHtAG = "Tags/GetTagsByPagiQuery";
       public static final String URL_USERPROFILE = "User/GetUserProfileDetails";
       public static final String URL_PERFORMNESELF = "Common/GetDistPerformanceForUser";
       public static final String URL_PERFORMNEDISTRCT = "Common/GetDistPerformanceForCity";
       public static final String URL_NOTIFICATIONSET = "User/ChangeUserSettingSendNotification";
       public static final String URL_AUTOPLAY = "User/ChangeUserSettingAutoPlay";
       public static final String URL_MENTION = "Tags/GetUserTagsByPagiQuery";
       public static final String URL_SETUSERADDRESS = "User/SetUserLocation";
       public static final String URL_GETTAHSIL = "Region/GetTahsilNameByLatLongAndCity";

    public static final String GET_COUNTRY_TRUE = "Region/GetCountry/true";
    public static final String GET_NEWSBYID = "News/GetNewsDetailBYId";
    public static final String GET_SEARCHTOPSESRCH= "Search/GetSearchKeywordByQuery";
    public static final String GET_HASHTAGDETYAIL= "News/GetNewsByHashTagPagi";
    public static final String GET_CITY_TRUE = "Region/GetStateByCountryId/true/";
    public static final String SETADDNEWS = "News/AddNews";
    public static final String UPLOADNEWS = "News/upload-news-file";
    public static final String UPDATEPROFILE = "User/UpdateProfileData";
    public static final String ADDUSERDOCUMENT = "User/AddUserDocument";
    public static final String URL_GETUSERDOCUMENT = "User/GetUserDocument";
    public static final String URL_GETFOLLOWERS = "User/GetFollowersByUserId";
    public static final String URL_GETFOLOWING = "User/GetFollowingsByUserId";
    public static final String URL_GETUSERSAVENEWS = "News/GetUserSavedNews";
    public static final String URL_GETUSEROWNNEWS = "News/GetNewsByUserId";
    public static final String URL_GETUSERHASHTAGNEWS = "News/GetNewsByUserTag";
    public static final String URL_GETNOTIFICATION = "Notification/GetNotification";
    public static final String URL_INSERTNEWS = "News/insertnews";
}
