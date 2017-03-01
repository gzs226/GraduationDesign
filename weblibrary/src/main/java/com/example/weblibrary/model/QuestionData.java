package com.example.weblibrary.model;

import java.util.List;

/**
 * Created by zy on 2017/2/16.
 */

public class QuestionData {

    private int number;
    private List<resultquestiondata> resultquestiondata;

    public List<com.example.weblibrary.model.resultquestiondata> getResultquestiondata() {
        return resultquestiondata;
    }

    public void setResultquestiondata(List<com.example.weblibrary.model.resultquestiondata> resultquestiondata) {
        this.resultquestiondata = resultquestiondata;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
