package com.pl.hengda.app.service.impl;


import com.pl.hengda.app.mapper.ISemanticSlotsDao;
import com.pl.hengda.app.model.SemanticSlots;
import com.pl.hengda.app.service.ISemanticSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemanticSlotServiceImpl implements ISemanticSlotsService {

    @Autowired
    private ISemanticSlotsDao iSemanticSlotsDao;

    @Override
    public List<SemanticSlots> getSemSlots(String apiKey, String serviceName, String intent) {
        return iSemanticSlotsDao.getSemanticSlotsByKey(apiKey,serviceName,intent);
    }

    @Override
    public Boolean isSemanticModelExist(String apiKey, String serviceName, String intent) {
        return iSemanticSlotsDao.isSemanticModelExist(apiKey,serviceName,intent);
    }

    @Override
    public List<SemanticSlots> getSemanticSlots() {
        return iSemanticSlotsDao.getSemanticSlots();
    }
}
