package com.nmnews.nmnewsagency.modelclass;

import java.io.Serializable;
import java.util.List;

public class NotificationModel {

    /**
     * Status : true
     * Message : Success
     * Data : [{"desireDate":"4m","Id":87,"NewsId":317,"Notification_Type":"LIKE","AddedOn":"2021-08-25T17:28:36.67","IsRead":false,"FromUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","ToUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","FromUserName":"Jayanti Saini jiya","Remark":null,"Notification_Desc":"audio editing app download ","Notification_JSON":"{\"UserId\":\"426fa746-0b64-4406-a27a-43b7fc93c903\",\"ShortDesc\":\"audio editing app download \",\"IncidentNo\":null,\"Description\":\"Jayanti Saini jiya liked your news.\",\"url\":\"http://nmnews.uislick.com//wwwroot\\\\images/Notification_Logo.jpg\",\"priority\":10,\"NewsTitle\":\"audio editing app download \",\"DeviceToken\":\"c4AjyY28SiK38m2EAAvzRd:APA91bESxGL_1gGBcXySa7R0mFehZutcv4N1squv-GS_wbM3Jr68C8lkEgDAeLhRiHOkwkXSDxbW-sIsoBFwuhPr6Q96HmCWMMnnbAzDllRYRvzS12VoI8L3NQCc2BiBtZaBhI582TRF\",\"Id\":0,\"NewsId\":317,\"Notification_Type\":\"LIKE\",\"AddedOn\":\"2021-08-25T17:28:36.6695322+05:30\",\"IsRead\":false,\"FromUserId\":\"426fa746-0b64-4406-a27a-43b7fc93c903\",\"ToUserId\":\"426fa746-0b64-4406-a27a-43b7fc93c903\",\"FromUserName\":\"Jayanti Saini jiya\",\"Remark\":null,\"Notification_Desc\":null,\"Notification_JSON\":null}"},{"desireDate":"20m","Id":85,"NewsId":317,"Notification_Type":"ADD","AddedOn":"2021-08-25T17:12:56.01","IsRead":false,"FromUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","ToUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","FromUserName":"Jayanti Saini jiya","Remark":null,"Notification_Desc":"audio editing app download ","Notification_JSON":null},{"desireDate":"40m","Id":83,"NewsId":316,"Notification_Type":"ADD","AddedOn":"2021-08-25T16:52:59.43","IsRead":false,"FromUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","ToUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","FromUserName":"Jayanti Saini jiya","Remark":null,"Notification_Desc":"audio add in response","Notification_JSON":null},{"desireDate":"47m","Id":81,"NewsId":315,"Notification_Type":"ADD","AddedOn":"2021-08-25T16:45:23.547","IsRead":false,"FromUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","ToUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","FromUserName":"Jayanti Saini jiya","Remark":null,"Notification_Desc":"audiobook news added in response","Notification_JSON":null},{"desireDate":"1h","Id":79,"NewsId":314,"Notification_Type":"ADD","AddedOn":"2021-08-25T16:17:50.31","IsRead":false,"FromUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","ToUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","FromUserName":"Jayanti Saini jiya","Remark":null,"Notification_Desc":"audio testing","Notification_JSON":null},{"desireDate":"5h","Id":65,"NewsId":310,"Notification_Type":"ADD","AddedOn":"2021-08-25T12:21:43.273","IsRead":false,"FromUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","ToUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","FromUserName":"Jayanti Saini jiya","Remark":null,"Notification_Desc":"Havey rain fall In Alwar","Notification_JSON":null},{"desireDate":"7d","Id":23,"NewsId":294,"Notification_Type":"LIKE","AddedOn":"2021-08-18T14:42:13.603","IsRead":false,"FromUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","ToUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","FromUserName":"Jayanti Saini jiya","Remark":null,"Notification_Desc":null,"Notification_JSON":"{\"UserId\":\"426fa746-0b64-4406-a27a-43b7fc93c903\",\"ShortDesc\":\"Havey rain fall In Alwar\",\"IncidentNo\":null,\"Description\":\"Jayanti Saini jiya liked your news.\",\"url\":\"http://nmnews.uislick.com//wwwroot\\\\images/Notification_Logo.jpg\",\"priority\":10,\"NewsTitle\":\"Havey rain fall In Alwar\",\"DeviceToken\":\"dSlYx_cFTFehG2naCL8Hjj:APA91bGVHZtjkcJqOe0lQpcx-j0lwlPsiPHRgU60huojxk2UAc1EfJqe2kHzBnxer8hEaX6Cf4mCAOTU3k2F9DoYbPpyXByd00bI_X6h_pGuMoB16bKDccjWBHmBiYxVdP-flV7r6Rdl\",\"Id\":0,\"NewsId\":294,\"Notification_Type\":\"LIKE\",\"AddedOn\":\"2021-08-18T14:42:13.0845624+05:30\",\"IsRead\":false,\"FromUserId\":\"426fa746-0b64-4406-a27a-43b7fc93c903\",\"ToUserId\":\"426fa746-0b64-4406-a27a-43b7fc93c903\",\"FromUserName\":\"Jayanti Saini jiya\",\"Remark\":null,\"Notification_Desc\":null,\"Notification_JSON\":null}"}]
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
         * desireDate : 4m
         * Id : 87
         * NewsId : 317
         * Notification_Type : LIKE
         * AddedOn : 2021-08-25T17:28:36.67
         * IsRead : false
         * FromUserId : 426fa746-0b64-4406-a27a-43b7fc93c903
         * ToUserId : 426fa746-0b64-4406-a27a-43b7fc93c903
         * FromUserName : Jayanti Saini jiya
         * Remark : null
         * Notification_Desc : audio editing app download
         * Notification_JSON : {"UserId":"426fa746-0b64-4406-a27a-43b7fc93c903","ShortDesc":"audio editing app download ","IncidentNo":null,"Description":"Jayanti Saini jiya liked your news.","url":"http://nmnews.uislick.com//wwwroot\\images/Notification_Logo.jpg","priority":10,"NewsTitle":"audio editing app download ","DeviceToken":"c4AjyY28SiK38m2EAAvzRd:APA91bESxGL_1gGBcXySa7R0mFehZutcv4N1squv-GS_wbM3Jr68C8lkEgDAeLhRiHOkwkXSDxbW-sIsoBFwuhPr6Q96HmCWMMnnbAzDllRYRvzS12VoI8L3NQCc2BiBtZaBhI582TRF","Id":0,"NewsId":317,"Notification_Type":"LIKE","AddedOn":"2021-08-25T17:28:36.6695322+05:30","IsRead":false,"FromUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","ToUserId":"426fa746-0b64-4406-a27a-43b7fc93c903","FromUserName":"Jayanti Saini jiya","Remark":null,"Notification_Desc":null,"Notification_JSON":null}
         */

        private String desireDate;
        private int Id;
        private int NewsId;
        private String Notification_Type;
        private String AddedOn;
        private boolean IsRead;
        private String FromUserId;
        private String ToUserId;
        private String FromUserName;
        private Object Remark;
        private String Notification_Desc;
        private String Notification_JSON;
        private String ImageUrl;

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String imageUrl) {
            ImageUrl = imageUrl;
        }

        public String getDesireDate() {
            return desireDate;
        }

        public void setDesireDate(String desireDate) {
            this.desireDate = desireDate;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getNewsId() {
            return NewsId;
        }

        public void setNewsId(int NewsId) {
            this.NewsId = NewsId;
        }

        public String getNotification_Type() {
            return Notification_Type;
        }

        public void setNotification_Type(String Notification_Type) {
            this.Notification_Type = Notification_Type;
        }

        public String getAddedOn() {
            return AddedOn;
        }

        public void setAddedOn(String AddedOn) {
            this.AddedOn = AddedOn;
        }

        public boolean isIsRead() {
            return IsRead;
        }

        public void setIsRead(boolean IsRead) {
            this.IsRead = IsRead;
        }

        public String getFromUserId() {
            return FromUserId;
        }

        public void setFromUserId(String FromUserId) {
            this.FromUserId = FromUserId;
        }

        public String getToUserId() {
            return ToUserId;
        }

        public void setToUserId(String ToUserId) {
            this.ToUserId = ToUserId;
        }

        public String getFromUserName() {
            return FromUserName;
        }

        public void setFromUserName(String FromUserName) {
            this.FromUserName = FromUserName;
        }

        public Object getRemark() {
            return Remark;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public String getNotification_Desc() {
            return Notification_Desc;
        }

        public void setNotification_Desc(String Notification_Desc) {
            this.Notification_Desc = Notification_Desc;
        }

        public String getNotification_JSON() {
            return Notification_JSON;
        }

        public void setNotification_JSON(String Notification_JSON) {
            this.Notification_JSON = Notification_JSON;
        }
    }
}
