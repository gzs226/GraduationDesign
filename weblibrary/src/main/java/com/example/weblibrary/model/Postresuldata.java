package com.example.weblibrary.model;

/**
 * Created by gg on 2017/2/12.
 */

public class Postresuldata {

    private int postid;
    private int userid;
    private String username;
    private String userschool;
    private String posttitle;
    private String postsort;
    private String posttype;
    private String postcontent;
    private int commentnumber;
    private int praisenumber;
    private String publictime;

    public String getUserschool() {
        return userschool;
    }

    public void setUserschool(String userschool) {
        this.userschool = userschool;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public String getPostsort() {
        return postsort;
    }

    public void setPostsort(String postsort) {
        this.postsort = postsort;
    }

    public String getPosttype() {
        return posttype;
    }

    public void setPosttype(String posttype) {
        this.posttype = posttype;
    }

    public String getPostcontent() {
        return postcontent;
    }

    public void setPostcontent(String postcontent) {
        this.postcontent = postcontent;
    }

    public int getCommentnumber() {
        return commentnumber;
    }

    public void setCommentnumber(int commentnumber) {
        this.commentnumber = commentnumber;
    }

    public int getPraisenumber() {
        return praisenumber;
    }

    public void setPraisenumber(int praisenumber) {
        this.praisenumber = praisenumber;
    }

    public String getPublictime() {
        return publictime;
    }

    public void setPublictime(String publictime) {
        this.publictime = publictime;
    }
}
