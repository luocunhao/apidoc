package com.pl.hengda.app.service;



import com.pl.hengda.app.model.SemanticSlots;

import java.util.List;

public interface ISemanticSlotsService {
    /**
     * 获取查询语义槽
     * @param serviceName 语意服务类型
     * @param intent 语意意图
     * @return
     */
    List<SemanticSlots> getSemSlots(String apiKey, String serviceName, String intent);

    /**
     * 判断语意模板是否存在
     * @param serviceName
     * @param intent
     * @return
     */
    Boolean isSemanticModelExist(String apiKey, String serviceName, String intent);


    List<SemanticSlots> getSemanticSlots();
}
