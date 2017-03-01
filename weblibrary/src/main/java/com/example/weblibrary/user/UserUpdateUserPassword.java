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
 * 用户登录的类
 */
public class UserUpdateUserPassword {
    /**
     * 用户登录方法
     *
     * @param userid               -用Id
     * @param oldPassword          -旧密码
     * @param newPassword          -新密码
     * @param mIUpdateUserPassword 登录回调
     */
    public void UpdateUserPassword(final String userid, final String oldPassword, final String newPassword, final IUpdateUserPassword mIUpdateUserPassword) {
        //username=444&password=555
        String UrlLogin =
                URL.updateuserpassword + "userid=" + userid + "&" + "oldpassword=" + oldPassword +
                "&" + "newpassword=" + newPassword;
        StringRequest request = new StringRequest(Request.Method.GET, UrlLogin, new Response.Listener<String>() {
            public void onResponse(String s) {
                Log.d("onResponse", "" + s);
                if (s.equals("1")) {
                    mIUpdateUserPassword.Success("完成更新！");
                } else if (s.equals("0")) {
                    mIUpdateUserPassword.Faild("密码更新失败！");
                } else if (s.equals("-1")) {
                    mIUpdateUserPassword.Faild("原密码不正确！");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mIUpdateUserPassword.Error(volleyError);
            }
        });
        request.setTag("updateuserpassword");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface IUpdateUserPassword {
        public void Success(String successMessage);

        public void Faild(String failMessage);

        public void Error(VolleyError volleyError);
    }

}
