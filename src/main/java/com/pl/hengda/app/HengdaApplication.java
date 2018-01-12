package com.pl.hengda.app;

import cn.jsms.api.common.SMSClient;
import com.baidu.aip.face.AipFace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HengdaApplication {

	@Value("${baidu.appid}")
	private String APP_ID;
	@Value("${baidu.apikey}")
	private String API_KEY;
	@Value("${baidu.secretkey}")
	private String SECRET_KEY;
	@Value("${jiguang.mastersecret}")
	private String JG_MASTERSECRET;
	@Value("${jiguang.appkey}")
	private String JG_APPKEY;
	public static void main(String[] args) {
		SpringApplication.run(HengdaApplication.class, args);
	}

	//百度人脸识别Client init
	@Bean(name = "faceClient")
	public AipFace faceClient() {
		// 初始化一个AipFace
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		client.setConnectionTimeoutInMillis(2000);
		return client;
	}
	//极光短信
	@Bean(name="smsClient")
	public SMSClient smsClient(){
		SMSClient smsClient = new SMSClient(JG_MASTERSECRET,JG_APPKEY);
		return smsClient;
	}
}
