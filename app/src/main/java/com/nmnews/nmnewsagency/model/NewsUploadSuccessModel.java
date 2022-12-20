package com.nmnews.nmnewsagency.model;

import com.nmnews.nmnewsagency.modelclass.UpdateProfileModel;

import java.io.Serializable;

public class NewsUploadSuccessModel implements Serializable{
    /**
     * Status : true
     * Message : Success
     * Data : {}
     */

    private boolean Status;
    private String Message;

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }


}
