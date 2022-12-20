package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class NewVideoMentionModelClass {


    /**
     * Status : true
     * Message : Success
     * Data : {"PagedRecord":[{"UserId":"00252404-c5be-45f8-82f4-6fed28e31d5c","Avatar":"http://nmnews.uislick.com/images/profile_images/user_default.jpg","UserName":"Sundar Pichai ","Id":4,"Title":"@sundarpichai","AddedOn":"2021-07-03T23:20:29.483","IsActive":true,"TagType":"USER"},{"UserId":"983b14fa-73b2-40f7-b359-9f2ac0d32aea","Avatar":"http://nmnews.uislick.com/images/profile_images/user_default.jpg","UserName":"Sundar Pichai ","Id":4,"Title":"@sundarpichai","AddedOn":"2021-07-03T23:20:29.483","IsActive":true,"TagType":"USER"}],"TotalCount":2,"recordsTotal":2,"recordsFiltered":2,"Take":0,"Skip":0,"iTotalRecords":2,"iTotalDisplayRecords":2}
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
         * PagedRecord : [{"UserId":"00252404-c5be-45f8-82f4-6fed28e31d5c","Avatar":"http://nmnews.uislick.com/images/profile_images/user_default.jpg","UserName":"Sundar Pichai ","Id":4,"Title":"@sundarpichai","AddedOn":"2021-07-03T23:20:29.483","IsActive":true,"TagType":"USER"},{"UserId":"983b14fa-73b2-40f7-b359-9f2ac0d32aea","Avatar":"http://nmnews.uislick.com/images/profile_images/user_default.jpg","UserName":"Sundar Pichai ","Id":4,"Title":"@sundarpichai","AddedOn":"2021-07-03T23:20:29.483","IsActive":true,"TagType":"USER"}]
         * TotalCount : 2
         * recordsTotal : 2
         * recordsFiltered : 2
         * Take : 0
         * Skip : 0
         * iTotalRecords : 2
         * iTotalDisplayRecords : 2
         */

        private int TotalCount;
        private int recordsTotal;
        private int recordsFiltered;
        private int Take;
        private int Skip;
        private int iTotalRecords;
        private int iTotalDisplayRecords;
        private List<PagedRecordBean> PagedRecord;

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public int getRecordsTotal() {
            return recordsTotal;
        }

        public void setRecordsTotal(int recordsTotal) {
            this.recordsTotal = recordsTotal;
        }

        public int getRecordsFiltered() {
            return recordsFiltered;
        }

        public void setRecordsFiltered(int recordsFiltered) {
            this.recordsFiltered = recordsFiltered;
        }

        public int getTake() {
            return Take;
        }

        public void setTake(int Take) {
            this.Take = Take;
        }

        public int getSkip() {
            return Skip;
        }

        public void setSkip(int Skip) {
            this.Skip = Skip;
        }

        public int getITotalRecords() {
            return iTotalRecords;
        }

        public void setITotalRecords(int iTotalRecords) {
            this.iTotalRecords = iTotalRecords;
        }

        public int getITotalDisplayRecords() {
            return iTotalDisplayRecords;
        }

        public void setITotalDisplayRecords(int iTotalDisplayRecords) {
            this.iTotalDisplayRecords = iTotalDisplayRecords;
        }

        public List<PagedRecordBean> getPagedRecord() {
            return PagedRecord;
        }

        public void setPagedRecord(List<PagedRecordBean> PagedRecord) {
            this.PagedRecord = PagedRecord;
        }

        public static class PagedRecordBean implements Serializable {
            /**
             * UserId : 00252404-c5be-45f8-82f4-6fed28e31d5c
             * Avatar : http://nmnews.uislick.com/images/profile_images/user_default.jpg
             * UserName : Sundar Pichai
             * Id : 4
             * Title : @sundarpichai
             * AddedOn : 2021-07-03T23:20:29.483
             * IsActive : true
             * TagType : USER
             */

            private String UserId;
            private String Avatar;
            private String UserName;
            private int Id;
            private String Title;
            private String AddedOn;
            private boolean IsActive;
            private String TagType;

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
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

            public String getAddedOn() {
                return AddedOn;
            }

            public void setAddedOn(String AddedOn) {
                this.AddedOn = AddedOn;
            }

            public boolean isIsActive() {
                return IsActive;
            }

            public void setIsActive(boolean IsActive) {
                this.IsActive = IsActive;
            }

            public String getTagType() {
                return TagType;
            }

            public void setTagType(String TagType) {
                this.TagType = TagType;
            }
        }
    }
}
