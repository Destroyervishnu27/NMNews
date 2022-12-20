package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestHashTag {

    /**
     * DynamicQuery : corona
     * PageIndex : 0
     * PageOffset : 100
     */

    private String DynamicQuery;
    private int PageIndex;
    private int PageOffset;

    public String getDynamicQuery() {
        return DynamicQuery;
    }

    public void setDynamicQuery(String DynamicQuery) {
        this.DynamicQuery = DynamicQuery;
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
