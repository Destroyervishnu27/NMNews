package com.nmnews.nmnewsagency.model;

import java.io.Serializable;
import java.util.List;

public class DownloadVIdeoNewModel implements Serializable {

    /**
     * Status : true
     * Message : Success
     * Data : {"downloadurl":"https://nmnews.in/temp_videos/722831576.mp4","NewsContentType":[{"Id":1,"Text":"All Content in this video is self-shot","IsActive":true},{"Id":2,"Text":"Did not shoot the content in this video","IsActive":true},{"Id":3,"Text":"All content in this video is real","IsActive":true},{"Id":4,"Text":"All content in this video is symbolic/duplicate","IsActive":true}]}
     */

    private Boolean Status;
    private String Message;
    private DataDTO Data;

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public DataDTO getData() {
        return Data;
    }

    public void setData(DataDTO data) {
        Data = data;
    }

    public static class DataDTO implements Serializable {
        /**
         * downloadurl : https://nmnews.in/temp_videos/722831576.mp4
         * NewsContentType : [{"Id":1,"Text":"All Content in this video is self-shot","IsActive":true},{"Id":2,"Text":"Did not shoot the content in this video","IsActive":true},{"Id":3,"Text":"All content in this video is real","IsActive":true},{"Id":4,"Text":"All content in this video is symbolic/duplicate","IsActive":true}]
         */

        private String downloadurl;
        private List<NewsContentTypeDTO> NewsContentType;

        public String getDownloadurl() {
            return downloadurl;
        }

        public void setDownloadurl(String downloadurl) {
            this.downloadurl = downloadurl;
        }

        public List<NewsContentTypeDTO> getNewsContentType() {
            return NewsContentType;
        }

        public void setNewsContentType(List<NewsContentTypeDTO> newsContentType) {
            NewsContentType = newsContentType;
        }

        public static class NewsContentTypeDTO implements Serializable {
            /**
             * Id : 1
             * Text : All Content in this video is self-shot
             * IsActive : true
             */

            private Integer Id;
            private String Text;
            private Boolean IsActive;

            public Integer getId() {
                return Id;
            }

            public void setId(Integer id) {
                Id = id;
            }

            public String getText() {
                return Text;
            }

            public void setText(String text) {
                Text = text;
            }

            public Boolean getActive() {
                return IsActive;
            }

            public void setActive(Boolean active) {
                IsActive = active;
            }
        }
    }
}
