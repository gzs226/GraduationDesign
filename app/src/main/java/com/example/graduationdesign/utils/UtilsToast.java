package com.example.graduationdesign.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by gg on 2016/11/6.
 */
public class UtilsToast
{
    public static void Toast(Context context, String text, int time)
    {
        if (time == 0)
        {
            Toast.makeText(context, "" + text, Toast.LENGTH_SHORT).show();
        } else if (time == 1)
        {
            Toast.makeText(context, "" + text, Toast.LENGTH_LONG).show();
        }

    }
}
