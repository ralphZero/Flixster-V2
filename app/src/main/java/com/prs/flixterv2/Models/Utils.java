package com.prs.flixterv2.Models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.prs.flixterv2.R;

public class Utils {

    private static int sTheme;
    private final static int THEME_FLIXSTER_LIGHT = 0;
    private final static int THEME_FLIXSTER_DARK = 1;

    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }



    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_FLIXSTER_LIGHT:
                activity.setTheme(R.style.Theme_FlixsterLight);
                break;
            case THEME_FLIXSTER_DARK:
                activity.setTheme(R.style.Theme_FlixsterDark);
                break;
        }

    }

}
