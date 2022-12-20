package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class GetProfileDataModel {


    /**
     * Status : true
     * Message : Success
     * Data : {"AspNetUser":{"NewsCount":11,"UserTagVideo":0,"SavedNewsCount":3,"IsFollowed":false,"Followers":5,"Followings":4,"FollowersSuffix":"5","FollowingsSuffix":"4","Id":"04149de1-2284-49a6-8248-1909c30fce45","Email":null,"EmailConfirmed":false,"PasswordHash":null,"PasswordText":null,"SecurityStamp":null,"ContactNumber":null,"PhoneNumberConfirmed":false,"TwoFactorEnabled":false,"LockoutEndDateUtc":"0001-01-01T00:00:00","LockoutEnabled":false,"AccessFailedCount":0,"UserName":"ramagyasharma1954","FirstName":"Sharma Ramagya R","LastName":"shivnath","FullName":"Sharma Ramagya R shivnath","UserType":"REPORTER","AddDate":null,"ModifyDate":null,"IsActive":false,"BusinessName":null,"Avatar":"http://nmnews.uislick.com/images//profile_images/c0c47d54-e1ed-4c58-ad33-5d08982ef01b.jpg","PageUrl":null,"AboutMe":null,"Profile_Score":0,"DeviceToken":null,"ChatId":"130746294"},"UserAddress":[{"Id":310,"UserId":"04149de1-2284-49a6-8248-1909c30fce45","FirstName":null,"LastName":null,"AddressLine1":"","AddressLine2":"","ZipCode":"","Addresstype":"APP","ContactNumber":null,"IsDefault":true,"BusinessName":null,"HouseNo":null,"GCountry":"INDIA","GState":"MAHARSHATRA","GCity":"MUMBAI SUBURBAN","GThasil":"MUMBAI SUBURBAN","CountryId":1,"StateId":1,"CityId":26,"TahsilId":192,"GFullAddress":"","Lat":"","Long":""}]}
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
         * AspNetUser : {"NewsCount":11,"UserTagVideo":0,"SavedNewsCount":3,"IsFollowed":false,"Followers":5,"Followings":4,"FollowersSuffix":"5","FollowingsSuffix":"4","Id":"04149de1-2284-49a6-8248-1909c30fce45","Email":null,"EmailConfirmed":false,"PasswordHash":null,"PasswordText":null,"SecurityStamp":null,"ContactNumber":null,"PhoneNumberConfirmed":false,"TwoFactorEnabled":false,"LockoutEndDateUtc":"0001-01-01T00:00:00","LockoutEnabled":false,"AccessFailedCount":0,"UserName":"ramagyasharma1954","FirstName":"Sharma Ramagya R","LastName":"shivnath","FullName":"Sharma Ramagya R shivnath","UserType":"REPORTER","AddDate":null,"ModifyDate":null,"IsActive":false,"BusinessName":null,"Avatar":"http://nmnews.uislick.com/images//profile_images/c0c47d54-e1ed-4c58-ad33-5d08982ef01b.jpg","PageUrl":null,"AboutMe":null,"Profile_Score":0,"DeviceToken":null,"ChatId":"130746294"}
         * UserAddress : [{"Id":310,"UserId":"04149de1-2284-49a6-8248-1909c30fce45","FirstName":null,"LastName":null,"AddressLine1":"","AddressLine2":"","ZipCode":"","Addresstype":"APP","ContactNumber":null,"IsDefault":true,"BusinessName":null,"HouseNo":null,"GCountry":"INDIA","GState":"MAHARSHATRA","GCity":"MUMBAI SUBURBAN","GThasil":"MUMBAI SUBURBAN","CountryId":1,"StateId":1,"CityId":26,"TahsilId":192,"GFullAddress":"","Lat":"","Long":""}]
         */

        private AspNetUserBean AspNetUser;
        private List<UserAddressBean> UserAddress;

        public AspNetUserBean getAspNetUser() {
            return AspNetUser;
        }

        public void setAspNetUser(AspNetUserBean AspNetUser) {
            this.AspNetUser = AspNetUser;
        }

        public List<UserAddressBean> getUserAddress() {
            return UserAddress;
        }

        public void setUserAddress(List<UserAddressBean> UserAddress) {
            this.UserAddress = UserAddress;
        }

        public static class AspNetUserBean implements Serializable {
            /**
             * NewsCount : 11
             * UserTagVideo : 0
             * SavedNewsCount : 3
             * IsFollowed : false
             * Followers : 5
             * Followings : 4
             * FollowersSuffix : 5
             * FollowingsSuffix : 4
             * Id : 04149de1-2284-49a6-8248-1909c30fce45
             * Email : null
             * EmailConfirmed : false
             * PasswordHash : null
             * PasswordText : null
             * SecurityStamp : null
             * ContactNumber : null
             * PhoneNumberConfirmed : false
             * TwoFactorEnabled : false
             * LockoutEndDateUtc : 0001-01-01T00:00:00
             * LockoutEnabled : false
             * AccessFailedCount : 0
             * UserName : ramagyasharma1954
             * FirstName : Sharma Ramagya R
             * LastName : shivnath
             * FullName : Sharma Ramagya R shivnath
             * UserType : REPORTER
             * AddDate : null
             * ModifyDate : null
             * IsActive : false
             * BusinessName : null
             * Avatar : http://nmnews.uislick.com/images//profile_images/c0c47d54-e1ed-4c58-ad33-5d08982ef01b.jpg
             * PageUrl : null
             * AboutMe : null
             * Profile_Score : 0.0
             * DeviceToken : null
             * ChatId : 130746294
             */

            private int NewsCount;
            private int UserTagVideo;
            private int SavedNewsCount;
            private boolean IsFollowed;
            private int Followers;
            private int Followings;
            private String FollowersSuffix;
            private String FollowingsSuffix;
            private String Id;
            private Object Email;
            private boolean EmailConfirmed;
            private Object PasswordHash;
            private Object PasswordText;
            private Object SecurityStamp;
            private Object ContactNumber;
            private boolean PhoneNumberConfirmed;
            private boolean SendNotification;
            private boolean TwoFactorEnabled;
            private String LockoutEndDateUtc;
            private boolean LockoutEnabled;
            private int AccessFailedCount;
            private String UserName;
            private String FirstName;
            private String LastName;
            private String FullName;
            private String UserType;
            private Object AddDate;
            private Object ModifyDate;
            private boolean IsActive;
            private Object BusinessName;
            private String Avatar;
            private Object PageUrl;
            private Object AboutMe;
            private double Profile_Score;
            private Object DeviceToken;
            private String ChatId;

            public int getNewsCount() {
                return NewsCount;
            }

            public void setNewsCount(int NewsCount) {
                this.NewsCount = NewsCount;
            }

            public int getUserTagVideo() {
                return UserTagVideo;
            }

            public void setUserTagVideo(int UserTagVideo) {
                this.UserTagVideo = UserTagVideo;
            }

            public int getSavedNewsCount() {
                return SavedNewsCount;
            }

            public void setSavedNewsCount(int SavedNewsCount) {
                this.SavedNewsCount = SavedNewsCount;
            }

            public boolean isIsFollowed() {
                return IsFollowed;
            }

            public void setIsFollowed(boolean IsFollowed) {
                this.IsFollowed = IsFollowed;
            }

            public int getFollowers() {
                return Followers;
            }

            public void setFollowers(int Followers) {
                this.Followers = Followers;
            }

            public int getFollowings() {
                return Followings;
            }

            public void setFollowings(int Followings) {
                this.Followings = Followings;
            }

            public String getFollowersSuffix() {
                return FollowersSuffix;
            }

            public void setFollowersSuffix(String FollowersSuffix) {
                this.FollowersSuffix = FollowersSuffix;
            }

            public String getFollowingsSuffix() {
                return FollowingsSuffix;
            }

            public void setFollowingsSuffix(String FollowingsSuffix) {
                this.FollowingsSuffix = FollowingsSuffix;
            }

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public Object getEmail() {
                return Email;
            }

            public void setEmail(Object Email) {
                this.Email = Email;
            }

            public boolean isEmailConfirmed() {
                return EmailConfirmed;
            }

            public void setEmailConfirmed(boolean EmailConfirmed) {
                this.EmailConfirmed = EmailConfirmed;
            }

            public Object getPasswordHash() {
                return PasswordHash;
            }

            public void setPasswordHash(Object PasswordHash) {
                this.PasswordHash = PasswordHash;
            }

            public Object getPasswordText() {
                return PasswordText;
            }

            public void setPasswordText(Object PasswordText) {
                this.PasswordText = PasswordText;
            }

            public Object getSecurityStamp() {
                return SecurityStamp;
            }

            public void setSecurityStamp(Object SecurityStamp) {
                this.SecurityStamp = SecurityStamp;
            }

            public Object getContactNumber() {
                return ContactNumber;
            }

            public void setContactNumber(Object ContactNumber) {
                this.ContactNumber = ContactNumber;
            }

            public boolean isPhoneNumberConfirmed() {
                return PhoneNumberConfirmed;
            }

            public void setPhoneNumberConfirmed(boolean PhoneNumberConfirmed) {
                this.PhoneNumberConfirmed = PhoneNumberConfirmed;
            }

            public boolean isTwoFactorEnabled() {
                return TwoFactorEnabled;
            }

            public void setTwoFactorEnabled(boolean TwoFactorEnabled) {
                this.TwoFactorEnabled = TwoFactorEnabled;
            }
            public boolean isSendNotification() {
                return SendNotification;
            }

            public void setSendNotification(boolean SendNotification) {
                this.SendNotification = SendNotification;
            }

            public String getLockoutEndDateUtc() {
                return LockoutEndDateUtc;
            }

            public void setLockoutEndDateUtc(String LockoutEndDateUtc) {
                this.LockoutEndDateUtc = LockoutEndDateUtc;
            }

            public boolean isLockoutEnabled() {
                return LockoutEnabled;
            }

            public void setLockoutEnabled(boolean LockoutEnabled) {
                this.LockoutEnabled = LockoutEnabled;
            }

            public int getAccessFailedCount() {
                return AccessFailedCount;
            }

            public void setAccessFailedCount(int AccessFailedCount) {
                this.AccessFailedCount = AccessFailedCount;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
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

            public String getFullName() {
                return FullName;
            }

            public void setFullName(String FullName) {
                this.FullName = FullName;
            }

            public String getUserType() {
                return UserType;
            }

            public void setUserType(String UserType) {
                this.UserType = UserType;
            }

            public Object getAddDate() {
                return AddDate;
            }

            public void setAddDate(Object AddDate) {
                this.AddDate = AddDate;
            }

            public Object getModifyDate() {
                return ModifyDate;
            }

            public void setModifyDate(Object ModifyDate) {
                this.ModifyDate = ModifyDate;
            }

            public boolean isIsActive() {
                return IsActive;
            }

            public void setIsActive(boolean IsActive) {
                this.IsActive = IsActive;
            }

            public Object getBusinessName() {
                return BusinessName;
            }

            public void setBusinessName(Object BusinessName) {
                this.BusinessName = BusinessName;
            }

            public String getAvatar() {
                return Avatar;
            }

            public void setAvatar(String Avatar) {
                this.Avatar = Avatar;
            }

            public Object getPageUrl() {
                return PageUrl;
            }

            public void setPageUrl(Object PageUrl) {
                this.PageUrl = PageUrl;
            }

            public Object getAboutMe() {
                return AboutMe;
            }

            public void setAboutMe(Object AboutMe) {
                this.AboutMe = AboutMe;
            }

            public double getProfile_Score() {
                return Profile_Score;
            }

            public void setProfile_Score(double Profile_Score) {
                this.Profile_Score = Profile_Score;
            }

            public Object getDeviceToken() {
                return DeviceToken;
            }

            public void setDeviceToken(Object DeviceToken) {
                this.DeviceToken = DeviceToken;
            }

            public String getChatId() {
                return ChatId;
            }

            public void setChatId(String ChatId) {
                this.ChatId = ChatId;
            }
        }

        public static class UserAddressBean implements Serializable {
            /**
             * Id : 310
             * UserId : 04149de1-2284-49a6-8248-1909c30fce45
             * FirstName : null
             * LastName : null
             * AddressLine1 :
             * AddressLine2 :
             * ZipCode :
             * Addresstype : APP
             * ContactNumber : null
             * IsDefault : true
             * BusinessName : null
             * HouseNo : null
             * GCountry : INDIA
             * GState : MAHARSHATRA
             * GCity : MUMBAI SUBURBAN
             * GThasil : MUMBAI SUBURBAN
             * CountryId : 1
             * StateId : 1
             * CityId : 26
             * TahsilId : 192
             * GFullAddress :
             * Lat :
             * Long :
             */

            private int Id;
            private String UserId;
            private Object FirstName;
            private Object LastName;
            private String AddressLine1;
            private String AddressLine2;
            private String ZipCode;
            private String Addresstype;
            private Object ContactNumber;
            private boolean IsDefault;
            private Object BusinessName;
            private Object HouseNo;
            private String GCountry;
            private String GState;
            private String GCity;
            private String GThasil;
            private int CountryId;
            private int StateId;
            private int CityId;
            private int TahsilId;
            private String GFullAddress;
            private String Lat;
            private String Long;

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

            public Object getFirstName() {
                return FirstName;
            }

            public void setFirstName(Object FirstName) {
                this.FirstName = FirstName;
            }

            public Object getLastName() {
                return LastName;
            }

            public void setLastName(Object LastName) {
                this.LastName = LastName;
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

            public Object getContactNumber() {
                return ContactNumber;
            }

            public void setContactNumber(Object ContactNumber) {
                this.ContactNumber = ContactNumber;
            }

            public boolean isIsDefault() {
                return IsDefault;
            }

            public void setIsDefault(boolean IsDefault) {
                this.IsDefault = IsDefault;
            }

            public Object getBusinessName() {
                return BusinessName;
            }

            public void setBusinessName(Object BusinessName) {
                this.BusinessName = BusinessName;
            }

            public Object getHouseNo() {
                return HouseNo;
            }

            public void setHouseNo(Object HouseNo) {
                this.HouseNo = HouseNo;
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

            public int getTahsilId() {
                return TahsilId;
            }

            public void setTahsilId(int TahsilId) {
                this.TahsilId = TahsilId;
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
        }
    }
}
