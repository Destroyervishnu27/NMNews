package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;

public class UploadNewsModel {


    /**
     * Status : true
     * Message : Video Successfully Saved
     * Data : {"Id":0,"NewsId":0,"ImageUrl":"7c10b639-def9-401d-bc87-0761c299e90f_img.jpg","IsActive":true,"AddedOn":null,"MediaType":"VIDEO","MediaSource":"SERVER","MediaSize":9.56,"SizeUnit":"MB","VideoUrl":"7c10b639-def9-401d-bc87-0761c299e90f.mp4","VideoFullPath":"http://nmnews.uislick.com/videos/7c10b639-def9-401d-bc87-0761c299e90f.mp4","ImageFullPath":"http://nmnews.uislick.com/videos_thumbnails/7c10b639-def9-401d-bc87-0761c299e90f_img.jpg"}
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
         * Id : 0
         * NewsId : 0
         * ImageUrl : 7c10b639-def9-401d-bc87-0761c299e90f_img.jpg
         * IsActive : true
         * AddedOn : null
         * MediaType : VIDEO
         * MediaSource : SERVER
         * MediaSize : 9.56
         * SizeUnit : MB
         * VideoUrl : 7c10b639-def9-401d-bc87-0761c299e90f.mp4
         * VideoFullPath : http://nmnews.uislick.com/videos/7c10b639-def9-401d-bc87-0761c299e90f.mp4
         * ImageFullPath : http://nmnews.uislick.com/videos_thumbnails/7c10b639-def9-401d-bc87-0761c299e90f_img.jpg
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
        private String VideoId;
        private String VideoUrl;
        private String VideoFullPath;
        private String ImageFullPath;
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

        public String getVideoId() {
            return VideoId;
        }

        public void setVideoId(String VideoId) {
            this.VideoId = VideoId;
        }

        public String getVideoUrl() {
            return VideoUrl;
        }

        public void setVideoUrl(String VideoUrl) {
            this.VideoUrl = VideoUrl;
        }

        public String getVideoFullPath() {
            return VideoFullPath;
        }

        public void setVideoFullPath(String VideoFullPath) {
            this.VideoFullPath = VideoFullPath;
        }

        public String getImageFullPath() {
            return ImageFullPath;
        }

        public void setImageFullPath(String ImageFullPath) {
            this.ImageFullPath = ImageFullPath;
        }
    }
}
