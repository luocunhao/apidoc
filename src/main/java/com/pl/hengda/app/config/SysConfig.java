package com.pl.hengda.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
public class SysConfig implements Serializable {

    //图灵API地址
    @Value("${robbot.api}")
    private String robotAPI;
    //图灵apiKey
    @Value("${robbot.apiKey}")
    private String robotApiKey;
    //pulan 语义理解 api
    @Value("${plserver.server-api}")
    private String plServerApi;
    //pulan slot 提取服务 api。
    @Value("${plserver.slot-server-api}")
    private String plSlotApi;
    //pulan 语义 apikey
    @Value("${plserver.apikey}")
    private String plServerApikey;
    //pulan语意理解匹配度
    @Value("${rc_value}")
    private Double rcValue;
    @Value("${data_base}")
    private Integer dataBase;

    public SysConfig(){
    }

    public String getRobotAPI() {
        return robotAPI;
    }

    public void setRobotAPI(String robotAPI) {
        this.robotAPI = robotAPI;
    }

    public String getRobotApiKey() {
        return robotApiKey;
    }

    public void setRobotApiKey(String robotApiKey) {
        this.robotApiKey = robotApiKey;
    }

    public String getPlServerApi() {
        return plServerApi;
    }

    public void setPlServerApi(String plServerApi) {
        this.plServerApi = plServerApi;
    }

    public String getPlSlotApi() {
        return plSlotApi;
    }

    public void setPlSlotApi(String plSlotApi) {
        this.plSlotApi = plSlotApi;
    }

    public String getPlServerApikey() {
        return plServerApikey;
    }

    public void setPlServerApikey(String plServerApikey) {
        this.plServerApikey = plServerApikey;
    }

    public Double getRcValue() {
        return rcValue;
    }

    public void setRcValue(Double rcValue) {
        this.rcValue = rcValue;
    }

    public Integer getDataBase() {
        return dataBase;
    }

    public void setDataBase(Integer dataBase) {
        this.dataBase = dataBase;
    }
}
