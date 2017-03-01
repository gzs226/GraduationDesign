package com.example.weblibrary.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyApplication;
import com.example.weblibrary.model.PostCommentDataResult;
import com.google.gson.Gson;

/**
 * Created by gg on 2016/11/5.
 * 用户登录的类
 */
public class CommentSelectByQuestionId {
    /**
     * 用户登录方法
     *
     * @param page                     -用户名
     * @param number                   -密码
     * @param mISelectCommentByTopicId 登录回调
     */
    public void SelectCommentByTopicId(final String page, final String number, final String topic_id, final ISelectCommentByTopicId mISelectCommentByTopicId) {
        String UrlLogin =
                URL.selectquestionbyquestionid + "page=" + page + "&" + "number=" + number + "&" +
                "topic_id=" + topic_id;
        Log.e("onResponse", "SelectCommentByTopicId: "+UrlLogin );
        StringRequest request = new StringRequest(Request.Method.GET, UrlLogin, new Response.Listener<String>() {
            public void onResponse(String s) {
                Log.e("onResponse", "" + s);
                Gson gson = new Gson();
                PostCommentDataResult postCommentDataResult = gson.fromJson(s, PostCommentDataResult.class);
                mISelectCommentByTopicId.Success(postCommentDataResult);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mISelectCommentByTopicId.Error(volleyError);
            }
        });
        request.setTag("selectcommentbytopicid");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface ISelectCommentByTopicId {
        void Success(PostCommentDataResult postCommentDataResult);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }

}
