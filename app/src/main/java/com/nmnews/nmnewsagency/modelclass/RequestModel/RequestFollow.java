package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestFollow {

    /**
     * who_follows : 78e50c5b-3e54-4d26-80ea-acc894a6ab3712
     * who_is_followed : 78e50c5b-3e54-4d26-80ea-acc894a6ab37
     */

    private String who_follows;
    private String who_is_followed;

    public String getWho_follows() {
        return who_follows;
    }

    public void setWho_follows(String who_follows) {
        this.who_follows = who_follows;
    }

    public String getWho_is_followed() {
        return who_is_followed;
    }

    public void setWho_is_followed(String who_is_followed) {
        this.who_is_followed = who_is_followed;
    }
}
