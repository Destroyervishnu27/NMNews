package com.nmnews.nmnewsagency.model;

import java.io.Serializable;
import java.util.List;

public class CityModel {

    /**
     * Status : true
     * Message : Success
     * Data : [{"Id":1,"Name":"INDIA","ShortName":"IN","Active":true,"CountryCode":"IN","Currency":"","Symbol":"","FlagImage":"","CompanyId":6,"Actions":null},{"Id":2,"Name":"NEPAL","ShortName":"NP","Active":true,"CountryCode":"","Currency":"","Symbol":"","FlagImage":"","CompanyId":6,"Actions":null},{"Id":3,"Name":"BANGLADESH","ShortName":"BD","Active":true,"CountryCode":"","Currency":"","Symbol":"","FlagImage":"","CompanyId":6,"Actions":null},{"Id":4,"Name":"SHRILANKA","ShortName":"","Active":true,"CountryCode":"","Currency":"","Symbol":"","FlagImage":"","CompanyId":6,"Actions":null},{"Id":5,"Name":"MANMAR","ShortName":"","Active":true,"CountryCode":"","Currency":"","Symbol":"","FlagImage":"","CompanyId":6,"Actions":null},{"Id":6,"Name":"AFGANISTAN","ShortName":"","Active":true,"CountryCode":"","Currency":"","Symbol":"","FlagImage":"","CompanyId":6,"Actions":null}]
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
         * Id : 1
         * Name : INDIA
         * ShortName : IN
         * Active : true
         * CountryCode : IN
         * Currency :
         * Symbol :
         * FlagImage :
         * CompanyId : 6
         * Actions : null
         */

        private int Id;
        private String Name;
        private String ShortName;
        private boolean Active;
        private String CountryCode;
        private String Currency;
        private String Symbol;
        private String FlagImage;
        private int CompanyId;
        private Object Actions;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getShortName() {
            return ShortName;
        }

        public void setShortName(String ShortName) {
            this.ShortName = ShortName;
        }

        public boolean isActive() {
            return Active;
        }

        public void setActive(boolean Active) {
            this.Active = Active;
        }

        public String getCountryCode() {
            return CountryCode;
        }

        public void setCountryCode(String CountryCode) {
            this.CountryCode = CountryCode;
        }

        public String getCurrency() {
            return Currency;
        }

        public void setCurrency(String Currency) {
            this.Currency = Currency;
        }

        public String getSymbol() {
            return Symbol;
        }

        public void setSymbol(String Symbol) {
            this.Symbol = Symbol;
        }

        public String getFlagImage() {
            return FlagImage;
        }

        public void setFlagImage(String FlagImage) {
            this.FlagImage = FlagImage;
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
