package com.example.weblibrary.user;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyApplication;

/**
 * Created by gg on 2016/11/5.
 * 用户注册的类
 */
public class UserRegister
{
    /**
     * 用户注册方法
     *
     * @param username   -用户名
     * @param password   -密码
     * @param mIRegister -注册回调
     */
    public void register(final String username, final String password, final IRegister mIRegister)
    { //password=555&username=gzs
        String UrlRegister = URL.register + "username=" + username + "&password=" + password;
        StringRequest request = new StringRequest(Request.Method.GET, UrlRegister, new Response.Listener<String>()
        {
            public void onResponse(String s)
            {
                Log.d("onResponse", "" + s);
                if (s.equals("1"))
                {
                    mIRegister.Success("注册成功！");
                } else if (s.equals("0"))
                {
                    mIRegister.Faild("用户名已存在！");
                }

            }
        }, new Response.ErrorListener()
        {
            public void onErrorResponse(VolleyError volleyError)
            {
                mIRegister.Error(volleyError);
            }
        });
        request.setTag("register");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 用户注册回调
     */
    public interface IRegister
    {
        public void Success(String successMessage);

        public void Faild(String failMessage);

        public void Error(VolleyError volleyError);
    }

}
