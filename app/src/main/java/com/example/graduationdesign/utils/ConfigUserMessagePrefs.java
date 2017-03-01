package com.example.graduationdesign.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class ConfigUserMessagePrefs {
    public final static String SETTING = "UserMessage";

    public static void putValue(Context context, String key, int value) {
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.apply();
    }

    public static void putValue(Context context, String key, float value) {
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putFloat(key, value);
        sp.apply();
    }

    public static void putValue(Context context, String key, boolean value) {
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.apply();
    }

    public static void putValue(Context context, String key, String value) {
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.apply();
    }

    public static int getValue(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static float getValue(Context context, String key, float defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static boolean getValue(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static String getValue(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }
}

