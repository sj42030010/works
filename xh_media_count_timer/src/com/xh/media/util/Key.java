package com.xh.media.util;

import java.io.Serializable;

public class Key implements Serializable{
//	public static final String[] COUNT_DATABASE = {"jdbc:mysql://192.168.202.2:3306/xh_media_count?characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull","root","tidecms2008"};//tidecms -p'5Z8AHpWZRheaQ9wEqCbC'
//	public static final String[] TIDE_DATABASE = {"jdbc:mysql://localhost:3306/tidemedia_tongji?characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull","songjian","773726791231"};
	public static final String[] COUNT_DATABASE = {"jdbc:mysql://192.168.1.15:3306/xh_media_count?characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull","tidecms","5Z8AHpWZRheaQ9wEqCbC"};
//	public static final String[] COUNT_DATABASE = {"jdbc:mysql://db.ctv-cloud.com:3306/xh_media_count?characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull","tidecms","5Z8AHpWZRheaQ9wEqCbC"};
//	public static final String[] COUNT_DATABASE = {"jdbc:mysql://localhost:3306/xh_media_count2?characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull","songjian","773726791231"};
//	public static final String[] COUNT_DATABASE = {"jdbc:mysql://localhost:3306/xh_media_count?characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull","tidecms","12345678"};//开发服务器
	public static final String CHANNEL_INFO_PATH = "getChannel";
	public static final String PROGRAM_INFO_PATH = "getProgram";
	
	public static final String[][] MINUTE = {
		{"00","05"},
		{"05","10"},
		{"10","15"},
		{"15","20"},
		{"20","25"},
		{"25","30"},
		{"30","35"},
		{"35","40"},
		{"40","45"},
		{"45","50"},
		{"50","55"},
		{"55","00"}
	};
	
	public static final String[] HOUR = {
		"00",
		"01",
		"02",
		"03",
		"04",
		"05",
		"06",
		"07",
		"08",
		"09",
		"10",
		"11",
		"12",
		"13",
		"14",
		"15",
		"16",
		"17",
		"18",
		"19",
		"20",
		"21",
		"22",
		"23",
	};
}
