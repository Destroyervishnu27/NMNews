package com.nmnews.nmnewsagency.model;

import java.io.Serializable;
import java.util.List;


public class LikesListModel implements Serializable {

    /**
     * Status : true
     * Message : Success
     * Data : {"Status":true,"Message":"Success","Data":[{"Id":833,"UserId":"df1adea6-71f8-4722-abe1-fffc1fc6c4f9","FullName":"Vishnu Saini ","UserName":"@vvvvvvish27","Avatar":"https://graph.facebook.com/4067942646659380/picture?type=normal","IsFollowing":false},{"Id":832,"UserId":"df1adea6-71f8-4722-abe1-fffc1fc6c4f9","FullName":"Vishnu Saini ","UserName":"@vvvvvvish27","Avatar":"https://graph.facebook.com/4067942646659380/picture?type=normal","IsFollowing":false}]}
     */

    private Boolean Status;
    private String Message;
    private DataDTOX Data;

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

    public DataDTOX getData() {
        return Data;
    }

    public void setData(DataDTOX data) {
        Data = data;
    }

    public static class DataDTOX implements Serializable {
        /**
         * Status : true
         * Message : Success
         * Data : [{"Id":833,"UserId":"df1adea6-71f8-4722-abe1-fffc1fc6c4f9","FullName":"Vishnu Saini ","UserName":"@vvvvvvish27","Avatar":"https://graph.facebook.com/4067942646659380/picture?type=normal","IsFollowing":false},{"Id":832,"UserId":"df1adea6-71f8-4722-abe1-fffc1fc6c4f9","FullName":"Vishnu Saini ","UserName":"@vvvvvvish27","Avatar":"https://graph.facebook.com/4067942646659380/picture?type=normal","IsFollowing":false}]
         */

        private Boolean Status;
        private String Message;
        private List<DataDTO> Data;

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

        public List<DataDTO> getData() {
            return Data;
        }

        public void setData(List<DataDTO> data) {
            Data = data;
        }

        public static class DataDTO implements Serializable {
            public Integer getId() {
                return Id;
            }

            public void setId(Integer id) {
                Id = id;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String userId) {
                UserId = userId;
            }

            public String getFullName() {
                return FullName;
            }

            public void setFullName(String fullName) {
                FullName = fullName;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String userName) {
                UserName = userName;
            }

            public String getAvatar() {
                return Avatar;
            }

            public void setAvatar(String avatar) {
                Avatar = avatar;
            }

            public Boolean getFollowing() {
                return IsFollowing;
            }

            public void setFollowing(Boolean following) {
                IsFollowing = following;
            }

            /**
             * Id : 833
             * UserId : df1adea6-71f8-4722-abe1-fffc1fc6c4f9
             * FullName : Vishnu Saini
             * UserName : @vvvvvvish27
             * Avatar : https://graph.facebook.com/4067942646659380/picture?type=normal
             * IsFollowing : false
             */

            private Integer Id;
            private String UserId;
            private String FullName;
            private String UserName;
            private String Avatar;
            private Boolean IsFollowing;
        }
    }
}
