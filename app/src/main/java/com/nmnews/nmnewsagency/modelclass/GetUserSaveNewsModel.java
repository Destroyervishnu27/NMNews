package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class GetUserSaveNewsModel {

    /**
     * Status : true
     * Message : Success
     * Data : {"Status":true,"Message":"Success","Data":{"PagedRecord":[{"NewsId":136,"Title":"SDRF ने आधे घंटे में गंगा नदी में डुबने वाले व्यक्ति को निकाला","Description":"News","ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":"http://nmnews.uislick.com/videos_thumbnails/323661ee-1cb8-4aef-8102-899ed67d6135_img.jpg","MediaType":"VIDEO","MediaSource":"SERVER","VideoUrl":"323661ee-1cb8-4aef-8102-899ed67d6135.mp4","Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false},{"NewsId":95,"Title":"ज्यादा कीमत में खाद बेचने वालों की खैर नहीं।\nपकड़े जाने पर होगी कार्यवाही।\n\n","Description":"News","ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":"http://nmnews.uislick.com/videos_thumbnails/a50e4de6-97da-4910-993d-94f51abae515_img.jpg","MediaType":"VIDEO","MediaSource":"SERVER","VideoUrl":"a50e4de6-97da-4910-993d-94f51abae515.mp4","Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false}],"TotalCount":2,"recordsTotal":2,"recordsFiltered":2,"Take":0,"Skip":0,"iTotalRecords":2,"iTotalDisplayRecords":2}}
     */

    private boolean Status;
    private String Message;
    private DataBeanX Data;

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

    public DataBeanX getData() {
        return Data;
    }

    public void setData(DataBeanX Data) {
        this.Data = Data;
    }

    public static class DataBeanX implements Serializable {
        /**
         * Status : true
         * Message : Success
         * Data : {"PagedRecord":[{"NewsId":136,"Title":"SDRF ने आधे घंटे में गंगा नदी में डुबने वाले व्यक्ति को निकाला","Description":"News","ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":"http://nmnews.uislick.com/videos_thumbnails/323661ee-1cb8-4aef-8102-899ed67d6135_img.jpg","MediaType":"VIDEO","MediaSource":"SERVER","VideoUrl":"323661ee-1cb8-4aef-8102-899ed67d6135.mp4","Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false},{"NewsId":95,"Title":"ज्यादा कीमत में खाद बेचने वालों की खैर नहीं।\nपकड़े जाने पर होगी कार्यवाही।\n\n","Description":"News","ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":"http://nmnews.uislick.com/videos_thumbnails/a50e4de6-97da-4910-993d-94f51abae515_img.jpg","MediaType":"VIDEO","MediaSource":"SERVER","VideoUrl":"a50e4de6-97da-4910-993d-94f51abae515.mp4","Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false}],"TotalCount":2,"recordsTotal":2,"recordsFiltered":2,"Take":0,"Skip":0,"iTotalRecords":2,"iTotalDisplayRecords":2}
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
             * PagedRecord : [{"NewsId":136,"Title":"SDRF ने आधे घंटे में गंगा नदी में डुबने वाले व्यक्ति को निकाला","Description":"News","ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":"http://nmnews.uislick.com/videos_thumbnails/323661ee-1cb8-4aef-8102-899ed67d6135_img.jpg","MediaType":"VIDEO","MediaSource":"SERVER","VideoUrl":"323661ee-1cb8-4aef-8102-899ed67d6135.mp4","Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false},{"NewsId":95,"Title":"ज्यादा कीमत में खाद बेचने वालों की खैर नहीं।\nपकड़े जाने पर होगी कार्यवाही।\n\n","Description":"News","ViewCount":0,"AddedOn":null,"IsTranding":false,"IsPopular":false,"UserId":null,"IsBreakingNews":false,"NewsType":0,"ImageUrl":"http://nmnews.uislick.com/videos_thumbnails/a50e4de6-97da-4910-993d-94f51abae515_img.jpg","MediaType":"VIDEO","MediaSource":"SERVER","VideoUrl":"a50e4de6-97da-4910-993d-94f51abae515.mp4","Avatar":null,"UserName":null,"AboutMe":null,"IsFollowed":false,"Country_Name":null,"State_Name":null,"City_Name":null,"Tahsil_Name":null,"LikesCount":0,"CommentCount":0,"IsSaved":false,"IsReport":false,"IsLike":false,"IsComment":false}]
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
                 * NewsId : 136
                 * Title : SDRF ने आधे घंटे में गंगा नदी में डुबने वाले व्यक्ति को निकाला
                 * Description : News
                 * ViewCount : 0
                 * AddedOn : null
                 * IsTranding : false
                 * IsPopular : false
                 * UserId : null
                 * IsBreakingNews : false
                 * NewsType : 0
                 * ImageUrl : http://nmnews.uislick.com/videos_thumbnails/323661ee-1cb8-4aef-8102-899ed67d6135_img.jpg
                 * MediaType : VIDEO
                 * MediaSource : SERVER
                 * VideoUrl : 323661ee-1cb8-4aef-8102-899ed67d6135.mp4
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
                 */

                private int NewsId;
                private String Title;
                private String Description;
                private String VideoId;
                private int ViewCount;
                private Object AddedOn;
                private boolean IsTranding;
                private boolean IsPopular;
                private Object UserId;
                private boolean IsBreakingNews;
                private int NewsType;
                private String ImageUrl;
                private String MediaType;
                private String MediaSource;
                private String VideoUrl;
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
                private String StatusName;

                public String getStatusName() {
                    return StatusName;
                }

                public void setStatusName(String statusName) {
                    StatusName = statusName;
                }

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

                public String getDescription() {
                    return Description;
                }

                public void setDescription(String Description) {
                    this.Description = Description;
                }

                public String getVideoId() {
                    return VideoId;
                }

                public void setVideoId(String VideoId) {
                    this.VideoId = VideoId;
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

                public String getImageUrl() {
                    return ImageUrl;
                }

                public void setImageUrl(String ImageUrl) {
                    this.ImageUrl = ImageUrl;
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

                public String getVideoUrl() {
                    return VideoUrl;
                }

                public void setVideoUrl(String VideoUrl) {
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
            }
        }
    }
}
