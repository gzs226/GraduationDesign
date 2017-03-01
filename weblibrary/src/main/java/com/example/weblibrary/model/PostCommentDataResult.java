package com.example.weblibrary.model;

import java.util.List;

public class PostCommentDataResult {
    private int number;
    private List<PostCommentData> PostCommentData;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<com.example.weblibrary.model.PostCommentData> getPostCommentData() {
        return PostCommentData;
    }

    public void setPostCommentData(List<com.example.weblibrary.model.PostCommentData> postCommentData) {
        PostCommentData = postCommentData;
    }
}
