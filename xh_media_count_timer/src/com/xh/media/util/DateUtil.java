package com.xh.media.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateUtil {
	private static String[][] hours = {
										{"00","00:00:00","01:00:00"},
										{"01","01:00:00","02:00:00"},
										{"02","02:00:00","03:00:00"},
										{"03","03:00:00","04:00:00"},
										{"04","04:00:00","05:00:00"},
										{"05","05:00:00","06:00:00"},
										{"06","06:00:00","07:00:00"},
										{"07","07:00:00","08:00:00"},
										{"08","08:00:00","09:00:00"},
										{"09","09:00:00","10:00:00"},
										{"10","10:00:00","11:00:00"},
										{"11","11:00:00","12:00:00"},
										{"12","12:00:00","13:00:00"},
										{"13","13:00:00","14:00:00"},
										{"14","14:00:00","15:00:00"},
										{"15","15:00:00","16:00:00"},
										{"16","16:00:00","17:00:00"},
										{"17","17:00:00","18:00:00"},
										{"18","18:00:00","19:00:00"},
										{"19","19:00:00","20:00:00"},
										{"20","20:00:00","21:00:00"},
										{"21","21:00:00","22:00:00"},
										{"22","22:00:00","23:00:00"},
										{"23","23:00:00","00:00:00"},
									  };
	
	private static String[] hours1 = {
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
	
	private static String[][] minutes = {
		{"00","00:00","05:00"},
		{"05","05:00","10:00"},
		{"10","10:00","15:00"},
		{"15","15:00","20:00"},
		{"20","20:00","25:00"},
		{"25","25:00","30:00"},
		{"30","30:00","35:00"},
		{"35","35:00","40:00"},
		{"40","40:00","45:00"},
		{"45","45:00","50:00"},
		{"50","50:00","55:00"},
		{"55","55:00","00:00"},
	  };
	
	public static String[][] getDateValueNomarl(){
		String[][] timeArr = new String[24][4];
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String nowDate = year+"-";
		if(month<10)
			nowDate += "0"+month+"-";
		else
			nowDate += month+"-";
		if(day<10)
			nowDate += "0"+day+"-";
		else
			nowDate += day;
//		System.out.println(nowDate);
		
		c.add(Calendar.DAY_OF_MONTH, -1);
		int yyear = c.get(Calendar.YEAR);
		int ymonth = c.get(Calendar.MONTH)+1;
		int yday = c.get(Calendar.DAY_OF_MONTH);
		String yesDate = yyear+"-";
		if(ymonth<10)
			yesDate += "0"+ymonth+"-";
		else
			yesDate += ymonth+"-";
		if(yday<10)
			yesDate += "0"+yday+"-";
		else
			yesDate += yday;
//		System.out.println(yesDate);
		
		for(int i=0;i<hours.length;i++){
			timeArr[i][3] = yesDate;
			if(i<hours.length-1){
				String startTime = yesDate+" "+hours[i][1];
				String endTime = yesDate+" "+hours[i][2];
				timeArr[i][0] = hours[i][0];
				timeArr[i][1] = startTime;
				timeArr[i][2] = endTime;
			}else{
				String startTime = yesDate+" "+hours[i][1];
				String endTime = nowDate+" "+hours[i][2];
				timeArr[i][0] = hours[i][0];
				timeArr[i][1] = startTime;
				timeArr[i][2] = endTime;
			}
		}
		return timeArr;
	}
	
	public static String[][] getDateValueNow(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		String nowDate = year+"-";
		if(month<10)
			nowDate += "0"+month+"-";
		else
			nowDate += month+"-";
		if(day<10)
			nowDate += "0"+day+"-";
		else
			nowDate += day;
//		System.out.println(nowDate);
		String[][] timeArr = new String[hour+1][4];
		for(int i=0;i<=hour;i++){
			timeArr[i][3] = nowDate;
			if(i<hours.length-1){
				String startTime = nowDate+" "+hours[i][1];
				String endTime = nowDate+" "+hours[i][2];
				timeArr[i][0] = hours[i][0];
				timeArr[i][1] = startTime;
				timeArr[i][2] = endTime;
			}else{
				String startTime = nowDate+" "+hours[i][1];
				String endTime = nowDate+" "+hours[i][2];
				timeArr[i][0] = hours[i][0];
				timeArr[i][1] = startTime;
				timeArr[i][2] = endTime;
			}
		}
		return timeArr;
	}
	
	public static String[] getDateValueNowMinute(){
		Calendar c = Calendar.getInstance();
//		c.add(Calendar.DAY_OF_MONTH, -5);
		c.add(Calendar.MINUTE, -5);
//		c.set(2017, 4, 19,17,0,0);
//		System.out.println(c.getTimeInMillis());
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		String nowDate = year+"-";
		if(month<10)
			nowDate += "0"+month+"-";
		else
			nowDate += month+"-";
		if(day<10)
			nowDate += "0"+day+" ";
		else
			nowDate += day+" ";
		if(hour<10)
			nowDate += "0"+hour+":";
		else
			nowDate += hour+":";
		if(minute<10)
			nowDate += "0"+minute+":00";
		else
			nowDate += minute+":00";
		c.add(Calendar.MINUTE, -5);
		int yyear = c.get(Calendar.YEAR);
		int ymonth = c.get(Calendar.MONTH)+1;
		int yday = c.get(Calendar.DAY_OF_MONTH);
		int yhour = c.get(Calendar.HOUR_OF_DAY);
		int yminute = c.get(Calendar.MINUTE);
		String yesDate = yyear+"-";
		String days = "";
		String hours = "";
		String minutes = "";
		if(ymonth<10)
			yesDate += "0"+ymonth+"-";
		else
			yesDate += ymonth+"-";
		if(yday<10)
			yesDate += "0"+yday;
		else
			yesDate += yday;
		days = yesDate;
		if(yhour<10){
			yesDate += " 0"+yhour+":";
			hours = "0"+yhour;
		}else{
			yesDate += " "+yhour+":";
			hours = yhour+"";
		}
		if(yminute<10){
			yesDate += "0"+yminute+":00";
			minutes = "0"+yminute;
		}else{
			yesDate += yminute+":00";
			minutes = yminute+"";
		}
		String[] timeArr = new String[5];
		timeArr[0] = days;
		timeArr[1] = hours;
		timeArr[2] = minutes;
		timeArr[3] = yesDate;
		timeArr[4] = nowDate;

//		System.out.println(nowDate);
//		String[][] timeArr = new String[hour+1][4];
//		for(int i=0;i<=hour;i++){
//			timeArr[i][3] = nowDate;
//			if(i<hours.length-1){
//				String startTime = nowDate+" "+hours[i][1];
//				String endTime = nowDate+" "+hours[i][2];
//				timeArr[i][0] = hours[i][0];
//				timeArr[i][1] = startTime;
//				timeArr[i][2] = endTime;
//			}else{
//				String startTime = nowDate+" "+hours[i][1];
//				String endTime = nowDate+" "+hours[i][2];
//				timeArr[i][0] = hours[i][0];
//				timeArr[i][1] = startTime;
//				timeArr[i][2] = endTime;
//			}
//		}
		return timeArr;
	}
	
	public static String[] getDateValueNowDay(){
		Calendar c = Calendar.getInstance();
//		c.set(2017, 4, 19,17,0,0);
//		System.out.println(c.getTimeInMillis());
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String nowDate = year+"-";
		if(month<10)
			nowDate += "0"+month+"-";
		else
			nowDate += month+"-";
		if(day<10)
			nowDate += "0"+day;
		else
			nowDate += day;
		c.add(Calendar.DAY_OF_MONTH, -1);
		int yyear = c.get(Calendar.YEAR);
		int ymonth = c.get(Calendar.MONTH)+1;
		int yday = c.get(Calendar.DAY_OF_MONTH);
		int yhour = c.get(Calendar.HOUR_OF_DAY);
		int yminute = c.get(Calendar.MINUTE);
		String yesDate = yyear+"-";
		String days = "";
		if(ymonth<10)
			yesDate += "0"+ymonth+"-";
		else
			yesDate += ymonth+"-";
		if(yday<10)
			yesDate += "0"+yday;
		else
			yesDate += yday;
		days = yesDate;
		String[] timeArr = new String[3];
		timeArr[0] = days;
		timeArr[1] = yesDate+" 00:00:00";
		timeArr[2] = nowDate+" 00:00:00";

//		System.out.println(nowDate);
//		String[][] timeArr = new String[hour+1][4];
//		for(int i=0;i<=hour;i++){
//			timeArr[i][3] = nowDate;
//			if(i<hours.length-1){
//				String startTime = nowDate+" "+hours[i][1];
//				String endTime = nowDate+" "+hours[i][2];
//				timeArr[i][0] = hours[i][0];
//				timeArr[i][1] = startTime;
//				timeArr[i][2] = endTime;
//			}else{
//				String startTime = nowDate+" "+hours[i][1];
//				String endTime = nowDate+" "+hours[i][2];
//				timeArr[i][0] = hours[i][0];
//				timeArr[i][1] = startTime;
//				timeArr[i][2] = endTime;
//			}
//		}
		return timeArr;
	}
	
	public static String[][] getDateValueAppoint(String date){
		int apYear = Integer.parseInt(date.substring(0,4));
		int apMonth = Integer.parseInt(date.substring(5,7));
		int apDay = Integer.parseInt(date.substring(8,10));
//		System.out.println(apYear+"-"+apMonth+"-"+apDay);
		Calendar c = Calendar.getInstance();
		long nowLong = c.getTimeInMillis();
//		System.out.println(nowLong);
		c.set(apYear, apMonth-1,apDay);
		long appointLong = c.getTimeInMillis();
//		System.out.println(appointLong);
		long differ = nowLong-appointLong;
		int dayNumber = (int)(differ/(24*60*60*1000));
//		System.out.println(dayNumber);
		String[][] timeArr = new String[dayNumber*24][4];
		for(int i=0;i<dayNumber;i++){
			int yyear = c.get(Calendar.YEAR);
			int ymonth = c.get(Calendar.MONTH)+1;
			int yday = c.get(Calendar.DAY_OF_MONTH);
			String yesDate = yyear+"-";
			if(ymonth<10)
				yesDate += "0"+ymonth+"-";
			else
				yesDate += ymonth+"-";
			if(yday<10)
				yesDate += "0"+yday+"-";
			else
				yesDate += yday;
//			System.out.println(yesDate+"====="+i);
			
			c.add(Calendar.DAY_OF_MONTH, 1);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
			int day = c.get(Calendar.DAY_OF_MONTH);
			String nowDate = year+"-";
			if(month<10)
				nowDate += "0"+month+"-";
			else
				nowDate += month+"-";
			if(day<10)
				nowDate += "0"+day+"-";
			else
				nowDate += day;
//			System.out.println(nowDate+"====="+i);
			
			for(int j=0;j<hours.length;j++){
				timeArr[24*i+j][3] = yesDate;
				if(j<hours.length-1){
					String startTime = yesDate+" "+hours[j][1];
					String endTime = yesDate+" "+hours[j][2];
					timeArr[24*i+j][0] = hours[j][0];
					timeArr[24*i+j][1] = startTime;
					timeArr[24*i+j][2] = endTime;
				}else{
					String startTime = yesDate+" "+hours[j][1];
					String endTime = nowDate+" "+hours[j][2];
					timeArr[24*i+j][0] = hours[j][0];
					timeArr[24*i+j][1] = startTime;
					timeArr[24*i+j][2] = endTime;
				}
			}
		}
		return timeArr;
	}
	
	public static void main(String[] args){
//		Timestamp ts = new Timestamp((long)1494821199);
//		System.out.println(ts);
		String[] timeArr = getDateValueNowDay();
		for(int i=0;i<timeArr.length;i++){
			System.out.println(timeArr[i]);
		}
//		Calendar c = Calendar.getInstance();
//		long nowLong = c.getTimeInMillis();
//		System.out.println(nowLong);
//		c.set(2017, 2,3);
//		long apLong = c.getTimeInMillis();
//		System.out.println(apLong);
//		System.out.println(nowLong-apLong);
//		System.out.println((nowLong-apLong)/(24*60*60*1000));
//		int year = c.get(Calendar.YEAR);
//		int month = c.get(Calendar.MONTH)+1;
//		int day = c.get(Calendar.DAY_OF_MONTH);
//		System.out.println(year+"-"+month+"-"+day);
//		String date = "2016-07-09";
//		System.out.println(date.substring(0,4)+"-"+date.substring(5,7)+"-"+date.substring(8,10));
//		String number = "0";
//		int dsa = (int)Float.parseFloat(number);
////		int asd = Integer.parseInt(number);
//		System.out.println(dsa);
	}
}
