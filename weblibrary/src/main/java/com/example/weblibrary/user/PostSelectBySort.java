package com.example.weblibrary.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyWebApplication;
import com.example.weblibrary.model.PostDataResult;
import com.example.weblibrary.model.Postresuldata;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by gg on 2016/11/5.
 * 用户登录的类
 */
public class PostSelectBySort {
    /**
     * 用户登录方法
     *
     * @param page               -用户名
     * @param number             -密码
     * @param mISelectPostBySort 登录回调
     */
    public void SelectPostBySort(final String page, final String number, final String posttype, final ISelectPostBySort mISelectPostBySort) {
        //username=444&password=555
        String UrlLogin =
                URL.selectpostsort + "page=" + page + "&" + "number=" + number + "&" + "posttype=" +
                posttype;
        Log.e("SelectPostBySort", "SelectPostBySort: " + UrlLogin);
        StringRequest request = new StringRequest(Request.Method.GET, UrlLogin, new Response.Listener<String>() {
            public void onResponse(String s) {
                Log.d("onResponse", "" + s);
                Gson gson = new Gson();
                PostDataResult postResult = gson.fromJson(s, PostDataResult.class);
                if (postResult.getState() == 1 || postResult.getState() == 0) {
                    mISelectPostBySort.Success(postResult.getResultdata());
                } else if (s.equals("0")) {
                    mISelectPostBySort.Faild("登录失败！");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mISelectPostBySort.Error(volleyError);
            }
        });
        request.setTag("login");
        MyWebApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface ISelectPostBySort {
        void Success(List<Postresuldata> listPostresuldata);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }

}
