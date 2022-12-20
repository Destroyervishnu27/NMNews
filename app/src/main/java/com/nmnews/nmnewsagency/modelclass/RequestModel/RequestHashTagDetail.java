package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestHashTagDetail {

    /**
     * HashTagId : 54
     * PageIndex : 0
     * PageOffset : 50
     * Country_Name : India
     * State_Name : Rajasthan
     * City_Name : Jaipur
     * Tahsil_Name : Snaganer
     */

    private int HashTagId;
    private int PageIndex;
    private int PageOffset;
    private String Country_Name;
    private String State_Name;
    private String City_Name;
    private String Tahsil_Name;

    public int getHashTagId() {
        return HashTagId;
    }

    public void setHashTagId(int HashTagId) {
        this.HashTagId = HashTagId;
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
}
