package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestGetComments {


    /**
     * IsVerify : true
     * NewsId : 1
     * LoggedInUserId : 78e50c5b-3e54-4d26-80ea-acc894a6ab37
     */

    private boolean IsVerify;
    private int NewsId;
    private String LoggedInUserId;

    public boolean isIsVerify() {
        return IsVerify;
    }

    public void setIsVerify(boolean IsVerify) {
        this.IsVerify = IsVerify;
    }

    public int getNewsId() {
        return NewsId;
    }

    public void setNewsId(int NewsId) {
        this.NewsId = NewsId;
    }

    public String getLoggedInUserId() {
        return LoggedInUserId;
    }

    public void setLoggedInUserId(String LoggedInUserId) {
        this.LoggedInUserId = LoggedInUserId;
    }
}
