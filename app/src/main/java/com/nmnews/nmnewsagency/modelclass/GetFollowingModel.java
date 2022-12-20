package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class GetFollowingModel {

    /**
     * Status : true
     * Message : Success
     * Data : [{"Id":38,"UserId":"7b3d10d9-a4a0-433e-b1b7-f139d5d97afe","FullName":"Devendra Rai ","UserName":"@devsinghrai.vikasnagar","Avatar":"https://graph.facebook.com/2926662074275171/picture?type=normal","IsFollowing":false}]
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
         * Id : 38
         * UserId : 7b3d10d9-a4a0-433e-b1b7-f139d5d97afe
         * FullName : Devendra Rai
         * UserName : @devsinghrai.vikasnagar
         * Avatar : https://graph.facebook.com/2926662074275171/picture?type=normal
         * IsFollowing : false
         */

        private int Id;
        private String UserId;
        private String FullName;
        private String UserName;
        private String Avatar;
        private boolean IsFollowing;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public boolean isIsFollowing() {
            return IsFollowing;
        }

        public void setIsFollowing(boolean IsFollowing) {
            this.IsFollowing = IsFollowing;
        }
    }
}
