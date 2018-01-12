package com.pl.hengda.app.service;

import com.alibaba.fastjson.JSONObject;
import com.pl.hengda.app.model.ReturnMsg;

public interface IMultiDialogService {

    ReturnMsg appMultiDialog(String content, String myAppkey, String userid, int tag ,ReturnMsg returnMsg ,JSONObject result);
}
