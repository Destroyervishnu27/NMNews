package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class PerformceDistrctModel {

    /**
     * Status : true
     * Message : Success
     * Data : {"NEWSCNT":13,"CityNews":[{"NewsId":1027,"Title":"test","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""},{"NewsId":675,"Title":"New Cheating Idea_4","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""}],"StateNews":[{"NewsId":1027,"Title":"test","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""},{"NewsId":675,"Title":"New Cheating Idea_4","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""},{"NewsId":710,"Title":"New Cheating Idea_4 @cheating_raj , #cheat_raj chhsdhsadh @cheating_raj_reet ","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""},{"NewsId":674,"Title":"New Cheating Idea_3","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""}]}
     */

    private boolean Status;
    private String Message;
    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * NEWSCNT : 13
         * CityNews : [{"NewsId":1027,"Title":"test","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""},{"NewsId":675,"Title":"New Cheating Idea_4","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""}]
         * StateNews : [{"NewsId":1027,"Title":"test","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""},{"NewsId":675,"Title":"New Cheating Idea_4","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""},{"NewsId":710,"Title":"New Cheating Idea_4 @cheating_raj , #cheat_raj chhsdhsadh @cheating_raj_reet ","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""},{"NewsId":674,"Title":"New Cheating Idea_3","Description":null,"ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":null,"MediaType":null,"MediaSource":null,"VideoUrl":null,"Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false,"ChatId":null,"VideoId":null,"StatusName":null,"UserType":null,"Actions":null,"AutoPlay":false,"DeviceToken":null,"NewsReportCount":0,"SendNotification":false,"RecordType":"","RedirectUrl":""}]
         */

        private int NEWSCNT;
        private List<CityNewsBean> CityNews;
        private List<StateNewsBean> StateNews;

        public int getNEWSCNT() {
            return NEWSCNT;
        }

        public void setNEWSCNT(int NEWSCNT) {
            this.NEWSCNT = NEWSCNT;
        }

        public List<CityNewsBean> getCityNews() {
            return CityNews;
        }

        public void setCityNews(List<CityNewsBean> CityNews) {
            this.CityNews = CityNews;
        }

        public List<StateNewsBean> getStateNews() {
            return StateNews;
        }

        public void setStateNews(List<StateNewsBean> StateNews) {
            this.StateNews = StateNews;
        }

        public static class CityNewsBean implements Serializable {
            /**
             * NewsId : 1027
             * Title : test
             * Description : null
             * ViewCount : 0
             * AddedOn : null
             * IsTranding : false
             * IsPopular : false
             * UserId : null
             * IsBreakingNews : false
             * NewsType : 0
             * ImageUrl : null
             * MediaType : null
             * MediaSource : null
             * VideoUrl : null
             * Avatar : null
             * UserName : null
             * AboutMe : null
             * IsFollowed : false
             * Country_Name : null
             * State_Name : null
             * City_Name : null
             * Tahsil_Name : null
             * LikesCount : 0
             * CommentCount : 0
             * IsSaved : false
             * IsReport : false
             * IsLike : false
             * IsComment : false
             * ChatId : null
             * VideoId : null
             * StatusName : null
             * UserType : null
             * Actions : null
             * AutoPlay : false
             * DeviceToken : null
             * NewsReportCount : 0
             * SendNotification : false
             * RecordType :
             * RedirectUrl :
             */

            private int NewsId;
            private String Title;
            private Object Description;
            private int ViewCount;
            private Object AddedOn;
            private boolean IsTranding;
            private boolean IsPopular;
            private Object UserId;
            private boolean IsBreakingNews;
            private int NewsType;
            private Object ImageUrl;
            private Object MediaType;
            private Object MediaSource;
            private Object VideoUrl;
            private Object Avatar;
            private Object UserName;
            private Object AboutMe;
            private boolean IsFollowed;
            private Object Country_Name;
            private Object State_Name;
            private Object City_Name;
            private Object Tahsil_Name;
            private int LikesCount;
            private int CommentCount;
            private boolean IsSaved;
            private boolean IsReport;
            private boolean IsLike;
            private boolean IsComment;
            private Object ChatId;
            private Object VideoId;
            private Object StatusName;
            private Object UserType;
            private Object Actions;
            private boolean AutoPlay;
            private Object DeviceToken;
            private int NewsReportCount;
            private boolean SendNotification;
            private String RecordType;
            private String RedirectUrl;

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

            public Object getDescription() {
                return Description;
            }

            public void setDescription(Object Description) {
                this.Description = Description;
            }

            public int getViewCount() {
                return ViewCount;
            }

            public void setViewCount(int ViewCount) {
                this.ViewCount = ViewCount;
            }

            public Object getAddedOn() {
                return AddedOn;
            }

            public void setAddedOn(Object AddedOn) {
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

            public Object getUserId() {
                return UserId;
            }

            public void setUserId(Object UserId) {
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

            public Object getImageUrl() {
                return ImageUrl;
            }

            public void setImageUrl(Object ImageUrl) {
                this.ImageUrl = ImageUrl;
            }

            public Object getMediaType() {
                return MediaType;
            }

            public void setMediaType(Object MediaType) {
                this.MediaType = MediaType;
            }

            public Object getMediaSource() {
                return MediaSource;
            }

            public void setMediaSource(Object MediaSource) {
                this.MediaSource = MediaSource;
            }

            public Object getVideoUrl() {
                return VideoUrl;
            }

            public void setVideoUrl(Object VideoUrl) {
                this.VideoUrl = VideoUrl;
            }

            public Object getAvatar() {
                return Avatar;
            }

            public void setAvatar(Object Avatar) {
                this.Avatar = Avatar;
            }

            public Object getUserName() {
                return UserName;
            }

            public void setUserName(Object UserName) {
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

            public Object getCountry_Name() {
                return Country_Name;
            }

            public void setCountry_Name(Object Country_Name) {
                this.Country_Name = Country_Name;
            }

            public Object getState_Name() {
                return State_Name;
            }

            public void setState_Name(Object State_Name) {
                this.State_Name = State_Name;
            }

            public Object getCity_Name() {
                return City_Name;
            }

            public void setCity_Name(Object City_Name) {
                this.City_Name = City_Name;
            }

            public Object getTahsil_Name() {
                return Tahsil_Name;
            }

            public void setTahsil_Name(Object Tahsil_Name) {
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

            public Object getChatId() {
                return ChatId;
            }

            public void setChatId(Object ChatId) {
                this.ChatId = ChatId;
            }

            public Object getVideoId() {
                return VideoId;
            }

            public void setVideoId(Object VideoId) {
                this.VideoId = VideoId;
            }

            public Object getStatusName() {
                return StatusName;
            }

            public void setStatusName(Object StatusName) {
                this.StatusName = StatusName;
            }

            public Object getUserType() {
                return UserType;
            }

            public void setUserType(Object UserType) {
                this.UserType = UserType;
            }

            public Object getActions() {
                return Actions;
            }

            public void setActions(Object Actions) {
                this.Actions = Actions;
            }

            public boolean isAutoPlay() {
                return AutoPlay;
            }

            public void setAutoPlay(boolean AutoPlay) {
                this.AutoPlay = AutoPlay;
            }

            public Object getDeviceToken() {
                return DeviceToken;
            }

            public void setDeviceToken(Object DeviceToken) {
                this.DeviceToken = DeviceToken;
            }

            public int getNewsReportCount() {
                return NewsReportCount;
            }

            public void setNewsReportCount(int NewsReportCount) {
                this.NewsReportCount = NewsReportCount;
            }

            public boolean isSendNotification() {
                return SendNotification;
            }

            public void setSendNotification(boolean SendNotification) {
                this.SendNotification = SendNotification;
            }

            public String getRecordType() {
                return RecordType;
            }

            public void setRecordType(String RecordType) {
                this.RecordType = RecordType;
            }

            public String getRedirectUrl() {
                return RedirectUrl;
            }

            public void setRedirectUrl(String RedirectUrl) {
                this.RedirectUrl = RedirectUrl;
            }
        }

        public static class StateNewsBean implements Serializable {
            /**
             * NewsId : 1027
             * Title : test
             * Description : null
             * ViewCount : 0
             * AddedOn : null
             * IsTranding : false
             * IsPopular : false
             * UserId : null
             * IsBreakingNews : false
             * NewsType : 0
             * ImageUrl : null
             * MediaType : null
             * MediaSource : null
             * VideoUrl : null
             * Avatar : null
             * UserName : null
             * AboutMe : null
             * IsFollowed : false
             * Country_Name : null
             * State_Name : null
             * City_Name : null
             * Tahsil_Name : null
             * LikesCount : 0
             * CommentCount : 0
             * IsSaved : false
             * IsReport : false
             * IsLike : false
             * IsComment : false
             * ChatId : null
             * VideoId : null
             * StatusName : null
             * UserType : null
             * Actions : null
             * AutoPlay : false
             * DeviceToken : null
             * NewsReportCount : 0
             * SendNotification : false
             * RecordType :
             * RedirectUrl :
             */

            private int NewsId;
            private String Title;
            private Object Description;
            private int ViewCount;
            private Object AddedOn;
            private boolean IsTranding;
            private boolean IsPopular;
            private Object UserId;
            private boolean IsBreakingNews;
            private int NewsType;
            private Object ImageUrl;
            private Object MediaType;
            private Object MediaSource;
            private Object VideoUrl;
            private Object Avatar;
            private Object UserName;
            private Object AboutMe;
            private boolean IsFollowed;
            private Object Country_Name;
            private Object State_Name;
            private Object City_Name;
            private Object Tahsil_Name;
            private int LikesCount;
            private int CommentCount;
            private boolean IsSaved;
            private boolean IsReport;
            private boolean IsLike;
            private boolean IsComment;
            private Object ChatId;
            private Object VideoId;
            private Object StatusName;
            private Object UserType;
            private Object Actions;
            private boolean AutoPlay;
            private Object DeviceToken;
            private int NewsReportCount;
            private boolean SendNotification;
            private String RecordType;
            private String RedirectUrl;

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

            public Object getDescription() {
                return Description;
            }

            public void setDescription(Object Description) {
                this.Description = Description;
            }

            public int getViewCount() {
                return ViewCount;
            }

            public void setViewCount(int ViewCount) {
                this.ViewCount = ViewCount;
            }

            public Object getAddedOn() {
                return AddedOn;
            }

            public void setAddedOn(Object AddedOn) {
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

            public Object getUserId() {
                return UserId;
            }

            public void setUserId(Object UserId) {
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

            public Object getImageUrl() {
                return ImageUrl;
            }

            public void setImageUrl(Object ImageUrl) {
                this.ImageUrl = ImageUrl;
            }

            public Object getMediaType() {
                return MediaType;
            }

            public void setMediaType(Object MediaType) {
                this.MediaType = MediaType;
            }

            public Object getMediaSource() {
                return MediaSource;
            }

            public void setMediaSource(Object MediaSource) {
                this.MediaSource = MediaSource;
            }

            public Object getVideoUrl() {
                return VideoUrl;
            }

            public void setVideoUrl(Object VideoUrl) {
                this.VideoUrl = VideoUrl;
            }

            public Object getAvatar() {
                return Avatar;
            }

            public void setAvatar(Object Avatar) {
                this.Avatar = Avatar;
            }

            public Object getUserName() {
                return UserName;
            }

            public void setUserName(Object UserName) {
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

            public Object getCountry_Name() {
                return Country_Name;
            }

            public void setCountry_Name(Object Country_Name) {
                this.Country_Name = Country_Name;
            }

            public Object getState_Name() {
                return State_Name;
            }

            public void setState_Name(Object State_Name) {
                this.State_Name = State_Name;
            }

            public Object getCity_Name() {
                return City_Name;
            }

            public void setCity_Name(Object City_Name) {
                this.City_Name = City_Name;
            }

            public Object getTahsil_Name() {
                return Tahsil_Name;
            }

            public void setTahsil_Name(Object Tahsil_Name) {
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

            public Object getChatId() {
                return ChatId;
            }

            public void setChatId(Object ChatId) {
                this.ChatId = ChatId;
            }

            public Object getVideoId() {
                return VideoId;
            }

            public void setVideoId(Object VideoId) {
                this.VideoId = VideoId;
            }

            public Object getStatusName() {
                return StatusName;
            }

            public void setStatusName(Object StatusName) {
                this.StatusName = StatusName;
            }

            public Object getUserType() {
                return UserType;
            }

            public void setUserType(Object UserType) {
                this.UserType = UserType;
            }

            public Object getActions() {
                return Actions;
            }

            public void setActions(Object Actions) {
                this.Actions = Actions;
            }

            public boolean isAutoPlay() {
                return AutoPlay;
            }

            public void setAutoPlay(boolean AutoPlay) {
                this.AutoPlay = AutoPlay;
            }

            public Object getDeviceToken() {
                return DeviceToken;
            }

            public void setDeviceToken(Object DeviceToken) {
                this.DeviceToken = DeviceToken;
            }

            public int getNewsReportCount() {
                return NewsReportCount;
            }

            public void setNewsReportCount(int NewsReportCount) {
                this.NewsReportCount = NewsReportCount;
            }

            public boolean isSendNotification() {
                return SendNotification;
            }

            public void setSendNotification(boolean SendNotification) {
                this.SendNotification = SendNotification;
            }

            public String getRecordType() {
                return RecordType;
            }

            public void setRecordType(String RecordType) {
                this.RecordType = RecordType;
            }

            public String getRedirectUrl() {
                return RedirectUrl;
            }

            public void setRedirectUrl(String RedirectUrl) {
                this.RedirectUrl = RedirectUrl;
            }
        }
    }
}
