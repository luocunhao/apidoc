package com.pl.hengda.app.webapi;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 向机器人发送请求得到响应
 * @author LiHao
 *
 */
@Component
public class WebRequest {
	private Logger logger = LoggerFactory.getLogger(WebRequest.class);

	/* api POST 请求*/
	public String postRequest(String url, String body) {
		String result ;
		BufferedReader reader ;
		try {
			URL apiUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			connection.connect();

			PrintWriter writer = new PrintWriter(connection.getOutputStream());
			writer.write(body);
			writer.flush();
			writer.close();

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = reader.readLine()) != null){
				buffer.append(line);
			}
			reader.close();
			result = buffer.toString();
			//logger.info("机器人应答：" + result);
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

	/**
	 *  api GET 请求
	 * @param urlNameString
	 * @return
	 */
	private String doGetRequest(String urlNameString) {
		String result ;
		BufferedReader in;
		try {
			URL realUrl =new URL(urlNameString);
			//打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			connection.setRequestMethod("GET");
			//Get请求不需要DoOutPut
			connection.setDoOutput(false);
			connection.setDoInput(true);
			//设置连接超时时间和读取超时时间
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			//connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			//建立实际连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = in.readLine()) != null){
				buffer.append(line);
			}
			in.close();
			result = buffer.toString();
			logger.info("Pulan AI Server response:" + result);
			return result;
		}catch (Exception e){
			logger.error("Pulan AI Server Error："+e.getMessage());
			return null;
		}
	}

	/* GET 请求 pulan Ai Server*/
	public String getAiServerRequest(String url, String param) {
		String result ;
		try {
			String urlNameString = url+URLEncoder.encode(param, "utf-8");
			logger.info("Request for Pulan AI Server:" + urlNameString);
			result =doGetRequest(urlNameString);
		}catch (Exception e) {
			logger.error("Pulan AI Server Error："+e.getMessage());
			result =null;
		}
		return result;
	}

	/* GET 请求 pulan Ai slot Server*/
	public String getAiSlotServerRequest(String url, String param,String type) {
		String result ;
		try {
			String urlNameString =url+URLEncoder.encode(param, "utf-8")+"?type="+type;
			logger.info("Request for Pulan AI Slot Server:" + urlNameString);
			result =doGetRequest(urlNameString);
		}catch (Exception e) {
			logger.error("Pulan AI Server Error："+e.getMessage());
			result =null;
		}
		return result;
	}

	/* POST 请求 HengDa Ai Server*/
	public String getHengDaAiRequest(String url, String body) {
		String result ;
		try {
			result =postRequest(url,body);
		}catch (Exception e) {
			logger.error("Pulan AI Server Error："+e.getMessage());
			result =null;
		}
		return result;
	}



	/*public static void main(String[] args) {
		String url ="http://pulanbd.vicp.io:8800/slot/";
		String url2 ="http://pulanbd.vicp.io:8800/test/";
		String param ="罗丽萍";
		WebRequest request =new WebRequest();
		*//*String rest =request.getRequest(url,param,"person");
		String rest2 =request.getRequest(url2,"我今天的考勤","");*//*
		String url3 ="http://pulanbd.iok.la:8800/hengda_ai";
		JSONObject json =new JSONObject();
		json.put("content","恒大集团");
		json.put("type","ask");
		json.put("error","");
		String rest2 =request.getHengDaAiRequest(url3,json.toJSONString());
		System.out.println(rest2);
	}*/


}
