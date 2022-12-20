package com.nmnews.nmnewsagency.modelclass.RequestModel;

public class RequestLoginModel {

    /**
     * Name : Sundar Pichai
     * Email : sundarpichai@gmail.com
     * DeviceToken : 1234562
     * DeviceId : 7894563
     * DeviceType : IOS
     * ProfileImage : https://pbs.twimg.com/profile_images/864282616597405701/M-FEJMZ0.jpg
     * LoginProviderUserId : 1234567890
     * LoginProvider : GOOGLE
     */

    private String Name;
    private String Email;
    private String DeviceToken;
    private String DeviceId;
    private String DeviceType;
    private String ProfileImage;
    private String LoginProviderUserId;
    private String LoginProvider;

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

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String DeviceToken) {
        this.DeviceToken = DeviceToken;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String DeviceType) {
        this.DeviceType = DeviceType;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String ProfileImage) {
        this.ProfileImage = ProfileImage;
    }

    public String getLoginProviderUserId() {
        return LoginProviderUserId;
    }

    public void setLoginProviderUserId(String LoginProviderUserId) {
        this.LoginProviderUserId = LoginProviderUserId;
    }

    public String getLoginProvider() {
        return LoginProvider;
    }

    public void setLoginProvider(String LoginProvider) {
        this.LoginProvider = LoginProvider;
    }
}
