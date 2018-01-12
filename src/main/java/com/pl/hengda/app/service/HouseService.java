package com.pl.hengda.app.service;

import com.pl.hengda.app.model.House;

import java.util.List;

public interface HouseService {
    /**
     * @Param name 户型名字
     * @return
     */
    public House getHouseByName(String housename);
    public List<House> getAllHouse();
}
