package com.pl.hengda.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pl.hengda.app.service.IPlaiserver;
import com.pl.hengda.app.webapi.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PlaiServerImpl implements IPlaiserver {

    private static final String url ="http://pulanbd.iok.la:8800/hengda_ai";
    private static final Logger logger = LoggerFactory.getLogger(PlaiServerImpl.class);
    @Autowired
    private WebRequest webRequest;

    /**
     * 电话虚拟机器人回答/IM机器人
     * @param content
     * @param result
     * @return
     */
    @Override
    public JSONObject robotForHengda(String content, JSONObject result) {
        JSONObject msgBody =new JSONObject();
        msgBody.put("content",content);
        msgBody.put("type","ask");
        msgBody.put("error","");
        String rest =webRequest.getHengDaAiRequest(url,msgBody.toJSONString());
        logger.info("HengDaQA："+rest);
        if (StringUtils.isEmpty(rest)){
            result.put("error","error");
        }else {
            JSONObject aiObj = JSON.parseObject(rest);
            result.put("resp",aiObj.getString("answer"));
            result.put("error","");
        }
        return result;
    }

    /**
     * 电话/IM 回访问答
     * @param content
     * @param result
     * @return
     */
    @Override
    public JSONObject roboyForHengdaCallBack(String content, JSONObject result) {
        String rest =webRequest.getAiSlotServerRequest(url,content,"hdckqa");
        if (StringUtils.isEmpty(rest)){
            return null;
        }else {
            return JSON.parseObject(rest);
        }
    }

    /**
     * 恒大信息介绍。
     * @param content
     * @param result
     * @return
     */
    @Override
    public JSONObject introduceHengda(String content, JSONObject result) {
        String rest =webRequest.getAiSlotServerRequest(url,content,"hdintdqa");
        if (StringUtils.isEmpty(rest)){
            result.put("resp","");
            return result;
        }else {
            return JSON.parseObject(rest);
        }
    }
}
