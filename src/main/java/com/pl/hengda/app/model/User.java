package com.pl.hengda.app.model;

import java.util.Calendar;

public class User {
    private String userid;
    private  String name;
    private String sex;
    private String phone;
    private int age;
    private String position;
    private String department;
    private String nickname;
    private String vip_flag;
    private String pinyin;
    private String idcard;
    private String reason;
    private String company;
    private String when;
    //0是老客户 1是新客户
    private String isoldCus;
    private String channel;
    public User(){}
    public User(String userid, String name, String sex, String phone, int age, String position, String department,String nickname,String vip_flag,String pinyin,String idcard,String reason,String company,String channel) {
        this.userid = userid;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.age = age;
        this.position = position;
        this.department = department;
        this.nickname = nickname;
        this.vip_flag = vip_flag;
        this.pinyin = pinyin;
        this.idcard = idcard;
        this.reason = reason;
        this.company = company;
        this.channel = channel;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setVip_flag(String vip_flag) {
        this.vip_flag = vip_flag;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public String getNickname() {
        return nickname;
    }
    public String getVip_flag() {
        return vip_flag;
    }

    public String getIsoldCus() {
        return isoldCus;
    }

    public void setIsoldCus(String isoldCus) {
        this.isoldCus = isoldCus;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getWhen() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if(hour==8){
            this.when = "0";
        }else {
            this.when = "1";
        }
        return when;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", nickname='" + nickname + '\'' +
                ", vip_flag='" + vip_flag + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", idcard='" + idcard + '\'' +
                ", reason='" + reason + '\'' +
                ", company='" + company + '\'' +
                ", when='" + when + '\'' +
                ", isoldCus='" + isoldCus + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}
