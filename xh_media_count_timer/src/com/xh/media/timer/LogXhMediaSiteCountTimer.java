package com.xh.media.timer;

import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.SiteCountBean;
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

public class LogXhMediaSiteCountTimer {
	private LogXhMediaUserDao lxmud;
	private LogXhMediaVisitDao lxmvd;
	private LogXhMediaInteractiveDao lxmid;
	private PublicDao pd;
	private DbConn dbConn;
	
	public LogXhMediaSiteCountTimer(){
		dbConn = new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
		lxmud = new LogXhMediaUserDao(dbConn);
		lxmid = new LogXhMediaInteractiveDao(dbConn);
		lxmvd = new LogXhMediaVisitDao(dbConn);
		pd = new PublicDao(dbConn);
	}
	
	public void TimerMainNowMinute(){
		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmud.getSiteList(timeArr[3], timeArr[4]);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
					SiteCountBean acb = new SiteCountBean();
					acb.setDate(timeArr[0]);
					acb.setHour(timeArr[1]);
					acb.setMinute(timeArr[2]);
					acb.setOriginId(svb.getOriginId());
					acb.setSiteCode(svb.getSiteCode());
					acb.setNewUser(lxmud.findSiteNewUserCount(svb.getSiteCode(), timeArr[3], timeArr[4]));
					acb.setStartPv(lxmvd.findSiteStartPvCount(svb.getSiteCode(), timeArr[3], timeArr[4]));
					acb.setStartUv(lxmvd.findSiteStartUvCount(svb.getSiteCode(), timeArr[3], timeArr[4]));
					acb.setLocal(lxmvd.findSiteLocalCount(svb.getSiteCode(), timeArr[3], timeArr[4]));
					acb.setNonLocal(lxmvd.findSiteNonLocalCount(svb.getSiteCode(), timeArr[3], timeArr[4]));
					acb.setCommentNumber(lxmid.findSiteCommentNumberCount(svb.getSiteCode(), timeArr[3], timeArr[4]));
					acb.setCollectionNumber(lxmid.findSiteCollectionNumberCount(svb.getSiteCode(), timeArr[3], timeArr[4]));
					acb.setShareNumber(lxmid.findSiteShareNumberCount(svb.getSiteCode(), timeArr[3], timeArr[4]));
					acb.setIp(lxmvd.findSiteIpCount(svb.getSiteCode(), timeArr[3], timeArr[4]));
					lxmud.insertSiteCountLog(acb);
//				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowDay(){
		String[] timeArr = DateUtil.getDateValueNowDay();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmud.getSiteList(timeArr[1], timeArr[2]);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
					SiteCountBean acb = new SiteCountBean();
					acb.setDate(timeArr[0]);
					acb.setSiteCode(svb.getSiteCode());
					acb.setOriginId(svb.getOriginId());
					acb.setNewUser(lxmud.findSiteNewUserCount(svb.getSiteCode(), timeArr[1], timeArr[2]));
					acb.setStartPv(lxmvd.findSiteStartPvCount(svb.getSiteCode(), timeArr[1], timeArr[2]));
					acb.setStartUv(lxmvd.findSiteStartUvCount(svb.getSiteCode(), timeArr[1], timeArr[2]));
					acb.setLocal(lxmvd.findSiteLocalCount(svb.getSiteCode(), timeArr[1], timeArr[2]));
					acb.setNonLocal(lxmvd.findSiteNonLocalCount(svb.getSiteCode(), timeArr[1], timeArr[2]));
					acb.setCommentNumber(lxmid.findSiteCommentNumberCount(svb.getSiteCode(), timeArr[1], timeArr[2]));
					acb.setCollectionNumber(lxmid.findSiteCollectionNumberCount(svb.getSiteCode(), timeArr[1], timeArr[2]));
					acb.setShareNumber(lxmid.findSiteShareNumberCount(svb.getSiteCode(), timeArr[1], timeArr[2]));
					acb.setIp(lxmvd.findSiteIpCount(svb.getSiteCode(), timeArr[1], timeArr[2]));
					lxmud.insertDaySiteCountLog(acb);
//				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowMinuteHistory(String days, String hours, String minutes, String yesDate, String nowDate){
//		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmud.getSiteList(yesDate, nowDate);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
					SiteCountBean acb = new SiteCountBean();
					acb.setDate(days);
					acb.setHour(hours);
					acb.setMinute(minutes);
					acb.setOriginId(svb.getOriginId());
					acb.setSiteCode(svb.getSiteCode());
					acb.setNewUser(lxmud.findSiteNewUserCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setStartPv(lxmvd.findSiteStartPvCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setStartUv(lxmvd.findSiteStartUvCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setLocal(lxmvd.findSiteLocalCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setNonLocal(lxmvd.findSiteNonLocalCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setCommentNumber(lxmid.findSiteCommentNumberCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setCollectionNumber(lxmid.findSiteCollectionNumberCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setShareNumber(lxmid.findSiteShareNumberCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setIp(lxmvd.findSiteIpCount(svb.getSiteCode(), yesDate, nowDate));
					lxmud.insertSiteCountLog(acb);
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
		list = lxmud.getSiteList(yesDate, nowDate);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
//				String originName = "";
//				originName = pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode());
//				if(!"null".equals(originName)){
					SiteCountBean acb = new SiteCountBean();
					acb.setDate(days);
					acb.setOriginId(svb.getOriginId());
					acb.setSiteCode(svb.getSiteCode());
					acb.setNewUser(lxmud.findSiteNewUserCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setStartPv(lxmvd.findSiteStartPvCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setStartUv(lxmvd.findSiteStartUvCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setLocal(lxmvd.findSiteLocalCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setNonLocal(lxmvd.findSiteNonLocalCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setCommentNumber(lxmid.findSiteCommentNumberCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setCollectionNumber(lxmid.findSiteCollectionNumberCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setShareNumber(lxmid.findSiteShareNumberCount(svb.getSiteCode(), yesDate, nowDate));
					acb.setIp(lxmvd.findSiteIpCount(svb.getSiteCode(), yesDate, nowDate));
					lxmud.insertDaySiteCountLog(acb);
//				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
}
