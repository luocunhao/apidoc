package com.pl.hengda.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.pl.hengda.app.model.ReturnMsg;
import com.pl.hengda.app.service.IMultiDialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hengda")
public class HengDaDialog {

    @Autowired
    private IMultiDialogService iMultiDialogService;

    /**
     * @apiDescription 第一次请求会创建多轮对话返回第一个介绍内容,后面的请求当tag =0 人机问答, tag =1 继续下一个模块讲解。
     * @api {POST}  /hengda/hddialog 多轮对话+问答
     * @apiGroup HengDaDialog
     * @apiName hddialog
     * @apiSuccess {String}  ReturnMsg  0：发送成功 1：发送失败
     * @apiSuccessExample {json}  成功返回示例 tag ="" 第一次访问接口
     * {
     *     "code":"0",
     *     "content":
     *          "{"rc":0,"resp":"shapan","error":"","type":"hengda_ai_shapan","tips":"沙盘介绍"}",
     *     "errorMsg":null
     * }
     * @apiSuccessExample {json}  成功返回示例 tag ="0" 第二次访问接口
     * {
     *      "code": "0",
     *      "content": "{"rc":0,"resp":"公司占地面积20万平方米，建筑面积15万平方米","error":"","type":"text"}",
     *      "errorMsg": null
     *  }
     *  @apiSuccessExample {json}  成功返回示例 tag ="1" 第三次访问接口
     * {
     *      "code": "0",
     *      "content": "{"rc":0,"resp":"traffic","error":"","type":"hengda_ai_shapan","tips":"交通介绍"}",
     *      "errorMsg": null
     *  }
     *
     *   @apiParamExample {String} 请求参数示例
     *   {
     *       "userid":"789456",
     *       "content":"小区的绿化面积",
     *       "tag":"0"
     *   }
     * @apiVersion 0.0.0
     */
    @RequestMapping(value = "/hddialog",method = RequestMethod.POST)
    public ReturnMsg hengDaApp(@RequestBody JSONObject msgBody){
        JSONObject result =new JSONObject();
        ReturnMsg returnMsg =new ReturnMsg();
        result.put("resp","");
        result.put("error","");
        String myAppkey = "hengdatest";
        String userid = msgBody.getString("userid");
        String content = msgBody.getString("content");
        int tag = msgBody.getIntValue("tag");
        return iMultiDialogService.appMultiDialog(content,myAppkey,userid,tag,returnMsg,result);
    }
}
