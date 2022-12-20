package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class GetDocumentModel {

    /**
     * Status : true
     * Message : Success
     * Data : [{"Id":2,"DocumentType":"AADHAR","AddedOn":"2021-07-17T17:13:08.653","UserId":"00252404-c5be-45f8-82f4-6fed28e31d5c","DocumentLink":"Could not find a part of the path 'C:\\inetpub\\wwwroot\\nmnews.uislick.com\\wwwroot\\wwwroot\\images\\documents\\6ce016c0-6a96-4802-b8fa-f334308198e9.3gp'.","Verified":false,"VerifiedBy":"","Comment":"","file":null,"DoProfileDisabled":false},{"Id":3,"DocumentType":"PRESSIDCARD","AddedOn":"2021-07-17T17:35:17.573","UserId":"00252404-c5be-45f8-82f4-6fed28e31d5c","DocumentLink":"Could not find a part of the path 'C:\\inetpub\\wwwroot\\nmnews.uislick.com\\wwwroot\\wwwroot\\images\\documents\\9ab0ee58-bc62-4674-86d4-702ce75f1e57.3gp'.","Verified":false,"VerifiedBy":"","Comment":"","file":null,"DoProfileDisabled":false},{"Id":7,"DocumentType":"PANCARD","AddedOn":"2021-07-17T18:47:25.35","UserId":"00252404-c5be-45f8-82f4-6fed28e31d5c","DocumentLink":"Could not find a part of the path 'C:\\inetpub\\wwwroot\\nmnews.uislick.com\\wwwroot\\wwwroot\\images\\documents\\c7a660b2-14b3-4ab9-979c-ec22f250bdd6.3gp'.","Verified":false,"VerifiedBy":"","Comment":"","file":null,"DoProfileDisabled":false}]
     */

    private boolean Status;
    private String Message;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * Id : 2
         * DocumentType : AADHAR
         * AddedOn : 2021-07-17T17:13:08.653
         * UserId : 00252404-c5be-45f8-82f4-6fed28e31d5c
         * DocumentLink : Could not find a part of the path 'C:\inetpub\wwwroot\nmnews.uislick.com\wwwroot\wwwroot\images\documents\6ce016c0-6a96-4802-b8fa-f334308198e9.3gp'.
         * Verified : false
         * VerifiedBy :
         * Comment :
         * file : null
         * DoProfileDisabled : false
         */

        private int Id;
        private String DocumentType;
        private String AddedOn;
        private String UserId;
        private String DocumentLink;
        private boolean Verified;
        private String VerifiedBy;
        private String Comment;
        private Object file;
        private boolean DoProfileDisabled;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getDocumentType() {
            return DocumentType;
        }

        public void setDocumentType(String DocumentType) {
            this.DocumentType = DocumentType;
        }

        public String getAddedOn() {
            return AddedOn;
        }

        public void setAddedOn(String AddedOn) {
            this.AddedOn = AddedOn;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getDocumentLink() {
            return DocumentLink;
        }

        public void setDocumentLink(String DocumentLink) {
            this.DocumentLink = DocumentLink;
        }

        public boolean isVerified() {
            return Verified;
        }

        public void setVerified(boolean Verified) {
            this.Verified = Verified;
        }

        public String getVerifiedBy() {
            return VerifiedBy;
        }

        public void setVerifiedBy(String VerifiedBy) {
            this.VerifiedBy = VerifiedBy;
        }

        public String getComment() {
            return Comment;
        }

        public void setComment(String Comment) {
            this.Comment = Comment;
        }

        public Object getFile() {
            return file;
        }

        public void setFile(Object file) {
            this.file = file;
        }

        public boolean isDoProfileDisabled() {
            return DoProfileDisabled;
        }

        public void setDoProfileDisabled(boolean DoProfileDisabled) {
            this.DoProfileDisabled = DoProfileDisabled;
        }
    }
}
