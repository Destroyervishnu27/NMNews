package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestGetNewsById {

    /**
     * UserId : 6fc00a95-dc1f-473d-b324-a990a9543498
     * NewsId : 108
     */

    private String UserId;
    private int NewsId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public int getNewsId() {
        return NewsId;
    }

    public void setNewsId(int NewsId) {
        this.NewsId = NewsId;
    }
}
