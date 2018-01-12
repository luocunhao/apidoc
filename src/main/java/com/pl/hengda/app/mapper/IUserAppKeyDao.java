package com.pl.hengda.app.mapper;

import org.apache.ibatis.annotations.Param;

public interface IUserAppKeyDao {

    /**
     * 查询appkey 是否存在。
     * @param appKey
     * @return
     */
    Boolean isAppKeyExist(@Param("appkey") String appKey);
}
