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
public class PostWriteNew {
    /**
     * 用户登录方法
     *
     * @param mPostresuldata -发贴的信息
     * @param mIWritePost    发帖的回调
     */
    public void WritePost(final Postresuldata mPostresuldata, final IWritePost mIWritePost) {
        //username=444&password=555
        String UrlWritePost = URL.insertcontanctpost + TransformPost(mPostresuldata);
        StringRequest request = new StringRequest(Request.Method.GET, UrlWritePost, new Response.Listener<String>() {
            public void onResponse(String s) {
                Log.e("onResponse", "" + s);
                if (s.equals("1")) {
                    mIWritePost.Success("发布成功！");
                } else if (s.equals("0")) {
                    mIWritePost.Faild("发布失败！");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                mIWritePost.Error(volleyError);
            }
        });
        request.setTag("writepost");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface IWritePost {
        void Success(String successMessage);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }

    private String TransformPost(Postresuldata mPostresuldata) {
        String postInfor = "";
        if (mPostresuldata.getUserid() != -1) {
            postInfor = postInfor + "userid=" + mPostresuldata.getUserid();
        }
        if (mPostresuldata.getUserschool() != null) {
            postInfor = postInfor + "&" + "userschool=" + mPostresuldata.getUserschool();
        }
        if (mPostresuldata.getCommentnumber() != -1) {
            postInfor = postInfor + "&" + "commentnumber=" + mPostresuldata.getCommentnumber();
        }
        if (mPostresuldata.getPosttitle() != null) {
            postInfor = postInfor + "&" + "posttitle=" + mPostresuldata.getPosttitle();
        }
        if (mPostresuldata.getPostcontent() != null) {
            postInfor = postInfor + "&" + "postcontent=" + mPostresuldata.getPostcontent();
        }
        if (mPostresuldata.getPostsort() != null) {
            postInfor = postInfor + "&" + "postsort=" + mPostresuldata.getPostsort();
        }
        if (mPostresuldata.getPosttype() != null) {
            postInfor = postInfor + "&" + "posttype=" + mPostresuldata.getPosttype();
        }
        if (mPostresuldata.getPraisenumber() != -1) {
            postInfor = postInfor + "&" + "praisenumber=" + mPostresuldata.getPraisenumber();
        }
        if (mPostresuldata.getPublictime() != null) {
            postInfor = postInfor + "&" + "publictime=" + mPostresuldata.getPublictime();
        }
        if (mPostresuldata.getUsername() != null) {
            postInfor = postInfor + "&" + "username=" + mPostresuldata.getUsername();
        }
        return postInfor;
    }

}
