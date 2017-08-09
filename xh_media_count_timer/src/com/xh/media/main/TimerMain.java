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

public class TimerMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println("Count Timer is Run at:"+ts);
//		//老得统计处理程序
//		SourceXhMediaUserVisitLogTimer sxmuvlt = new SourceXhMediaUserVisitLogTimer();
//		LogXhMediaVisitTimer lxmvt = new LogXhMediaVisitTimer();
//		LogXhMediaProgramTimer lxmpt = new LogXhMediaProgramTimer();
//		LogXhMediaUserTimer lxmut = new LogXhMediaUserTimer();
//		LogXhMediaInteractiveTimer lxmit = new LogXhMediaInteractiveTimer();
//		//定时执行
//		sxmuvlt.updateIndexTableAndSourceTable();//跟新频道栏目索引表和节目索引表
//		lxmvt.TimerMainNowMinute1();
//		lxmpt.TimerMainNowMinute1();
//		lxmut.TimerMainNowMinute1();
//		lxmit.TimerMainNowMinute1();
		
//		//测试用只处理今天的数据
//		lxmvt.TimerMainNow();
//		lxmpt.TimerMainNow();
//		lxmut.TimerMainNow();
//		lxmit.TimerMainNow();
		
		
		//新的统计处理程序
		SourceXhMediaUserVisitLogTimer sxmuvlt = new SourceXhMediaUserVisitLogTimer();
		LogXhMediaAppCountTimer lxact = new LogXhMediaAppCountTimer();
		LogXhMediaProgramNewTimer lxmpnt = new LogXhMediaProgramNewTimer();
		LogXhMediaSiteCountTimer lxsct = new LogXhMediaSiteCountTimer();
		
		sxmuvlt.updateIndexTableAndSourceTable();//更新频道栏目索引表和节目索引表
		lxmpnt.TimerMainNowMinute();
		lxact.TimerMainNowMinute();
		lxsct.TimerMainNowMinute();
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		System.out.println("Count Timer is End at:"+ts1);
	}

}
