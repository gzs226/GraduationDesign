package com.example.weblibrary.model;

/**
 * Created by gg on 2017/2/14.
 */

public class PostCommentData {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_type() {
        return topic_type;
    }

    public void setTopic_type(String topic_type) {
        this.topic_type = topic_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(int from_uid) {
        this.from_uid = from_uid;
    }

    public String getFrom_uname() {
        return from_uname;
    }

    public void setFrom_uname(String from_uname) {
        this.from_uname = from_uname;
    }

    public int getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(int to_uid) {
        this.to_uid = to_uid;
    }

    public String getTo_uname() {
        return to_uname;
    }

    public void setTo_uname(String to_uname) {
        this.to_uname = to_uname;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public int getComment_number() {
        return comment_number;
    }

    public void setComment_number(int comment_number) {
        this.comment_number = comment_number;
    }

    public int getPraise_number() {
        return praise_number;
    }

    public void setPraise_number(int praise_number) {
        this.praise_number = praise_number;
    }
}
