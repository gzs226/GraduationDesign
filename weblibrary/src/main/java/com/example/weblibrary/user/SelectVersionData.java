package com.example.weblibrary.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyWebApplication;
import com.example.weblibrary.model.resultVersionData;
import com.example.weblibrary.model.versiondata;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by gg on 2016/11/5.
 * 用户登录的类
 */
public class SelectVersionData {
    /**
     * 用户登录方法
     *
     * @param mISelectVersion 登录回调
     */
    public void SelectVersion(final ISelectVersion mISelectVersion) {
        //username=444&password=555
        String UrlLogin = URL.selectversion ;
        StringRequest request = new StringRequest(Request.Method.GET, UrlLogin, new Response.Listener<String>() {
            public void onResponse(String s) {
                Log.d("onResponse", "" + s);
                Gson gson = new Gson();
                resultVersionData postResult = gson.fromJson(s, resultVersionData.class);
                if (postResult.getNumber() >0 ) {
                    mISelectVersion.Success(postResult.getVersiondata());
                } else if (s.equals("0")) {
                    mISelectVersion.Faild("获取数据失败！");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mISelectVersion.Error(volleyError);
            }
        });
        request.setTag("selectversion");
        MyWebApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface ISelectVersion {
        void Success(List<versiondata> listresultVersionData);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }

}
