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
public class SelectQuestionPraise {
    /**
     * 用户登录方法
     *
     * @param mISelectQuestionPraise 发帖的回调
     */
    public void SelectQuestionPraise(final String userid, String questionId, final ISelectQuestionPraise mISelectQuestionPraise) {
        //username=444&password=555
        String UrlSelectQuestionPraise =
                URL.selectprasebyQidUid + "userid=" + userid + "&questionid=" + questionId;
        Log.e("onResponse", "SelectQuestionPraise: "+UrlSelectQuestionPraise );
        StringRequest request = new StringRequest(Request.Method.GET, UrlSelectQuestionPraise, new Response.Listener<String>() {
            public void onResponse(String s) {
                if (s.equals("1")) {
                    mISelectQuestionPraise.Success("已赞");
                } else if (s.equals("0")) {
                    mISelectQuestionPraise.Faild("未赞");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mISelectQuestionPraise.Error(volleyError);
            }
        });
        request.setTag("questionprase");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface ISelectQuestionPraise {
        void Success(String successMessage);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }


}
