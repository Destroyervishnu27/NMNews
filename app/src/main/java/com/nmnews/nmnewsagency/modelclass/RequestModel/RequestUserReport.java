package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestUserReport {

    /**
     * UserId : 78e50c5b-3e54-4d26-80ea-acc894a6ab37
     * WhoseReported : 78e50c5b-3e54-4d26-80ea-acc894a6ab37_2
     * SubReason : Abuse
     * Reason : xxxxxx
     */

    private String UserId;
    private String WhoseReported;
    private String SubReason;
    private String Reason;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getWhoseReported() {
        return WhoseReported;
    }

    public void setWhoseReported(String WhoseReported) {
        this.WhoseReported = WhoseReported;
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
