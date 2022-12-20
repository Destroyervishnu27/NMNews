package com.nmnews.nmnewsagency.model;

import java.util.List;

/**
 * Created by Vishnu Saini on 07-Sep-18.
 * vishnusainideveloper27@gmail.com
 */
public class LoginRegisterModel {

    /**
     * status : 200
     * message : Login successfully
     * data : {"id":2,"name":"test user","email":"testuser@gmail.com","mobile_number":"0123456789","forgot_key":"","image":"IMG_1532353755623.jpg","city":"Jaipur","registration_number":"","number_plate":"","vehicle_manufacturer":"","vehicle_model":"","vehicle_type_id":0,"vehicle_color":"","device_id":"skjd","device_token":"1234","device_type":"ios","latitude":"26.45345","longitude":"23423","payment_method":"cash","cancellation_charge":"0.00","is_verified":1,"is_visible":0,"identity_verification":1,"vehicle_verification":1,"document_verification":1,"user_status":9,"block_reason":"","status":"AC","created_at":"2018-07-17 06:35:32","updated_at":"2018-09-07 09:50:24","user_role":[{"id":2,"role":"user","status":"AC","created_at":"-0001-11-30 00:00:00","updated_at":"2018-05-15 14:43:11","pivot":{"user_id":2,"role_id":2}}]}
     */

    private int status;
    private String message;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * name : test user
         * email : testuser@gmail.com
         * mobile_number : 0123456789
         * forgot_key :
         * image : IMG_1532353755623.jpg
         * city : Jaipur
         * registration_number :
         * number_plate :
         * vehicle_manufacturer :
         * vehicle_model :
         * vehicle_type_id : 0
         * vehicle_color :
         * device_id : skjd
         * device_token : 1234
         * device_type : ios
         * latitude : 26.45345
         * longitude : 23423
         * payment_method : cash
         * cancellation_charge : 0.00
         * is_verified : 1
         * is_visible : 0
         * identity_verification : 1
         * vehicle_verification : 1
         * document_verification : 1
         * user_status : 9
         * block_reason :
         * status : AC
         * created_at : 2018-07-17 06:35:32
         * updated_at : 2018-09-07 09:50:24
         * user_role : [{"id":2,"role":"user","status":"AC","created_at":"-0001-11-30 00:00:00","updated_at":"2018-05-15 14:43:11","pivot":{"user_id":2,"role_id":2}}]
         */

        private int id;
        private String name;

        public String getProfile_picture() {
            return profile_picture;
        }

        public void setProfile_picture(String profile_picture) {
            this.profile_picture = profile_picture;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        private String profile_picture;

        public String getVehicle_type() {
            return vehicle_type;
        }

        public void setVehicle_type(String vehicle_type) {
            this.vehicle_type = vehicle_type;
        }

        public String getMobile_number() {
            return mobile_number;
        }

        public void setMobile_number(String mobile_number) {
            this.mobile_number = mobile_number;
        }

        private String vehicle_type;

        public String getCurrency_symbol() {
            return currency_symbol;
        }

        public void setCurrency_symbol(String currency_symbol) {
            this.currency_symbol = currency_symbol;
        }

        private String currency_symbol;
        private String rating;
        private String email;
        private String mobile_number;
        private String forgot_key;
        private String image;
        private String city;
        private String registration_number;
        private String number_plate;
        private String vehicle_manufacturer;
        private String vehicle_model;
        private int vehicle_type_id;
        private String vehicle_color;
        private String device_id;
        private String device_token;
        private String device_type;
        private String latitude;
        private String longitude;
        private String payment_method;
        private String cancellation_charge;
        private int is_verified;
        private int is_visible;
        private int identity_verification;
        private int vehicle_verification;
        private int document_verification;
        private int user_status;
        private String block_reason;
        private String status;
        private String created_at;
        private String updated_at;
        private List<UserRoleBean> user_role;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone_no() {
            return mobile_number;
        }

        public void setPhone_no(String mobile_number) {
            this.mobile_number = mobile_number;
        }

        public String getForgot_key() {
            return forgot_key;
        }

        public void setForgot_key(String forgot_key) {
            this.forgot_key = forgot_key;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegistration_number() {
            return registration_number;
        }

        public void setRegistration_number(String registration_number) {
            this.registration_number = registration_number;
        }

        public String getNumber_plate() {
            return number_plate;
        }

        public void setNumber_plate(String number_plate) {
            this.number_plate = number_plate;
        }

        public String getVehicle_manufacturer() {
            return vehicle_manufacturer;
        }

        public void setVehicle_manufacturer(String vehicle_manufacturer) {
            this.vehicle_manufacturer = vehicle_manufacturer;
        }

        public String getVehicle_model() {
            return vehicle_model;
        }

        public void setVehicle_model(String vehicle_model) {
            this.vehicle_model = vehicle_model;
        }

        public int getVehicle_type_id() {
            return vehicle_type_id;
        }

        public void setVehicle_type_id(int vehicle_type_id) {
            this.vehicle_type_id = vehicle_type_id;
        }

        public String getVehicle_color() {
            return vehicle_color;
        }

        public void setVehicle_color(String vehicle_color) {
            this.vehicle_color = vehicle_color;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public String getCancellation_charge() {
            return cancellation_charge;
        }

        public void setCancellation_charge(String cancellation_charge) {
            this.cancellation_charge = cancellation_charge;
        }

        public int getIs_verified() {
            return is_verified;
        }

        public void setIs_verified(int is_verified) {
            this.is_verified = is_verified;
        }

        public int getIs_visible() {
            return is_visible;
        }

        public void setIs_visible(int is_visible) {
            this.is_visible = is_visible;
        }

        public int getIdentity_verification() {
            return identity_verification;
        }

        public void setIdentity_verification(int identity_verification) {
            this.identity_verification = identity_verification;
        }

        public int getVehicle_verification() {
            return vehicle_verification;
        }

        public void setVehicle_verification(int vehicle_verification) {
            this.vehicle_verification = vehicle_verification;
        }

        public int getDocument_verification() {
            return document_verification;
        }

        public void setDocument_verification(int document_verification) {
            this.document_verification = document_verification;
        }

        public int getUser_status() {
            return user_status;
        }

        public void setUser_status(int user_status) {
            this.user_status = user_status;
        }

        public String getBlock_reason() {
            return block_reason;
        }

        public void setBlock_reason(String block_reason) {
            this.block_reason = block_reason;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public List<UserRoleBean> getUser_role() {
            return user_role;
        }

        public void setUser_role(List<UserRoleBean> user_role) {
            this.user_role = user_role;
        }

        public static class UserRoleBean {
            /**
             * id : 2
             * role : user
             * status : AC
             * created_at : -0001-11-30 00:00:00
             * updated_at : 2018-05-15 14:43:11
             * pivot : {"user_id":2,"role_id":2}
             */

            private int id;
            private String role;
            private String status;
            private String created_at;
            private String updated_at;
            private PivotBean pivot;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public PivotBean getPivot() {
                return pivot;
            }

            public void setPivot(PivotBean pivot) {
                this.pivot = pivot;
            }

            public static class PivotBean {
                /**
                 * user_id : 2
                 * role_id : 2
                 */

                private int user_id;
                private int role_id;

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public int getRole_id() {
                    return role_id;
                }

                public void setRole_id(int role_id) {
                    this.role_id = role_id;
                }
            }
        }
    }
}
