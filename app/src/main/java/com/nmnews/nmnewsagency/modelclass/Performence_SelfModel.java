package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class Performence_SelfModel {

    /**
     * Status : true
     * Message : Success
     * Data : [{"Avatar":"https://lh3.googleusercontent.com/a-/AOh14GjxnRZ14OyGNwxCsAVoevd07O0SyKwYwKGznXqyKA","UserName":"@mohitedilip4","FullName":"dilip mohite - sai entertainment ","UserId":"a6c1f2c2-ae90-4046-80c0-95f834181de3","NewsCount":0,"Score":0},{"Avatar":"https://lh3.googleusercontent.com/a-/AOh14GjBxBHPTxY9FSPytII_kCR_Rcnr-DE1Pt-6iaaskA","UserName":"@rajuthorat23320","FullName":"Raju Thorat ","UserId":"885b459b-6406-490a-b6ab-14766c958b65","NewsCount":0,"Score":0},{"Avatar":"https://lh3.googleusercontent.com/a-/AOh14GiCJqWfyPfF9CfVQyLmNSMeF1mhxml7z8lPGp-i","UserName":"@mahamadrajuddinshaikh3286","FullName":"Mahamad Rajuddin Shaikh ","UserId":"d3eeb0e1-315b-42e7-9b8a-685380b206db","NewsCount":0,"Score":0},{"Avatar":"null","UserName":"@hemantvyasyuth","FullName":"Hemant Vyas ","UserId":"5f6d6384-1dad-4fc8-9f6c-8f8f6e356b86","NewsCount":0,"Score":0},{"Avatar":"null","UserName":"@jamirsanadi121","FullName":"Jamir Sanadi ","UserId":"fcc6d504-200a-41e5-bf96-8902d302b4fc","NewsCount":0,"Score":0},{"Avatar":"http://nmnews.uislick.com/images//profile_images/bd42d30d-d2c3-46a2-8e95-0ca6e23cc1f4.png","UserName":"@ashokpawartv","FullName":"Ashok  Pawar R","UserId":"a6730420-c310-44a4-8ecf-6c8d34fc9f2c","NewsCount":0,"Score":70}]
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
         * Avatar : https://lh3.googleusercontent.com/a-/AOh14GjxnRZ14OyGNwxCsAVoevd07O0SyKwYwKGznXqyKA
         * UserName : @mohitedilip4
         * FullName : dilip mohite - sai entertainment
         * UserId : a6c1f2c2-ae90-4046-80c0-95f834181de3
         * NewsCount : 0
         * Score : 0
         */

        private String Avatar;
        private String UserName;
        private String FullName;
        private String UserId;
        private int NewsCount;
        private int Score;

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

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public int getNewsCount() {
            return NewsCount;
        }

        public void setNewsCount(int NewsCount) {
            this.NewsCount = NewsCount;
        }

        public int getScore() {
            return Score;
        }

        public void setScore(int Score) {
            this.Score = Score;
        }
    }
}
