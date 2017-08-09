package com.xh.media.timer;

import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.ChannelBean;
import com.xh.media.bean.InteractiveLogBean;
import com.xh.media.bean.OriginBean;
import com.xh.media.bean.ProgramBean;
import com.xh.media.bean.SourceInteractiveBean;
import com.xh.media.dao.LogXhMediaInteractiveDao;
import com.xh.media.dao.PublicDao;
import com.xh.media.util.DateUtil;
import com.xh.media.util.DbConn;
import com.xh.media.util.Key;
import com.xh.media.util.Util;

public class LogXhMediaInteractiveTimer {
	private LogXhMediaInteractiveDao lxmid;
	private PublicDao pd;
	private DbConn dbConn;
	
	public LogXhMediaInteractiveTimer(){
		dbConn = new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
		lxmid = new LogXhMediaInteractiveDao(dbConn);
		pd = new PublicDao(dbConn);
	}
	
	public void TimerMainNomarl(){
		List<OriginBean> originList = pd.getOriginAndChannelAndProgram();
		for(OriginBean ob:originList){
			for(ChannelBean cb:ob.getChannelList()){
				for(ProgramBean pb:cb.getProgramList()){
					String[][] timeArr = DateUtil.getDateValueNomarl();
					for(int i=0;i<timeArr.length;i++){
						InteractiveLogBean ilb = new InteractiveLogBean();
						ilb.setDate(timeArr[i][3]);
						ilb.setHour(timeArr[i][0]);
						ilb.setChannelId(cb.getChannelId());
						ilb.setChannelName(cb.getChannelName());
						ilb.setOriginId(ob.getOriginId());
						ilb.setOriginName(ob.getOriginName());
						ilb.setGlobalId(pb.getGlobalId());
						ilb.setChineseName(pb.getChineseName());
						ilb.setSource(pb.getSource());
						ilb.setType(Integer.parseInt(pb.getType()));
						ilb.setSiteCode(pb.getSiteCode());
						ilb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						ilb.setTopicNumber(lxmid.findTopicNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						ilb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						ilb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						lxmid.insertInteractiveLog(ilb);
					}
				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNow(){
		List<OriginBean> originList = pd.getOriginAndChannelAndProgram();
		for(OriginBean ob:originList){
			for(ChannelBean cb:ob.getChannelList()){
				for(ProgramBean pb:cb.getProgramList()){
					String[][] timeArr = DateUtil.getDateValueNomarl();
					for(int i=0;i<timeArr.length;i++){
						InteractiveLogBean ilb = new InteractiveLogBean();
						ilb.setDate(timeArr[i][3]);
						ilb.setHour(timeArr[i][0]);
						ilb.setChannelId(cb.getChannelId());
						ilb.setChannelName(cb.getChannelName());
						ilb.setOriginId(ob.getOriginId());
						ilb.setOriginName(ob.getOriginName());
						ilb.setGlobalId(pb.getGlobalId());
						ilb.setChineseName(pb.getChineseName());
						ilb.setSource(pb.getSource());
						ilb.setType(Integer.parseInt(pb.getType()));
						ilb.setSiteCode(pb.getSiteCode());
						ilb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						ilb.setTopicNumber(lxmid.findTopicNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						ilb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						ilb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						lxmid.insertInteractiveLog(ilb);
					}
				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowMinute(){//存在全为零的数据记录需修改
		List<OriginBean> originList = pd.getOriginAndChannelAndProgram();
		for(OriginBean ob:originList){
			for(ChannelBean cb:ob.getChannelList()){
				for(ProgramBean pb:cb.getProgramList()){
					String[] timeArr = DateUtil.getDateValueNowMinute();
					InteractiveLogBean ilb = new InteractiveLogBean();
					ilb.setDate(timeArr[0]);
					ilb.setHour(timeArr[1]);
					ilb.setMinute(timeArr[2]);
					ilb.setChannelId(cb.getChannelId());
					ilb.setChannelName(cb.getChannelName());
					ilb.setOriginId(ob.getOriginId());
					ilb.setOriginName(ob.getOriginName());
					ilb.setGlobalId(pb.getGlobalId());
					ilb.setChineseName(pb.getChineseName());
					ilb.setSource(pb.getSource());
					ilb.setType(Integer.parseInt(pb.getType()));
					ilb.setSiteCode(pb.getSiteCode());
					ilb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					ilb.setTopicNumber(lxmid.findTopicNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					ilb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					ilb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					lxmid.insertInteractiveLog(ilb);
				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowMinute1(){
		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceInteractiveBean> list = new ArrayList<SourceInteractiveBean>();
		list = lxmid.getInteractiveList(timeArr[3], timeArr[4]);
		if(list.size()>0){
			for(SourceInteractiveBean sib:list){
				ProgramBean pb = pd.getProgramByGlobalId(sib.getGlobalId(), sib.getSiteCode());
				if(pb.getGlobalId() != null){
					InteractiveLogBean ilb = new InteractiveLogBean();
					ilb.setDate(timeArr[0]);
					ilb.setHour(timeArr[1]);
					ilb.setMinute(timeArr[2]);
					ilb.setOriginId(sib.getOriginId());
					ilb.setOriginName(pd.getOriginNameByOriginId(sib.getOriginId(), pb.getSiteCode()));
					ilb.setChannelId(pb.getChannelId());
					ilb.setChannelName(pd.getChannelNameByChannelId(pb.getChannelId(), pb.getSiteCode()));
					ilb.setGlobalId(pb.getGlobalId());
					ilb.setChineseName(pb.getChineseName());
					ilb.setSource(pb.getSource());
					ilb.setType(Integer.parseInt(pb.getType()));
					ilb.setSiteCode(pb.getSiteCode());
					ilb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					ilb.setTopicNumber(lxmid.findTopicNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					ilb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					ilb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					lxmid.insertInteractiveLog(ilb);
				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowMinuteTest(String start, String end, String date, String hour, String minute){
//		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceInteractiveBean> list = new ArrayList<SourceInteractiveBean>();
		list = lxmid.getInteractiveList(start, end);
		System.out.println(list.size());
		if(list.size()>0){
			for(SourceInteractiveBean sib:list){
				ProgramBean pb = pd.getProgramByGlobalId(sib.getGlobalId(), sib.getSiteCode());
				System.out.println("111111111="+sib.getGlobalId()+"="+pb.getChineseName());
				if(pb.getGlobalId() != null){
					System.out.println(sib.getGlobalId()+"="+pb.getChineseName());
					InteractiveLogBean ilb = new InteractiveLogBean();
					ilb.setDate(date);
					ilb.setHour(hour);
					ilb.setMinute(minute);
					ilb.setOriginId(sib.getOriginId());
					ilb.setOriginName(pd.getOriginNameByOriginId(sib.getOriginId(), pb.getSiteCode()));
					ilb.setChannelId(pb.getChannelId());
					ilb.setChannelName(pd.getChannelNameByChannelId(pb.getChannelId(), pb.getSiteCode()));
					ilb.setGlobalId(pb.getGlobalId());
					ilb.setChineseName(pb.getChineseName());
					ilb.setSource(pb.getSource());
					ilb.setType(Integer.parseInt(pb.getType()));
					ilb.setSiteCode(pb.getSiteCode());
					ilb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), start, end));
					ilb.setTopicNumber(lxmid.findTopicNumberCount(pb.getGlobalId(), pb.getSiteCode(), start, end));
					ilb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), start, end));
					ilb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), start, end));
					lxmid.insertInteractiveLogTest(ilb);
				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowDay(){
		String[] timeArr = DateUtil.getDateValueNowDay();
		List<SourceInteractiveBean> list = new ArrayList<SourceInteractiveBean>();
		list = lxmid.getInteractiveList(timeArr[1], timeArr[2]);
		if(list.size()>0){
			for(SourceInteractiveBean sib:list){
				ProgramBean pb = pd.getProgramByGlobalId(sib.getGlobalId(), sib.getSiteCode());
				if(pb.getGlobalId() != null){
					InteractiveLogBean ilb = new InteractiveLogBean();
					ilb.setDate(timeArr[0]);
					ilb.setOriginId(sib.getOriginId());
					ilb.setOriginName(pd.getOriginNameByOriginId(sib.getOriginId(), pb.getSiteCode()));
					ilb.setChannelId(pb.getChannelId());
					ilb.setChannelName(pd.getChannelNameByChannelId(pb.getChannelId(), pb.getSiteCode()));
					ilb.setGlobalId(pb.getGlobalId());
					ilb.setChineseName(pb.getChineseName());
					ilb.setSource(pb.getSource());
					ilb.setType(Integer.parseInt(pb.getType()));
					ilb.setSiteCode(pb.getSiteCode());
					ilb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[1], timeArr[2]));
					ilb.setTopicNumber(lxmid.findTopicNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[1], timeArr[2]));
					ilb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[1], timeArr[2]));
					ilb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[1], timeArr[2]));
					lxmid.insertDayInteractiveLog(ilb);
				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
}
