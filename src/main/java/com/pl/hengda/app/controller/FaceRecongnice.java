package com.pl.hengda.app.controller;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.speech.AipSpeech;
import com.pl.hengda.app.model.ReturnMsg;
import com.pl.hengda.app.model.User;
import com.pl.hengda.app.service.IFaceService;
import com.pl.hengda.app.service.UserService;
import com.pl.hengda.app.utils.UtilHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/hengda")
public class FaceRecongnice {
    private final static Logger logger = LoggerFactory.getLogger(FaceRecongnice.class);
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private IFaceService iFaceService;

    @Autowired
    @Qualifier("faceClient")
    private AipFace client;

    @RequestMapping(value = "/apiface", method = RequestMethod.POST)
    public String uploadBase64Img(@RequestBody String msgbody, HttpServletRequest request) {
        JSONObject msgData = null;
        int code = -1;
        String image;
        try {
            msgData = new JSONObject(msgbody);
            code = msgData.getInt("code");
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        UUID uuid = UUID.randomUUID();
        JSONObject result;
        ReturnMsg ret = new ReturnMsg();
        try {
            switch (code) {
                case 1:
                    image = msgData.getString("image");
                    byte[] files1 = UtilHelper.base64String2ByteFun(image);
                    result = iFaceService.faceDetection(client, files1);
                    logger.info("人脸检测结果：" + result);
                    int face_num = result.getInt("result_num");
                    if (face_num == 1) {
                        JSONObject result0 = result.getJSONArray("result").getJSONObject(0);
                        Double face_probability = result0.getDouble("face_probability");
                        if (face_probability > 0.5) {
                            //人脸质量通过 先检索人脸库是否存在该人脸
                            JSONObject result2 = iFaceService.faceRecognize(client, files1, "vankegroup");
                            JSONObject jo = result2.getJSONArray("result").getJSONObject(0);
                            Double score = jo.getJSONArray("scores").getDouble(0);
                            logger.info("人脸识别匹配得分：" + score);
                            User user = new User();
                            if (score > 80) {
                                String uid = jo.getString("uid");
                                user.setIsoldCus("0");
                                user.setUserid(uid);
                            } else {
                                String uid = uuid.toString().replace("-", "");
                                user.setUserid(uid);
                                user.setIsoldCus("1");
                                userServiceImpl.addUser(user);
                                iFaceService.faceSetAddUser(client, user.getUserid(), "", files1, "vankegroup");
                            }
                            ret.setCode("0");
                            ret.setContent(JSON.toJSONString(user));
                        } else {
                            ret.setCode("1");
                            ret.setErrorMsg("照片质量不行,请重拍");
                        }
                    }
                    break;
                case 2:
                    User user1 = JSON.parseObject(msgData.getString("content"), User.class);
                    String username = user1.getName();
                    String pinyin = UtilHelper.getShortPinyin(username);
                    user1.setPinyin(pinyin);
//				User user1 = new User(userid,username,sex,phone,age,position,department,nickname,vip_flag,pinyin,idcard,reason);
                    userServiceImpl.updateUserById(user1);
                    logger.info("更新数据库用户信息：" + user1.toString());
                    //result = iFaceService.faceSetAddUser(client, userid, username, files, "vankegroup");
                    ret.setCode("0");
                    break;
                case 3:
                    image = msgData.getString("image");
                    byte[] files3 = UtilHelper.base64String2ByteFun(image);
                    JSONObject result2 = iFaceService.faceRecognize(client, files3, "vankegroup");
                    logger.info("人脸识别结果：" + result2);
                    if (result2.length() == 0 || result2.has("error_code")) {
                        ret.setCode("0");
                        User user = new User();
                        user.setVip_flag("3");
                        user.setIsoldCus("1");
                        ret.setContent(JSON.toJSONString(user));
                    } else {
                        JSONObject jo = result2.getJSONArray("result").getJSONObject(0);
                        Double score = jo.getJSONArray("scores").getDouble(0);
                        logger.info("识别得分：" + score);
                        if (score < 80) {
                            ret.setCode("0");
                            User user = new User();
                            user.setIsoldCus("1");
                            user.setVip_flag("3");
                            ret.setContent(JSON.toJSONString(user));
                        } else {
                            String uid = jo.getString("uid");
                            //根据姓名查询返回结果集的第一条
                            User user = userServiceImpl.getUserById(uid);
                            user.setIsoldCus("0");
                            if (user == null) {
                                ret.setCode("1");
                                ret.setContent("数据库中没有找到该人员信息");
                            } else {
                                logger.info("识别结果匹配到的数据库用户：" + user.toString());
                                ret.setCode("0");
                                ret.setContent(JSON.toJSONString(user));
                            }
                        }
                    }
                    break;
                default:
                    ret.setCode("1");
                    ret.setErrorMsg("code错误");
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        // 返回json
        logger.info("BaiduFace Return:" + JSON.toJSONString(ret));
        return JSON.toJSONString(ret);
    }

    @RequestMapping("/test")
    public String test() {
        return "你好呀，小伙子！";
    }

}
