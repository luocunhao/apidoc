package com.pl.hengda.app.mapper;

import com.pl.hengda.app.model.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseDao {
    /**
     * @Param name 户型名字
     * @return
     */
    public House getHouseByName(@Param("housename") String housename);
    public List<House> getAllHouse();
}
