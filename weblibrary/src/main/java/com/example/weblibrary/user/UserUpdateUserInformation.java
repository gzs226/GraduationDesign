package com.example.weblibrary.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyApplication;
import com.example.weblibrary.model.UserInformation;

/**
 * Created by gg on 2016/11/5.
 * 用户登录的类
 */
public class UserUpdateUserInformation {
    /**
     * 用户登录方法
     *
     * @param mUserInformation        -用户更新信息
     * @param mIUpdateUserInformation 登录回调
     */
    public void UpdateUserInformation(final UserInformation mUserInformation, final IUpdateUserInformation mIUpdateUserInformation) {
        //username=444&password=555
        String UrlLogin = URL.updatepersonalmessage + TransformInformation(mUserInformation);
        StringRequest request = new StringRequest(Request.Method.GET, UrlLogin, new Response.Listener<String>() {
            public void onResponse(String s) {
                Log.e("onResponse", "---" + s);
                if (s.equals("1")) {
                    mIUpdateUserInformation.Success("更新完成！");
                } else if (s.equals("0")) {
                    mIUpdateUserInformation.Faild("更新失败！");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mIUpdateUserInformation.Error(volleyError);
                Log.e("onResponse", "---" + volleyError.toString());
            }
        });
        request.setTag("updatepersonalmessage");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface IUpdateUserInformation {
        public void Success(String successMessage);

        public void Faild(String failMessage);

        public void Error(VolleyError volleyError);
    }

    private String TransformInformation(final UserInformation mUserInformation) {
        String userinfor = "";
        if (mUserInformation.getUserid() != null) {
            userinfor = "userid=" + mUserInformation.getUserid();
        }
        if (mUserInformation.getUsericon() != null) {
            userinfor = userinfor + "&" + "usericon=" + mUserInformation.getUsericon();
        } else {
            userinfor = userinfor + "&" + "usericon=" + "-200";
        }
        if (mUserInformation.getUsergender() != null) {
            userinfor = userinfor + "&" + "usergender=" + mUserInformation.getUsergender();
        } else {
            userinfor = userinfor + "&" + "usergender=" + "-200";
        }
        if (mUserInformation.getUserschool() != null) {
            userinfor = userinfor + "&" + "userschool=" + mUserInformation.getUserschool();
        } else {
            userinfor = userinfor + "&" + "userschool=" + "-200";
        }
        if (mUserInformation.getUsermajor() != null) {
            userinfor = userinfor + "&" + "usermajor=" + mUserInformation.getUsermajor();
        } else {
            userinfor = userinfor + "&" + "usermajor=" + "-200";
        }
        if (mUserInformation.getAimschool() != null) {
            userinfor = userinfor + "&" + "aimschool=" + mUserInformation.getAimschool();
        } else {
            userinfor = userinfor + "&" + "aimschool=" + "-200";
        }
        if (mUserInformation.getAimmajor() != null) {
            userinfor = userinfor + "&" + "aimmajor=" + mUserInformation.getAimmajor();
        } else {
            userinfor = userinfor + "&" + "aimmajor=" + "-200";
        }
        if (mUserInformation.getExamtime() != null) {
            userinfor = userinfor + "&" + "examtime=" + mUserInformation.getExamtime();
        } else {
            userinfor = userinfor + "&" + "examtime=" + "-200";
        }
        return userinfor;
    }

}
