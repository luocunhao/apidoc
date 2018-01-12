package com.pl.hengda.app.controller;

import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pl.hengda.app.model.ReturnMsg;
import com.pl.hengda.app.service.HendaUserService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hengda")
public class SmsControl {
    @Autowired
    @Qualifier("smsClient")
    private SMSClient smsClient;
    @Autowired
    private HendaUserService hendaUserService;
    @Value("${jiguang.templateId}")
    private  int templateId;
    /**
     *
     * @api {POST}  /hengda/sendMessage 短信发送接口
     * @apiGroup SmsController
     * @apiName 发送短信
     * @apiParam {String} userid 来访者id
     * @apiParam {String} name  来访者姓名
     * @apiParamExample {json} 请求参数示例
     * {
     *      "name":"张三",
     *     “userid”:"13215"
     * }
     * @apiSuccess {String}  ReturnMsg  0：发送成功 1：发送失败
     * @apiSuccessExample {json}  成功返回示例
     * {
     *     "code":"0",
     *     "content":"发送成功"
     * }
     * @apiVersion 0.0.0
     */
    @RequestMapping(value = "sendMessage",method = RequestMethod.POST)
    public String sendMessage(@RequestBody String msgBody){
        ReturnMsg retMsg = new ReturnMsg();
      //  String visitorPhone = JSONObject.parseObject(msgBody).getString("visitorPhone");
        String name = JSONObject.parseObject(msgBody).getString("name");
        String userid = JSONObject.parseObject(msgBody).getString("userid");
        //根据Id查对应的置业顾问电话
        String sendphone = hendaUserService.getConsultanPhoneByUserid(userid);
        Map<String,String> map = new HashMap<String,String>();
        map.put("name",name);
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(sendphone)
                .setTempId(templateId)
                .setTempPara(map)
                .build();
        try {
            SendSMSResult res = smsClient.sendTemplateSMS(payload);
            retMsg.setCode("0");
            retMsg.setContent("发送成功");
        } catch (Exception e) {
            retMsg.setCode("1");
            retMsg.setContent("发送失败");
            return JSON.toJSONString(retMsg);
        }
        return JSON.toJSONString(retMsg);
    }

}
