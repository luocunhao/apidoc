package com.pl.hengda.app.mapper;


import com.pl.hengda.app.model.Consultant;
import com.pl.hengda.app.model.Huashu;
import com.pl.hengda.app.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
     List<User> getUserList();
     List<User> getUserByPinyin(@Param("pinyinname") String pinyinname);
     void addUser(User user);
     User getUserById(String userid);
     void updateUserById(User user);
     Huashu getHuashuByVipflag(String vip_flag);

     List<Consultant> getConsultant();

}
