package com.xh.media.main;

import java.sql.Timestamp;

import com.xh.media.dao.LogXhMediaInteractiveDao;
import com.xh.media.dao.LogXhMediaUserDao;
import com.xh.media.dao.LogXhMediaVisitDao;
import com.xh.media.dao.PublicDao;
import com.xh.media.timer.LogXhMediaInteractiveTimer;
import com.xh.media.timer.LogXhMediaProgramTimer;
import com.xh.media.timer.LogXhMediaUserTimer;
import com.xh.media.timer.LogXhMediaVisitTimer;
import com.xh.media.util.DbConn;
import com.xh.media.util.Key;

public class TestMain {
	private static DbConn dbConn= new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
	private static PublicDao pd = new PublicDao(dbConn);
	
	public static void main(String[] args){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println("Count Timer History is Run at:"+ts);
		String time = pd.getFirstVisitTime();
		String[] times = time.split(" ");
		String date = "";
		String hour = "";
		String minute = "";
		if(times.length>1)
		{
			date = times[0];
			String[] hms = times[1].split(":");
			if(hms.length>2)
			{
				hour = hms[0];
				minute = hms[1];
			}
		}
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		System.out.println("Count Timer History is End at:"+ts1);
	}
}
