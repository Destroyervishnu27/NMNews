package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class SearchTopSearchModel {


    /**
     * Status : true
     * Message : Success
     * Data : {"Status":true,"Message":"Success","Data":{"PagedRecord":[{"Id":53,"Title":"#latest-news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":41,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":192,"Title":"तहसील में कोरोना के 31 नए मरीज मिले तो23 मरीज  ठीक हो कर घर लौटे","Avatar":"http://nmnews.uislick.com/videos_thumbnails/xyz.jpg","UserName":"admin@admin.com","searchcount":32,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":54,"Title":"#corona-news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":31,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":191,"Title":"तहसील में कोरोना के 31 नए मरीज मिले तो23 मरीज  ठीक हो कर घर लौटे","Avatar":"http://nmnews.uislick.com/videos_thumbnails/xyz.jpg","UserName":"admin@admin.com","searchcount":28,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":38,"Title":"@boopathijournalist","Avatar":"http://nmnews.uislick.com/images/profile_images/66ee145a-c021-4c2d-b256-c0649ea22af3.jpg","UserName":"KS BoopathiJournalist . ","searchcount":23,"SearchType":"PEOPLE","UserId":"61e2bd4a-d671-46e4-a33a-f968b86fd2bb","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":47,"Title":"@buldanamirror","Avatar":"null","UserName":"Buldana Mirror ","searchcount":17,"SearchType":"PEOPLE","UserId":"93eb372f-c429-4b16-8f20-3b9e586eb822","IsFollowed":false,"Followers":1,"FollowersSuffix":"1"},{"Id":43,"Title":"@sudhirpawar101","Avatar":"https://lh3.googleusercontent.com/a-/AOh14GhkEpsoq2dXlrFkQPqMrK28YFX5oHcBbfrNAernb2I","UserName":"Dnyaneshwari Pawar ","searchcount":15,"SearchType":"PEOPLE","UserId":"e74d9a06-b607-45b8-83bb-788bfe10d431","IsFollowed":false,"Followers":1,"FollowersSuffix":"1"},{"Id":226,"Title":"Havey rain fall In Alwar","Avatar":"http://nmnews.uislick.com/videos_thumbnails/d8a80e31-8499-4cfc-a747-3501f0ebf6a5_img.jpg","UserName":"admin@admin.com","searchcount":15,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":40,"Title":"@vengaboy787","Avatar":"null","UserName":"venhuesen lasith ","searchcount":13,"SearchType":"PEOPLE","UserId":"961a3d9b-780f-4589-8fd4-b44c89dd4e3b","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":225,"Title":"Havey rain fall","Avatar":"http://nmnews.uislick.com/videos_thumbnails/d8a80e31-8499-4cfc-a747-3501f0ebf6a5_img.jpg","UserName":"admin@admin.com","searchcount":11,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":176,"Title":"अमरावती महानगरपालिका मध्ये  स्वच्छता अभियान चे उडाले 3  13","Avatar":"http://nmnews.uislick.com/videos_thumbnails/70646abb-f530-471e-a49c-1f765edf5f31_img.jpg","UserName":"mrkhansalman545","searchcount":10,"SearchType":"VIDEO","UserId":"f5d0bc44-68d2-4149-aabb-ac06ac4dec5a","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":39,"Title":"@pachora951","Avatar":"null","UserName":"Pachora Pachora ","searchcount":5,"SearchType":"PEOPLE","UserId":"8b4fd49d-b6f8-429c-9b14-9ce3ea7b091f","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":64,"Title":"#corona_news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":2,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":63,"Title":"#latest_news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":2,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"}],"TotalCount":15,"recordsTotal":14,"recordsFiltered":14,"Take":0,"Skip":0,"iTotalRecords":15,"iTotalDisplayRecords":15}}
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
         * Data : {"PagedRecord":[{"Id":53,"Title":"#latest-news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":41,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":192,"Title":"तहसील में कोरोना के 31 नए मरीज मिले तो23 मरीज  ठीक हो कर घर लौटे","Avatar":"http://nmnews.uislick.com/videos_thumbnails/xyz.jpg","UserName":"admin@admin.com","searchcount":32,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":54,"Title":"#corona-news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":31,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":191,"Title":"तहसील में कोरोना के 31 नए मरीज मिले तो23 मरीज  ठीक हो कर घर लौटे","Avatar":"http://nmnews.uislick.com/videos_thumbnails/xyz.jpg","UserName":"admin@admin.com","searchcount":28,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":38,"Title":"@boopathijournalist","Avatar":"http://nmnews.uislick.com/images/profile_images/66ee145a-c021-4c2d-b256-c0649ea22af3.jpg","UserName":"KS BoopathiJournalist . ","searchcount":23,"SearchType":"PEOPLE","UserId":"61e2bd4a-d671-46e4-a33a-f968b86fd2bb","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":47,"Title":"@buldanamirror","Avatar":"null","UserName":"Buldana Mirror ","searchcount":17,"SearchType":"PEOPLE","UserId":"93eb372f-c429-4b16-8f20-3b9e586eb822","IsFollowed":false,"Followers":1,"FollowersSuffix":"1"},{"Id":43,"Title":"@sudhirpawar101","Avatar":"https://lh3.googleusercontent.com/a-/AOh14GhkEpsoq2dXlrFkQPqMrK28YFX5oHcBbfrNAernb2I","UserName":"Dnyaneshwari Pawar ","searchcount":15,"SearchType":"PEOPLE","UserId":"e74d9a06-b607-45b8-83bb-788bfe10d431","IsFollowed":false,"Followers":1,"FollowersSuffix":"1"},{"Id":226,"Title":"Havey rain fall In Alwar","Avatar":"http://nmnews.uislick.com/videos_thumbnails/d8a80e31-8499-4cfc-a747-3501f0ebf6a5_img.jpg","UserName":"admin@admin.com","searchcount":15,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":40,"Title":"@vengaboy787","Avatar":"null","UserName":"venhuesen lasith ","searchcount":13,"SearchType":"PEOPLE","UserId":"961a3d9b-780f-4589-8fd4-b44c89dd4e3b","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":225,"Title":"Havey rain fall","Avatar":"http://nmnews.uislick.com/videos_thumbnails/d8a80e31-8499-4cfc-a747-3501f0ebf6a5_img.jpg","UserName":"admin@admin.com","searchcount":11,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":176,"Title":"अमरावती महानगरपालिका मध्ये  स्वच्छता अभियान चे उडाले 3  13","Avatar":"http://nmnews.uislick.com/videos_thumbnails/70646abb-f530-471e-a49c-1f765edf5f31_img.jpg","UserName":"mrkhansalman545","searchcount":10,"SearchType":"VIDEO","UserId":"f5d0bc44-68d2-4149-aabb-ac06ac4dec5a","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":39,"Title":"@pachora951","Avatar":"null","UserName":"Pachora Pachora ","searchcount":5,"SearchType":"PEOPLE","UserId":"8b4fd49d-b6f8-429c-9b14-9ce3ea7b091f","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":64,"Title":"#corona_news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":2,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":63,"Title":"#latest_news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":2,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"}],"TotalCount":15,"recordsTotal":14,"recordsFiltered":14,"Take":0,"Skip":0,"iTotalRecords":15,"iTotalDisplayRecords":15}
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
             * PagedRecord : [{"Id":53,"Title":"#latest-news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":41,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":192,"Title":"तहसील में कोरोना के 31 नए मरीज मिले तो23 मरीज  ठीक हो कर घर लौटे","Avatar":"http://nmnews.uislick.com/videos_thumbnails/xyz.jpg","UserName":"admin@admin.com","searchcount":32,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":54,"Title":"#corona-news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":31,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":191,"Title":"तहसील में कोरोना के 31 नए मरीज मिले तो23 मरीज  ठीक हो कर घर लौटे","Avatar":"http://nmnews.uislick.com/videos_thumbnails/xyz.jpg","UserName":"admin@admin.com","searchcount":28,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":38,"Title":"@boopathijournalist","Avatar":"http://nmnews.uislick.com/images/profile_images/66ee145a-c021-4c2d-b256-c0649ea22af3.jpg","UserName":"KS BoopathiJournalist . ","searchcount":23,"SearchType":"PEOPLE","UserId":"61e2bd4a-d671-46e4-a33a-f968b86fd2bb","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":47,"Title":"@buldanamirror","Avatar":"null","UserName":"Buldana Mirror ","searchcount":17,"SearchType":"PEOPLE","UserId":"93eb372f-c429-4b16-8f20-3b9e586eb822","IsFollowed":false,"Followers":1,"FollowersSuffix":"1"},{"Id":43,"Title":"@sudhirpawar101","Avatar":"https://lh3.googleusercontent.com/a-/AOh14GhkEpsoq2dXlrFkQPqMrK28YFX5oHcBbfrNAernb2I","UserName":"Dnyaneshwari Pawar ","searchcount":15,"SearchType":"PEOPLE","UserId":"e74d9a06-b607-45b8-83bb-788bfe10d431","IsFollowed":false,"Followers":1,"FollowersSuffix":"1"},{"Id":226,"Title":"Havey rain fall In Alwar","Avatar":"http://nmnews.uislick.com/videos_thumbnails/d8a80e31-8499-4cfc-a747-3501f0ebf6a5_img.jpg","UserName":"admin@admin.com","searchcount":15,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":40,"Title":"@vengaboy787","Avatar":"null","UserName":"venhuesen lasith ","searchcount":13,"SearchType":"PEOPLE","UserId":"961a3d9b-780f-4589-8fd4-b44c89dd4e3b","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":225,"Title":"Havey rain fall","Avatar":"http://nmnews.uislick.com/videos_thumbnails/d8a80e31-8499-4cfc-a747-3501f0ebf6a5_img.jpg","UserName":"admin@admin.com","searchcount":11,"SearchType":"VIDEO","UserId":"6fc00a95-dc1f-473d-b324-a990a9543498","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":176,"Title":"अमरावती महानगरपालिका मध्ये  स्वच्छता अभियान चे उडाले 3  13","Avatar":"http://nmnews.uislick.com/videos_thumbnails/70646abb-f530-471e-a49c-1f765edf5f31_img.jpg","UserName":"mrkhansalman545","searchcount":10,"SearchType":"VIDEO","UserId":"f5d0bc44-68d2-4149-aabb-ac06ac4dec5a","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":39,"Title":"@pachora951","Avatar":"null","UserName":"Pachora Pachora ","searchcount":5,"SearchType":"PEOPLE","UserId":"8b4fd49d-b6f8-429c-9b14-9ce3ea7b091f","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":64,"Title":"#corona_news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":2,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"},{"Id":63,"Title":"#latest_news","Avatar":"http://nmnews.uislick.com/videos_thumbnails/","UserName":"","searchcount":2,"SearchType":"HASHTAG","UserId":"","IsFollowed":false,"Followers":0,"FollowersSuffix":"0"}]
             * TotalCount : 15
             * recordsTotal : 14
             * recordsFiltered : 14
             * Take : 0
             * Skip : 0
             * iTotalRecords : 15
             * iTotalDisplayRecords : 15
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
                 * Id : 53
                 * Title : #latest-news
                 * Avatar : http://nmnews.uislick.com/videos_thumbnails/
                 * UserName :
                 * searchcount : 41
                 * SearchType : HASHTAG
                 * UserId :
                 * IsFollowed : false
                 * Followers : 0
                 * FollowersSuffix : 0
                 */

                private int Id;
                private String Title;
                private String Avatar;
                private String UserName;
                private int searchcount;
                private String SearchType;
                private String UserId;
                private boolean IsFollowed;
                private int Followers;
                private String FollowersSuffix;

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

                public int getSearchcount() {
                    return searchcount;
                }

                public void setSearchcount(int searchcount) {
                    this.searchcount = searchcount;
                }

                public String getSearchType() {
                    return SearchType;
                }

                public void setSearchType(String SearchType) {
                    this.SearchType = SearchType;
                }

                public String getUserId() {
                    return UserId;
                }

                public void setUserId(String UserId) {
                    this.UserId = UserId;
                }

                public boolean isIsFollowed() {
                    return IsFollowed;
                }

                public void setIsFollowed(boolean IsFollowed) {
                    this.IsFollowed = IsFollowed;
                }

                public int getFollowers() {
                    return Followers;
                }

                public void setFollowers(int Followers) {
                    this.Followers = Followers;
                }

                public String getFollowersSuffix() {
                    return FollowersSuffix;
                }

                public void setFollowersSuffix(String FollowersSuffix) {
                    this.FollowersSuffix = FollowersSuffix;
                }
            }
        }
    }
}
