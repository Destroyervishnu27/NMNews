package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestReportModel {

    /**
     * NewsId : 2
     * UserId : 78e50c5b-3e54-4d26-80ea-acc894a6ab37
     * SubReason : Abuse
     * Reason : xxxxxx
     */

    private int NewsId;
    private String UserId;
    private String SubReason;
    private String Reason;

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

    public String getSubReason() {
        return SubReason;
    }

    public void setSubReason(String SubReason) {
        this.SubReason = SubReason;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }
}
