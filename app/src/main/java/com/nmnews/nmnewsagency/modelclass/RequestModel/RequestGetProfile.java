package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestGetProfile {

    /**
     * UserId : f0523348-0c2f-44e4-b0cd-c5d5d4f66ada
     * LogedInUserId : f0523348-0c2f-44e4-b0cd-c5d5d4f66ada
     */

    private String UserId;
    private String LogedInUserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getLogedInUserId() {
        return LogedInUserId;
    }

    public void setLogedInUserId(String LogedInUserId) {
        this.LogedInUserId = LogedInUserId;
    }
}
