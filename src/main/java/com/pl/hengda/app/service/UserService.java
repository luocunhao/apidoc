package com.pl.hengda.app.service;



import com.pl.hengda.app.model.Consultant;
import com.pl.hengda.app.model.Huashu;
import com.pl.hengda.app.model.User;

import java.util.List;

public interface UserService {
    public List<User> getUserList();
    public void updateUserById(User user);
    public List<User> getUserByName(String name);
    public void addUser(User user);
    public User getUserById(String userid);
    public Huashu getHuashuByVipflag(String vip_flag);
    List<Consultant> getConsultant();
}
