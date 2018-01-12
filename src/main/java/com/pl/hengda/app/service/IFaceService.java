package com.pl.hengda.app.service;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

import java.util.List;

public interface IFaceService {

    //人脸检测
    JSONObject faceDetection(AipFace client, byte[] file);
    //人脸比对 （支持活体检测）
    JSONObject faceCompare(AipFace client, String imagePath1, String imagePath2);
    //人脸识别 人脸识别返回值不直接判断是否是同一人，只返回用户信息及相似度分值。
    JSONObject faceRecognize(AipFace client, byte[] fiels, String group);
    //人脸认证 （举例，要认证一张图片在指定group中是否为uid1的用户）
    JSONObject faceVerifyUser(AipFace client, String uid, List<String> group, byte[] files);
    //人脸注册 (要注册一个新用户，用户id为uid1，加入组id为group1和group2, 注册成功后服务端会返回操作的logid)
    JSONObject faceSetAddUser(AipFace client, String uid, String user_info, byte[] file, String group);
    //人脸更新
    JSONObject faceSetUpdateUser(AipFace client, String uid, String uer_info, String group, String filePath);
    //人脸删除
    JSONObject faceSetDeleteUser(AipFace client, String uid);
    //用户信息查询
    JSONObject getUser(AipFace client, String uid);
    //组列表查询
    JSONObject getGroupList(AipFace client, Integer start, Integer end);
    //组内用户列表查询
    JSONObject getGroupUsers(AipFace client, String group);

    /*---------语音合成---------*/
    JSONObject baiDuTTS(AipSpeech aipSpeech, String content, String path);

}
