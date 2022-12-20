package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;

public class ChatIdModerl {


    /**
     * Status : true
     * Message : Success
     * Data : {"Status":true,"Message":"Success","Data":{}}
     */

    private boolean Status;
    private String Message;
    private DataBeanX Data;

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

    public DataBeanX getData() {
        return Data;
    }

    public void setData(DataBeanX Data) {
        this.Data = Data;
    }

    public static class DataBeanX implements Serializable {
        /**
         * Status : true
         * Message : Success
         * Data : {}
         */

        private boolean Status;
        private String Message;
        private DataBean Data;

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

        public DataBean getData() {
            return Data;
        }

        public void setData(DataBean Data) {
            this.Data = Data;
        }

        public static class DataBean implements Serializable {
        }
    }
}
