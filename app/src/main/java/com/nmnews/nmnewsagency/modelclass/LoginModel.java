package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;

public class LoginModel {


    /**
     * Status : true
     * Message : Success
     * Data : {"UserInfo":{"UserId":"f0523348-0c2f-44e4-b0cd-c5d5d4f66ada","Name":"jayanti saini","Email":"jesmin.saini@gmail.com","EmailVerified":true,"UserActive":false,"ProfileImage":"http://nmnews.uislick.com/images/profile_images/fafbfe98-7f75-4d17-9ab8-792c84330677.jpg","Limit":0},"IsAddressFound":true,"UserAddress":{"Id":29,"UserId":"f0523348-0c2f-44e4-b0cd-c5d5d4f66ada","AddressLine1":"Ramnagar","AddressLine2":"","CountryId":0,"StateId":0,"CityId":0,"ZipCode":"302019","Addresstype":"APP","FirstName":"Jayanti","LastName":"Saini","ContactNumber":"","IsDefault":true,"BusinessName":"","HouseNo":"E-65","TahsilId":0,"GCountry":"INDIA","GState":"RAJASTHAN","GCity":"JAIPUR","GThasil":null,"GFullAddress":"E-65, Katariya Colony, Ganesh Nagar, Ramnagar, Jaipur, Rajasthan 302019, India","Lat":"26.8948197","Long":"75.7768056","IsAddressMatch":true}}
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
         * UserInfo : {"UserId":"f0523348-0c2f-44e4-b0cd-c5d5d4f66ada","Name":"jayanti saini","Email":"jesmin.saini@gmail.com","EmailVerified":true,"UserActive":false,"ProfileImage":"http://nmnews.uislick.com/images/profile_images/fafbfe98-7f75-4d17-9ab8-792c84330677.jpg","Limit":0}
         * IsAddressFound : true
         * UserAddress : {"Id":29,"UserId":"f0523348-0c2f-44e4-b0cd-c5d5d4f66ada","AddressLine1":"Ramnagar","AddressLine2":"","CountryId":0,"StateId":0,"CityId":0,"ZipCode":"302019","Addresstype":"APP","FirstName":"Jayanti","LastName":"Saini","ContactNumber":"","IsDefault":true,"BusinessName":"","HouseNo":"E-65","TahsilId":0,"GCountry":"INDIA","GState":"RAJASTHAN","GCity":"JAIPUR","GThasil":null,"GFullAddress":"E-65, Katariya Colony, Ganesh Nagar, Ramnagar, Jaipur, Rajasthan 302019, India","Lat":"26.8948197","Long":"75.7768056","IsAddressMatch":true}
         */

        private UserInfoBean UserInfo;
        private boolean IsAddressFound;
        private UserAddressBean UserAddress;

        public UserInfoBean getUserInfo() {
            return UserInfo;
        }

        public void setUserInfo(UserInfoBean UserInfo) {
            this.UserInfo = UserInfo;
        }

        public boolean isIsAddressFound() {
            return IsAddressFound;
        }

        public void setIsAddressFound(boolean IsAddressFound) {
            this.IsAddressFound = IsAddressFound;
        }

        public UserAddressBean getUserAddress() {
            return UserAddress;
        }

        public void setUserAddress(UserAddressBean UserAddress) {
            this.UserAddress = UserAddress;
        }

        public static class UserInfoBean implements Serializable {
            /**
             * UserId : f0523348-0c2f-44e4-b0cd-c5d5d4f66ada
             * Name : jayanti saini
             * Email : jesmin.saini@gmail.com
             * EmailVerified : true
             * UserActive : false
             * ProfileImage : http://nmnews.uislick.com/images/profile_images/fafbfe98-7f75-4d17-9ab8-792c84330677.jpg
             * Limit : 0
             */

            private String UserId;
            private String Name;
            private String Email;
            private boolean EmailVerified;
            private boolean UserActive;
            private String ProfileImage;
            private int Limit;

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getEmail() {
                return Email;
            }

            public void setEmail(String Email) {
                this.Email = Email;
            }

            public boolean isEmailVerified() {
                return EmailVerified;
            }

            public void setEmailVerified(boolean EmailVerified) {
                this.EmailVerified = EmailVerified;
            }

            public boolean isUserActive() {
                return UserActive;
            }

            public void setUserActive(boolean UserActive) {
                this.UserActive = UserActive;
            }

            public String getProfileImage() {
                return ProfileImage;
            }

            public void setProfileImage(String ProfileImage) {
                this.ProfileImage = ProfileImage;
            }

            public int getLimit() {
                return Limit;
            }

            public void setLimit(int Limit) {
                this.Limit = Limit;
            }
        }

        public static class UserAddressBean implements Serializable {
            /**
             * Id : 29
             * UserId : f0523348-0c2f-44e4-b0cd-c5d5d4f66ada
             * AddressLine1 : Ramnagar
             * AddressLine2 :
             * CountryId : 0
             * StateId : 0
             * CityId : 0
             * ZipCode : 302019
             * Addresstype : APP
             * FirstName : Jayanti
             * LastName : Saini
             * ContactNumber :
             * IsDefault : true
             * BusinessName :
             * HouseNo : E-65
             * TahsilId : 0
             * GCountry : INDIA
             * GState : RAJASTHAN
             * GCity : JAIPUR
             * GThasil : null
             * GFullAddress : E-65, Katariya Colony, Ganesh Nagar, Ramnagar, Jaipur, Rajasthan 302019, India
             * Lat : 26.8948197
             * Long : 75.7768056
             * IsAddressMatch : true
             */

            private int Id;
            private String UserId;
            private String AddressLine1;
            private String AddressLine2;
            private int CountryId;
            private int StateId;
            private int CityId;
            private String ZipCode;
            private String Addresstype;
            private String FirstName;
            private String LastName;
            private String ContactNumber;
            private boolean IsDefault;
            private String BusinessName;
            private String HouseNo;
            private int TahsilId;
            private String GCountry;
            private String GState;
            private String GCity;
            private String GThasil;
            private String GFullAddress;
            private String Lat;
            private String Long;
            private boolean IsAddressMatch;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getAddressLine1() {
                return AddressLine1;
            }

            public void setAddressLine1(String AddressLine1) {
                this.AddressLine1 = AddressLine1;
            }

            public String getAddressLine2() {
                return AddressLine2;
            }

            public void setAddressLine2(String AddressLine2) {
                this.AddressLine2 = AddressLine2;
            }

            public int getCountryId() {
                return CountryId;
            }

            public void setCountryId(int CountryId) {
                this.CountryId = CountryId;
            }

            public int getStateId() {
                return StateId;
            }

            public void setStateId(int StateId) {
                this.StateId = StateId;
            }

            public int getCityId() {
                return CityId;
            }

            public void setCityId(int CityId) {
                this.CityId = CityId;
            }

            public String getZipCode() {
                return ZipCode;
            }

            public void setZipCode(String ZipCode) {
                this.ZipCode = ZipCode;
            }

            public String getAddresstype() {
                return Addresstype;
            }

            public void setAddresstype(String Addresstype) {
                this.Addresstype = Addresstype;
            }

            public String getFirstName() {
                return FirstName;
            }

            public void setFirstName(String FirstName) {
                this.FirstName = FirstName;
            }

            public String getLastName() {
                return LastName;
            }

            public void setLastName(String LastName) {
                this.LastName = LastName;
            }

            public String getContactNumber() {
                return ContactNumber;
            }

            public void setContactNumber(String ContactNumber) {
                this.ContactNumber = ContactNumber;
            }

            public boolean isIsDefault() {
                return IsDefault;
            }

            public void setIsDefault(boolean IsDefault) {
                this.IsDefault = IsDefault;
            }

            public String getBusinessName() {
                return BusinessName;
            }

            public void setBusinessName(String BusinessName) {
                this.BusinessName = BusinessName;
            }

            public String getHouseNo() {
                return HouseNo;
            }

            public void setHouseNo(String HouseNo) {
                this.HouseNo = HouseNo;
            }

            public int getTahsilId() {
                return TahsilId;
            }

            public void setTahsilId(int TahsilId) {
                this.TahsilId = TahsilId;
            }

            public String getGCountry() {
                return GCountry;
            }

            public void setGCountry(String GCountry) {
                this.GCountry = GCountry;
            }

            public String getGState() {
                return GState;
            }

            public void setGState(String GState) {
                this.GState = GState;
            }

            public String getGCity() {
                return GCity;
            }

            public void setGCity(String GCity) {
                this.GCity = GCity;
            }

            public String getGThasil() {
                return GThasil;
            }

            public void setGThasil(String GThasil) {
                this.GThasil = GThasil;
            }

            public String getGFullAddress() {
                return GFullAddress;
            }

            public void setGFullAddress(String GFullAddress) {
                this.GFullAddress = GFullAddress;
            }

            public String getLat() {
                return Lat;
            }

            public void setLat(String Lat) {
                this.Lat = Lat;
            }

            public String getLong() {
                return Long;
            }

            public void setLong(String Long) {
                this.Long = Long;
            }

            public boolean isIsAddressMatch() {
                return IsAddressMatch;
            }

            public void setIsAddressMatch(boolean IsAddressMatch) {
                this.IsAddressMatch = IsAddressMatch;
            }
        }
    }
}
