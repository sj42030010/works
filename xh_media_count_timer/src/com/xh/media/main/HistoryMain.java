package com.xh.media.main;

import java.sql.Timestamp;
import java.util.Calendar;

import com.xh.media.dao.PublicDao;
import com.xh.media.timer.LogXhMediaAppCountTimer;
import com.xh.media.timer.LogXhMediaProgramNewTimer;
import com.xh.media.timer.LogXhMediaSiteCountTimer;
import com.xh.media.timer.SourceXhMediaUserVisitLogTimer;
import com.xh.media.util.DbConn;
import com.xh.media.util.Key;
import com.xh.media.util.Util;

public class HistoryMain {
	private static DbConn dbConn = new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
	private static PublicDao pd = new PublicDao(dbConn);
	
	public static void main(String[] args) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println("Count Timer History is Run at:"+ts);
		LogXhMediaAppCountTimer lxact = new LogXhMediaAppCountTimer();
		LogXhMediaProgramNewTimer lxmpnt = new LogXhMediaProgramNewTimer();
		SourceXhMediaUserVisitLogTimer sxmuvlt = new SourceXhMediaUserVisitLogTimer();
		LogXhMediaSiteCountTimer lxsct = new LogXhMediaSiteCountTimer();
		String time = pd.getFirstVisitTime();
		String date = "";
		String hour = "";
		String minute = "";
		int arrayNo = 0;
		String year = "";
		String month = "";
		String day = "";
		if(!"".equals(time))
		{
			String[] times = time.split(" ");
			if(times.length>1)
			{
				date = times[0];
				String[] dates = date.split("-");
				if(dates.length>2){
					year = dates[0];
					month = dates[1];
					day = dates[2];
				}
				String[] hms = times[1].split(":");
				if(hms.length>2)
				{
					hour = hms[0];
					minute = hms[1];
					arrayNo = Integer.parseInt(minute)/5;
				}
			}
			
			
			Calendar c = Calendar.getInstance();
			c.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(hour),Integer.parseInt(Key.MINUTE[arrayNo][0]),0);
			boolean isRun = true;
			while(isRun)
			{
				int nyear = c.get(Calendar.YEAR);
				int nmonth = c.get(Calendar.MONTH)+1;
				int nday = c.get(Calendar.DAY_OF_MONTH);
				int nhour = c.get(Calendar.HOUR_OF_DAY);
				int nminute = c.get(Calendar.MINUTE);
//				System.out.println(new Timestamp(c.getTimeInMillis()));
				c.add(Calendar.MINUTE, 5);
				int tyear = c.get(Calendar.YEAR);
				int tmonth = c.get(Calendar.MONTH)+1;
				int tday = c.get(Calendar.DAY_OF_MONTH);
				int thour = c.get(Calendar.HOUR_OF_DAY);
				int tminute = c.get(Calendar.MINUTE);
				StringBuffer days = new StringBuffer().append(nyear).append("-").append(Util.intTransToString(nmonth)).append("-").append(Util.intTransToString(nday));
				StringBuffer hours = new StringBuffer(Util.intTransToString(nhour));
				StringBuffer minutes = new StringBuffer(Util.intTransToString(nminute));
				StringBuffer yesDate = new StringBuffer().append(nyear).append("-").append(Util.intTransToString(nmonth)).append("-").append(Util.intTransToString(nday)).append(" ").append(Util.intTransToString(nhour)).append(":").append(Util.intTransToString(nminute)).append(":00");
				StringBuffer nowDate = new StringBuffer().append(tyear).append("-").append(Util.intTransToString(tmonth)).append("-").append(Util.intTransToString(tday)).append(" ").append(Util.intTransToString(thour)).append(":").append(Util.intTransToString(tminute)).append(":00");
				StringBuffer tdays = new StringBuffer().append(tyear).append("-").append(Util.intTransToString(tmonth)).append("-").append(Util.intTransToString(tday));
				StringBuffer dyesDate = new StringBuffer(days).append(" 00:00:00");
				StringBuffer dnowDate = new StringBuffer(tdays).append(" 00:00:00");
//				sxmuvlt.updateIndexTableAndSourceTableHistory(days.toString(), hours.toString(), minutes.toString(), yesDate.toString(), nowDate.toString());
				lxmpnt.TimerMainNowMinuteHistory(days.toString(), hours.toString(), minutes.toString(), yesDate.toString(), nowDate.toString());
				lxact.TimerMainNowMinuteHistory(days.toString(), hours.toString(), minutes.toString(), yesDate.toString(), nowDate.toString());
				lxsct.TimerMainNowMinuteHistory(days.toString(), hours.toString(), minutes.toString(), yesDate.toString(), nowDate.toString());
				System.out.println(days+";;"+hours+";;"+minutes+";;"+yesDate+";;"+nowDate);
				if(nday!=tday)
				{
					lxmpnt.TimerMainNowDayHistory(days.toString(), dyesDate.toString(), dnowDate.toString());
					lxact.TimerMainNowDayHistory(days.toString(), dyesDate.toString(), dnowDate.toString());
					lxsct.TimerMainNowDayHistory(days.toString(), dyesDate.toString(), dnowDate.toString());
					System.out.println(days+";;"+dyesDate+";;"+dnowDate);
				}
				Calendar now = Calendar.getInstance();
				long nowl = c.getTimeInMillis();
				long toml = now.getTimeInMillis();
				if(nowl>toml)
				{
					isRun = false;
				}
			}
		}
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		System.out.println("Count Timer History is End at:"+ts1);
		
//		Calendar c = Calendar.getInstance();
//		c.set(2017,6,26,18,28,0);
//		int year = c.get(Calendar.YEAR);
//		int month = c.get(Calendar.MONTH)+1;
//		int day = c.get(Calendar.DAY_OF_MONTH);
//		int hour = c.get(Calendar.HOUR_OF_DAY);
//		int minute = c.get(Calendar.MINUTE);
//		int second = c.get(Calendar.SECOND);
//		System.out.println(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second);
//		System.out.println(new Timestamp(c.getTimeInMillis()));
	}
}
