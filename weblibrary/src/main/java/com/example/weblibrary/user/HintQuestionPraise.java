package com.example.weblibrary.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyApplication;
import com.example.weblibrary.model.Postresuldata;

/**
 * Created by gg on 2016/11/5.
 * 用户登录的类
 */
public class HintQuestionPraise {
    /**
     * 用户登录方法
     *
     * @param mIQuestionPraise 发帖的回调
     */
    public void QuestionPraise(final String userid, String questionId, final IQuestionPraise mIQuestionPraise) {
        //username=444&password=555
        String UrlQuestionPraise =
                URL.insertquestionprase + "userid=" + userid + "&questionid=" + questionId;

        Log.e("onResponse", "SelectQuestionPraise: "+UrlQuestionPraise );
        StringRequest request = new StringRequest(Request.Method.GET, UrlQuestionPraise, new Response.Listener<String>() {
            public void onResponse(String s) {
                if (s.equals("1")) {
                    mIQuestionPraise.Success("点赞成功！");
                } else if (s.equals("0")) {
                    mIQuestionPraise.Faild("点赞失败！");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mIQuestionPraise.Error(volleyError);
            }
        });
        request.setTag("questionprase");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface IQuestionPraise {
        void Success(String successMessage);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }


}
