package com.nmnews.nmnewsagency.utils;

import android.app.Activity;

/**
 * Created by ubuntu on 21/6/16.
 */

//  validator class
public class Validator {
    public static boolean isEmptyEmail(Activity act, String email) {
        if (email.isEmpty()) {
            Utils.showToast(act, "Please enter your email");
            return true;
        }
        return false;

    }

    public static boolean isEmptypassword(Activity act, String password) {
        if (password.isEmpty()) {
            Utils.showToast(act, "Please enter your password");
            return true;
        }
        return false;
    }

    public static boolean isEmptyLoginInfo(Activity act, String email, String password) {
        if (email.isEmpty() && password.isEmpty()) {
            Utils.showToast(act, " Please enter valid email & password");
            return true;
        }
        return false;
    }

    public static boolean checkNullValidation(Activity act, String s, String msg) {

        if(s==null )
        {
            return false;
        }
        s = s.trim();
        if (s == null || s.isEmpty()) {
            Utils.showToast(act, msg);
            return false;
        }

        return true;
    }

    public static boolean validateLName(Activity act, String lName, String msg) {
        if (checkStringsContainsOnlySpecialChar(lName)) {
            Utils.showToast(act, msg);
        } else if (isNumeric(lName)) {
            Utils.showToast(act, msg);
        } else {
            return true;
        }
        return false;
    }

    public static boolean checkStringsContainsOnlySpecialChar(String inputString) {
        boolean found = false;
        try {
            String splChrs = "/^[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$/";
            found = inputString.matches("[" + splChrs + "]+");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    public static boolean checkSpaceValidation(Activity act, String s, String msg) {

        if (s == null || s.contains(" ")) {
            Utils.showToast(act, msg);
            return false;
        }

        return true;
    }

    public static boolean isNumeric(String str) {
        try {
            long d = Long.parseLong(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public static boolean checkpassword(Activity act, String password, String config_pass) {
        boolean status = true;
        if (password != null && config_pass != null) {
            if (!password.equals(config_pass)) {
                Utils.showToast(act, " Password And Confirm Password must be same");
                status = false;
            }
        }
        return status;
    }

    public static boolean checkpasswordValidation(Activity act, String password, String message) {
        boolean status = true;
        if (password != null && password.length() < 6) {
            Utils.showToast(act, message);
            status = false;

        }
        return status;
    }
   /* public static boolean checkprovidertype(Activity act, boolean status)
    {

    }
*/

    public static boolean isEmailValid(Activity act, CharSequence email, String message) {
        boolean b = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (!b) {
            Utils.showToast(act, message);

        }
        return b;
    }

    public static boolean isValidMobileNumber(Activity act, String phone, String message) {
        boolean b = true;
        int lenght = phone.trim().length();
        //  if (phone.isEmpty()||!isNumeric(phone) || phone.trim().length() != Constants.MAXLENGTHOFPHONENUMBER) {
        if (phone.isEmpty() || !isNumeric(phone) || phone.trim().length() != 10) {
            Utils.showToast(act, message);
            b = false;
        }
        return b;
    }

    public static boolean isValidPhoneNumber(Activity act, String phone, String message) {
        boolean b = true;
        int lenght = phone.trim().length();
        //  if (phone.isEmpty()||!isNumeric(phone) || phone.trim().length() != Constants.MAXLENGTHOFPHONENUMBER) {
        if (phone.isEmpty() || !isNumeric(phone) || phone.trim().length() < 6) {
            Utils.showToast(act, message);
            b = false;
        }
        return b;
    }
}
