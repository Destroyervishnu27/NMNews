package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class GetNewsByIdModel {

    /**
     * Status : true
     * Message : Success
     * Data : [{"NewsId":1,"Title":"तहसील में कोरोना के 31 नए मरीज मिले तो23 मरीज  ठीक हो कर घर लौटे","Description":"आटपाडी: ","ViewCount":1,"AddedOn":"2021-06-30T20:00:49.367","IsTranding":false,"IsPopular":false,"UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsBreakingNews":true,"NewsType":0,"ImageUrl":"xyz.jpg","MediaType":"IMAGE","MediaSource":"YOUTUBE","VideoUrl":"https://www.youtube.com/watch?v=w88LODwHoQM","Avatar":"23b52982-c37b-4814-ac03-a6a5a30eef72.jpg","UserName":"Admin Main Ui","AboutMe":null,"IsFollowed":false,"Country_Name":"India","State_Name":"Rajasthan","City_Name":"Jaipur","Tahsil_Name":"Snaganer","LikesCount":1,"CommentCount":3,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false}]
     */

    private boolean Status;
    private String Message;
    private List<DataBean> Data;

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * NewsId : 1
         * Title : तहसील में कोरोना के 31 नए मरीज मिले तो23 मरीज  ठीक हो कर घर लौटे
         * Description : आटपाडी:
         * ViewCount : 1
         * AddedOn : 2021-06-30T20:00:49.367
         * IsTranding : false
         * IsPopular : false
         * UserId : 6fc00a95-dc1f-473d-b324-a990a9543498
         * IsBreakingNews : true
         * NewsType : 0
         * ImageUrl : xyz.jpg
         * MediaType : IMAGE
         * MediaSource : YOUTUBE
         * VideoUrl : https://www.youtube.com/watch?v=w88LODwHoQM
         * Avatar : 23b52982-c37b-4814-ac03-a6a5a30eef72.jpg
         * UserName : Admin Main Ui
         * AboutMe : null
         * IsFollowed : false
         * Country_Name : India
         * State_Name : Rajasthan
         * City_Name : Jaipur
         * Tahsil_Name : Snaganer
         * LikesCount : 1
         * CommentCount : 3
         * IsSaved : false
         * IsReport : false
         * IsLike : false
         * IsComment : false
         */

        private int NewsId;
        private String Title;
        private String Description;
        private int ViewCount;
        private String AddedOn;
        private boolean IsTranding;
        private boolean IsPopular;
        private String UserId;
        private boolean IsBreakingNews;
        private int NewsType;
        private String ImageUrl;
        private String MediaType;
        private String MediaSource;
        private String VideoUrl;
        private String Avatar;
        private String UserName;
        private Object AboutMe;
        private boolean IsFollowed;
        private String Country_Name;
        private String State_Name;
        private String City_Name;
        private String Tahsil_Name;
        private String ChatId;
        private String ViewCountSuffix;
        private String LikesCountSuffix;
        private String CommentCountSuffix;
        private String UserType;
        private int LikesCount;
        private int CommentCount;
        private boolean IsSaved;
        private boolean IsReport;
        private boolean IsLike;
        private boolean IsComment;

        public String getUserType() {
            return UserType;
        }

        public void setUserType(String userType) {
            UserType = userType;
        }

        public String getViewCountSuffix() {
            return ViewCountSuffix;
        }

        public void setViewCountSuffix(String viewCountSuffix) {
            ViewCountSuffix = viewCountSuffix;
        }

        public String getLikesCountSuffix() {
            return LikesCountSuffix;
        }

        public void setLikesCountSuffix(String likesCountSuffix) {
            LikesCountSuffix = likesCountSuffix;
        }

        public String getCommentCountSuffix() {
            return CommentCountSuffix;
        }

        public void setCommentCountSuffix(String commentCountSuffix) {
            CommentCountSuffix = commentCountSuffix;
        }

        public String getChatId() {
            return ChatId;
        }

        public void setChatId(String ChatId) {
            this.ChatId = ChatId;
        }
        public int getNewsId() {
            return NewsId;
        }

        public void setNewsId(int NewsId) {
            this.NewsId = NewsId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public int getViewCount() {
            return ViewCount;
        }

        public void setViewCount(int ViewCount) {
            this.ViewCount = ViewCount;
        }

        public String getAddedOn() {
            return AddedOn;
        }

        public void setAddedOn(String AddedOn) {
            this.AddedOn = AddedOn;
        }

        public boolean isIsTranding() {
            return IsTranding;
        }

        public void setIsTranding(boolean IsTranding) {
            this.IsTranding = IsTranding;
        }

        public boolean isIsPopular() {
            return IsPopular;
        }

        public void setIsPopular(boolean IsPopular) {
            this.IsPopular = IsPopular;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public boolean isIsBreakingNews() {
            return IsBreakingNews;
        }

        public void setIsBreakingNews(boolean IsBreakingNews) {
            this.IsBreakingNews = IsBreakingNews;
        }

        public int getNewsType() {
            return NewsType;
        }

        public void setNewsType(int NewsType) {
            this.NewsType = NewsType;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getMediaType() {
            return MediaType;
        }

        public void setMediaType(String MediaType) {
            this.MediaType = MediaType;
        }

        public String getMediaSource() {
            return MediaSource;
        }

        public void setMediaSource(String MediaSource) {
            this.MediaSource = MediaSource;
        }

        public String getVideoUrl() {
            return VideoUrl;
        }

        public void setVideoUrl(String VideoUrl) {
            this.VideoUrl = VideoUrl;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public Object getAboutMe() {
            return AboutMe;
        }

        public void setAboutMe(Object AboutMe) {
            this.AboutMe = AboutMe;
        }

        public boolean isIsFollowed() {
            return IsFollowed;
        }

        public void setIsFollowed(boolean IsFollowed) {
            this.IsFollowed = IsFollowed;
        }

        public String getCountry_Name() {
            return Country_Name;
        }

        public void setCountry_Name(String Country_Name) {
            this.Country_Name = Country_Name;
        }

        public String getState_Name() {
            return State_Name;
        }

        public void setState_Name(String State_Name) {
            this.State_Name = State_Name;
        }

        public String getCity_Name() {
            return City_Name;
        }

        public void setCity_Name(String City_Name) {
            this.City_Name = City_Name;
        }

        public String getTahsil_Name() {
            return Tahsil_Name;
        }

        public void setTahsil_Name(String Tahsil_Name) {
            this.Tahsil_Name = Tahsil_Name;
        }

        public int getLikesCount() {
            return LikesCount;
        }

        public void setLikesCount(int LikesCount) {
            this.LikesCount = LikesCount;
        }

        public int getCommentCount() {
            return CommentCount;
        }

        public void setCommentCount(int CommentCount) {
            this.CommentCount = CommentCount;
        }

        public boolean isIsSaved() {
            return IsSaved;
        }

        public void setIsSaved(boolean IsSaved) {
            this.IsSaved = IsSaved;
        }

        public boolean isIsReport() {
            return IsReport;
        }

        public void setIsReport(boolean IsReport) {
            this.IsReport = IsReport;
        }

        public boolean isIsLike() {
            return IsLike;
        }

        public void setIsLike(boolean IsLike) {
            this.IsLike = IsLike;
        }

        public boolean isIsComment() {
            return IsComment;
        }

        public void setIsComment(boolean IsComment) {
            this.IsComment = IsComment;
        }
    }
}
