package com.nmnews.nmnewsagency.modelclass.RequestModel;

import java.io.Serializable;

public class RequestAddNews {


    /**
     * NewsObj : {"Id":0,"Title":"New Cheating Idea_1","Description":"Cheating ","Suggestion":"News Updated","UserId":"3c37ae72-d7c7-40bf-b2f8-a8705844bc82","IsBreakingNews":true,"NewsType":"1,2,3,4","CountryId":1,"Country_Name":"India","StateId":3,"State_Name":"RAJASTHAN","CityId":483,"City_Name":"JAIPUR","TahsilId":1772,"Tahsil_Name":"JAIPUR","AddressLine_1":"JAIPUR","AddressLin_2":null,"Lat":270.25688,"Long":45.8549,"ZipCode":"302015","UserTags":"@codeslick.in","HashTags":"#latest news,#developer"}
     * NewsMediaObj : {"Id":0,"NewsId":0,"ImageUrl":"https://i.vimeocdn.com/video/1257885052-7f77428cb2e272c30f74861ba0361ce30f67caf993a2a8d91_1920x1080?r=pad","IsActive":true,"AddedOn":null,"MediaType":"VIDEO","MediaSource":"VIMEO","MediaSize":3.75,"SizeUnit":"MB","VideoUrl":"https://player.vimeo.com/external/615938040.sd.mp4?s=4e1921db75622851795fd48c985ca282067e074c&profile_id=164&oauth2_token_id=1539357354","VideoId":"615938040"}
     */

    private NewsObjBean NewsObj;
    private NewsMediaObjBean NewsMediaObj;

    public NewsObjBean getNewsObj() {
        return NewsObj;
    }

    public void setNewsObj(NewsObjBean NewsObj) {
        this.NewsObj = NewsObj;
    }

    public NewsMediaObjBean getNewsMediaObj() {
        return NewsMediaObj;
    }

    public void setNewsMediaObj(NewsMediaObjBean NewsMediaObj) {
        this.NewsMediaObj = NewsMediaObj;
    }

    public static class NewsObjBean implements Serializable {
        /**
         * Id : 0
         * Title : New Cheating Idea_1
         * Description : Cheating
         * Suggestion : News Updated
         * UserId : 3c37ae72-d7c7-40bf-b2f8-a8705844bc82
         * IsBreakingNews : true
         * NewsType : 1,2,3,4
         * CountryId : 1
         * Country_Name : India
         * StateId : 3
         * State_Name : RAJASTHAN
         * CityId : 483
         * City_Name : JAIPUR
         * TahsilId : 1772
         * Tahsil_Name : JAIPUR
         * AddressLine_1 : JAIPUR
         * AddressLin_2 : null
         * Lat : 270.25688
         * Long : 45.8549
         * ZipCode : 302015
         * UserTags : @codeslick.in
         * HashTags : #latest news,#developer
         */

        private int Id;
        private String Title;
        private String Description;
        private String Suggestion;
        private String UserId;
        private boolean IsBreakingNews;
        private String NewsType;
        private int CountryId;
        private String Country_Name;
        private int StateId;
        private String State_Name;
        private int CityId;
        private String City_Name;
        private int TahsilId;
        private String Tahsil_Name;
        private String AddressLine_1;
        private String AddressLin_2;
        private double Lat;
        private double Long;
        private String ZipCode;
        private String UserTags;
        private String HashTags;
        private String DownloadLink;
        private String videoFilePath;

        public String getVideoFilePath() {
            return videoFilePath;
        }

        public void setVideoFilePath(String videoFilePath) {
            this.videoFilePath = videoFilePath;
        }

        public String getDownloadLink() {
            return DownloadLink;
        }

        public void setDownloadLink(String downloadLink) {
            DownloadLink = downloadLink;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
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

        public String getSuggestion() {
            return Suggestion;
        }

        public void setSuggestion(String Suggestion) {
            this.Suggestion = Suggestion;
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

        public String getNewsType() {
            return NewsType;
        }

        public void setNewsType(String NewsType) {
            this.NewsType = NewsType;
        }

        public int getCountryId() {
            return CountryId;
        }

        public void setCountryId(int CountryId) {
            this.CountryId = CountryId;
        }

        public String getCountry_Name() {
            return Country_Name;
        }

        public void setCountry_Name(String Country_Name) {
            this.Country_Name = Country_Name;
        }

        public int getStateId() {
            return StateId;
        }

        public void setStateId(int StateId) {
            this.StateId = StateId;
        }

        public String getState_Name() {
            return State_Name;
        }

        public void setState_Name(String State_Name) {
            this.State_Name = State_Name;
        }

        public int getCityId() {
            return CityId;
        }

        public void setCityId(int CityId) {
            this.CityId = CityId;
        }

        public String getCity_Name() {
            return City_Name;
        }

        public void setCity_Name(String City_Name) {
            this.City_Name = City_Name;
        }

        public int getTahsilId() {
            return TahsilId;
        }

        public void setTahsilId(int TahsilId) {
            this.TahsilId = TahsilId;
        }

        public String getTahsil_Name() {
            return Tahsil_Name;
        }

        public void setTahsil_Name(String Tahsil_Name) {
            this.Tahsil_Name = Tahsil_Name;
        }

        public String getAddressLine_1() {
            return AddressLine_1;
        }

        public void setAddressLine_1(String AddressLine_1) {
            this.AddressLine_1 = AddressLine_1;
        }

        public String getAddressLin_2() {
            return AddressLin_2;
        }

        public void setAddressLin_2(String AddressLin_2) {
            this.AddressLin_2 = AddressLin_2;
        }

        public double getLat() {
            return Lat;
        }

        public void setLat(double Lat) {
            this.Lat = Lat;
        }

        public double getLong() {
            return Long;
        }

        public void setLong(double Long) {
            this.Long = Long;
        }

        public String getZipCode() {
            return ZipCode;
        }

        public void setZipCode(String ZipCode) {
            this.ZipCode = ZipCode;
        }

        public String getUserTags() {
            return UserTags;
        }

        public void setUserTags(String UserTags) {
            this.UserTags = UserTags;
        }

        public String getHashTags() {
            return HashTags;
        }

        public void setHashTags(String HashTags) {
            this.HashTags = HashTags;
        }
    }

    public static class NewsMediaObjBean implements Serializable {
        /**
         * Id : 0
         * NewsId : 0
         * ImageUrl : https://i.vimeocdn.com/video/1257885052-7f77428cb2e272c30f74861ba0361ce30f67caf993a2a8d91_1920x1080?r=pad
         * IsActive : true
         * AddedOn : null
         * MediaType : VIDEO
         * MediaSource : VIMEO
         * MediaSize : 3.75
         * SizeUnit : MB
         * VideoUrl : https://player.vimeo.com/external/615938040.sd.mp4?s=4e1921db75622851795fd48c985ca282067e074c&profile_id=164&oauth2_token_id=1539357354
         * VideoId : 615938040
         */

        private int Id;
        private int NewsId;
        private String ImageUrl;
        private boolean IsActive;
        private Object AddedOn;
        private String MediaType;
        private String MediaSource;
        private double MediaSize;
        private String SizeUnit;
        private String VideoUrl;
        private String VideoId;
        private String DownloadLink;

        public String getDownloadLink() {
            return DownloadLink;
        }

        public void setDownloadLink(String downloadLink) {
            DownloadLink = downloadLink;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getNewsId() {
            return NewsId;
        }

        public void setNewsId(int NewsId) {
            this.NewsId = NewsId;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public Object getAddedOn() {
            return AddedOn;
        }

        public void setAddedOn(Object AddedOn) {
            this.AddedOn = AddedOn;
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

        public double getMediaSize() {
            return MediaSize;
        }

        public void setMediaSize(double MediaSize) {
            this.MediaSize = MediaSize;
        }

        public String getSizeUnit() {
            return SizeUnit;
        }

        public void setSizeUnit(String SizeUnit) {
            this.SizeUnit = SizeUnit;
        }

        public String getVideoUrl() {
            return VideoUrl;
        }

        public void setVideoUrl(String VideoUrl) {
            this.VideoUrl = VideoUrl;
        }

        public String getVideoId() {
            return VideoId;
        }

        public void setVideoId(String VideoId) {
            this.VideoId = VideoId;
        }
    }
}
