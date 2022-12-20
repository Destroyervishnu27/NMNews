package com.nmnews.nmnewsagency.modelclass;

public class GetTahsilModel {

    /**
     * Status : true
     * Message : Success
     * Data : JAIPUR
     */

    private boolean Status;
    private String Message;
    private String Data;

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

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}
