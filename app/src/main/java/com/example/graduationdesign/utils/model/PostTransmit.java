package com.example.graduationdesign.utils.model;

import java.io.Serializable;

public class PostTransmit implements Serializable {

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

    public final String getUserschool() {
        return userschool;
    }

    public final void setUserschool(String userschool) {
        this.userschool = userschool;
    }

    public final int getPostid() {
        return postid;
    }

    public final void setPostid(int postid) {
        this.postid = postid;
    }

    public final int getUserid() {
        return userid;
    }

    public final void setUserid(int userid) {
        this.userid = userid;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final String getPosttitle() {
        return posttitle;
    }

    public final void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public final String getPostsort() {
        return postsort;
    }

    public final void setPostsort(String postsort) {
        this.postsort = postsort;
    }

    public final String getPosttype() {
        return posttype;
    }

    public final void setPosttype(String posttype) {
        this.posttype = posttype;
    }

    public final String getPostcontent() {
        return postcontent;
    }

    public final void setPostcontent(String postcontent) {
        this.postcontent = postcontent;
    }

    public final int getCommentnumber() {
        return commentnumber;
    }

    public final void setCommentnumber(int commentnumber) {
        this.commentnumber = commentnumber;
    }

    public final int getPraisenumber() {
        return praisenumber;
    }

    public final void setPraisenumber(int praisenumber) {
        this.praisenumber = praisenumber;
    }

    public final String getPublictime() {
        return publictime;
    }

    public final void setPublictime(String publictime) {
        this.publictime = publictime;
    }
}
