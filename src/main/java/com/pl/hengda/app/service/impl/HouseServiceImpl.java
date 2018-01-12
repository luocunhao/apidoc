package com.pl.hengda.app.service.impl;

import com.pl.hengda.app.mapper.HouseDao;
import com.pl.hengda.app.model.House;
import com.pl.hengda.app.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseDao houseDao;
    @Override
    public House getHouseByName(String housename) {
        return houseDao.getHouseByName(housename);
    }

    @Override
    public List<House> getAllHouse() {
        return houseDao.getAllHouse();
    }
}
