package com.example.weblibrary.model;

import java.util.List;

/**
 * Created by gg on 2017/2/12.
 */

public class PostDataResult {
    private int state;
    private List<Postresuldata> resultdata;
    public List<Postresuldata> getResultdata() {
        return resultdata;
    }

    public void setResultdata(List<Postresuldata> Postresuldata) {
        this.resultdata = Postresuldata;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }



}
