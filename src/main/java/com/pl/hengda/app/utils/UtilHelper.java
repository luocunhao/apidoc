package com.pl.hengda.app.utils;


import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class UtilHelper {
    //base64字符串转byte[]
    public static byte[] base64String2ByteFun(String base64Str){
        return Base64.decodeBase64(base64Str);
    }
    //byte[]转base64
    public static String byte2Base64StringFun(byte[] b){
        return Base64.encodeBase64String(b);
    }
    public static String postRequest(String url, String body) {
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
			return result;
		} catch (Exception e) {
			return null;
		}

	}
	public static String hanzi2pinyin(String name){
		String pinyin = "";
		try {
			pinyin = PinyinHelper.convertToPinyinString(name, "",
					PinyinFormat.WITHOUT_TONE);
		} catch (PinyinException e) {
			e.printStackTrace();
		}
		return pinyin;
	}
	public static String getShortPinyin(String name){
		String pinyin = "";
		try {
			pinyin = PinyinHelper.getShortPinyin(name);
		} catch (PinyinException e) {
			e.printStackTrace();
		}
		return pinyin;
	}

    }
