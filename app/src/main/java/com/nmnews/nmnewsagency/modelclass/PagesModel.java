package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;

public class PagesModel {

    /**
     * Status : true
     * Message : Success
     * Data : {"Id":1,"PageTitle":"Terms & Conditions","PageUrl":"terms-conditions","IsPublished":true,"AddedOn":"2021-09-17T12:06:40.3","FeaturedImage":null,"TemplateName":"_CommonPage.cshtml","SeoTitle":null,"SeoKeyword":null,"SeoDescription":null,"PageDescription":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","CompanyId":6,"Actions":null}
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
        /**
         * Id : 1
         * PageTitle : Terms & Conditions
         * PageUrl : terms-conditions
         * IsPublished : true
         * AddedOn : 2021-09-17T12:06:40.3
         * FeaturedImage : null
         * TemplateName : _CommonPage.cshtml
         * SeoTitle : null
         * SeoKeyword : null
         * SeoDescription : null
         * PageDescription : Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
         * CompanyId : 6
         * Actions : null
         */

        private int Id;
        private String PageTitle;
        private String PageUrl;
        private boolean IsPublished;
        private String AddedOn;
        private Object FeaturedImage;
        private String TemplateName;
        private Object SeoTitle;
        private Object SeoKeyword;
        private Object SeoDescription;
        private String PageDescription;
        private int CompanyId;
        private Object Actions;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getPageTitle() {
            return PageTitle;
        }

        public void setPageTitle(String PageTitle) {
            this.PageTitle = PageTitle;
        }

        public String getPageUrl() {
            return PageUrl;
        }

        public void setPageUrl(String PageUrl) {
            this.PageUrl = PageUrl;
        }

        public boolean isIsPublished() {
            return IsPublished;
        }

        public void setIsPublished(boolean IsPublished) {
            this.IsPublished = IsPublished;
        }

        public String getAddedOn() {
            return AddedOn;
        }

        public void setAddedOn(String AddedOn) {
            this.AddedOn = AddedOn;
        }

        public Object getFeaturedImage() {
            return FeaturedImage;
        }

        public void setFeaturedImage(Object FeaturedImage) {
            this.FeaturedImage = FeaturedImage;
        }

        public String getTemplateName() {
            return TemplateName;
        }

        public void setTemplateName(String TemplateName) {
            this.TemplateName = TemplateName;
        }

        public Object getSeoTitle() {
            return SeoTitle;
        }

        public void setSeoTitle(Object SeoTitle) {
            this.SeoTitle = SeoTitle;
        }

        public Object getSeoKeyword() {
            return SeoKeyword;
        }

        public void setSeoKeyword(Object SeoKeyword) {
            this.SeoKeyword = SeoKeyword;
        }

        public Object getSeoDescription() {
            return SeoDescription;
        }

        public void setSeoDescription(Object SeoDescription) {
            this.SeoDescription = SeoDescription;
        }

        public String getPageDescription() {
            return PageDescription;
        }

        public void setPageDescription(String PageDescription) {
            this.PageDescription = PageDescription;
        }

        public int getCompanyId() {
            return CompanyId;
        }

        public void setCompanyId(int CompanyId) {
            this.CompanyId = CompanyId;
        }

        public Object getActions() {
            return Actions;
        }

        public void setActions(Object Actions) {
            this.Actions = Actions;
        }
    }
}
