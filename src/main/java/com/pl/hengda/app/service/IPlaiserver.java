package com.pl.hengda.app.service;

import com.alibaba.fastjson.JSONObject;

public interface IPlaiserver {


    JSONObject robotForHengda(String content,JSONObject result);

    JSONObject roboyForHengdaCallBack(String content,JSONObject result);

    JSONObject introduceHengda(String content,JSONObject result);
}
