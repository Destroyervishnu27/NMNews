package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestGetSaveNews {

    /**
     * UserId : f0523348-0c2f-44e4-b0cd-c5d5d4f66ada
     * PageIndex : 0
     * PageOffset : 50
     */

    private String UserId;
    private int PageIndex;
    private int PageOffset;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getPageOffset() {
        return PageOffset;
    }

    public void setPageOffset(int PageOffset) {
        this.PageOffset = PageOffset;
    }
}
