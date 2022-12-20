package com.nmnews.nmnewsagency.modelclass;

public class DeleteCommentsModel {

    /**
     * Status : true
     * Message : Comment Deleted.
     * Data : null
     */

    private boolean Status;
    private String Message;
    private Object Data;

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

    public Object getData() {
        return Data;
    }

    public void setData(Object Data) {
        this.Data = Data;
    }
}
