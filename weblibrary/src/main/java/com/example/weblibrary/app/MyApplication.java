package com.example.weblibrary.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by gg on 2016/11/5.
 */
public class MyApplication extends Application
{
    public static RequestQueue mQueues;

    public void onCreate()
    {
        super.onCreate();
        mQueues = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueue()
    {
        return mQueues;
    }
}
