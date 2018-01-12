package com.pl.hengda.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.pl.hengda.app.service.IPlaiserver;
import com.pl.hengda.app.utils.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/hengda")
public class HengDaControl {

    private static final Logger logger = LoggerFactory.getLogger(HengDaControl.class);

    @Autowired
    private IPlaiserver iPlaiserver;

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/textapp")
    public JSONObject appRequest(@RequestBody JSONObject msgBody, HttpServletRequest request){
        logger.info("msgBody:"+msgBody.toJSONString());
        String concatKey = msgBody.getString("userid");
        JSONObject result =new JSONObject();
        try {
            String content =msgBody.getString("content");
            String type = msgBody.getString("type");
            if (StringUtils.isEmpty(content)){
                result.put("resp","不好意思，我没听清请再讲一次好吗？");
                result.put("type","error");
                return result;
            }
            switch (type){
                case "openqa":
                    JSONObject aiResult = iPlaiserver.robotForHengda(content,result);
                    String airesult = aiResult.getString("error");
                    if (StringUtils.isEmpty(aiResult)){
                        result.put("userid",concatKey);
                        result.put("resp",airesult);
                        result.put("type","text");
                    }else {
                        result.put("userid",concatKey);
                        result.put("resp","不好意思，你问的问题太深奥了，等我知道了再告诉你吧！");
                        result.put("type","error");
                    }
                    break;
                case "introduce":
                    int function =msgBody.getIntValue("function");
                    switch (function){
                        case 1:
                            result.put("resp","沙盘介绍录音资源");
                            result.put("type","voice");
                            break;
                        case 2:
                            result.put("resp","交通介绍录音资源");
                            result.put("type","voice");
                            break;
                        case 3:
                            result.put("resp","配套介绍录音资源");
                            result.put("type","voice");
                            break;
                        case 4:
                            result.put("resp","楼盘介绍录音资源");
                            result.put("type","voice");
                            break;
                        case 5:
                            result.put("resp","户型介绍录音资源");
                            result.put("type","voice");
                            break;
                        default:
                            result.put("resp","不好意思，我没听清楚，请再讲一次好吗？");
                            result.put("type","voice");
                            break;
                    }
                    break;
                case "talkbusiness":
                    int rules =msgBody.getIntValue("function");
                    switch (rules){
                        case 1:
                            result.put("resp","销售政策资源");
                            result.put("type","voice");
                            break;
                        case 2:
                            result.put("resp","房型推荐资源");
                            result.put("type","voice");
                            break;
                        default:
                            result.put("resp","不好意思，我没有听清，请再讲一遍好吗？");
                            result.put("type","voice");
                            break;
                    }

            }
            String cunContent ="Q:"+content+"A:"+result.getString("resp");
            redisClient.lpush(concatKey,cunContent);
        }catch (Exception e){
            result.put("resp","不好意思，我没听清请再讲一次好吗？");
            result.put("type","error");
        }
        return result;
    }

    @RequestMapping("nihao")
    public String test(){

        return "Hello World!";
    }
}
