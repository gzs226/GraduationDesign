package com.example.weblibrary.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyApplication;
import com.example.weblibrary.model.UserInformation;
import com.example.weblibrary.model.resultVersionData;
import com.example.weblibrary.model.versiondata;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by gg on 2016/11/5.
 * 获取用户的个人资料
 */
public class UserSelectMessage {
    /**
     * 用户登录方法
     *
     * @param mISelectMessage 登录回调
     */
    public void SelectMessage(final String userid, final ISelectMessage mISelectMessage) {
        //username=444&password=555
        String UrlLogin = URL.getuserinformation + "userid=" + userid;
        StringRequest request = new StringRequest(Request.Method.GET, UrlLogin, new Response.Listener<String>() {
            public void onResponse(String s) {
                Log.d("onResponse", "" + s);
                Gson gson = new Gson();
                UserInformation mUserInformation = gson.fromJson(s, UserInformation.class);

                mISelectMessage.Success(mUserInformation);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mISelectMessage.Error(volleyError);
            }
        });
        request.setTag("selectmessage");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface ISelectMessage {
        void Success(UserInformation mUserInformation);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }

}
