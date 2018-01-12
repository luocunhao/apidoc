package com.pl.hengda.app.controller;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.pl.hengda.app.model.HengdaUser;
import com.pl.hengda.app.model.ReturnMsg;
import com.pl.hengda.app.model.User;
import com.pl.hengda.app.service.HendaUserService;
import com.pl.hengda.app.service.IFaceService;
import com.pl.hengda.app.utils.UtilHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("hengda")
public class FaceController {
    private final static Logger logger = LoggerFactory.getLogger(FaceController.class);
    @Autowired
    private HendaUserService hendaUserService;
    @Autowired
    private IFaceService iFaceService;
    @Autowired
    @Qualifier("faceClient")
    private AipFace client;
    @Value("${baidu.facegroup}")
    private  String group;
    /**
     * @apiIgnore 接口弃用
     * @apiDescription 人脸识别接口 对识别分数小于80的暂且认为为新客户  注册接口会通过手机号姓名再次校验 参数为人脸图像的base64去除头部
     * @api {POST}  /hengda/faceRec 人脸识别
     * @apiGroup facecontroller
     * @apiName faceRec
     * @apiSuccess {String}  code  0:识别成功 1：识别失败,重新拍摄调接口
     * @apiSuccess {String}  content 客户Id
     * @apiSuccessExample {json}  成功返回示例
     * {
     *     "code":"0",
     *     "content":"skfg545hd5j5d4j5"
     * }
     * @apiParamExample {String} 请求参数示例
     *   {
     *     "image":""
     *   }
     * @apiVersion 0.0.0
     */
    @RequestMapping(value = "/faceRec",method = RequestMethod.POST)
    public ReturnMsg facerecongnice(@RequestBody String msgbody){
        UUID uuid = UUID.randomUUID();
        String uid;
        ReturnMsg ret = new ReturnMsg();
        try {
            JSONObject msgData = new JSONObject(msgbody);
            String image = msgData.getString("image");
            byte[] files = UtilHelper.base64String2ByteFun(image);
            JSONObject result = iFaceService.faceRecognize(client, files, group);
            logger.info("人脸识别结果：" + result);
            if (result.length() == 0 || result.has("error_code")) {
                ret.setCode("1");
                ret.setContent("识别出错,请重新拍摄");
            }else{
                JSONObject jo = result.getJSONArray("result").getJSONObject(0);
                Double score = jo.getJSONArray("scores").getDouble(0);
                logger.info("识别得分：" + score);
                if(score < 80){
                    //匹配度低于80 暂且认为为新客户  注册接口会通过手机号姓名再次校验
                    uid = uuid.toString().replace("-", "");
                }else{
                    uid = jo.getString("uid");
                }
                ret.setCode("0");
                ret.setContent(uid);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * @apiDescription 用户注册接口  image为人脸图像的base64去除头部
     * @api {POST}  /hengda/userRegister 用户注册
     * @apiGroup facecontroller
     * @apiName userRegister
     * @apiSuccess {String}  code  0请求成功 1：请求失败
     * @apiSuccess (content) {String}  isOldCus  0:老客户 1:新客户
     * @apiSuccess (content) {String}  userid  客户id
     * @apiSuccessExample {json}  成功返回示例
     * {
     *     "code":"0",
     *     "content":
     *     {
     *         "isOldCus";"0",
     *         "userid":"bbf3f55432ac43e589cde6b1f0022714"
     *     }
     *
     * }
     * @apiParam {String} image 图像base64 去除头部
     * @apiParam {String} username 客户姓名
     * @apiParam {String} phone 客户手机号
     * @apiParam {String} channel 渠道
     * @apiParamExample {String} 请求参数示例
     *   {
     *     "image":"",
     *     "username":"罗",
     *     "phone":"18676762954",
     *     "channel":"渠道A",
     *   }
     * @apiVersion 0.0.0
     */
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public ReturnMsg userRegister(@RequestBody String msgbody){
        ReturnMsg ret = new ReturnMsg();
        UUID uuid = UUID.randomUUID();
        String uid = null;
        try {
            JSONObject msgData = new JSONObject(msgbody);
            String username = msgData.getString("username");
            String phone = msgData.getString("phone");
            String channel = msgData.getString("channel");
            String image = msgData.getString("image");
            byte[] files = UtilHelper.base64String2ByteFun(image);
            JSONObject result = iFaceService.faceRecognize(client, files, group);
            logger.info("人脸识别结果：" + result);
            if (result.length() == 0 || result.has("error_code")) {
                ret.setCode("1");
                ret.setContent("识别人脸出错,请重新拍摄");
            }else{
                JSONObject jo = result.getJSONArray("result").getJSONObject(0);
                Double score = jo.getJSONArray("scores").getDouble(0);
                logger.info("识别得分：" + score);
                if(score < 80){
                    //匹配度低于80 暂且认为为新客户  注册接口会通过手机号姓名再次校验
                    uid = uuid.toString().replace("-", "");
                }else{
                    uid = jo.getString("uid");
                }
            }
            HengdaUser hengdaUser = hendaUserService.getUserById(uid);
            //根据Userid判断是否是新用户
            if(hengdaUser!=null){
                //如果是老用户  根据userid 更新用户信息
                logger.info("根据Userid查到数据库中有记录!!!,该客户为老客户");
                HengdaUser hengdaUser1 = new HengdaUser(uid,username,phone,channel);
                hendaUserService.updateUser(hengdaUser1);
                ret.setCode("0");
                ret.setContent("{'isOldCus':'0','userid':'"+uid+"'}");
            }else{
                //如果是新用户  根据手机号和姓名再次确认是否是老客户
                HengdaUser hengdaUser2 = hendaUserService.getUserByPhone(phone);
                if(hengdaUser2==null){
                    //手机号和姓名确认查无此人  将头像插入人脸库  基本信息插入用户表
                    logger.info("该客户为新客户");
                    HengdaUser hengdaUser3 = new HengdaUser(uid,username,phone,channel);
                    iFaceService.faceSetAddUser(client, uid,username, files, group);
                    hendaUserService.addUser(hengdaUser3);
                    ret.setCode("0");
                    ret.setContent("{'isOldCus':'1','userid':'"+uid+"'}");
                }else{
                    //手机号和姓名查到有该用户  将头像新增到人脸库该用户下 更新基本信息
                    logger.info("人脸识别分数不够,根据手机号查到数据库中有记录!!!,该客户为老客户");
                    iFaceService.faceSetAddUser(client, hengdaUser2.getUserid(),username, files, group);
                    hendaUserService.updateUser(new HengdaUser(hengdaUser2.getUserid(),username,phone,channel));
                    ret.setCode("0");
                    ret.setContent("{'isOldCus':'0','userid':'"+uid+"'}");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     *
     * @api {GET}  /hengda/chooseConsultant 选择置业顾问
     *  @apiName chooseConsultant
     * @apiGroup facecontroller
     * @apiSuccess {String}  code  0：成功 1：失败
     * @apiSuccess {String}  content  选择成功
     * @apiSuccess {String}  errorMsg  code=1时的失败说明
     * @apiSuccessExample {json}  成功返回示例
     * {
     *     "code":"0",
     *     "content":"选择成功",
     *     "errorMsg":null
     * }
     * @apiParam {String} userid 来访者id
     * @apiParam {String} conid  置业顾问id
     * @apiParamExample {String}  参数实例
     * userid = 1321 & conid = 32135
     * @apiVersion 0.0.0
     */
    @RequestMapping(value = "/chooseConsultant",method = RequestMethod.GET)
    public ReturnMsg chooseConsultant(String userid,String conid){
        //新用户指定置业顾问
        ReturnMsg ret = new ReturnMsg();
        hendaUserService.setConidByUserid(userid,conid);
        ret.setCode("0");
        ret.setContent("选择成功");
        return ret;
    }
}
