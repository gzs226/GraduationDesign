package com.example.weblibrary.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyWebApplication;
import com.example.weblibrary.model.LoginResultData;
import com.google.gson.Gson;

/**
 * Created by gg on 2016/11/5.
 * 用户登录的类
 */
public class UserLogin {
    /**
     * 用户登录方法
     *
     * @param username -用户名
     * @param password -密码
     * @param mILogin  登录回调
     */
    public void Login(final String username, final String password, final ILogin mILogin) {
        //username=444&password=555
        String UrlLogin = URL.login + "username=" + username + "&" + "password=" + password;
        Log.e("Loginurl", "Login: " + UrlLogin);
        StringRequest request = new StringRequest(Request.Method.GET, UrlLogin, new Response.Listener<String>() {
            public void onResponse(String s) {

                Log.d("onResponse", "" + s);
                Gson gson = new Gson();
                LoginResultData mLoginResultData = gson.fromJson(s, LoginResultData.class);

                Log.d("onResponse", "" + s);
                if (mLoginResultData.getNumber() == -1) {
                    mILogin.Faild("登录失败！");
                } else if (mLoginResultData.getNumber() == 1) {
                    mILogin.Success(mLoginResultData.getLoginresult().get(0).getUserid()+"");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mILogin.Error(volleyError);
            }
        });
        request.setTag("login");
        MyWebApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface ILogin {
        void Success(String successMessage);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }

}
