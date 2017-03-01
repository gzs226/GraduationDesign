package com.example.graduationdesign.utils.model;

import java.io.Serializable;

/**
 * Created by gg on 2017/2/14.
 */

public class PostCommentTransmit implements Serializable {
    private int id;
    private int topic_id;
    private String topic_type;
    private String content;
    private int from_uid;
    private String from_uname;
    private int to_uid;
    private String to_uname;
    private String comment_time;
    private int comment_number;
    private int praise_number;

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final int getTopic_id() {
        return topic_id;
    }

    public final void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public final String getTopic_type() {
        return topic_type;
    }

    public final void setTopic_type(String topic_type) {
        this.topic_type = topic_type;
    }

    public final String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content = content;
    }

    public final int getFrom_uid() {
        return from_uid;
    }

    public final void setFrom_uid(int from_uid) {
        this.from_uid = from_uid;
    }

    public final String getFrom_uname() {
        return from_uname;
    }

    public final void setFrom_uname(String from_uname) {
        this.from_uname = from_uname;
    }

    public final int getTo_uid() {
        return to_uid;
    }

    public final void setTo_uid(int to_uid) {
        this.to_uid = to_uid;
    }

    public final String getTo_uname() {
        return to_uname;
    }

    public final void setTo_uname(String to_uname) {
        this.to_uname = to_uname;
    }

    public final String getComment_time() {
        return comment_time;
    }

    public final void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public final int getComment_number() {
        return comment_number;
    }

    public final void setComment_number(int comment_number) {
        this.comment_number = comment_number;
    }

    public final int getPraise_number() {
        return praise_number;
    }

    public final void setPraise_number(int praise_number) {
        this.praise_number = praise_number;
    }
}
