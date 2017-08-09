package com.xh.media.main;

import java.sql.Timestamp;

import com.xh.media.timer.LogXhMediaAppCountTimer;
import com.xh.media.timer.LogXhMediaInteractiveTimer;
import com.xh.media.timer.LogXhMediaProgramNewTimer;
import com.xh.media.timer.LogXhMediaProgramTimer;
import com.xh.media.timer.LogXhMediaSiteCountTimer;
import com.xh.media.timer.LogXhMediaUserTimer;
import com.xh.media.timer.LogXhMediaVisitTimer;
import com.xh.media.timer.SourceXhMediaUserVisitLogTimer;

public class DayTimerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println("Day Count Timer is Run at:"+ts);
//		//老得统计处理程序
//		LogXhMediaVisitTimer lxmvt = new LogXhMediaVisitTimer();
//		LogXhMediaProgramTimer lxmpt = new LogXhMediaProgramTimer();
//		LogXhMediaUserTimer lxmut = new LogXhMediaUserTimer();
//		LogXhMediaInteractiveTimer lxmit = new LogXhMediaInteractiveTimer();
//		//定时执行
//		lxmvt.TimerMainNowDay();
//		lxmpt.TimerMainNowDay();
//		lxmut.TimerMainNowDay();
//		lxmit.TimerMainNowDay();
		
		//新的统计处理程序
		LogXhMediaAppCountTimer lxact = new LogXhMediaAppCountTimer();
		LogXhMediaProgramNewTimer lxmpnt = new LogXhMediaProgramNewTimer();
		LogXhMediaSiteCountTimer lxsct = new LogXhMediaSiteCountTimer();
				
		lxmpnt.TimerMainNowDay();
		lxact.TimerMainNowDay();
		lxsct.TimerMainNowDay();
		
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		System.out.println("Day Count Timer is End at:"+ts1);
	}

}
