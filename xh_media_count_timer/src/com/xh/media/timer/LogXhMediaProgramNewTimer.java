package com.xh.media.timer;

import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.ProgramBean;
import com.xh.media.bean.ProgramLogBean;
import com.xh.media.bean.ProgramLogNewBean;
import com.xh.media.bean.SourceVisitBean;
import com.xh.media.dao.LogXhMediaInteractiveDao;
import com.xh.media.dao.LogXhMediaProgramDao;
import com.xh.media.dao.PublicDao;
import com.xh.media.util.DateUtil;
import com.xh.media.util.DbConn;
import com.xh.media.util.Key;
import com.xh.media.util.Util;

public class LogXhMediaProgramNewTimer {
	private LogXhMediaProgramDao lxmpd;
	private LogXhMediaInteractiveDao lxmid;
	private PublicDao pd;
	private DbConn dbConn;
	
	public LogXhMediaProgramNewTimer(){
		dbConn = new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
		lxmpd = new LogXhMediaProgramDao(dbConn);
		lxmid = new LogXhMediaInteractiveDao(dbConn);
		pd = new PublicDao(dbConn);
	}
	
	public void TimerMainNowMinute(){
		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmpd.getProgramList(timeArr[3], timeArr[4]);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
				ProgramBean pb = pd.getProgramByGlobalId(svb.getGlobalId(), svb.getSiteCode());
				if(pb.getGlobalId() != null){
					String channelId = "";
					channelId = pd.getParentIdByChannelId(svb.getChannelId(), svb.getSiteCode());
					if(!"null".equals(channelId))
					{
						ProgramLogNewBean plnb = new ProgramLogNewBean();
						plnb.setDate(timeArr[0]);
						plnb.setHour(timeArr[1]);
						plnb.setMinute(timeArr[2]);
						plnb.setOriginId(svb.getOriginId());
						plnb.setChannelId(channelId);
						plnb.setPartId(pb.getChannelId());
						plnb.setGlobalId(pb.getGlobalId());
						plnb.setPubDate(pb.getPubDate());
						plnb.setSource(pb.getSource());
						plnb.setSiteCode(pb.getSiteCode());
						int singleTimeLength = 0;
						if(!"".equals(pb.getTimeLength())){
							boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
							if (result == true) 
								singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
						}
						plnb.setPlayNumber(lxmpd.findPlayNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
						plnb.setPlayTime(lxmpd.findPlayTimeCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
						plnb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
						plnb.setTimeLength(singleTimeLength);
						plnb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
						plnb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
						plnb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
						
						lxmpd.insertProgramLogNew(plnb);
					}
				}
			}
			//完成推荐节目的数据合并
			List<ProgramLogNewBean> notPlnbList = new ArrayList<ProgramLogNewBean>();
			List<ProgramLogNewBean> plnbList = new ArrayList<ProgramLogNewBean>();
			notPlnbList = lxmpd.getAllNotCommendProgramNewList(timeArr[0], timeArr[1], timeArr[2]);
			plnbList = lxmpd.getAllCommendProgramNewList(timeArr[0], timeArr[1], timeArr[2]);
			for(ProgramLogNewBean plnb:notPlnbList){
				lxmpd.insertProgramLogNewSummary(plnb);
			}
			for(ProgramLogNewBean plnb:plnbList){
				int count = lxmpd.getIsNeedSummaryNew(plnb);
				if(count>0)
					lxmpd.updateProgramLogNewSummary(plnb);
				else if(count == 0)
					lxmpd.insertProgramLogNewSummary(plnb);
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowDay(){
		String[] timeArr = DateUtil.getDateValueNowDay();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmpd.getProgramList(timeArr[1], timeArr[2]);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
				ProgramBean pb = pd.getProgramByGlobalId(svb.getGlobalId(), svb.getSiteCode());
				if(pb.getGlobalId() != null){
					String channelId = "";
					channelId = pd.getParentIdByChannelId(svb.getChannelId(), svb.getSiteCode());
					if(!"null".equals(channelId))
					{
						ProgramLogNewBean plnb = new ProgramLogNewBean();
						plnb.setDate(timeArr[0]);
						plnb.setOriginId(svb.getOriginId());
						plnb.setChannelId(channelId);
						plnb.setPartId(svb.getChannelId());
						plnb.setGlobalId(svb.getGlobalId());
						plnb.setPubDate(pb.getPubDate());
						plnb.setSource(pb.getSource());
						plnb.setSiteCode(svb.getSiteCode());
						int singleTimeLength = 0;
						if(!"".equals(pb.getTimeLength())){
							boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
							if (result == true) 
								singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
						}
						plnb.setPlayNumber(lxmpd.findPlayNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
						plnb.setPlayTime(lxmpd.findPlayTimeCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
						plnb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
						plnb.setTimeLength(singleTimeLength);
						plnb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[1], timeArr[2]));
						plnb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[1], timeArr[2]));
						plnb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), timeArr[1], timeArr[2]));
						lxmpd.insertDayProgramLogNew(plnb);
					}
				}
			}
			
			//完成推荐节目的数据合并
			List<ProgramLogNewBean> notPlnbList = new ArrayList<ProgramLogNewBean>();
			List<ProgramLogNewBean> plnbList = new ArrayList<ProgramLogNewBean>();
			notPlnbList = lxmpd.getDayAllNotCommendProgramNewList(timeArr[0], "", "");
			plnbList = lxmpd.getDayAllCommendProgramNewList(timeArr[0], "", "");
			for(ProgramLogNewBean plb:notPlnbList){
				lxmpd.insertDayProgramLogNewSummary(plb);
			}
			for(ProgramLogNewBean plnb:plnbList){
				int count = lxmpd.getDayIsNeedSummaryNew(plnb);
				if(count>0)
					lxmpd.updateDayProgramLogNewSummary(plnb);
				else if(count == 0)
					lxmpd.insertDayProgramLogNewSummary(plnb);
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowMinuteHistory(String days, String hours, String minutes, String yesDate, String nowDate){
//		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmpd.getProgramList(yesDate, nowDate);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
				ProgramBean pb = pd.getProgramByGlobalId(svb.getGlobalId(), svb.getSiteCode());
				if(pb.getGlobalId() != null){
					String channelId = "";
					channelId = pd.getParentIdByChannelId(svb.getChannelId(), svb.getSiteCode());
					if(!"null".equals(channelId))
					{
						ProgramLogNewBean plnb = new ProgramLogNewBean();
						plnb.setDate(days);
						plnb.setHour(hours);
						plnb.setMinute(minutes);
						plnb.setOriginId(svb.getOriginId());
						plnb.setChannelId(channelId);
						plnb.setPartId(pb.getChannelId());
						plnb.setGlobalId(pb.getGlobalId());
						plnb.setPubDate(pb.getPubDate());
						plnb.setSource(pb.getSource());
						plnb.setSiteCode(pb.getSiteCode());
						int singleTimeLength = 0;
						if(!"".equals(pb.getTimeLength())){
							boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
							if (result == true) 
								singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
						}
						plnb.setPlayNumber(lxmpd.findPlayNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), yesDate, nowDate));
						plnb.setPlayTime(lxmpd.findPlayTimeCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), yesDate, nowDate));
						plnb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), yesDate, nowDate));
						plnb.setTimeLength(singleTimeLength);
						plnb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), yesDate, nowDate));
						plnb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), yesDate, nowDate));
						plnb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), yesDate, nowDate));
						lxmpd.insertProgramLogNew(plnb);
					}
				}
			}
			//完成推荐节目的数据合并
			List<ProgramLogNewBean> notPlnbList = new ArrayList<ProgramLogNewBean>();
			List<ProgramLogNewBean> plnbList = new ArrayList<ProgramLogNewBean>();
			notPlnbList = lxmpd.getAllNotCommendProgramNewList(days, hours, minutes);
			plnbList = lxmpd.getAllCommendProgramNewList(days, hours, minutes);
			for(ProgramLogNewBean plnb:notPlnbList){
				lxmpd.insertProgramLogNewSummary(plnb);
			}
			for(ProgramLogNewBean plnb:plnbList){
				int count = lxmpd.getIsNeedSummaryNew(plnb);
				if(count>0)
					lxmpd.updateProgramLogNewSummary(plnb);
				else if(count == 0)
					lxmpd.insertProgramLogNewSummary(plnb);
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowDayHistory(String days, String yesDate, String nowDate){
//		String[] timeArr = DateUtil.getDateValueNowDay();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmpd.getProgramList(yesDate, nowDate);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
				ProgramBean pb = pd.getProgramByGlobalId(svb.getGlobalId(), svb.getSiteCode());
				if(pb.getGlobalId() != null){
					String channelId = "";
					channelId = pd.getParentIdByChannelId(svb.getChannelId(), svb.getSiteCode());
					if(!"null".equals(channelId))
					{
						ProgramLogNewBean plnb = new ProgramLogNewBean();
						plnb.setDate(days);
						plnb.setOriginId(svb.getOriginId());
						plnb.setChannelId(channelId);
						plnb.setPartId(pb.getChannelId());
						plnb.setGlobalId(pb.getGlobalId());
						plnb.setPubDate(pb.getPubDate());
						plnb.setSource(pb.getSource());
						plnb.setSiteCode(svb.getSiteCode());
						int singleTimeLength = 0;
						if(!"".equals(pb.getTimeLength())){
							boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
							if (result == true) 
								singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
						}
						plnb.setPlayNumber(lxmpd.findPlayNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), yesDate, nowDate));
						plnb.setPlayTime(lxmpd.findPlayTimeCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), yesDate, nowDate));
						plnb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), yesDate, nowDate));
						plnb.setTimeLength(singleTimeLength);
						plnb.setCommentNumber(lxmid.findCommentNumberCount(pb.getGlobalId(), pb.getSiteCode(), yesDate, nowDate));
						plnb.setShareNumber(lxmid.findShareNumberCount(pb.getGlobalId(), pb.getSiteCode(), yesDate, nowDate));
						plnb.setCollectionNumber(lxmid.findCollectionNumberCount(pb.getGlobalId(), pb.getSiteCode(), yesDate, nowDate));
						lxmpd.insertDayProgramLogNew(plnb);
					}
				}
			}
			
			//完成推荐节目的数据合并
			List<ProgramLogNewBean> notPlnbList = new ArrayList<ProgramLogNewBean>();
			List<ProgramLogNewBean> plnbList = new ArrayList<ProgramLogNewBean>();
			notPlnbList = lxmpd.getDayAllNotCommendProgramNewList(days, "", "");
			plnbList = lxmpd.getDayAllCommendProgramNewList(days, "", "");
			for(ProgramLogNewBean plb:notPlnbList){
				lxmpd.insertDayProgramLogNewSummary(plb);
			}
			for(ProgramLogNewBean plnb:plnbList){
				int count = lxmpd.getDayIsNeedSummaryNew(plnb);
				if(count>0)
					lxmpd.updateDayProgramLogNewSummary(plnb);
				else if(count == 0)
					lxmpd.insertDayProgramLogNewSummary(plnb);
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
}
