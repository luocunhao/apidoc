package com.pl.hengda.app.mapper;

import com.pl.hengda.app.model.HengdaUser;
import org.apache.ibatis.annotations.Param;

public interface HengdaUserDao {
    public HengdaUser getUserById(String userid);
    public void updateUser(HengdaUser hengdaUser);
    public HengdaUser getUserByPhone(String phone);
    public void addUser(HengdaUser hengdaUser);
    public void setConidByUserid(@Param("userid") String userid, @Param("conid") String conid);
    public String getConsultanPhoneByUserid(String userid);
}
