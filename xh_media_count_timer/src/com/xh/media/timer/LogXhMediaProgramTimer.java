package com.xh.media.timer;

import java.util.ArrayList;
import java.util.List;

import com.xh.media.bean.ChannelBean;
import com.xh.media.bean.OriginBean;
import com.xh.media.bean.ProgramBean;
import com.xh.media.bean.ProgramLogBean;
import com.xh.media.bean.SourceInteractiveBean;
import com.xh.media.bean.SourceVisitBean;
import com.xh.media.dao.LogXhMediaProgramDao;
import com.xh.media.dao.PublicDao;
import com.xh.media.util.DateUtil;
import com.xh.media.util.DbConn;
import com.xh.media.util.Key;
import com.xh.media.util.Util;

public class LogXhMediaProgramTimer {
	private LogXhMediaProgramDao lxmpd;
	private PublicDao pd;
	private DbConn dbConn;
	
	public LogXhMediaProgramTimer(){
		dbConn = new DbConn(Key.COUNT_DATABASE[0], Key.COUNT_DATABASE[1], Key.COUNT_DATABASE[2]);
		lxmpd = new LogXhMediaProgramDao(dbConn);
		pd = new PublicDao(dbConn);
	}
	
	public void TimerMainNomarl(){
		List<OriginBean> originList = pd.getOriginAndChannelAndProgram();
		for(OriginBean ob:originList){
			for(ChannelBean cb:ob.getChannelList()){
				for(ProgramBean pb:cb.getProgramList()){
					String[][] timeArr = DateUtil.getDateValueNomarl();
					for(int i=0;i<timeArr.length;i++){
						ProgramLogBean plb = new ProgramLogBean();
						plb.setDate(timeArr[i][3]);
						plb.setHour(timeArr[i][0]);
						plb.setChannelId(cb.getChannelId());
						plb.setChannelName(cb.getChannelName());
						plb.setOriginId(ob.getOriginId());
						plb.setOriginName(ob.getOriginName());
						plb.setGlobalId(pb.getGlobalId());
						plb.setChineseName(pb.getChineseName());
						plb.setPubDate(pb.getPubDate());
						plb.setSource(pb.getSource());
						plb.setType(Integer.parseInt(pb.getType()));
						plb.setSiteCode(cb.getSiteCode());
						int singleTimeLength = 0;
						if(!"".equals(pb.getTimeLength())){
							boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
							if (result == true) 
								singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
						}
						plb.setIp(lxmpd.findIpCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setPlayNumber(lxmpd.findPlayNumberCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setPlayTime(lxmpd.findPlayTimeCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setPv(lxmpd.findPvCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setTimeLength(singleTimeLength*plb.getPlayUserNumber());
						plb.setUv(lxmpd.findUvCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						lxmpd.insertProgramLog(plb);
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
					String[][] timeArr = DateUtil.getDateValueNow();
					for(int i=0;i<timeArr.length;i++){
						ProgramLogBean plb = new ProgramLogBean();
						plb.setDate(timeArr[i][3]);
						plb.setHour(timeArr[i][0]);
						plb.setChannelId(cb.getChannelId());
						plb.setChannelName(cb.getChannelName());
						plb.setOriginId(ob.getOriginId());
						plb.setOriginName(ob.getOriginName());
						plb.setGlobalId(pb.getGlobalId());
						plb.setChineseName(pb.getChineseName());
						plb.setPubDate(pb.getPubDate());
						plb.setSource(pb.getSource());
						plb.setType(Integer.parseInt(pb.getType()));
						plb.setSiteCode(cb.getSiteCode());
						int singleTimeLength = 0;
						boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
						if (result == true) 
							singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
						plb.setIp(lxmpd.findIpCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setPlayNumber(lxmpd.findPlayNumberCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setPlayTime(lxmpd.findPlayTimeCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setPv(lxmpd.findPvCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						plb.setTimeLength(singleTimeLength*plb.getPlayUserNumber());
						plb.setUv(lxmpd.findUvCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[i][1], timeArr[i][2]));
						lxmpd.insertProgramLog(plb);
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
					ProgramLogBean plb = new ProgramLogBean();
					plb.setDate(timeArr[0]);
					plb.setHour(timeArr[1]);
					plb.setMinute(timeArr[2]);
					plb.setChannelId(cb.getChannelId());
					plb.setChannelName(cb.getChannelName());
					plb.setOriginId(ob.getOriginId());
					plb.setOriginName(ob.getOriginName());
					plb.setGlobalId(pb.getGlobalId());
					plb.setChineseName(pb.getChineseName());
					plb.setPubDate(pb.getPubDate());
					plb.setSource(pb.getSource());
					plb.setType(Integer.parseInt(pb.getType()));
					plb.setSiteCode(cb.getSiteCode());
					int singleTimeLength = 0;
					if(!"".equals(pb.getTimeLength())){
						boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
						if (result == true) 
							singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
					}
					plb.setIp(lxmpd.findIpCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					plb.setPlayNumber(lxmpd.findPlayNumberCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					plb.setPlayTime(lxmpd.findPlayTimeCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					plb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					plb.setPv(lxmpd.findPvCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					plb.setTimeLength(singleTimeLength*plb.getPlayUserNumber());
					plb.setUv(lxmpd.findUvCount(ob.getOriginId(), cb.getChannelId(), pb.getGlobalId(), pb.getSiteCode(), timeArr[3], timeArr[4]));
					lxmpd.insertProgramLog(plb);
				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowMinute1(){
		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmpd.getProgramList(timeArr[3], timeArr[4]);
		if(list.size()>0){
			for(SourceVisitBean svb:list){
				ProgramBean pb = pd.getProgramByGlobalId(svb.getGlobalId(), svb.getSiteCode());
				if(pb.getGlobalId() != null){
					String channelId = "";
					channelId = pd.getParentIdByChannelId(svb.getChannelId(), svb.getSiteCode());
					String channelName = "";
					channelName = pd.getChannelNameByChannelId(channelId, svb.getSiteCode());
					if(!"null".equals(channelId) && !"null".equals(channelName))
					{
						ProgramLogBean plb = new ProgramLogBean();
						plb.setDate(timeArr[0]);
						plb.setHour(timeArr[1]);
						plb.setMinute(timeArr[2]);
						plb.setOriginId(svb.getOriginId());
						plb.setOriginName(pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode()));
						plb.setChannelId(channelId);
						plb.setChannelName(channelName);
						plb.setPartId(pb.getChannelId());
						plb.setPartName(pd.getChannelNameByChannelId(svb.getChannelId(), svb.getSiteCode()));
						plb.setGlobalId(pb.getGlobalId());
						plb.setChineseName(pb.getChineseName());
						plb.setPubDate(pb.getPubDate());
						plb.setSource(pb.getSource());
						plb.setType(Integer.parseInt(pb.getType()));
						plb.setSiteCode(pb.getSiteCode());
						int singleTimeLength = 0;
						if(!"".equals(pb.getTimeLength())){
							boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
							if (result == true) 
								singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
						}
						plb.setIp(lxmpd.findIpCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
						plb.setPlayNumber(lxmpd.findPlayNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
						plb.setPlayTime(lxmpd.findPlayTimeCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
						plb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
						plb.setPv(lxmpd.findPvCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
						plb.setTimeLength(singleTimeLength*plb.getPlayUserNumber());
						plb.setUv(lxmpd.findUvCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[3], timeArr[4]));
						lxmpd.insertProgramLog(plb);
					}
				}
			}
			//完成推荐节目的数据合并
			List<ProgramLogBean> notPlbList = new ArrayList<ProgramLogBean>();
			List<ProgramLogBean> plbList = new ArrayList<ProgramLogBean>();
			notPlbList = lxmpd.getAllNotCommendProgramList(timeArr[0], timeArr[1], timeArr[2]);
			plbList = lxmpd.getAllCommendProgramList(timeArr[0], timeArr[1], timeArr[2]);
			for(ProgramLogBean plb:notPlbList){
				lxmpd.insertProgramLogSummary(plb);
			}
			for(ProgramLogBean plb:plbList){
				int count = lxmpd.getIsNeedSummary(plb);
				if(count>0)
					lxmpd.updateProgramLogSummary(plb);
				else if(count == 0)
					lxmpd.insertProgramLogSummary(plb);
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
					String channelName = "";
					channelName = pd.getChannelNameByChannelId(channelId, svb.getSiteCode());
					if(!"null".equals(channelId) && !"null".equals(channelName))
					{
						ProgramLogBean plb = new ProgramLogBean();
						plb.setDate(timeArr[0]);
						plb.setOriginId(svb.getOriginId());
						plb.setOriginName(pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode()));
						plb.setChannelId(channelId);
						plb.setChannelName(channelName);
						plb.setPartId(pb.getChannelId());
						plb.setPartName(pd.getChannelNameByChannelId(svb.getChannelId(), svb.getSiteCode()));
						plb.setGlobalId(pb.getGlobalId());
						plb.setChineseName(pb.getChineseName());
						plb.setPubDate(pb.getPubDate());
						plb.setSource(pb.getSource());
						plb.setType(Integer.parseInt(pb.getType()));
						plb.setSiteCode(svb.getSiteCode());
						int singleTimeLength = 0;
						if(!"".equals(pb.getTimeLength())){
							boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
							if (result == true) 
								singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
						}
						plb.setIp(lxmpd.findIpCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
						plb.setPlayNumber(lxmpd.findPlayNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
						plb.setPlayTime(lxmpd.findPlayTimeCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
						plb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
						plb.setPv(lxmpd.findPvCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
						plb.setTimeLength(singleTimeLength*plb.getPlayUserNumber());
						plb.setUv(lxmpd.findUvCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), timeArr[1], timeArr[2]));
						lxmpd.insertDayProgramLog(plb);
					}
				}
			}
			
			//完成推荐节目的数据合并
			List<ProgramLogBean> notPlbList = new ArrayList<ProgramLogBean>();
			List<ProgramLogBean> plbList = new ArrayList<ProgramLogBean>();
			notPlbList = lxmpd.getDayAllNotCommendProgramList(timeArr[0], "", "");
			plbList = lxmpd.getDayAllCommendProgramList(timeArr[0], "", "");
			for(ProgramLogBean plb:notPlbList){
				lxmpd.insertDayProgramLogSummary(plb);
			}
			for(ProgramLogBean plb:plbList){
				int count = lxmpd.getDayIsNeedSummary(plb);
				if(count>0)
					lxmpd.updateDayProgramLogSummary(plb);
				else if(count == 0)
					lxmpd.insertDayProgramLogSummary(plb);
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	public void TimerMainNowMinuteTest(String start, String end, String date, String hour, String minute){
//		String[] timeArr = DateUtil.getDateValueNowMinute();
		List<SourceVisitBean> list = new ArrayList<SourceVisitBean>();
		list = lxmpd.getProgramList(start, end);
		System.out.println(list.size());
		if(list.size()>0){
			for(SourceVisitBean svb:list){
				ProgramBean pb = pd.getProgramByGlobalId(svb.getGlobalId(), svb.getSiteCode());
				System.out.println("111111111="+svb.getGlobalId()+"="+pb.getChineseName());
				if(pb.getGlobalId() != null){
					System.out.println(svb.getGlobalId()+"="+pb.getChineseName());
					ProgramLogBean plb = new ProgramLogBean();
					plb.setDate(date);
					plb.setHour(hour);
					plb.setMinute(minute);
					plb.setOriginId(svb.getOriginId());
					plb.setOriginName(pd.getOriginNameByOriginId(svb.getOriginId(), svb.getSiteCode()));
					plb.setChannelId(svb.getChannelId());
					plb.setChannelName(pd.getChannelNameByChannelId(svb.getChannelId(), svb.getSiteCode()));
					plb.setGlobalId(pb.getGlobalId());
					plb.setChineseName(pb.getChineseName());
					plb.setPubDate(pb.getPubDate());
					plb.setSource(pb.getSource());
					plb.setType(Integer.parseInt(pb.getType()));
					plb.setSiteCode(pb.getSiteCode());
					int singleTimeLength = 0;
					if(!"".equals(pb.getTimeLength())){
						boolean result=pb.getTimeLength().matches("[0-9]+\\.?[0-9]*");
						if (result == true) 
							singleTimeLength = (int)Float.parseFloat(pb.getTimeLength());
					}
					plb.setIp(lxmpd.findIpCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), start, end));
					plb.setPlayNumber(lxmpd.findPlayNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), start, end));
					plb.setPlayTime(lxmpd.findPlayTimeCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), start, end));
					plb.setPlayUserNumber(lxmpd.findPlayUserNumberCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), start, end));
					plb.setPv(lxmpd.findPvCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), start, end));
					plb.setTimeLength(singleTimeLength*plb.getPlayUserNumber());
					plb.setUv(lxmpd.findUvCount(svb.getOriginId(), svb.getChannelId(), pb.getGlobalId(), svb.getSiteCode(), start, end));
					lxmpd.insertProgramLogTest(plb);
				}
			}
		}
		Util.closeResultSetAndConnection(dbConn);
	}
	
	//处理之前历史节目统计数据合并问题
	public void dealHistoryDataSummary(){
		//处理分时汇总数据
		List<ProgramLogBean> notPlbList = new ArrayList<ProgramLogBean>();
		List<ProgramLogBean> plbList = new ArrayList<ProgramLogBean>();
		notPlbList = lxmpd.getAllNotCommendProgramList();
		plbList = lxmpd.getAllCommendProgramList();
		for(ProgramLogBean plb:notPlbList){
			lxmpd.insertProgramLogSummary(plb);
		}
		for(ProgramLogBean plb:plbList){
			int count = lxmpd.getIsNeedSummary(plb);
			if(count>0)
				lxmpd.updateProgramLogSummary(plb);
			else if(count == 0)
				lxmpd.insertProgramLogSummary(plb);
		}
		//处理日报汇总数据
		List<ProgramLogBean> notPlbList1 = new ArrayList<ProgramLogBean>();
		List<ProgramLogBean> plbList1 = new ArrayList<ProgramLogBean>();
		notPlbList1 = lxmpd.getDayAllNotCommendProgramList();
		plbList1 = lxmpd.getDayAllCommendProgramList();
		for(ProgramLogBean plb:notPlbList1){
			lxmpd.insertDayProgramLogSummary(plb);
		}
		for(ProgramLogBean plb:plbList1){
			int count = lxmpd.getDayIsNeedSummary(plb);
			if(count>0)
				lxmpd.updateDayProgramLogSummary(plb);
			else if(count == 0)
				lxmpd.insertDayProgramLogSummary(plb);
		}
	}
	
}
