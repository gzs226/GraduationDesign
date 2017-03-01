package com.example.weblibrary.user;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyApplication;

/**
 * Created by gg on 2016/11/5.
 */
public class UserCheckname {
    public void checkUsername(String username) {
        String UrlRegister = URL.register + "username=" + username;
        StringRequest request = new StringRequest(Request.Method.GET, UrlRegister, new Response.Listener<String>() {
            public void onResponse(String s) {
                if (s.equals("1")) {
                } else if (s.equals("0")) {
                }

            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        request.setTag("register");
        MyApplication.getHttpQueue().add(request);
    }

}
