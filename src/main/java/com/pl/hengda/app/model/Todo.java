package com.pl.hengda.app.model;

public class Todo {
    private String userid;
    private String name;
    private String todotask;
    private String date;
    public Todo(){}
    public Todo(String userid, String name, String todotask,String date) {
        this.userid = userid;
        this.name = name;
        this.todotask = todotask;
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getTodotask() {
        return todotask;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTodotask(String todotask) {
        this.todotask = todotask;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", todotask='" + todotask + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
