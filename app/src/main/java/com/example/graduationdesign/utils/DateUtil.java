package com.example.graduationdesign.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    public static int[] getScreenSize(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int[] ScreenSize = new int[2];
        ScreenSize[0] = dm.widthPixels;
        ScreenSize[1] = dm.heightPixels;
        manager = null;
        return ScreenSize;
    }

    //格式2015-11-22 17:34
    public static String getStartDate() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR) - 2;
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String startDate = year + "-" + 1 + "-" + 1;
        return startDate;
    }

    //格式2015-11-22 17:34
    public static String getEndDate() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR) + 6;
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String endDate = year + "-" + 12 + "-" + 31;
        return endDate;
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */

    public static Date parse(String strDate, String pattern) {

        if (isEmpty(strDate)) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || "null".equalsIgnoreCase(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

}
