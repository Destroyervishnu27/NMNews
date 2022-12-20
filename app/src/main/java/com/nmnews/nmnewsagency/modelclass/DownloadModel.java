package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;


public class DownloadModel implements Serializable {


    /**
     * Status : true
     * Message : Success
     * Data : https://nmnews.in/temp_videos/662152842.mp4
     */


    private Boolean Status;
    private String Message;
    private String Data;

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
