package com.example.weblibrary.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyApplication;
import com.example.weblibrary.model.PostCommentDataResult;
import com.example.weblibrary.model.QuestionData;
import com.google.gson.Gson;

/**
 * Created by gg on 2016/11/5.
 * 用户登录的类
 */
public class QuestionSelectUpdateLocal {
    /**
     * 用户登录方法
     *
     * @param page                  -用户名
     * @param number                -密码
     * @param mIUpdateLocalQuestion 登录回调
     */
    public void UpdateLocalQuestion(final String page, final String number, final IUpdateLocalQuestion mIUpdateLocalQuestion) {
        String UrlLogin = URL.selectquestionbankpage + "page=" + page + "&" + "number=" + number;
        Log.e("onResponse", "SelectCommentByTopicId: " + UrlLogin);
        StringRequest request = new StringRequest(Request.Method.GET, UrlLogin, new Response.Listener<String>() {
            public void onResponse(String s) {
                Log.e("onResponse", "" + s);
                Gson gson = new Gson();
                QuestionData mQuestionData = gson.fromJson(s, QuestionData.class);
                mIUpdateLocalQuestion.Success(mQuestionData);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mIUpdateLocalQuestion.Error(volleyError);
            }
        });
        request.setTag("selectquestionbankpage");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface IUpdateLocalQuestion {
        void Success(QuestionData mQuestionData);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }

}
