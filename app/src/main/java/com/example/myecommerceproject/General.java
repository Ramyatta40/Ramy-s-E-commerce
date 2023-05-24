package com.example.myecommerceproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
* Created by ADMIN on 8/21/2016.
*/

public class General {


    public static void addToSharedPreference(Context c, String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor e = sp.edit();
        e.putString(key, value);
        e.commit();
    }

    public static void addToLongPreference(Context c, String key, long value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor e = sp.edit();
        e.putLong(key, value);
        e.commit();
    }

    public static void addToSharedPreference(Context c, String key, boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean(key, value);
        e.commit();
    }


    public static void addToSharedPreference(Context c, String key, int value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor e = sp.edit();
        e.putInt(key, value);
        e.commit();
    }

    public static String getPreferenceValue(Context c, String key, String defaultValue) {
        String result;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        result = sp.getString(key, defaultValue);
        return result;
    }

    public static int getIntegerPreferenceValue(Context c, String key, int defaultValue) {
        int result = 0;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        result = sp.getInt(key, defaultValue);
        return result;
    }

    public static long getLongPreferenceValue(Context c, String key, long defaultValue) {
        long result = 0;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        result = sp.getLong(key, defaultValue);
        return result;
    }

    public static boolean getBooleanPreferenceValue(Context c, String key, boolean defaultValue) {
        boolean result;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);
        result = sp.getBoolean(key, defaultValue);
        return result;
    }



    public static void goToActivity(Context c, Class NewActivity) {
        Intent i = new Intent(c, NewActivity);
        c.startActivity(i);
    }


}
