package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class NewVideoHashtagModelClass {

    /**
     * Status : true
     * Message : Success
     * Data : {"PagedRecord":[{"Id":3,"Title":"#corona-news","AddedOn":"2021-07-01T19:03:52.563","IsActive":true,"TagType":"HASH"}],"TotalCount":1,"recordsTotal":1,"recordsFiltered":1,"Take":0,"Skip":0,"iTotalRecords":1,"iTotalDisplayRecords":1}
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
         * PagedRecord : [{"Id":3,"Title":"#corona-news","AddedOn":"2021-07-01T19:03:52.563","IsActive":true,"TagType":"HASH"}]
         * TotalCount : 1
         * recordsTotal : 1
         * recordsFiltered : 1
         * Take : 0
         * Skip : 0
         * iTotalRecords : 1
         * iTotalDisplayRecords : 1
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
             * Id : 3
             * Title : #corona-news
             * AddedOn : 2021-07-01T19:03:52.563
             * IsActive : true
             * TagType : HASH
             */

            private int Id;
            private String Title;
            private String AddedOn;
            private boolean IsActive;
            private String TagType;

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
