package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestGetTahsil {


    /**
     * CityName : Jaipur
     * Lat : 26.874058299999998
     * Long : 75.7805566
     * CountryName : India
     * StateName : Rajasthan
     */

    private String CityName;
    private double Lat;
    private double Long;
    private String CountryName;
    private String StateName;

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double Lat) {
        this.Lat = Lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double Long) {
        this.Long = Long;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String StateName) {
        this.StateName = StateName;
    }
}
