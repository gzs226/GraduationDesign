package com.example.weblibrary.user;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weblibrary.URL;
import com.example.weblibrary.app.MyWebApplication;
import com.example.weblibrary.model.PostCommentData;

/**
 * Created by gg on 2016/11/5.
 * 用户登录的类
 */
public class CommentWriteNew {
    /**
     * 用户登录方法
     *
     * @param mPostCommentData -发贴的信息
     * @param mIWritePost      发帖的回调
     */
    public void WritePost(final PostCommentData mPostCommentData, final IWritePost mIWritePost) {
        //username=444&password=555
        String UrlWriteComment = URL.insertcontanctcomment + TransformPost(mPostCommentData);
        Log.e("WritePost---", "WritePost: " + UrlWriteComment);
        StringRequest request = new StringRequest(Request.Method.GET, UrlWriteComment, new Response.Listener<String>() {
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
        MyWebApplication.getHttpQueue().add(request);
    }
    /**
     * 用户登录方法
     *
     * @param mPostCommentData -发贴的信息
     * @param mIWritePost      发帖的回调
     */
    public void WriteQuestion(final PostCommentData mPostCommentData, final IWritePost mIWritePost) {
        //username=444&password=555
        String UrlWriteComment = URL.insertquestioncomment + TransformPost(mPostCommentData);
        Log.e("WritePost", "WritePost: " + UrlWriteComment);
        StringRequest request = new StringRequest(Request.Method.GET, UrlWriteComment, new Response.Listener<String>() {
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
        MyWebApplication.getHttpQueue().add(request);
    }

    /**
     * 用户登录的回调
     */
    public interface IWritePost {
        void Success(String successMessage);

        void Faild(String failMessage);

        void Error(VolleyError volleyError);
    }

    private String TransformPost(PostCommentData mPostCommentData) {
        String postInfor = "";
        if (mPostCommentData.getTopic_id() != -1) {
            postInfor = postInfor + "topic_id=" + mPostCommentData.getTopic_id();
        }
        if (mPostCommentData.getTopic_type() != null) {
            postInfor = postInfor + "&" + "topic_type=" + mPostCommentData.getTopic_type();
        }
        if (mPostCommentData.getContent() != null) {
            postInfor = postInfor + "&" + "content=" + mPostCommentData.getContent();
        }
        if (mPostCommentData.getFrom_uid() != -1) {
            postInfor = postInfor + "&" + "from_uid=" + mPostCommentData.getFrom_uid();
        }
        if (mPostCommentData.getFrom_uname() != null) {
            postInfor = postInfor + "&" + "from_uname=" + mPostCommentData.getFrom_uname();
        }
        if (mPostCommentData.getTo_uid() != -1) {
            postInfor = postInfor + "&" + "to_uid=" + mPostCommentData.getTo_uid();
        }
        if (mPostCommentData.getTo_uname() != null) {
            postInfor = postInfor + "&" + "to_uname=" + mPostCommentData.getTo_uname();
        }
        if (mPostCommentData.getComment_time() != null) {
            postInfor = postInfor + "&" + "comment_time=" + "2017-01-30-15:26:05";//mPostCommentData.getComment_time();
        }
        if (mPostCommentData.getComment_number() != -1) {
            postInfor = postInfor + "&" + "comment_number=" + mPostCommentData.getComment_number();
        }
        if (mPostCommentData.getPraise_number() != -1) {
            postInfor = postInfor + "&" + "praise_number=" + mPostCommentData.getPraise_number();
        }
        return postInfor;
    }

}
