package com.example.weblibrary.model;

import java.util.List;

/**
 * Created by gg on 2017/2/17.
 */

public class resultVersionData {
    private int number;
    private List<versiondata> versiondata;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<com.example.weblibrary.model.versiondata> getVersiondata() {
        return versiondata;
    }

    public void setVersiondata(List<com.example.weblibrary.model.versiondata> versiondata) {
        this.versiondata = versiondata;
    }
}
