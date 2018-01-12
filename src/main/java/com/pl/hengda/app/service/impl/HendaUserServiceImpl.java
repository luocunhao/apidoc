package com.pl.hengda.app.service.impl;

import com.pl.hengda.app.mapper.HengdaUserDao;
import com.pl.hengda.app.model.HengdaUser;
import com.pl.hengda.app.service.HendaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HendaUserServiceImpl implements HendaUserService{
    @Autowired
    private HengdaUserDao hengdaUserDao;
    @Override
    public HengdaUser getUserById(String userid) {
        return hengdaUserDao.getUserById(userid);
    }

    @Override
    public void updateUser(HengdaUser hengdaUser) {
        hengdaUserDao.updateUser(hengdaUser);
    }

    @Override
    public HengdaUser getUserByPhone(String phone) {
        return hengdaUserDao.getUserByPhone(phone);
    }

    @Override
    public void addUser(HengdaUser hengdaUser) {
        hengdaUserDao.addUser(hengdaUser);
    }

    @Override
    public void setConidByUserid(String userid, String conid) {
        hengdaUserDao.setConidByUserid(userid,conid);
    }

    @Override
    public String getConsultanPhoneByUserid(String userid) {
        return hengdaUserDao.getConsultanPhoneByUserid(userid);
    }
}
