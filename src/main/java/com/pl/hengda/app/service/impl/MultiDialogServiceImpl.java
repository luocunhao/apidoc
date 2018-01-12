package com.pl.hengda.app.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pl.hengda.app.model.ReturnMsg;
import com.pl.hengda.app.model.SemanticSlots;
import com.pl.hengda.app.service.IMultiDialogService;
import com.pl.hengda.app.service.IPlaiserver;
import com.pl.hengda.app.service.ISemanticSlotsService;
import com.pl.hengda.app.utils.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MultiDialogServiceImpl implements IMultiDialogService {
    private Logger logger = LoggerFactory.getLogger(MultiDialogServiceImpl.class);

    @Autowired
    private RedisClient redisClient; //Redis

    @Autowired
    private ISemanticSlotsService iSemanticSlotsService; //slots

    @Autowired
    private IPlaiserver iPlaiserver; // pulan ai for hengda

    /**
     * App 多轮会话逻辑控制
     * @param result 返回的结果
     * @return 返回值
     */
    @Override
    public ReturnMsg appMultiDialog(String content, String myAppkey, String userid, int tag ,ReturnMsg returnMsg ,JSONObject result) {
        String converKey = myAppkey + ":" + userid;
        logger.info("Content:"+content);
        logger.info("Now User ModelKey："+converKey);
        try {
            // 判断当前用户是否已经存在对话语义问答模板对象。
            String converModel = redisClient.get(converKey); //当前用户存储的会话模板。
            if (StringUtils.isEmpty(converModel)) {
                List<SemanticSlots> semsot = iSemanticSlotsService.getSemanticSlots();
                for (int i = 0; i < semsot.size(); i++) {
                    SemanticSlots slotObj = semsot.get(i);
                    String fsolt = slotObj.getSlotValue();
                    if (StringUtils.isEmpty(fsolt)) {
                        result.put("rc", 0);
                        result.put("resp", slotObj.getTemplateIntent());
                        result.put("type", slotObj.getTemplateService());
                        result.put("tips", slotObj.getUtterance());
                        returnMsg.setCode("0");
                        returnMsg.setContent(result.toJSONString());
                        slotObj.setSlotValue("done");
                        semsot.add(i, slotObj);
                        semsot.remove(i + 1);
                        redisClient.saveSemanticModel(converKey, semsot);
                        break;
                    }
                }
            } else {
                switch (tag) { //根据标识判断是否继续走语义模板，（中间穿插的问答都会被记录下来）
                    case 0:
                        JSONObject aiResult = iPlaiserver.robotForHengda(content, result);
                        logger.info("aiResult:" + aiResult.toJSONString());
                        if (StringUtils.isEmpty(aiResult.getString("error"))) {
                            result.put("rc", 0);
                            result.put("resp", aiResult.getString("resp"));
                            result.put("type", "text");
                            returnMsg.setCode("0");
                            returnMsg.setContent(result.toJSONString());
                        } else {
                            result.put("rc", -1);
                            result.put("resp", "不好意思，我没听清请再讲一次好吗？");
                            result.put("type", "error");
                            returnMsg.setCode("1");
                            returnMsg.setContent(result.toJSONString());
                            returnMsg.setErrorMsg("aiserver is error");
                        }
                        redisClient.lpush(converKey, "Q:" + content + "A:" + result.getString("resp"));
                        break;
                    case 1:
                        List<SemanticSlots> secondSSot = JSON.parseArray(converModel, SemanticSlots.class);
                        boolean isNull =false;
                        for (int i = 0; i < secondSSot.size(); i++) {
                            SemanticSlots smst = secondSSot.get(i);
                            String slot_value = smst.getSlotValue();
                            //循环遍历模板将第一个为空的语意槽的问题返回。
                            if (StringUtils.isEmpty(slot_value)) {
                                result.put("rc", 0);
                                result.put("resp", smst.getTemplateIntent());
                                result.put("type", smst.getTemplateService());
                                result.put("tips",smst.getUtterance());
                                returnMsg.setCode("0");
                                returnMsg.setContent(result.toJSONString());
                                smst.setSlotValue("done");
                                secondSSot.add(i, smst);
                                secondSSot.remove(i + 1);
                                redisClient.saveSemanticModel(converKey, secondSSot);
                                isNull =true;
                                break;
                            }
                        }
                        if (!isNull){
                            result.put("rc", 1);
                            result.put("resp", "done");
                            result.put("type", "done");
                            result.put("tips","讲解完成！");
                            returnMsg.setCode("0");
                            returnMsg.setContent(result.toJSONString());
                            redisClient.del(converKey);
                        }
                        break;
                    default:
                        result.put("rc", -1);
                        result.put("resp", "不好意思，我没听清请再讲一次好吗？");
                        result.put("type", "error");
                        returnMsg.setCode("1");
                        returnMsg.setContent(result.toJSONString());
                        returnMsg.setErrorMsg("tag is not find");
                        break;
                }
            }
        }catch (Exception e){
            logger.error("AppMultiDialog Exception:"+e.getMessage());
            result.put("rc", -1);
            result.put("resp", "不好意思，我没听清请再讲一次好吗？");
            result.put("type", "error");
            returnMsg.setCode("1");
            returnMsg.setContent(result.toJSONString());
            returnMsg.setErrorMsg("HengDa appMultiDialog.class is Error");
            try {
                redisClient.del(converKey);
            }catch (Exception e1){

                e1.printStackTrace();
            }
        }
        return returnMsg;
    }

}
