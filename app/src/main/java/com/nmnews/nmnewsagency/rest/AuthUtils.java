package com.nmnews.nmnewsagency.rest;

import android.util.Base64;

/**
 * Created by Vishnu Saini on 2/20/2018.
 * vishnusainideveloper27@gmail.com
 */
public class AuthUtils {

    /**
     * <p><b>Author: praveen singh @ B.R. Softech<b></p>
     * <p>Date: 24/6/16</p>
     * <p>Time: 12:39 PM</p>
     * <p>Project: pidaproject</p>
     */
    public static String basic(String email, String password) {

        String basicAuth = "Basic " +
                Base64.encodeToString(String.format("%s:%s", email, password)
                                .getBytes(),
                        Base64.NO_WRAP);

        return basicAuth;
    }
}
