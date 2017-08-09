package com.xh.media.timer;

import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.OriginBean;
import com.xh.media.bean.SourceVisitBean;
import com.xh.media.bean.UserLogBean;
import com.xh.media.dao.LogXhMediaUserDao;
import com.xh.media.dao.PublicDao;
import com.xh.media.util.DateUtil;
import com.xh.media.util.DbConn;
import com.xh.media.util.Key;
import com.xh.media.util.Util;

public class LogXhMediaUserTimer {
	private LogXhMediaUserDao lxmud;
	private PublicDao pd;
	private DbConn dbConn;
	
//	public LogXhMediaUserTimer(){
//		dbConn = new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
//		lxmud = new LogXhMediaUserDao(dbConn);
//		pd = new PublicDao(dbConn);
//	}
//	
//	public void TimerMainNomarl(){
//		List<OriginBean> originList = pd.getOrigin();
//		for(OriginBean ob:originList){
//			String[][] timeArr = DateUtil.getDateValueNomarl();
//			for(int i=0;i<timeArr.length;i++){
//				UserLogBean ulb = new UserLogBean();
//				ulb.setDate(timeArr[i][3]);
//				ulb.setHour(timeArr[i][0]);
//				ulb.setOriginId(ob.getOriginId());
//				ulb.setOriginName(ob.getOriginName());
//				ulb.setSiteCode(ob.getSiteCode());
//				ulb.setNewUser(lxmud.findNewUserCount(ob.getOriginId(), ob.getSiteCode(), timeArr[i][1], timeArr[i][2]));
//				ulb.setActiveUser(lxmud.findActiveUserCount(ob.getOriginId(), ob.getSiteCode(), timeArr[i][1], timeArr[i][2]));
//				ulb.setUseLength(0);
//				lxmud.insertUserLog(ulb);
//			}
//		}
//		Util.closeResultSetAndConnection(dbConn);
//	}
//	
//	public void TimerMainNow(){
//		List<OriginBean> originList = pd.getOrigin();
//		for(OriginBean ob:originList){
//			String[][] timeArr = DateUtil.getDateValueNow();
//			for(int i=0;i<timeArr.length;i++){
//				UserLogBean ulb = new UserLogBean();
//				ulb.setDate(timeArr[i][3]);
//				ulb.setHour(timeArr[i][0]);
//				ulb.setOriginId(ob.getOriginId());
//				ulb.setOriginName(ob.getOriginName());
//				ulb.setSiteCode(ob.getSiteCode());
//				ulb.setNewUser(lxmud.findNewUserCount(ob.getOriginId(), ob.getSiteCode(), timeArr[i][1], timeArr[i][2]));
//				ulb.setActiveUser(lxmud.findActiveUserCount(ob.getOriginId(), ob.getSiteCode(), timeArr[i][1], timeArr[i][2]));
//				ulb.setUseLength(0);
//				lxmud.insertUserLog(ulb);
//			}
//		}
//		Util.closeResultSetAndConnection(dbConn);
//	}
//	
//	public void TimerMainNowMinute(){//存在全为零的数据记录需修改
//		List<OriginBean> originList = pd.getOrigin();
//		for(OriginBean ob:originList){
//			String[] timeArr = DateUtil.getDateValueNowMinute();
//			UserLogBean ulb = new UserLogBean();
//			ulb.setDate(timeArr[0]);
//			ulb.setHour(timeArr[1]);
//			ulb.setMinute(timeArr[2]);
//			ulb.setOriginId(ob.getOriginId());
//			ulb.setOriginName(ob.getOriginName());
//			ulb.setSiteCode(ob.getSiteCode());
//			ulb.setNewUser(lxmud.findNewUserCount(ob.getOriginId(), ob.getSiteCode(), timeArr[3], timeArr[4]));
//			ulb.setActiveUser(lxmud.findActiveUserCount(ob.getOriginId(), ob.getSiteCode(), timeArr[3], timeArr[4]));
//			ulb.setUseLength(0);
//			lxmud.insertUserLog(ulb);
//		}
//		Util.closeResultSetAndConnection(dbConn);
//	}
//	
//	public void TimerMainNowMinute1(){
//		String[] timeArr = DateUtil.getDateValueNowMinute();
//		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
//		list = lxmud.getUserList(timeArr[3], timeArr[4]);
//		if(list.size()>0){
//			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
//					UserLogBean ulb = new UserLogBean();
//					ulb.setDate(timeArr[0]);
//					ulb.setHour(timeArr[1]);
//					ulb.setMinute(timeArr[2]);
//					ulb.setOriginId(svb.getOriginId());
//					ulb.setOriginName(originName);
//					ulb.setSiteCode(svb.getSiteCode());
//					ulb.setNewUser(lxmud.findNewUserCount(svb.getOriginId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
//					ulb.setActiveUser(lxmud.findActiveUserCount(svb.getOriginId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
//					ulb.setUseLength(0);
//					lxmud.insertUserLog(ulb);
//				}
//			}
//		}
//		Util.closeResultSetAndConnection(dbConn);
//	}
//	
//	public void TimerMainNowDay(){
//		String[] timeArr = DateUtil.getDateValueNowDay();
//		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
//		list = lxmud.getUserList(timeArr[1], timeArr[2]);
//		if(list.size()>0){
//			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
//					UserLogBean ulb = new UserLogBean();
//					ulb.setDate(timeArr[0]);
//					ulb.setOriginId(svb.getOriginId());
//					ulb.setOriginName(originName);
//					ulb.setSiteCode(svb.getSiteCode());
//					ulb.setNewUser(lxmud.findNewUserCount(svb.getOriginId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
//					ulb.setActiveUser(lxmud.findActiveUserCount(svb.getOriginId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
//					ulb.setUseLength(0);
//					lxmud.insertDayUserLog(ulb);
//				}
//			}
//		}
//		Util.closeResultSetAndConnection(dbConn);
//	}
//	
//	public void TimerMainNowMinuteTest(String start, String end, String date, String hour, String minute){
////		String[] timeArr = DateUtil.getDateValueNowMinute();
//		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
//		list = lxmud.getUserList(start, end);
//		System.out.println(list.size());
//		if(list.size()>0){
//			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				System.out.println("111111111="+svb.getOriginId()+"="+originName);
//				if(!"null".equals(originName)){
//					System.out.println(svb.getOriginId()+"="+originName);
//					UserLogBean ulb = new UserLogBean();
//					ulb.setDate(date);
//					ulb.setHour(hour);
//					ulb.setMinute(minute);
//					ulb.setOriginId(svb.getOriginId());
//					ulb.setOriginName(originName);
//					ulb.setSiteCode(svb.getSiteCode());
//					ulb.setNewUser(lxmud.findNewUserCount(svb.getOriginId(), svb.getSiteCode(), start, end));
//					ulb.setActiveUser(lxmud.findActiveUserCount(svb.getOriginId(), svb.getSiteCode(), start, end));
//					ulb.setUseLength(0);
//					lxmud.insertUserLogTest(ulb);
//				}
//			}
//		}
//		Util.closeResultSetAndConnection(dbConn);
//	}
}
