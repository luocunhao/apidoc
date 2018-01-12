package com.pl.hengda.app.service.impl;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.pl.hengda.app.service.IFaceService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class FaceServiceImpl implements IFaceService {
    /**
     * 人脸检测
     * @param client
     * @param file
     * @return
     */
    @Override
    public JSONObject faceDetection(AipFace client, byte[] file) {
        // 自定义参数定义
        HashMap<String, String> options = new HashMap<>();
        options.put("max_face_num", "1");
        options.put("face_fields", "expression");
        JSONObject response = client.detect(file,options);
        return response;
    }

    /**
     * 人脸比对
     * @param client
     * @param imagePath1
     * @param imagePath2
     * @return
     */
    @Override
    public JSONObject faceCompare(AipFace client, String imagePath1, String imagePath2) {
        ArrayList<String> pathArray = new ArrayList<String>();
        pathArray.add(imagePath1);
        pathArray.add(imagePath2);
        JSONObject response = client.match(pathArray, new HashMap<>());
        return response;
    }

    /**
     * 人脸识别
     * @param client
     * @param files
     * @param group
     * @return
     */
    @Override
    public JSONObject faceRecognize(AipFace client, byte[] files, String group) {
        HashMap<String, Object> options = new HashMap<>();
        options.put("user_top_num", 1);  //返回用户top数，默认为1，最多返回5个
        JSONObject res = client.identifyUser(Arrays.asList(group), files, options);
        return res;
    }

    /**
     * 人脸认证
     * @param client
     * @param uid
     * @param group
     * @param files
     * @return
     */
    @Override
    public JSONObject faceVerifyUser(AipFace client, String uid, List<String> group, byte[] files) {
        HashMap<String, Object> options = new HashMap<>();
        options.put("top_num", 5); //返回匹配得分top数，默认为1
        JSONObject res = client.verifyUser(uid, group, files, options);
        return res;
    }

    /**
     * 人脸注册
     * @param client
     * @param uid
     * @param user_info
     * @param group
     * @return
     */
    @Override
    public JSONObject faceSetAddUser(AipFace client, String uid, String user_info,byte[] file,String group) {
        HashMap<String, String> options = new HashMap<>();
        JSONObject res = client.addUser(uid, user_info, Arrays.asList(group), file, options);
        return res;
    }

    /**
     *
     * @param client
     * @param uid
     * @param uer_info
     * @param group
     * @param filePath
     * @return
     */
    @Override
    public JSONObject faceSetUpdateUser(AipFace client, String uid, String uer_info,String group, String filePath) {
        HashMap<String, String> options = new HashMap<>();
        JSONObject res = client.updateUser(uid, uer_info, group, filePath, options);
        return res;
    }

    @Override
    public JSONObject faceSetDeleteUser(AipFace client, String uid) {
        // 从人脸库中彻底删除用户
        JSONObject res = client.deleteUser(uid);
        return res;
    }

    @Override
    public JSONObject getUser(AipFace client, String uid) {
        // 查询一个用户在所有组内的信息
        JSONObject res = client.getUser(uid);
        return res;
    }

    @Override
    public JSONObject getGroupList(AipFace client, Integer start, Integer end) {
        HashMap<String, Object> options = new HashMap<>();
        options.put("start", start);
        options.put("end", end);
        JSONObject res = client.getGroupList(options);
        return res;
    }

    @Override
    public JSONObject getGroupUsers(AipFace client, String group) {
        HashMap<String, Object> options = new HashMap<>();
        options.put("start", 0);
        options.put("end", 10);
        JSONObject res = client.getGroupUsers(group, options);
        return res;
    }

    @Override
    public JSONObject baiDuTTS(AipSpeech aipSpeech, String content,String path) {
        // 设置可选参数
        HashMap<String, Object> options = new HashMap<>();
        JSONObject result =new JSONObject();
        options.put("spd", "5"); //语速(0-9)默认5
        options.put("pit", "5"); //音调(0-9)默认5
        options.put("per", "4"); //发声音人
        try {
            TtsResponse res =aipSpeech.synthesis("你好呀百度","zh",1,null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        /*while (res.getData()==null){
            try {
                Thread.sleep(100);
            }catch (Exception e){
            }
        }*/
//        byte[] data = res.getData();
//        JSONObject res1 = res.getResult();
//        if (data != null) {
//            try {
//                Util.writeBytesToFileSystem(data, path+"output.mp3");
//                System.out.println("voiceurl:"+path);
//                result.put("resp",1);
//                result.put("url",path+"output.mp3");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else {
//            System.out.println(res1.toString(2));
//            result.put("resp",-1);
//        }
        return result;
    }
}
