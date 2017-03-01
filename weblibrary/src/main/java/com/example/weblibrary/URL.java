package com.example.weblibrary;

/**
 * Created by gg on 2016/11/5.
 */
public class URL {

    private static final String mURL = "http://172.19.75.167/WWW/GraduationDesign/index.php/gd_api";

    /**
     * 用户登录的URL
     */
    public static final String login = mURL + "/user/login?";
    /**
     * 用户注册的URL
     */
    public static final String register = mURL + "/user/register?";
    /**
     * 用户更改密码的URL
     */
    public static final String updateuserpassword = mURL + "/user/updateuserpassword?";
    /**
     * 用户更改密码的URL
     */
    public static final String updatepersonalmessage = mURL + "/user/updatepersonalmessage?";
    /**
     * 上传头像的的URL
     */
    public static final String uploadImage = mURL + "/user/uploadImage";
    /**
     * 获取用户的个人资料
     */
    public static final String getuserinformation = mURL + "/user/getuserinformation";
    /**
     * 发布新的贴子
     */
    public static final String insertcontanctpost = mURL + "/sociacontact/insertcontanctpost?";
    /**
     * 获取帖子
     */
    public static final String selectpostall = mURL + "/sociacontact/selectpostall?";
    /**
     * 获取帖子
     */
    public static final String selectpostsort = mURL + "/sociacontact/selectpostsort?";
    /**
     * 获取帖子的评论
     */
    public static final String selectcommentbytopicid =
            mURL + "/socialcontactcomment/selectcommentbytopicid?";
    /**
     * 获取帖子的评论
     */
    public static final String selectquestionbyquestionid =
            mURL + "/questioncomment/selectquestionbyquestionid?";
    /**
     * 获取版本信息
     */
    public static final String selectversion = mURL + "/questionBank/selectversion?";
    /**
     * 更新题库
     */
    public static final String selectquestionbankpage =
            mURL + "/questionBank/selectquestionbankpage?";
    /**
     * 写评论
     */
    public static final String insertcontanctcomment =
            mURL + "/socialContactComment/insertcontanctcomment?";
    /**
     * 写评论
     */
    public static final String insertquestioncomment =
            mURL + "/questionComment/insertquestioncomment?";
    /**
     * 写评论
     */
    public static final String downloadimage = mURL + "/user/downloadImage?";
    /**
     * 点赞
     */
    public static final String insertquestionprase = mURL + "/praise/insertquestionprase?";
    /**
     * 查询点赞
     */
    public static final String selectprasebyQidUid = mURL + "/praise/selectprasebyQidUid?";
}
