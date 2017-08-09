package com.xh.media.timer;

import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.AppCountBean;
import com.xh.media.bean.SourceVisitBean;
import com.xh.media.bean.UserLogBean;
import com.xh.media.dao.LogXhMediaInteractiveDao;
import com.xh.media.dao.LogXhMediaUserDao;
import com.xh.media.dao.LogXhMediaVisitDao;
import com.xh.media.dao.PublicDao;
import com.xh.media.util.DateUtil;
import com.xh.media.util.DbConn;
import com.xh.media.util.Key;
import com.xh.media.util.Util;

public class LogXhMediaAppCountTimer {
	private LogXhMediaUserDao lxmud;
	private LogXhMediaVisitDao lxmvd;
	private LogXhMediaInteractiveDao lxmid;
	private PublicDao pd;
	private DbConn dbConn;
	
	public LogXhMediaAppCountTimer(){
		dbConn = new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
		lxmud = new LogXhMediaUserDao(dbConn);
		lxmid = new LogXhMediaInteractiveDao(dbConn);
		lxmvd = new LogXhMediaVisitDao(dbConn);
		pd = new PublicDao(dbConn);
	}
	
	public void TimerMainNowMinute(){
		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmud.getAppList(timeArr[3], timeArr[4]);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
					AppCountBean acb = new AppCountBean();
					acb.setDate(timeArr[0]);
					acb.setHour(timeArr[1]);
					acb.setMinute(timeArr[2]);
					acb.setOriginId(svb.getOriginId());
					acb.setSiteCode(svb.getSiteCode());
					acb.setNewUser(lxmud.findAppNewUserCount(svb.getOriginId(), timeArr[3], timeArr[4]));
					acb.setStartPv(lxmvd.findAppStartPvCount(svb.getOriginId(), timeArr[3], timeArr[4]));
					acb.setStartUv(lxmvd.findAppStartUvCount(svb.getOriginId(), timeArr[3], timeArr[4]));
					acb.setLocal(lxmvd.findAppLocalCount(svb.getOriginId(), timeArr[3], timeArr[4]));
					acb.setNonLocal(lxmvd.findAppNonLocalCount(svb.getOriginId(), timeArr[3], timeArr[4]));
					acb.setCommentNumber(lxmid.findAppCommentNumberCount(svb.getOriginId(), timeArr[3], timeArr[4]));
					acb.setCollectionNumber(lxmid.findAppCollectionNumberCount(svb.getOriginId(), timeArr[3], timeArr[4]));
					acb.setShareNumber(lxmid.findAppShareNumberCount(svb.getOriginId(), timeArr[3], timeArr[4]));
					acb.setIp(lxmvd.findAppIpCount(svb.getOriginId(), timeArr[3], timeArr[4]));
					lxmud.insertAppCountLog(acb);
//				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowDay(){
		String[] timeArr = DateUtil.getDateValueNowDay();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmud.getAppList(timeArr[1], timeArr[2]);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
					AppCountBean acb = new AppCountBean();
					acb.setDate(timeArr[0]);
					acb.setSiteCode(svb.getSiteCode());
					acb.setOriginId(svb.getOriginId());
					acb.setNewUser(lxmud.findAppNewUserCount(svb.getOriginId(), timeArr[1], timeArr[2]));
					acb.setStartPv(lxmvd.findAppStartPvCount(svb.getOriginId(), timeArr[1], timeArr[2]));
					acb.setStartUv(lxmvd.findAppStartUvCount(svb.getOriginId(), timeArr[1], timeArr[2]));
					acb.setLocal(lxmvd.findAppLocalCount(svb.getOriginId(), timeArr[1], timeArr[2]));
					acb.setNonLocal(lxmvd.findAppNonLocalCount(svb.getOriginId(), timeArr[1], timeArr[2]));
					acb.setCommentNumber(lxmid.findAppCommentNumberCount(svb.getOriginId(), timeArr[1], timeArr[2]));
					acb.setCollectionNumber(lxmid.findAppCollectionNumberCount(svb.getOriginId(), timeArr[1], timeArr[2]));
					acb.setShareNumber(lxmid.findAppShareNumberCount(svb.getOriginId(), timeArr[1], timeArr[2]));
					acb.setIp(lxmvd.findAppIpCount(svb.getOriginId(), timeArr[1], timeArr[2]));
					lxmud.insertDayAppCountLog(acb);
//				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowMinuteHistory(String days, String hours, String minutes, String yesDate, String nowDate){
//		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmud.getAppList(yesDate, nowDate);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
					AppCountBean acb = new AppCountBean();
					acb.setDate(days);
					acb.setHour(hours);
					acb.setMinute(minutes);
					acb.setOriginId(svb.getOriginId());
					acb.setSiteCode(svb.getSiteCode());
					acb.setNewUser(lxmud.findAppNewUserCount(svb.getOriginId(), yesDate, nowDate));
					acb.setStartPv(lxmvd.findAppStartPvCount(svb.getOriginId(), yesDate, nowDate));
					acb.setStartUv(lxmvd.findAppStartUvCount(svb.getOriginId(), yesDate, nowDate));
					acb.setLocal(lxmvd.findAppLocalCount(svb.getOriginId(), yesDate, nowDate));
					acb.setNonLocal(lxmvd.findAppNonLocalCount(svb.getOriginId(), yesDate, nowDate));
					acb.setCommentNumber(lxmid.findAppCommentNumberCount(svb.getOriginId(), yesDate, nowDate));
					acb.setCollectionNumber(lxmid.findAppCollectionNumberCount(svb.getOriginId(), yesDate, nowDate));
					acb.setShareNumber(lxmid.findAppShareNumberCount(svb.getOriginId(), yesDate, nowDate));
					acb.setIp(lxmvd.findAppIpCount(svb.getOriginId(), yesDate, nowDate));
					lxmud.insertAppCountLog(acb);
//				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowDayHistory(String days, String yesDate, String nowDate){
//		timeArr[0] = days;
//		timeArr[1] = yesDate+" 00:00:00";
//		timeArr[2] = nowDate+" 00:00:00";
//		String[] timeArr = DateUtil.getDateValueNowDay();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmud.getAppList(yesDate, nowDate);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
					AppCountBean acb = new AppCountBean();
					acb.setDate(days);
					acb.setOriginId(svb.getOriginId());
					acb.setSiteCode(svb.getSiteCode());
					acb.setNewUser(lxmud.findAppNewUserCount(svb.getOriginId(), yesDate, nowDate));
					acb.setStartPv(lxmvd.findAppStartPvCount(svb.getOriginId(), yesDate, nowDate));
					acb.setStartUv(lxmvd.findAppStartUvCount(svb.getOriginId(), yesDate, nowDate));
					acb.setLocal(lxmvd.findAppLocalCount(svb.getOriginId(), yesDate, nowDate));
					acb.setNonLocal(lxmvd.findAppNonLocalCount(svb.getOriginId(), yesDate, nowDate));
					acb.setCommentNumber(lxmid.findAppCommentNumberCount(svb.getOriginId(), yesDate, nowDate));
					acb.setCollectionNumber(lxmid.findAppCollectionNumberCount(svb.getOriginId(), yesDate, nowDate));
					acb.setShareNumber(lxmid.findAppShareNumberCount(svb.getOriginId(), yesDate, nowDate));
					acb.setIp(lxmvd.findAppIpCount(svb.getOriginId(), yesDate, nowDate));
					lxmud.insertDayAppCountLog(acb);
//				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
}
