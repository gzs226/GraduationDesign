package com.example.weblibrary.model;

import java.util.List;

/**
 * Created by gg on 2017/2/19.
 */

public class LoginResultData {
    private int number;
    private List<loginresult> loginresult;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<com.example.weblibrary.model.loginresult> getLoginresult() {
        return loginresult;
    }

    public void setLoginresult(List<com.example.weblibrary.model.loginresult> loginresult) {
        this.loginresult = loginresult;
    }
}
