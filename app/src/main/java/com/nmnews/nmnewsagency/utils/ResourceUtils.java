package com.nmnews.nmnewsagency.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.nmnews.nmnewsagency.Application;


public class ResourceUtils {

    public static String getString(@StringRes int stringId) {
        return Application.getInstance().getString(stringId);
    }

    public static Drawable getDrawable(@DrawableRes int drawableId) {
        return Application.getInstance().getResources().getDrawable(drawableId);
    }

    public static int getColor(@ColorRes int colorId) {
        return Application.getInstance().getResources().getColor(colorId);
    }

    public static int getDimen(@DimenRes int dimenId) {
        return (int) Application.getInstance().getResources().getDimension(dimenId);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}