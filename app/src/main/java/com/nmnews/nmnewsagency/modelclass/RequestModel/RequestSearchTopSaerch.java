package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestSearchTopSaerch {


    /**
     * Query :
     * SearchType : 1
     * PageIndex : 0
     * PageOffset : 50
     * Country_Name : India
     * State_Name : Rajasthan
     * City_Name : Jaipur
     * Tahsil_Name : Snaganer
     * LogedInUserId : e3efdf6a-e61f-4a2a-bfd3-baea75854bc6
     */

    private String Query;
    private int SearchType;
    private int PageIndex;
    private int PageOffset;
    private String Country_Name;
    private String State_Name;
    private String City_Name;
    private String Tahsil_Name;
    private String LogedInUserId;

    public String getQuery() {
        return Query;
    }

    public void setQuery(String Query) {
        this.Query = Query;
    }

    public int getSearchType() {
        return SearchType;
    }

    public void setSearchType(int SearchType) {
        this.SearchType = SearchType;
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

    public String getCountry_Name() {
        return Country_Name;
    }

    public void setCountry_Name(String Country_Name) {
        this.Country_Name = Country_Name;
    }

    public String getState_Name() {
        return State_Name;
    }

    public void setState_Name(String State_Name) {
        this.State_Name = State_Name;
    }

    public String getCity_Name() {
        return City_Name;
    }

    public void setCity_Name(String City_Name) {
        this.City_Name = City_Name;
    }

    public String getTahsil_Name() {
        return Tahsil_Name;
    }

    public void setTahsil_Name(String Tahsil_Name) {
        this.Tahsil_Name = Tahsil_Name;
    }

    public String getLogedInUserId() {
        return LogedInUserId;
    }

    public void setLogedInUserId(String LogedInUserId) {
        this.LogedInUserId = LogedInUserId;
    }
}
