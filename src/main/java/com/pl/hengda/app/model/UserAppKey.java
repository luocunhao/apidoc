package com.pl.hengda.app.model;

import java.io.Serializable;

public class UserAppKey implements Serializable{

    private String appKey;

    public UserAppKey() {

    }

    public UserAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public String toString() {
        return "UserAppKey{" +
                "appKey='" + appKey + '\'' +
                '}';
    }
}
