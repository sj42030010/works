package com.xh.media.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Util {
	public static void closeResultSetAndConnection(DbConn dbConn){
		dbConn.closeResultSet();
		dbConn.closeConnection();
	}
	
	public static String getResponseStr(String siteUrl){
		String str = "";
		try {
			URL url = new URL(siteUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				str += line;
			}
//			Thread.sleep(3000);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String intTransToString(int a){
		StringBuffer str = new StringBuffer("");
		if(a<10)
		{
			str.append("0").append(a);
		}
		else
		{
			str.append(a);
		}
		return str.toString();
	}
	
	public static void main(String[] args){
//		System.out.println(getResponseStr("http://cms.ctv-cloud.com/cms/getChannel?channelId=16045&globalId=1314321"));
		String str = "{\"channelName\":\"新华标准专题2\",\"channelPath\":\"15800_15801_16041_16045_\",\"tableName\":\"channel_s33_content_center_newsubject_topic1\"}";
		Gson gson = new Gson();
		Map map = gson.fromJson(str, Map.class);
		System.out.println(map.get("channelName"));
		System.out.println(map.get("tableName"));
		System.out.println(map.get("channelPath"));
	}
}
