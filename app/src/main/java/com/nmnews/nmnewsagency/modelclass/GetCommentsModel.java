package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class GetCommentsModel {


    /**
     * Status : true
     * Message : Success
     * Data : [{"FirstName":"jayanti saini","LastName":"","Avatar":"http://nmnews.uislick.com/images/profile_images/user_default.jpg","CanDelete":false,"Id":6,"NewsId":1,"UserId":"f0523348-0c2f-44e4-b0cd-c5d5d4f66ada","Comment":"Good News","ParentCommentId":1,"LevelId":0,"Verified":true,"AddedOn":"2021-07-09T15:19:06.6"},{"FirstName":"jayanti saini","LastName":"","Avatar":"http://nmnews.uislick.com/images/profile_images/user_default.jpg","CanDelete":false,"Id":5,"NewsId":1,"UserId":"f0523348-0c2f-44e4-b0cd-c5d5d4f66ada","Comment":"Good News","ParentCommentId":1,"LevelId":0,"Verified":true,"AddedOn":"2021-07-09T15:18:49.73"},{"FirstName":null,"LastName":null,"Avatar":"http://nmnews.uislick.com/images/profile_images/user_default.jpg","CanDelete":true,"Id":4,"NewsId":1,"UserId":"78e50c5b-3e54-4d26-80ea-acc894a6ab37","Comment":"Good News 11","ParentCommentId":1,"LevelId":0,"Verified":true,"AddedOn":"2021-07-09T15:17:21.23"}]
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
         * FirstName : jayanti saini
         * LastName :
         * Avatar : http://nmnews.uislick.com/images/profile_images/user_default.jpg
         * CanDelete : false
         * Id : 6
         * NewsId : 1
         * UserId : f0523348-0c2f-44e4-b0cd-c5d5d4f66ada
         * Comment : Good News
         * ParentCommentId : 1
         * LevelId : 0
         * Verified : true
         * AddedOn : 2021-07-09T15:19:06.6
         */

        private String FirstName;
        private String LastName;
        private String Avatar;
        private boolean CanDelete;
        private int Id;
        private int NewsId;
        private String UserId;
        private String Comment;
        private int ParentCommentId;
        private int LevelId;
        private boolean Verified;
        private String AddedOn;

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String LastName) {
            this.LastName = LastName;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public boolean isCanDelete() {
            return CanDelete;
        }

        public void setCanDelete(boolean CanDelete) {
            this.CanDelete = CanDelete;
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

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getComment() {
            return Comment;
        }

        public void setComment(String Comment) {
            this.Comment = Comment;
        }

        public int getParentCommentId() {
            return ParentCommentId;
        }

        public void setParentCommentId(int ParentCommentId) {
            this.ParentCommentId = ParentCommentId;
        }

        public int getLevelId() {
            return LevelId;
        }

        public void setLevelId(int LevelId) {
            this.LevelId = LevelId;
        }

        public boolean isVerified() {
            return Verified;
        }

        public void setVerified(boolean Verified) {
            this.Verified = Verified;
        }

        public String getAddedOn() {
            return AddedOn;
        }

        public void setAddedOn(String AddedOn) {
            this.AddedOn = AddedOn;
        }
    }
}
