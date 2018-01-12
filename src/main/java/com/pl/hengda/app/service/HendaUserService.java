package com.pl.hengda.app.service;

import com.pl.hengda.app.model.HengdaUser;

public interface HendaUserService {
    public HengdaUser getUserById(String userid);
    public void updateUser(HengdaUser hengdaUser);
    public HengdaUser getUserByPhone(String phone);
    public void addUser(HengdaUser hengdaUser);
    public void setConidByUserid(String userid,String conid);
    public String getConsultanPhoneByUserid(String userid);
}
