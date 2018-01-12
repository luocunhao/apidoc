package com.pl.hengda.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pl.hengda.app.model.House;
import com.pl.hengda.app.model.ReturnMsg;
import com.pl.hengda.app.service.HouseService;
import com.pl.hengda.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hengda")
public class ConsultantControl {

    @Autowired
    private UserService userService;
    @Autowired
    private HouseService houseService;
    /**
     *
     * @api {GET}  /hengda/getConsltants 获取置业顾问
     * @apiName getConsltants
     * @apiGroup ConsultantControl
     * @apiSuccess {String}  ReturnMsg  0：发送成功 1：发送失败
     * @apiSuccessExample {json}  成功返回示例
     * {
     *     "code":"0",
     *     "content":
     *     [
     *     {
     *          {
    *               "id": 1,
    *                "name": "王小丽",
    *                 "sex": "女",
    *               "age": 28,
    *               "image": "http://pulanbd.iok.la:8800/resource/a.jpg",
    *               "title": "高级销售顾问",
    *               "phone": "123456",
    *               " desc": "8年当地产销售，经验丰富，始终如一的工作态度。"
    *           }
     *     }
     *     ],
     *     "errorMsg":null
     * }
     * @apiVersion 0.0.0
     */
    @RequestMapping(value = "/getConsltants",method = RequestMethod.GET)
    public ReturnMsg getConsltants(){
        ReturnMsg rem = new ReturnMsg();
        JSONObject result =new JSONObject();
        rem.setCode("0");
        rem.setContent(userService.getConsultant());
        return rem;
    }
    /**
     *
     * @api {GET}  /hengda/getChannel 获取顾客渠道
     *  @apiName getChannel
     * @apiGroup ConsultantControl
     * @apiSuccess {String}  ReturnMsg  0：发送成功 1：发送失败
     * @apiSuccessExample {json}  成功返回示例
     * {
     *     "code":"0",
     *     "content":
     *     [
     *
     *          {
     *             'id':1,'channel':'渠道A'
     *           }
     *
     *     ],
     *     "errorMsg":null
     * }
     * @apiVersion 0.0.0
     */
    @RequestMapping(value = "/getChannel",method = RequestMethod.GET)
    public ReturnMsg getChannel(){
        ReturnMsg rem = new ReturnMsg();
        rem.setCode("0");
        rem.setContent("{'channel':[{'id':1,'channel':'渠道A'},{'id':2,'channel':'渠道B'},{'id':3,'channel':'渠道C'}]}");
        return rem;
    }
    /**
     *
     * @api {GET}  /hengda/getrecLayout 推荐房型
     *  @apiName getrecLayout
     * @apiGroup ConsultantControl
     * @apiSuccess {String}  ReturnMsg  0：发送成功 1：发送失败
     * @apiSuccessExample {json}  成功返回示例
     * {
     *     "code":"0",
     *     "content":
     *
     *
     *          {
    *    "id": "1",
    *    "housename": "F户型",
    *    "housedirect": "南北朝向",
    *    "apartmentlayout": "3室2厅2卫",
    *    "measure": "建面118㎡",
    *    "referenceprice": "均价 600万/套",
    *    "floor": "28层",
    *   "roomnumber": "2809",
    *    "desc": "餐厅与客厅相连，室内视野更开阔;格局方正，方便家具摆放;主卧远离活动区，保证休息时不被打扰。",
    *    "housemappath": "http://pulanbd.iok.la:8800/resource/housing01.png",
    *    "discount": "享开盘8.8折 再送价值4万的家私"
     *           },
     *
     *
     *     "errorMsg":null
     * }
     *   @apiParam {String} userid  用户id
     *   @apiParamExample {String} 请求参数示例
     *   userid = 2131
     * @apiVersion 0.0.0
     */
    @RequestMapping(value = "/getrecLayout",method = RequestMethod.GET)
    public ReturnMsg recLayout(String userid){
        ReturnMsg rem = new ReturnMsg();
        House house = houseService.getHouseByName("F户型");
        if(house!=null) {
            rem.setCode("0");
            rem.setContent(house);
        }else{
            rem.setCode("1");
            rem.setContent("没有查到该户型");
        }
        return rem;
    }
    /**
     *
     * @api {GET}  /hengda/getOtherLayout 推荐其他房型
     *  @apiName getOtherLayout
     * @apiGroup ConsultantControl
     * @apiSuccess {String}  ReturnMsg  0：成功 1：失败
     * @apiSuccessExample {json}  成功返回示例
     * {
     *     "code":"0",
     *     "content":
     *
     *[
     *          {
     *    "id": "1",
     *    "housename": "F户型",
     *    "housedirect": "南北朝向",
     *    "apartmentlayout": "3室2厅2卫",
     *    "measure": "建面118㎡",
     *    "referenceprice": "均价 600万/套",
     *    "floor": "28层",
     *   "roomnumber": "2809",
     *    "desc": "餐厅与客厅相连，室内视野更开阔;格局方正，方便家具摆放;主卧远离活动区，保证休息时不被打扰。",
     *    "housemappath": "http://pulanbd.iok.la:8800/resource/housing01.png",
     *    "discount": "享开盘8.8折 再送价值4万的家私"
     *           }
     *],
     *
     *     "errorMsg":null
     * }
     *   @apiParam {String} userid  用户id
     *   @apiParamExample {String} 请求参数示例
     *   userid = 2131
     * @apiVersion 0.0.0
     */
    @RequestMapping(value = "/getOtherLayout",method = RequestMethod.GET)
    public ReturnMsg recOtherLayout(String userid){
        ReturnMsg rem = new ReturnMsg();
        List<House> houses = houseService.getAllHouse();
        if(houses.size()>0) {
            rem.setCode("0");
            rem.setContent(houses);
        }else{
            rem.setCode("1");
            rem.setContent("没有查到户型");
        }
        return rem;
    }

}
