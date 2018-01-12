package com.pl.hengda.app.service.impl;


import com.pl.hengda.app.mapper.UserDao;
import com.pl.hengda.app.model.Consultant;
import com.pl.hengda.app.model.Huashu;
import com.pl.hengda.app.model.User;
import com.pl.hengda.app.service.UserService;
import com.pl.hengda.app.utils.UtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;
    @Override
    public List<User> getUserList() {
        return userdao.getUserList();
    }

    @Override
    public List<User> getUserByName(String name) {
        String pinyin = UtilHelper.getShortPinyin(name);
        return userdao.getUserByPinyin(pinyin);
    }

    @Override
    public void addUser(User user) {
        userdao.addUser(user);
    }

    @Override
    public User getUserById(String userid) {
        return userdao.getUserById(userid);
    }
    @Override
    public void updateUserById(User user){
        userdao.updateUserById(user);
    }

    @Override
    public Huashu getHuashuByVipflag(String vip_flag) {
        return userdao.getHuashuByVipflag(vip_flag);
    }

    @Override
    public List<Consultant> getConsultant() {
        return userdao.getConsultant();
    }
}
