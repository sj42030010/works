package com.xh.media.util;

import java.sql.Timestamp;

public class Test {
	int b = 1;
	public void str(int a){
		a = a+1;
	}
	
	public static void main(String[] args){
//		String jsonStr = Util.getResponseStr("http://cms.ctv-cloud.com/cms/getChannel?channelId=15859");
//		int strLength = jsonStr.length();
//		int b = 3;
//		int c = 10;
//		float a =(float)b/c;
//		System.out.println(a);
//		System.out.println(String.valueOf((int)(a*100)));
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String nowTime = String.valueOf(ts);
		System.out.println(nowTime);
	}

}
