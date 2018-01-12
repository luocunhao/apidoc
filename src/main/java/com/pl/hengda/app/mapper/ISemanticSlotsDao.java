package com.pl.hengda.app.mapper;


import com.pl.hengda.app.model.SemanticSlots;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISemanticSlotsDao {

    /**
     *  appid +Service+ intent查询语义槽
     * @param apiKey
     * @param servername
     * @param intent
     * @return
     */
    List<SemanticSlots> getSemanticSlotsByKey(@Param("appid") String apiKey, @Param("servername") String servername, @Param("intent") String intent);

    /**
     * appid +Service+intent查询语意模板是否存在
     * @param apiKey
     * @param servername
     * @param intent
     * @return
     */
    Boolean isSemanticModelExist(@Param("appid") String apiKey, @Param("servername") String servername, @Param("intent") String intent);

    /**
     * 查询所有消息模板。
     * @return
     */
    List<SemanticSlots> getSemanticSlots();

}
