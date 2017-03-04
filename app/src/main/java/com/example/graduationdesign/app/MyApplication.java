package com.example.graduationdesign.app;


import android.support.v7.app.AppCompatDelegate;

import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.weblibrary.app.MyWebApplication;

/**
 * Created by gg on 2016/11/5.
 */
public class MyApplication extends MyWebApplication {

    public void onCreate() {
        super.onCreate();
    }

    private void IsFrist() {
        boolean isFrist = ConfigUserMessagePrefs.getValue(this, Contents.IS_FRIST, true);
        if (isFrist) {
            ConfigUserMessagePrefs.putValue(this, Contents.IS_FRIST, false);
            ConfigUserMessagePrefs.putValue(this, Contents.DAY_NIGHT_MODEL, Contents.IS_DAY);
            ConfigUserMessagePrefs.putValue(this, Contents.DAY_NIGHT_STATE, Contents.IS_DAY);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
